package threads;

import cirbuffer.CirBuf;

public class Producer implements Runnable {
    private int speed;
    private CirBuf theCirBuf;

    public Producer(CirBuf buf, int speed) {
        this.theCirBuf = buf;
        this.speed = speed;
        new Thread(this, "Producer").start();
    }
    
    public void run() {
        char setChar = 'a';
        while (true) {
            theCirBuf.put(setChar);
            System.out.println("Indsætter: " + (char)setChar + " " + theCirBuf);
            try {
            	Thread.sleep(speed);
            	//Thread.sleep((int) (Math.random() * speed));
            } catch (InterruptedException e) {
            }
        }
    }
}
