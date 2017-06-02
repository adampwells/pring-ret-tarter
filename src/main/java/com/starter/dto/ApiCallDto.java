package com.starter.dto;

/**
 * Created by adam.wells on 26/08/2016.
 */
public class ApiCallDto {

    private Long apiCallId;
    private String url;
    private Integer httpStatus;
    private String session;
    private String params;
    private String response;
    private String body;
    private String person;

    public Long getApiCallId() {
        return apiCallId;
    }

    public void setApiCallId(Long apiCallId) {
        this.apiCallId = apiCallId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
