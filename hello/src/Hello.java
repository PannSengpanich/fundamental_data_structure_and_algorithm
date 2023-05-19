import java.util.Scanner;

public class Hello {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//--------------------------------------------------
		final double a = 3.14;
		String b = "aaaaa"; //double quote
		String b1 = "bbbbb";
		
		char c = 'a'; //not a string (single quote)
		char c1 ='b';
		
		boolean d = true;
		boolean d1 = false;
		boolean d2 = !d;
		
		int[] e = new int[10];
		
		int i = 3;
		int j = 31;
		int k = 1;
		
		System.out.println("1"+5);
		System.out.println(b+","+b1);
		System.out.println((int)c);
		
		System.out.println(d||d1);
		System.out.println(d&&d1);
		System.out.println(d2);
		System.out.println(e);
		
		if (i==j) { // i<j i>j 
			System.out.println("equal");
		}else {
			System.out.println("not equal");
		}
		//System.out.println((String)c + (String)c1);
		
		System.out.println(i+i+b+b1+" i ");
		
		//----------------------------------------------------
		
		Scanner s1 = new Scanner(System.in);
		System.out.println("Type something: ");
		double result = s1.nextDouble();
		System.out.println("Result is "+result);
	}

}
