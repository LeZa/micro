package org.springframework.cloud.contract.verifier.tests;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import javax.inject.Inject;
import org.junit.Test;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessage;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierMessaging;
import org.springframework.cloud.contract.verifier.messaging.internal.ContractVerifierObjectMapper;
import org.springframework.cloud.netflix.hystrix.stream.StreamSourceTestBase;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;
import static org.springframework.cloud.contract.verifier.assertion.SpringCloudContractAssertions.assertThat;
import static org.springframework.cloud.contract.verifier.messaging.util.ContractVerifierMessagingUtil.headers;

public class ContractVerifierTest extends StreamSourceTestBase {

	@Inject ContractVerifierMessaging contractVerifierMessaging;
	@Inject ContractVerifierObjectMapper contractVerifierObjectMapper;

	@Test
	public void validate_shouldProduceValidMetricsData() throws Exception {
		// when:
			createMetricsData();

		// then:
			ContractVerifierMessage response = contractVerifierMessaging.receive("hystrixStreamOutput");
			assertThat(response).isNotNull();
			assertThat(response.getHeader("contentType")).isNotNull();
			assertThat(response.getHeader("contentType").toString()).isEqualTo("application/json");
		// and:
			DocumentContext parsedJson = JsonPath.parse(contractVerifierObjectMapper.writeValueAsString(response.getPayload()));
		// and:
			assertOrigin(parsedJson.read("$.origin"));
			assertEvent(parsedJson.read("$.event"));
			assertData(parsedJson.read("$.data"));
	}

}
