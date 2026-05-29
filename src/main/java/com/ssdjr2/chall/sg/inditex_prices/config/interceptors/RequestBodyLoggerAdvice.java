package com.ssdjr2.chall.sg.inditex_prices.config.interceptors;

import com.ssdjr2.chall.sg.inditex_prices.config.properties.GlobalProperties;
import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;

@RequiredArgsConstructor
@ControllerAdvice
public class RequestBodyLoggerAdvice extends RequestBodyAdviceAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger( RequestBodyLoggerAdvice.class );

	private final GlobalProperties globalProperties;

	@Override
	public boolean supports(
			@Nonnull MethodParameter methodParameter,
			@Nonnull Type targetType,
			@Nonnull Class<? extends HttpMessageConverter<?>> converterType	) {
		return true;
	}

	@Override
	public Object afterBodyRead(
			@Nonnull Object body,
			@Nonnull HttpInputMessage inputMessage,
			@Nonnull MethodParameter parameter,
			@Nonnull Type targetType,
			@Nonnull Class<? extends HttpMessageConverter<?>> converterType	) {
		LOGGER.info("{}: {}", this.globalProperties.getLogMsgBaseInfoReqBody(), body);

		return body;
	}
}
