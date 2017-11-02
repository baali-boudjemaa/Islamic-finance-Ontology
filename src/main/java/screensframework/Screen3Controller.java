package screensframework;

import com.sun.javafx.beans.event.AbstractNotifyListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.adapter.JavaBeanObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import login.login;
import org.controlsfx.control.PopOver;
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
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
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
import org.semanticweb.owlapi.model.RemoveAxiom;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.vocab.OWL2Datatype;
import static org.testng.Assert.assertEquals;
import static screensframework.Screen5Controller.df;

/**
 * add new Contract to the Ontology
 *
 * @author fathi
 */
public class Screen3Controller implements Initializable ,EventHandler<MouseEvent>{

    @FXML
    AnchorPane ach;
    @FXML
    ComboBox<String> combolist;
    @FXML
    ObservableList<String> list = FXCollections.observableArrayList();
    @FXML
    ObservableList<String> listConditionContent = FXCollections.observableArrayList();
    ObservableList<String> listConditionContent2 = FXCollections.observableArrayList();

    @FXML
    ComboBox hokme;
    private final ObservableList<String> itemsHokm = FXCollections.observableArrayList("حلال", "حرام");

    private final ObservableList<String> items = FXCollections.observableArrayList();

    @FXML
    TextArea definition;
    @FXML
    ListView<String> conditions;
    @FXML
    TextArea quran;
    @FXML
    TextArea suna;
    @FXML
    TextArea ijtihad;
    @FXML
    Button button1;
    @FXML
    Button button2;
    @FXML
    private TextField conditionField;
    @FXML
    private TextField transactionField1;
    @FXML
    private TextField transactionField2;
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
    PopOver over;
    public static List<IRI> listContracts = new ArrayList();
    public static List<IRI> listConditionsIndividulas = new ArrayList();
    public static List<IRI> listConditions = new ArrayList();
    OWLOntology ontology;
    Reasoner reasoner;
    OWLDataFactory df;
    OWLNamedIndividual mick;
    OWLDataProperty hasname, hasdefinition;
    File file;
    OWLOntologyManager manager;
    boolean booltransactionField1 = false;
    String stringtransactionField1;
    boolean booltransactionField2 = false;
    String stringtransactionField2;
    boolean boollistConditionContent = false;

    boolean booldefinition = false;
    String stringdefinition;
    boolean change = false;
    boolean boolquran = false;
    String stringquran;
    boolean boolsuna = false;
    String stringsuna;
    boolean boolijtihad = false;
    String stringijtihad;
    boolean boollHokm = false;
    String stringHokm;
    Thread t;

    /**
     * Initializes the controller class.
     *
     * @param rb
     */
    @Override
    public void initialize(URL location, ResourceBundle rb) {
        
        boolean admin = false;
        admin = login.isLogin();
        if (!admin) {
            adds.setDisable(true);
            updates.setDisable(true);
            deletes.setDisable(true);
            System.out.println("////////////");
        }
        over=new PopOver();
        
        ach.sceneProperty().addListener(new ChangeListener<Scene>() {

            @Override
            public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
                if (newValue == null) {
                    // not showing...
                } else {

                }
            }

        });
        conditions.setItems(listConditionContent);

        combolist.setItems(list);
        hokme.setItems(itemsHokm);
        definition.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (change) {
                    if (!newValue.equals("")) {
                        if (!stringdefinition.equals(newValue)) {
                            booldefinition = true;
                            button2.setDisable(false);

                        } else {
                            booldefinition = false;
                            if (checkChange()) {
                                button2.setDisable(false);
                            } else {
                                button2.setDisable(true);
                            }
                        }
                    } else {
                        button2.setDisable(true);
                    }
                }
            }

        });
        transactionField1.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (change) {
                    if (!newValue.endsWith("")) {
                        if (!stringtransactionField1.equals(newValue)) {
                            booltransactionField1 = true;
                            button2.setDisable(false);

                        } else {
                            booltransactionField1 = false;
                            if (checkChange()) {
                                button2.setDisable(false);
                            } else {
                                button2.setDisable(true);
                            }
                        }
                    }
                }
            }

        }
        );
        transactionField2.textProperty()
                .addListener(new ChangeListener<String>() {

                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue
                    ) {
                        if (change) {
                            if (!newValue.equals("")) {
                                if (!stringtransactionField2.equals(newValue)) {

                                    booltransactionField2 = true;
                                    button2.setDisable(false);

                                } else {

                                    booltransactionField2 = false;
                                    if (checkChange()) {
                                        button2.setDisable(false);
                                    } else {
                                        button2.setDisable(true);
                                    }
                                }
                            }
                        }
                    }

                }
                );
        quran.textProperty()
                .addListener(new ChangeListener<String>() {

                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue
                    ) {
                        if (change) {
                            if (!newValue.equals("")) {
                                if (!stringquran.equals(newValue)) {

                                    boolquran = true;
                                    button2.setDisable(false);

                                } else {
                                    boolquran = false;
                                    if (checkChange()) {
                                        button2.setDisable(false);
                                    } else {
                                        button2.setDisable(true);
                                    }
                                }
                            } else {
                                button2.setDisable(true);
                            }
                        }
                    }

                }
                );
        suna.textProperty()
                .addListener(new ChangeListener<String>() {

                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue
                    ) {
                        if (change) {
                            if (!newValue.equals("")) {
                                if (!stringsuna.equals(newValue)) {

                                    boolsuna = true;
                                    button2.setDisable(false);

                                } else {
                                    boolsuna = false;
                                    if (checkChange()) {
                                        button2.setDisable(false);
                                    } else {
                                        button2.setDisable(true);
                                    }
                                }
                            } else {
                                button2.setDisable(true);
                            }
                        }
                    }

                }
                );
        ijtihad.textProperty()
                .addListener(new ChangeListener<String>() {

                    @Override
                    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue
                    ) {
                        if (change) {
                            if (!newValue.equals("")) {
                                if (!stringijtihad.equals(newValue)) {

                                    boolijtihad = true;
                                    button2.setDisable(false);

                                } else {
                                    boolijtihad = false;
                                    if (checkChange()) {
                                        button2.setDisable(false);
                                    } else {
                                        button2.setDisable(true);
                                    }
                                }
                            } else {
                                button2.setDisable(true);
                            }
                        }
                    }

                }
                );

        listConditionContent.addListener(
                new ListChangeListener<String>() {

                    @Override
                    public void onChanged(ListChangeListener.Change<? extends String> c
                    ) {

                        if (change) {
                            if (listConditionContent.size() != 0) {
                                if (!listConditionContent.equals(listConditionContent2)) {
                                    boollistConditionContent = true;
                                    button2.setDisable(false);

                                } else {
                                    boollistConditionContent = false;
                                    if (checkChange()) {
                                        button2.setDisable(false);
                                    } else {
                                        button2.setDisable(true);
                                    }
                                }

                            } else {
                                button2.setDisable(true);
                            }
                        }
                    }

                }
        );

        hokme.setOnAction(new EventHandler() {

                    @Override
                    public void handle(Event event
                    ) {
                        if (change) {
                            if (hokme.getSelectionModel().getSelectedItem().equals(stringHokm)) {
                                boollHokm = false;
                            } else {
                                boollHokm = true;

                                if (checkChange()) {
                                    button2.setDisable(false);
                                } else {
                                    button2.setDisable(true);
                                }
                            }
                        }
                    }
                }
        );
        t = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    loadOntology();
                    getClassInstance();
                } catch (Exception ex) {
                }

            }
        });

        t.start();
        change = true;

        button2.setDisable(
                true);

    }

    public void loadOntology() {

        //"/home/fathi/Downloads/ExplorerOWLExample/projetOWL
        ///"/home/fathi/islamic_finance.owl"
        //"/home/fathi/Downloads/Ontology/islamic_finance.owl"
        //file = new File("/home/fathi/ISFO.owl");
        try {
             manager = OntoConnect.manager;
            ontology = OntoConnect.ontology;
            //file = new File("/home/fathi/islamic_finance.owl");
            file = OntoConnect.file;
            df = manager.getOWLDataFactory();
            reasoner = new Reasoner(ontology);
        } catch (Exception ex) {
            System.out.println("123" + ex);
        }

    }

    public void getClassInstance() {
        reasoner = new Reasoner(ontology);
        //mick = df.getOWLNamedIndividual(IRI.create("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#Al-Rahn"));
        hasname = df.getOWLDataProperty(IRI.create("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#hasname"));

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
            //System.out.println("" + e);
        }

    }

    public void saveChanges() {
        if (checkChange()) {
            button2.setDisable(true);
            int i = combolist.getSelectionModel().getSelectedIndex();
            IRI iri = listContracts.get(i);
            System.out.println("changee");
            String latinContractName = iri.toString().replace("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#", "");
            OWLIndividual ShariahEvidence = createIndividual("http://www.semanticweb.org/IslamicFinanceOWL.owl#Shariah_Evidence_Of_" + latinContractName);

            OWLIndividual Sale = createIndividual(iri);

            if (booltransactionField1) {

            }
            if (booltransactionField2) {

            }
            if (booldefinition) {
                System.out.println("definition changed");
                OWLDataProperty hasdefinition = createDataProperty("http://www.semanticweb.org/IslamicFinanceOWL.owl#hasdefinition");
                OWLDataPropertyAssertionAxiom rangeAxiom = df.getOWLDataPropertyAssertionAxiom(hasdefinition, Sale, stringdefinition);
                RemoveAxiom dd = new RemoveAxiom(ontology, rangeAxiom);
                applyChange(dd);

                applyChange(addDataToIndividual(ontology, Sale, hasdefinition, definition.getText()));
            }
            if (boollistConditionContent) {
                OWLIndividual NecessaryCondition = createIndividual("http://www.semanticweb.org/IslamicFinanceOWL.owl#Necessary_Condition_Of_" + latinContractName);

                int cpt = 1;
                for (String conditions : listConditionContent2) {
                    OWLDataProperty hasConditiion1 = createDataProperty("http://www.semanticweb.org/IslamicFinanceOWL.owl#hasCondition" + cpt);
                    OWLDataPropertyAssertionAxiom rangeAxiom = df.getOWLDataPropertyAssertionAxiom(hasConditiion1, NecessaryCondition, conditions);
                    RemoveAxiom dd = new RemoveAxiom(ontology, rangeAxiom);
                    applyChange(dd);

                    cpt++;
                }
                cpt = 1;
                for (String conditions : listConditionContent) {
                    OWLDataProperty hasConditiion1 = createDataProperty("http://www.semanticweb.org/IslamicFinanceOWL.owl#hasCondition" + cpt);
                    OWLDataProperty hasCondition = createDataProperty("http://www.semanticweb.org/IslamicFinanceOWL.owl#hasCondition");
                    applyChange(createSubDataProperty(ontology, hasConditiion1, hasCondition));
                    applyChange(addDataToIndividual(ontology, NecessaryCondition, hasConditiion1, conditions));

                    cpt++;
                }
            }
            if (boollHokm) {
                int j = hokme.getSelectionModel().getSelectedIndex();
                OWLIndividual HokmHarem = createIndividual("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#Haram");
                OWLIndividual HokmHalel = createIndividual("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#Halal");
                if (j == 0) {
                    OWLObjectProperty hasHokm = createObjectProperty("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#hasHokm");
                    OWLObjectPropertyAssertionAxiom objectAssertion = df.getOWLObjectPropertyAssertionAxiom(hasHokm, Sale, HokmHarem);
                    applyChange(new RemoveAxiom(ontology, objectAssertion));
                    addObjectproperty(ontology, Sale, hasHokm, HokmHalel);
                    applyChange(addObjectproperty(ontology, Sale, hasHokm, HokmHalel));
                } else if (j == 1) {
                    OWLObjectProperty hasHokm = createObjectProperty("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#hasHokm");
                    OWLObjectPropertyAssertionAxiom objectAssertion = df.getOWLObjectPropertyAssertionAxiom(hasHokm, Sale, HokmHalel);
                    applyChange(new RemoveAxiom(ontology, objectAssertion));
                    addObjectproperty(ontology, Sale, hasHokm, HokmHalel);
                    applyChange(addObjectproperty(ontology, Sale, hasHokm, HokmHarem));
                }
            }
            if (boolquran) {
                OWLDataProperty hasQuranEvidence = createDataProperty("http://www.semanticweb.org/IslamicFinanceOWL.owl#hasQuranEvidece");
                OWLDataPropertyAssertionAxiom rangeAxiom = df.getOWLDataPropertyAssertionAxiom(hasQuranEvidence, ShariahEvidence, stringquran);
                RemoveAxiom dd = new RemoveAxiom(ontology, rangeAxiom);
                applyChange(dd);
                applyChange(addDataToIndividual(ontology, ShariahEvidence, hasQuranEvidence, quran.getText()));
            }
            if (boolsuna) {
                OWLDataProperty hasSunaEvidence = createDataProperty("http://www.semanticweb.org/IslamicFinanceOWL.owl#hasSunaEvidence");
                OWLDataPropertyAssertionAxiom rangeAxiom = df.getOWLDataPropertyAssertionAxiom(hasSunaEvidence, ShariahEvidence, stringsuna);
                RemoveAxiom dd = new RemoveAxiom(ontology, rangeAxiom);
                applyChange(dd);
                applyChange(addDataToIndividual(ontology, ShariahEvidence, hasSunaEvidence, suna.getText()));
            }
            if (boolijtihad) {
                OWLDataProperty hasIjtihadEvidence = createDataProperty("http://www.semanticweb.org/IslamicFinanceOWL.owl#hasIjtihadEvidence");
                OWLDataPropertyAssertionAxiom rangeAxiom = df.getOWLDataPropertyAssertionAxiom(hasIjtihadEvidence, ShariahEvidence, stringijtihad);
                RemoveAxiom dd = new RemoveAxiom(ontology, rangeAxiom);
                applyChange(dd);
                applyChange(addDataToIndividual(ontology, ShariahEvidence, hasIjtihadEvidence, ijtihad.getText()));
            }
            file = file.getAbsoluteFile();
            RDFXMLOntologyFormat rdfxmlFormat = new RDFXMLOntologyFormat();
            try {
                manager.saveOntology(ontology, rdfxmlFormat, IRI.create(file));
            } catch (OWLOntologyStorageException ex) {
                Logger.getLogger(Screen3Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    public void setContractInformation() {
        change = false;
        int i = combolist.getSelectionModel().getSelectedIndex();
        IRI iri = listContracts.get(i);
        String arab = iri.toString().replace("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#", "");
        transactionField1.setText(list.get(i));
        transactionField2.setText(arab);
        stringtransactionField1 = list.get(i);
        stringtransactionField2 = arab;

        mick = df.getOWLNamedIndividual(iri);
        hasdefinition = df.getOWLDataProperty(IRI.create("http://www.semanticweb.org/IslamicFinanceOWL.owl#hasdefinition"));
        Set<OWLLiteral> dataproperty = reasoner.getDataPropertyValues(mick, hasdefinition);
        // item=combolist.getSelectionModel().getSelectedItem();
        //System.out.println("dff" + dataproperty);
        for (OWLLiteral ind : dataproperty) {
            definition.setText(ind.getLiteral());
            stringdefinition = definition.getText();

        }
        //getCondtionsIdividulList();
        setConditionsInfromation();
        setHokmInformation();
        setEvidenceInformation();
        change = true;
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
        NodeSet<OWLNamedIndividual> ns = reasoner.getObjectPropertyValues(mick, hasLObjectProperty);
        for (org.semanticweb.owlapi.reasoner.Node<OWLNamedIndividual> o : ns) {
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
                Set<OWLLiteral> dataproperty = reasoner.getDataPropertyValues(mick, hasname);
                for (OWLLiteral ind : dataproperty) {
                    //System.out.println(ind.getLiteral());

                    listConditionContent.add(ind.getLiteral());
                    listConditionContent2.add(ind.getLiteral());
                }
            }
        }
    }

    public void getCondtionsIdividulList() {
        //reasoner = new Reasoner(ontology);
        //mick = df.getOWLNamedIndividual(IRI.create("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#Al-Rahn"));
        hasname = df.getOWLDataProperty(IRI.create("http://www.semanticweb.org/fathi/ontologies/2016/10/untitled-ontology-26#hasname"));
        //System.out.println("kkkk");
        try {
            //get specific INDIVIDUAL of class   

            for (OWLClass c : ontology.getClassesInSignature()) {

                if (c.getIRI().getFragment().equals("Conditions")) {

                    NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(c, true);
                  //NodeSet<OWLNamedIndividual> datatype;

                    // System.out.println(c.getIRI().getFragment());
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
                         System.out.println(ind.getLiteral());
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
        NodeSet<OWLNamedIndividual> ns = reasoner.getObjectPropertyValues(mick, hasLObjectProperty);
        for (org.semanticweb.owlapi.reasoner.Node<OWLNamedIndividual> o : ns) {

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
                Set<OWLLiteral> dataproperty = reasoner.getDataPropertyValues(mick, hasname);
                for (OWLLiteral ind : dataproperty) {
                    //System.out.println(ind.getLiteral());

                    if (ind.getLiteral().equals("حلال")) {

                        hokme.setValue(ind.getLiteral());
                        stringHokm = ind.getLiteral();
                    } else if (ind.getLiteral().equals("حرام")) {
                        hokme.setValue(ind.getLiteral());
                        stringHokm = ind.getLiteral();
                    }
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
        NodeSet<OWLNamedIndividual> ns = reasoner.getObjectPropertyValues(mick, hasLObjectProperty);
        for (org.semanticweb.owlapi.reasoner.Node<OWLNamedIndividual> o : ns) {

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
                if (hasname.getIRI().toString().equals("http://www.semanticweb.org/IslamicFinanceOWL.owl#hasQuranEvidece")) {
                    Set<OWLLiteral> dataproperty = reasoner.getDataPropertyValues(mick, hasname);

                    for (OWLLiteral ind : dataproperty) {
                        //System.out.println("123" + ind.getLiteral());

                        quran.setText(ind.getLiteral());
                        stringquran = ind.getLiteral();
                        //listConditionContent.add(ind.getLiteral());
                    }
                } else if (hasname.getIRI().toString().equals("http://www.semanticweb.org/IslamicFinanceOWL.owl#hasSunaEvidence")) {
                    Set<OWLLiteral> dataproperty = reasoner.getDataPropertyValues(mick, hasname);

                    for (OWLLiteral ind : dataproperty) {
                        // System.out.println("123" + ind.getLiteral());

                        suna.setText(ind.getLiteral());
                        stringsuna = ind.getLiteral();
                        //listConditionContent.add(ind.getLiteral());
                    }
                } else if (hasname.getIRI().toString().equals("http://www.semanticweb.org/IslamicFinanceOWL.owl#hasIjtihadEvidence")) {
                    Set<OWLLiteral> dataproperty = reasoner.getDataPropertyValues(mick, hasname);

                    for (OWLLiteral ind : dataproperty) {
                        //  System.out.println("123" + ind.getLiteral());

                        ijtihad.setText(ind.getLiteral());
                        stringijtihad = ind.getLiteral();
                        //listConditionContent.add(ind.getLiteral());
                    }
                }
            }
        }
    }

    @FXML
    public void saveOntology(ActionEvent actionEvent) {
        saveChanges();
        OntoConnect.refresh();

    }

    public boolean checkChange() {
        if ((booldefinition || boolijtihad || boollHokm || boollistConditionContent || boolquran || boolsuna)) {

            return true;
        } else {

            return false;
        }

    }

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
    public void setItemsCondition(ActionEvent evt) {
        String item;
        try {
            item = conditionField.getText();
            if (!item.equals("")) {
                listConditionContent.add(item);

            }
        } catch (Exception e) {

        }
    }

    @FXML
    public void print(ActionEvent evt) {
        int i = -1;
        i = conditions.getSelectionModel().getSelectedIndex();
        if (i == -1) {

        } else {
            listConditionContent.remove(i);

        }

    }

    private void fadeTransition(Node e) {
        FadeTransition x = new FadeTransition(new Duration(2000), e);
        x.setFromValue(0);
        x.setToValue(100);
        x.setCycleCount(1);
        x.setInterpolator(Interpolator.LINEAR);
        x.play();
    }

    public void setItemSelected() {
        String item = conditions.getSelectionModel().getSelectedItem();
        try {
            if (!item.equals("")) {
                conditionField.setText(item);

            }
        } catch (Exception e) {

        }
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

            System.out.println(sdt);
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

    @Override
    public void handle(MouseEvent event) {
       
    }
}
