package Parallel.Division;

import java.util.Random;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;

public class RandomDivision extends Thread{

	String result, dataset;
	
    //constructor
    public void randomDivision(/*ontology, numPartitions*/ )
    {
    	result="";
    	dataset="";
    }
    
    public void run(){
    	//拆分数据 dataset.split (" ?");
    	//循环检测关系
    		//调用 subsumption test
    	//生成结果 result =""+AE+";"
    }
    
    //Get all the possible concepts from the possible list
    public void getAllPossibleConcepts()
    {
    	//get the number of the total concepts
    	
    	//create an array with the same size
    	
    	//put all the concepts in the array with an unique index
    	
    }
    
    //Disorder the sequence of the possible concept list
    public void disorderSequence()
    {
    	//use shuffle function to disorder the sequence
    }
    
    //Arrange the divided groups for each thread
    public void arrangeDivision()
    {
    	
    }
    
    
    
    //Divide the concepts into different groups
    //tlen record the number of the concepts for each arrays
    public String[][] RamDivision(String[] cs, int tlen)
	{
		if(cs == null || tlen <0)
		{
			return null;
		}
		String[] c = new String[cs.length];
		System.arraycopy(cs, 0, c, 0, c.length);
		
		//Disorder the array list
		Random ran = new Random();
		for (int i = c.length; i>1; i--)
		{
			int r = ran.nextInt(i);
			//exchange
			String buf = c[r];
			c[r] = c[i-1];
			c[i-1] = buf;
		}
		//the number of arrays 
		int len = cs.length/tlen;
		if(cs.length > len*tlen)
		{
			len++;
		}
		String[][] re = new String[len][];
		
		//Division
		for (int i=0, t=0; i<cs.length; i+=tlen, t++)
		{
			int clen;
			if (i + tlen > cs.length)
			{
				clen = cs.length - i;
			}
			else
			{
				clen = tlen;
			}
			String[] cstr = new String[clen];
			System.arraycopy(c, i, cstr, 0, clen);
			re[t] = cstr;
		}
		return re;
	}

    
    public void setPartition(String s){
    	dataset=s;//改变传入的数据
    }
    public String result(){
    	return result;//结果
    }
}
