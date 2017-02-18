import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class FloydWarshall {
	static Vector<Vector<Double> > a = new Vector<Vector<Double> >();
	static Vector<Integer> vert = new Vector<Integer> ();
	static Vector<Double> temp = new Vector<Double>();
	static Vector<Integer> temp1 = new Vector<Integer>();
	static Vector<Vector<Integer> > paths = new Vector<Vector<Integer> >();
	static int n = 4;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		int thing1 = 99;
		int thing2 = 0;
		double thing3 = 0;
		for (int x = 0; x < n/* mod later */; x++)
		{
			temp.add(0.0);
			temp1.add(0);
		}
		for (int x = 0; x < n; x++)
		{
			a.add(temp);
			paths.add(temp1);
		}
		for (int x = 0; x < n; x++)
		{
			vert.add(x);
			paths.get(x).set(x, 101);
			
		}
		while (thing1 != thing2)
		{
			thing1 = in.nextInt();
			thing2 = in.nextInt();
			if (thing1 == thing2)
			{
				break;
			}
			thing3 = in.nextDouble();
			a.get(thing1).set(thing2, thing3);
		}
		System.out.println("Input done.");
		// Here lieth the api import code
		for (int x = 0; x < n; x++)
		{
			for (int y = 0; y < n; y++)
			{
				if (x != y)
				{
					paths.get(x).set(y, x);
				}
			}
		}
		for (int x = 0; x < n; x++)
		{
			for (int y = 0; y < n; y++)
			{
				for (int z = 0; z < n; z++)
				{
					if (a.get(y).get(z)> a.get(x).get(z)+a.get(y).get(x))
					{
						a.get(y).set(z, a.get(x).get(z)+a.get(y).get(x));
						paths.get(y).set(z, x);
					}	
				}
			}
		}
		Scanner sc = new Scanner(System.in);
		int node = sc.nextInt();
		System.out.println(a.get(0).get(node));
		
	}
}
