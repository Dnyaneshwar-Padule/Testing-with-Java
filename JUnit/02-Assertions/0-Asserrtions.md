# 02 - Assertions (JUnit)

Assertions are the **core mechanism used by JUnit to verify behavior**.

When a test runs, it must decide whether the program behaved correctly or not.  
Assertions perform this verification by comparing **expected results with actual results**.

If the assertion condition is true → the test **passes**.  
If the assertion condition is false → the test **fails**.

In simple terms:

> Assertions are statements that check whether a program behaves as expected.

---

# Why Assertions Are Important

Without assertions, a test would only run code but would **not verify anything**.

Example without assertion:

```java
@Test
void testAddition() {
    Calculator calculator = new Calculator();
    calculator.add(2, 3);
}
```

This test runs successfully even if the method returns the wrong value.

With assertions:

```java
@Test
void testAddition() {

    Calculator calculator = new Calculator();

    int result = calculator.add(2, 3);

    assertEquals(5, result);
}
```

Now the test verifies correctness.

---

# Assertions Class

JUnit provides assertions through the class:

```
org.junit.jupiter.api.Assertions
```

Most methods in this class are **static utility methods**.

Common import styles:

### Import specific method

```java
import static org.junit.jupiter.api.Assertions.assertEquals;
```

### Import all assertion methods

```java
import static org.junit.jupiter.api.Assertions.*;
```

The second approach is commonly used in test classes.

---

# Commonly Used Assertions

Below are the most frequently used assertions in JUnit.

---

## assertEquals

Checks whether two values are equal.

```java
assertEquals(expected, actual);
```

Example:

```java
assertEquals(10, calculator.add(5,5));
```

If the values are not equal, the test fails.

---

## assertNotEquals

Checks that two values are **not equal**.

```java
assertNotEquals(expected, actual);
```

Example:

```java
assertNotEquals(10, calculator.add(5,4));
```

---

## assertTrue

Checks whether a condition is true.

```java
assertTrue(condition);
```

Example:

```java
assertTrue(number > 0);
```

---

## assertFalse

Checks whether a condition is false.

```java
assertFalse(condition);
```

Example:

```java
assertFalse(number < 0);
```

---

## assertNull

Verifies that an object is null.

```java
assertNull(object);
```

Example:

```java
assertNull(user.getMiddleName());
```

---

## assertNotNull

Verifies that an object is not null.

```java
assertNotNull(object);
```

Example:

```java
assertNotNull(user.getName());
```

---

## assertSame

Checks whether two references point to the **same object in memory**.

```java
assertSame(expected, actual);
```

Example:

```java
assertSame(object1, object2);
```

---

## assertNotSame

Checks that two references do **not point to the same object**.

```java
assertNotSame(object1, object2);
```

---

## assertThrows

Verifies that a specific exception is thrown.

```java
assertThrows(ExceptionType.class, () -> {
    methodCall();
});
```

Example:

```java
assertThrows(ArithmeticException.class, () -> {
    calculator.divide(10, 0);
});
```

---

# Assertion Message (Optional)

Assertions can include a message that will be displayed if the test fails.

Example:

```java
assertEquals(5, result, "Addition result should be 5");
```

If the test fails, the message appears in the test report.

---

# Basic Assertion Pattern

Most tests follow this pattern:

```
Arrange
Act
Assert
```

Example:

```java
@Test
void add_shouldReturnCorrectSum() {

    // Arrange
    Calculator calculator = new Calculator();

    // Act
    int result = calculator.add(2, 3);

    // Assert
    assertEquals(5, result);
}
```

---

# Summary

Assertions are the **verification step in unit tests**.

They compare the **expected behavior** with the **actual behavior** of the program.

Commonly used assertions include:

```
assertEquals
assertNotEquals
assertTrue
assertFalse
assertNull
assertNotNull
assertSame
assertNotSame
assertThrows
```

Using assertions correctly ensures that tests provide **meaningful validation of code behavior**.

Without assertions, a test would only execute code without verifying correctness.
