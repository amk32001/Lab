import java.lang.*;
import java.util.*;
import java.io.*;
class Box_Plot
{

   public static double findMedian(int arr[],int start,int end)
   {
        int n=end-start+1;
        if(n%2==0)
        {
            return (arr[start+n/2-1]+arr[start+n/2])/2;
        }
        else
        {
            return arr[start+n/2];
        }

   }
   public static void main(String[] args) throws Exception
   {
    BufferedReader   br = new BufferedReader(new FileReader("box_plot.csv"));

    
    String line="";

        
    
    int i=0;

    while((line=br.readLine())!=null)
        {
            i++;
        }
        
        int[] arr=new int[i-1];
        i=-1;


        BufferedReader   nr = new BufferedReader(new FileReader("box_plot.csv"));
        while((line=nr.readLine())!=null)
        {
            if(i==-1)
            {
                i++;continue;
            }
            int x=Integer.parseInt(line); //prints out name
            arr[i++]=x;
        }



    
    
    Arrays.sort(arr);
    
    int n=arr.length;
    double median=findMedian(arr,0,n-1);
    double q1=findMedian(arr,0,n/2-1);
    double q3=0;
    if(n%2==0)
    {
        q3=findMedian(arr,n/2,n-1);
    }
    else
    {
        q3=findMedian(arr,n/2+1,n-1);
    }

    PrintWriter out=new PrintWriter(new File("box_plot_out.csv"));
    out.printf("%s,%s","Attribute","Value");
    out.printf("\n");

    out.printf("%s,%d\n","Max",arr[n-1]);
    out.printf("%s,%d\n","Min",arr[0]);
    out.printf("%s,%.3f\n","Median",median);
    out.printf("%s,%.3f\n","Q1",q1);
    out.printf("%s,%.3f\n","Q3",q3);


    out.close();
   }
}