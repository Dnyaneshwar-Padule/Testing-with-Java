package com.tca.util;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TestMathUtil {

	private MathUtil mathUtil;
	
	@BeforeEach
	void setUp() throws Exception {
		mathUtil = new MathUtil();
	}

	@ParameterizedTest
	@CsvSource({
		"12,0",
		"10398,0"
	})
	void divide_shouldThrow_ArithmeticException(int a, int b) {
		assertThrows(ArithmeticException.class, ()->{
			mathUtil.divide(a, b);
		});
	}

}
