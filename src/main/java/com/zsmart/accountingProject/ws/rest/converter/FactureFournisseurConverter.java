package com.zsmart.accountingProject.ws.rest.converter;

import com.zsmart.accountingProject.bean.FactureItem;
import com.zsmart.accountingProject.bean.OperationComptable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.zsmart.accountingProject.service.util.*;
import com.zsmart.accountingProject.bean.FactureFournisseur;
import com.zsmart.accountingProject.ws.rest.vo.FactureFournisseurVo;

import java.math.BigDecimal;

@Component
public class FactureFournisseurConverter extends AbstractConverter<FactureFournisseur, FactureFournisseurVo> {

    private boolean fournisseur;

    @Autowired
    private FournisseurConverter fournisseurConverter;

    private boolean etatFacture;

    @Autowired
    private EtatFactureConverter etatFactureConverter;
    private boolean paimentFactures;

    @Autowired
    private PaiementFactureConverter paiementFactureConverter;
    private boolean operationComptable;

    @Autowired
    private OperationComptableConverter operationComptableConverter;
    private boolean factureItems;

    @Autowired
    private ClientConverter clientConverter;
    @Autowired
    private FactureItemConverter factureItemConverter;

    @Autowired
    private SocieteConverter societeConverter;

    @Override
    public FactureFournisseur toItem(FactureFournisseurVo vo) {
        if (vo == null) {
            return null;
        } else {
            FactureFournisseur item = new FactureFournisseur();

            if (fournisseur && vo.getFournisseurVo() != null) {
                item.setFournisseur(fournisseurConverter.toItem(vo.getFournisseurVo()));
            }

            if (etatFacture && vo.getEtatFactureVo() != null) {
                item.setEtatFacture(etatFactureConverter.toItem(vo.getEtatFactureVo()));
            }

            if (StringUtil.isNotEmpty(vo.getReference())) {
                item.setReference(vo.getReference());
            }

            if (StringUtil.isNotEmpty(vo.getTypeFacture())) {
                item.setTypeFacture(vo.getTypeFacture());
            }
            if (StringUtil.isNotEmpty(vo.getScanPath())) {
                item.setScanPath(vo.getScanPath());
            }

            if (vo.getSocieteVo()!=null) {
                item.setSociete(societeConverter.toItem(vo.getSocieteVo()));
            }

            if (vo.getId() != null) {
                item.setId(NumberUtil.toLong(vo.getId()));
            }

            if (vo.getAnnee() != null) {
                item.setAnnee(NumberUtil.toInt(vo.getAnnee()));
            }

            if (vo.getMois() != null) {
                item.setMois(NumberUtil.toInt(vo.getMois()));
            }

            if (vo.getTrimester() != null) {
                item.setTrimester(NumberUtil.toInt(vo.getTrimester()));
            }

            if (vo.getTotalHt() != null) {
                item.setTotalHt(NumberUtil.toBigDecimal(vo.getTotalHt()));
            }

            if (vo.getTotalTtc() != null) {
                item.setTotalTtc(NumberUtil.toBigDecimal(vo.getTotalTtc()));
            }

            if (vo.getTva() != null) {
                item.setTva(NumberUtil.toBigDecimal(vo.getTva()));
            }

            if (vo.getTotalPayerHt() != null) {
                item.setTotalPayerHt(NumberUtil.toBigDecimal(vo.getTotalPayerHt()));
            }

            if (vo.getTotalRestantHt() != null) {
                item.setTotalRestantHt(NumberUtil.toBigDecimal(vo.getTotalRestantHt()));
            }

            if (vo.getDateFacture() != null) {
                item.setDateFacture(DateUtil.parse(vo.getDateFacture()));
            }

            if (vo.getDateSaisie() != null) {
                item.setDateSaisie(DateUtil.parse(vo.getDateSaisie()));
            }

            if (ListUtil.isNotEmpty(vo.getPaiementFacturesVo()) && paimentFactures) {
                item.setPaimentFactures(paiementFactureConverter.toItem(vo.getPaiementFacturesVo()));
            }

            if (ListUtil.isNotEmpty(vo.getOperationComptablesVo()) && operationComptable) {
                item.setOperationComptable(operationComptableConverter.toItem(vo.getOperationComptablesVo()));
            }

            if (ListUtil.isNotEmpty(vo.getFactureItemsVo()) && factureItems) {
                item.setFactureItems(factureItemConverter.toItem(vo.getFactureItemsVo()));
            }

            return item;
        }
    }

    @Override
    public FactureFournisseurVo toVo(FactureFournisseur item) {
        if (item == null) {
            return null;
        } else {
            FactureFournisseurVo vo = new FactureFournisseurVo();

            if (etatFacture && item.getEtatFacture() != null) {
                vo.setEtatFactureVo(etatFactureConverter.toVo(item.getEtatFacture()));
            }

            if (StringUtil.isNotEmpty(item.getReference())) {
                vo.setReference(item.getReference());
            }
            if (StringUtil.isNotEmpty(item.getScanPath())) {
                vo.setScanPath(item.getScanPath());
            }

            if (StringUtil.isNotEmpty(item.getTypeFacture())) {
                vo.setTypeFacture(item.getTypeFacture());
            }

            if (item.getSociete()!=null) {
                vo.setSocieteVo(societeConverter.toVo(item.getSociete()));
            }

            if (item.getAnnee() != null) {
                vo.setAnnee(NumberUtil.toString(item.getAnnee()));
            }

            if (item.getMois() != null) {
                vo.setMois(NumberUtil.toString(item.getMois()));
            }

            if (item.getTrimester() != null) {
                vo.setTrimester(NumberUtil.toString(item.getTrimester()));
            }

            if (item.getTotalHt() != null) {
                vo.setTotalHt(NumberUtil.toString(item.getTotalHt()));
            }

            if (item.getTotalTtc() != null) {
                vo.setTotalTtc(NumberUtil.toString(item.getTotalTtc()));
            }

            if (item.getTva() != null) {
                vo.setTva(NumberUtil.toString(item.getTva()));
            }

            if (item.getTotalPayerHt() != null) {
                vo.setTotalPayerHt(NumberUtil.toString(item.getTotalPayerHt()));
            }

            if (item.getTotalRestantHt() != null) {
                vo.setTotalRestantHt(NumberUtil.toString(item.getTotalRestantHt()));
            }

            if (item.getDateFacture() != null) {
                vo.setDateFacture(DateUtil.formateDate(item.getDateFacture()));
            }

            if (item.getDateSaisie() != null) {
                vo.setDateSaisie(DateUtil.formateDate(item.getDateSaisie()));
            }

            if (ListUtil.isNotEmpty(item.getPaimentFactures()) && paimentFactures) {
                vo.setPaiementFacturesVo(paiementFactureConverter.toVo(item.getPaimentFactures()));
            }

            if (ListUtil.isNotEmpty(item.getOperationComptable()) && operationComptable) {
                BigDecimal sumDebit=BigDecimal.ZERO;
                BigDecimal sumCredit=BigDecimal.ZERO;
                for (OperationComptable op: item.getOperationComptable()
                ) {
                    if (op.getTypeOperationComptable()!=null){
                        if (op.getTypeOperationComptable().getLibelle().equals("Debit")){
                            sumDebit=sumDebit.add(op.getMontant());
                        }else if (op.getTypeOperationComptable().getLibelle().equals("Credit")){
                            sumCredit=sumCredit.add(op.getMontant());
                        }
                    }
                }
                vo.setTotalDebit(NumberUtil.toString(sumDebit));
                vo.setTotalCredit(NumberUtil.toString(sumCredit));
                vo.setOperationComptablesVo(operationComptableConverter.toVo(item.getOperationComptable()));
            }

            if (ListUtil.isNotEmpty(item.getFactureItems()) && factureItems) {
                BigDecimal total=BigDecimal.ZERO;
                for (FactureItem fi:item.getFactureItems()
                     ) {
                    total=total.add(fi.getMontant().multiply(fi.getQuantite()));
                }
                vo.setTotalFactureItems(NumberUtil.toString(total));
                vo.setFactureItemsVo(factureItemConverter.toVo(item.getFactureItems()));
            }

            if (fournisseur && item.getFournisseur() != null) {
                vo.setFournisseurVo(fournisseurConverter.toVo(item.getFournisseur()));
            }

            if (item.getId() != null) {
                vo.setId(NumberUtil.toString(item.getId()));
            }

            return vo;
        }
    }
    public void init() {
        etatFacture=true;
        paimentFactures=true;
        factureItems=true;
        operationComptable=true;

        fournisseur = true;
    }

    public boolean isFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(boolean fournisseur) {
        this.fournisseur = fournisseur;
    }

    public FournisseurConverter getFournisseurConverter() {
        return fournisseurConverter;
    }

    public void setFournisseurConverter(FournisseurConverter fournisseurConverter) {
        this.fournisseurConverter = fournisseurConverter;
    }

    public boolean isEtatFacture() {
        return etatFacture;
    }

    public void setEtatFacture(boolean etatFacture) {
        this.etatFacture = etatFacture;
    }

    public EtatFactureConverter getEtatFactureConverter() {
        return etatFactureConverter;
    }

    public void setEtatFactureConverter(EtatFactureConverter etatFactureConverter) {
        this.etatFactureConverter = etatFactureConverter;
    }

    public boolean isPaimentFactures() {
        return paimentFactures;
    }

    public void setPaimentFactures(boolean paimentFactures) {
        this.paimentFactures = paimentFactures;
    }

    public PaiementFactureConverter getPaiementFactureConverter() {
        return paiementFactureConverter;
    }

    public void setPaiementFactureConverter(PaiementFactureConverter paiementFactureConverter) {
        this.paiementFactureConverter = paiementFactureConverter;
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

    public boolean isFactureItems() {
        return factureItems;
    }

    public void setFactureItems(boolean factureItems) {
        this.factureItems = factureItems;
    }

    public ClientConverter getClientConverter() {
        return clientConverter;
    }

    public void setClientConverter(ClientConverter clientConverter) {
        this.clientConverter = clientConverter;
    }

    public FactureItemConverter getFactureItemConverter() {
        return factureItemConverter;
    }

    public void setFactureItemConverter(FactureItemConverter factureItemConverter) {
        this.factureItemConverter = factureItemConverter;
    }
}
