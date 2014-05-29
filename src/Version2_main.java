import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Marco on 20.05.2014.
 */
public class Version2_main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // DONE: In the main thread, calculate a list of prime numbers until the maximum value m and store these numbers in an array list.
        // DONE: Recalculate the same prime numbers, but divide the calculation equally between m parallel threads. E.g., when you decide to calculate prime numbers until 1000 with 5 threads, the first thread should calculate prime numbers in the interval 1..200, the second in the interval 201..400, etc.
        // DONE: Each thread must store the prime number it calculates in a local array list and when it terminates, it should return this list to the caller (use Callable object).
        // DONE: At program end, all partial lists (one for each thread) must be stored together in an array list which must have the same content as the array list built in the main thread. Check this!

        int numOfThreads = 1;
        int minPrime = 0;
        int maxPrime = 50000;

        long startThreadProc, stopThreadProc, startMainProc, stopMainProc;

        /////////////////////////////////////////
        // START Main Thread Only Calculation
        /////////////////////////////////////////
        startMainProc = System.nanoTime();

        //Start Calculation in main method from minPrime to maxPrime
        List<Integer> primes = new ArrayList<Integer>();
        for (int i = minPrime; i <= maxPrime; i++) {
            if (Version2_PrimeCalculator.isPrime(i)) {
                primes.add(i);
            }
        }
        stopMainProc = System.nanoTime();
        /////////////////////////////////////////
        // END Main Thread Only Calculation
        /////////////////////////////////////////

        /////////////////////////////////////////
        // START Multi-Thread based Calculation
        /////////////////////////////////////////
        startThreadProc = System.nanoTime();

        int blocksize = ((maxPrime - minPrime) - ((maxPrime - minPrime) % numOfThreads)) / numOfThreads;
        int minNum, maxNum;

        ExecutorService pool = Executors.newFixedThreadPool(numOfThreads);

        List<Future<List<Integer>>> futurePrimesOfThread = new ArrayList<Future<List<Integer>>>();

        for (int num = 1; num <= numOfThreads; num++) {
            minNum = minPrime + (num - 1) * blocksize;
            maxNum = num < numOfThreads ? minNum + blocksize - 1 : maxPrime;

            Callable callable = new Version2_PrimeCalculator("Worker " + num, minNum, maxNum);
            Future<List<Integer>> future = pool.submit(callable);
            futurePrimesOfThread.add(future);
        }

        ArrayList<Integer> consolidatedPrimes = new ArrayList<Integer>();
        for (Future<List<Integer>> listFuture : futurePrimesOfThread) {
            consolidatedPrimes.addAll(listFuture.get());
        }

        pool.shutdown();

        stopThreadProc = System.nanoTime();
        /////////////////////////////////////////
        // END Multi-Thread based Calculation
        /////////////////////////////////////////

        /////////////////////////////////////////
        // START Statistics
        /////////////////////////////////////////
        System.out.println("Found Primes in MAIN: " + primes);
        System.out.println("Found Primes in Threads: " + consolidatedPrimes);
        System.out.println("Needed time Single-Threading: " + (stopMainProc - startMainProc) / 1000000);
        System.out.println("Needed time Multi-Threading: " + (stopThreadProc - startThreadProc) / 1000000);
        System.out.println("The two arrays are" + (consolidatedPrimes.equals(primes) ? " " : " NOT ") + "equal!");
        /////////////////////////////////////////
        // END Statistics
        /////////////////////////////////////////
    }
}