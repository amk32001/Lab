import java.lang.*;
import java.util.*;
import java.io.*;
public class Bayes
{

    static double p_yes=1.0;
    static double p_no=1.0;


    public static void main(String[] args) throws Exception 
    {
        BufferedReader br = new BufferedReader(new FileReader("bayes_input.csv"));
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        String line="";

        while((line=br.readLine())!=null)
        {
            String str[] =line.split(",");
            ArrayList<String> temp = new ArrayList<>();
            for (String s : str)
                temp.add(s);
            data.add(temp);
        }
        int yes = 0, no = 0;
        int lastColumn=data.get(0).size()-1;
        for (int i = 1; i < data.size(); i++) {
            if (data.get(i).get(lastColumn).equals("yes"))//considering target value as 5th attribute
                yes++;
            else
                no++;
        }
        double totRecord = data.size()-1;


        PrintWriter out=new PrintWriter(new File("bayes_out.csv"));
        out.printf("%s,%s","yes_probability","no_probability");
        out.printf("\n");


        Scanner input=new Scanner(System.in);
        System.out.println("Enter your query for given dataset below(give space seperated input of each attribute): -");
        String query=input.nextLine();//give space seperated input.
        String[] queryList=query.split(" ");

        for(int i=0;i<data.get(0).size()-1;i++)
        {
            solve(i,data,queryList,yes,no);
        }


        p_yes*=(double)yes/totRecord;
        p_no*=(double)no/totRecord;

        out.printf("%.3f,%.3f",p_yes,p_no);
        
        out.close();
    }

    private static void solve(int i, ArrayList<ArrayList<String>> data,String[] queryList,int yes,int no) {

        Set<String> attribute=new HashSet<>();
        boolean flg=true;
        for(ArrayList<String>x:data)
        {
            if(flg)
            {   
                flg=false;
                continue;
            }
            attribute.add(x.get(i));
        }
       
        Map<String,double[]>total=new HashMap<>();
        for(String x:attribute)
        {
            total.put(x,new double[2]);
        }
        int lastColumn=data.get(0).size()-1;
        flg=true;
        for(ArrayList<String> x:data)
        {
            if(flg)
            {
                flg=false;
                continue;
            }
            if(x.get(lastColumn).equals(("yes")))
             total.get(x.get(i))[0]++;
            else
             total.get(x.get(i))[1]++;

        }
        p_yes*=(double)total.get(queryList[i])[0]/yes;
        p_no*=(double)total.get(queryList[i])[1]/no;

    }


}