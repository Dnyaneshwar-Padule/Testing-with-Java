# Why `@BeforeAll` and `@AfterAll` Are Static in JUnit

To understand why `@BeforeAll` and `@AfterAll` are usually declared as `static`, we first need to understand **how JUnit creates and executes test classes**.

JUnit is designed around an important principle:

> **Each test should run in isolation so that one test cannot affect another.**

This principle is called **test isolation**.

---

# Default Test Instance Lifecycle

By default, JUnit 5 uses the following lifecycle:

```
@TestInstance(Lifecycle.PER_METHOD)
```

This means that **JUnit creates a new instance of the test class for every test method**.

Example test class:

```java
class ExampleTest {

    @Test
    void testA() {}

    @Test
    void testB() {}

}
```

JUnit internally behaves roughly like this:

```
create ExampleTest instance
run testA()

create ExampleTest instance
run testB()
```

Each test runs using a **fresh object instance**.

This ensures that tests remain independent and do not share state accidentally.

---

# Why Test Isolation Matters

Consider the following test class:

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

Because JUnit creates a new object for each test:

```
create CounterTest instance
counter = 0
testA → counter = 1 ✔

create CounterTest instance
counter = 0
testB → counter = 1 ✔
```

Both tests pass because **each test starts with a clean state**.

If the same instance were reused, the execution would look like this:

```
counter = 0

testA → counter = 1
testB → counter = 2 ❌
```

The second test would fail because the previous test modified the shared state.

JUnit avoids this problem by creating **a new test instance per test method**.

This behavior ensures **test independence**.

---

# Why `@BeforeAll` Must Be Static

`@BeforeAll` is executed **once before all test methods in the class run**.

However, at that moment **JUnit has not yet created any test instance**.

Because there is no object available, JUnit cannot call a normal instance method.

Therefore the method must belong to the **class itself**, not to a specific object.

This is exactly what the `static` keyword represents.

Example:

```java
@BeforeAll
static void init() {
    System.out.println("Runs once before all tests");
}
```

JUnit invokes it like this:

```
ExampleTest.init()
```

No object instance is required.

---

# Why `@AfterAll` Must Be Static

`@AfterAll` runs **once after all test methods have finished executing**.

Like `@BeforeAll`, it runs outside the lifecycle of individual test instances.

Therefore it must also be declared `static`.

Example:

```java
@AfterAll
static void cleanup() {
    System.out.println("Runs once after all tests");
}
```

---

# Why `@BeforeEach` Is Used Instead of `@BeforeAll`

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

Execution flow:

```
create CalculatorTest instance
run @BeforeEach
run test1

create CalculatorTest instance
run @BeforeEach
run test2
```

Each test gets a **fresh test environment**.

This prevents tests from modifying shared objects or affecting other tests.

Although objects are recreated each time, test setup is usually lightweight, so the overhead is minimal.

---

# When `@BeforeAll` Is Useful

`@BeforeAll` should be used when initializing **expensive resources that should only be created once**.

Examples include:

```
Starting a database
Launching a server
Loading large datasets
Starting containerized services
Initializing shared configuration
```

Example:

```java
@BeforeAll
static void startDatabase() {
    // start database connection once
}
```

---

# Can We Use the Constructor Instead of `@BeforeEach`?

Yes.

JUnit calls the constructor every time it creates a new test instance.

Example:

```java
class CalculatorTest {

    Calculator calculator;

    CalculatorTest() {
        calculator = new Calculator();
    }

}
```

This behaves similarly to `@BeforeEach`.

However, `@BeforeEach` is usually preferred because:

- It clearly communicates **test setup logic**
- It works better with extensions and frameworks
- It separates object construction from test preparation

---

# Alternative Lifecycle Mode

JUnit 5 also provides an alternative lifecycle mode:

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
JUnit creates only one instance of the test class for all test methods
```

Because the instance already exists, `@BeforeAll` and `@AfterAll` **do not need to be static**.

However, this mode allows tests to share state, which can lead to unexpected interference between tests.

For this reason, the default lifecycle (`PER_METHOD`) is usually safer.

---

# Execution Flow of Lifecycle Methods

The typical execution order in JUnit is:

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

This lifecycle ensures that each test runs in a **controlled and predictable environment**.

---

# Summary

Key concepts:

- JUnit creates **a new test instance for every test method by default**.
- This ensures **test isolation** and prevents shared state between tests.
- `@BeforeAll` and `@AfterAll` run outside instance creation, so they must be `static`.
- `@BeforeEach` prepares a clean environment for each test.
- Expensive resources should be initialized using `@BeforeAll`.
- JUnit also supports `@TestInstance(PER_CLASS)` if a shared test instance is needed.

This lifecycle design helps make tests **independent, repeatable, and reliable**.
