# Why `@BeforeAll` and `@AfterAll` Are Static in JUnit

Understanding why some lifecycle methods must be `static` requires understanding **how JUnit actually executes tests**.

JUnit follows a design principle called **test isolation**. Each test should behave like an independent experiment so that one test cannot accidentally affect another.

---

# Default Test Instance Lifecycle

By default, JUnit uses the following lifecycle:

```
PER_METHOD
```

This means **JUnit creates a new instance of the test class for every test method**.

Example test class:

```java
class ExampleTest {

    @Test
    void testA() {}

    @Test
    void testB() {}

}
```

Internally JUnit behaves approximately like this:

```
create ExampleTest object
run testA()

create ExampleTest object
run testB()
```

Each test runs with a **fresh object instance**.

This ensures that tests do not interfere with each other.

---

# Why Test Isolation Is Important

Consider this example:

```java
class CounterTest {

    int counter = 0;

    @Test
    void testA() {
        counter++;
        assertEquals(1, counter);
    }

    @Test
    void testB() {
        counter++;
        assertEquals(1, counter);
    }

}
```

If the same object were reused:

```
counter = 0

testA → counter = 1
testB → counter = 2   (FAIL)
```

But with JUnit’s default behavior:

```
testA → new instance → counter = 1
testB → new instance → counter = 1
```

Each test starts from a clean state.

This is called **test independence**.

---

# Why `@BeforeAll` Must Be Static

`@BeforeAll` runs **once before all tests in the class**.

But at that moment, **JUnit has not created any test instance yet**.

Since there is no object, JUnit cannot call a normal instance method.

Therefore the method must belong to the **class itself**, not to an object.

This is exactly what `static` means.

Example:

```java
@BeforeAll
static void init() {
    System.out.println("Runs once before all tests");
}
```

JUnit calls it like this:

```
ExampleTest.init()
```

No object instance is required.

---

# Why `@AfterAll` Must Be Static

`@AfterAll` runs **once after all tests have finished**.

Like `@BeforeAll`, it runs **outside the lifecycle of individual test objects**.

Therefore it also must be `static`.

Example:

```java
@AfterAll
static void cleanup() {
    System.out.println("Runs once after all tests");
}
```

---

# Why We Use `@BeforeEach` Instead of `@BeforeAll`

`@BeforeEach` runs **before every test method**.

Example:

```java
class CalculatorTest {

    Calculator calculator;

    @BeforeEach
    void setup() {
        calculator = new Calculator();
    }

}
```

JUnit internally behaves like this:

```
create CalculatorTest instance
run @BeforeEach
run test1

create CalculatorTest instance
run @BeforeEach
run test2
```

Each test gets a **fresh environment**.

This prevents tests from accidentally modifying shared state.

Although objects are recreated each time, most test objects are lightweight, so this cost is usually negligible.

---

# When `@BeforeAll` Is Useful

`@BeforeAll` should be used for **expensive resources that should be created only once**.

Examples:

```
database connections
starting a server
loading a large dataset
starting containers
```

Example:

```java
@BeforeAll
static void startDatabase() {
    // start database once
}
```

---

# Can We Use the Constructor Instead of `@BeforeEach`?

Yes.

JUnit calls the constructor every time it creates a test instance.

Example:

```java
class CalculatorTest {

    Calculator calculator;

    CalculatorTest() {
        calculator = new Calculator();
    }

}
```

This works similarly to `@BeforeEach`.

However, `@BeforeEach` is preferred because:

- it clearly indicates **test setup**
- it integrates better with test frameworks and extensions
- it separates object construction from test preparation

---

# Alternative Lifecycle Mode

JUnit 5 provides another lifecycle mode:

```
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
```

Example:

```java
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExampleTest {
}
```

In this mode:

```
JUnit creates only one test instance for the entire class
```

Now `@BeforeAll` and `@AfterAll` **do not need to be static**.

However, this mode allows tests to share state, which can cause tests to interfere with each other.

For this reason, the default lifecycle (`PER_METHOD`) is generally safer.

---

# Execution Flow of Lifecycle Methods

JUnit executes lifecycle methods in the following order:

```
@BeforeAll

@BeforeEach
@Test
@AfterEach

@BeforeEach
@Test
@AfterEach

@BeforeEach
@Test
@AfterEach

@AfterAll
```

---

# Summary

Important concepts:

- JUnit creates **a new test instance for each test method** by default.
- This ensures **test isolation** and prevents tests from affecting each other.
- `@BeforeAll` and `@AfterAll` run **outside test instance creation**, so they must be `static`.
- `@BeforeEach` prepares a fresh environment for every test.
- Expensive shared resources should be initialized in `@BeforeAll`.
- JUnit also supports an alternative lifecycle using `@TestInstance(PER_CLASS)`.

JUnit’s lifecycle design ensures that tests remain **independent, repeatable, and reliable**.
