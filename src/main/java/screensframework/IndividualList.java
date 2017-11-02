/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screensframework;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.Node;
import static screensframework.SuperClass.df;
import static screensframework.SuperClass.o;

/**
 *
 * @author fathi
 */
public class IndividualList {

    static Reasoner reasoner;
    
    static List<String> individual=new ArrayList<>();

    IndividualList() {

    }

    public static List<String> getSameAsIndividuals(String syn) {
        try {
            
            OWLNamedIndividual i = df.getOWLNamedIndividual(IRI.create("http://www.semanticweb.org/fathi/AidFatwa.owl#" + syn));
            reasoner = new Reasoner(o);
            Node<OWLNamedIndividual> s = reasoner.getSameIndividuals(i);
            Set<OWLNamedIndividual> aa = s.getEntities();
            for (OWLNamedIndividual ss : aa) {
                System.out.println("ssss" + ss.getIRI().getFragment());
               
                    individual.add(ss.getIRI().getFragment());
                
            }

        } catch (Exception e) {
            {
                System.err.println("aaa"+e);
            }

        }
        return individual;
    }
}
