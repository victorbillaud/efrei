#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

int i = 65;

void *increment_i(void *arg) {
    i++;
    printf("T1 incremented i: %d\n", i);
    return NULL;
}

void *decrement_i(void *arg) {
    i--;
    printf("T2 decremented i: %d\n", i);
    return NULL;
}

int main() {
    pthread_t t1, t2;

    // create threads
    pthread_create(&t1, NULL, increment_i, NULL);
    pthread_create(&t2, NULL, decrement_i, NULL);

    // wait for threads to finish
    pthread_join(t1, NULL);
    pthread_join(t2, NULL);

    printf("Final value of i: %d\n", i);

    return 0;
}
