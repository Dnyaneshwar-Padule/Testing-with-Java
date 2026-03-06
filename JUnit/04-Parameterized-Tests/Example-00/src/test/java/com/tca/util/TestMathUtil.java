package com.tca.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TestMathUtil {

	private MathUtil mathUtil;
	
	@BeforeEach
	void initializeMathUtil() {
		mathUtil = new MathUtil();
	}
	
	@ParameterizedTest
	@ValueSource(ints = {2,4,6,8,10,12,14,16,18,20})
	void isEven_shouldReturnTrue(int num) {
		assertTrue(mathUtil.isEven(num));
	}

	@ParameterizedTest
	@ValueSource(ints = {1,3,5,7,11,13,17,19})
	void isEven_shouldReturnFalse(int num) {
		assertFalse(mathUtil.isEven(num));
	}
	
}
