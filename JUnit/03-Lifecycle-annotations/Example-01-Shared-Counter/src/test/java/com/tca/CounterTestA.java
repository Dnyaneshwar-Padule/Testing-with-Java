package com.tca;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CounterTestA {
	private int counter;
	
	public CounterTestA() {
		counter = 0;
	}
	
	@Test
	void testA() {
		counter++;
		System.out.println(counter); // 1
		assertEquals(1, counter);
	}
	
	@Test
	void testB() {
		counter++;
		System.out.println(counter); // 1 
		assertEquals(1, counter);
	}

}

/*
 
 	New Instance of CounterTestA is created for every test (i.e. testA, testB)
 
 */