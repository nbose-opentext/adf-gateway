package com.opentext.adf.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opentext.adf.common.exception.AdfException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Json writer wrapper for ADF.
 *
 * @author opentext
 */
@Component
public class AdfJsonWriterImpl implements AdfJsonWriter {
    private final ObjectMapper jsonMapper;

    @Autowired
    AdfJsonWriterImpl() {
        this.jsonMapper = new ObjectMapper();
    }

    /**
     * Gets the json string for the object.
     *
     * @param obj object to serialize to json
     * @return json string
     */
    @Override
    public String toJson(Object obj) {
        try {
            return jsonMapper.writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            throw new AdfException("Error while converting to Json", ex);
        }
    }
}
