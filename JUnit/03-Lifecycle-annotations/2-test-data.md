# Lifecycle Annotations and Test Data in JUnit

When learning JUnit lifecycle annotations, a common question arises:

> If multiple tests use the same data or resource, should we initialize it in `@BeforeEach`, `@BeforeAll`, or somewhere else?

Another related question is:

> Where should test data be stored — inside the test code or in external files?

This document explains the **real-world usage of lifecycle annotations** and **how test data is typically managed** in professional projects.

---

# Purpose of Lifecycle Annotations

JUnit lifecycle annotations exist to manage the **test environment**, not the test values themselves.

They control what happens:

```
Before tests start
Before each test
After each test
After all tests finish
```

Lifecycle annotations ensure that tests run in a **clean, predictable environment**.

---

# Main Lifecycle Annotations

JUnit provides four primary lifecycle annotations:

```
@BeforeAll
@BeforeEach
@AfterEach
@AfterAll
```

These control **when specific setup or cleanup code is executed**.

---

# Real Use Cases of Lifecycle Annotations

Lifecycle annotations are typically used for **environment preparation and cleanup**, such as:

### @BeforeAll

Runs once before all tests.

Common uses:

```
Starting a database
Starting an embedded web server
Loading large configuration files
Starting Docker containers (TestContainers)
Initializing expensive resources
```

Example:

```java
@BeforeAll
static void startDatabase() {
    // start database connection
}
```

---

### @BeforeEach

Runs before every test method.

Common uses:

```
Creating objects required by tests
Resetting system state
Clearing caches
Preparing fresh test data
```

Example:

```java
@BeforeEach
void setup() {
    calculator = new Calculator();
}
```

This ensures every test starts with a **fresh object instance**.

---

### @AfterEach

Runs after every test.

Common uses:

```
Cleaning temporary data
Resetting shared variables
Closing resources created during the test
```

Example:

```java
@AfterEach
void cleanup() {
    // cleanup temporary resources
}
```

---

### @AfterAll

Runs once after all tests complete.

Common uses:

```
Stopping servers
Closing database connections
Releasing shared resources
```

Example:

```java
@AfterAll
static void shutdown() {
    // stop database
}
```

---

# Lifecycle Execution Order

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

This pattern ensures tests run in a **controlled environment**.

---

# Example Scenario: Calculator Test Data

Suppose we want to test a calculator using multiple input values.

Example dataset:

```
2,3,5
1,2,3
2,2,4
-1,2,1
```

Each row represents:

```
number1, number2, expectedResult
```

A naive approach might be reading this file manually inside tests.

However, JUnit provides a better feature called **Parameterized Tests**.

---

# Parameterized Tests (Running Tests with Multiple Inputs)

Parameterized tests allow a single test method to run **multiple times with different inputs**.

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

    int result = calculator.add(a,b);

    assertEquals(expected, result);
}
```

JUnit will execute this test **four times**, once for each row.

Parameterized tests are the preferred way to test multiple data combinations.

---

# Storing Test Data

Test data can be stored in two main ways:

```
1. Inside the test code
2. In external files
```

Both approaches are commonly used depending on the situation.

---

# Test Data Inside the Test Code

For small datasets, developers usually write the data directly in the test.

Example:

```java
@CsvSource({
    "2,3,5",
    "1,2,3"
})
```

Advantages:

```
Easy to read
Easy to maintain
Test logic and data stay together
```

This approach is common for simple unit tests.

---

# Test Data in External Files

When the dataset becomes large, it is often stored in external files.

JUnit supports this using:

```
@CsvFileSource
```

Example:

```java
@ParameterizedTest
@CsvFileSource(resources = "/add_test.csv")
void testAddition(int a, int b, int expected) {

    Calculator calculator = new Calculator();

    int result = calculator.add(a,b);

    assertEquals(expected, result);
}
```

Example CSV file:

```
2,3,5
1,2,3
2,2,4
-1,2,1
```

---

# Where Test Data Files Are Stored

External test files are usually stored in:

```
src/test/resources
```

Example project structure:

```
src
 ├── main
 │   └── java
 │
 └── test
     ├── java
     │   └── CalculatorTest.java
     │
     └── resources
         └── add_test.csv
```

This keeps **test data separate from test code**.

---

# When to Store Data Inside the Test

Use inline test data when:

```
The dataset is small
The data helps explain the test logic
The test should be easy to read
```

Example:

```
@CsvSource
```

---

# When to Use External Test Files

Use external files when:

```
The dataset is large
Test data changes frequently
Data is shared across multiple tests
Data comes from real-world datasets
```

Examples of external formats:

```
CSV
JSON
YAML
SQL scripts
```

---

# Key Concept: Test Environment vs Test Data

JUnit separates two responsibilities:

```
Lifecycle Annotations → Manage the test environment
Parameterized Tests → Manage multiple test inputs
```

Example combined workflow:

```
@BeforeAll
Start database

@BeforeEach
Reset database state

@ParameterizedTest
Run tests using multiple input values

@AfterAll
Shutdown database
```

---

# Summary

Important concepts to remember:

- Lifecycle annotations prepare and clean the **test environment**.
- They are not mainly used to manage test data.
- Parameterized tests allow a single test to run with multiple inputs.
- Small datasets are often written directly in test methods.
- Large datasets are stored in external files like CSV.
- External test files are typically stored in `src/test/resources`.

Understanding the separation between **environment setup** and **test data management** helps in designing **clean and maintainable test suites**.
