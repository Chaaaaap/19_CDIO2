package threads;

import cirbuffer.CirBuf;

public class Producer implements Runnable {
    private int speed;
    private CirBuf cb;

    public Producer(CirBuf buf, int speed) {
        cb = buf;
        this.speed = speed;
        new Thread(this, "Producer").start();
    }
    
    public void run() {
        char setChar = 'a';
        while (true) {
            cb.put(setChar);
            System.out.println("Indsætter: " + (char)setChar + " " + cb);
            try {
            	Thread.sleep(speed);
            	//Thread.sleep((int) (Math.random() * speed));
            } catch (InterruptedException e) {
            }
        }
    }
}
