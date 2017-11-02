/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screensframework;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.RDFXMLOntologyFormat;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLSameIndividualAxiom;
import org.semanticweb.owlapi.reasoner.Node;
import org.semanticweb.owlapi.reasoner.NodeSet;
import static screensframework.OntoConnect.manager;
/**
 *
 * @author Baali Fathi
 */
public class SuperClass {

    static Reasoner reasoner;
    static OWLOntology o;
    static OWLOntologyManager manager;
    static File file;
    static OWLDataFactory df;
    static List<String> list = new ArrayList<>();
    public static void addAction(String action, String actionsyn) {
        try {
            /*OntoConnect.setConnection();
            manager = OntoConnect.manager;
            df = OntoConnect.df;
            o = OntoConnect.ontology;
            
            file = OntoConnect.file;*/
            
            IRI iri = IRI.create("http://www.semanticweb.org/fathi/AidFatwa.owl#");
            OWLClass Contracts = df.getOWLClass(IRI.create(iri + "فعل"));
            OWLIndividual Action = df.getOWLNamedIndividual(IRI.create(iri + action));
            OWLIndividual synonyme = df.getOWLNamedIndividual(IRI.create(iri + action));
            OWLClassAssertionAxiom contractsAssertion = df.getOWLClassAssertionAxiom(Contracts, Action);
            if (!actionsyn.equals("")) {
                OWLSameIndividualAxiom AssertionAxiom = df.getOWLSameIndividualAxiom(Action, synonyme);
                manager.addAxiom(o, AssertionAxiom);
            }
            
            manager.addAxiom(o, contractsAssertion);
            
            file = file.getAbsoluteFile();
            RDFXMLOntologyFormat rdfxmlFormat = new RDFXMLOntologyFormat();
            manager.saveOntology(o, rdfxmlFormat, IRI.create(file));
        } catch (Exception ex) {
            Logger.getLogger(SuperClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void addSubClass(String subclass) {
        try {

            IRI iri = IRI.create("http://www.semanticweb.org/fathi/AidFatwa.owl#");
            OWLClass clsA = df.getOWLClass(IRI.create(iri + subclass));
            OWLClass clsB = df.getOWLClass(IRI.create(iri + "منفعة"));
            OWLAxiom axiom = df.getOWLSubClassOfAxiom(clsA, clsB);
            AddAxiom addAxiom = new AddAxiom(o, axiom);
            manager.applyChange(addAxiom);

            file = file.getAbsoluteFile();
            RDFXMLOntologyFormat rdfxmlFormat = new RDFXMLOntologyFormat();
            manager.saveOntology(o, rdfxmlFormat, IRI.create(file));

        } catch (Exception ex) {
            Logger.getLogger(SuperClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void createIndividual(String clas, String benifit) {
        try {
            /*setConnection();
            manager = OntoConnect.manager;
            df = OntoConnect.df;
            o = OntoConnect.ontology;

            file = OntoConnect.file;*/

            IRI iri = IRI.create("http://www.semanticweb.org/fathi/AidFatwa.owl#");
            OWLClass Contracts = df.getOWLClass(IRI.create(iri + clas));
            OWLIndividual Sale = df.getOWLNamedIndividual(IRI.create(iri + benifit));
            OWLClassAssertionAxiom contractsAssertion = df.getOWLClassAssertionAxiom(Contracts, Sale);
            manager.addAxiom(o, contractsAssertion);

            file = file.getAbsoluteFile();
            RDFXMLOntologyFormat rdfxmlFormat = new RDFXMLOntologyFormat();
            manager.saveOntology(o, rdfxmlFormat, IRI.create(file));
        } catch (Exception ex) {
            Logger.getLogger(SuperClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<String> getAllCalss() {
        try {
            list.clear();
            
            reasoner = new Reasoner(o);
            IRI iri = IRI.create("http://www.semanticweb.org/fathi/AidFatwa.owl#" + "منفعة");
            OWLClass Contracts = df.getOWLClass(iri);
            //iri = IRI.create("http://www.semanticweb.org/fathi/AidFatwa.owl#", syn);
            NodeSet<OWLClass> set = reasoner.getSubClasses(Contracts, true);
            for (Node<OWLClass> v : set) {
                for (OWLClass vv : v) {
                    System.out.println("" + vv.getIRI().getFragment());
                    list.add(vv.getIRI().getFragment().toString());
                }
            }
            
        } catch (Exception ex) {
            Logger.getLogger(SuperClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public static List<String> getListActionFromOntology() {
        List<String> list = new ArrayList<>();
        try {

            list.clear();
            // setConnection(); i must put this function in luncher class 
            reasoner = new Reasoner(o);

            IRI iri = IRI.create("http://www.semanticweb.org/fathi/AidFatwa.owl#" + "فعل");
            OWLClass Action = df.getOWLClass(iri);
            NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(Action, true);
            for (Node<OWLNamedIndividual> cc : instances) {

                for (OWLNamedIndividual ccc : cc) {

                    list.add(ccc.getIRI().getFragment().toString());

                }
            }
        } catch (Exception e) {
            System.out.print(e);
        }

        return list;
    }

    public static String getSubClass(String syn) throws OWLOntologyCreationException {
        
        String subclass = null;
        
        try {
            
            if (!syn.equals("")) {
                
                reasoner = new Reasoner(o);
                IRI iri = IRI.create("http://www.semanticweb.org/fathi/AidFatwa.owl#" + "منفعة");
                OWLClass Contracts = df.getOWLClass(iri);
                iri = IRI.create("http://www.semanticweb.org/fathi/AidFatwa.owl#", syn);
                OWLNamedIndividual myindividual = df.getOWLNamedIndividual(iri);
                
                NodeSet<OWLClass> d = reasoner.getTypes(myindividual, true);
                
                for (Node<OWLClass> df : d) {
                    
                    for (OWLClass cf : df) {
                        subclass = cf.getIRI().getFragment();
                    }
                }
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return subclass;
    }
    
    public static boolean isIndividual(String syn) throws OWLOntologyCreationException {

        String subclass = null;
        boolean test = false;
        try {
            if (!syn.equals("")) {

                reasoner = new Reasoner(o);
                IRI iri = IRI.create("http://www.semanticweb.org/fathi/AidFatwa.owl#" + "منفعة");
                OWLClass Contracts = df.getOWLClass(iri);
                //iri = IRI.create("http://www.semanticweb.org/fathi/AidFatwa.owl#", syn);
                NodeSet<OWLClass> set = reasoner.getSubClasses(Contracts, true);
                for (Node<OWLClass> v : set) {
                    for (OWLClass vv : v) {

                        NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(vv, true);

                        for (Node<OWLNamedIndividual> cc : instances) {

                            for (OWLNamedIndividual ccc : cc) {

                                if (ccc.getIRI().getFragment().equals(syn)) {
                                    test = true;
                                    subclass = vv.getIRI().getFragment();

                                    break;
                                }
                            }
                            if (test) {
                                break;
                            }
                        }

                        if (test) {
                            break;
                        }
                    }
                    if (test) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(e);

        }
        return test;
    }

   

    public static void setConnection(String in) throws OWLOntologyCreationException {

      
        //file = new File("/home/fathi/ISFO.owl");
        file = new File(in);
        manager = OWLManager.createOWLOntologyManager();
        df = OWLManager.getOWLDataFactory();
        o = manager.loadOntologyFromOntologyDocument(file);

    }
}
