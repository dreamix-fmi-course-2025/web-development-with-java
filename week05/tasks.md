# Tasks

Before continuing, ensure that you have at least one full domain thread (controller -> service -> repository) from the last week tasks


## Task 0
Implement two [logging](https://www.graylog.org/post/server-log-files-in-a-nutshell#:~:text=A%20server%20log%20file%20is,or%20the%20application%20was%20accessed.) mechanism to show data from your application - to a console and to a file

Your custom loggers must implement the following interface:
```
public interface Logger {

    void info(Object toLog);

    void debug(Object toLog);

    void trace(Object toLog);

    void error(Object toLog);
}
```

The first implementation should use the STDOUT (standard output -> System.out.println)

The second one should store information inside file (use the code snippet bellow)
```
private void logInformation(Object toLog, LoggerLevel currentLoggerLevel) {
    File log = new File("log.txt");
    try (PrintWriter out = new PrintWriter(new FileWriter(log, true))) {
        out.println(new Date() + " [" + currentLoggerLevel.getLabel() + "] - " + toLog);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

```

Information for the logger level can be store inside enum like:
```
public enum LoggerLevel {

     INFO(1, "INFO"),
     DEBUG(2, "DEBUG"),
     TRACE(3, "TRACE"),
     ERROR(0, "ERROR");

     private final Integer code;
     public final String label;

     LoggerLevel(Integer code, String label) {
         this.code = code;
         this.label = label;
     }

     public Integer getCode() {
         return code;
     }

     public final String getLabel() {
         return label;
     }

 }
```

Based on the logging level defined inside the application.propeties limit the information that you log inside your application.

Logging parameters: INFO, DEBUG, TRACE
```
INFO level log only INFO
DEBUG level log INFO & DEBUG
TRACE level log INFO & DEBUG & TRACE
```
error log is always shown.


Based on your active spring profile you must use the appropriate implementation
For example 
```
local profile to use the STDOUT implementation
dev profile to use the FILE implementation
```

## Task 1
Use your custom logger inside ClubManagementSystem.

*Example*: log data when you enter creation method -> logger.info("addItem triggered");


## Task 2
*Note:* to finish this task you need InventoryService implementation.

Implement property data reading from the application.properties file.

In our ClubManagementSystem system we want to redefine what low-stock threshold means. An low-stock threshold is quantity which is below 7 units (old implementation getLowStockItems(7)). The usage of threshold passed to the service is no longer required (make sure that if the property is not set a default of 10 is set)

Property example:

```
config.inventory.low-stock-threshold=7
```

Tips: you can use @Value or AppConfig class from lecture example. 
[similar class implementation](https://github.com/GeorgiMinkov/smart-garden/blob/master/ms-smart-garden/src/main/java/bg/unisofia/fmi/robotcourse/config/AppConfig.java) and
[property file](https://github.com/GeorgiMinkov/smart-garden/blob/master/ms-smart-garden/src/main/resources/application.properties)

## Task 3 - Reminder for Soon-to-Be Overdue Transactions

### User Story
**Title:** Implement Alerting Mechanism for Soon-to-Be Overdue Borrowable Items  
**As** a system administrator,  
**I want** the system to notify me when items are about to become overdue,  
**So that** I can contact members and reduce overdue returns.

---

### Acceptance Criteria

- A configurable property `config.transaction.reminder-safety-window-days` determines the reminder threshold.
- Default value is `3` days.
- Unreturned transactions are considered "soon to be overdue" if:
  ```
  dueDate - today <= reminderWindow
  ```
- A REST endpoint returns all such transactions:  
  **GET /api/transactions/reminder**
- Log the result of every check:
  - ✅ If matches: log count and transaction IDs.
  - ❌ If none: log "No upcoming due transactions found."

## Bonus Task (REST API {lec06})
For your management platform create a simple get all inventory items REST endpoint

Steps:

- add web starter dependency (Additional info: Starter of Spring web uses Spring MVC, REST and Tomcat as a default embedded server. The single spring-boot-starter-web dependency transitively pulls in all dependencies related to web development. It also reduces the build dependency count.)
```
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
```

- create a controller package
- use @RestController (Additional info: you can use @Controller but, @RestController annotation in order to simplify the creation of RESTful web services. It's a convenient annotation that combines @Controller and @ResponseBody, which eliminates the need to annotate every request handling method of the controller class with the @ResponseBody annotation.)
- use @GetMapping (Additional info: Annotation for mapping HTTP GET requests onto specific handler methods. Specifically, @GetMapping is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod. GET) .)

Example:
```
@RestController
@RequestMapping("example")
public class SimpleBookRestController {
    
    @GetMapping("/books")
    public Book getBook() {
        return service.getBooks();
    }
}
```