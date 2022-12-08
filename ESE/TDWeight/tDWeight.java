import java.io.*;
import java.lang.*;
import java.util.*;

public class tDWeight
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader file=new BufferedReader(new FileReader("data (1).csv"));
		List<String[]> data=new ArrayList<>();
		String str="";
		while((str=file.readLine())!=null)
		{
			String[] line=str.split(",");
			data.add(line);
		}
		List<Double> A_Tw=new ArrayList<>();
		List<Double> B_Tw=new ArrayList<>();
		List<Double> A_Dw=new ArrayList<>();
		List<Double> B_Dw=new ArrayList<>();
		int A_sum=0;//col1 sum
		int B_sum=0;//col2 sum
		for(int i=1;i<data.size();i++)
		{
			String[] x=data.get(i);
			int p=Integer.parseInt(x[1]);
			int q=Integer.parseInt(x[2]);
			A_Tw.add((double)p/(p+q));
			B_Tw.add((double)q/(p+q));
			A_sum+=p;
			B_sum+=q;
		}
		for(int i=1;i<data.size();i++)
		{
			String[] x=data.get(i);
			int p=Integer.parseInt(x[1]);
			int q=Integer.parseInt(x[2]);
			A_Dw.add((double)p/(A_sum));
			B_Dw.add((double)q/(B_sum));
		}
		//writing to file
		PrintWriter out=new PrintWriter(new File("tDWeight_out.csv"));
		out.printf("%s,%s,%s,%s,%s,%s","A","B","A_Twt","B_Twt","A_Dwt","B_Dwt");
		out.printf("\n");

		for(int i=1;i<data.size();i++)
		{
			String[] x=data.get(i);
			int p=Integer.parseInt(x[1]);
			int q=Integer.parseInt(x[2]);

			out.printf("%d,%d,%.3f,%.3f,%.3f,%.3f",p,q,A_Tw.get(i-1),B_Tw.get(i-1),A_Dw.get(i-1),B_Dw.get(i-1));
			out.printf("\n");
		}
		out.close();;
	}
}
