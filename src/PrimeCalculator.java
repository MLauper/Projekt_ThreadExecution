/**
 * Created by Marco on 20.05.2014.
 */
public class PrimeCalculator extends Thread {
    String name;
    int curNum;

    public PrimeCalculator(String name) {
        this.name = name;
        this.start();
    }

    @Override
    public void run() {
        log("Started Execution");
        curNum = 1000000000;
        for (;!(this.isInterrupted());curNum++){
            if(isPrime(curNum)) log("PRIME: " + curNum);
        }
    }

    public static boolean isPrime(int no){
        if (no < 2) return false;
        for (int i = 2; i < no; i++){ if (no % i == 0) return false; }
        return true;
    }

    private void log(String message){
        System.out.println("Thread " + name + ": " + message);
    }
}
