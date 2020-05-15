package com.zsmart.accountingProject.ws.rest.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.zsmart.accountingProject.service.util.*;
import com.zsmart.accountingProject.bean.OperationComptable;
import com.zsmart.accountingProject.ws.rest.vo.OperationComptableVo;

@Component
public class OperationComptableConverter extends AbstractConverter<OperationComptable, OperationComptableVo> {

    private boolean caisse;

    @Autowired
    private CaisseConverter caisseConverter;
    private boolean typeOperationComptable;

    @Autowired
    private TypeOperationComptableConverter typeOperationComptableConverter;
    private boolean compteBanquaire;

    @Autowired
    private CompteBanquaireConverter compteBanquaireConverter;
    private boolean compteComptable;

    @Autowired
    private CompteComptableConverter compteComptableConverter;
    private boolean operationComptableGroupe;

    @Autowired
    private OperationComptableGroupeConverter operationComptableGroupeConverter;
    private boolean facture;

    @Autowired
    private FactureConverter factureConverter;
    @Autowired
    private SocieteConverter societeConverter;

    @Override
    public OperationComptable toItem(OperationComptableVo vo) {
        if (vo == null) {
            return null;
        } else {
            OperationComptable item = new OperationComptable();

            if (caisse && vo.getCaisseVo() != null) {
                item.setCaisse(caisseConverter.toItem(vo.getCaisseVo()));
            }

            if (typeOperationComptable && vo.getTypeOperationComptableVo() != null) {
                item.setTypeOperationComptable(typeOperationComptableConverter.toItem(vo.getTypeOperationComptableVo()));
            }

            if (compteBanquaire && vo.getCompteBanquaireVo() != null) {
                item.setCompteBanquaire(compteBanquaireConverter.toItem(vo.getCompteBanquaireVo()));
            }

            if (compteComptable && vo.getCompteComptableVo() != null) {
                item.setCompteComptable(compteComptableConverter.toItem(vo.getCompteComptableVo()));
            }

            if (operationComptableGroupe && vo.getOperationComptableGroupeVo() != null) {
                item.setOperationComptableGroupe(operationComptableGroupeConverter.toItem(vo.getOperationComptableGroupeVo()));
            }

            if (facture && vo.getFactureVo() != null) {
                item.setFacture(factureConverter.toItem(vo.getFactureVo()));
            }

            if (StringUtil.isNotEmpty(vo.getLibelle())) {
                item.setLibelle(vo.getLibelle());
            }

            if (vo.getSocieteVo()!=null) {
                item.setSociete(societeConverter.toItem(vo.getSocieteVo()));
            }

            if (StringUtil.isNotEmpty(vo.getReferenceFacture())) {
                item.setReferenceFacture(vo.getReferenceFacture());
            }

            if (vo.getId() != null) {
                item.setId(NumberUtil.toLong(vo.getId()));
            }

            if (vo.getMontant() != null) {
                item.setMontant(NumberUtil.toBigDecimal(vo.getMontant()));
            }

            if (vo.getDateOperationComptable() != null) {
                item.setDateOperationComptable(DateUtil.parse(vo.getDateOperationComptable()));
            }

            if (vo.getDateSaisie() != null) {
                item.setDateSaisie(DateUtil.parse(vo.getDateSaisie()));
            }

            return item;
        }
    }

    @Override
    public OperationComptableVo toVo(OperationComptable item) {
        if (item == null) {
            return null;
        } else {
            OperationComptableVo vo = new OperationComptableVo();

            if (caisse && item.getCaisse() != null) {
                vo.setCaisseVo(caisseConverter.toVo(item.getCaisse()));
            }

            if (typeOperationComptable && item.getTypeOperationComptable() != null) {
                vo.setTypeOperationComptableVo(typeOperationComptableConverter.toVo(item.getTypeOperationComptable()));
            }

            if (compteBanquaire && item.getCompteBanquaire() != null) {
                vo.setCompteBanquaireVo(compteBanquaireConverter.toVo(item.getCompteBanquaire()));
            }

            if (compteComptable && item.getCompteComptable() != null) {
                vo.setCompteComptableVo(compteComptableConverter.toVo(item.getCompteComptable()));
            }

            if (operationComptableGroupe && item.getOperationComptableGroupe() != null) {
                vo.setOperationComptableGroupeVo(operationComptableGroupeConverter.toVo(item.getOperationComptableGroupe()));
            }

            if (facture && item.getFacture() != null) {
                vo.setFactureVo(factureConverter.toVo(item.getFacture()));
            }

            if (StringUtil.isNotEmpty(item.getLibelle())) {
                vo.setLibelle(item.getLibelle());
            }

            if (item.getSociete()!=null) {
                vo.setSocieteVo(societeConverter.toVo(item.getSociete()));
            }

            if (StringUtil.isNotEmpty(item.getReferenceFacture())) {
                vo.setReferenceFacture(item.getReferenceFacture());
            }

            if (item.getId() != null) {
                vo.setId(NumberUtil.toString(item.getId()));
            }

            if (item.getMontant() != null) {
                vo.setMontant(NumberUtil.toString(item.getMontant()));
            }

            if (item.getDateOperationComptable() != null) {
                vo.setDateOperationComptable(DateUtil.formateDate(item.getDateOperationComptable()));
            }

            if (item.getDateSaisie() != null) {
                vo.setDateSaisie(DateUtil.formateDate(item.getDateSaisie()));
            }

            return vo;
        }
    }

    public void init() {

        caisse = true;

        typeOperationComptable = true;

        compteBanquaire = true;

        compteComptable = true;

        operationComptableGroupe = true;

        facture = true;
    }

    public boolean isCaisse() {
        return caisse;
    }

    public void setCaisse(boolean caisse) {
        this.caisse = caisse;
    }

    public CaisseConverter getCaisseConverter() {
        return caisseConverter;
    }

    public void setCaisseConverter(CaisseConverter caisseConverter) {
        this.caisseConverter = caisseConverter;
    }

    public boolean isTypeOperationComptable() {
        return typeOperationComptable;
    }

    public void setTypeOperationComptable(boolean typeOperationComptable) {
        this.typeOperationComptable = typeOperationComptable;
    }

    public TypeOperationComptableConverter getTypeOperationComptableConverter() {
        return typeOperationComptableConverter;
    }

    public void setTypeOperationComptableConverter(TypeOperationComptableConverter typeOperationComptableConverter) {
        this.typeOperationComptableConverter = typeOperationComptableConverter;
    }

    public boolean isCompteBanquaire() {
        return compteBanquaire;
    }

    public void setCompteBanquaire(boolean compteBanquaire) {
        this.compteBanquaire = compteBanquaire;
    }

    public CompteBanquaireConverter getCompteBanquaireConverter() {
        return compteBanquaireConverter;
    }

    public void setCompteBanquaireConverter(CompteBanquaireConverter compteBanquaireConverter) {
        this.compteBanquaireConverter = compteBanquaireConverter;
    }

    public boolean isCompteComptable() {
        return compteComptable;
    }

    public void setCompteComptable(boolean compteComptable) {
        this.compteComptable = compteComptable;
    }

    public CompteComptableConverter getCompteComptableConverter() {
        return compteComptableConverter;
    }

    public void setCompteComptableConverter(CompteComptableConverter compteComptableConverter) {
        this.compteComptableConverter = compteComptableConverter;
    }

    public boolean isOperationComptableGroupe() {
        return operationComptableGroupe;
    }

    public void setOperationComptableGroupe(boolean operationComptableGroupe) {
        this.operationComptableGroupe = operationComptableGroupe;
    }

    public OperationComptableGroupeConverter getOperationComptableGroupeConverter() {
        return operationComptableGroupeConverter;
    }

    public void setOperationComptableGroupeConverter(OperationComptableGroupeConverter operationComptableGroupeConverter) {
        this.operationComptableGroupeConverter = operationComptableGroupeConverter;
    }

    public boolean isFacture() {
        return facture;
    }

    public void setFacture(boolean facture) {
        this.facture = facture;
    }

    public FactureConverter getFactureConverter() {
        return factureConverter;
    }

    public void setFactureConverter(FactureConverter factureConverter) {
        this.factureConverter = factureConverter;
    }
}
