package com.eureka.me.ribbon.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.client.RestOperations;

import java.util.concurrent.Future;

public interface OauthConnectionService {

	// implementation of this method should be annotated with @Async
	@Async
	public <T> Future<T> getAsynchronousResults(String resourceUrl,
                                                Class<T> resultType, RestOperations restTemplate);

	public <T> T getResults(String resourceUrl, Class<T> resultType,
                            RestOperations restTemplate);

	public <T> T getClientOnlyResults(String resourceUrl, Class<T> resultType,
                                      RestOperations restTemplate);

	default <T> T getForObject(String resourceUrl, Class<T> responseType,
                               RestOperations restTemplate) {
		return restTemplate.getForObject(resourceUrl, responseType);
	}
}
