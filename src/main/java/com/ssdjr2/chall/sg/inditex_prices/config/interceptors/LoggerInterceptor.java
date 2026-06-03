package com.ssdjr2.chall.sg.inditex_prices.config.interceptors;

import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class LoggerInterceptor implements HandlerInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggerInterceptor.class);
	private static final String START_TIME_ATTR = "startTime";

	@Override
	public boolean preHandle(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler) {
		if( (handler instanceof HandlerMethod controller) ) {
			LOGGER.info("[INFO-REQ] » {} {}", request.getMethod(), request.getRequestURI());
			LOGGER.info("{}.{} coming into ...", controller.getBean().getClass().getSimpleName(),	controller.getMethod().getName());

			long startTime = System.nanoTime();
			request.setAttribute(START_TIME_ATTR, startTime);
		}

		return true;
	}

	@Override
	public void afterCompletion(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler, Exception ex) {
		if( (handler instanceof HandlerMethod controller) ) {
			Object startTimeAttr = request.getAttribute( START_TIME_ATTR);

			if ( Objects.nonNull( startTimeAttr ) ) {
				long startTime = (Long) startTimeAttr;
				long endTime = System.nanoTime();
				long timeElapsed = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);

				LOGGER.info("{}.{} going out in {} ms ...",	controller.getBean().getClass().getSimpleName(),
						controller.getMethod().getName(),	timeElapsed);
			}
		}
	}
}
