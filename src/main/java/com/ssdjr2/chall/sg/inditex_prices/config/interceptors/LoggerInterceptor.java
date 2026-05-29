package com.ssdjr2.chall.sg.inditex_prices.config.interceptors;

import com.ssdjr2.chall.sg.inditex_prices.config.properties.GlobalProperties;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@RequiredArgsConstructor
@Component
public class LoggerInterceptor implements HandlerInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggerInterceptor.class);
	private static final String START_TIME_ATTR = "startTime";

	private final GlobalProperties globalProperties;

	@Override
	public boolean preHandle(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler) {
		if( (handler instanceof HandlerMethod controller) ) {
			LOGGER.info("{} {} {}", this.globalProperties.getLogMsgBaseInfoReq(), request.getMethod(), request.getRequestURI());
			LOGGER.info("{}.{} coming into ...", controller.getBean().getClass().getSimpleName(),	controller.getMethod().getName());

			long startTime = System.currentTimeMillis();
			request.setAttribute(START_TIME_ATTR, startTime);
		}

		return true;
	}

	@Override
	public void afterCompletion(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response, @Nonnull Object handler, Exception ex) {
		if( (handler instanceof HandlerMethod controller) ) {
			long startTime = ( Long ) request.getAttribute( START_TIME_ATTR);
			long endTime = System.currentTimeMillis();
			long timeElapsed = endTime - startTime;

			LOGGER.info("{}.{} going out in {} ms ...",	controller.getBean().getClass().getSimpleName(),controller.getMethod().getName(),	timeElapsed);
		}
	}
}
