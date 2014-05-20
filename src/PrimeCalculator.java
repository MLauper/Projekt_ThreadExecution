/**
 * Created by Marco on 20.05.2014.
 */
public class PrimeCalculator extends Thread {
    String name;

    public PrimeCalculator(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        super.run();
    }

    @Override
    public synchronized void start() {
        super.start();
    }

    public static boolean isPrime(int no){
        if (no < 2) return false;
        for (int i = 2; i < no; i++){ if (no % i == 0) return false; }
        return true;
    }
}
