package com.zsmart.accountingProject.ws.rest.vo;

import com.zsmart.accountingProject.bean.Journal;

import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;


public class OperationComptableGroupeVo{

    private String id ;
    private String libelle ;
    private String code ;
    private List<OperationComptableVo> operationComptablesVo;
    private String dateSaisie;
    private AdherantVo adherantVo;
    private ComptableVo comptableVo;
    @ManyToOne
    private JournalVo journalVo;

    public String getDateSaisie() {
        return dateSaisie;
    }

    public void setDateSaisie(String dateSaisie) {
        this.dateSaisie = dateSaisie;
    }

    public JournalVo getJournalVo() {
        return journalVo;
    }

    public void setJournalVo(JournalVo journalVo) {
        this.journalVo = journalVo;
    }

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

public String  getCode(){
    return code;
}

public void setCode(String  code){
     this.code = code;
}

public List<OperationComptableVo> getOperationComptablesVo(){
    return operationComptablesVo;
}

public void setOperationComptablesVo(List<OperationComptableVo> operationComptablesVo){
     this.operationComptablesVo = operationComptablesVo;
}




}