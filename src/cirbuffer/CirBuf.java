package cirbuffer;

public class CirBuf {
	private static final int SIZE = 16;
	private int tail1; 		// last char in buffer, det som tages ud v�ste gang
	private int head1; 		// first empty slot in buffer
	private int length; 	// number of characters in buffer
	private int size; 		// capacity of buffer
	private char[] cb; 		// character buffer

	public CirBuf() {
		this(SIZE);
	}

	public CirBuf(int s) { // no-arg constructor
		tail1 = head1 = length = 0;
		size = s;
		cb = new char[s]; // char array buffer
		for (int i = 0; i < cb.length; i++)
			cb[i] = 't'; // fyldes op med 't' af hensyn til test

	}

	public boolean isEmpty() {
		return (length == 0);
	}

	public boolean isFull() {
		return (length == size);
	}

	public synchronized void put(char c) { // vent til det er muligt og put s� c
		// i ringbufferen
		while (isFull()) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		cb[head1] = c;
		head1++;
		length++;
		head1 = mod(head1);
		notify();
	}

	public synchronized char get() {
		while (isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}

		char temp = cb[tail1];
		cb[tail1] = 'T';
		tail1++;
		length--;
		tail1 = mod(tail1);
		notify();
		return temp;

	}

	private int mod(int x) {
		return (x >= size ? x - size : x);
	}

	public String toString() {

		String temp = "(Bufferlength = " + length + ")";
		temp = temp + " Tail = " + tail1 + " Head = " + head1;
		temp = temp + " IsEmpty = " + isEmpty() + " IsFull = " + isFull();

		temp = temp + (char) (13) + " " + (char) (10);
		for (int i = 0; i < cb.length; i++) 
			temp = temp + " " + i;
		temp = temp + (char)(13) + " " + (char)(10);
		String temp2 = " ";
		for (int i = 0; i < cb.length; i++) {
			temp2 = temp2 + cb[i] + " ";
			if (i > 9)
				temp2 = temp2 + " ";
		}
		temp2 = temp2 + (char) (13) + " " + (char) (10);

		return temp + temp2;

	}

}

