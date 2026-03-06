# 05 - Exception Testing (JUnit)

In addition to verifying correct outputs, a good test suite must also verify that a program **fails correctly when invalid input or unexpected conditions occur**.

In many applications, methods are designed to throw **exceptions** when something goes wrong.

Exception testing ensures that:

```
The correct exception is thrown
The exception occurs under the correct conditions
The program fails in a predictable and controlled way
```

Testing exceptions is an important part of **robust software testing**, because many real-world bugs occur when programs handle errors incorrectly.

---

# What is Exception Testing?

Exception testing verifies that a method throws a **specific exception** when given invalid input or when an error condition occurs.

Example scenario:

```
Division by zero
Invalid arguments
Null values
Illegal states
File not found
```

For example, a calculator might throw an exception when dividing by zero.

Example method:

```java
public int divide(int a, int b) {

    if (b == 0) {
        throw new ArithmeticException("Division by zero");
    }

    return a / b;
}
```

In such cases, the correct behavior is **not returning a value**, but throwing an exception.

---

# Why Exception Testing is Important

Without testing exceptions, incorrect behavior might go unnoticed.

Example problem:

```
Expected behavior → throw exception
Actual behavior → silently return incorrect value
```

If tests only verify successful scenarios, such errors may remain undetected.

Exception tests ensure that **error handling logic is also validated**.

---

# Exception Testing in JUnit

JUnit provides built-in support for testing exceptions using the method:

```
assertThrows()
```

This method verifies that a specific exception type is thrown.

Example:

```java
@Test
void testDivideByZero() {

    Calculator calculator = new Calculator();

    assertThrows(ArithmeticException.class, () -> {
        calculator.divide(10, 0);
    });
}
```

If the exception is thrown, the test **passes**.

If the exception is not thrown, the test **fails**.

---

# How `assertThrows` Works

`assertThrows` takes two arguments:

```
Exception type
Executable code block
```

Structure:

```java
assertThrows(ExceptionType.class, () -> {
    // code expected to throw exception
});
```

JUnit executes the code inside the lambda expression and checks whether the expected exception occurs.

---

# Example Execution

Example test:

```java
assertThrows(ArithmeticException.class, () -> {
    calculator.divide(10, 0);
});
```

Execution result:

```
divide(10,0) → throws ArithmeticException → Test PASSES
```

If no exception occurs:

```
divide(10,0) → returns value → Test FAILS
```

---

# Common Exceptions Tested

Some commonly tested exceptions include:

```
IllegalArgumentException
ArithmeticException
NullPointerException
IllegalStateException
IOException
```

These exceptions often represent **invalid input or incorrect program state**.

---

# What Exception Testing Verifies

Exception tests help confirm that:

```
The correct exception type is thrown
Exceptions occur for the correct conditions
Invalid inputs are handled safely
The application fails in a predictable way
```

This improves **software reliability and error handling**.

---

# Summary

Exception testing is used to verify that a program **throws the correct exceptions when errors occur**.

Key ideas:

```
Programs should fail safely and predictably
assertThrows() verifies expected exceptions
Error conditions must be tested just like successful cases
```

Testing exceptions ensures that both **normal behavior and failure behavior** of a program are correctly validated.
