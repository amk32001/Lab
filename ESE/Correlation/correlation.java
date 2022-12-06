import java.io.*;
import java.lang.*;
import java.util.*;

public class correlation {
    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new FileReader("corr_input.csv"));
        ArrayList<ArrayList<String>> data=new ArrayList<>();
        int n=br.readLine().trim().split(",").length;
	String line="";
        while((line=br.readLine())!=null){
            String[] str=line.split(",");
            ArrayList<String>temp=new ArrayList<>();
            for(String s:str)
                 temp.add(s);
            data.add(temp);
        }
        for(int i=0;i<data.size()-1;i++){
            for(int j=i+1;j< data.size();j++){
                System.out.println("Correlation between "+data.get(i).get(0)+" and "+data.get(j).get(0)+" is:"+findCorrelation(i,j,data));

            }
        }
    }

    private static double findCorrelation(int i, int j, ArrayList<ArrayList<String>> data) {

        double yesinI=0.0,yesinJ=0.0,yesinCO=0.0;
        for(int k=0;k<data.get(i).size();k++){
            int flg1=0,flg2=0;
            if (data.get(i).get(k).equals("1")) {
                yesinI++;
                flg1=1;
            }
            if(data.get(j).get(k).equals("1")) {
                yesinJ++;
                flg2=1;
            }
            if(flg1==1&&flg2==1)
                yesinCO++;

        }
        
        yesinCO/=data.get(0).size()-1;
        yesinI/=data.get(0).size()-1;
        yesinJ/=data.get(0).size()-1;

        return (1.0 * yesinCO/(yesinI*yesinJ));

    }
}