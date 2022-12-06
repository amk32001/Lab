import java.lang.*;
import java.util.*;
import java.io.*;
class Info_Gain 
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
        int posR = 0, negR = 0;
        int lastColumn=data.get(0).size()-1;
        for (int i = 1; i < data.size(); i++) {
            if (data.get(i).get(lastColumn).equals("yes"))//considering target value as last attribute
                posR++;
            else
                negR++;
        }
        double totRecord = data.size()-1;
        
        double infoGain= -((1.0 * posR / totRecord) * (Math.log10(1.0 * posR / totRecord)) / Math.log10(2)) - ((1.0 * negR / totRecord) * (Math.log10(1.0 * negR / totRecord) / Math.log10(2)));
       

        PrintWriter out=new PrintWriter(new File("info_gain_out.csv"));
        out.printf("%s,%s","Attribute","Info_Gain");
        out.printf("\n");

        for(int i=0;i<data.get(0).size()-1;i++)
        {
            out.printf("%s,%.3f\n",data.get(0).get(i),findAllEntropy(i,infoGain,data));
        }
        out.close();
    }

    private static double findAllEntropy(int i, double infoGain, ArrayList<ArrayList<String>> data) {
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
        double totalE=0.0;
        double totRecord=data.size()-1;
        for(Map.Entry<String,double[]> x:total.entrySet())
        {
            double total1=x.getValue()[0]+x.getValue()[1];
            if(x.getValue()[0]==0||x.getValue()[1]==0)
                 continue;
            double temp= -((1.0 * x.getValue()[0] / total1) * (Math.log10(1.0 * x.getValue()[0] / total1)) / Math.log10(2)) - ((1.0 * x.getValue()[1]/ total1) * (Math.log10(1.0 * x.getValue()[1] / total1) / Math.log10(2)));
             totalE+=(total1/totRecord)*temp;
        }

        double gain=infoGain-totalE;
        
        return gain;
    }


}