# Concurrent Access To Shared Memory : Race Problems

## 1

```
T1 incremented i: 66
T2 decremented i: 64
Final value of i: 64
```

The result depend of the threads execution delay

## 2

This code could lead to an error because both threads can access and modify the variable "i", without any synchronization mechanism. So, a thread can read the value of "i" while another modify it, or even both threads can modify "i" simultaneously. 

# Solving the Problem : Synchronizing access using semaphores

## 1

In this version of the code, a semaphore i_semaphore is initialized with a value of 1 using sem_init. The sem_wait and sem_post functions are used to enforce mutual exclusion around the critical section where the global variable i is accessed and modified. This ensures that only one thread can access and modify i at a time.

### a

In this example, I added a third thread t3 that multiplies the global variable i by 2. The same semaphore i_semaphore is used to protect the critical sections in each of the three threads, ensuring that only one thread can access and modify i at a time. This prevents race conditions and makes the code thread-safe.