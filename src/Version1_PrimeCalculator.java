import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Marco on 20.05.2014.
 */
public class Version1_PrimeCalculator extends Thread {
    // DONE: Each thread should sleep randomly between 0 and 1 s between each calculation step.
    // DONE: Name every thread and distribute the output to the console conveniently in order to observer which thread is running or waiting (thread state).
    // DONE: To check, if a number is a prime number, use the following method (given in exercise description)

    String name;
    int curNum;
    int maxNum;
    Object lock;
    List<Integer> primes = new ArrayList<Integer>();
    private boolean LOG = true;

    public Version1_PrimeCalculator(String name, List<Integer> primes, Object lock) {
        this.name = name;
        this.primes = primes;
        this.lock = lock;
        this.start();
    }

    public static boolean isPrime(int no) {
        if (no < 2) return false;
        for (int i = 2; i < no; i++) {
            if (no % i == 0) return false;
        }
        return true;
    }

    @Override
    public void run() {
        log("Started Execution");
        curNum = 1;
        Random random = new Random();

        for (; !(this.isInterrupted()); curNum++) {
            if (isPrime(curNum)) {
                primes.add(curNum);
                log("PRIME: " + curNum);
                try {
                    System.out.println("Thread " + name + " starts sleeping");
                    sleep(random.nextInt(1000));
                    System.out.println("Thread " + name + " working...");
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }

    private void log(String message) {
        if (LOG) System.out.println("Thread " + name + ": " + message);
    }
}
