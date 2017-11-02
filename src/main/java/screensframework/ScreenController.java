/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screensframework;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
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
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import login.login;
import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLOntologyDocumentSource;
import org.semanticweb.owlapi.io.OWLOntologyDocumentTarget;
import org.semanticweb.owlapi.io.RDFXMLOntologyFormat;
import org.semanticweb.owlapi.io.StringDocumentSource;
import org.semanticweb.owlapi.io.StringDocumentTarget;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiomChange;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLDisjointClassesAxiom;
import org.semanticweb.owlapi.model.OWLException;
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
import org.semanticweb.owlapi.vocab.OWL2Datatype;
import static org.testng.Assert.assertEquals;

public class ScreenController implements Initializable {

    @FXML
    ComboBox<String> hokmlist;
    private Pane p1;
    private Pane p2;

    Button button1;

    @FXML
    private TextField transactionField1;
    @FXML
    private TextField transactionField2;
    @FXML

    private TextField conditionField;
    @FXML
    private TextArea transactionDef;

    @FXML
    private ListView<String> conditionList;
    @FXML
    private TextArea transactionVerdict;
    @FXML
    private TextArea qurenEvidence;
    @FXML
    private TextArea sunaEvidence;
    private TextField ijtihadEvidence;
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
    private static final ObservableList<String> items = FXCollections.observableArrayList();

    private static final ObservableList<String> itemsHokm = FXCollections.observableArrayList("حلال", "حرام");

    private static int itemIndex;

    Reasoner reasoner;
    OWLOntology ontology;
    OWLOntologyManager manager;
    File file;
    OWLDataFactory df;
    OWLNamedIndividual mick;
    OWLDataProperty hasname, hasdefinition;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        boolean admin = false;
        admin = login.isLogin();
        if (!admin) {
            adds.setDisable(true);
            updates.setDisable(true);
            deletes.setDisable(true);
            System.out.println("////////////");
        }
        // TODO
        conditionList.setItems(items);
        hokmlist.setItems(itemsHokm);
        transactionField2.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {

            }
        });
        loadOntology();

        menu.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                contextmenu.show(menu, Side.LEFT, event.getX(), event.getY());
            }

        });
        items.clear();
    }

    public void loadOntology() {
        manager = OntoConnect.manager;
        ontology = OntoConnect.ontology;
        //file = new File("/home/fathi/islamic_finance.owl");
        file = OntoConnect.file;
        df = manager.getOWLDataFactory();
        try {
            ontology = manager.loadOntologyFromOntologyDocument(file);

            if (ontology != null) {
            }

        } catch (OWLOntologyCreationException ex) {
        }
    }

    public void createIndividual() throws OWLOntologyStorageException, FileNotFoundException, OWLException {
        String arabicContractName = null;
        String latinContractName = null;
        String contractDefinition = null;
        String condition;
        String qurenString = null;
        String sunaString = null;
        String ijtihadString = null;
        boolean there = false;
        int i = -1;
        try {
            i = hokmlist.getSelectionModel().getSelectedIndex();

            arabicContractName = transactionField1.getText();
            latinContractName = transactionField2.getText();
            contractDefinition = transactionDef.getText();

            qurenString = qurenEvidence.getText();
            sunaString = sunaEvidence.getText();
            ijtihadString = transactionVerdict.getText();

            if (!arabicContractName.equals("")) {
                if (!latinContractName.equals("")) {
                    if (!contractDefinition.equals("")) {
                        if (items.size() != 0) {
                            if (i != -1) {
                                if (!qurenString.equals("")) {
                                    if (!sunaString.equals("")) {
                                        if (!ijtihadString.equals("")) {
                                            there = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {

        }
        if (there == true) {

            //OntologyHelper oh = new OntologyHelper();
            OWLClass Contracts = createClass("http://www.co-ode.org/ontologies/ont.owl#Contracts");

            OWLIndividual Sale = createIndividual("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#" + latinContractName);
            OWLClassAssertionAxiom contractsAssertion = df.getOWLClassAssertionAxiom(Contracts, Sale);
            manager.addAxiom(ontology, contractsAssertion);

            OWLIndividual NecessaryCondition = createIndividual("http://www.semanticweb.org/IslamicFinanceOWL.owl#Necessary_Condition_Of_" + latinContractName);
            OWLClass Conditions = createClass("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#Conditions");
            OWLClassAssertionAxiom classAssertion = df.getOWLClassAssertionAxiom(Conditions, NecessaryCondition);
            manager.addAxiom(ontology, classAssertion);

            OWLIndividual HokmHarem = createIndividual("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#Haram");
            OWLIndividual HokmHalel = createIndividual("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#Halal");

            OWLIndividual ShariahEvidence = createIndividual("http://www.semanticweb.org/IslamicFinanceOWL.owl#Shariah_Evidence_Of_" + latinContractName);
            OWLClass ShariaSources = createClass("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#Shariah_Sources");
            OWLClassAssertionAxiom classAssertion2 = df.getOWLClassAssertionAxiom(ShariaSources, ShariahEvidence);
            manager.addAxiom(ontology, classAssertion2);

            ///make relationShip between Individuals 
            OWLObjectProperty hasHokm = createObjectProperty("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#hasHokm");
            OWLObjectProperty hasConditions = createObjectProperty("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#hasConditions");
            OWLObjectProperty hasShariahEvidence = createObjectProperty("http://www.semanticweb.org/IslamicFinanceOWL.owl#hasShariahEvidence");
            i = hokmlist.getSelectionModel().getSelectedIndex();

            if (i == 0) {

                addObjectproperty(ontology, Sale, hasHokm, HokmHalel);
                applyChange(addObjectproperty(ontology, Sale, hasHokm, HokmHalel));

            } else if (i == 1) {

                addObjectproperty(ontology, Sale, hasHokm, HokmHarem);
                applyChange(addObjectproperty(ontology, Sale, hasHokm, HokmHarem));
            }

            addObjectproperty(ontology, Sale, hasShariahEvidence, ShariahEvidence);
            addObjectproperty(ontology, Sale, hasConditions, NecessaryCondition);

            applyChange(addObjectproperty(ontology, Sale, hasShariahEvidence, ShariahEvidence));
            applyChange(addObjectproperty(ontology, Sale, hasConditions, NecessaryCondition));

            ///create the DataProperty 
            OWLDataProperty hasname = createDataProperty("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#hasname");

            applyChange(addDataToIndividual(ontology, Sale, hasname, arabicContractName));

            OWLDataProperty hasdefinition = createDataProperty("http://www.semanticweb.org/IslamicFinanceOWL.owl#hasdefinition");
            OWLDatatype integer = df.getOWLDatatype(OWL2Datatype.XSD_STRING.getIRI());
            applyChange(addDataToIndividual(ontology, Sale, hasdefinition, contractDefinition));
            int cpt = 1;
            for (String conditions : items) {
                OWLDataProperty hasConditiion1 = createDataProperty("http://www.semanticweb.org/IslamicFinanceOWL.owl#hasCondition" + cpt);
                OWLDataProperty hasCondition = createDataProperty("http://www.semanticweb.org/IslamicFinanceOWL.owl#hasCondition");
                OWLDataPropertyDomainAxiom asert = df.getOWLDataPropertyDomainAxiom(hasConditiion1, Conditions);
                OWLDataPropertyRangeAxiom asertrange = df.getOWLDataPropertyRangeAxiom(hasConditiion1, integer);
                manager.addAxiom(ontology, asertrange);
                manager.addAxiom(ontology, asert);
                applyChange(createSubDataProperty(ontology, hasConditiion1, hasCondition));
                applyChange(addDataToIndividual(ontology, NecessaryCondition, hasConditiion1, conditions));

                cpt++;
            }

            OWLDataProperty hasQuranEvidence = createDataProperty("http://www.semanticweb.org/IslamicFinanceOWL.owl#hasQuranEvidece");
            OWLDataProperty hasSunaEvidence = createDataProperty("http://www.semanticweb.org/IslamicFinanceOWL.owl#hasSunaEvidence");
            OWLDataProperty hasIjtihadEvidence = createDataProperty("http://www.semanticweb.org/IslamicFinanceOWL.owl#hasIjtihadEvidence");

            applyChange(addDataToIndividual(ontology, ShariahEvidence, hasQuranEvidence, qurenString));
            applyChange(addDataToIndividual(ontology, ShariahEvidence, hasSunaEvidence, sunaString));
            applyChange(addDataToIndividual(ontology, ShariahEvidence, hasIjtihadEvidence, ijtihadString));

            //http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#Shariah_Sources
            //http://www.semanticweb.org/IslamicFinanceOWL.owl#Shariah_Evidence_Of_Mudarabah
            //http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#Halal
            //http://www.semanticweb.org/IslamicFinanceOWL.owl#Necessary_Condition_Of_Kafalah
            //http://www.semanticweb.org/IslamicFinanceOWL.owl#hasCondition
            // applyChange(associateIndividualWithClass(ontology, person, captainKirk));
            //applyChange(associateIndividualWithClass(ontology, person, milod));
            //String base = "http://autumncode.com/ontologies/terminator.owl#";
            //PrefixManager pm = new DefaultPrefixManager(base);
            //OWLNamedIndividual mary = df.getOWLNamedIndividual("Salah", pm);
            // OWLClassAssertionAxiom classAssertion = df.getOWLClassAssertionAxiom(Contracts, Sale);//
            //OWLDatatype integer = df.getOWLDatatype(OWL2Datatype.XSD_STRING.getIRI());
            //OWLDatatype integerDatatype = df.getIntegerOWLDatatype();
            //OWLDatatype rdfsLiteral = df.getTopDatatype();
            //OWLLiteral eighteen = df.getOWLLiteral(18);
            //manager.addAxiom(ontology, classAssertion);
            //OWLDataProperty hasAge = df.getOWLDataProperty("hasAge", pm);
            //OWLDatatypeRestriction integerGE18 = df.getOWLDatatypeRestriction(integer, OWLFacet.MIN_INCLUSIVE, eighteen);
            //OWLDataPropertyRangeAxiom rangeAxiom = df.getOWLDataPropertyRangeAxiom(hasAge, integerGE18);
            //OWLDataProperty hasname = createDataProperty("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#hasname");
            //applyChange(addDataToIndividual(ontology, Sale, hasname, "بيع"));
            //manager.addAxiom(ontology, rangeAxiom);
            //writeOntology();
            //dumpOWL(ontology);
            file = file.getAbsoluteFile();
            //BufferedOutputStream outputStream=new BufferedOutputStream(new FileOutputStream(file));
            // manager.saveOntology(ontology, new OWLFunctionalSyntaxOntologyFormat(), outputStream);
            RDFXMLOntologyFormat rdfxmlFormat = new RDFXMLOntologyFormat();
            manager.saveOntology(ontology, rdfxmlFormat, IRI.create(file));
        //Save the ontology in a different format
            // OWLOntologyFormat format = manager.getOntologyFormat(ontology);
            //  OWLXMLOntologyFormat owlxmlFormat = new OWLXMLOntologyFormat();

            //manager.saveOntology(ontology, owlxmlFormat, IRI.create(file.toURI()));
            //manager.saveOntology(ontology, null);
            //mick = fac.getOWLNamedIndividual(IRI.create("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#Al-Rahn"));
        }
    }

    /**
     * @return the p1
     */
    public void createIndividual2() throws OWLOntologyStorageException, FileNotFoundException, OWLException {
        String prefix = "http://www.w3.org/IslamicFinanceOntology.owl#";
        String arabicContractName = null;
        String latinContractName = null;
        String contractDefinition = null;
        String condition;
        String qurenString = null;
        String sunaString = null;
        String ijtihadString = null;
        boolean there = false;
        int i = -1;

        try {
            i = hokmlist.getSelectionModel().getSelectedIndex();

            arabicContractName = transactionField1.getText();
            latinContractName = transactionField2.getText();
            contractDefinition = transactionDef.getText();

            qurenString = qurenEvidence.getText();
            sunaString = sunaEvidence.getText();
            ijtihadString = transactionVerdict.getText();

            if (!arabicContractName.equals("")) {
                if (!latinContractName.equals("")) {
                    if (!contractDefinition.equals("")) {
                        if (items.size() != 0) {
                            if (i != -1) {
                                if (!qurenString.equals("")) {
                                    if (!sunaString.equals("")) {
                                        if (!ijtihadString.equals("")) {
                                            there = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {

        }
        if (there
                == true) {

            //OntologyHelper oh = new OntologyHelper();
            OWLClass Contracts = createClass(prefix + "Contracts");

            OWLIndividual Sale = createIndividual(prefix + latinContractName);
            OWLClassAssertionAxiom contractsAssertion = df.getOWLClassAssertionAxiom(Contracts, Sale);
            manager.addAxiom(ontology, contractsAssertion);

            OWLIndividual NecessaryCondition = createIndividual(prefix + "Necessary_Condition_Of_" + latinContractName);
            OWLClass Conditions = createClass(prefix + "Conditions");
            OWLClassAssertionAxiom classAssertion = df.getOWLClassAssertionAxiom(Conditions, NecessaryCondition);
            manager.addAxiom(ontology, classAssertion);

            OWLIndividual HokmHarem = createIndividual(prefix + "Haram");
            OWLIndividual HokmHalel = createIndividual(prefix + "Halal");

            OWLIndividual ShariahEvidence = createIndividual(prefix + "Shariah_Evidence_Of_" + latinContractName);
            OWLClass ShariaSources = createClass(prefix + "Shariah_Sources");
            OWLClassAssertionAxiom classAssertion2 = df.getOWLClassAssertionAxiom(ShariaSources, ShariahEvidence);
            manager.addAxiom(ontology, classAssertion2);

            ///make relationShip between Individuals 
            OWLObjectProperty hasHokm = createObjectProperty(prefix + "hasHokm");
            OWLObjectProperty hasConditions = createObjectProperty(prefix + "hasConditions");
            OWLObjectProperty hasShariahEvidence = createObjectProperty(prefix + "hasShariahEvidence");
            i = hokmlist.getSelectionModel().getSelectedIndex();

            if (i == 0) {

                addObjectproperty(ontology, Sale, hasHokm, HokmHalel);
                applyChange(addObjectproperty(ontology, Sale, hasHokm, HokmHalel));

            } else if (i == 1) {

                addObjectproperty(ontology, Sale, hasHokm, HokmHarem);
                applyChange(addObjectproperty(ontology, Sale, hasHokm, HokmHarem));
            }

            addObjectproperty(ontology, Sale, hasShariahEvidence, ShariahEvidence);
            addObjectproperty(ontology, Sale, hasConditions, NecessaryCondition);

            applyChange(addObjectproperty(ontology, Sale, hasShariahEvidence, ShariahEvidence));
            applyChange(addObjectproperty(ontology, Sale, hasConditions, NecessaryCondition));

            ///create the DataProperty 
            OWLDataProperty hasname = createDataProperty(prefix + "hasname");

            applyChange(addDataToIndividual(ontology, Sale, hasname, arabicContractName));
            OWLDatatype integer = df.getOWLDatatype(OWL2Datatype.XSD_STRING.getIRI());
            OWLDataProperty hasdefinition = createDataProperty(prefix + "hasdefinition");
            applyChange(addDataToIndividual(ontology, Sale, hasdefinition, contractDefinition));
            int cpt = 1;
            for (String conditions : items) {
                OWLDataProperty hasConditiion1 = createDataProperty(prefix + "hasCondition" + cpt);
                OWLDataProperty hasCondition = createDataProperty(prefix + "hasCondition");
                OWLDataPropertyDomainAxiom asert = df.getOWLDataPropertyDomainAxiom(hasConditiion1, Conditions);
                OWLDataPropertyRangeAxiom asertrange = df.getOWLDataPropertyRangeAxiom(hasConditiion1, integer);
                manager.addAxiom(ontology, asertrange);
                manager.addAxiom(ontology, asert);
                applyChange(createSubDataProperty(ontology, hasConditiion1, hasCondition));
                applyChange(addDataToIndividual(ontology, NecessaryCondition, hasConditiion1, conditions));

                cpt++;
            }

            OWLDataProperty hasQuranEvidence = createDataProperty(prefix + "hasQuranEvidece");
            OWLDataProperty hasSunaEvidence = createDataProperty(prefix + "hasSunaEvidence");
            OWLDataProperty hasIjtihadEvidence = createDataProperty(prefix + "hasIjtihadEvidence");

            applyChange(addDataToIndividual(ontology, ShariahEvidence, hasQuranEvidence, qurenString));
            applyChange(addDataToIndividual(ontology, ShariahEvidence, hasSunaEvidence, sunaString));
            applyChange(addDataToIndividual(ontology, ShariahEvidence, hasIjtihadEvidence, ijtihadString));

            //http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#Shariah_Sources
            //http://www.semanticweb.org/IslamicFinanceOWL.owl#Shariah_Evidence_Of_Mudarabah
            //http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#Halal
            //http://www.semanticweb.org/IslamicFinanceOWL.owl#Necessary_Condition_Of_Kafalah
            //http://www.semanticweb.org/IslamicFinanceOWL.owl#hasCondition
            // applyChange(associateIndividualWithClass(ontology, person, captainKirk));
            //applyChange(associateIndividualWithClass(ontology, person, milod));
            //String base = "http://autumncode.com/ontologies/terminator.owl#";
            //PrefixManager pm = new DefaultPrefixManager(base);
            //OWLNamedIndividual mary = df.getOWLNamedIndividual("Salah", pm);
            // OWLClassAssertionAxiom classAssertion = df.getOWLClassAssertionAxiom(Contracts, Sale);//
            //OWLDatatype integer = df.getOWLDatatype(OWL2Datatype.XSD_STRING.getIRI());
            //OWLDatatype integerDatatype = df.getIntegerOWLDatatype();
            //OWLDatatype rdfsLiteral = df.getTopDatatype();
            //OWLLiteral eighteen = df.getOWLLiteral(18);
            //manager.addAxiom(ontology, classAssertion);
            //OWLDataProperty hasAge = df.getOWLDataProperty("hasAge", pm);
            //OWLDatatypeRestriction integerGE18 = df.getOWLDatatypeRestriction(integer, OWLFacet.MIN_INCLUSIVE, eighteen);
            //OWLDataPropertyRangeAxiom rangeAxiom = df.getOWLDataPropertyRangeAxiom(hasAge, integerGE18);
            //OWLDataProperty hasname = createDataProperty("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#hasname");
            //applyChange(addDataToIndividual(ontology, Sale, hasname, "بيع"));
            //manager.addAxiom(ontology, rangeAxiom);
            //writeOntology();
            //dumpOWL(ontology);
            file = file.getAbsoluteFile();
            //BufferedOutputStream outputStream=new BufferedOutputStream(new FileOutputStream(file));
            // manager.saveOntology(ontology, new OWLFunctionalSyntaxOntologyFormat(), outputStream);
            RDFXMLOntologyFormat rdfxmlFormat = new RDFXMLOntologyFormat();
            manager.saveOntology(ontology, rdfxmlFormat, IRI.create(file));
        //Save the ontology in a different format
            // OWLOntologyFormat format = manager.getOntologyFormat(ontology);
            //  OWLXMLOntologyFormat owlxmlFormat = new OWLXMLOntologyFormat();

            //manager.saveOntology(ontology, owlxmlFormat, IRI.create(file.toURI()));
            //manager.saveOntology(ontology, null);
            //mick = fac.getOWLNamedIndividual(IRI.create("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#Al-Rahn"));
        }
    }

    public Pane getP1() {
        return p1;
    }

    /**
     * @return the p2
     */
    public Pane getP2() {
        return p2;
    }

    /**
     * @return the transactionField1
     */
    public String getTransactionField1() {
        return transactionField1.getText();
    }

    /**
     * @return the transactionField2
     */
    public String getTransactionField2() {
        return transactionField2.getText();
    }

    /**
     * @return the conditionField
     */
    public String getConditionField() {
        return conditionField.getText();
    }

    /**
     * @return the transactionDef
     */
    public String getTransactionDef() {
        return transactionDef.getText();
    }

    /**
     * @return the transactionCondi
     */
    /**
     * @return the conditionList
     */
    public ListView<String> getConditionList() {
        return conditionList;
    }

    /**
     * @return the transactionVerdict
     */
    public TextArea getTransactionVerdict() {
        return transactionVerdict;
    }

    /**
     * @return the qurenEvidence
     */
    public TextArea getQurenEvidence() {
        return qurenEvidence;
    }

    /**
     * @return the sunaEvidence
     */
    public TextArea getSunaEvidence() {
        return sunaEvidence;
    }

    /**
     * @return the ijtihadEvidence
     */
    public TextField getIjtihadEvidence() {
        return ijtihadEvidence;
    }

    /**
     * @return the items
     */
    public ObservableList<String> getItems() {
        return items;
    }

    /**
     * @return the itemIndex
     */
    public int getItemIndex() {
        return itemIndex;
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
     * With ontology ontology, an object of class a cannot be simultaneously an object
 of class b. This is not implied to be an inverse relationship; saying
     * that a cannot be a b does not mean that b cannot be an a.
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

    @FXML
    public void print(ActionEvent evt) {
        int i = -1;
        i = conditionList.getSelectionModel().getSelectedIndex();
        if (i == -1) {

        } else {
            items.remove(i);
        }

    }

    @FXML
    public void setItemsCondition(ActionEvent evt) {
        String item;
        item = conditionField.getText();
        if (!item.equals("")) {
            items.add(item);

        }
    }

    @FXML
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
    public void saveOntology(ActionEvent actionEvent) {
        try {
            this.createIndividual();
            //createIndividual2();
            transactionField1.setText("");
            transactionField2.setText("");
            transactionDef.setText("");
            conditionField.setText("");
            items.clear();
            hokmlist.setPromptText("إختر حكم المعاملة");

            sunaEvidence.setText("");
            qurenEvidence.setText("");
            transactionVerdict.setText("");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(ScreenController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (OWLException ex) {
            Logger.getLogger(ScreenController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

}
