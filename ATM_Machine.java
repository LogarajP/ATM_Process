
import java.io.*;
public class ATM_Machine
{
	
	public static void loadATM() throws IOException
	{
		int array[]=getCountarrayay();
	    int k1=array[0];  int k2=array[1];int k3=array[2];
	    FileWriter fw=new FileWriter("Currency 2k 500 100.txt");
		String str1=String.valueOf(k1+20);
		String str2=String.valueOf(k2+100);
		String str3=String.valueOf(k3+100);
		String s=str1+" "+str2+" "+str3;
		int l=s.length();
		for(int i=0;i<l;i++)
		{
			fw.write(s.charAt(i));
		}
		fw.close();
	}
	public static void displayBalance() throws IOException 
	{
		FileReader filereader=new FileReader("Currency 2k 500 100.txt");
		String s="";
		int data;
		while((data=filereader.read())!=-1)
			s+=(char)data;
		String array[]=s.split(" ");
		int k1=Integer.parseInt(array[0]);
		int k2=Integer.parseInt(array[1]);
		int k3=Integer.parseInt(array[2]);
		int t1=k1*2000;
		int t2=k2*500;
		int t3=k3*100;
		System.out.println("Denomination   Number             Value ");
		System.out.format("%8s %8s  %17s","2000", k1, t1);
		System.out.println();
		System.out.format("%8s %8s %17s", "500", k2, t2);
		System.out.println();
		System.out.format("%8s %8s %17s", "100", k3, t3);
		System.out.println();
		System.out.println("-------------------------------------------");
		System.out.println(" Amount in the ATM:"+(t1+t2+t3));
		filereader.close();
	}
	public static int getATMBalance() throws IOException 
	{
		FileReader filereader=new FileReader("Currency 2k 500 100.txt");
		String s="";
		int d;
		while((d=filereader.read())!=-1)
			s+=(char)d;
		String array[]=s.split(" ");
		int k1=Integer.parseInt(array[0]);
		int k2=Integer.parseInt(array[1]);
		int k3=Integer.parseInt(array[2]);
		int t1=k1*2000;int t2=k2*500; int t3=k3*100;
		filereader.close();
		return t1+t2+t3;
	}
	public static int[] getCountarrayay() throws IOException
	{
		FileReader filereader=new FileReader("Currency 2000 500 100.txt");
		String s="";
		int data;
		while((data=filereader.read())!=-1)
			s+=(char)data;
		String array[]=s.split(" ");
		int k1=Integer.parseInt(array[0]);
		int k2=Integer.parseInt(array[1]);
		int k3=Integer.parseInt(array[2]);
		filereader.close();
		return new int[]{k1,k2,k3};
	}
	public static void reduceATM(int amount) throws ClassNotFoundException, IOException
	{
		int array[]=getCountarrayay();
	    int k1=array[0];
	    int k2=array[1];
	    int k3=array[2];       
		int a1=0,a2=0,a3=0;
		if(amount/2000>0)
		{
			a1=amount/2000;
			if(a1>k1)
			   a1=k1;  
			amount=amount-(2000*a1);   
		}
		if(amount/500>0)
		{
			a2=amount/500;
			if(a2>k2) 
				a2=k2;
		    amount=amount-(500*a2);
		}
		if(amount/100>0)
		{
			a3=amount/100;
			if(a3>k3) 
				a3=k3;
			amount=amount-(100*a3);
		}
		FileWriter fw=new FileWriter("Currency 2k 500 100.txt");
		String str1=String.valueOf(k1-a1);
		String str2=String.valueOf(k2-a2);
		String str3=String.valueOf(k3-a3);
		String string=str1+" "+str2+" "+str3;
		int len=string.length();
		for(int i=0;i<len;i++)
		{
			fw.write(string.charAt(i));
		}
		fw.close();
	}
}

