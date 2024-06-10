import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Akif_Selim_ARSLAN_2020510009 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int[] demands = readOp("yearly_player_demand.txt");
		int[] salary = readOp("players_salary.txt");
		int k = 1;
		
		long startTime = System.currentTimeMillis();
		System.out.println("Minimum cost is: " + gr2(16,5,10,demands,salary,k)); // n, p, c
		long finish = System.currentTimeMillis();
		long estimatedTime = (finish - startTime) ;
		System.out.println("Estimated time is: " + estimatedTime);
	}
	
	public static int gr2(int n, int p, int c,int[] demands, int[] salary, int k) //n: year, p: player, c: coach
	{
		int[] yearlydemand = new int[n + 1]; 
		for (int i = 0; i < yearlydemand.length; i++) { //creating yearly demands array
			yearlydemand[i] = demands[i]; 
		}
		int[][] matrix = new int[salary.length][yearlydemand.length]; // creating matrix 
		for (int i = 0; i < salary.length; i++)
            for (int j = 0; j < yearlydemand.length; j++)
                matrix[i][j] = -1;
		int unrented = 0;
		return gr(n,p,c,yearlydemand,salary,k, matrix, unrented); 
	}
	
	public static int gr(int n, int p, int c,int[] yearlydemand, int[] salary, int k, int[][] matrix, int unrented) {
		
		if (yearlydemand[k] < p && unrented == 0) { // if demand < p and there is no unrented player from previous year.
			if (yearlydemand[k-1] != -1) // if this possibilty comes in first year because empty matrix elements are -1 
			{
				unrented = p - yearlydemand[k]; 
				while (unrented != -1) {
					matrix[unrented][k] = matrix[0][k-1] + salary[unrented];
					unrented--;
				}
				unrented = p - yearlydemand[k];
			}
			else { // if this possibilty comes in first year
				unrented = p - yearlydemand[k];
				while (unrented != -1) {
					matrix[unrented][k] = matrix[0][k-1] + salary[unrented] + 1;
					unrented--;
				}
				unrented = p - yearlydemand[k];
			}
		}
		else if (yearlydemand[k] < p && unrented != 0) { // if demand < p and there is unrented players from previous year.
			unrented = p - yearlydemand[k];
			while (unrented != -1) {
				matrix[unrented][k] = matrix[0][k-1] + salary[unrented];
				unrented--;
			}
			unrented = p - yearlydemand[k];
		}
		else if (yearlydemand[k] == p && unrented == 0) { // if demand = p and there is no unrented player from previous years.
			
			if (matrix[0][k-1] == -1) {
				matrix[0][k] = 0;
			}
			else {
				matrix[0][k] = matrix[0][k-1];
			}
		}
		else if (yearlydemand[k] == p && unrented != 0)// if demand = p and there is unrented player from previous years. 
		{
			matrix[0][k] = matrix[0][k-1];
			unrented = 0;
		}
		else if (yearlydemand[k] > p && unrented == 0) {
			if (matrix[0][k-1] == -1) 
			{
				matrix[0][k] = ((yearlydemand[k] - p) * c) + matrix[0][k-1] + 1;
			}
			else {
				matrix[0][k] = ((yearlydemand[k] - p) * c) + matrix[0][k-1];
			}
		}
		else if (yearlydemand[k] > p && unrented != 0) {
			
			int demand = yearlydemand[k] - p; //kiralanması ya da koç tutulması gereken oyuncu sayısı
			int newcolumn = 0;
			if (unrented <= demand) {
				newcolumn = yearlydemand[k] - unrented -p;
				matrix[0][k] = matrix[unrented][k-1] + (newcolumn * c); //demand kadarını önceki yıldan al 
				unrented = 0;
			}
			else {
				unrented = demand;
				matrix[0][k] = matrix[unrented][k-1];
				unrented = 0;
			}
			
			
		}
		int cost = 0;
		if (k != n) {
			return gr(n,p,c,yearlydemand,salary,k+1,matrix, unrented); //recursive call for next years.
		}
		else 
		{
			for (int i = 0; i < matrix.length; i++) { // printing the matrix.
			    for (int j = 0; j < matrix[i].length; j++) {
			        if (matrix[i][j] == -1) {
			            matrix[i][j] = 0;
			        }
			    }
			}
			for (int i = 0; i < yearlydemand.length; i++) {// printing the matrix.
				System.out.print(yearlydemand[i] + "	");
			}
			System.out.println();
			for (int i = 0; i < matrix.length; i++) {
				matrix[i][0] = salary[i];
			}
			for (int i = 0; i < matrix.length; i++) {
			    boolean hasNonZero = false;
			    for (int j = 1; j < matrix[i].length; j++) {// printing the matrix.
			        if (matrix[i][j] != 0) {
			            hasNonZero = true;
			            break;
			        }
			    }
			    if (hasNonZero) {
			        for (int j = 0; j < matrix[i].length; j++) {// printing the matrix.
			            System.out.print(matrix[i][j] + "	");
			        }
			        System.out.println();
			    }
			}
			cost = matrix[0][matrix[0].length - 1]; // minimum cost 
			return cost;
		}
	}
	public static int[] readOp(String filename) throws IOException { // file operations
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        ArrayList<Integer> datas = new ArrayList<Integer>();
        String line = bufferedReader.readLine();
        while ((line = bufferedReader.readLine()) != null) {
            String[] parts = line.split("\t");
            int data = Integer.parseInt(parts[1]);
            datas.add(data);
        }
        bufferedReader.close();
        int[] Array = new int[datas.size()+1];
        Array[0] = 0;
        for (int i = 1; i < datas.size()+1; i++) {
            Array[i] = datas.get(i-1);
        }
        return Array;
    }
	
}
