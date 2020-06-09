package com.zsmart.accountingProject.ws.rest.converter;

import com.zsmart.accountingProject.bean.Comptable;
import com.zsmart.accountingProject.bean.Utilisateur;
import com.zsmart.accountingProject.service.util.ListUtil;
import com.zsmart.accountingProject.service.util.NumberUtil;
import com.zsmart.accountingProject.ws.rest.vo.ComptableVo;
import com.zsmart.accountingProject.ws.rest.vo.FactureVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class ComptableConverter extends AbstractConverter<Comptable, ComptableVo> {
    @Autowired
    private UtilisateurConverter utilisateurConverter;
    @Autowired
    private AdherantConverter adherantConverter;
    @Autowired
    private SocieteConverter societeConverter;
    @Autowired
    private FactureConverter factureConverter;
    @Autowired
    private CpcConverter cpcConverter;
    @Autowired
    private DeclarationTvaConverter declarationTvaConverter;
    @Autowired
    private ClientConverter clientConverter;
    @Autowired
    private FournisseurConverter fournisseurConverter;
    @Autowired
    private CompteBanquaireConverter compteBanquaireConverter;
    @Autowired
    private OperationComptableGroupeConverter operationComptableGroupeConverter;
    private boolean societe;
    private boolean declarationtva;
    private boolean adherant;
    private boolean facture;
    private boolean cpc;
    private boolean utilisateur;
    private boolean client;
    private boolean fournisseur;
    private boolean comptebanquaire;
    private boolean operationComptable;
    @Override
    public Comptable toItem(ComptableVo vo) {
        if(vo==null) {

            return null;
        }
        else {
            Comptable item = new Comptable();
            if (vo.getId() != null) {
                item.setId(NumberUtil.toLong(vo.getId()));
            }
            if (ListUtil.isNotEmpty(vo.getFacturesVo()) && isFacture()) {
                item.setFactures(factureConverter.toItem(vo.getFacturesVo()));
            }
            if (ListUtil.isNotEmpty(vo.getSocietesVo()) && isSociete()) {
                item.setSocietes(societeConverter.toItem(vo.getSocietesVo()));
            }
            if (ListUtil.isNotEmpty(vo.getAdherantsVo()) && isAdherant()) {
                item.setAdherants(adherantConverter.toItem(vo.getAdherantsVo()));
            }
            if (ListUtil.isNotEmpty(vo.getDeclarationTvasVo()) && isDeclarationtva()) {
                item.setDeclarationTvas(declarationTvaConverter.toItem(vo.getDeclarationTvasVo()));
            }
            if (ListUtil.isNotEmpty(vo.getCpcsVo()) && isCpc()) {
                item.setCpcs(cpcConverter.toItem(vo.getCpcsVo()));
            }
            if (ListUtil.isNotEmpty(vo.getClientsVo()) && isClient()) {
                item.setClients(clientConverter.toItem(vo.getClientsVo()));
            }
            if (ListUtil.isNotEmpty(vo.getFournisseursVo()) && isFournisseur()) {
                item.setFournisseurs(fournisseurConverter.toItem(vo.getFournisseursVo()));
            }
            if (ListUtil.isNotEmpty(vo.getCompteBanquairesVo()) && isComptebanquaire()) {
                item.setCompteBanquaires(compteBanquaireConverter.toItem(vo.getCompteBanquairesVo()));
            }
            if (ListUtil.isNotEmpty(vo.getOperationComptableGroupesVo()) && isOperationComptable()) {
                item.setOperationComptableGroupes(operationComptableGroupeConverter.toItem(vo.getOperationComptableGroupesVo()));
            }
            if (vo.getUtilisateurVo()!=null && isUtilisateur()) {
                item.setUtilisateur(utilisateurConverter.toItem(vo.getUtilisateurVo()));
            }
            return item;
        }

    }

    @Override
    public ComptableVo toVo(Comptable item) {
        if (item == null) {
            return null;
        } else {
            ComptableVo vo = new ComptableVo();
            if (ListUtil.isNotEmpty(item.getFactures()) && isFacture()) {
                vo.setFacturesVo(factureConverter.toVo(item.getFactures()));
            }
            if (ListUtil.isNotEmpty(item.getAdherants()) && isAdherant()) {
                vo.setAdherantsVo(adherantConverter.toVo(item.getAdherants()));
            }
            if (ListUtil.isNotEmpty(item.getSocietes()) && isSociete()) {
                vo.setSocietesVo(societeConverter.toVo(item.getSocietes()));
            }
            if (ListUtil.isNotEmpty(item.getCpcs()) && isCpc()) {
                vo.setCpcsVo(cpcConverter.toVo(item.getCpcs()));
            }
            if (ListUtil.isNotEmpty(item.getDeclarationTvas()) && isDeclarationtva()) {
                vo.setDeclarationTvasVo(declarationTvaConverter.toVo(item.getDeclarationTvas()));
            }
            if (ListUtil.isNotEmpty(item.getClients()) && isClient()) {
                vo.setClientsVo(clientConverter.toVo(item.getClients()));
            }
            if (ListUtil.isNotEmpty(item.getFournisseurs()) && isFournisseur()) {
                vo.setFournisseursVo(fournisseurConverter.toVo(item.getFournisseurs()));
            }
            if (ListUtil.isNotEmpty(item.getCompteBanquaires()) && isComptebanquaire()) {
                vo.setCompteBanquairesVo(compteBanquaireConverter.toVo(item.getCompteBanquaires()));
            }
            if (ListUtil.isNotEmpty(item.getOperationComptableGroupes()) && isOperationComptable()) {
                vo.setOperationComptableGroupesVo(operationComptableGroupeConverter.toVo(item.getOperationComptableGroupes()));
            }
            if (item.getUtilisateur()!=null && isUtilisateur()) {
                vo.setUtilisateurVo(utilisateurConverter.toVo(item.getUtilisateur()));
            }
            if (item.getId() != null) {
                vo.setId(NumberUtil.toString(item.getId()));
            }
            return vo;

        }

    }

    public boolean isUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(boolean utilisateur) {
        this.utilisateur = utilisateur;
    }

    public boolean isSociete() {
        return societe;
    }

    public void setSociete(boolean societe) {
        this.societe = societe;
    }

    public boolean isDeclarationtva() {
        return declarationtva;
    }

    public void setDeclarationtva(boolean declarationtva) {
        this.declarationtva = declarationtva;
    }

    public boolean isClient() {
        return client;
    }

    public void setClient(boolean client) {
        this.client = client;
    }

    public boolean isFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(boolean fournisseur) {
        this.fournisseur = fournisseur;
    }

    public boolean isComptebanquaire() {
        return comptebanquaire;
    }

    public void setComptebanquaire(boolean comptebanquaire) {
        this.comptebanquaire = comptebanquaire;
    }

    public boolean isOperationComptable() {
        return operationComptable;
    }

    public void setOperationComptable(boolean operationComptable) {
        this.operationComptable = operationComptable;
    }

    public boolean isAdherant() {
        return adherant;
    }

    public void setAdherant(boolean asherant) {
        this.adherant = asherant;
    }

    public boolean isFacture() {
        return facture;
    }

    public void setFacture(boolean facture) {
        this.facture = facture;
    }

    public boolean isCpc() {
        return cpc;
    }

    public void setCpc(boolean cpc) {
        this.cpc = cpc;
    }
}
