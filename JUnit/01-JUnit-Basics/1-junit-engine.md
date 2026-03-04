# JUnit Engine (How Tests Are Actually Executed)

To understand JUnit properly, it is important to understand **who actually runs the tests**.

When writing normal Java programs, execution begins from a `main()` method.

Example:

```java
public class Application {
    public static void main(String[] args) {
        System.out.println("Program started");
    }
}
```

JUnit works differently.

In JUnit, **your test classes are not the entry point**.  
Instead, a **JUnit Test Engine (Test Runner)** starts the execution and runs the tests.

---

# 1. What is the JUnit Engine?

The **JUnit Engine** is a component responsible for:

- discovering test classes
- discovering test methods
- executing those tests
- reporting results

In simple terms:

> The JUnit Engine is the program that finds and runs your tests.

Your test classes are simply **instructions for the engine**.

---

# 2. Do Test Classes Have a `main()` Method?

No.

JUnit test classes **do not need a `main()` method**.

Example test class:

```java
import org.junit.jupiter.api.Test;

class GreeterTest {

    @Test
    void greet_shouldReturnHello() {
        System.out.println("Running test...");
    }

}
```

There is no `main()` method here.

Instead, the **JUnit engine starts first**, and it executes all test methods.

---

# 3. Who Starts the JUnit Engine?

Normally, developers do not start the engine manually.

The JUnit engine is started by tools such as:

- IDEs (IntelliJ IDEA, Eclipse, VS Code)
- Build tools (Maven / Gradle)
- CI systems (GitHub Actions, Jenkins, etc.)

Example with Maven:

```
mvn test
```

Example with Gradle:

```
gradle test
```

These tools start the **JUnit Platform**, which loads the JUnit Engine and runs tests.

So the actual execution flow is:

```
IDE / Build Tool
        ↓
JUnit Platform
        ↓
JUnit Engine
        ↓
Your Test Classes
```

---

# 4. Does JUnit Scan All Classes in the Project?

JUnit **does not scan every class in the project randomly**.

In standard Java projects, code is usually separated into two directories:

```
src/main/java
src/test/java
```

### src/main/java

Contains the **application code**.

Example:

```
Greeter.java
UserService.java
OrderController.java
```

These classes are **not tests**.

---

### src/test/java

Contains **test classes**.

Example:

```
GreeterTest.java
UserServiceTest.java
OrderControllerTest.java
```

JUnit engines and build tools usually **scan only this directory** for tests.

This keeps application code separate from test code.

---

# 5. How JUnit Detects Test Methods

JUnit detects tests using **annotations**.

Example:

```java
@Test
void greet_shouldReturnHello() {
}
```

When the engine scans classes, it checks for:

```
@Test
```

Any method with this annotation is considered a **test method**.

If the annotation is missing, the method will not be executed.

---

# 6. Do Test Classes Need to Be Public?

In **JUnit 4**, test classes had to be `public`.

But in **JUnit 5**, this is no longer required.

Example (valid in JUnit 5):

```java
class GreeterTest {

    @Test
    void greet_shouldReturnHello() {

    }

}
```

The class is **package-private**, not public.

This works because JUnit uses **reflection** to discover and execute test methods.

Reflection allows JUnit to inspect and invoke methods even if they are not public.

Because of this capability:

- Test classes can be package-private
- Test methods can also be package-private

This makes test code cleaner and avoids unnecessary public visibility.

---

# 7. Why Are Application Classes Usually Public?

Application classes are often public because they need to be accessed from different packages.

Example:

```
controller → service → repository
```

These layers often live in different packages.

Therefore classes are marked `public`.

Test classes usually exist only inside the test package and do not need external access, so they don't need to be public.

---

# 8. How the JUnit Engine Finds Tests

The test discovery process usually follows these steps:

```
1. Start JUnit Platform
2. Locate test directories
3. Load classes from src/test/java
4. Inspect each class
5. Find methods annotated with @Test
6. Register those methods as tests
7. Execute them
8. Collect results
9. Report success or failure
```

This process is called **Test Discovery**.

---

# 9. What Happens During Test Execution?

For each test method, JUnit performs steps similar to:

```
Create test class instance
        ↓
Execute test method
        ↓
Evaluate assertions
        ↓
Mark test as PASSED or FAILED
```

Example result:

```
✔ greet_shouldReturnHello
```

or

```
✘ greet_shouldReturnHello
Expected: Hello!
Actual: Hi!
```

---

# 10. Simplified Execution Flow

The complete flow of running a test looks like this:

```
Developer runs tests
        ↓
IDE / Maven / Gradle starts
        ↓
JUnit Platform starts
        ↓
JUnit Engine loads
        ↓
Engine scans src/test/java
        ↓
Find classes with @Test methods
        ↓
Execute test methods
        ↓
Assertions validate behavior
        ↓
Results are reported
```

---

# 11. Visual Overview

```
Your Command / IDE
        │
        ▼
JUnit Platform
        │
        ▼
JUnit Engine
        │
        ▼
Scan src/test/java
        │
        ▼
Find @Test methods
        │
        ▼
Run Tests
        │
        ▼
Report Results
```

---

# 12. Key Points

- JUnit tests **do not require a main method**.
- The **JUnit Engine** executes the tests.
- IDEs and build tools start the JUnit Platform automatically.
- Tests are usually placed in `src/test/java`.
- JUnit detects test methods using the `@Test` annotation.
- In **JUnit 5**, test classes do **not need to be public**.
- Test execution follows a discovery → execution → reporting process.

---

Understanding the JUnit engine helps clarify an important idea:

> Test classes are not programs by themselves.  
> They are instructions that the JUnit engine discovers and executes.
