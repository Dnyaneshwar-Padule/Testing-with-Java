# 03 - Annotation Lifecycle (JUnit)

JUnit provides several **lifecycle annotations** that allow developers to control what happens **before and after tests are executed**.

These annotations are useful when tests require **common setup or cleanup operations**.

For example:

- Creating objects used by multiple tests
- Opening database connections
- Preparing test data
- Cleaning resources after tests finish

Lifecycle annotations help avoid **duplicating the same code in every test method**.

---

# Why Lifecycle Annotations Are Needed

Consider this example without lifecycle annotations:

```java
@Test
void testAddition() {

    Calculator calculator = new Calculator();

    int result = calculator.add(2,3);

    assertEquals(5, result);
}

@Test
void testSubtraction() {

    Calculator calculator = new Calculator();

    int result = calculator.subtract(5,3);

    assertEquals(2, result);
}
```

Here, the `Calculator` object is created repeatedly in every test.

Lifecycle annotations allow us to move such common setup code into **dedicated methods that run automatically**.

---

# Main Lifecycle Annotations in JUnit 5

JUnit provides the following lifecycle annotations:

```
@BeforeEach
@AfterEach
@BeforeAll
@AfterAll
```

These annotations define **when a method should run during the test execution process**.

---

# @BeforeEach

`@BeforeEach` runs **before every test method**.

It is commonly used for **initializing objects or test data**.

Example:

```java
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setup() {
        calculator = new Calculator();
    }

    @Test
    void testAddition() {
        int result = calculator.add(2,3);
        assertEquals(5, result);
    }

    @Test
    void testSubtraction() {
        int result = calculator.subtract(5,3);
        assertEquals(2, result);
    }

}
```

Here, the `setup()` method runs **before each test method**.

---

# @AfterEach

`@AfterEach` runs **after every test method**.

It is typically used for **cleanup operations**, such as:

- closing resources
- resetting values
- clearing temporary data

Example:

```java
@AfterEach
void cleanup() {
    System.out.println("Test completed");
}
```

---

# @BeforeAll

`@BeforeAll` runs **once before all tests in the class**.

This is useful for expensive operations that should only run once.

Examples:

- starting a database
- initializing large resources
- loading configuration

Example:

```java
@BeforeAll
static void init() {
    System.out.println("Starting test suite");
}
```

Important note:

Methods annotated with `@BeforeAll` must be **static**.

This is because they run before any test instance is created.

---

# @AfterAll

`@AfterAll` runs **once after all tests in the class have completed**.

It is used for **final cleanup operations**.

Example:

```java
@AfterAll
static void finish() {
    System.out.println("All tests finished");
}
```

Like `@BeforeAll`, this method must also be **static**.

---

# Lifecycle Execution Order

When a test class runs, JUnit follows this sequence:

```
@BeforeAll
      ↓
@BeforeEach
      ↓
@Test
      ↓
@AfterEach
      ↓
(repeat for each test)
      ↓
@AfterAll
```

Example flow with two tests:

```
@BeforeAll

@BeforeEach
@Test method 1
@AfterEach

@BeforeEach
@Test method 2
@AfterEach

@AfterAll
```

---

# Example Lifecycle Execution

Example test class:

```java
import org.junit.jupiter.api.*;

class LifecycleExampleTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all tests");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Before each test");
    }

    @Test
    void testOne() {
        System.out.println("Test One");
    }

    @Test
    void testTwo() {
        System.out.println("Test Two");
    }

    @AfterEach
    void afterEach() {
        System.out.println("After each test");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After all tests");
    }

}
```

Expected execution order:

```
Before all tests

Before each test
Test One
After each test

Before each test
Test Two
After each test

After all tests
```

---

# When to Use Each Annotation

| Annotation | Purpose |
|--------|--------|
| `@BeforeAll` | Run once before all tests |
| `@BeforeEach` | Run before each test method |
| `@AfterEach` | Run after each test method |
| `@AfterAll` | Run once after all tests |

---

# Summary

JUnit lifecycle annotations help manage **test setup and cleanup operations**.

They prevent duplication of common code and ensure that tests run in a predictable environment.

The most commonly used lifecycle annotation is:

```
@BeforeEach
```

which prepares the test environment before every test execution.

Understanding the test lifecycle is important because many real-world tests require **setup and teardown operations**.
