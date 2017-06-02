package com.starter;

import com.starter.dto.JsonConverter;
import com.starter.dto.SessionDto;
import com.starter.service.SecurityService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import scala.Option;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by awells on 2/6/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@PropertySource(value= {"classpath:*.properties"})
@ActiveProfiles("test")
@SpringBootTest()
@Rollback
@Transactional
@WebAppConfiguration
public class SecurityTest {

    private MockMvc mockMvc;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Test
    public void testLogin() throws Exception {

        MvcResult mvcResult = mockMvc.perform(post("/pub/v1/login").param("username", "administrator").param("password", "administrator"))
                .andExpect(status().is2xxSuccessful())
                .andDo(print()).andReturn();
        String jsonString = mvcResult.getResponse().getContentAsString();
        SessionDto sessionDto = JsonConverter.fromJsonDataAsObject(jsonString, SessionDto.class);

        assertTrue(securityService.isValidSession(sessionDto.getSessionUid()));

    }

    @Test
    public void testApiSecurity() throws Exception {

        mockMvc.perform(get("/api/v1/callLog")
                .header("X-Auth-Token", "fake"))
                .andExpect(status().isForbidden())
                .andDo(print()).andReturn();

        Option<SessionDto> administrator = securityService.login("administrator", "administrator", "", "");

        mockMvc.perform(get("/api/v1/callLog")
                .header("X-Auth-Token", administrator.get().getSessionUid()))
                .andExpect(status().is2xxSuccessful())
                .andDo(print()).andReturn();

    }



}