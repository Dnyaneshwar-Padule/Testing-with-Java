package com.tca.util;

public class MathUtil {

	public boolean isPrime(int num) {
		int i;
		for(i = 2; i < num && (num % i) != 0; i++);
		return num == i;
	}
	
}
