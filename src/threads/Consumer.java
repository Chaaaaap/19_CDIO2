package threads;

import cirbuffer.CirBuf;

public class Consumer implements Runnable {
    private int speed;
    private CirBuf cb;
    
    public void run() {
        char getChar;
        while (true) {
            getChar = cb.get();
            System.out.println("Udtager: " + getChar + " " + cb);
            try {
            	
            } catch (Exception e) {
            	
            }
        }
    }
    public Consumer(CirBuf buf, int speed) {
        cb = buf;
        this.speed = speed;
        new Thread(this, "Consumer").start();
    }
}

