# Using `@CsvSource` in JUnit Parameterized Tests

`@CsvSource` is used when a parameterized test requires **multiple parameters for each test execution**.

Unlike `@ValueSource`, which supports only **one varying parameter**, `@CsvSource` allows supplying **multiple values per test case**.

Each line inside `@CsvSource` represents **one complete set of parameters** for a single test execution.

---

# What is `@CsvSource`?

`@CsvSource` allows you to provide test data in **CSV format (Comma-Separated Values)** directly inside the test annotation.

Example dataset:

```
2,3,5
1,2,3
2,2,4
-1,2,1
```

Each row represents:

```
input1, input2, expectedResult
```

JUnit will execute the test **once for every row**.

---

# Basic Syntax

To use `@CsvSource`, annotate the test method with:

```
@ParameterizedTest
```

and supply data using:

```
@CsvSource
```

Example:

```java
@ParameterizedTest
@CsvSource({
    "2,3,5",
    "1,2,3",
    "2,2,4",
    "-1,2,1"
})
void testAddition(int a, int b, int expected) {

    Calculator calculator = new Calculator();

    int result = calculator.add(a, b);

    assertEquals(expected, result);
}
```

---

# How JUnit Executes the Test

JUnit runs the test method multiple times.

Conceptually:

```
Run 1 → a=2,  b=3, expected=5
Run 2 → a=1,  b=2, expected=3
Run 3 → a=2,  b=2, expected=4
Run 4 → a=-1, b=2, expected=1
```

Each row becomes a **separate test execution**.

---

# Example: Testing String Concatenation

Application method:

```java
public String join(String a, String b) {
    return a + b;
}
```

Test:

```java
@ParameterizedTest
@CsvSource({
    "Hello,World,HelloWorld",
    "Java,Test,JavaTest",
    "Unit,Testing,UnitTesting"
})
void testJoin(String first, String second, String expected) {

    StringUtils utils = new StringUtils();

    String result = utils.join(first, second);

    assertEquals(expected, result);
}
```

Each CSV row provides the values for:

```
first
second
expected
```

---

# Using Strings with Spaces

When string values contain spaces, they should be enclosed in quotes.

Example:

```java
@CsvSource({
    "'Hello World',5",
    "'JUnit Test',10"
})
```

---

# Handling Empty Values

You can test empty strings using:

```
''
```

Example:

```java
@CsvSource({
    "'',0",
    "'hello',5"
})
```

---

# Custom Delimiter

By default, `@CsvSource` uses a comma `,` as the separator.

You can change it using the `delimiter` attribute.

Example:

```java
@ParameterizedTest
@CsvSource(value = {
    "2|3|5",
    "1|2|3"
}, delimiter = '|')
```

---

# When to Use `@CsvSource`

Use `@CsvSource` when:

```
Multiple parameters are required
Test data is small and simple
You want test data directly inside the test code
```

Typical use cases include:

```
Testing mathematical calculations
Validating string operations
Testing input-output transformations
Checking combinations of parameters
```

---

# When Not to Use `@CsvSource`

`@CsvSource` becomes difficult to manage when:

```
Test data is very large
Test data must be reused across multiple tests
Test data is stored externally
```

In such cases, consider using:

```
@CsvFileSource
@MethodSource
```

---

# Execution Visualization

Example:

```java
@ParameterizedTest
@CsvSource({
    "10,5,15",
    "20,5,25"
})
void testAddition(int a, int b, int expected) {
    assertEquals(expected, a + b);
}
```

Execution:

```
Run 1 → 10 + 5 = 15
Run 2 → 20 + 5 = 25
```

JUnit treats each row as a **separate test run**.

---

# Summary

`@CsvSource` allows parameterized tests to receive **multiple values per test execution**.

Key ideas:

```
Works with @ParameterizedTest
Each CSV row becomes a separate test case
Supports multiple parameters
Test data is written directly in the annotation
```

`@CsvSource` is best used when testing **small datasets with multiple input parameters** directly inside the test code.
```
