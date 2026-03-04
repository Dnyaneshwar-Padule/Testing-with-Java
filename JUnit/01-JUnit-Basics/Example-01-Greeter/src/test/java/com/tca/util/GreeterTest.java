//Observe that the Greeter class is kept in the com.tca.util package
// And so, the GreeterTest is also kept in the same package com.tca.util (A convention)
package com.tca.util;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class GreeterTest {

	// @Test annotation tells that this is a test case, It's for JUnit Test Engine
	@Test
	void greet_shouldReturnHello() {
		
		// Arrange 
		//Preparing the required objects/data required for test case
		Greeter greeter = new Greeter();
		
		//Act
		//Calling method or functionality that we want to test
		String result = greeter.greet();
		
		//Assert
		//Checking whether the result matches the expected value.
		//assertEquals(expectedValue, actualValue);
		assertEquals("Hello !", result);

	}

}
