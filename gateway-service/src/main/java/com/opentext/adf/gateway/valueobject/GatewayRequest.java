package com.opentext.adf.gateway.valueobject;

public class GatewayRequest {
    private String client;
    private String stream;
    private String data ;
    private String echoReference;

    public String getClient() {
        return client;
    }

    public GatewayRequest setClient(String client) {
        this.client = client;
        return this;
    }

    public String getStream() {
        return stream;
    }

    public GatewayRequest setStream(String stream) {
        this.stream = stream;
        return this;
    }

    public String getData() {
        return data;
    }

    public GatewayRequest setData(String data) {
        this.data = data;
        return this;
    }

    public String getEchoReference() {
        return echoReference;
    }

    public GatewayRequest setEchoReference(String echoReference) {
        this.echoReference = echoReference;
        return this;
    }
}
