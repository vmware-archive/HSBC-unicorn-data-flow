package io.pivotal.cloud.stream.http;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;

/**
 * @author Vinicius Carvalho
 */
@EnableBinding(Sink.class)
public class HttpSink {

	Logger logger = LoggerFactory.getLogger(HttpSink.class);

	@Autowired
	private AsyncRestTemplate template;

	@ServiceActivator(inputChannel = Sink.INPUT)
	public void dispatch(Message message) throws Exception{
		String endpoint = message.getHeaders().get("ENDPOINT").toString();
		String method = message.getHeaders().get("METHOD").toString().toUpperCase();
		Map<String,String> headers = message.getHeaders().get("HTTP_HEADERS",Map.class);
		HttpHeaders httpHeaders = new HttpHeaders();
		for(String key : headers.keySet()){
			httpHeaders.add(key, headers.get(key));
		}
		HttpEntity request = new HttpEntity(message.getPayload(),httpHeaders);

		template.exchange(endpoint, HttpMethod.valueOf(method),request,String.class).addCallback(new ListenableFutureCallback() {
			@Override
			public void onFailure(Throwable throwable) {
				logger.error("Could not send data",throwable);
			}

			@Override
			public void onSuccess(Object o) {
				logger.info(o.toString());
			}
		});



	}

}
