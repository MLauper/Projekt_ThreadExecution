import java.util.ArrayList;
import java.util.List;

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

        //Start numOfThreads threads
        int numOfThreads = 25;
        List<Thread> workers = new ArrayList<Thread>(numOfThreads);
        List<List<Integer>> primesOfThread = new ArrayList<List<Integer>>();
        for (int num = 1; num <= numOfThreads; num++){
            primesOfThread.add(new ArrayList<Integer>());
            workers.add(new PrimeCalculator("Worker " + num, primesOfThread.get(num-1)));
        }

        //Start Calculation in main method from minPrime to maxPrime
        int minPrime = 1;
        int maxPrime = 1000;
        List<Integer> primes = new ArrayList<Integer>();
        for (int i = minPrime;i <= maxPrime ;i++){
            if(PrimeCalculator.isPrime(i)) primes.add(i);
        }

        //Scanner scanner = new Scanner(System.in);
        //scanner.nextLine();
        Thread.sleep(1000);

        System.out.println("Fimnish, interrupting threads...");
        for(Thread thread : workers){
            thread.interrupt();
        }

        System.out.println("Found Prime Numbers in MAIN");
        System.out.println(primes);

        System.out.println("Found Prime Numbers in THREADS");
        for (List<Integer> p : primesOfThread){
            System.out.println(p);
            System.out.println("Found " + p.size() + " Primes");
        }
    }
}
