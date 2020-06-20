package com.zsmart.accountingProject.ws.rest.converter;

import com.zsmart.accountingProject.bean.PaiementFacture;
import com.zsmart.accountingProject.service.util.DateUtil;
import com.zsmart.accountingProject.service.util.NumberUtil;
import com.zsmart.accountingProject.service.util.StringUtil;
import com.zsmart.accountingProject.ws.rest.vo.PaiementFactureVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaiementFactureConverter extends AbstractConverter<PaiementFacture, PaiementFactureVo> {

    private boolean typePaiment;

    @Autowired
    private TypePaiementConverter typePaiementConverter;
    private boolean facture;
    private boolean caisse;
    private boolean compteBanquaire;
    private boolean operationComptable;

    @Autowired
    private FactureConverter factureConverter;
    @Autowired
    private CaisseConverter caisseConverter;
    @Autowired
    private CompteBanquaireConverter compteBanquaireConverter;
    @Autowired
    private OperationComptableConverter operationComptableConverter;

    @Override
    public PaiementFacture toItem(PaiementFactureVo vo) {
        if (vo == null) {
            return null;
        } else {
            PaiementFacture item = new PaiementFacture();

            if (typePaiment && vo.getTypePaimentVo() != null) {
                item.setTypePaiment(typePaiementConverter.toItem(vo.getTypePaimentVo()));
            }

            if (facture && vo.getFactureVo() != null) {
                item.setFacture(factureConverter.toItem(vo.getFactureVo()));
            }
            if (caisse && vo.getCaisseVo() != null) {
                item.setCaisse(caisseConverter.toItem(vo.getCaisseVo()));
            }
            if (compteBanquaire && vo.getCompteBanquaireVo() != null) {
                item.setCompteBanquaire(compteBanquaireConverter.toItem(vo.getCompteBanquaireVo()));
            }
            if (operationComptable && vo.getOperationComptableVo() != null) {
                item.setOperationComptable(operationComptableConverter.toItem(vo.getOperationComptableVo()));
            }

            if (StringUtil.isNotEmpty(vo.getDescription())) {
                item.setDescription(vo.getDescription());
            }

            if (StringUtil.isNotEmpty(vo.getScan())) {
                item.setScan(vo.getScan());
            }

            if (vo.getId() != null) {
                item.setId(NumberUtil.toLong(vo.getId()));
            }

            if (vo.getDatePaiment() != null) {
                item.setDatePaiment(DateUtil.parse(vo.getDatePaiment()));
            }

            if (vo.getDateSaisie() != null) {
                item.setDateSaisie(DateUtil.parse(vo.getDateSaisie()));
            }

            if (vo.getMontant() != null) {
                item.setMontant(NumberUtil.toBigDecimal(vo.getMontant()));
            }


            return item;
        }
    }

    @Override
    public PaiementFactureVo toVo(PaiementFacture item) {
        if (item == null) {
            return null;
        } else {
            PaiementFactureVo vo = new PaiementFactureVo();

            if (typePaiment && item.getTypePaiment() != null) {
                vo.setTypePaimentVo(typePaiementConverter.toVo(item.getTypePaiment()));
            }

            if (facture && item.getFacture() != null) {
                vo.setFactureVo(factureConverter.toVo(item.getFacture()));
            }
            if (caisse && item.getCaisse() != null) {
                vo.setCaisseVo(caisseConverter.toVo(item.getCaisse()));
            }
            if (compteBanquaire && item.getCompteBanquaire() != null) {
                vo.setCompteBanquaireVo(compteBanquaireConverter.toVo(item.getCompteBanquaire()));
            }
            if (operationComptable && item.getOperationComptable() != null) {
                vo.setOperationComptableVo(operationComptableConverter.toVo(item.getOperationComptable()));
            }

            if (StringUtil.isNotEmpty(item.getDescription())) {
                vo.setDescription(item.getDescription());
            }

            if (StringUtil.isNotEmpty(item.getScan())) {
                vo.setScan(item.getScan());
            }

            if (item.getId() != null) {
                vo.setId(NumberUtil.toString(item.getId()));
            }

            if (item.getDatePaiment() != null) {
                vo.setDatePaiment(DateUtil.formateDate(item.getDatePaiment()));
            }

            if (item.getDateSaisie() != null) {
                vo.setDateSaisie(DateUtil.formateDate(item.getDateSaisie()));
            }

            if (item.getMontant() != null) {
                vo.setMontant(NumberUtil.toString(item.getMontant()));
            }

            return vo;
        }
    }

    public void init() {

        typePaiment = true;

        facture = true;
    }

    public boolean isTypePaiment() {
        return typePaiment;
    }

    public void setTypePaiment(boolean typePaiment) {
        this.typePaiment = typePaiment;
    }

    public boolean isCaisse() {
        return caisse;
    }

    public void setCaisse(boolean caisse) {
        this.caisse = caisse;
    }

    public boolean isCompteBanquaire() {
        return compteBanquaire;
    }

    public void setCompteBanquaire(boolean compteBanquaire) {
        this.compteBanquaire = compteBanquaire;
    }

    public CaisseConverter getCaisseConverter() {
        return caisseConverter;
    }

    public void setCaisseConverter(CaisseConverter caisseConverter) {
        this.caisseConverter = caisseConverter;
    }

    public CompteBanquaireConverter getCompteBanquaireConverter() {
        return compteBanquaireConverter;
    }

    public void setCompteBanquaireConverter(CompteBanquaireConverter compteBanquaireConverter) {
        this.compteBanquaireConverter = compteBanquaireConverter;
    }

    public TypePaiementConverter getTypePaiementConverter() {
        return typePaiementConverter;
    }

    public void setTypePaiementConverter(TypePaiementConverter typePaiementConverter) {
        this.typePaiementConverter = typePaiementConverter;
    }

    public boolean isFacture() {
        return facture;
    }

    public void setFacture(boolean facture) {
        this.facture = facture;
    }

    public boolean isOperationComptable() {
        return operationComptable;
    }

    public void setOperationComptable(boolean operationComptable) {
        this.operationComptable = operationComptable;
    }

    public OperationComptableConverter getOperationComptableConverter() {
        return operationComptableConverter;
    }

    public void setOperationComptableConverter(OperationComptableConverter operationComptableConverter) {
        this.operationComptableConverter = operationComptableConverter;
    }

    public FactureConverter getFactureConverter() {
        return factureConverter;
    }

    public void setFactureConverter(FactureConverter factureConverter) {
        this.factureConverter = factureConverter;
    }
}
