package com.zsmart.accountingProject.ws.rest.converter;

import com.zsmart.accountingProject.bean.Adherant;
import com.zsmart.accountingProject.service.util.ListUtil;
import com.zsmart.accountingProject.service.util.NumberUtil;
import com.zsmart.accountingProject.service.util.StringUtil;
import com.zsmart.accountingProject.ws.rest.vo.AdherantVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class AdherantConverter extends AbstractConverter<Adherant, AdherantVo> {
    @Autowired
    private UtilisateurConverter utilisateurConverter;
    @Autowired
    private ComptableConverter comptableConverter;
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
    private boolean facture;
    private boolean cpc;
    private boolean utilisateur;
    private boolean client;
    private boolean fournisseur;
    private boolean comptebanquaire;
    private boolean operationComptable;
    private boolean comptable;
    @Override
    public Adherant toItem(AdherantVo vo) {
        if(vo==null) {

            return null;
        }
        else {
            Adherant item = new Adherant();
            if (vo.getId() != null) {
                item.setId(NumberUtil.toLong(vo.getId()));
            }
            if (ListUtil.isNotEmpty(vo.getFacturesVo()) && isFacture()) {
                item.setFactures(factureConverter.toItem(vo.getFacturesVo()));
            }
            if (ListUtil.isNotEmpty(vo.getSocietesVo()) && societe) {
                item.setSocietes(societeConverter.toItem(vo.getSocietesVo()));
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

            if (vo.getComptableVo() != null && isComptable()) {
                item.setComptable(comptableConverter.toItem(vo.getComptableVo()));
            }
            if (StringUtil.isNotEmpty(vo.getEmail())) {
                item.setEmail(vo.getEmail());
            }
            if (StringUtil.isNotEmpty(vo.getNom())) {
                item.setNom(vo.getNom());
            }
            if (StringUtil.isNotEmpty(vo.getPrenom())) {
                item.setPrenom(vo.getPrenom());
            }
            return item;
        }    }

    @Override
    public AdherantVo toVo(Adherant item) {
        if (item == null) {
            return null;
        } else {
            AdherantVo vo = new AdherantVo();
            if (ListUtil.isNotEmpty(item.getFactures()) && isFacture()) {
                vo.setFacturesVo(factureConverter.toVo(item.getFactures()));
            }

            if (ListUtil.isNotEmpty(item.getSocietes()) && societe) {
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

            if (item.getId() != null) {
                vo.setId(NumberUtil.toString(item.getId()));
            }
            if (item.getComptable() != null && isComptable()) {
                vo.setComptableVo(comptableConverter.toVo(item.getComptable()));
            }
            if (StringUtil.isNotEmpty(item.getEmail())) {
                vo.setEmail(item.getEmail());
            }
            if (StringUtil.isNotEmpty(item.getNom())) {
                vo.setNom(item.getNom());
            }
            if (StringUtil.isNotEmpty(item.getPrenom())) {
                vo.setPrenom(item.getPrenom());
            }
            return vo;

        }    }

    public boolean isComptable() {
        return comptable;
    }

    public void setComptable(boolean comptable) {
        this.comptable = comptable;
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

    public boolean isUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(boolean utilisateur) {
        this.utilisateur = utilisateur;
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

    public UtilisateurConverter getUtilisateurConverter() {
        return utilisateurConverter;
    }

    public void setUtilisateurConverter(UtilisateurConverter utilisateurConverter) {
        this.utilisateurConverter = utilisateurConverter;
    }

    public ComptableConverter getComptableConverter() {
        return comptableConverter;
    }

    public void setComptableConverter(ComptableConverter comptableConverter) {
        this.comptableConverter = comptableConverter;
    }

    public SocieteConverter getSocieteConverter() {
        return societeConverter;
    }

    public void setSocieteConverter(SocieteConverter societeConverter) {
        this.societeConverter = societeConverter;
    }

    public FactureConverter getFactureConverter() {
        return factureConverter;
    }

    public void setFactureConverter(FactureConverter factureConverter) {
        this.factureConverter = factureConverter;
    }

    public CpcConverter getCpcConverter() {
        return cpcConverter;
    }

    public void setCpcConverter(CpcConverter cpcConverter) {
        this.cpcConverter = cpcConverter;
    }

    public DeclarationTvaConverter getDeclarationTvaConverter() {
        return declarationTvaConverter;
    }

    public void setDeclarationTvaConverter(DeclarationTvaConverter declarationTvaConverter) {
        this.declarationTvaConverter = declarationTvaConverter;
    }

    public ClientConverter getClientConverter() {
        return clientConverter;
    }

    public void setClientConverter(ClientConverter clientConverter) {
        this.clientConverter = clientConverter;
    }

    public FournisseurConverter getFournisseurConverter() {
        return fournisseurConverter;
    }

    public void setFournisseurConverter(FournisseurConverter fournisseurConverter) {
        this.fournisseurConverter = fournisseurConverter;
    }

    public CompteBanquaireConverter getCompteBanquaireConverter() {
        return compteBanquaireConverter;
    }

    public void setCompteBanquaireConverter(CompteBanquaireConverter compteBanquaireConverter) {
        this.compteBanquaireConverter = compteBanquaireConverter;
    }

    public OperationComptableGroupeConverter getOperationComptableGroupeConverter() {
        return operationComptableGroupeConverter;
    }

    public void setOperationComptableGroupeConverter(OperationComptableGroupeConverter operationComptableGroupeConverter) {
        this.operationComptableGroupeConverter = operationComptableGroupeConverter;
    }

    public void setOperationComptable(boolean operationComptable) {
        this.operationComptable = operationComptable;
    }
}
