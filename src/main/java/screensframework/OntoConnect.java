/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screensframework;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import static screensframework.SuperClass.file;

/**
 *
 * @author fathi
 */
public class OntoConnect {

    static OWLOntology ontology;
    static OWLOntologyManager manager;
    static File file;
    static OWLDataFactory df;

    public OntoConnect() {

    }

    /*public static void setConnection() throws OWLOntologyCreationException {
        manager = OWLManager.createOWLOntologyManager();
        df = OWLManager.getOWLDataFactory();
        //file = new File("/home/fathi/ISFO.owl");
        String dire = System.getProperty("user.dir");
        file = new File(dire+"/AidFatwa2.owl");
        ontology = manager.loadOntologyFromOntologyDocument(file);

    */

    public static void setConnection(String in) {
        try {
            manager = OWLManager.createOWLOntologyManager();
            df = OWLManager.getOWLDataFactory();
            file = new File(in);
           
            
            ontology = manager.loadOntologyFromOntologyDocument(file);
            
            if (ontology == null) {
                System.out.printf("zzzzz");
            }

        } catch (Exception e) {
            System.err.println("bbba" + e);
        }

    }
 public static void refresh(){
        try {
            String dire = System.getProperty("user.dir");
            file=new File(dire+"/islamic_finance.owl");
            manager = OWLManager.createOWLOntologyManager();
            df = OWLManager.getOWLDataFactory();
            ontology = manager.loadOntologyFromOntologyDocument(file);
        } catch (OWLOntologyCreationException ex) {
            Logger.getLogger(SuperClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
