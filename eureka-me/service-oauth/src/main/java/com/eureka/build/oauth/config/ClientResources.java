package com.eureka.build.oauth.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.*;
import org.springframework.boot.context.properties.*;
import org.springframework.security.oauth2.client.token.grant.code.*;

public class ClientResources {

    @NestedConfigurationProperty
    private AuthorizationCodeResourceDetails client = new AuthorizationCodeResourceDetails();

    @NestedConfigurationProperty
    private ResourceServerProperties resource = new ResourceServerProperties();

    public AuthorizationCodeResourceDetails getClient() {
        return client;
    }

    public ResourceServerProperties getResource() {
        return resource;
    }

}
