package io.pivotal.cloud.stream.http;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.cloud.stream.annotation.Bindings;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Vinicius Carvalho
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HttpSinkApplication.class)
@DirtiesContext
@WebIntegrationTest({"server.port:0"})
public class HttpSinkTest {

	@Autowired
	@Bindings(HttpSink.class)
	private Sink sink;

	@Test
	public void pushMessage() throws Exception{
		Map<String,Object> headers = new HashMap<>();
		headers.put("ENDPOINT","http://push-api.corpdemo.fe.pivotal.io/v1/push");
		headers.put("METHOD","POST");
		Map<String,String> httpHeaders = new HashMap<>();
		httpHeaders.put(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON_VALUE);
		httpHeaders.put(HttpHeaders.AUTHORIZATION,"Basic NzdjMzJjOTItNTUyZC00NzViLWJhYWYtYjUyMWQzYjQ2MDE1OjYzYmU5MjY2LWI0YTItNGYwZC1iNzVhLWM4MzQwN2NmMjNlYg==");
		headers.put("HTTP_HEADERS",httpHeaders);

		sink.input().send(MessageBuilder.createMessage("{\n" +
				"  \"message\": {\n" +
				"    \"body\": \"Message body\"\n" +
				"},\n" +
				" \"target\": {\n" +
				"    \n" +
				"   \n" +
				"    \"interactive-only\": false,     \n" +
				"    \"platform\": \"all\"             \n" +
				"  }\n" +
				"}",new MessageHeaders(headers)));
		Thread.sleep(10000L);
	}
}
