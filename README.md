
![Logo](/Concurrency_Logo.png)

# Concurrency Demo with Spring Boot (Java 21)

This project demonstrates core concepts of **Java concurrency** using a RESTful Spring Boot application. It covers:

- `ExecutorService`
- `Callable` and `Future`
- `Locks` and `Atomic Variables`

## 🧪 How to Run

1. Clone the repository
2. Run the application using:

```bash
./mvnw spring-boot:run
```

3. Test the endpoints using a browser or Postman:

- `GET /concurrency/executor`
- `GET /concurrency/callable`
- `GET /concurrency/atomic`

---

## 📚 Topics Explained

### 1. ExecutorService

`ExecutorService` provides a pool of threads for executing tasks asynchronously.

**Example:**

```java
ExecutorService executor = Executors.newFixedThreadPool(3);
executor.submit(() -> System.out.println("Running async task"));
executor.shutdown();
```

**Endpoint:** `/concurrency/executor`

**Use Case:** Execute multiple independent tasks concurrently without manually managing threads.

**Reference:**
- [Oracle Documentation - ExecutorService](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/concurrent/ExecutorService.html)

---

### 2. Callable and Future

`Callable` returns a result and can throw exceptions. A `Future` represents the result of an asynchronous computation.

**Example:**

```java
Callable<String> task = () -> "Result";
Future<String> future = executor.submit(task);
String result = future.get(); // blocking call
```

**Endpoint:** `/concurrency/callable`

**Use Case:** When you need the result of an async task (e.g. from a microservice or DB call).

**Reference:**
- [Oracle Documentation - Callable](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/concurrent/Callable.html)
- [Oracle Documentation - Future](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/concurrent/Future.html)

---

### 3. Locks and Atomic Variables

#### Atomic Variables

`AtomicInteger`, `AtomicBoolean`, etc., provide thread-safe operations without explicit locking.

**Example:**

```java
AtomicInteger counter = new AtomicInteger();
int updated = counter.incrementAndGet();
```

#### Locks

`ReentrantLock` provides more control than `synchronized`, including timed locks and interruptible locks.

**Example:**

```java
ReentrantLock lock = new ReentrantLock();
lock.lock();
try {
    // critical section
} finally {
    lock.unlock();
}
```

**Endpoint:** `/concurrency/atomic`

**Use Case:** Protect shared mutable state with locks or atomic operations in multithreaded applications.

**Reference:**
- [Oracle Documentation - AtomicInteger](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/concurrent/atomic/AtomicInteger.html)
- [Oracle Documentation - ReentrantLock](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/concurrent/locks/ReentrantLock.html)

---

## 📁 Project Structure

```
src
└── main
    ├── java
    │   └── com.example.concurrency
    │       ├── controller
    │       │   └── ConcurrencyController.java
    │       └── service
    │           ├── ExecutorServiceDemo.java
    │           ├── CallableFutureDemo.java
    │           └── LockAtomicDemo.java
    └── resources
        └── application.yml
```

---

## ✅ Benefits of Concurrency

- Improved throughput and responsiveness
- Efficient use of CPU resources
- Scalable architecture for I/O-bound or CPU-bound tasks

---

## 📘 Further Reading

- [Baeldung - Java Concurrency](https://www.baeldung.com/java-concurrency)
- [Official Java Tutorials](https://docs.oracle.com/javase/tutorial/essential/concurrency/)
- [Concurrency Utilities Javadoc](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/concurrent/package-summary.html)
