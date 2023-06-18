#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include <semaphore.h>

int i = 65;
sem_t s1, s2, s3;

void *increment_i(void *arg) {
    sem_wait(&s1);
    sleep(1);
    sem_wait(&s2);
    i++;
    sem_post(&s1);
    sem_wait(&s3);
    printf("T1 incremented i: %d\n", i);
    sem_post(&s2);
    sem_post(&s3);

    return NULL;
}

void *decrement_i(void *arg) {
    sem_wait(&s2);
    sleep(1);
    sem_wait(&s3);
    i--;
    sem_post(&s2);
    sem_wait(&s1);
    printf("T2 decremented i: %d\n", i);
    sem_post(&s3);
    sem_post(&s1);

    return NULL;
}

// PART THREE SOLUTION
void *reset_i(void *arg) {
    sem_wait(&s3);
    sleep(1);
    sem_wait(&s1);
    i = 0;
    sem_post(&s3);
    sem_wait(&s2);
    printf("T3 reset i: %d\n", i);
    sem_post(&s1);
    sem_post(&s2);

    return NULL;
}

int main() {
    pthread_t t1, t2, t3;

    // Initialize the semaphores
    sem_init(&s1, 0, 1);
    sem_init(&s2, 0, 1);
    sem_init(&s3, 0, 1);

    // create threads
    pthread_create(&t1, NULL, increment_i, NULL);
    pthread_create(&t2, NULL, decrement_i, NULL);
    pthread_create(&t3, NULL, reset_i, NULL);

    // wait for threads to finish
    pthread_join(t1, NULL);
    pthread_join(t2, NULL);
    pthread_join(t3, NULL);

    // Destroy the semaphores
    sem_destroy(&s1);
    sem_destroy(&s2);
    sem_destroy(&s3);

    printf("Final value of i: %d\n", i);

    return 0;
}
