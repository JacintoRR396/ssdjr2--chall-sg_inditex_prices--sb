package com.ssdjr2.chall.sg.inditex_prices.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssdjr2.chall.sg.inditex_prices.config.advice.RequestBodyLoggerAdvice;
import com.ssdjr2.chall.sg.inditex_prices.config.advice.ResponseBodyLoggerAdvice;
import com.ssdjr2.chall.sg.inditex_prices.config.interceptors.LoggerInterceptor;
import com.ssdjr2.chall.sg.inditex_prices.config.properties.GlobalProperties;
import com.ssdjr2.chall.sg.inditex_prices.controller.dto.request.PriceSearchQueryDTO;
import com.ssdjr2.chall.sg.inditex_prices.controller.dto.response.ApplicationDatesResponseDTO;
import com.ssdjr2.chall.sg.inditex_prices.controller.dto.response.MoneyResponseDTO;
import com.ssdjr2.chall.sg.inditex_prices.controller.dto.response.PriceSearchResponseDTO;
import com.ssdjr2.chall.sg.inditex_prices.controller.mapper.RespEntityErrorMapper;
import com.ssdjr2.chall.sg.inditex_prices.exception.handler.GlobalExceptionHandler;
import com.ssdjr2.chall.sg.inditex_prices.factory.FactoryBrand;
import com.ssdjr2.chall.sg.inditex_prices.factory.FactoryDate;
import com.ssdjr2.chall.sg.inditex_prices.factory.FactoryPrice;
import com.ssdjr2.chall.sg.inditex_prices.service.PriceService;
import com.ssdjr2.chall.sg.inditex_prices.util.UDateTimeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(
		controllers = PriceController.class,
		excludeFilters = {
				@ComponentScan.Filter(
						type = FilterType.ASSIGNABLE_TYPE,
						classes = {
								GlobalExceptionHandler.class,
								RequestBodyLoggerAdvice.class,
								ResponseBodyLoggerAdvice.class
						}
				)
		}
)
class PriceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockitoBean
	private LoggerInterceptor loggerInterceptor;

	@MockitoBean
	private GlobalProperties globalProperties;

	@MockitoBean
	private UDateTimeService uDateTimeService;

	@MockitoBean
	private RespEntityErrorMapper respEntityErrorMapper;

	@MockitoBean
	private PriceService priceService;

	@Test
	@DisplayName("Given valid request when search then return OK")
	void givenValidRequest_whenSearch_thenReturnOk() throws Exception {
		PriceSearchQueryDTO request =
				new PriceSearchQueryDTO( FactoryBrand.ZARA_ID, FactoryPrice.PRODUCT_ID_VALID, FactoryDate.test1() );
		ApplicationDatesResponseDTO applicationDatesResp =
				new ApplicationDatesResponseDTO( FactoryDate.test1StartDate(), FactoryDate.test1EndDate() );
		MoneyResponseDTO moneyResp = new MoneyResponseDTO( new BigDecimal("35.50"), "EUR" );
		PriceSearchResponseDTO response = new PriceSearchResponseDTO(
				FactoryBrand.ZARA_ID, FactoryPrice.PRODUCT_ID_VALID, FactoryPrice.PRICE_LIST_1, applicationDatesResp, moneyResp );

		when(priceService.search(any(PriceSearchQueryDTO.class))).thenReturn(response);
		MvcResult result = mockMvc.perform(
						MockMvcRequestBuilders.post( "/prices/search" )
						.contentType( MediaType.APPLICATION_JSON )
						.content( objectMapper.writeValueAsString( request ) ) )
				.andReturn();

		assertEquals( HttpStatus.OK.value(), result.getResponse().getStatus() );

		System.out.println("------ TEST ------------");
		System.out.println(result.getResponse().getContentAsString());
		System.out.println("------ TEST ------------");

		//		andExpect( MockMvcResultMatchers.status().isOk() )
		//		.andExpect( MockMvcResultMatchers.content().json( objectMapper.writeValueAsString( response ) ) )

		//verify(priceService, times(1)).search( request );
	}
}
