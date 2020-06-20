package com.zsmart.accountingProject.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
@Entity
public class DeclarationTva  implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(precision = 16, scale = 4)
    private BigDecimal totalTvaGain;
    @Column(precision = 16, scale = 4)
    private BigDecimal totalTvaCharge;
    @Column(precision = 16, scale = 4)
    private BigDecimal cotisation;
    @Column(precision = 16, scale = 4)
    private BigDecimal differenceChargeGain;

    @OneToMany(mappedBy = "declarationTva")
    private List<Facture> facturesGain;
    @OneToMany(mappedBy = "declarationTva")
    private List<Facture> facturescharge;
    @ManyToOne
    private Adherant adherant;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private Societe societe;

    public Adherant getAdherant() {
        return adherant;
    }


    public void setAdherant(Adherant adherant) {
        this.adherant = adherant;
    }

    public DeclarationTva() {
    }

    public List<Facture> getFacturesGain() {
        return facturesGain;
    }

    public void setFacturesGain(List<Facture> facturesGain) {
        this.facturesGain = facturesGain;
    }

    public List<Facture> getFacturescharge() {
        return facturescharge;
    }

    public void setFacturescharge(List<Facture> facturescharge) {
        this.facturescharge = facturescharge;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalTvaGain() {
        return totalTvaGain;
    }

    public void setTotalTvaGain(BigDecimal totalTvaGain) {
        this.totalTvaGain = totalTvaGain;
    }

    public BigDecimal getTotalTvaCharge() {
        return totalTvaCharge;
    }

    public void setTotalTvaCharge(BigDecimal totalTvaCharge) {
        this.totalTvaCharge = totalTvaCharge;
    }

    public BigDecimal getCotisation() {
        return cotisation;
    }

    public void setCotisation(BigDecimal cotisation) {
        this.cotisation = cotisation;
    }

    public BigDecimal getDifferenceChargeGain() {
        return differenceChargeGain;
    }

    public void setDifferenceChargeGain(BigDecimal differenceChargeGain) {
        this.differenceChargeGain = differenceChargeGain;
    }



    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }
}
