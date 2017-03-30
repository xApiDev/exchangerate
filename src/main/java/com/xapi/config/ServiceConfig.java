package com.xapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
public class ServiceConfig {

    @Value("${exchange.apiProtocol}")
    private String apiProtocol;

    @Value("${exchange.apiDomain}")
    private String apiDomain;

    @Value("${exchange.apiPath}")
    private String apiPath;

    public String getApiProtocol() {
        return apiProtocol;
    }

    public String getApiDomain() {
        return apiDomain;
    }

    public String getApiPath() {
        return apiPath;
    }
}
