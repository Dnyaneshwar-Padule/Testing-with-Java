# Running JUnit Tests in IntelliJ IDEA and Eclipse

This guide explains:

- How to create a **JUnit test class**
- How to **run a single test**
- How to **run all tests in a project**

The instructions apply to **JUnit 5**.

---

# Project Structure

Before creating tests, ensure your project follows the standard structure.

```
src
├── main
│   └── java
│       └── com
│           └── tca
│               └── Greeter.java
│
└── test
    └── java
        └── com
            └── tca
                └── GreeterTest.java
```

Application code goes in:

```
src/main/java
```

Test code goes in:

```
src/test/java
```

The **package name of the test class should usually match the application class package**.

Example:

```java
package com.tca;
```

---

# Creating a Test Class in IntelliJ IDEA

### Step 1: Navigate to Test Directory

In the Project Explorer:

```
src
└── test
    └── java
```

Right-click on the desired package.

Example:

```
src/test/java/com/tca
```

---

### Step 2: Create Test Class

Right-click → **New → Java Class**

Enter the class name.

Example:

```
GreeterTest
```

Add the correct package declaration at the top:

```java
package com.tca;
```

---

### Step 3: Add JUnit Imports

Add the required imports.

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
```

---

# Running a Test in IntelliJ IDEA

### Run a Single Test Class

1. Right-click the test class.
2. Select:

```
Run 'GreeterTest'
```

IntelliJ will run the test using the **JUnit runner**.

Results appear in the **Run window**.

Example result:

```
✔ greet_shouldReturnHello()
```

---

### Run a Single Test Method

Right-click directly on the method.

Select:

```
Run 'greet_shouldReturnHello'
```

Only that test will execute.

---

### Run All Tests in a Package

Right-click the package containing test classes.

Example:

```
com.tca
```

Select:

```
Run Tests in 'com.tca'
```

All tests inside that package will execute.

---

### Run All Tests in the Project

Right-click the `test` directory.

```
src/test/java
```

Select:

```
Run Tests in 'java'
```

JUnit will discover and execute all tests in the project.

---

# Creating a Test Class in Eclipse

### Step 1: Navigate to Test Directory

In **Package Explorer**, locate:

```
src/test/java
```

Right-click the package where you want to create the test class.

Example:

```
com.tca
```

---

### Step 2: Create the Test Class

Right-click → **New → Class**

Enter the class name.

Example:

```
GreeterTest
```

Ensure the package name is correct:

```java
package com.tca;
```

---

### Step 3: Add JUnit Imports

Add the imports manually if needed.

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
```

---

# Running a Test in Eclipse

### Run a Single Test Class

1. Right-click the test class.
2. Select:

```
Run As → JUnit Test
```

Eclipse will start the **JUnit Test Runner** and execute the tests.

Results appear in the **JUnit View**.

---

### Run a Single Test Method

Right-click the test method.

Select:

```
Run As → JUnit Test
```

Only that method will run.

---

### Run All Tests in a Package

Right-click the package containing tests.

Example:

```
com.tca
```

Select:

```
Run As → JUnit Test
```

All test classes inside the package will run.

---

### Run All Tests in the Project

Right-click the `src/test/java` directory.

Select:

```
Run As → JUnit Test
```

Eclipse will discover and execute all test classes in the project.

---

# How JUnit Executes Tests

When a test is executed, the following process occurs:

```
IDE starts JUnit Runner
        ↓
JUnit Platform loads the JUnit Engine
        ↓
Engine scans test classes
        ↓
Methods annotated with @Test are discovered
        ↓
Each test method is executed
        ↓
Assertions verify expected behavior
        ↓
Results are reported (PASS / FAIL)
```

---

# Summary

To run JUnit tests:

**In IntelliJ**

```
Right Click Test Class → Run
```

**In Eclipse**

```
Right Click Test Class → Run As → JUnit Test
```

To run all tests:

```
Right Click src/test/java → Run Tests
```

JUnit automatically discovers test classes and executes all methods annotated with:

```
@Test
```

No `main()` method is required because the **JUnit runner executes the tests automatically**.
