package com.zsmart.accountingProject.ws.rest.vo;


import java.util.List;

public class AdherantVo extends UtilisateurVo {
    private String id;
    private List<SocieteVo> societesVo;
    private List<FactureVo> facturesVo;
    private List<CpcVo> cpcsVo;
    private List<DeclarationTvaVo> declarationTvasVo;
    private List<ClientVo> clientsVo;
    private List<FournisseurVo> fournisseursVo;
    private List<CompteBanquaireVo> compteBanquairesVo;
    private List<OperationComptableGroupeVo> operationComptableGroupesVo;
    private ComptableVo comptableVo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SocieteVo> getSocietesVo() {
        return societesVo;
    }

    public void setSocietesVo(List<SocieteVo> societesVo) {
        this.societesVo = societesVo;
    }

    public List<FactureVo> getFacturesVo() {
        return facturesVo;
    }

    public void setFacturesVo(List<FactureVo> facturesVo) {
        this.facturesVo = facturesVo;
    }

    public List<CpcVo> getCpcsVo() {
        return cpcsVo;
    }

    public void setCpcsVo(List<CpcVo> cpcsVo) {
        this.cpcsVo = cpcsVo;
    }

    public List<DeclarationTvaVo> getDeclarationTvasVo() {
        return declarationTvasVo;
    }

    public void setDeclarationTvasVo(List<DeclarationTvaVo> declarationTvasVo) {
        this.declarationTvasVo = declarationTvasVo;
    }

    public List<ClientVo> getClientsVo() {
        return clientsVo;
    }

    public void setClientsVo(List<ClientVo> clientsVo) {
        this.clientsVo = clientsVo;
    }

    public List<FournisseurVo> getFournisseursVo() {
        return fournisseursVo;
    }

    public void setFournisseursVo(List<FournisseurVo> fournisseursVo) {
        this.fournisseursVo = fournisseursVo;
    }

    public List<CompteBanquaireVo> getCompteBanquairesVo() {
        return compteBanquairesVo;
    }

    public void setCompteBanquairesVo(List<CompteBanquaireVo> compteBanquairesVo) {
        this.compteBanquairesVo = compteBanquairesVo;
    }

    public List<OperationComptableGroupeVo> getOperationComptableGroupesVo() {
        return operationComptableGroupesVo;
    }

    public void setOperationComptableGroupesVo(List<OperationComptableGroupeVo> operationComptableGroupesVo) {
        this.operationComptableGroupesVo = operationComptableGroupesVo;
    }


    public ComptableVo getComptableVo() {
        return comptableVo;
    }

    public void setComptableVo(ComptableVo comptableVo) {
        this.comptableVo = comptableVo;
    }
}
