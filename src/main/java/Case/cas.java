/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Case;

import Fatw.fatwa;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Parameter;
import static org.omg.CORBA.AnySeqHelper.insert;
import static org.omg.CORBA.PolicyListHelper.insert;

/**
 *
 * @author fathi
 */
@Entity
@Table(name = "cas")
public class cas implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "act")
    private String act;
    @Column(name = "benifit")
    private String benifit;

    @Column(name = "transactio")
    private String transactio;

    @Column(name = "inde", insertable = false, updatable = false)
    private Integer inde;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inde")
    private fatwa ftw;

    public cas(String action, String benifit, String transaction, Integer index, fatwa ftw) {

        this.act = action;
        this.benifit = benifit;
        this.transactio = transaction;
        this.inde = index;
        this.ftw = ftw;
    }

    public cas(int id, String action, String benifit, String transaction) {
        this.id = id;
        this.act = action;
        this.benifit = benifit;
        this.transactio = transaction;
        //this.inde = inde;
    }

    public cas(String action, String benifit, String transaction) {

        this.act = action;
        this.benifit = benifit;
        this.transactio = transaction;
        //this.inde = inde;
    }

    public cas(String action, String benifit, String transaction, Integer index) {

        this.act = action;
        this.benifit = benifit;
        this.transactio = transaction;
        this.inde = index;
    }

    public cas() {

    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the act
     */
    public String getAction() {
        return act;
    }

    /**
     * @param action the act to set
     */
    public void setAction(String action) {
        this.act = action;
    }

    /**
     * @return the benifit
     */
    public String getBenifit() {
        return benifit;
    }

    /**
     * @param benifit the benifit to set
     */
    public void setBenifit(String benifit) {
        this.benifit = benifit;
    }

    /**
     * @return the transactio
     */
    public String getTransaction() {
        return transactio;
    }

    /**
     * @param transaction the transactio to set
     */
    public void setTransaction(String transaction) {
        this.transactio = transaction;
    }

    /**
     * @return the index
     */
    /* public Integer getIndex() {
     return inde;
     }

     /**
     * @param index the index to set
     */
    /*
     public void setIndex(Integer index) {
     this.inde = index;
     }
     */
    @Override
     public String toString() {
     return " Case [ id=" + id + ", action=" + act + ", benifit=" + benifit + ", transaction=" + getTransaction() + ", index=" + getInde() + "]";
     }
    /**
     * @return the ftw
     */
    public fatwa getFtw() {
        return ftw;
    }

    /**
     * @param ftw the ftw to set
     */
    public void setFtw(fatwa ftw) {
        this.ftw = ftw;
    }

    /**
     * @return the inde
     */
    public Integer getInde() {
        return inde;
    }

    /**
     * @param inde the inde to set
     */
    public void setInde(Integer inde) {
        this.inde = inde;
    }

}
