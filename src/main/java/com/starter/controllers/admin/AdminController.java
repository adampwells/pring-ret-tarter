package com.starter.controllers.admin;

/**
 * Created by adam.wells on 5/03/2016.
 */

import com.starter.controllers.BaseController;
import com.starter.dto.ApiCallDto;
import com.starter.dto.RestBody;
import com.starter.model.log.ApiCall;
import com.starter.repositories.ApiCallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AdminController extends BaseController {

    @Autowired
    private ApiCallRepository apiCallRepository;

    @RequestMapping(value = "/api/v1/callLog",method = RequestMethod.GET)
    public ResponseEntity<RestBody> getMessage() {

        return withPermission("ADMIN").execute(() -> {

            List<ApiCallDto> apiCallDtos = apiCallRepository.findAll().stream().map(ApiCall::asDto).collect(Collectors.toList());
            RestBody body = new RestBody("API Calls", apiCallDtos);
            return new ResponseEntity<>(body, HttpStatus.OK);

        });

    }

}