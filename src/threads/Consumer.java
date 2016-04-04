package threads;

import cirbuffer.CirBuf;

public class Consumer implements Runnable {
    private int speed;
    private CirBuf theCirBuf;
    
    public void run() {
        char getChar;
        while (true) {
            getChar = theCirBuf.get();
            System.out.println("Udtager: " + getChar + " " + theCirBuf);
            try {
            	Thread.sleep(speed);
            	//Thread.sleep((int) (Math.random() * speed));
            } catch (InterruptedException e) {
            }
        }
    }
    public Consumer(CirBuf buf, int speed) {
        this.theCirBuf = buf;
        this.speed = speed;
        new Thread(this, "Consumer").start();
    }
}

