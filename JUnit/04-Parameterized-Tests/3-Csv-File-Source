# Using `@CsvFileSource` in JUnit Parameterized Tests

`@CsvFileSource` is used when test data is stored in an **external CSV file** instead of being written directly inside the test code.

It is especially useful when:

- the dataset is large
- test data should be separated from test logic
- the same data needs to be reused by multiple tests

Each row in the CSV file represents **one set of input parameters for the test**.

---

# What is `@CsvFileSource`?

`@CsvFileSource` reads test data from a **CSV file** and provides the values to a parameterized test.

Example CSV file:

```
2,3,5
1,2,3
2,2,4
-1,2,1
```

Each row represents:

```
input1,input2,expectedResult
```

JUnit will execute the test once for every row.

---

# Where CSV Files Are Stored

In a standard Java project, test resources are stored in:

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
    │   └── com/tca/CalculatorTest.java
    │
    └── resources
        └── add_test.csv
```

The CSV file should be placed inside the **resources directory**.

---

# Basic Syntax

To use `@CsvFileSource`, annotate the test method with:

```
@ParameterizedTest
```

and specify the file location using:

```
@CsvFileSource
```

Example:

```java
@ParameterizedTest
@CsvFileSource(resources = "/add_test.csv")
void testAddition(int a, int b, int expected) {

    Calculator calculator = new Calculator();

    int result = calculator.add(a, b);

    assertEquals(expected, result);
}
```

JUnit will read each row from the file and pass the values to the test method.

---

# Example CSV File

Example file: `add_test.csv`

```
2,3,5
1,2,3
2,2,4
-1,2,1
```

Execution becomes:

```
Run 1 → 2 + 3 = 5
Run 2 → 1 + 2 = 3
Run 3 → 2 + 2 = 4
Run 4 → -1 + 2 = 1
```

---

# Skipping Header Rows

If the CSV file contains a header row, it can be skipped using:

```
numLinesToSkip
```

Example CSV:

```
a,b,result
2,3,5
1,2,3
```

Test:

```java
@ParameterizedTest
@CsvFileSource(resources = "/add_test.csv", numLinesToSkip = 1)
```

This skips the header line.

---

# Custom Delimiter

CSV normally uses commas `,`, but a different delimiter can be specified.

Example CSV:

```
2|3|5
1|2|3
```

Test:

```java
@ParameterizedTest
@CsvFileSource(resources = "/add_test.csv", delimiter = '|')
```

---

# Example: Testing String Concatenation

CSV file:

```
Hello,World,HelloWorld
Java,Test,JavaTest
Unit,Testing,UnitTesting
```

Test:

```java
@ParameterizedTest
@CsvFileSource(resources = "/join_test.csv")
void testJoin(String first, String second, String expected) {

    StringUtils utils = new StringUtils();

    String result = utils.join(first, second);

    assertEquals(expected, result);
}
```

---

# When to Use `@CsvFileSource`

Use `@CsvFileSource` when:

```
Test data is large
Test data should be stored separately
Multiple tests share the same dataset
Test data may change frequently
```

This approach keeps **test code clean and readable**.

---

# When Not to Use `@CsvFileSource`

`@CsvFileSource` may not be ideal when:

```
Test data is small
Test inputs need to be quickly readable inside the test
Only a few test cases exist
```

In those cases, `@CsvSource` is usually simpler.

---

# Comparison with `@CsvSource`

| Feature | @CsvSource | @CsvFileSource |
|--------|------------|---------------|
| Data location | Inside test code | External CSV file |
| Best for | Small datasets | Large datasets |
| Readability | Immediate | Requires opening file |

---

# Summary

`@CsvFileSource` allows parameterized tests to read input data from **external CSV files**.

Key points:

```
Works with @ParameterizedTest
Reads test data from a CSV file
Each row becomes one test execution
Files are usually stored in src/test/resources
```

This approach is useful for **large datasets and data-driven testing** where test data should remain separate from test logic.
