# Using `@EnumSource` in JUnit Parameterized Tests

`@EnumSource` is used when the parameterized test needs to run with **values from an Enum**.

Enums represent a **fixed set of constants**, so they are often used for things like:

- application states
- configuration modes
- user roles
- order statuses
- days of the week

Instead of writing separate tests for each enum value, `@EnumSource` allows JUnit to automatically run the test for **each enum constant**.

---

# What is `@EnumSource`?

`@EnumSource` supplies enum constants to a parameterized test.

JUnit will execute the test **once for each enum value**.

Example Enum:

```java
enum Day {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}
```

If this enum is used with `@EnumSource`, the test will run **seven times**, once for each day.

---

# Basic Syntax

To use `@EnumSource`, annotate the test method with:

```
@ParameterizedTest
@EnumSource(EnumClass.class)
```

Example:

```java
@ParameterizedTest
@EnumSource(Day.class)
void testDays(Day day) {

    assertNotNull(day);

}
```

Execution will occur for:

```
MONDAY
TUESDAY
WEDNESDAY
THURSDAY
FRIDAY
SATURDAY
SUNDAY
```

Each value becomes a separate test execution.

---

# Example: Testing Order Status

Enum:

```java
enum OrderStatus {
    CREATED,
    PROCESSING,
    SHIPPED,
    DELIVERED
}
```

Test:

```java
@ParameterizedTest
@EnumSource(OrderStatus.class)
void testOrderStatus(OrderStatus status) {

    assertNotNull(status);

}
```

JUnit will run the test **four times**, once for each enum value.

---

# Selecting Specific Enum Values

You can run tests for **specific enum constants** using the `names` attribute.

Example:

```java
@ParameterizedTest
@EnumSource(value = Day.class, names = {"MONDAY", "FRIDAY"})
void testSpecificDays(Day day) {

    assertNotNull(day);

}
```

This test will run only for:

```
MONDAY
FRIDAY
```

---

# Excluding Enum Values

You can exclude certain enum values using the `mode` attribute.

Example:

```java
@ParameterizedTest
@EnumSource(
    value = Day.class,
    names = {"SATURDAY", "SUNDAY"},
    mode = EnumSource.Mode.EXCLUDE
)
void testWeekdays(Day day) {

    assertNotNull(day);

}
```

This will run for:

```
MONDAY
TUESDAY
WEDNESDAY
THURSDAY
FRIDAY
```

---

# Matching Enum Names with Patterns

`@EnumSource` can also match enum constants using regular expressions.

Example:

```java
@ParameterizedTest
@EnumSource(value = Day.class, names = ".*DAY", mode = EnumSource.Mode.MATCH_ANY)
void testDaysEndingWithDay(Day day) {

    assertNotNull(day);

}
```

This selects enum constants whose names match the pattern.

---

# When to Use `@EnumSource`

`@EnumSource` is useful when:

```
Testing logic based on enum values
Validating behavior for different states
Testing system modes or configurations
Checking behavior across all enum constants
```

Examples include:

```
User roles (ADMIN, USER, GUEST)
Order statuses
Application environments
Days of the week
```

---

# When Not to Use `@EnumSource`

`@EnumSource` should not be used when:

```
Test inputs are not enums
Multiple parameters are required
Dynamic data is needed
```

In such cases, other parameter sources like:

```
@CsvSource
@CsvFileSource
@MethodSource
```

are more appropriate.

---

# Example Execution

Example test:

```java
@ParameterizedTest
@EnumSource(Day.class)
void testEnumValues(Day day) {

    assertTrue(day.name().length() > 0);

}
```

Execution:

```
Run 1 → MONDAY
Run 2 → TUESDAY
Run 3 → WEDNESDAY
Run 4 → THURSDAY
Run 5 → FRIDAY
Run 6 → SATURDAY
Run 7 → SUNDAY
```

Each enum constant becomes a separate test execution.

---

# Summary

`@EnumSource` allows parameterized tests to receive **enum constants as input values**.

Key points:

```
Works with @ParameterizedTest
Supplies values from an enum
Runs the test once for each enum constant
Supports filtering specific enum values
```

It is particularly useful when testing **logic that depends on enum-based states or modes**.
