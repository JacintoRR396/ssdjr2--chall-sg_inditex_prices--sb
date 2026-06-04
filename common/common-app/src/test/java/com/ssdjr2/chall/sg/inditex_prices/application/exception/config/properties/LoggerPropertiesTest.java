package com.ssdjr2.chall.sg.inditex_prices.application.exception.config.properties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class LoggerPropertiesTest {

	private static final String LOGGER_MSG_OK = "[OK] »";
	private static final String LOGGER_MSG_INFO = "[INFO] »";
	private static final String LOGGER_MSG_WARN = "[WARN] »";
	private static final String LOGGER_MSG_ERROR = "[ERROR] »";
	private static final String LOGGER_MSG_FORMATTER = "{} {} : {}";
	private static final String LOGGER_MSG_DATE_FORMATTER = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

	private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
			.withUserConfiguration(
					LoggerProperties.class,
					PropertyPlaceholderAutoConfiguration.class
			)
			.withInitializer(context ->
					ConfigurationPropertiesBindingPostProcessor.register((BeanDefinitionRegistry) context)
			);

	@Test
	void givenLoggerPropertiesFile_whenContextStarts_thenPropertiesAreMappedCorrectly() {
		this.contextRunner.run(context -> {
			assertThat(context).hasSingleBean(LoggerProperties.class);

			LoggerProperties properties = context.getBean(LoggerProperties.class);

			assertThat(properties.getLogMsgBaseOk()).isEqualTo(LOGGER_MSG_OK);
			assertThat(properties.getLogMsgBaseInfo()).isEqualTo(LOGGER_MSG_INFO);
			assertThat(properties.getLogMsgBaseWarning()).isEqualTo(LOGGER_MSG_WARN);
			assertThat(properties.getLogMsgBaseError()).isEqualTo(LOGGER_MSG_ERROR);

			assertThat(properties.getLogMsgLogFormatter()).isEqualTo(LOGGER_MSG_FORMATTER);
			assertThat(properties.getLogMsgDateFormatter()).isEqualTo(LOGGER_MSG_DATE_FORMATTER);
		});
	}
}
