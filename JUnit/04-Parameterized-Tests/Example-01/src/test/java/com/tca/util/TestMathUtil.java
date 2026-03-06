package com.tca.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
		"1,2,3",
		"12,12,24",
		"-12,10,-2",
		"1000,1000,2000",
		"1511,1000, 2511"
	})
	void add_shouldAddTwoIntegers(int a, int b, int ans) {
		assertEquals(ans, mathUtil.add(a, b));
	}

}
