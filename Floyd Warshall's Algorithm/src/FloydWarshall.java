import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class FloydWarshall {
	static int n = 5;
	static double[][] a = new double[n][n];
	static int[] vert = new int[n];
	static int[][] paths = new int[n][n];
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		for (int x = 0; x < n; x++)
		{
			for (int y = 0; y < n; y++)
			{
				a[x][y] = 9999;
				paths[x][y] = n;
			}
		}
		for (int x = 0; x < n; x++)
		{
			paths[x][x] = n;
		}
		int thing1 = 99;
		int thing2 = 0;
		double thing3 = 0;
		while (thing1 != thing2)
		{
			thing1 = in.nextInt();
			thing2 = in.nextInt();
			if (thing1 == thing2)
			{
				break;
			}
			thing3 = in.nextDouble();
			a[thing1][thing2] = thing3;
			paths[thing1][thing2] = thing1;
		}
		System.out.println("Input done.");
		
		a = convertMatrix(a);
		// Here lieth the api import code
		for (int x = 0; x < n; x++)
		{
			for (int y = 0; y < n; y++)
			{
				System.out.print(paths[x][y]);
				System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println();
		for (int x = 0; x < n; x++)
		{
			for (int y = 0; y < n; y++)
			{
				if (y == x)
				{
					continue;
				}
				for (int z = 0; z < n; z++)
				{
					if (z == y || z == x || a[x][z] == 9999 || a[y][x] == 9999)
					{
						continue;
					}
					if (a[y][z] < a[y][x] + a[x][z])
					{
						a[y][z] = a[y][x] + a[x][z];
						paths[y][z] = paths[x][z];
						System.out.print(y);
						System.out.print(" ");
						System.out.print(z);
						System.out.print(" ");
						System.out.print(x);
						System.out.println();
					}	
				}
			}
		}
		System.out.println();
		for (int x = 0; x < n; x++)
		{
			for (int y = 0; y < n; y++)
			{
				System.out.print(paths[x][y]);
				System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println();
		for (int x = 0; x < n; x++)
		{
			for (int y = 0; y < n; y++)
			{
				System.out.print(a[x][y]);
				System.out.print(" ");
			}
			System.out.println();
		}
		Scanner sc = new Scanner(System.in);
		int node = sc.nextInt();
		int node1 = sc.nextInt();
		System.out.println(node1);
		tracePath(node, node1);
		System.out.println(a[node][node1]);
	}
	
	public static void tracePath(int start, int stop)
	{
		if (paths[start][stop] == start)
		{
			System.out.println(start);
		}
		else
		{
			System.out.println(paths[start][stop]);
			tracePath(start, paths[start][stop]);
		}
	}
	public static double[][] convertMatrix (double[][] matrix)
	{
		for (int x = 0; x < matrix.length; x++)
		{
			for (int y = 0; y < matrix[x].length; y++)
			{
				matrix[x][y] = Math.log10(1.0/matrix[x][y]);
			}
		}
		return matrix;
	}
}
