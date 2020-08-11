package com.opentext.adf.gateway.controller;

import com.opentext.adf.common.config.AdfConfig;
import com.opentext.adf.common.valueobject.AdfFieldError;
import com.opentext.adf.gateway.config.GatewayResponseCode;
import com.opentext.adf.gateway.valueobject.GatewayRequest;
import com.opentext.adf.gateway.valueobject.GatewayResponse;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
public class GatewayController {
    private final Logger logger;
    private final AdfConfig adfConfig;
    /**
     * Spring context injected Message Source.
     */
    private final MessageSource messageSource;

    @Autowired
    GatewayController(Logger logger,
                      AdfConfig adfConfig, MessageSource messageSource) {
        this.adfConfig = adfConfig;
        this.logger = logger;
        this.messageSource = messageSource;
    }

    @PostMapping(path = "/assert", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GatewayResponse> doAssert(@RequestBody GatewayRequest gatewayRequest,
                                                    BindingResult result,
                                                    Locale locale) {
        ResponseEntity<GatewayResponse> responseEntity;
        GatewayResponse gatewayResponse;
        try {
            if (result.getErrorCount() > 0) {
                gatewayResponse = handleValidationErrors(gatewayRequest, result, locale);
                responseEntity = new ResponseEntity<>(gatewayResponse, HttpStatus.BAD_REQUEST);
            } else {
                gatewayResponse = processRequest(gatewayRequest, locale);
                responseEntity = new ResponseEntity<>(gatewayResponse, HttpStatus.OK);
            }
        } catch (Exception ex) {
            logger.error("Handle request exception [%s]", ex);
            gatewayResponse = createGatewayResponse(gatewayRequest, GatewayResponseCode.INTERNAL_SERVER_ERROR, locale);
            responseEntity = new ResponseEntity<>(gatewayResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    GatewayResponse processRequest(GatewayRequest gatewayRequest, Locale locale) {
        return new GatewayResponse()
                .setEchoReference(gatewayRequest.getEchoReference())
                .setStatusCode(GatewayResponseCode.SUCCESS.getStatusCode())
                .setStatusMessage(messageSource.getMessage(GatewayResponseCode.SUCCESS.getStatusMsgCode(),
                        null, GatewayResponseCode.SUCCESS.getStatusMsgCode(), locale));

    }

    GatewayResponse handleValidationErrors(GatewayRequest request, BindingResult result, Locale locale) {
        List<AdfFieldError> fieldErrors = new ArrayList<>();
        result.getFieldErrors().forEach(err ->
                fieldErrors.add(new AdfFieldError(err.getField(), err.getDefaultMessage())));

        return new GatewayResponse()
                .setEchoReference(request.getEchoReference())
                .setStatusCode(GatewayResponseCode.VALIDATION_ERROR.getStatusCode())
                .setStatusMessage(messageSource.getMessage(GatewayResponseCode.VALIDATION_ERROR.getStatusMsgCode(),
                        null, GatewayResponseCode.VALIDATION_ERROR.getStatusMsgCode(), locale))
                .setErrors(fieldErrors);
    }

    GatewayResponse createGatewayResponse(GatewayRequest request, GatewayResponseCode gatewayResponseCode,
                                          Locale locale) {
        return new GatewayResponse()
                .setEchoReference(request.getEchoReference())
                .setStatusCode(gatewayResponseCode.getStatusCode())
                .setStatusMessage(messageSource.getMessage(gatewayResponseCode.getStatusMsgCode(),
                        null, gatewayResponseCode.getStatusMsgCode(), locale));
    }
}
