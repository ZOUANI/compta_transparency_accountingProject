package com.zsmart.accountingProject.ws.rest.vo;

import java.util.ArrayList;
import java.util.List;


public class FactureVo {

    private String serialVersionUID;
    private String serialVersionUIDMin;
    private String serialVersionUIDMax;
    private String id;
    private String reference;
    private String typeFacture;
    private String scanPath;
    private String annee;
    private String anneeMin;
    private String anneeMax;
    private String mois;
    private String moisMin;
    private String moisMax;
    private String trimester;
    private String trimesterMin;
    private String trimesterMax;
    private String totalHt;
    private String totalHtMin;
    private String totalHtMax;
    private String totalTtc;
    private String totalTtcMin;
    private String totalTtcMax;
    private String tva;
    private String tauxTva;
    private String totalDebit;
    private String totalCredit;
    private String totalFactureItems;
    private String tvaMin;
    private String tvaMax;
    private String totalPayerHt;
    private String totalPayerHtMin;
    private String totalPayerHtMax;
    private String totalRestantHt;
    private String totalRestantHtMin;
    private String totalRestantHtMax;
    private String dateFacture;
    private String dateFactureMin;
    private String dateFactureMax;
    private String dateSaisie;
    private String dateSaisieMin;
    private String dateSaisieMax;
    private SocieteVo societeVo;
    private Boolean traiter;
    private EtatFactureVo etatFactureVo;
    private List<PaiementFactureVo> paiementFacturesVo;
    private List<OperationComptableVo> operationComptablesVo;
    private List<FactureItemVo> factureItemsVo;
    private DeclarationTvaVo declarationTvaVo;
    private AdherantVo adherantVo;
    private ComptableVo comptableVo;

    public AdherantVo getAdherantVo() {
        return adherantVo;
    }

    public void setAdherantVo(AdherantVo adherantVo) {
        this.adherantVo = adherantVo;
    }

    public ComptableVo getComptableVo() {
        return comptableVo;
    }

    public void setComptableVo(ComptableVo comptableVo) {
        this.comptableVo = comptableVo;
    }

    public Boolean getTraiter() {
        return traiter;
    }

    public void setTraiter(Boolean traiter) {
        this.traiter = traiter;
    }
    public DeclarationTvaVo getDeclarationTvaVo() {
        return declarationTvaVo;
    }

    public void setDeclarationTvaVo(DeclarationTvaVo declarationTvaVo) {
        this.declarationTvaVo = declarationTvaVo;
    }

    public String getTotalFactureItems() {
        return totalFactureItems;
    }

    public void setTotalFactureItems(String totalFactureItems) {
        this.totalFactureItems = totalFactureItems;
    }

    public String getScanPath() {
        return scanPath;
    }

    public void setScanPath(String scanPath) {
        this.scanPath = scanPath;
    }

    public String getTauxTva() {
        return tauxTva;
    }

    public void setTauxTva(String tauxTva) {
        this.tauxTva = tauxTva;
    }

    public String getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(String totalDebit) {
        this.totalDebit = totalDebit;
    }

    public String getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(String totalCredit) {
        this.totalCredit = totalCredit;
    }

    public String getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setSerialVersionUID(String serialVersionUID) {
        this.serialVersionUID = serialVersionUID;
    }

    public String getSerialVersionUIDMin() {
        return serialVersionUIDMin;
    }

    public void setSerialVersionUIDMin(String serialVersionUIDMin) {
        this.serialVersionUIDMin = serialVersionUIDMin;
    }

    public String getSerialVersionUIDMax() {
        return serialVersionUIDMax;
    }

    public void setSerialVersionUIDMax(String serialVersionUIDMax) {
        this.serialVersionUIDMax = serialVersionUIDMax;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getTypeFacture() {
        return typeFacture;
    }

    public void setTypeFacture(String typeFacture) {
        this.typeFacture = typeFacture;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getAnneeMin() {
        return anneeMin;
    }

    public void setAnneeMin(String anneeMin) {
        this.anneeMin = anneeMin;
    }

    public String getAnneeMax() {
        return anneeMax;
    }

    public void setAnneeMax(String anneeMax) {
        this.anneeMax = anneeMax;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public String getMoisMin() {
        return moisMin;
    }

    public void setMoisMin(String moisMin) {
        this.moisMin = moisMin;
    }

    public String getMoisMax() {
        return moisMax;
    }

    public void setMoisMax(String moisMax) {
        this.moisMax = moisMax;
    }

    public String getTrimester() {
        return trimester;
    }

    public void setTrimester(String trimester) {
        this.trimester = trimester;
    }

    public String getTrimesterMin() {
        return trimesterMin;
    }

    public void setTrimesterMin(String trimesterMin) {
        this.trimesterMin = trimesterMin;
    }

    public String getTrimesterMax() {
        return trimesterMax;
    }

    public void setTrimesterMax(String trimesterMax) {
        this.trimesterMax = trimesterMax;
    }

    public String getTotalHt() {
        return totalHt;
    }

    public void setTotalHt(String totalHt) {
        this.totalHt = totalHt;
    }

    public String getTotalHtMin() {
        return totalHtMin;
    }

    public void setTotalHtMin(String totalHtMin) {
        this.totalHtMin = totalHtMin;
    }

    public String getTotalHtMax() {
        return totalHtMax;
    }

    public void setTotalHtMax(String totalHtMax) {
        this.totalHtMax = totalHtMax;
    }

    public String getTotalTtc() {
        return totalTtc;
    }

    public void setTotalTtc(String totalTtc) {
        this.totalTtc = totalTtc;
    }

    public String getTotalTtcMin() {
        return totalTtcMin;
    }

    public void setTotalTtcMin(String totalTtcMin) {
        this.totalTtcMin = totalTtcMin;
    }

    public String getTotalTtcMax() {
        return totalTtcMax;
    }

    public void setTotalTtcMax(String totalTtcMax) {
        this.totalTtcMax = totalTtcMax;
    }

    public String getTva() {
        return tva;
    }

    public void setTva(String tva) {
        this.tva = tva;
    }

    public String getTvaMin() {
        return tvaMin;
    }

    public void setTvaMin(String tvaMin) {
        this.tvaMin = tvaMin;
    }

    public String getTvaMax() {
        return tvaMax;
    }

    public void setTvaMax(String tvaMax) {
        this.tvaMax = tvaMax;
    }

    public String getTotalPayerHt() {
        return totalPayerHt;
    }

    public void setTotalPayerHt(String totalPayerHt) {
        this.totalPayerHt = totalPayerHt;
    }

    public String getTotalPayerHtMin() {
        return totalPayerHtMin;
    }

    public void setTotalPayerHtMin(String totalPayerHtMin) {
        this.totalPayerHtMin = totalPayerHtMin;
    }

    public String getTotalPayerHtMax() {
        return totalPayerHtMax;
    }

    public void setTotalPayerHtMax(String totalPayerHtMax) {
        this.totalPayerHtMax = totalPayerHtMax;
    }

    public String getTotalRestantHt() {
        return totalRestantHt;
    }

    public void setTotalRestantHt(String totalRestantHt) {
        this.totalRestantHt = totalRestantHt;
    }

    public String getTotalRestantHtMin() {
        return totalRestantHtMin;
    }

    public void setTotalRestantHtMin(String totalRestantHtMin) {
        this.totalRestantHtMin = totalRestantHtMin;
    }

    public String getTotalRestantHtMax() {
        return totalRestantHtMax;
    }

    public void setTotalRestantHtMax(String totalRestantHtMax) {
        this.totalRestantHtMax = totalRestantHtMax;
    }

    public String getDateFacture() {
        return dateFacture;
    }

    public void setDateFacture(String dateFacture) {
        this.dateFacture = dateFacture;
    }

    public String getDateFactureMin() {
        return dateFactureMin;
    }

    public void setDateFactureMin(String dateFactureMin) {
        this.dateFactureMin = dateFactureMin;
    }

    public String getDateFactureMax() {
        return dateFactureMax;
    }

    public void setDateFactureMax(String dateFactureMax) {
        this.dateFactureMax = dateFactureMax;
    }

    public String getDateSaisie() {
        return dateSaisie;
    }

    public void setDateSaisie(String dateSaisie) {
        this.dateSaisie = dateSaisie;
    }

    public String getDateSaisieMin() {
        return dateSaisieMin;
    }

    public void setDateSaisieMin(String dateSaisieMin) {
        this.dateSaisieMin = dateSaisieMin;
    }

    public String getDateSaisieMax() {
        return dateSaisieMax;
    }

    public void setDateSaisieMax(String dateSaisieMax) {
        this.dateSaisieMax = dateSaisieMax;
    }

    public SocieteVo getSocieteVo() {
        return societeVo;
    }

    public void setSocieteVo(SocieteVo societeVo) {
        this.societeVo = societeVo;
    }

    public EtatFactureVo getEtatFactureVo() {
        return etatFactureVo;
    }

    public void setEtatFactureVo(EtatFactureVo etatFactureVo) {
        this.etatFactureVo = etatFactureVo;
    }

    public List<PaiementFactureVo> getPaiementFacturesVo() {
        return paiementFacturesVo;
    }

    public void setPaiementFacturesVo(List<PaiementFactureVo> paiementFacturesVo) {
        this.paiementFacturesVo = paiementFacturesVo;
    }

    public List<OperationComptableVo> getOperationComptablesVo() {
        return operationComptablesVo;
    }

    public void setOperationComptablesVo(List<OperationComptableVo> operationComptablesVo) {
        this.operationComptablesVo = operationComptablesVo;
    }

    public List<FactureItemVo> getFactureItemsVo() {
        return factureItemsVo;
    }

    public void setFactureItemsVo(List<FactureItemVo> factureItemsVo) {
        this.factureItemsVo = factureItemsVo;
    }


}