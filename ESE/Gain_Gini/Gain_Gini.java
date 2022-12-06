import java.lang.*;
import java.util.*;
import java.io.*;
public class Gain_Gini 
{
    public static void main(String[] args) throws Exception 
    {
        BufferedReader br = new BufferedReader(new FileReader("info_gain.csv"));
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
        int play = 0, noPlay = 0;
        int lastColumn=data.get(0).size()-1;
        for (int i = 1; i < data.size(); i++) {
            if (data.get(i).get(lastColumn).equals("yes"))//considering target value as 5th attribute
                play++;
            else
                noPlay++;
        }
        double totRecord = data.size()-1;
        double infoGain= -((1.0 * play / totRecord) * (Math.log(1.0 * play / totRecord)) / Math.log(2)) - ((1.0 * noPlay / totRecord) * (Math.log(1.0 * noPlay / totRecord) / Math.log(2)));

        PrintWriter out=new PrintWriter(new File("gain_gini_out.csv"));
        out.printf("%s,%s,%s","Attribute","Info_Gain","Gini_Index");
        out.printf("\n");

        for(int i=0;i<data.get(0).size()-1;i++)
        {
            double[] ans=findGainGini(i,infoGain,data);
            out.printf("%s,%.3f,%.3f\n",data.get(0).get(i),ans[0],ans[1]);
        }
        out.close();
    }

    private static double[] findGainGini(int i, double infoGain, ArrayList<ArrayList<String>> data) {
        Set<String> attribute=new HashSet<>();
        for(ArrayList<String>x:data)
           attribute.add(x.get(i));
        Map<String,double[]>total=new HashMap<>();
        for(String x:attribute)
        {
            total.put(x,new double[2]);
        }
         int lastColumn=data.get(0).size()-1;
        for(ArrayList<String> x:data)
        {
            if(x.get(lastColumn).equals(("yes")))
             total.get(x.get(i))[0]++;
            else
             total.get(x.get(i))[1]++;

        }
        double totalE=0.0;
          double totRecord=data.size()-1;
        for(Map.Entry<String,double[]> x:total.entrySet())
        {
            double total1=x.getValue()[0]+x.getValue()[1];
            if(x.getValue()[0]==0||x.getValue()[1]==0)
                 continue;
            double temp= -((1.0 * x.getValue()[0] / total1) * (Math.log(1.0 * x.getValue()[0] / total1)) / Math.log(2)) - ((1.0 * x.getValue()[1]/ total1) * (Math.log(1.0 * x.getValue()[1] / total1) / Math.log(2)));
             totalE+=(total1/ totRecord)*temp;
        }


        double totalG=0;

        for(Map.Entry<String,double[]> p:total.entrySet())
        {
            double total2=p.getValue()[0] + p.getValue()[1];
            double x=(1 - Math.pow(p.getValue()[0]/total2,2) - Math.pow(p.getValue()[1]/total2,2));
            totalG+=(total2/totRecord)*(x);
        }


        double[] result=new double[2];
        double gain=infoGain-totalE;

        result[0]=gain;
        result[1]=totalG;
        return result;
    }


}