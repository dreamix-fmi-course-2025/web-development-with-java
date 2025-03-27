# Additional Tasks 

## 1. Advanced Filtering and Mapping
### Task 1: Filter and Transform a List of Transactions
You have a `Transaction` class:

```java
public class Transaction {
    private String id;
    private String type; // "DEPOSIT" or "WITHDRAWAL"
    private double amount;
    private LocalDateTime timestamp;

    // Constructor, Getters, Setters
}
```

**Objective:**
1. **Filter** transactions that occurred in the last **7 days**.
2. **Convert** all `"WITHDRAWAL"` transactions to negative values.
3. **Sort** transactions by timestamp **descending**.

**Method Signature:**
```java
public List<Double> processTransactions(List<Transaction> transactions)
```

---

## 2. Grouping and Aggregation
### Task 2: Group Transactions by Type and Sum Their Amounts
Using the same `Transaction` class, implement a method that:
- Groups transactions by their `type` (`DEPOSIT`, `WITHDRAWAL`).
- Computes the **total amount** for each type.

**Expected Output Example:**
```
{
    "DEPOSIT": 12000.50,
    "WITHDRAWAL": -5400.75
}
```

**Method Signature:**
```java
public Map<String, Double> sumByTransactionType(List<Transaction> transactions)
```

---

## 3. Nested Streams and FlatMapping
### Task 3: Extract Unique Skills from a List of Employees
You have an `Employee` class:

```java
public class Employee {
    private String name;
    private List<String> skills;

    // Constructor, Getters, Setters
}
```

**Objective:**
1. Extract all unique skills from a list of employees.
2. Return them **sorted alphabetically**.

**Method Signature:**
```java
public List<String> getUniqueSortedSkills(List<Employee> employees)
```

**Hint:** Use `flatMap()` and `distinct()`.

---

## 4. Reduction and Custom Collectors
### Task 4: Find the Employee with the Highest Salary
You have an `Employee` class:

```java
public class Employee {
    private String name;
    private double salary;

    // Constructor, Getters, Setters
}
```

**Objective:**
- Find the **employee with the highest salary** using **Stream.reduce()**.

**Method Signature:**
```java
public Optional<Employee> findHighestPaidEmployee(List<Employee> employees)
```

---

## 5. Parallel Streams
### Task 5: Parallel Processing - Compute Large Prime Numbers
Create a method that:
- Filters out **prime numbers** from a list of `integers`.
- Uses **parallel streams** for performance.

**Method Signature:**
```java
public List<Integer> findPrimeNumbers(List<Integer> numbers)
```

**Hint:** Use `IntStream.rangeClosed(2, sqrt(n))` to check primality efficiently.

---

## 6. Custom Collector
### Task 6: Compute Employee Salary Statistics
Using the same `Employee` class:
- Compute **average**, **min**, and **max salary** using a **custom collector**.

**Expected Output Example:**
```
{
    "average": 45000.5,
    "min": 32000.0,
    "max": 78000.0
}
```

**Method Signature:**
```java
public Map<String, Double> computeSalaryStatistics(List<Employee> employees)
```

**Hint:** Use `Collector.of()` for a custom collector.

---

## 7. Stream-Based File Processing
### Task 7: Count Word Frequency in a Large File
You have a large text file where each line contains words. Implement a method that:
- Reads the file **line by line** using `Files.lines(Path)`.
- Splits each line into words.
- Counts **word occurrences**.

**Method Signature:**
```java
public Map<String, Long> countWordFrequency(Path filePath)
```

**Hint:** Use `Collectors.groupingBy()`.

---

## 8. Combination of Streams
### Task 8: Find the Top 3 Most Expensive Products in Each Category
You have a `Product` class:

```java
public class Product {
    private String name;
    private String category;
    private double price;

    // Constructor, Getters, Setters
}
```

**Objective:**
- Group products by `category`.
- Sort products **by price descending** within each category.
- Return the **top 3 most expensive** products per category.

**Method Signature:**
```java
public Map<String, List<Product>> topExpensiveProductsByCategory(List<Product> products)
```

**Hint:** Use `groupingBy()` and `limit()` inside a `Collector`.

---