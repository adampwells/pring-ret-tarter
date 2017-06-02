package com.starter.controllers.pub;

/**
 * Created by adam.wells on 5/03/2016.
 */

import com.starter.controllers.BaseController;
import com.starter.dto.RestBody;
import com.starter.dto.SessionDto;
import com.starter.service.PersonService;
import com.starter.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import scala.Option;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@RestController
public class LoginController extends BaseController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private PersonService personService;

    @Autowired
    private SecurityService securityService;

    @Value("${project.version}")
    private String projectVersion;

    @RequestMapping(value = "/pub/v1/version", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<RestBody> version(
    ) {

        String gitVersion = "not found!";
        String timeCommitted = "not found!";
        Properties properties = new Properties();
        InputStream is = null;

        try {

            is = this.getClass().getResourceAsStream("/git.properties");
            properties.load(is);
            gitVersion = properties.getProperty("git.commit.id.abbrev");
            timeCommitted = properties.getProperty("git.commit.time");

        } catch (Exception e){
            log.error(e.getMessage());
        } finally {
            if(is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        Map<String,Object> data = new HashMap<>();
        data.put("version", gitVersion);
        data.put("projectVersion", projectVersion);
        data.put("timeCommitted", timeCommitted);

        RestBody body = new RestBody("System version", data);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @RequestMapping(value = "/pub/v1/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<RestBody> login(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password,
            @RequestHeader(value = "User-Agent", required = false) String userAgent,
            HttpServletRequest request
    ) {
        Option<SessionDto> login = securityService.login(username, password, request.getRemoteAddr(), userAgent);
        return withAnyPermission().execute(() -> {
            RestBody body = new RestBody("Login OK", login.get());
            log.info("Successful login for [" + login.toString() + "]");
            return new ResponseEntity<>(body, HttpStatus.OK);
        });
    }

}