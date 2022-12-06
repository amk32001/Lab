import java.lang.*;
import java.util.*;
import java.io.*;

public class Clustering
{
	public static void main(String[] args) throws Exception
	{
		List<int[]> data=new ArrayList<>();
		

		BufferedReader bR=new BufferedReader(new FileReader("cluster.csv"));
		String line="";
		bR.readLine();
		while((line=bR.readLine())!=null)
		{
			String[] x=line.split(",");
			data.add(new int[]{Integer.parseInt(x[0]),Integer.parseInt(x[1])});
		}

		int n=data.size();
		double sum_x=0;
		double sum_y=0;
		for(int i=0;i<n;i++)
		{
			sum_x+=data.get(i)[0];
			sum_y+=data.get(i)[1];
		}

		sum_x/=data.size();
		sum_y/=data.size();

		double cx=sum_x;
		double cy=sum_y;

		double[][] matrix=new double[data.size()+1][data.size()+1];
		for(int i=0;i<data.size();i++)
		{
			
			int x=data.get(i)[0];
			int y=data.get(i)[1];
			matrix[0][i+1]=Math.sqrt((x-cx)*(x-cx) + (y-cy)*(y-cy));
		}

		for(int i=0;i<data.size();i++)
		{
			int x=data.get(i)[0];
			int y=data.get(i)[1];

			
			for(int j=i;j<data.size();j++)
			{
				int x1=data.get(j)[0];
				int y1=data.get(j)[1];
				
				matrix[i+1][j+1]=Math.sqrt((x-x1)*(x-x1) + (y-y1)*(y-y1));
				
			}
		}

		PrintWriter out=new PrintWriter(new File("cluster_out.csv"));
		out.printf("%s,"," ");
		for(int i=0;i<=data.size();i++)
		{
			if(i==0)
				out.printf("%s,","C");
			else
				out.printf("%s,","P"+(i));
		}
		
		out.printf("\n");


		for(int i=0;i<=data.size();i++)
		{
			if(i==0)
				out.printf("%s,","C");
			else
				out.printf("%s,","P"+i);
			for(int j=0;j<=data.size();j++)
			{
				out.printf("%.3f,",matrix[i][j]);
			}
			out.printf("\n");
		}
		out.close();
	}
}