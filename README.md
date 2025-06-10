
![Logo](/Concurrency_Logo.png)

# Concurrency Demo with Spring Boot (Java 21)

This project demonstrates core concepts of **Java concurrency** using a RESTful Spring Boot application. It covers:

- `ExecutorService`
- `Callable` and `Future`
- `Locks` and `Atomic Variables`
- `CountDownLatch`
- `Semaphore`
- `CompletableFuture`

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
- `GET /concurrency/countdown`
- `GET /concurrency/semaphore`
- `GET /concurrency/completable`

---

## 📚 Topics Explained

### 1. ExecutorService

`ExecutorService` provides a pool of threads for executing tasks asynchronously.

```java
ExecutorService executor = Executors.newFixedThreadPool(3);
executor.submit(() -> System.out.println("Running async task"));
executor.shutdown();
```

**Endpoint:** `/concurrency/executor`

**Use Case:** Execute multiple independent tasks concurrently without manually managing threads.

📖 [Oracle Documentation - ExecutorService](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/concurrent/ExecutorService.html)

---

### 2. Callable and Future

`Callable` is a functional interface that allows tasks to return results and throw checked exceptions.  
A `Future` represents the result of an asynchronous computation, which may not be immediately available.

```java
Callable<String> task = () -> "Result";
Future<String> future = executor.submit(task);
String result = future.get(); // blocking call
```

**Endpoint:** `/concurrency/callable`

**Use Case:** Use when you need the result of an asynchronous task — for example, querying an external microservice or database.

📖 [Oracle Docs – Callable](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/concurrent/Callable.html)  
📖 [Oracle Docs – Future](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/concurrent/Future.html)

---

### 3. Locks and Atomic Variables

#### Atomic Variables

Atomic types like `AtomicInteger` provide thread-safe operations without explicit locking.

```java
AtomicInteger counter = new AtomicInteger();
int updated = counter.incrementAndGet();
```

#### Locks

`ReentrantLock` offers more fine-grained control over synchronization.

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

📖 [AtomicInteger Docs](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/concurrent/atomic/AtomicInteger.html)  
📖 [ReentrantLock Docs](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/concurrent/locks/ReentrantLock.html)

---

### 4. CountDownLatch

A `CountDownLatch` allows threads to wait until a set of operations complete.

```java
CountDownLatch latch = new CountDownLatch(3);
latch.await(); // main thread waits
```

**Endpoint:** `/concurrency/countdown`

📖 [CountDownLatch Docs](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/concurrent/CountDownLatch.html)

---

### 5. Semaphore

A `Semaphore` limits the number of threads accessing a resource simultaneously.

```java
Semaphore semaphore = new Semaphore(2);
semaphore.acquire();
try {
    // access shared resource
} finally {
    semaphore.release();
}
```

**Endpoint:** `/concurrency/semaphore`

📖 [Semaphore Docs](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/concurrent/Semaphore.html)

---

### 6. CompletableFuture

`CompletableFuture` is used for non-blocking asynchronous programming.

```java
CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello");
future.thenAccept(System.out::println);
```

**Endpoint:** `/concurrency/completable`

📖 [CompletableFuture Docs](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/concurrent/CompletableFuture.html)

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
    │           ├── LockAtomicDemo.java
    │           ├── CountDownLatchDemo.java
    │           ├── SemaphoreDemo.java
    │           └── CompletableFutureDemo.java
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
