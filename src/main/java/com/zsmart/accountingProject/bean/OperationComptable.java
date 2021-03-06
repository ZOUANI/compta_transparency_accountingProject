package com.zsmart.accountingProject.bean;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class OperationComptable implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String libelle;
    @ManyToOne
    private Societe societe;
    private String referenceFacture;
    @Column(precision = 16, scale = 4)
    private BigDecimal montant;
   
    @Temporal(TemporalType.DATE)
    private Date dateOperationComptable;
    @Temporal(TemporalType.DATE)
    private Date dateSaisie;
    @ManyToOne
    private Caisse caisse;
    @ManyToOne
    private TypeOperationComptable typeOperationComptable;
    @ManyToOne
    private CompteBanquaire compteBanquaire;
    @ManyToOne
    private CompteComptable compteComptable;
    @ManyToOne
    private OperationComptableGroupe operationComptableGroupe;
    @ManyToOne
    private Facture facture;
    @ManyToOne
    private Adherant adherant;

    public OperationComptable() {
    }

    public OperationComptable(BigDecimal montant, CompteComptable compteComptable) {
        this.montant = montant;
        this.compteComptable = compteComptable;
    }

    public Adherant getAdherant() {
        return adherant;
    }

    public void setAdherant(Adherant adherant) {
        this.adherant = adherant;
    }

    public OperationComptableGroupe getOperationComptableGroupe() {
        return operationComptableGroupe;
    }

    public void setOperationComptableGroupe(OperationComptableGroupe operationComptableGroupe) {
        this.operationComptableGroupe = operationComptableGroupe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public String getReferenceFacture() {
        return referenceFacture;
    }

    public void setReferenceFacture(String referenceFacture) {
        this.referenceFacture = referenceFacture;
    }


    public BigDecimal getMontant() {
		return montant;
	}

	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}

	public TypeOperationComptable getTypeOperationComptable() {
		return typeOperationComptable;
	}

	public void setTypeOperationComptable(TypeOperationComptable typeOperationComptable) {
		this.typeOperationComptable = typeOperationComptable;
	}

	public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public Date getDateOperationComptable() {
        return dateOperationComptable;
    }

    public void setDateOperationComptable(Date dateOperationComptable) {
        this.dateOperationComptable = dateOperationComptable;
    }

    public Date getDateSaisie() {
        return dateSaisie;
    }

    public void setDateSaisie(Date dateSaisie) {
        this.dateSaisie = dateSaisie;
    }

    public Caisse getCaisse() {
        return caisse;
    }

    public void setCaisse(Caisse caisse) {
        this.caisse = caisse;
    }

    public CompteBanquaire getCompteBanquaire() {
        return compteBanquaire;
    }

    public void setCompteBanquaire(CompteBanquaire compteBanquaire) {
        this.compteBanquaire = compteBanquaire;
    }

    public CompteComptable getCompteComptable() {
        return compteComptable;
    }

    public void setCompteComptable(CompteComptable compteComptable) {
        this.compteComptable = compteComptable;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        OperationComptable other = (OperationComptable) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    

   
}
