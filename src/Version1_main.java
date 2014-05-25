import java.util.*;

/**
 * Created by Marco on 20.05.2014.
 */
public class Version1_main {
    public static void main(String[] args) throws InterruptedException {
        // TODO: In the main method, start these n threads and let them calculate prime numbers as long as they are not interrupted.
        // TODO: Also start a calculation in the main method, which calculates the list of prime numbers until the maximum number m.
        // TODO: When the calculation of prime numbers (2, 3, …, m) is completed in the main method, interrupt all the other running threads.
        // TODO: When all threads (except the main one) have been terminated, display for each thread the highest prime number that was calculated.

        // TODO: ArrayList evtl. durch Vector ersetzen -> TrheadSafe...

        // Teime measurement

        List<Long> runTime = new ArrayList<Long>();

        for (int i = 1; i <= 100; i++) {
            System.out.println("Starting Execution of run " + i + " at " + new Date());
            long startTime = System.nanoTime();


            //Start numOfThreads threads
            int numOfThreads = 10000;
            Object lock = null;
            List<Thread> workers = new ArrayList<Thread>(numOfThreads);
            List<List<Integer>> primesOfThread = new ArrayList<List<Integer>>();
            for (int num = 1; num <= numOfThreads; num++) {
                primesOfThread.add(new ArrayList<Integer>());
                workers.add(new PrimeCalculator("Worker " + num, primesOfThread.get(num - 1), lock));
            }


            //Start Calculation in main method from minPrime to maxPrime
//        int minPrime = 1;
//        int maxPrime = 100000;
//        List<Integer> primes = new ArrayList<Integer>();
//        for (int i = minPrime;i <= maxPrime ;i++){
//            if(PrimeCalculator.isPrime(i)) { primes.add(i); }
//        }

            //Scanner scanner = new Scanner(System.in);
            //scanner.nextLine();
            //Thread.sleep(1000);

            //System.out.println("Fimnish, interrupting threads...");
//        for(Thread thread : workers){
//            thread.interrupt();
//        }

            for (Thread thread : workers) {
                thread.join();
            }

            //System.out.println("Found Prime Numbers in MAIN");
            //System.out.println(primes);

            //System.out.println("Found Prime Numbers in THREADS");
            //for (List<Integer> p : primesOfThread){
            //System.out.println(p);
            //System.out.println("Found " + p.size() + " Primes");
            //}

            long stopTime = System.nanoTime();
            //System.out.println("Finished Execution " + new Date());

            //System.out.println("Num of milliseconds: " + (stopTime - startTime) / 1000000);

            runTime.add((stopTime - startTime) / 1000000);

        }

        long sum = 0;
        long min = 10000000;
        long max = 0;
        for (Long value : runTime){
            sum += value;
            if (value < min) min = value;
            if (value > max) max = value;
        }
        System.out.println("Average Runtime: " + (sum / runTime.size()));
        System.out.println("Max Runtime: " + max);
        System.out.println("Min Runtime: " + min );



    }
}
