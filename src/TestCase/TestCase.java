package TestCase;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import org.junit.Before;
import org.junit.Test;
import org.semanticweb.HermiT.Configuration;
import org.semanticweb.HermiT.Reasoner.ReasonerFactory;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDisjointClassesAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.ConsoleProgressMonitor;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerConfiguration;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.SimpleConfiguration;
import org.semanticweb.owlapi.util.InferredAxiomGenerator;
import org.semanticweb.owlapi.util.InferredClassAssertionAxiomGenerator;
import org.semanticweb.owlapi.util.InferredDisjointClassesAxiomGenerator;
import org.semanticweb.owlapi.util.InferredOntologyGenerator;
import org.semanticweb.owlapi.util.InferredSubClassAxiomGenerator;

import uk.ac.manchester.cs.owl.owlapi.mansyntaxrenderer.ManchesterOWLSyntaxOWLObjectRendererImpl;

public class TestCase {

	OWLReasonerFactory reasonerFactory = null;
    OWLOntology myOntology;
    OWLClass DConcept,AConcept;
    @Before
    public void setUp() throws OWLOntologyCreationException {
    	File file = new File("/Users/Zixi/Desktop/JFACT/example/ore_ont_960.owl");
        this.reasonerFactory = new ReasonerFactory();
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        //load the ontology
        this.myOntology = manager.loadOntologyFromOntologyDocument(file); 
        //Extract other Classes to check their satisfiability
        OWLDataFactory dataFactory=manager.getOWLDataFactory();
        //OWLDataFactory dataFactory= manager.
        //create instance of a concept in ontology
        IRI DInstance=IRI.create("http://www.semanticweb.org/zixi/ontologies/2015/7/untitled-ontology-309#D");
        IRI AInstance=IRI.create("http://www.semanticweb.org/zixi/ontologies/2015/7/untitled-ontology-309#A");
        
        //get the class
        DConcept=dataFactory.getOWLClass(DInstance);
        AConcept=dataFactory.getOWLClass(AInstance);
        
    }
    //@Ignore
    @Test
    public void testUnsatisfiableClasses() throws Exception {
        // a config object. Things like monitor, timeout, etc, go here
        OWLReasonerConfiguration config = new SimpleConfiguration(50000);
        // Create a reasoner that will reason over our ontology and its imports
        // closure. Pass in the configuration.
        OWLReasoner reasoner = this.reasonerFactory.createReasoner(this.myOntology,
                config);
        
        // Ask the reasoner to classify the ontology
        reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY);
        // We can determine if the ontology is actually consistent (in this
        // case, it should be).
        
        assertTrue(reasoner.isConsistent());
        //check the satisfiability of D
        final long startTime = System.nanoTime();    
        System.out.println("Is D satisfiable? "+reasoner.isSatisfiable(DConcept));
        
        final long elapsedTimeNano = System.nanoTime() - startTime;
        System.out.println("elapsedTimeNano: "+elapsedTimeNano);

    }
}
