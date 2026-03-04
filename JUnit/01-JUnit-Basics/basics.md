# JUnit Basics

This document explains the **fundamentals of JUnit testing in Java** using a very small example application.  
The goal is to understand **how tests are written, executed, and structured**.

---

# 1. What is JUnit?

JUnit is a **testing framework for Java**.  
It allows developers to automatically verify that their code behaves correctly.

Instead of manually running a program and checking output, JUnit lets you write **tests** that run automatically and report whether the behavior is correct.

In simple terms:

> A test is a small program that checks whether another piece of code works as expected.

---

# 2. Example Application: Greeter

We will create a very simple class called `Greeter`.

It has one method that returns a greeting message.

```java
public class Greeter {

    public String greet() {
        return "Hello!";
    }

}
```

This is the **code we want to test**.

---

# 3. Test Class Naming Convention

Test classes usually follow this naming rule:

```
ClassNameTest
```

Example:

```
Greeter.java
GreeterTest.java
```

This makes it easy to identify which test belongs to which class.

---

# 4. Writing a Basic Test

Here is the test class for the `Greeter` example.

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GreeterTest {

    @Test
    void greet_shouldReturnHello() {

        // Arrange
        Greeter greeter = new Greeter();

        // Act
        String message = greeter.greet();

        // Assert
        assertEquals("Hello!", message);
    }

}
```

---

# 5. Understanding the Test Structure

Most tests follow a common pattern called:

```
Arrange
Act
Assert
```

### Arrange
Prepare the objects and data required for the test.

```
Greeter greeter = new Greeter();
```

### Act
Call the method that you want to test.

```
String message = greeter.greet();
```

### Assert
Check whether the result matches the expected value.

```
assertEquals("Hello!", message);
```

If the expected value and actual value are different, the test **fails**.

---

# 6. What Does `@Test` Do?

`@Test` is an **annotation** that tells JUnit that this method is a test.

```java
@Test
void greet_shouldReturnHello()
```

When JUnit runs, it scans classes and executes all methods marked with `@Test`.

Without this annotation, the method will **not be executed as a test**.

---

# 7. How Are Tests Executed?

Test classes do **not** have a `main()` method.

Normal Java programs start execution like this:

```java
public static void main(String[] args)
```

JUnit works differently.

A **JUnit Test Runner (engine)** executes the tests.

When you run tests using:

- IntelliJ / Eclipse
- Maven (`mvn test`)
- Gradle (`gradle test`)

The process looks like this:

```
JUnit Test Runner
       ↓
Find test classes
       ↓
Find methods annotated with @Test
       ↓
Execute them
       ↓
Report results
```

So the **JUnit engine acts as the main program**.

---

# 8. Assertions

Assertions verify whether the program behaves correctly.

JUnit provides many assertion methods.

Common ones:

```
assertEquals(expected, actual)
assertTrue(condition)
assertFalse(condition)
assertNull(object)
assertNotNull(object)
```

Example:

```java
assertEquals("Hello!", message);
```

If the values are different, the test fails.

Example failure message:

```
Expected: Hello!
Actual: Hi!
```

Assertions are the **core of testing**.

---

# 9. Comments in Tests

Comments in test files work exactly like normal Java comments.

### Single-line comment

```java
// This test verifies the greeting message
```

### Multi-line comment

```java
/*
 This test checks that
 the greet() method
 returns the correct message.
*/
```

However, good test names often reduce the need for comments.

Example:

Bad:

```
test1()
```

Better:

```
greet_shouldReturnHello()
```

The name itself explains the purpose.

---

# 10. Test Naming Best Practice

A good naming pattern is:

```
methodName_expectedBehavior
```

Example:

```
greet_shouldReturnHello
```

Other examples:

```
divide_shouldThrowExceptionWhenZero
createUser_shouldSaveUser
withdraw_shouldFailWhenBalanceLow
```

This makes tests readable and self-explanatory.

---

# 11. Test Class Structure

A typical test class looks like this:

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GreeterTest {

    @Test
    void greet_shouldReturnHello() {

        Greeter greeter = new Greeter();
        String message = greeter.greet();

        assertEquals("Hello!", message);
    }

}
```

---

# 12. Project Structure for Tests

In Maven or Gradle projects, tests are placed in:

```
src/test/java
```

Example structure:

```
project
│
├── src
│   ├── main
│   │   └── java
│   │       └── Greeter.java
│   │
│   └── test
│       └── java
│           └── GreeterTest.java
```

Build tools automatically detect and run tests from this directory.

---

# 13. What Happens When a Test Runs?

Internally, the JUnit engine performs steps similar to this:

```
Create test class object
      ↓
Execute test method
      ↓
Evaluate assertions
      ↓
Report result
```

Possible results:

```
✔ Test Passed
✘ Test Failed
```

Most IDEs display this visually.

---

# 14. Small Practice Exercise

Modify the `Greeter` class.

```java
public class Greeter {

    public String greet(String name) {
        return "Hello, " + name + "!";
    }

}
```

Now write tests for:

```
greet("John")
greet("Alice")
greet("Java")
```

Example expected outputs:

```
Hello, John!
Hello, Alice!
Hello, Java!
```

---

# 15. Key Ideas to Remember

1. JUnit is used to **automatically test Java code**.
2. Tests are methods marked with the `@Test` annotation.
3. Tests follow the **Arrange → Act → Assert** pattern.
4. Assertions verify expected behavior.
5. Test classes do **not require a main method**.
6. JUnit Test Runner executes the tests automatically.

---

# 16. Summary

JUnit allows developers to create **automated tests** that verify the correctness of code.

Instead of manually checking output, tests provide:

- repeatability
- reliability
- fast feedback
- better code quality

A simple test can confirm that a method behaves correctly, and many tests together help ensure that a system works as intended.

---

Next topics to explore:

- JUnit Lifecycle Annotations (`@BeforeEach`, `@AfterEach`)
- Testing Exceptions
- Parameterized Tests
- Mockito (mocking dependencies)
