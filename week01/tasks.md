# Task 1: Find the Nth Odd Element in an Array

## Problem Statement
Write a Java method that returns the **Nth odd element** of an array. If the given index exceeds the available number of odd elements in the list, the method should throw an exception.

## Example Inputs and Outputs

### Example 1
```java
Input: [5, 3, 8, 1, 9], 4  
Output: 9
```

### Example 2
```java
Input: [5, 3, 8, 1, 9], 5  
Output: Exception: "Index out of bounds for odd elements"
```

### Example 3 (Bigger Example)
```java
Input: [12, 15, 7, 8, 9, 10, 3, 5, 11, 19], 3  
Output: 9
```

<details>
    <summary>Solution</summary>

```java
import java.util.ArrayList;
import java.util.List;

public class OddElementFinder {

    public static int getNthOddElement(int[] arr, int n) {
        List<Integer> oddNumbers = new ArrayList<>();
        
        for (int num : arr) {
            if (num % 2 != 0) {
                oddNumbers.add(num);
            }
        }
        
        if (n > oddNumbers.size() || n <= 0) {
            throw new IndexOutOfBoundsException("Index out of bounds for odd elements");
        }
        
        return oddNumbers.get(n - 1);
    }

    public static void main(String[] args) {
        int[] numbers = {12, 15, 7, 8, 9, 10, 3, 5, 11, 19};
        
        try {
            System.out.println(getNthOddElement(numbers, 3)); // Output: 9
            System.out.println(getNthOddElement(numbers, 5)); // Output: 11
            System.out.println(getNthOddElement(numbers, 7)); // Throws Exception
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
```
</details>

<details>
  <summary>Explanation</summary>

## Explanation
1. **Filter Odd Numbers:**
   - Iterate through the array and store only the odd numbers in a `List<Integer>`.

2. **Check for Index Out of Bounds:**
   - If `n` is greater than the number of odd elements or is invalid (`n <= 0`), throw an exception.

3. **Retrieve the Nth Odd Number:**
   - Use `get(n - 1)` to fetch the nth odd number (since lists use **zero-based indexing**).

4. **Exception Handling:**
   - The `try-catch` block in `main` ensures that errors are caught gracefully.

</details>


<details>
    <summary>Alternative Approach (Using Streams)</summary>

## Alternative Approach (Using Streams)
If you want a **functional approach** using Java Streams:

```java
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OddElementFinderStream {

    public static int getNthOddElement(int[] arr, int n) {
        List<Integer> oddNumbers = Arrays.stream(arr)
                                         .filter(num -> num % 2 != 0)
                                         .boxed()
                                         .collect(Collectors.toList());

        if (n > oddNumbers.size() || n <= 0) {
            throw new IndexOutOfBoundsException("Index out of bounds for odd elements");
        }

        return oddNumbers.get(n - 1);
    }

    public static void main(String[] args) {
        int[] numbers = {12, 15, 7, 8, 9, 10, 3, 5, 11, 19};
        
        try {
            System.out.println(getNthOddElement(numbers, 3)); // Output: 9
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}
```
</details>

<details>
    <summary>Performance Considerations</summary>

## Performance Considerations
- The **first solution** uses a `for` loop with `ArrayList`, which runs in **O(n)**.
- The **second solution** uses Streams, which might be **slower** due to intermediate operations.
- **Both methods** ensure safe handling of out-of-bounds cases.

</details>

# Task 2: Autoboxing and Unboxing

## Problem Statement
Write a Java program that demonstrates **autoboxing** (primitive → wrapper) and **unboxing** (wrapper → primitive).

## Example
```java
Input: int num = 10;
Output: Autoboxed: 10

Input: Integer wrappedNum = 20;
Output: Unboxed: 20
```

---

<details>
  <summary>Solution</summary>

```java
public class AutoboxingExample {
    public static void main(String[] args) {
        // Autoboxing (int -> Integer)
        int num = 10;
        Integer obj = num; 
        System.out.println("Autoboxed: " + obj);

        // Unboxing (Integer -> int)
        Integer wrappedNum = 20;
        int primitiveNum = wrappedNum; 
        System.out.println("Unboxed: " + primitiveNum);
    }
}
```

</details>

---

<details>
  <summary>Explanation</summary>

## Explanation
- **Autoboxing:** Java automatically converts **primitive types** into **wrapper objects**.
- **Unboxing:** Java automatically converts **wrapper objects** into **primitive types**.

</details>

# Task 3: String vs StringBuilder

## Problem Statement
Write a program that demonstrates the difference between **String** and **StringBuilder** in terms of **mutability**.

## Example
```java
Input: String s = "Hello";
       s.concat(" World");
Output: Hello

Input: StringBuilder sb = new StringBuilder("Hello");
       sb.append(" World");
Output: Hello World
```

---

<details>
  <summary>Solution</summary>

```java
public class StringVsStringBuilder {
    public static void main(String[] args) {
        // Immutable String
        String s = "Hello";
        s.concat(" World"); // Not modifying original string
        System.out.println("String: " + s); // Output: Hello

        // Mutable StringBuilder
        StringBuilder sb = new StringBuilder("Hello");
        sb.append(" World"); // Modifies the original object
        System.out.println("StringBuilder: " + sb); // Output: Hello World
    }
}
```

</details>

---


<details>
  <summary>Explanation</summary>

## Explanation
- **String is immutable** – modifications create **new objects**, and the original string remains unchanged.
- **StringBuilder is mutable** – modifications happen on the **same object**, improving performance when dealing with frequent changes.

</details>


# Task 4: Person Class Implementation

## Problem Statement
Create a class named `Person` with two attributes: `name` and `age`. Implement 3 constructors:

1. **Without parameters**, setting default values for `name` as "No name" and `age` as -1.
2. **With 1 parameter** for `name` and default value for `age` as -1.
3. **With 2 parameters**, setting both attributes `name` and `age`.

Implement setters and getters for the attributes.

Override the `toString()` method to provide a string representation of the object. The string representation should follow this logic:

- If the `age` is not set, print: `Hello, I am <name>`.
- If the `name` is not set, print: `I am John Doe`.

## Example

```java
Input: Person person1 = new Person();
Output: I am John Doe

Input: Person person2 = new Person("Alice");
Output: Hello, I am Alice

Input: Person person3 = new Person("Bob", 25);
Output: Hello, I am Bob. I am 25 years old
```

---

<details>
  <summary>Solution</summary>

```java
public class Person {
    private String name;
    private int age;

    // Constructor without parameters
    public Person() {
        this.name = "No name";
        this.age = -1;
    }

    // Constructor with 1 parameter for name
    public Person(String name) {
        this.name = name;
        this.age = -1;
    }

    // Constructor with 2 parameters for name and age
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Setters and getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Overriding toString() method for string representation
    @Override
    public String toString() {
        if (this.name.equals("No name") && this.age == -1) {
            return "I am John Doe";
        } else if (this.age == -1) {
            return "Hello, I am " + this.name;
        } else if (this.name.equals("No name")) {
            return "Hello, I am " + this.age + " years old";
        } else {
            return "Hello, I am " + this.name + ". I am " + this.age + " years old";
        }
    }

    // Main method to demonstrate functionality
    public static void main(String[] args) {
        Person person1 = new Person(); // Default constructor
        Person person2 = new Person("Alice"); // Name only
        Person person3 = new Person("Bob", 25); // Both name and age

        // Print the string representations of the Person objects
        System.out.println(person1); // "I am John Doe"
        System.out.println(person2); // "Hello, I am Alice"
        System.out.println(person3); // "Hello, I am Bob. I am 25 years old"
    }
}
```

</details>

---

<details>
  <summary>Explanation</summary>

## Explanation
- **Constructors:**
  - The first constructor initializes the `name` to `"No name"` and `age` to `-1`.
  - The second constructor accepts a name and sets the `age` to `-1` by default.
  - The third constructor accepts both `name` and `age` parameters.
  
- **Setters and Getters:** 
  - Getters and setters are provided for both `name` and `age` attributes.
  
- **`toString` method:**
  - If `name` is `"No name"` and `age` is `-1`, the string returned is `"I am John Doe"`.
  - If only the `name` is provided, it returns `"Hello, I am <name>"`.
  - If only `age` is set, it returns `"Hello, I am <age> years old"`.
  - If both `name` and `age` are provided, it returns `"Hello, I am <name>. I am <age> years old"`.
</details>



# Task 5: Insert Element into ArrayList at First Position

## Problem Statement
Write a method to insert an element (String) into an `ArrayList` at the first position.

## Example

```java
Input: 
ArrayList<String> list = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie"));
insertFirst(list, "John");

Output: 
[John, Alice, Bob, Charlie]
```

---

<details>
  <summary>Solution</summary>

```java
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListExample {
    
    // Method to insert an element at the first position
    public static void insertFirst(ArrayList<String> list, String element) {
        list.add(0, element); // Insert the element at index 0 (first position)
    }

    // Main method to demonstrate functionality
    public static void main(String[] args) {
        // Initial ArrayList
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie"));
        
        // Insert "John" at the first position
        insertFirst(list, "John");
        
        // Print the updated ArrayList
        System.out.println(list); // Output: [John, Alice, Bob, Charlie]
    }
}
```

</details>

---

<details>
  <summary>Explanation</summary>

## Explanation
- The method `insertFirst` accepts an `ArrayList` and a `String` element as parameters.
- It uses the `add(index, element)` method of the `ArrayList` class to insert the element at index `0`, which is the first position.
- The `main` method demonstrates the functionality by creating an `ArrayList`, calling the `insertFirst` method, and printing the updated list.

### Key Points:
- `list.add(0, element)` inserts the specified element at the first position in the list.
- The rest of the elements are shifted one position to the right.

</details>

# Task 6: Retrieve Element from List at Specified Index

## Problem Statement
Write a method to retrieve an element (String) from a given list at a specified index.

## Example

```java
Input: 
ArrayList<String> list = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie"));
String element = retrieveElement(list, 1);

Output: 
Bob
```

---

<details>
  <summary>Solution</summary>

```java
import java.util.ArrayList;
import java.util.Arrays;

public class ListExample {

    // Method to retrieve an element at the specified index
    public static String retrieveElement(ArrayList<String> list, int index) {
        if (index >= 0 && index < list.size()) {
            return list.get(index); // Retrieve element at the specified index
        } else {
            return "Index out of bounds"; // Return message if index is invalid
        }
    }

    // Main method to demonstrate functionality
    public static void main(String[] args) {
        // Initial ArrayList
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie"));
        
        // Retrieve element at index 1
        String element = retrieveElement(list, 1);
        
        // Print the retrieved element
        System.out.println(element); // Output: Bob
    }
}
```

</details>

---

<details>
  <summary>Explanation</summary>

## Explanation
- The method `retrieveElement` takes an `ArrayList<String>` and an `int` index as parameters.
- It checks if the given index is within the valid range (0 to list size - 1) using a conditional statement.
- If the index is valid, it retrieves and returns the element using `list.get(index)`.
- If the index is invalid (out of range), it returns `"Index out of bounds"` to indicate an error.

### Key Points:
- `list.get(index)` is used to retrieve an element at a specific index from the list.
- The method handles invalid indices gracefully by returning a message instead of throwing an exception.

</details>


# Task 7: Remove the Third Element from ArrayList

## Problem Statement
Write a method to remove the third element (String) from an `ArrayList`.

## Example

```java
Input: 
ArrayList<String> list = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie", "David"));
removeThirdElement(list);

Output: 
[Alice, Bob, David]
```

---

<details>
  <summary>Solution</summary>

```java
import java.util.ArrayList;
import java.util.Arrays;

public class ListExample {

    // Method to remove the third element (index 2) from the list
    public static void removeThirdElement(ArrayList<String> list) {
        if (list.size() >= 3) {
            list.remove(2); // Remove the element at index 2 (third element)
        } else {
            System.out.println("List does not have a third element.");
        }
    }

    // Main method to demonstrate functionality
    public static void main(String[] args) {
        // Initial ArrayList
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie", "David"));
        
        // Remove the third element
        removeThirdElement(list);
        
        // Print the updated ArrayList
        System.out.println(list); // Output: [Alice, Bob, David]
    }
}
```

</details>

---

<details>
  <summary>Explanation</summary>

## Explanation
- The method `removeThirdElement` checks if the `ArrayList` has at least 3 elements (since the third element is at index 2).
- If the list has 3 or more elements, it removes the element at index 2 using `list.remove(2)`.
- If the list contains fewer than 3 elements, it prints a message indicating that the third element does not exist.

### Key Points:
- `list.remove(index)` removes the element at the specified index and shifts subsequent elements to the left.
- The method checks the list size before attempting to remove the element to prevent `IndexOutOfBoundsException`.

</details>

# Task 8: Search for an Element in a List

## Problem Statement
Write a method to search for an element (String) in a given list.

## Example

```java
Input: 
ArrayList<String> list = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie"));
boolean found = searchElement(list, "Bob");

Output: 
true
```

---

<details>
  <summary>Solution</summary>

```java
import java.util.ArrayList;
import java.util.Arrays;

public class ListExample {

    // Method to search for an element in the list
    public static boolean searchElement(ArrayList<String> list, String element) {
        return list.contains(element); // Use contains() to check if the element exists
    }

    // Main method to demonstrate functionality
    public static void main(String[] args) {
        // Initial ArrayList
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie"));
        
        // Search for "Bob" in the list
        boolean found = searchElement(list, "Bob");
        
        // Print the result of the search
        System.out.println(found); // Output: true
    }
}
```

</details>

---

<details>
  <summary>Explanation</summary>

## Explanation
- The method `searchElement` takes an `ArrayList<String>` and a `String` element as parameters.
- It uses the `contains()` method of the `ArrayList` class to check if the specified element exists in the list.
- The method returns `true` if the element is found, otherwise, it returns `false`.

### Key Points:
- `list.contains(element)` checks if the element is present in the list.
- The method simplifies the search operation and avoids the need for manually iterating over the list.

</details>


# Task 9: Replace the Second Element of an ArrayList

## Problem Statement
Write a method to replace the second element of an `ArrayList` with the specified element.

## Example

```java
Input: 
ArrayList<String> list = new ArrayList<>(Arrays.asList("Apple", "Banana", "Orange"));
replaceSecondElement(list, "Grapes");

Output: 
[Apple, Grapes, Orange]
```

---

<details>
  <summary>Solution</summary>

```java
import java.util.ArrayList;
import java.util.Arrays;

public class ListExample {

    // Method to replace the second element of the list
    public static <T> void replaceSecondElement(ArrayList<T> list, T element) {
        // Check if the list has at least two elements
        if (list != null && list.size() >= 2) {
            list.set(1, element); // Replace the second element (index 1)
        } else {
            System.out.println("The list is too small to replace the second element.");
        }
    }

    // Main method to demonstrate functionality
    public static void main(String[] args) {
        // Create a list of Strings
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Apple", "Banana", "Orange"));

        // Replace the second element with "Grapes"
        System.out.println("Before: " + list);
        replaceSecondElement(list, "Grapes");
        System.out.println("After: " + list);
    }
}
```

</details>

---

<details>
  <summary>Explanation</summary>

## Explanation

### **replaceSecondElement Method:**
- This method takes an `ArrayList<T>` and a specified element of type `T`.
- It checks if the list has at least two elements to ensure the second element exists (index 1).
- If the list is valid, it uses the `set()` method to replace the second element at index `1` with the new element.
- If the list has fewer than two elements, it prints a message indicating that the replacement is not possible.

### **Usage:**
- The method can be used with any type of list (`String`, `Integer`, `Dog`, etc.), thanks to the generic type `<T>`.
- The `set()` method is used to replace the element at the specified index (in this case, index `1`, the second element).

### Example:
- Before replacement: `["Apple", "Banana", "Orange"]`
- After replacement: `["Apple", "Grapes", "Orange"]`

</details>

Here’s the markdown solution for the task:


# Task 10: Count the Number of Key-Value Mappings in a Map

## Problem Statement
Write a Java program to count the number of key-value (size) mappings in a `Map`.

## Example

```java
Input: 
Map<String, Integer> map = new HashMap<>();
map.put("Apple", 10);
map.put("Banana", 20);
map.put("Orange", 30);

Output: 
The size of the map is: 3
```

---

<details>
  <summary>Solution</summary>

```java
import java.util.HashMap;
import java.util.Map;

public class MapExample {

    // Method to count the number of key-value mappings in a map
    public static <K, V> int countMappings(Map<K, V> map) {
        return map.size(); // Returns the number of key-value mappings
    }

    // Main method to demonstrate functionality
    public static void main(String[] args) {
        // Create a map and add some key-value pairs
        Map<String, Integer> map = new HashMap<>();
        map.put("Apple", 10);
        map.put("Banana", 20);
        map.put("Orange", 30);

        // Count the number of key-value mappings
        int size = countMappings(map);

        // Display the result
        System.out.println("The size of the map is: " + size);
    }
}
```

</details>

---

<details>
  <summary>Explanation</summary>

## Explanation

### **countMappings Method:**
- The `countMappings` method accepts a `Map<K, V>` where `K` is the type of the key and `V` is the type of the value.
- The method uses the `size()` function of the `Map` interface, which returns the number of key-value mappings in the map.

### **Main Method:**
- A `HashMap<String, Integer>` is created, and key-value pairs are added.
- The `countMappings` method is called to find the number of mappings in the map.
- The result is printed using `System.out.println()`.

### Key Points:
- The `size()` method is a built-in method of the `Map` interface in Java, which returns the number of key-value mappings in the map.
  
### Example:
- Given the map: `{"Apple"=10, "Banana"=20, "Orange"=30}`
- The size of the map will be `3` because there are three key-value pairs.

</details>


### Task 11: Aircraft Flight Tracker

**Objective**:  
Create a system that will represent an aircraft's tail number and associate it with a list of `FlightLeg` information. Implement filtering based on specific airports, and output the relevant details.

---

<details>
  <summary>Solution</summary>

```java
import java.time.LocalDate;
import java.util.*;

// FlightLeg class to represent individual flight details
class FlightLeg {
    private String fromAirport;
    private String toAirport;
    private LocalDate date;

    // Constructor to initialize flight leg details
    public FlightLeg(String fromAirport, String toAirport, LocalDate date) {
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
        this.date = date;
    }

    // Getters for attributes
    public String getFromAirport() {
        return fromAirport;
    }

    public String getToAirport() {
        return toAirport;
    }

    public LocalDate getDate() {
        return date;
    }

    // Override toString method to format FlightLeg details
    @Override
    public String toString() {
        return "From: " + fromAirport + " To: " + toAirport + " Date: " + date;
    }
}

// Aircraft class to represent the aircraft with its tail number and associated flight legs
class Aircraft {
    private String tailNumber;
    private List<FlightLeg> flightLegs;

    // Constructor to initialize the aircraft with a tail number
    public Aircraft(String tailNumber) {
        this.tailNumber = tailNumber;
        this.flightLegs = new ArrayList<>();
    }

    // Method to add flight leg to the aircraft
    public void addFlightLeg(FlightLeg flightLeg) {
        flightLegs.add(flightLeg);
    }

    // Method to get all flight legs associated with this aircraft
    public List<FlightLeg> getFlightLegs() {
        return flightLegs;
    }

    // Method to filter and return legs involving a specific airport
    public List<FlightLeg> filterLegsByAirport(String airportCode) {
        List<FlightLeg> filteredLegs = new ArrayList<>();
        for (FlightLeg leg : flightLegs) {
            if (leg.getFromAirport().equals(airportCode) || leg.getToAirport().equals(airportCode)) {
                filteredLegs.add(leg);
            }
        }
        return filteredLegs;
    }

    // Getter for tail number
    public String getTailNumber() {
        return tailNumber;
    }
}

// Main class to demonstrate the Aircraft Flight Tracker functionality
public class AircraftFlightTracker {
    public static void main(String[] args) {
        // Step 1: Create flight legs (from airport, to airport, date)
        FlightLeg leg1 = new FlightLeg("LBSF", "EBBR", LocalDate.of(2025, 2, 26));
        FlightLeg leg2 = new FlightLeg("EBBR", "LBSF", LocalDate.of(2025, 2, 27));
        FlightLeg leg3 = new FlightLeg("LBSF", "EGLL", LocalDate.of(2025, 3, 1));
        FlightLeg leg4 = new FlightLeg("EGLL", "EBBR", LocalDate.of(2025, 3, 2));

        // Step 2: Create an aircraft and associate flight legs with it
        Aircraft aircraft = new Aircraft("9H-VCA");
        aircraft.addFlightLeg(leg1);
        aircraft.addFlightLeg(leg2);
        aircraft.addFlightLeg(leg3);
        aircraft.addFlightLeg(leg4);

        // Step 3: Use a map to associate the aircraft tail number with its flight legs
        Map<String, List<FlightLeg>> aircraftData = new HashMap<>();
        aircraftData.put(aircraft.getTailNumber(), aircraft.getFlightLegs());

        // Step 4: Display all flight legs for the aircraft
        System.out.println("=== Flight Legs for Aircraft " + aircraft.getTailNumber() + " ===");
        for (FlightLeg flightLeg : aircraftData.get("9H-VCA")) {
            System.out.println(flightLeg);
        }

        // Step 5: Filter and display flight legs involving the airport LBSF
        System.out.println("\n=== Flight Legs Involving LBSF Airport ===");
        List<FlightLeg> filteredLegs = aircraft.filterLegsByAirport("LBSF");
        for (FlightLeg filteredLeg : filteredLegs) {
            System.out.println(filteredLeg);
        }
    }
}
```
</details>

<details>
<summary>Breakdown</summary>

1. **FlightLeg Class**:  
   This class is used to represent individual flight details, including the `fromAirport`, `toAirport`, and `date`. It also includes the `toString()` method to format how each `FlightLeg` should be printed.

2. **Aircraft Class**:  
   This class represents an aircraft identified by its `tailNumber`. It holds a list of `FlightLeg` objects, which are associated with the aircraft. 
   - It includes the method `addFlightLeg()` to add flight legs to the aircraft.
   - The `filterLegsByAirport()` method allows us to filter the flight legs based on the airport code (either as the departure or destination).

3. **AircraftFlightTracker Class** (Main Class):  
   - **Creating Flight Legs**: We create a few `FlightLeg` objects with different airports and dates.
   - **Populating Aircraft**: An `Aircraft` object is created with a tail number `"9H-VCA"`, and we add flight legs to it.
   - **Using a Map**: The `aircraftData` map stores the list of flight legs for each aircraft keyed by its tail number.
   - **Displaying Data**: The flight legs for the aircraft are printed, followed by filtering and displaying the flight legs that involve `LBSF` airport.

### Sample Output:

```
=== Flight Legs for Aircraft 9H-VCA ===
From: LBSF To: EBBR Date: 2025-02-26
From: EBBR To: LBSF Date: 2025-02-27
From: LBSF To: EGLL Date: 2025-03-01
From: EGLL To: EBBR Date: 2025-03-02

=== Flight Legs Involving LBSF Airport ===
From: LBSF To: EBBR Date: 2025-02-26
From: EBBR To: LBSF Date: 2025-02-27
From: LBSF To: EGLL Date: 2025-03-01
```

### Key Features:
- **Clear Output**: The output is now neatly separated into sections with titles.
- **Filtering**: The `filterLegsByAirport()` function allows for filtering flight legs by airport code, making it easy to see all flights involving a specific airport.
- **Data Organization**: The use of a `Map` to organize the flight legs by tail number ensures that we can efficiently associate multiple flight legs with the same aircraft.

</details>

# Task 11
Imagine you need to design a simple shop with a bascket. The following structure is used

High-level structure
```
User {List<Order>}
    Order {id, enum OrderStatus ; List<OrderLine> ; LocalDate orderDate ; enum PaymentMethod}
        OrderLine { quantity ; Item ; specialOffer }
            Item { String }
        PaymentMethod
```

![alt tag](https://github.com/dreamix-fmi-course-2024/web-development-with-java-lab/blob/main/lab01/class-diagram.png)

User will have a list of orders. An order will be a combination of status (enum), list of order items, date of order (LocalDate) and payment method (enum)

Order of implementation: Item -> OrderList -> PaymentMethod -> Order -> User

* Create a class named Item inside *entity* package which consists of name:String, description:String and price:BigDecimal.

* Create OrderLine which holds information for product, boolean specialOffer and count
Implement setters/getters/constructor

* Create class Order that holds information for id, status:OrderStatus, creationDate, totalPrice, paymentMethod, deliveryDueDate, user.
  * Write constructors: default by status, by id, by array of lines (use ...), getters, setters, toString, isActive

* Create a class User with list of orders

Write your enums in VO (value object) package;


```
package ....streams.vo;

public enum OrderStatus {
    DRAFT, ACTIVE, INACTIVE
}
```

```
package ....streams.vo;

public enum PaymentMethod {
    CARD,
    CASH_ON_SITE,
    CASH_ON_DELIVERY
}
```

```
package ....streams.entity;

public class Item {
    private String name;
    private String description;

    private BigDecimal price;
}
```



Write all your business logic inside *service* package (bg.uni.fmi.lab02.streams.service)

```
public class SearchExercise {

    /**
     * extract all active orders
     * @param user
     * @return List<Order>
     */
    public List<Order> getActiveOrders(User user) {
        return null;
    }

    public List<Order> getActiveOrdersByIteration(User user) {
        return null;
    }

    /**
     * Return order by a specific id
     * @param orders
     * @param orderId
     * @return Order
     */
    public Order getOrderById(List<Order> orders, long orderId) {
        return null;
    }

    public Order getOrderByIdIteration(List<Order> orders, long orderId) {
        return null;
    }

    /**
     * Return orders that have specific description for item
     * @param user
     * @param description
     * @return List<Order>
     */
    public List<Order> getOrdersThatHaveItemDescription(User user, String description) {
        return null;
    }

    /**
     * @return true if customer has at least one order with status ACTIVE
     */
    public boolean hasActiveOrders(User user) {
        return null;
    }

    /**
     * Return true if inside the Order we don't have OrderLine with special offer
     */
    public boolean canBeReturned(Order order) {
        return null;
    }

    /**
     * Return the order with maximum total price
     * @param user
     * @return
     */
    public Optional<Order> getMaxPriceOrder(User user) {
        return null;
    }
}
```

# Additional tasks
Create a Book store program. In the store we can have only one book from the same type.( Same type means book with same author, title and year). Our programm should implement all method from Store interface. 
```
public interface Store {

  /**
   * Add book to the store
   * @param o is the book which we want to add
   * @return true is the book is add successful and false is the book is already exists
   */
  boolean add(Book o);

  /**
   * Remove specific book from the store
   * @param o is the book which we want to remove
   */
  void remove(Book o);

  /**
   *  Get all books by Author
   * @param author
   * @return
   */
  List<Book> getAllBooksByAuthor(String author);

  /**
   * Get all books publish after specific year
   * @param from
   * @return
   */
  List<Book> getAllBooksPublishedAfter(LocalDate from);

  /**
   * Return all books between two dates
   * @param from
   * @param to
   * @return
   */
  List<Book> getAllBooksBetween(LocalDate from, LocalDate to);

  /**
   * Clear the whole book store
   */
  void clear();

  /**
   * Return all books grouped by author
   * @return
   */
  Map<String, List<Book>> getAllBooksGroupByAuthor();


  /**
   * Return all books grouped by publisher
   * @return
   */
  Map<String, List<Book>> getAllBooksGroupByPublisher();

  /**
   * Filter books by given filter
   * @param bookPredicate
   * @return
   */
  List<Book> getAllBooksFilterBy(Predicate<Book> bookPredicate);

}
```
Also, we have class Book:
```
public class Book {
  private String title;
  private String author;
  private BigDecimal price;
  private String publisher;
  private LocalDate publishedYear;
}
```

As a part of this task you should choose the correct collection for your Store implementation