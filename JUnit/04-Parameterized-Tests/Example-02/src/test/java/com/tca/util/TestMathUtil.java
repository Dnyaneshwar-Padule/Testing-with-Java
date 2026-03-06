package com.tca.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

class TestMathUtil {

	private MathUtil mathUtil;
	
	@BeforeEach
	void setUp() throws Exception {
		mathUtil = new MathUtil();
	}

	@ParameterizedTest
	@CsvFileSource(resources = "/mathUtil_test.csv")
	void isPrime_shouldWorkProperly(int num, boolean result) {
		assertEquals(result, mathUtil.isPrime(num));
	}

}
