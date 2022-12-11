package com.udacity.pricing;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PricingServiceApplicationTests {

	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();

	@Test
	public void contextLoads() {
	}

	@Test
	public void testRetrievePrices() throws Exception {
		var entity = new HttpEntity<String>(null, headers);
		var response = restTemplate.exchange(
				testCreateUrl("/prices/15"), HttpMethod.GET, entity, String.class);

		String expected = "{\"currency\":\"USD\",\"vehicleId\":15}";
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	@Test
	public void testSearchByVehicleId() throws Exception {
		var entity = new HttpEntity<String>(null, headers);
		var response = restTemplate.exchange(
				testCreateUrl("/prices/search/findByVehicleId?vehicleId=17"), HttpMethod.GET, entity, String.class);

		String expected = "{\"currency\":\"USD\",\"vehicleId\":17}";
		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	private String testCreateUrl(String path) {
		return "http://localhost:" + port + path;
	}

}
