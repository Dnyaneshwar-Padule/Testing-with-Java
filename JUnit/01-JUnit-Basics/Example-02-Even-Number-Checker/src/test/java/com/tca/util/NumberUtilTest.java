package com.tca.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NumberUtilTest {

	@Test
	void isEven_shouldReturnTrueForEvenNumber() {
		// Arrange
		NumberUtil numberUtil = new NumberUtil();
		
		// Act
		boolean result = numberUtil.isEven(12);
		
		//Assert
		assertTrue(result);
	
		//one liner
		//assertTrue(new NumberUtil().isEven(12));
		
	}
	
	@Test
	void isEven_shouldReturnFalseForOddNumber() {
		//Arrange
		NumberUtil numberUtil = new NumberUtil();
	
		//Act
		boolean result = numberUtil.isEven(121);
		
		//Assert
		assertFalse(result);
		
		//one liner
		//assertFalse(new NumberUtil().isEven(121));
	}
	
}
