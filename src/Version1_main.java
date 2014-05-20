import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Marco on 20.05.2014.
 */
public class Version1_main {
    public static void main(String[] args) {
        // TODO: In the main method, start these n threads and let them calculate prime numbers as long as they are not interrupted.
        // TODO: Also start a calculation in the main method, which calculates the list of prime numbers until the maximum number m.
        // TODO: When the calculation of prime numbers (2, 3, …, m) is completed in the main method, interrupt all the other running threads.
        // TODO: When all threads (except the main one) have been terminated, display for each thread the highest prime number that was calculated.

        int numOfThreads = 1;
        List<Thread> workers = new ArrayList<Thread>(numOfThreads);
        for (int num = 1; num <= numOfThreads; num++){
            workers.add(new PrimeCalculator("Worker " + num));
        }
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println("finisch...");
        for(Thread thread : workers){
            thread.interrupt();
        }
    }
}
