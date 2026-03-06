package com.tca.util;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestDemo {

	private Demo demo;
	
	@BeforeAll
	public static void beforeAllMethod() {
		System.out.println("In BeforeAll");
		System.out.println("Initiating test environment !");
		System.out.println("----------------------------------");
	}
	
	
	@BeforeEach
	public void beforeEachMethod() {
		System.out.println("In BeforeEach");
		System.out.println("Creating independent test resources !");
		System.out.println("-----------------------------------");
		demo = new Demo(); // It's like a test resource
	}
	
	@Test
	public void TestA() {
		System.out.println("Test A is executed !");
		System.out.println("----------------------------------");
		assertTrue(true);
	}
	
	
	@Test
	public void TestB() {
		System.out.println("Test B is executed !");
		System.out.println("----------------------------------");
		assertTrue(true);
	}
	
	public void afterEachMethod()	{	
		System.out.println("In AfterEach");
		System.out.println("Cleanning up the temporary test resources !");
		System.out.println("----------------------------------");
	}
	
	@AfterAll
	public static void afterAllMethod() {
		System.out.println("In AfterAll");
		System.out.println("Cleaning up test environment !");
		System.out.println("----------------------------------");
	}
}
