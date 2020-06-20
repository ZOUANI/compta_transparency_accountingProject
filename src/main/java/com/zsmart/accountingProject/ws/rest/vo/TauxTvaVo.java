package com.zsmart.accountingProject.ws.rest.vo;

import java.util.List;

public class TauxTvaVo {

    private String id;
    private String libelle;
    private String taux;
    private AdherantVo adherantVo;
    private List<FactureVo> factureVoList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public AdherantVo getAdherantVo() {
        return adherantVo;
    }

    public void setAdherantVo(AdherantVo adherantVo) {
        this.adherantVo = adherantVo;
    }

    public String getTaux() {
        return taux;
    }

    public void setTaux(String taux) {
        this.taux = taux;
    }

    public List<FactureVo> getFactureVoList() {
        return factureVoList;
    }

    public void setFactureVoList(List<FactureVo> factureVoList) {
        this.factureVoList = factureVoList;
    }
}
