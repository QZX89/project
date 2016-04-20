package Parallel.Division;

import static org.semanticweb.owlapi.search.Searcher.annotations;
import static org.semanticweb.owlapi.vocab.OWLRDFVocabulary.RDFS_LABEL;

import java.util.Random;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;

import java.io.File;
import java.lang.Object;

import org.semanticweb.HermiT.Reasoner.ReasonerFactory;
import org.semanticweb.owlapi.AbstractCompositeOntologyChange;
import org.semanticweb.owlapi.SplitSubClassAxioms;


public class MasterPartition extends Thread{
	private static final String Null = null;

	
    //OWLClass DConcept,AConcept;
    String result, dataset;
    OWLReasonerFactory reasonerFactory = null;
    OWLOntology myOntology;
    OWLClass DConcept,AConcept;
	

	//constructor
	public MasterPartition()
	{
		//constructor
	}
	
    //The procedure for the code to run in parallel
	public void run(){
		//Read an ontology to get the Random Partition
		
    	File file = new File("/Users/Zixi/Desktop/JFACT/example/ore_ont_960.owl");
        this.reasonerFactory = new ReasonerFactory();
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        //load the ontology
        try {
			this.myOntology = manager.loadOntologyFromOntologyDocument(file);
		} catch (OWLOntologyCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        //Extract other Classes to check their satisfiability
        OWLDataFactory dataFactory=manager.getOWLDataFactory();
        //OWLDataFactory dataFactory= manager.
        //create instance of a concept in ontology
        IRI DInstance=IRI.create("http://www.semanticweb.org/zixi/ontologies/2015/7/untitled-ontology-309#D");
        IRI AInstance=IRI.create("http://www.semanticweb.org/zixi/ontologies/2015/7/untitled-ontology-309#A");
        
        //get the class
        DConcept=dataFactory.getOWLClass(DInstance);
        AConcept=dataFactory.getOWLClass(AInstance);
        
		//Transform the input ontology
		//SplitSubClassAxioms(myOntology, dataFactory);
		
		//execute twice random division
		
		
		//execute sub division
		//It is a cycle here. When the possible list is empty, the cycle will stop. 
		
		
	}
	
	//Transform the string into a two-dimensional array
	public void transformStringIntoArray(OWLOntology ontology,OWLDataFactory owlDataFactory)
	{
		
	}
	
	
	//execute twice random division
	public void ArrangeTwiceRandomDivision(OWLOntology ontology,OWLDataFactory owlDataFactory)
	{
		//随机分组执行2次 数据
		//New N-threads Partitions
		RandomDivision R1 = new RandomDivision();
		RandomDivision R2 = new RandomDivision();
				
		//循环 分组次数,此处两次随机分组之后执行sub分组同时执行添加表单List
		//两次随机分组，在第一次随机分组结束后添加K&I List{
		R1.setPartition("AS");//n次
		R1.start();
		String s;
		//if(!R1.isAlive()) {s=R1.result();R1.setPartition(s2);R1.start()};
				
				
		//}
		//
	}
	
    //非随机分组
	public void OrderedDivision(String[] cs, int tlen)
	{
		
	}           
	
	
	//
	AddtoKnownList()
	{
		
	}
	
	//
	AddtoPossibleList()
	{
		
	}
	
}
