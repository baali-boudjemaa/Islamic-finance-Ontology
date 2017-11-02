/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fatw;

import Case.cas;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author fathi
 */
@Entity
@Table(name = "fatwa")
public class fatwa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;//il faaut yarja3 kima kan id

    @Column(name = "hokm")
    private String hokm;

    @Column(name = "quren")
    private String quren;

    @Column(name = "suna")
    private String suna;

    @Column(name = "ijtihad")
    private String ijtihad;

    

  

    public fatwa(String hokm, String quren, String suna, String ijtihad) {
        //this.id=id;
        this.hokm = hokm;
        this.quren = quren;
        this.suna = suna;
        this.ijtihad = ijtihad;
    }

    public fatwa(Integer id, String hokm, String quren, String suna, String ijtihad) {
        this.id = id;
        this.hokm = hokm;
        this.quren = quren;
        this.suna = suna;
        this.ijtihad = ijtihad;
    }

    public fatwa() {

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
     * @return the hokm
     */
    public String getHokm() {
        return hokm;
    }

    /**
     * @param hokm the hokm to set
     */
    public void setHokm(String hokm) {
        this.hokm = hokm;
    }

    /**
     * @return the quren
     */
    public String getQuren() {
        return quren;
    }

    /**
     * @param quren the quren to set
     */
    public void setQuren(String quren) {
        this.quren = quren;
    }

    /**
     * @return the suna
     */
    public String getSuna() {
        return suna;
    }

    /**
     * @param suna the suna to set
     */
    public void setSuna(String suna) {
        this.suna = suna;
    }

    /**
     * @return the ijtihad
     */
    public String getIjtihad() {
        return ijtihad;
    }

    /**
     * @param ijtihad the ijtihad to set
     */
    public void setIjtihad(String ijtihad) {
        this.ijtihad = ijtihad;
    }

    @Override
    public String toString() {
        return " fatwa [ id=" + id + ", hokm=" + hokm + ", quren=" + quren + ", suna=" + suna + " , ijtihad=" + ijtihad + "]";
    }

    /**
     * @return the cas
     */
    

    /**
     * @param cas the cas to set
     */
   
}
