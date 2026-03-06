# Using `@ValueSource` in JUnit Parameterized Tests

`@ValueSource` is one of the simplest ways to supply input values to a **Parameterized Test** in JUnit.

It is used when a test method needs to run multiple times with **a single parameter that varies across executions**.

---

# What is `@ValueSource`?

`@ValueSource` provides a list of values to a parameterized test.

JUnit will execute the test **once for each value provided**.

Example idea:

```
Test input values:
1
2
3
4
5
```

If these values are supplied through `@ValueSource`, the test will run **five times**.

---

# Basic Syntax

To use `@ValueSource`, the test must be annotated with:

```
@ParameterizedTest
```

and the input values are defined inside `@ValueSource`.

Example:

```java
@ParameterizedTest
@ValueSource(ints = {1, 2, 3, 4})
void testNumbers(int number) {
    assertTrue(number > 0);
}
```

JUnit will execute this test **four times**, once for each value.

Execution conceptually looks like:

```
Run 1 → number = 1
Run 2 → number = 2
Run 3 → number = 3
Run 4 → number = 4
```

---

# Supported Data Types

`@ValueSource` supports several basic data types.

Common types include:

```
ints
longs
doubles
strings
booleans
chars
classes
```

Examples:

### Integer values

```java
@ValueSource(ints = {2, 4, 6, 8})
```

---

### String values

```java
@ValueSource(strings = {"apple", "banana", "orange"})
```

---

### Boolean values

```java
@ValueSource(booleans = {true, false})
```

---

### Character values

```java
@ValueSource(chars = {'a', 'b', 'c'})
```

---

# Example: Testing Even Numbers

Application method:

```java
public boolean isEven(int number) {
    return number % 2 == 0;
}
```

Test using `@ValueSource`:

```java
@ParameterizedTest
@ValueSource(ints = {2, 4, 6, 8})
void testEvenNumbers(int number) {

    NumberUtils utils = new NumberUtils();

    assertTrue(utils.isEven(number));
}
```

JUnit will run the test **four times**:

```
number = 2
number = 4
number = 6
number = 8
```

---

# Example: Testing Non-Empty Strings

Application method:

```java
public boolean isEmpty(String text) {
    return text.isEmpty();
}
```

Test:

```java
@ParameterizedTest
@ValueSource(strings = {"hello", "java", "testing"})
void testNonEmptyStrings(String input) {

    assertFalse(input.isEmpty());
}
```

Each string value becomes a separate test run.

---

# When to Use `@ValueSource`

`@ValueSource` should be used when:

```
Only one parameter needs to vary
The test inputs are simple values
The dataset is small
```

Examples of suitable cases:

```
Testing positive numbers
Testing multiple valid strings
Testing simple boolean conditions
Testing character inputs
```

---

# When Not to Use `@ValueSource`

`@ValueSource` is **not suitable when multiple parameters are required**.

Example:

```
input1, input2, expectedResult
```

For such cases, other annotations are more appropriate:

```
@CsvSource
@CsvFileSource
@MethodSource
```

---

# Example Limitation

This **cannot be done with `@ValueSource`**:

```
2,3 → 5
1,2 → 3
```

Because it requires **multiple parameters**.

Instead, you would use `@CsvSource`.

---

# Execution Visualization

Example:

```java
@ParameterizedTest
@ValueSource(strings = {"JUnit", "Mockito", "Spring"})
void testFrameworkNames(String framework) {
    assertNotNull(framework);
}
```

Execution:

```
Run 1 → framework = "JUnit"
Run 2 → framework = "Mockito"
Run 3 → framework = "Spring"
```

---

# Summary

`@ValueSource` is used for simple parameterized tests where **a single parameter varies across executions**.

Key points:

```
Requires @ParameterizedTest
Provides simple value lists
Runs the same test multiple times
Works with primitive and simple data types
```

Best used when testing **simple variations of a single input value**.

For more complex inputs involving multiple parameters, other parameterized test sources should be used.
