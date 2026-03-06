# Using `@MethodSource` in JUnit Parameterized Tests

`@MethodSource` is used when test data needs to be generated or supplied by a **Java method** instead of writing it directly inside annotations.

It is one of the **most flexible ways** to provide data to parameterized tests.

Unlike `@ValueSource`, `@CsvSource`, or `@CsvFileSource`, which provide static data, `@MethodSource` allows:

- generating data dynamically
- creating complex test objects
- using loops or logic to produce test data
- reusing test data across multiple tests

---

# What is `@MethodSource`?

`@MethodSource` tells JUnit to get test arguments from a **method**.

That method returns a collection or stream of arguments that will be passed to the test method.

Conceptually:

```
method → provides data
      ↓
JUnit runs test with each data set
```

---

# Basic Syntax

To use `@MethodSource`, the test must include:

```
@ParameterizedTest
@MethodSource("methodName")
```

The referenced method must return a **Stream, Collection, Iterable, or Array** of arguments.

Example:

```java
@ParameterizedTest
@MethodSource("additionData")
void testAddition(int a, int b, int expected) {

    Calculator calculator = new Calculator();

    int result = calculator.add(a, b);

    assertEquals(expected, result);
}
```

The method providing data:

```java
static Stream<Arguments> additionData() {

    return Stream.of(
        Arguments.of(2, 3, 5),
        Arguments.of(1, 2, 3),
        Arguments.of(2, 2, 4),
        Arguments.of(-1, 2, 1)
    );
}
```

Each `Arguments.of(...)` represents **one test execution**.

---

# How JUnit Executes the Test

JUnit calls the data method and runs the test multiple times.

Conceptually:

```
Arguments.of(2,3,5)   → Run test
Arguments.of(1,2,3)   → Run test
Arguments.of(2,2,4)   → Run test
Arguments.of(-1,2,1)  → Run test
```

Each argument set becomes a **separate test case**.

---

# Using Method Name Inferred Automatically

If the data method has the **same name as the test method**, the name does not need to be specified.

Example:

```java
@ParameterizedTest
@MethodSource
void testAddition(int a, int b, int expected) {
}
```

JUnit automatically looks for a method called:

```
testAddition()
```

that returns the arguments.

---

# Example: Single Parameter Source

`@MethodSource` can also supply **single values**.

Example data method:

```java
static Stream<Integer> numbers() {
    return Stream.of(1, 2, 3, 4);
}
```

Test method:

```java
@ParameterizedTest
@MethodSource("numbers")
void testPositiveNumbers(int number) {
    assertTrue(number > 0);
}
```

JUnit executes the test four times.

---

# Example: Using Complex Objects

One advantage of `@MethodSource` is that it supports **complex objects**.

Example data method:

```java
static Stream<String> names() {
    return Stream.of("Alice", "Bob", "Charlie");
}
```

Test:

```java
@ParameterizedTest
@MethodSource("names")
void testNames(String name) {
    assertNotNull(name);
}
```

This approach works well when testing **custom objects or complex data structures**.

---

# When to Use `@MethodSource`

`@MethodSource` is useful when:

```
Test data must be generated programmatically
Complex objects need to be supplied
Test data requires loops or logic
Test data needs to be reused across tests
Data cannot easily fit in annotations
```

Examples include:

```
Testing object transformations
Generating large sets of input data
Testing combinations of parameters
Creating dynamic test inputs
```

---

# When Not to Use `@MethodSource`

If the test data is simple and small, using:

```
@ValueSource
@CsvSource
```

is often easier and more readable.

`@MethodSource` should be used when test data becomes **too complex for annotations**.

---

# Comparison with Other Sources

| Source Type | Best Use Case |
|-------------|---------------|
| `@ValueSource` | Single simple values |
| `@CsvSource` | Multiple parameters inside the test |
| `@CsvFileSource` | Large datasets in external files |
| `@MethodSource` | Dynamic or complex data generation |

---

# Summary

`@MethodSource` allows parameterized tests to receive data from **methods that generate arguments**.

Key ideas:

```
Works with @ParameterizedTest
Test data is supplied by a method
Supports dynamic and complex data
Each argument set becomes one test execution
```

`@MethodSource` is the **most flexible parameter source** in JUnit and is commonly used when annotation-based sources become insufficient.
