package com.zsmart.accountingProject.ws.rest.converter;

import com.zsmart.accountingProject.bean.Facture;
import com.zsmart.accountingProject.bean.FactureItem;
import com.zsmart.accountingProject.bean.OperationComptable;
import com.zsmart.accountingProject.service.util.DateUtil;
import com.zsmart.accountingProject.service.util.ListUtil;
import com.zsmart.accountingProject.service.util.NumberUtil;
import com.zsmart.accountingProject.service.util.StringUtil;
import com.zsmart.accountingProject.ws.rest.vo.FactureVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FactureConverter extends AbstractConverter<Facture, FactureVo> {

    private boolean etatFacture;

    @Autowired
    private EtatFactureConverter etatFactureConverter;
    private boolean paimentFactures;
    @Autowired
    private AdherantConverter adherantConverter;
    @Autowired
    private ComptableConverter comptableConverter;
    private boolean adherant;
    private boolean comptable;
    @Autowired
    private PaiementFactureConverter paiementFactureConverter;
    private boolean operationComptable;

    @Autowired
    private OperationComptableConverter operationComptableConverter;
    private boolean factureItems;

    @Autowired
    private FactureItemConverter factureItemConverter;
    @Autowired
    private DeclarationTvaConverter declarationTvaConverter;
    @Autowired
    private TauxTvaConverter tauxTvaConverter;
    @Autowired
    private SocieteConverter societeConverter;
    private boolean societe;
    private boolean declarationtva;

    @Override
    public Facture toItem(FactureVo vo) {
        if (vo == null) {
            return null;
        } else {
            Facture item = new Facture();

            if (etatFacture && vo.getEtatFactureVo() != null) {
                item.setEtatFacture(etatFactureConverter.toItem(vo.getEtatFactureVo()));
            }
            if (declarationtva && vo.getDeclarationTvaVo() != null) {
                item.setDeclarationTva(declarationTvaConverter.toItem(vo.getDeclarationTvaVo()));
            }


            if (StringUtil.isNotEmpty(vo.getReference())) {
                item.setReference(vo.getReference());
            }

            if (StringUtil.isNotEmpty(vo.getTypeFacture())) {
                item.setTypeFacture(vo.getTypeFacture());
            }

            if (vo.getSocieteVo()!=null && societe) {
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
            if (vo.getTraiter()!=null){
                item.setTraiter(vo.getTraiter());
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
            if (vo.getAdherantVo() != null && adherant) {
                item.setAdherant(adherantConverter.toItem(vo.getAdherantVo()));
            }

            if (vo.getTauxTvaVo() != null) {
                item.setTauxTva(tauxTvaConverter.toItem(vo.getTauxTvaVo()));
            }
            return item;
        }
    }

    @Override
    public FactureVo toVo(Facture item) {
        if (item == null) {
            return null;
        } else {
            FactureVo vo = new FactureVo();

            if (etatFacture && item.getEtatFacture() != null) {
                vo.setEtatFactureVo(etatFactureConverter.toVo(item.getEtatFacture()));
            }
            if (declarationtva && item.getDeclarationTva() != null) {
                vo.setDeclarationTvaVo(declarationTvaConverter.toVo(item.getDeclarationTva()));
            }

            if (StringUtil.isNotEmpty(item.getReference())) {
                vo.setReference(item.getReference());
            }

            if (StringUtil.isNotEmpty(item.getTypeFacture())) {
                vo.setTypeFacture(item.getTypeFacture());
            }

            if (item.getSociete()!=null && societe) {
                vo.setSocieteVo(societeConverter.toVo(item.getSociete()));
            }


            if (item.getId() != null) {
                vo.setId(NumberUtil.toString(item.getId()));
            }

            if (item.getAnnee() != null) {
                vo.setAnnee(NumberUtil.toString(item.getAnnee()));
            }
            if(item.getTraiter()!=null){
                vo.setTraiter(item.getTraiter());
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
                BigDecimal total = BigDecimal.ZERO;
                for (FactureItem fi : item.getFactureItems()
                ) {
                    total = total.add(fi.getMontant().multiply(fi.getQuantite()));
                }
                vo.setTotalFactureItems(NumberUtil.toString(total));
                vo.setFactureItemsVo(factureItemConverter.toVo(item.getFactureItems()));
            }
            if (item.getAdherant() != null && adherant) {
                vo.setAdherantVo(adherantConverter.toVo(item.getAdherant()));
            }

            if (item.getTauxTva() != null) {
                vo.setTauxTvaVo(tauxTvaConverter.toVo(item.getTauxTva()));
            }
            return vo;
        }
    }

    public void init() {

        etatFacture = true;

        paimentFactures = true;

        operationComptable = true;

        factureItems = true;

        societe=true;
    }

    public boolean isEtatFacture() {
        return etatFacture;
    }

    public boolean isDeclarationtva() {
        return declarationtva;
    }

    public void setDeclarationtva(boolean declarationtva) {
        this.declarationtva = declarationtva;
    }
    public boolean isSociete() {
        return societe;
    }

    public void setSociete(boolean societe) {
        this.societe = societe;
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

    public Boolean getAdherant() {
        return adherant;
    }

    public void setAdherant(boolean adherant) {
        this.adherant = adherant;
    }

    public Boolean getComptable() {
        return comptable;
    }

    public void setComptable(boolean comptable) {
        this.comptable = comptable;
    }

    public boolean isFactureItems() {
        return factureItems;
    }

    public void setFactureItems(boolean factureItems) {
        this.factureItems = factureItems;
    }

    public FactureItemConverter getFactureItemConverter() {
        return factureItemConverter;
    }

    public void setFactureItemConverter(FactureItemConverter factureItemConverter) {
        this.factureItemConverter = factureItemConverter;
    }

    public SocieteConverter getSocieteConverter() {
        return societeConverter;
    }

    public void setSocieteConverter(SocieteConverter societeConverter) {
        this.societeConverter = societeConverter;
    }
}
