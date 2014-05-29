import sun.org.mozilla.javascript.internal.Context;
import sun.org.mozilla.javascript.internal.Scriptable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Created by Marco on 20.05.2014.
 */
public class Version2_PrimeCalculator implements Callable{
    // DONE: Each thread should sleep randomly between 0 and 1 s between each calculation step.
    // DONE: Name every thread and distribute the output to the console conveniently in order to observer which thread is running or waiting (thread state).
    // DONE: To check, if a number is a prime number, use the following method (given in exercise description)

    String name;
    int curNum;
    int maxNum;
    Object lock;
    List<Integer> primes = new ArrayList<Integer>();
    private boolean LOG = false;

    public Version2_PrimeCalculator(String name, int minNum, int maxNum) {
        this.name = name;
        this.lock = lock;
        this.curNum = minNum;
        this.maxNum = maxNum;
    }

    public static boolean isPrime(int no) {
        if (no < 2) return false;
        for (int i = 2; i < no; i++) {
            if (no % i == 0) return false;
        }
        return true;
    }


    private void log(String message) {
        if (LOG) System.out.println("Thread " + name + ": " + message);
    }

    @Override
    public Object call() throws Exception {
        log("Started Execution of " + name + ", calculation primes from " + curNum + " to " + maxNum);

        Random random = new Random();

        for (; curNum <= maxNum; curNum++) {
            if (isPrime(curNum)) {
                primes.add(curNum);
            }
        }
        log("Finished Execution");
        return primes;
    }
}
