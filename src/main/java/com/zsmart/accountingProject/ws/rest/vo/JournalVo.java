package com.zsmart.accountingProject.ws.rest.vo;

import com.zsmart.accountingProject.bean.OperationComptableGroupe;

import java.util.List;

public class JournalVo {

    private String id ;
    private String libele ;
    private List<OperationComptableGroupeVo> operationComptableGroupeVos;
    private String datedebut;
    private String datefin;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibele() {
        return libele;
    }

    public void setLibele(String libele) {
        this.libele = libele;
    }

    public List<OperationComptableGroupeVo> getOperationComptableGroupeVos() {
        return operationComptableGroupeVos;
    }

    public void setOperationComptableGroupeVos(List<OperationComptableGroupeVo> operationComptableGroupeVos) {
        this.operationComptableGroupeVos = operationComptableGroupeVos;
    }

    public String getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(String datedebut) {
        this.datedebut = datedebut;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }
}
