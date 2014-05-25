import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marco on 20.05.2014.
 */
public class PrimeCalculator extends Thread {
    private boolean LOG = false;
    String name;
    int curNum;
    int maxNum;
    Object lock;
    List<Integer> primes = new ArrayList<Integer>();

    public PrimeCalculator(String name, List<Integer> primes, Object lock) {
        this.name = name;
        this.primes = primes;
        this.lock = lock;
        this.start();
    }

    @Override
    public void run() {
        log("Started Execution");
        curNum = 1;
        maxNum = 1000;
        for (;!(this.isInterrupted()) && curNum<=maxNum ;curNum++){
            if(isPrime(curNum)) primes.add(curNum); //log("PRIME: " + curNum);
        }
    }

    public static boolean isPrime(int no){
//        if (no < 2) return false;
//        for (int i = 2; i < no; i++){ if (no % i == 0) return false; }
//        return true;
        return true;
    }

    private void log(String message){
        if(LOG) System.out.println("Thread " + name + ": " + message);
    }
}
