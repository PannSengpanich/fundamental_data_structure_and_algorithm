import java.util.Random;

public class HeapTest {

	public static void main(String[] args) throws Exception {
		Heap h = new Heap();
		Random rnd = new Random();
		// 100
		long starttime1 = System.nanoTime();

		for (int n = 0; n < 100; n++) {
			int rnd1 = rnd.nextInt(1000000);
			h.add(rnd1);
		}
		System.out.println("n=100 add:  " + (System.nanoTime() - starttime1));
		for (int n = 0; n < 100; n++) {
			h.pop();
		}
		System.out.println("n=100 pop:  " + (System.nanoTime() - starttime1));

		// 10000
		long starttime2 = System.nanoTime();
		for (int n = 0; n < 10000; n++) {
			int rnd2 = rnd.nextInt(1000000);
			h.add(rnd2);
		}
		System.out.println("n=10000 add:  " + (System.nanoTime() - starttime1));
		for (int n = 0; n < 10000; n++) {
			h.pop();
		}
		System.out.println("n=10000 pop:  " + (System.nanoTime() - starttime1));

		// 1000000
		long starttime3 = System.nanoTime();
		for (int n = 0; n < 1000000; n++) {
			int rnd3 = rnd.nextInt(1000000);
			h.add(rnd3);
		}
		System.out.println("n=1000000 add:  " + (System.nanoTime() - starttime1));
		for (int n = 0; n < 1000000; n++) {
			h.pop();
		}
		System.out.println("n=1000000 pop:  " + (System.nanoTime() - starttime1));

	}
}
