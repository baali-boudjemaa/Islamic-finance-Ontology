/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screensframework;

import java.awt.MouseInfo;
import java.io.File;
//import static com.hp.hpl.jena.ontology.OntDocumentManager.PREFIX;
//import com.hp.hpl.jena.query.QuerySolution;
//import com.hp.hpl.jena.query.ResultSet;
//import com.hp.hpl.jena.rdf.model.Resource;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;
import login.login;
import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.Node;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;

/**
 * FXML Controller class
 *
 * @author fathi
 */
public class Screen5Controller implements Initializable {
//    ResultSet rs;

    @FXML
    ComboBox<String> combolist;

    @FXML
    ObservableList<String> list = FXCollections.observableArrayList();
    @FXML
    ObservableList<String> listConditionContent = FXCollections.observableArrayList();
    @FXML
    TextArea definition;
    @FXML
    TextField hokme;
    @FXML
    TextArea quran;
    @FXML
    TextArea suna;
    @FXML
    TextArea ijtihad;
    @FXML
    ListView conditions;
    @FXML
    Button menu;
    @FXML
    ContextMenu contextmenu;
    @FXML
    MenuItem adds;
    @FXML
    MenuItem updates;
    @FXML
    MenuItem deletes;
    public static List<IRI> listContracts = new ArrayList();
    public static List<IRI> listConditionsIndividulas = new ArrayList();
    public static List<IRI> listConditions = new ArrayList();

    OWLOntology ontology;
    static Reasoner reasoner;
    static OWLDataFactory df;
    OWLNamedIndividual mick;
    OWLDataProperty hasname, hasdefinition;
    File file;
    static String prefix = "http://www.w3.org/IslamicFinanceOntology.owl#";
    /**
     * @param args the command line arguments
     */
    OWLOntologyManager manager;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //System.setProperty("prism.text", "t2k");
        boolean admin = false;
        admin = login.isLogin();
        if (!admin) {
            adds.setDisable(true);
            updates.setDisable(true);
            deletes.setDisable(true);
            System.out.println("////////////");
        }
        conditions.setItems(listConditionContent);
        combolist.setItems(list);
        loadOntology();
        getClassInstance();
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {

                try {

                    //getClassInstance2();
                } catch (Exception ex) {
                    System.out.println("cccc" + ex);
                }

            }
        });
        t.start();

        //getCondtionsIdividulList();
        AutoCompleteComboBoxListener< Object> autoCompleteComboBoxListener = new AutoCompleteComboBoxListener<>(combolist);
    }

    public void loadOntology() {

        //"/home/fathi/Downloads/ExplorerOWLExample/projetOWL
        ///"/home/fathi/islamic_finance.owl"
        //"/home/fathi/Downloads/Ontology/islamic_finance.owl"
        //file = new File("/home/fathi/ISFO.owl");
        try {
            //file = new File("/home/fathi/onto/islamic_finance.owl"); 
            manager = OntoConnect.manager;
            ontology = OntoConnect.ontology;
            //file = new File("/home/fathi/islamic_finance.owl");
            if (ontology == null) {
                System.out.println("ontology nulll");
            }
            df = manager.getOWLDataFactory();
            reasoner = new Reasoner(ontology);
        } catch (Exception ex) {
            System.out.println("123" + ex);
        }

    }

    //go boack to home screen
    public void changeScene(ActionEvent actionEvent) {
        try {
            //get the button object on which user clicks and change scene acoording to that

            ScreensFramework.changeScene("Screen1.fxml");

        } catch (IOException ex) {
        }
    }

    @FXML
    public void gotToSceen2() {
        try {
            //get the button object on which user clicks and change scene acoording to that

            ScreensFramework.changeScene("Screen.fxml");

        } catch (IOException ex) {
        }
    }

    @FXML
    public void gotToSceen3() {
        try {
            //get the button object on which user clicks and change scene acoording to that

            ScreensFramework.changeScene("Screen3.fxml");

        } catch (IOException ex) {
        }
    }

    @FXML
    public void gotToSceen4() {
        try {
            //get the button object on which us
            ScreensFramework.changeScene("fScreen4.fxml");

        } catch (IOException ex) {
        }
    }

    @FXML
    public void gotToSceen5() {
        try {
            //get the button object on which user clicks and change scene acoording to that

            ScreensFramework.changeScene("Screen5.fxml");

        } catch (IOException ex) {
        }
    }

    @FXML
    public void gotToLogin() {
        try {
            login.setLogout();
            ScreensFramework.changeScene("login.fxml");
        } catch (IOException ex) {
            Logger.getLogger(ScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getContratsList() {

        // TODO add your handling code here:

        /*   try {
         // OntModel model = OpenOWL.OpenConnectOWL();

         System.out.println("Avoir les activités");  // get the activity list
         String queryString;
         queryString = "PREFIX rdf: <http://www.semanticweb.org/IslamicFinanceOWL.owl#>" +"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
         "PREFIX owl: <http://www.w3.org/2002/07/owl#>"+"PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+"PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+

         " SELECT DISTINCT ?sub " +
         " WHERE { ?sub rdf:type owl:NamedIndividual }";

         ResultSet results = OpenOWL.ExecSparQl(queryString); //all method ExecSparQl from OpenOWL class
         while (results.hasNext()) {
         QuerySolution soln = results.nextSolution();

         //  String nomactiviter = soln.getLiteral("NomActivite").getString();
         Resource nomactiviter = soln.getResource(queryString);
         list.add(Resource.class.toString());


         }

         } catch (Exception ex) {
         ex.printStackTrace();
         }
         try {
         // OntModel model = OpenOWL.OpenConnectOWL();

         System.out.println("Avoir les activités");  // get the activity list
           
         String queryString = 
         "PREFIX rdf: <http://www.semanticweb.org/IslamicFinanceOWL.owl#>" 
         +"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>"+
         "PREFIX owl: <http://www.w3.org/2002/07/owl#>"+
         "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>"+
         "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>"+

         " SELECT DISTINCT ?sub " +
         " WHERE { ?sub rdf:type owl:NamedIndividual }";

         ResultSet results = OpenOWL.ExecSparQl(queryString); //all method ExecSparQl from OpenOWL class
         while ( results.hasNext()) {
         QuerySolution soln = results.nextSolution();
         //  String nomactiviter = soln.getLiteral("NomActivite").getString();
         Resource nomactiviter = soln.getResource(queryString);
         //Resource.class.toString()
         String List=soln.get("sub").toString().replace("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#", "");
         list.add(List);
         //  System.out.println(""+soln.get("sub").toString().replace("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#", ""));

         }

         } catch (Exception ex) {
         ex.printStackTrace();
         }*/
        /* Set<OWLLiteral> pd= OWLapi.getClassInstance(); 
         for(OWLLiteral ind : pd) {
         System.out.println( ind.getLiteral());
         }*/
    }

    @FXML
    public void getItemSelected() {
        String item = null;
        item = combolist.getSelectionModel().getSelectedItem();
        if (item != null) {
            //System.out.println("" + item + "ll" + combolist.getSelectionModel().getSelectedIndex());
        }
    }

    public void getData(String item) {

    }

    public void getClassInstance() {

        //mick = df.getOWLNamedIndividual(IRI.create("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#Al-Rahn"));
        hasname = df.getOWLDataProperty(IRI.create("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#hasname"));
        //hasname = df.getOWLDataProperty(IRI.create(prefix + "hasname"));

        try {
            //get specific INDIVIDUAL of class   

            for (OWLClass c : ontology.getClassesInSignature()) {

                if (c.getIRI().getFragment().equals("Contracts")) {

                    NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(c, true);
                     //NodeSet<OWLNamedIndividual> datatype;

                    // System.out.println(c.getIRI().getFragment());
                    for (OWLNamedIndividual i : instances.getFlattened()) {
                        // System.out.println(i.getIRI().getFragment());

                        //System.out.println(""+i.getIRI());
                        Set<OWLLiteral> dataproperty = reasoner.getDataPropertyValues(i, hasname);
                        for (OWLLiteral ind : dataproperty) {
                            if (ind != null) {

                                String d = ind.getLiteral();
                                listContracts.add(i.getIRI());
                                list.add(d);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            //System.out.println("" + e);
        }

    }

    @FXML
    public void setContractInformation() {
        //setContractInformation2();
        try {
            definition.setText("");
            hokme.setText("");
            quran.setText("");
            suna.setText("");
            ijtihad.setText("");
            listConditionContent.clear();

            int i = combolist.getSelectionModel().getSelectedIndex();
            IRI iri = listContracts.get(i);

            mick = df.getOWLNamedIndividual(iri);
            hasdefinition = df.getOWLDataProperty(IRI.create("http://www.semanticweb.org/IslamicFinanceOWL.owl#hasdefinition"));
            //hasdefinition = df.getOWLDataProperty(IRI.create(prefix + "hasdefinition"));

            Set<OWLLiteral> dataproperty = reasoner.getDataPropertyValues(mick, hasdefinition);
            // item=combolist.getSelectionModel().getSelectedItem();
            //System.out.println("dff" + dataproperty);
            for (OWLLiteral ind : dataproperty) {

                definition.setText(ind.getLiteral());

            }
            //getCondtionsIdividulList();
            setConditionsInfromation();//
            setHokmInformation();
            setEvidenceInformation();

        } catch (Exception e) {

        }
    }

    public void setConditionsInfromation() {
        listConditionContent.clear();
        IRI iri = null;
        String uri;
        int i = combolist.getSelectionModel().getSelectedIndex();
        iri = listContracts.get(i);

        mick = df.getOWLNamedIndividual(iri);
        //iri = listConditionsIndividulas.get(i);
        OWLObjectProperty hasLObjectProperty = df.getOWLObjectProperty(IRI.create("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#hasConditions"));
        //OWLObjectProperty hasLObjectProperty = df.getOWLObjectProperty(IRI.create(prefix + "hasConditions"));

        NodeSet<OWLNamedIndividual> ns = reasoner.getObjectPropertyValues(mick, hasLObjectProperty);
        for (Node<OWLNamedIndividual> o : ns) {
            iri = o.getRepresentativeElement().getIRI();
        }

        if (iri != null) {
            mick = df.getOWLNamedIndividual(iri);
            Set<OWLDataPropertyAssertionAxiom> properties = ontology.getDataPropertyAssertionAxioms(mick);
            for (OWLDataPropertyAssertionAxiom ii : properties) {
                uri = ii.getProperty().toString().replace("<", "").replace(">", "");
                //listConditions.add(IRI.create(uri));
                hasname = df.getOWLDataProperty(IRI.create(uri));
                Set<OWLLiteral> dataproperty = reasoner.getDataPropertyValues(mick, hasname);
                for (OWLLiteral ind : dataproperty) {

                    listConditionContent.add(ind.getLiteral());
                }
            }
        }
    }

    public void getCondtionsIdividulList() {
        //reasoner = new Reasoner(ontology);
        //mick = df.getOWLNamedIndividual(IRI.create("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#Al-Rahn"));
        hasname = df.getOWLDataProperty(IRI.create("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#hasname"));
        //hasname = df.getOWLDataProperty(IRI.create(prefix + "hasname"));

        try {
            //get specific INDIVIDUAL of class   

            for (OWLClass c : ontology.getClassesInSignature()) {

                if (c.getIRI().getFragment().equals("Conditions")) {

                    NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(c, true);
                  //NodeSet<OWLNamedIndividual> datatype;

                    //get  list of the individual of class
                    for (OWLNamedIndividual i : instances.getFlattened()) {
                        //System.out.println(i.getIRI().getFragment()); 
                        if (i.getIRI().getFragment().contains("Necessary_Condition")) {
                            listConditionsIndividulas.add(i.getIRI());

                        }

                        /*
                         //get the value of dataproperty of specific individual
                         Set<OWLLiteral> dataproperty = reasoner.getDataPropertyValues(i, hasname);
                         for (OWLLiteral ind : dataproperty) {
                         if (ind != null) {
                     
                         String d = ind.getLiteral();

                         list.add(d);
                         }
                         }*/
                    }
                }
            }
        } catch (Exception e) {
            //System.out.println("" + e);
        }

    }

    public void setHokmInformation() {
        IRI iri = null;
        String uri;

        int i = combolist.getSelectionModel().getSelectedIndex();
        iri = listContracts.get(i);

        mick = df.getOWLNamedIndividual(iri);
        OWLObjectProperty hasLObjectProperty = df.getOWLObjectProperty(IRI.create("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#hasHokm"));
        //OWLObjectProperty hasLObjectProperty = df.getOWLObjectProperty(IRI.create(prefix + "hasHokm"));

        NodeSet<OWLNamedIndividual> ns = reasoner.getObjectPropertyValues(mick, hasLObjectProperty);
        for (Node<OWLNamedIndividual> o : ns) {

            iri = o.getRepresentativeElement().getIRI();

        }
        if (iri != null) {
            mick = df.getOWLNamedIndividual(iri);
            Set<OWLDataPropertyAssertionAxiom> properties = ontology.getDataPropertyAssertionAxioms(mick);
            for (OWLDataPropertyAssertionAxiom ii : properties) {
                uri = ii.getProperty().toString().replace("<", "").replace(">", "");
                //listConditions.add(IRI.create(uri));
                hasname = df.getOWLDataProperty(IRI.create(uri));
                Set<OWLLiteral> dataproperty = reasoner.getDataPropertyValues(mick, hasname);
                for (OWLLiteral ind : dataproperty) {
                    hokme.setText(ind.getLiteral());
                    System.out.println("" + ind.getLiteral());
                    //listConditionContent.add(ind.getLiteral());
                }
            }
        }
    }

    //get shariah Evidence from ontology and set it in gui
    public void setEvidenceInformation() {
        quran.setText("");
        suna.setText("");
        ijtihad.setText("");
        int cp = 1;
        IRI iri = null;
        String uri;
        int i = combolist.getSelectionModel().getSelectedIndex();
        iri = listContracts.get(i);
        //System.out.println("" + iri);
        mick = df.getOWLNamedIndividual(iri);
        OWLObjectProperty hasLObjectProperty = df.getOWLObjectProperty(IRI.create("http://www.semanticweb.org/IslamicFinanceOWL.owl#hasShariahEvidence"));
        //OWLObjectProperty hasLObjectProperty = df.getOWLObjectProperty(IRI.create(prefix+"hasShariahEvidence"));

        NodeSet<OWLNamedIndividual> ns = reasoner.getObjectPropertyValues(mick, hasLObjectProperty);
        for (Node<OWLNamedIndividual> o : ns) {

            //get the iri of the Individual "Shariah_Evidence_Of_''" corespond to the Contract that we chosed in combobox 
            iri = o.getRepresentativeElement().getIRI();

        }
        //System.out.println(""+iri.toString()+"eeeeeeeeee");
        if (iri != null) {
            mick = df.getOWLNamedIndividual(iri);
            Set<OWLDataPropertyAssertionAxiom> properties = ontology.getDataPropertyAssertionAxioms(mick);
            for (OWLDataPropertyAssertionAxiom ii : properties) {
                uri = ii.getProperty().toString().replace("<", "").replace(">", "");
                //listConditions.add(IRI.create(uri));
                hasname = df.getOWLDataProperty(IRI.create(uri));
                //hasname.getIRI().toString().equals(prefix + "hasQuranEvidece")
                if (hasname.getIRI().toString().equals("http://www.semanticweb.org/IslamicFinanceOWL.owl#hasQuranEvidece")) {
                    Set<OWLLiteral> dataproperty = reasoner.getDataPropertyValues(mick, hasname);

                    for (OWLLiteral ind : dataproperty) {
                        //System.out.println("123" + ind.getLiteral());

                        quran.setText(ind.getLiteral());
                        //hasname.getIRI().toString().equals(prefix + "hasSunaEvidence")
                        //listConditionContent.add(ind.getLiteral());
                        // 
                    }
                } else if (hasname.getIRI().toString().equals("http://www.semanticweb.org/IslamicFinanceOWL.owl#hasSunaEvidence")) {
                    Set<OWLLiteral> dataproperty = reasoner.getDataPropertyValues(mick, hasname);

                    for (OWLLiteral ind : dataproperty) {
                        // System.out.println("123" + ind.getLiteral());

                        suna.setText(ind.getLiteral());
                        //hasname.getIRI().toString().equals(prefix + "hasIjtihadEvidence")
                        //listConditionContent.add(ind.getLiteral());
                        //
                    }
                } else if (hasname.getIRI().toString().equals("http://www.semanticweb.org/IslamicFinanceOWL.owl#hasIjtihadEvidence")) {
                    Set<OWLLiteral> dataproperty = reasoner.getDataPropertyValues(mick, hasname);

                    for (OWLLiteral ind : dataproperty) {
                        //  System.out.println("123" + ind.getLiteral());

                        ijtihad.setText(ind.getLiteral());

                        //listConditionContent.add(ind.getLiteral());
                    }
                }
            }
        }
    }

    public void getClassInstance2() {
        try {
            OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
            OWLReasoner r = reasonerFactory.createNonBufferingReasoner(ontology);

            //mick = df.getOWLNamedIndividual(IRI.create("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#Al-Rahn"));
            hasname = df.getOWLDataProperty(IRI.create("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#hasname"));
        //hasname = df.getOWLDataProperty(IRI.create(prefix + "hasname"));

            //get specific INDIVIDUAL of class   
            for (OWLClass c : ontology.getClassesInSignature()) {

                if (c.getIRI().getFragment().equals("Contracts")) {

                    NodeSet<OWLNamedIndividual> instances = r.getInstances(c, true);
                     //NodeSet<OWLNamedIndividual> datatype;

                    // System.out.println(c.getIRI().getFragment());
                    for (OWLNamedIndividual i : instances.getFlattened()) {
                        // System.out.println(i.getIRI().getFragment());

                        //System.out.println(""+i.getIRI());
                        Set<OWLLiteral> dataproperty = r.getDataPropertyValues(i, hasname);
                        for (OWLLiteral ind : dataproperty) {
                            if (ind != null) {

                                String d = ind.getLiteral();
                                System.out.println("123456" + d);
                                listContracts.add(i.getIRI());
                                list.add(d);
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Screen1Controller.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public void setContractInformation2() {
        try {
            OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
            OWLReasoner r = reasonerFactory.createNonBufferingReasoner(ontology);
            definition.setText("");
            hokme.setText("");
            quran.setText("");
            suna.setText("");
            ijtihad.setText("");
            listConditionContent.clear();

            int i = combolist.getSelectionModel().getSelectedIndex();
            IRI iri = listContracts.get(i);

            mick = df.getOWLNamedIndividual(iri);
            hasdefinition = df.getOWLDataProperty(IRI.create("http://www.semanticweb.org/IslamicFinanceOWL.owl#hasdefinition"));
            //hasdefinition = df.getOWLDataProperty(IRI.create(prefix + "hasdefinition"));

            Set<OWLLiteral> dataproperty = r.getDataPropertyValues(mick, hasdefinition);
            // item=combolist.getSelectionModel().getSelectedItem();
            //System.out.println("dff" + dataproperty);
            for (OWLLiteral ind : dataproperty) {

                definition.setText(ind.getLiteral());

            }
            getCondtionsIdividulList2();
            setConditionsInfromation2();
            setHokmInformation2();
            setEvidenceInformation2();
        } catch (Exception e) {

        }
    }

    public void setConditionsInfromation2() {
        OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
        OWLReasoner r = reasonerFactory.createNonBufferingReasoner(ontology);

        listConditionContent.clear();
        IRI iri = null;
        String uri;
        int i = combolist.getSelectionModel().getSelectedIndex();
        iri = listContracts.get(i);

        mick = df.getOWLNamedIndividual(iri);
        //iri = listConditionsIndividulas.get(i);
        OWLObjectProperty hasLObjectProperty = df.getOWLObjectProperty(IRI.create("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#hasConditions"));
        //OWLObjectProperty hasLObjectProperty = df.getOWLObjectProperty(IRI.create(prefix + "hasConditions"));

        NodeSet<OWLNamedIndividual> ns = r.getObjectPropertyValues(mick, hasLObjectProperty);
        for (Node<OWLNamedIndividual> o : ns) {
            iri = o.getRepresentativeElement().getIRI();
        }

        if (iri != null) {
            mick = df.getOWLNamedIndividual(iri);
            Set<OWLDataPropertyAssertionAxiom> properties = ontology.getDataPropertyAssertionAxioms(mick);
            for (OWLDataPropertyAssertionAxiom ii : properties) {
                uri = ii.getProperty().toString().replace("<", "").replace(">", "");
                //listConditions.add(IRI.create(uri));
                hasname = df.getOWLDataProperty(IRI.create(uri));
                Set<OWLLiteral> dataproperty = r.getDataPropertyValues(mick, hasname);
                for (OWLLiteral ind : dataproperty) {

                    listConditionContent.add(ind.getLiteral());
                }
            }
        }
    }

    public void getCondtionsIdividulList2() {
        OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
        OWLReasoner r = reasonerFactory.createNonBufferingReasoner(ontology);
        //reasoner = new Reasoner(ontology);
        //mick = df.getOWLNamedIndividual(IRI.create("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#Al-Rahn"));
        hasname = df.getOWLDataProperty(IRI.create("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#hasname"));
        //hasname = df.getOWLDataProperty(IRI.create(prefix + "hasname"));

        try {
            //get specific INDIVIDUAL of class   

            for (OWLClass c : ontology.getClassesInSignature()) {

                if (c.getIRI().getFragment().equals("Conditions")) {

                    NodeSet<OWLNamedIndividual> instances = r.getInstances(c, true);
                  //NodeSet<OWLNamedIndividual> datatype;

                    //get  list of the individual of class
                    for (OWLNamedIndividual i : instances.getFlattened()) {
                        //System.out.println(i.getIRI().getFragment()); 
                        if (i.getIRI().getFragment().contains("Necessary_Condition")) {
                            listConditionsIndividulas.add(i.getIRI());

                        }

                        /*
                         //get the value of dataproperty of specific individual
                         Set<OWLLiteral> dataproperty = reasoner.getDataPropertyValues(i, hasname);
                         for (OWLLiteral ind : dataproperty) {
                         if (ind != null) {
                     
                         String d = ind.getLiteral();

                         list.add(d);
                         }
                         }*/
                    }
                }
            }
        } catch (Exception e) {
            //System.out.println("" + e);
        }

    }

    public void setHokmInformation2() {
        OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
        OWLReasoner r = reasonerFactory.createNonBufferingReasoner(ontology);
        IRI iri = null;
        String uri;

        int i = combolist.getSelectionModel().getSelectedIndex();
        iri = listContracts.get(i);

        mick = df.getOWLNamedIndividual(iri);
        OWLObjectProperty hasLObjectProperty = df.getOWLObjectProperty(IRI.create("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#hasHokm"));
        //OWLObjectProperty hasLObjectProperty = df.getOWLObjectProperty(IRI.create(prefix + "hasHokm"));

        NodeSet<OWLNamedIndividual> ns = r.getObjectPropertyValues(mick, hasLObjectProperty);
        for (Node<OWLNamedIndividual> o : ns) {

            iri = o.getRepresentativeElement().getIRI();

        }
        if (iri != null) {
            mick = df.getOWLNamedIndividual(iri);
            Set<OWLDataPropertyAssertionAxiom> properties = ontology.getDataPropertyAssertionAxioms(mick);
            for (OWLDataPropertyAssertionAxiom ii : properties) {
                uri = ii.getProperty().toString().replace("<", "").replace(">", "");
                //listConditions.add(IRI.create(uri));
                hasname = df.getOWLDataProperty(IRI.create(uri));
                Set<OWLLiteral> dataproperty = r.getDataPropertyValues(mick, hasname);
                for (OWLLiteral ind : dataproperty) {
                    hokme.setText(ind.getLiteral());
                    System.out.println("" + ind.getLiteral());
                    //listConditionContent.add(ind.getLiteral());
                }
            }
        }
    }

    public void setEvidenceInformation2() {
        OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
        OWLReasoner r = reasonerFactory.createNonBufferingReasoner(ontology);
        quran.setText("");
        suna.setText("");
        ijtihad.setText("");
        int cp = 1;
        IRI iri = null;
        String uri;
        int i = combolist.getSelectionModel().getSelectedIndex();
        iri = listContracts.get(i);
        //System.out.println("" + iri);
        mick = df.getOWLNamedIndividual(iri);
        OWLObjectProperty hasLObjectProperty = df.getOWLObjectProperty(IRI.create("http://www.semanticweb.org/IslamicFinanceOWL.owl#hasShariahEvidence"));
        //OWLObjectProperty hasLObjectProperty = df.getOWLObjectProperty(IRI.create(prefix+"hasShariahEvidence"));

        NodeSet<OWLNamedIndividual> ns = r.getObjectPropertyValues(mick, hasLObjectProperty);
        for (Node<OWLNamedIndividual> o : ns) {

            //get the iri of the Individual "Shariah_Evidence_Of_''" corespond to the Contract that we chosed in combobox 
            iri = o.getRepresentativeElement().getIRI();

        }
        //System.out.println(""+iri.toString()+"eeeeeeeeee");
        if (iri != null) {
            mick = df.getOWLNamedIndividual(iri);
            Set<OWLDataPropertyAssertionAxiom> properties = ontology.getDataPropertyAssertionAxioms(mick);
            for (OWLDataPropertyAssertionAxiom ii : properties) {
                uri = ii.getProperty().toString().replace("<", "").replace(">", "");
                //listConditions.add(IRI.create(uri));
                hasname = df.getOWLDataProperty(IRI.create(uri));
                //hasname.getIRI().toString().equals(prefix + "hasQuranEvidece")
                if (hasname.getIRI().toString().equals("http://www.semanticweb.org/IslamicFinanceOWL.owl#hasQuranEvidece")) {
                    Set<OWLLiteral> dataproperty = r.getDataPropertyValues(mick, hasname);

                    for (OWLLiteral ind : dataproperty) {
                        //System.out.println("123" + ind.getLiteral());

                        quran.setText(ind.getLiteral());
                        //hasname.getIRI().toString().equals(prefix + "hasSunaEvidence")
                        //listConditionContent.add(ind.getLiteral());
                        // 
                    }
                } else if (hasname.getIRI().toString().equals("http://www.semanticweb.org/IslamicFinanceOWL.owl#hasSunaEvidence")) {
                    Set<OWLLiteral> dataproperty = r.getDataPropertyValues(mick, hasname);

                    for (OWLLiteral ind : dataproperty) {
                        // System.out.println("123" + ind.getLiteral());

                        suna.setText(ind.getLiteral());
                        //hasname.getIRI().toString().equals(prefix + "hasIjtihadEvidence")
                        //listConditionContent.add(ind.getLiteral());
                        //
                    }
                } else if (hasname.getIRI().toString().equals("http://www.semanticweb.org/IslamicFinanceOWL.owl#hasIjtihadEvidence")) {
                    Set<OWLLiteral> dataproperty = r.getDataPropertyValues(mick, hasname);

                    for (OWLLiteral ind : dataproperty) {
                        //  System.out.println("123" + ind.getLiteral());

                        ijtihad.setText(ind.getLiteral());

                        //listConditionContent.add(ind.getLiteral());
                    }
                }
            }
        }
    }

}
