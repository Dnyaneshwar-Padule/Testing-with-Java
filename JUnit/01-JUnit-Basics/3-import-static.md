# Understanding `import static org.junit.jupiter.api.Assertions.assertEquals`

When writing JUnit tests you often see this line:

```java
import static org.junit.jupiter.api.Assertions.assertEquals;
```

At first glance it can look strange if you're used to normal imports.  
To understand it properly we need to break it into pieces.

---

# 1. The Assertions Class

JUnit provides a utility class called `Assertions`.

```java
org.junit.jupiter.api.Assertions
```

This class contains many **static methods** used for testing.

Examples:

```
assertEquals()
assertTrue()
assertFalse()
assertNull()
assertNotNull()
assertThrows()
```

Example of how it is defined internally (simplified):

```java
public class Assertions {

    public static void assertEquals(Object expected, Object actual) {
        // compare values
    }

}
```

Notice something important:

```
assertEquals is static
```

Static methods belong to the **class**, not to an object.

---

# 2. Normal Import (Without Static Import)

Without static import you would write the test like this:

```java
import org.junit.jupiter.api.Assertions;

class GreeterTest {

    @Test
    void greet_shouldReturnHello() {

        Greeter greeter = new Greeter();
        String message = greeter.greet();

        Assertions.assertEquals("Hello!", message);
    }

}
```

Here you must call the method using the class name:

```
Assertions.assertEquals()
```

This is perfectly valid Java.

---

# 3. Static Import

Static import allows you to import **static members of a class directly**.

Example:

```java
import static org.junit.jupiter.api.Assertions.assertEquals;
```

Now you can call the method directly:

```java
assertEquals("Hello!", message);
```

Instead of writing:

```
Assertions.assertEquals(...)
```

This makes test code cleaner and shorter.

This is why JUnit examples commonly use static imports.

---

# 4. Importing All Assertion Methods

Instead of importing one method, you can import **all static methods** of the class.

Example:

```java
import static org.junit.jupiter.api.Assertions.*;
```

Now you can use:

```
assertEquals()
assertTrue()
assertFalse()
assertThrows()
```

without prefixing them with `Assertions`.

This is the most common style used in JUnit tests.

---

# 5. Does Static Import Load the Entire Class?

Yes.

Even if you write:

```java
import static org.junit.jupiter.api.Assertions.assertEquals;
```

The JVM will still load the **Assertions class** when needed.

Static import only affects **how you write the code**, not what the JVM loads.

So this line:

```java
import static org.junit.jupiter.api.Assertions.assertEquals;
```

does **not load only the method**.

The class is still loaded when the program executes.

Static import is purely a **compile-time convenience feature**.

---

# 6. Can We Create an Assertions Object Instead?

You asked whether we could do something like this:

```java
private static Assertions assertions;
```

and call:

```
assertions.assertEquals(...)
```

This approach does not work properly.

Reason:

`assertEquals` is a **static method**, and static methods belong to the class itself.

They are not meant to be called through objects.

Example:

```
Assertions.assertEquals(...)
```

not

```
assertions.assertEquals(...)
```

Even if Java technically allows calling static methods through objects, it is considered **bad practice**.

Also, creating an instance of `Assertions` is unnecessary because the class is designed to work without objects.

JUnit intentionally designed `Assertions` as a **utility class**.

Utility classes usually:

- contain static methods
- do not require object creation
- act like a collection of helper functions

Examples in Java standard library:

```
Math.sqrt()
Collections.sort()
Arrays.sort()
```

These are also static utility methods.

---

# 7. Why Static Import Is Preferred in Tests

Using static import makes test code **more readable**.

Compare these two versions.

### Without static import

```java
Assertions.assertEquals(5, result);
Assertions.assertTrue(isValid);
```

### With static import

```java
assertEquals(5, result);
assertTrue(isValid);
```

The second version is cleaner and easier to read.

Testing frameworks prefer this style because tests should read almost like **plain English statements**.

Example:

```
assertEquals(expected, actual)
```

This clearly communicates the intent.

---

# 8. Summary

Static import allows direct access to static members of a class.

Example:

```
import static org.junit.jupiter.api.Assertions.assertEquals;
```

Benefits:

- removes the need to write `Assertions.` every time
- makes test code shorter
- improves readability

Important points:

- `assertEquals` is a **static method** in the `Assertions` class
- static import does **not load only that method**
- the JVM still loads the entire class
- creating an object of `Assertions` is unnecessary
- static import is simply a **syntax convenience**

---

# Common Ways to Write Assertions

### Method-specific static import

```java
import static org.junit.jupiter.api.Assertions.assertEquals;
```

### Import all assertion methods

```java
import static org.junit.jupiter.api.Assertions.*;
```

### Normal class import

```java
import org.junit.jupiter.api.Assertions;
```

Then call:

```
Assertions.assertEquals(...)
```

All three approaches work, but static imports are the **most common style used in JUnit tests**.
```
