import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Marco on 20.05.2014.
 */
public class Version1_main {
    public static void main(String[] args) throws InterruptedException {
        // DONE: In the main method, start these n threads and let them calculate prime numbers as long as they are not interrupted.
        // DONE: Also start a calculation in the main method, which calculates the list of prime numbers until the maximum number m.
        // DONE: When the calculation of prime numbers (2, 3, …, m) is completed in the main method, interrupt all the other running threads.
        // DONE: When all threads (except the main one) have been terminated, display for each thread the highest prime number that was calculated.

        // DONE: ArrayList evtl. durch Vector ersetzen -> TrheadSafe... --> Replaced by a synchronized List

        // Teime measurement

        List<Long> runTime = Collections.synchronizedList(new ArrayList<Long>());

        //Start numOfThreads threads
        int numOfThreads = 10;
        Object lock = null;
        List<Thread> workers = new ArrayList<Thread>(numOfThreads);
        List<List<Integer>> primesOfThread = new ArrayList<List<Integer>>();
        for (int num = 1; num <= numOfThreads; num++) {
            //primesOfThread.add(new ArrayList<Integer>());
            //Using synchronizedList to be ThreadSafe
            primesOfThread.add(Collections.synchronizedList(new ArrayList<Integer>()));
            workers.add(new Version1_PrimeCalculator("Worker " + num, primesOfThread.get(num - 1), lock));
        }


        //Start Calculation in main method from minPrime to maxPrime
        int minPrime = 1;
        int maxPrime = 100000;
        List<Integer> primes = new ArrayList<Integer>();
        for (int i = minPrime; i <= maxPrime; i++) {
            if (Version1_PrimeCalculator.isPrime(i)) {
                primes.add(i);
            }
        }

        System.out.println("Finish, interrupting threads...");
        for (Thread thread : workers) {
            thread.interrupt();
        }

        for (Thread thread : workers) {
            thread.join();
        }

        //System.out.println("Found Prime Numbers in MAIN");
        //System.out.println(primes);

        System.out.println("Found Prime Numbers in THREADS");
        int i = 1;
        for (List<Integer> p : primesOfThread) {
            //System.out.println(p);
            System.out.println("Thread Number " + i + ", Found " + p.size() + " Primes, Max Prime is " + p.get(p.size()-1));
            i++;
        }


    }
}