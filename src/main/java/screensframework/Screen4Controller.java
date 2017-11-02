/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screensframework;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import login.login;
import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.io.OWLOntologyDocumentSource;
import org.semanticweb.owlapi.io.OWLOntologyDocumentTarget;
import org.semanticweb.owlapi.io.RDFXMLOntologyFormat;
import org.semanticweb.owlapi.io.StringDocumentTarget;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiomChange;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDisjointClassesAxiom;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.util.OWLEntityRemover;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

/**
 * FXML Controller class
 *
 * @author fathi
 */
public class Screen4Controller implements Initializable {

    @FXML
    Button button1;
    @FXML
    AnchorPane ach;
    @FXML
    ComboBox<String> combolist;
    @FXML
    Button menu;
    @FXML
    ContextMenu contextmenu;
    @FXML
    MenuItem adds;
    @FXML
    MenuItem updates;

    ObservableList<String> list = FXCollections.observableArrayList();
    OWLOntology ontology;
    OWLOntologyManager manager;
    Reasoner reasoner;
    OWLDataFactory df;
    OWLNamedIndividual mick;
    OWLDataProperty hasname, hasdefinition;
    File file;

    //OWLOntologyManager manager;
    public static List<IRI> listContracts = new ArrayList();

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        boolean admin = false;
        admin = login.isLogin();
        if (!admin) {
            adds.setDisable(true);
            updates.setDisable(true);

            System.out.println("////////////");
        }
        combolist.setItems(list);
        button1.setDisable(true);
        combolist.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println("aqwsedc");

            }

        });
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {

                try {
                    loadOntology();
                    reasoner = new Reasoner(ontology);
                    getClassInstance1();
                    //getClassInstance2();
                } catch (Exception ex) {
                    System.out.println("cccc" + ex);
                }

            }
        });
        t.start();

    }

    @FXML
    public void selectedItem(ActionEvent evt) {
        button1.setDisable(false);
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
            file = OntoConnect.file;
            df = manager.getOWLDataFactory();
            // reasoner = new Reasoner(ontology);
        } catch (Exception ex) {
            System.out.println("123" + ex);
            ex.printStackTrace();
        }

    }

    public void getClassInstance() {

        //mick = df.getOWLNamedIndividual(IRI.create("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#Al-Rahn"));
        try {
            if (ontology == null) {
                System.out.println("on null");
            }
            if (df == null) {
                System.out.println("df null");
            }
            if (reasoner == null) {
                System.out.println("re null");
            }
            hasname = df.getOWLDataProperty(IRI.create("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#hasname"));

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
                                //System.out.println("---"+ind.getLiteral());
                                //System.out.println("***" + i.getIRI());
                                String d = ind.getLiteral();
                                listContracts.add(i.getIRI());
                                list.add(d);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getClassInstance1() {
        list.clear();
        listContracts.clear();
        try {

            IRI iri = IRI.create("http://www.co-ode.org/ontologies/ont.owl#Contracts");
            hasname = df.getOWLDataProperty(IRI.create("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#hasname"));

            OWLClass Contracts = df.getOWLClass(iri);
            //iri = IRI.create("http://www.semanticweb.org/fathi/AidFatwa.owl#", syn);
            NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(Contracts, true);
            for (org.semanticweb.owlapi.reasoner.Node<OWLNamedIndividual> cc : instances) {

                for (OWLNamedIndividual ccc : cc) {
                    Set<OWLLiteral> dataproperty = reasoner.getDataPropertyValues(ccc, hasname);
                    listContracts.add(ccc.getIRI());

                    for (OWLLiteral ind : dataproperty) {

                        //System.out.println("---"+ind.getLiteral());
                        //System.out.println("***" + i.getIRI());
                        String d = ind.getLiteral();

                        list.add(d);

                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void changeScene() {
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
            //get the button object on which user clicks and change scene acoording to that

            ScreensFramework.changeScene("Screen4.fxml");

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

    @FXML
    public void deleteContract() throws OWLOntologyStorageException {

        int i = combolist.getSelectionModel().getSelectedIndex();
        IRI iri = listContracts.get(i);
        String latinContractName = iri.toString().replace("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#", "");
        DialogExample d = new DialogExample();
        if (d.displayDialog()) {
            OWLEntityRemover remover = new OWLEntityRemover(manager, Collections.singleton(ontology));

            OWLNamedIndividual shariconditiah = df.getOWLNamedIndividual(IRI.create("http://www.semanticweb.org/IslamicFinanceOWL.owl#Necessary_Condition_Of_" + latinContractName));

            OWLNamedIndividual shariah = df.getOWLNamedIndividual(IRI.create("http://www.semanticweb.org/IslamicFinanceOWL.owl#Shariah_Evidence_Of_" + latinContractName));

            OWLNamedIndividual Sale = df.getOWLNamedIndividual(iri);

            remover.visit(Sale);
            remover.visit(shariconditiah);
            remover.visit(shariah);
            manager.applyChanges(remover.getChanges());

            OntoConnect.file = file.getAbsoluteFile();
            RDFXMLOntologyFormat rdfxmlFormat = new RDFXMLOntologyFormat();

            manager.saveOntology(ontology, rdfxmlFormat, IRI.create(file));
            OntoConnect.refresh();
        }

    }

    private void displayDialog() {

        String titleTxt = "JavaFX Dialogs Example";

        // Custom dialog
        Dialog dialog = new Dialog<>();
        dialog.setTitle(titleTxt);
        dialog.setHeaderText("This is a dialog. Enter info and \n"
                + "press Okay (or click title bar 'X' for cancel).");
        dialog.setResizable(true);

        // Widgets
        Label label1 = new Label("Name: ");
        Label label2 = new Label("Phone: ");
        TextField text1 = new TextField();
        TextField text2 = new TextField();

        // Create layout and add to dialog
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 35, 20, 35));
        grid.add(label1, 1, 1); // col=1, row=1
        grid.add(text1, 2, 1);
        grid.add(label2, 1, 2); // col=1, row=2
        grid.add(text2, 2, 2);
        dialog.getDialogPane().setContent(grid);

        // Add button to dialog
        ButtonType buttonTypeOk = new ButtonType("Okay", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().add(buttonTypeOk);
        dialog.getDialogPane().getButtonTypes().add(cancel);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.get() == buttonTypeOk) {
            System.out.println("ssdaaaa");
        }
        // Result converter for dialog
        // Show dialog

    }

    public static IRI convertStringToIRI(String ns) {
        return IRI.create(ns);
    }

    /**
     * Simple method to write an OWL structure to <code>System.out</code>. It is
     * basically a wrapper for the writeOntology method.
     *
     * @param ontology the ontology to display
     */
    public void dumpOWL(OWLOntology ontology) {
        try {
            StringDocumentTarget sdt = new StringDocumentTarget();
            writeOntology(ontology, sdt);

        } catch (Exception e) {
            // this is where Scala would be nice.
            throw new RuntimeException(e);
        }
    }

    public OWLOntology createOntology(String iri) throws OWLOntologyCreationException {
        return createOntology(convertStringToIRI(iri));
    }

    public OWLOntology createOntology(IRI iri) throws OWLOntologyCreationException {
        return manager.createOntology(iri);
    }

    public void writeOntology(OWLOntology o, OWLOntologyDocumentTarget documentTarget)
            throws OWLOntologyStorageException {
        manager.saveOntology(o, documentTarget);
    }

    public OWLOntology readOntology(OWLOntologyDocumentSource source)
            throws OWLOntologyCreationException {
        return manager.loadOntologyFromOntologyDocument(source);
    }

    public OWLClass createClass(String iri) {
        return createClass(convertStringToIRI(iri));
    }

    public OWLClass createClass(IRI iri) {
        return df.getOWLClass(iri);
    }

    public OWLAxiomChange createSubclass(OWLOntology o, OWLClass subclass, OWLClass superclass) {
        return new AddAxiom(o, df.getOWLSubClassOfAxiom(subclass, superclass));
    }

    public void applyChange(OWLAxiomChange... axiom) {
        applyChanges(axiom);
    }

    private void applyChanges(OWLAxiomChange... axioms) {
        manager.applyChanges(Arrays.asList(axioms));

    }

    public OWLIndividual createIndividual(String iri) {
        return createIndividual(convertStringToIRI(iri));
    }

    private OWLIndividual createIndividual(IRI iri) {
        return df.getOWLNamedIndividual(iri);
    }

    public OWLAxiomChange associateIndividualWithClass(OWLOntology o,
            OWLClass clazz,
            OWLIndividual individual) {
        return new AddAxiom(o, df.getOWLClassAssertionAxiom(clazz, individual));
    }

    public OWLObjectProperty createObjectProperty(String iri) {
        return createObjectProperty(convertStringToIRI(iri));
    }

    public OWLObjectProperty createObjectProperty(IRI iri) {
        return df.getOWLObjectProperty(iri);
    }

    /**
     * With ontology ontology, property in refHolder points to a refTo.
     *
     * @param o The ontology reference
     * @param property the data property reference
     * @param refHolder the container of the property
     * @param refTo the class the property points to
     * @return a patch to the ontology
     */
    public OWLAxiomChange associateObjectPropertyWithClass(OWLOntology o,
            OWLObjectProperty property,
            OWLClass refHolder,
            OWLClass refTo) {
        OWLClassExpression hasSomeRefTo = df.getOWLObjectSomeValuesFrom(property, refTo);
        OWLSubClassOfAxiom ax = df.getOWLSubClassOfAxiom(refHolder, hasSomeRefTo);
        return new AddAxiom(o, ax);
    }

    /**
     * With ontology ontology, an object of class a cannot be simultaneously an
     * object of class b. This is not implied to be an inverse relationship;
     * saying that a cannot be a b does not mean that b cannot be an a.
     *
     * @param o the ontology reference
     * @param a the source of the disjunction
     * @param b the object of the disjunction
     * @return a patch to the ontology
     */
    public OWLAxiomChange addDisjointClass(OWLOntology o, OWLClass a, OWLClass b) {
        OWLDisjointClassesAxiom expression = df.getOWLDisjointClassesAxiom(a, b);
        return new AddAxiom(o, expression);
    }

    public OWLAxiomChange addObjectproperty(OWLOntology o, OWLIndividual target, OWLObjectProperty property, OWLIndividual value) {
        OWLObjectPropertyAssertionAxiom prop = df.getOWLObjectPropertyAssertionAxiom(property, target, value);
        return new AddAxiom(o, prop);
    }

    public OWLDataProperty createDataProperty(String iri) {
        return createDataProperty(convertStringToIRI(iri));
    }

    public OWLDataProperty createDataProperty(IRI iri) {
        return df.getOWLDataProperty(iri);
    }

    public OWLAxiomChange addDataToIndividual(OWLOntology o, OWLIndividual individual, OWLDataProperty property, String value) {
        OWLLiteral literal = df.getOWLLiteral(value, OWL2Datatype.XSD_STRING);
        return new AddAxiom(o, df.getOWLDataPropertyAssertionAxiom(property, individual, literal));
    }

    public OWLAxiomChange addDataToIndividual(OWLOntology o, OWLIndividual individual, OWLDataProperty property, boolean value) {
        OWLLiteral literal = df.getOWLLiteral(value);
        return new AddAxiom(o, df.getOWLDataPropertyAssertionAxiom(property, individual, literal));
    }

    public OWLAxiomChange addDataToIndividual(OWLOntology o, OWLIndividual individual, OWLDataProperty property, int value) {
        OWLLiteral literal = df.getOWLLiteral(value);
        return new AddAxiom(o, df.getOWLDataPropertyAssertionAxiom(property, individual, literal));
    }

    public OWLAxiomChange createSubDataProperty(OWLOntology o, OWLDataProperty subpoperty, OWLDataProperty superproperty) {

        return new AddAxiom(o, df.getOWLSubDataPropertyOfAxiom(subpoperty, superproperty));
    }

}
