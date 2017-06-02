package com.starter.model.log;

import com.starter.dto.ApiCallDto;
import com.starter.model.AbstractEntity;
import com.starter.model.party.Person;
import org.hibernate.envers.Audited;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by adam.wells on 4/06/2016.
 */
@Audited
@Entity
public class ApiCall extends AbstractEntity {

    @Column(columnDefinition = "TEXT")
    private String url;

    private Integer httpStatus;

    private String session;

    @Column(columnDefinition = "TEXT")
    private String params;

    @Column(columnDefinition = "TEXT")
    private String response;

    @Column(columnDefinition = "TEXT")
    private String body;

    @ManyToOne(targetEntity = Person.class)
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getUrl() {
        return url;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    public ApiCallDto asDto(){
        ApiCallDto dto = new ApiCallDto();

        dto.setHttpStatus(httpStatus);
        dto.setUrl(url);
        dto.setBody(body);
        dto.setParams(params);
        dto.setPerson(person.getFirstName() + " " + person.getLastName());
        dto.setResponse(response);
        dto.setHttpStatus(httpStatus);
        dto.setApiCallId(getId());

        return dto;
    }
}
