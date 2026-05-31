package com.ssdjr2.chall.sg.inditex_prices.config.advice;

import com.ssdjr2.chall.sg.inditex_prices.config.properties.GlobalProperties;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RequiredArgsConstructor
@ControllerAdvice
public class ResponseBodyLoggerAdvice	implements ResponseBodyAdvice<Object> {

	private static final Logger LOGGER = LoggerFactory.getLogger( ResponseBodyLoggerAdvice.class );

	private final GlobalProperties globalProperties;

	@Override
	public boolean supports(
			@Nonnull MethodParameter returnType,
			@Nonnull Class<? extends HttpMessageConverter<?>> converterType	) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(
			Object body,
			@Nonnull MethodParameter returnType,
			@Nonnull MediaType selectedContentType,
			@Nonnull Class<? extends HttpMessageConverter<?>> selectedConverterType,
			@Nonnull ServerHttpRequest request,
			@Nonnull ServerHttpResponse response
	) {
		LOGGER.info("{}: {}", this.globalProperties.getLogMsgBaseInfoRespBody(), body);

		return body;
	}
}
