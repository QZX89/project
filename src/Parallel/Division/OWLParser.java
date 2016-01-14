package Parallel.Division;

import java.util.ArrayList;
import java.util.List;

import static org.semanticweb.owlapi.search.Searcher.annotations;
import static org.semanticweb.owlapi.vocab.OWLRDFVocabulary.RDFS_LABEL;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;


public class OWLParser {
	
	private OWLDataFactory reasonerFactory = null;
    private OWLOntology ontology;
    
	//Constructor
	public OWLParser (OWLOntology ontology,OWLDataFactory owlDataFactory )
	{
		this.ontology = ontology;
		this.reasonerFactory = owlDataFactory;
	}

	public void parseOntology() throws OWLOntologyCreationException
	{
		for(OWLClass cls : ontology.getClassesInSignature())
		{
			String id = cls.getIRI().toString();
			String label = get(cls, RDFS_LABEL.toString()).get(0);
			System.out.println(label + "[" + id + "]");
		}
	}
	
	private List<String> get(OWLClass clazz, String property)
	{
		List<String> ret = new ArrayList<String>();
		
		final OWLAnnotationProperty owlProperty = reasonerFactory.getOWLAnnotationProperty(IRI.create(property));
		for (OWLOntology o : ontology.getImportsClosure())
		{
			for(OWLAnnotation annotation : annotations(o.getAnnotationAssertionAxioms(clazz.getIRI()), owlProperty))
			{
				if (annotation.getValue() instanceof OWLLiteral)
				{
					OWLLiteral val = (OWLLiteral) annotation.getValue();
					ret.add(val.getLiteral());
				}
			}
		}				   
		return ret;
	}
}
