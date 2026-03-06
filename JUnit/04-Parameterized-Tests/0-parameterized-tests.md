# 04 - Parameterized Tests (JUnit)

In normal unit tests, each test method usually verifies **one specific input and output**.

Example:

```java
@Test
void testAddition() {
    Calculator calculator = new Calculator();
    assertEquals(5, calculator.add(2,3));
}
```

If we want to test multiple input combinations, we might end up writing many test methods:

```java
@Test
void addTest1() {
    assertEquals(5, calculator.add(2,3));
}

@Test
void addTest2() {
    assertEquals(3, calculator.add(1,2));
}

@Test
void addTest3() {
    assertEquals(4, calculator.add(2,2));
}
```

This approach quickly becomes **repetitive and difficult to maintain**.

JUnit provides a better solution called **Parameterized Tests**.

---

# What Are Parameterized Tests?

A **Parameterized Test** allows the same test method to run **multiple times with different input values**.

Instead of writing many test methods, we define **one test method** and supply different data sets.

Example idea:

```
2 + 3 = 5
1 + 2 = 3
2 + 2 = 4
-1 + 2 = 1
```

Each row becomes a **separate test execution**.

---

# How Parameterized Tests Work

JUnit executes the same test method repeatedly with different arguments.

Conceptually:

```
Test Method
      ↓
Input Set 1 → Run Test
Input Set 2 → Run Test
Input Set 3 → Run Test
Input Set 4 → Run Test
```

So a single test method can produce **multiple test runs**.

---

# Parameterized Test Annotation

To create a parameterized test, JUnit provides the annotation:

```
@ParameterizedTest
```

Example:

```java
@ParameterizedTest
void testAddition(int a, int b, int expected) {
    Calculator calculator = new Calculator();
    assertEquals(expected, calculator.add(a,b));
}
```

This method will run multiple times depending on the provided input data.

---

# Supplying Test Data

JUnit allows several ways to supply input data to parameterized tests.

Common sources include:

```
@ValueSource
@CsvSource
@CsvFileSource
@MethodSource
@EnumSource
```

Each of these provides a different way to feed data into the test.

These will be explored in the following sections.

---

# Example Concept

Suppose we want to test addition using multiple inputs:

```
Input A | Input B | Expected
----------------------------
2       | 3       | 5
1       | 2       | 3
2       | 2       | 4
-1      | 2       | 1
```

Instead of writing four separate tests, we create **one parameterized test** that runs four times.

---

# Why Parameterized Tests Are Useful

Parameterized tests provide several advantages:

### 1. Reduce Code Duplication

Instead of writing many similar test methods, we write **one reusable test**.

---

### 2. Improve Test Coverage

More input combinations can be tested easily.

---

### 3. Better Organization of Test Data

Test inputs can be organized in:

```
annotations
CSV files
methods
external data sources
```

---

### 4. Data-Driven Testing

Parameterized tests enable **data-driven testing**, where test behavior remains constant while input data changes.

---

# Example Execution

A parameterized test using four input rows produces:

```
Test Run 1 → PASS
Test Run 2 → PASS
Test Run 3 → PASS
Test Run 4 → PASS
```

Even though only **one test method** was written.

---

# Where Parameterized Tests Are Commonly Used

Parameterized tests are especially useful when testing:

```
mathematical calculations
validation rules
string processing
data transformations
edge cases
```

Any situation where the same logic must be verified with **many input combinations**.

---

# Summary

Parameterized tests allow a single test method to be executed **multiple times with different inputs**.

Key ideas:

```
@ParameterizedTest → enables parameterized testing
Input sources provide test data
One test method → multiple executions
```

This approach reduces duplication, improves coverage, and enables **data-driven testing**.

The next sections will explore different ways to provide data to parameterized tests, such as:

```
@ValueSource
@CsvSource
@CsvFileSource
@MethodSource
@EnumSource
```
