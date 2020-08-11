package com.opentext.adf.gateway.valueobject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.opentext.adf.common.valueobject.AdfFieldError;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GatewayResponse {
    private String statusCode;
    private String statusMessage;
    private String echoReference;
    private List<AdfFieldError> errors;

    public String getStatusCode() {
        return statusCode;
    }

    public GatewayResponse setStatusCode(String statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public GatewayResponse setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
        return this;
    }

    public String getEchoReference() {
        return echoReference;
    }

    public GatewayResponse setEchoReference(String echoReference) {
        this.echoReference = echoReference;
        return this;
    }

    public List<AdfFieldError> getErrors() {
        return errors;
    }

    public GatewayResponse setErrors(List<AdfFieldError> errors) {
        this.errors = errors;
        return this;
    }
}
