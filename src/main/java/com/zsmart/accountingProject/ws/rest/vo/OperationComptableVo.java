package com.zsmart.accountingProject.ws.rest.vo;

import java.util.ArrayList;
import java.util.List;


public class OperationComptableVo{

private String id ;
private String libelle ;
private String referenceSociete ;
private String referenceFacture ;
private String montant;
private String montantMin ;
private String montantMax ;
private String dateOperationComptable;
private String dateOperationComptableMin ;
private String dateOperationComptableMax ;
private String dateSaisie;
private String dateSaisieMin ;
private String dateSaisieMax ;
private CaisseVo caisseVo ;
private TypeOperationComptableVo typeOperationComptableVo ;
private CompteBanquaireVo compteBanquaireVo ;
private CompteComptableVo compteComptableVo ;
private OperationComptableGroupeVo operationComptableGroupeVo ;
private FactureVo factureVo ;



public String  getId(){
    return id;
}

public void setId(String  id){
     this.id = id;
}

public String  getLibelle(){
    return libelle;
}

public void setLibelle(String  libelle){
     this.libelle = libelle;
}

public String  getReferenceSociete(){
    return referenceSociete;
}

public void setReferenceSociete(String  referenceSociete){
     this.referenceSociete = referenceSociete;
}

public String  getReferenceFacture(){
    return referenceFacture;
}

public void setReferenceFacture(String  referenceFacture){
     this.referenceFacture = referenceFacture;
}

public String  getMontant(){
    return montant;
}

public void setMontant(String  montant){
     this.montant = montant;
}

public String  getMontantMin(){
    return montantMin;
}

public void setMontantMin(String  montantMin){
     this.montantMin = montantMin;
}

public String  getMontantMax(){
    return montantMax;
}

public void setMontantMax(String  montantMax){
     this.montantMax = montantMax;
}

public String  getDateOperationComptable(){
    return dateOperationComptable;
}

public void setDateOperationComptable(String  dateOperationComptable){
     this.dateOperationComptable = dateOperationComptable;
}

public String  getDateOperationComptableMin(){
    return dateOperationComptableMin;
}

public void setDateOperationComptableMin(String  dateOperationComptableMin){
     this.dateOperationComptableMin = dateOperationComptableMin;
}

public String  getDateOperationComptableMax(){
    return dateOperationComptableMax;
}

public void setDateOperationComptableMax(String  dateOperationComptableMax){
     this.dateOperationComptableMax = dateOperationComptableMax;
}

public String  getDateSaisie(){
    return dateSaisie;
}

public void setDateSaisie(String  dateSaisie){
     this.dateSaisie = dateSaisie;
}

public String  getDateSaisieMin(){
    return dateSaisieMin;
}

public void setDateSaisieMin(String  dateSaisieMin){
     this.dateSaisieMin = dateSaisieMin;
}

public String  getDateSaisieMax(){
    return dateSaisieMax;
}

public void setDateSaisieMax(String  dateSaisieMax){
     this.dateSaisieMax = dateSaisieMax;
}

public CaisseVo  getCaisseVo (){
    return caisseVo ;
}

public void setCaisseVo (CaisseVo  caisseVo ){
     this.caisseVo  = caisseVo ;
}

public TypeOperationComptableVo  getTypeOperationComptableVo (){
    return typeOperationComptableVo ;
}

public void setTypeOperationComptableVo (TypeOperationComptableVo  typeOperationComptableVo ){
     this.typeOperationComptableVo  = typeOperationComptableVo ;
}

public CompteBanquaireVo  getCompteBanquaireVo (){
    return compteBanquaireVo ;
}

public void setCompteBanquaireVo (CompteBanquaireVo  compteBanquaireVo ){
     this.compteBanquaireVo  = compteBanquaireVo ;
}

public CompteComptableVo  getCompteComptableVo (){
    return compteComptableVo ;
}

public void setCompteComptableVo (CompteComptableVo  compteComptableVo ){
     this.compteComptableVo  = compteComptableVo ;
}

public OperationComptableGroupeVo  getOperationComptableGroupeVo (){
    return operationComptableGroupeVo ;
}

public void setOperationComptableGroupeVo (OperationComptableGroupeVo  operationComptableGroupeVo ){
     this.operationComptableGroupeVo  = operationComptableGroupeVo ;
}

public FactureVo  getFactureVo (){
    return factureVo ;
}

public void setFactureVo (FactureVo  factureVo ){
     this.factureVo  = factureVo ;
}




}