package com.ssdjr2.chall.sg.inditex_prices.price.service.application.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssdjr2.chall.sg.inditex_prices.application.exception.config.properties.LoggerProperties;
import com.ssdjr2.chall.sg.inditex_prices.application.exception.custom.CustomException;
import com.ssdjr2.chall.sg.inditex_prices.application.exception.error.RespEntityErrorDTO;
import com.ssdjr2.chall.sg.inditex_prices.application.exception.mapper.RespEntityErrorMapper;
import com.ssdjr2.chall.sg.inditex_prices.price.service.application.TestInfrastructureConfig;
import com.ssdjr2.chall.sg.inditex_prices.price.service.application.factory.BrandMother;
import com.ssdjr2.chall.sg.inditex_prices.price.service.application.factory.PriceMother;
import com.ssdjr2.chall.sg.inditex_prices.price.service.application.factory.PriceSearchQueryDTOMother;
import com.ssdjr2.chall.sg.inditex_prices.price.service.application.rest.PriceController;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.dto.search.request.PriceSearchQueryDTO;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.exception.PriceNotFoundException;
import com.ssdjr2.chall.sg.inditex_prices.price.service.domain.ports.input.service.PriceApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(
		controllers = PriceController.class,
		excludeAutoConfiguration = {
				HibernateJpaAutoConfiguration.class,
				DataSourceAutoConfiguration.class
		}
)
@ContextConfiguration(classes = TestInfrastructureConfig.class)
@Import({
		PriceGlobalExceptionHandler.class,
		HttpEncodingAutoConfiguration.class
})
class PriceGlobalExceptionHandlerTest {

	public static final String URL_PATH_SEARCH = "/prices/search";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockitoBean
	private LoggerProperties loggerProperties;

	@MockitoBean
	private RespEntityErrorMapper respEntityErrorMapper;

	@MockitoBean
	private PriceApplicationService priceApplicationService;

	@Test
	void givenValidQueryButNonExistingPrice_whenSearchIsCalled_thenHandlerInterceptsAndReturns404() throws Exception {
		PriceSearchQueryDTO queryBody = PriceSearchQueryDTOMother.createPriceSearchQueryDTOAboutTest1();
		PriceNotFoundException domainException = new PriceNotFoundException(
				BrandMother.ZARA_ID, PriceMother.PRODUCT_ID_VALID);
		RespEntityErrorDTO expectedErrorDto = new RespEntityErrorDTO();
		expectedErrorDto.setHttpStatusCode(404);
		expectedErrorDto.setErrorCode(40401);
		expectedErrorDto.setErrorMessage("Price not Found");
		expectedErrorDto.setExMessage(domainException.getMessage());

		when(priceApplicationService.searchPrice(any(PriceSearchQueryDTO.class))).thenThrow(domainException);
		when(respEntityErrorMapper.toDTO(any(CustomException.class), any(String.class)))
				.thenReturn(expectedErrorDto);
		when(loggerProperties.getLogMsgBaseWarning()).thenReturn("[WARN] »");
		when(loggerProperties.getLogMsgLogFormatter()).thenReturn("{} {} : {}");
		when(loggerProperties.getLogMsgDateFormatter()).thenReturn("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

		ResultActions resultActions = mockMvc.perform(
				MockMvcRequestBuilders.post(URL_PATH_SEARCH)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(queryBody)));

		resultActions
				.andExpect(MockMvcResultMatchers.status().isNotFound())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(expectedErrorDto)));
	}
}
