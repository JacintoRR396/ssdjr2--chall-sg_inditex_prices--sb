package com.ssdjr2.chall.sg.inditex_prices.config.advice;

import jakarta.annotation.Nonnull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;
import java.util.Objects;

@ControllerAdvice(basePackages = "com.ssdjr2.chall.sg.inditex_prices.controller")
public class RequestBodyLoggerAdvice extends RequestBodyAdviceAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger( RequestBodyLoggerAdvice.class );

	@Override
	public boolean supports(
			@Nonnull MethodParameter methodParameter,
			@Nonnull Type targetType,
			@Nonnull Class<? extends HttpMessageConverter<?>> converterType	) {
		return true;
	}

	@Override
	public Object afterBodyRead(
			Object body,
			@Nonnull HttpInputMessage inputMessage,
			@Nonnull MethodParameter parameter,
			@Nonnull Type targetType,
			@Nonnull Class<? extends HttpMessageConverter<?>> converterType	) {
		String loggableBody = Objects.nonNull(body) ? body.toString() : "[EMPTY BODY]";
		LOGGER.info("Request DTO: {}", loggableBody);

		return body;
	}
}
