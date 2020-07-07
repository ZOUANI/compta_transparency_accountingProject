package com.zsmart.accountingProject.ws.rest.converter;

import com.zsmart.accountingProject.bean.Societe;
import com.zsmart.accountingProject.service.util.ListUtil;
import com.zsmart.accountingProject.service.util.NumberUtil;
import com.zsmart.accountingProject.service.util.StringUtil;
import com.zsmart.accountingProject.ws.rest.vo.SocieteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SocieteConverter extends AbstractConverter<Societe, SocieteVo> {

    @Autowired
    private FactureConverter factureConverter;

    private boolean facture;
    @Autowired
    private AdherantConverter adherantConverter;
    @Autowired
    private ComptableConverter comptableConverter;
    private boolean adherant;
    private boolean comptable;

    @Override
    public Societe toItem(SocieteVo vo) {
        if (vo == null) {
            return null;
        } else {
            Societe item = new Societe();


            if (StringUtil.isNotEmpty(vo.getContratBail())) {
                item.setContratBail(vo.getContratBail());
            }
            if (StringUtil.isNotEmpty(vo.getCertificatNegatif())) {
                item.setCertificatNegatif(vo.getCertificatNegatif());
            }
            if (StringUtil.isNotEmpty(vo.getRegistreCommerceimage())) {
                item.setRegistreCommerceimage(vo.getRegistreCommerceimage());
            }
            if (StringUtil.isNotEmpty(vo.getPatente())) {
                item.setPatente(vo.getPatente());
            }
            if (StringUtil.isNotEmpty(vo.getStatuet())) {
                item.setStatuet(vo.getStatuet());
            }
            if (StringUtil.isNotEmpty(vo.getReleverBanquaire())) {
                item.setReleverBanquaire(vo.getReleverBanquaire());
            }
            if (StringUtil.isNotEmpty(vo.getPublicationCreationBo())) {
                item.setPublicationCreationBo(vo.getPublicationCreationBo());
            }
            if (StringUtil.isNotEmpty(vo.getRaisonSocial())) {
                item.setRaisonSocial(vo.getRaisonSocial());
            }

            if (StringUtil.isNotEmpty(vo.getRegistreComerce())) {
                item.setRegistreComerce(vo.getRegistreComerce());
            }
            if (StringUtil.isNotEmpty(vo.getAuthentificationCnss())) {
                item.setAuthentificationCnss(vo.getAuthentificationCnss());
            }
            if (StringUtil.isNotEmpty(vo.getJuridiction())) {
                item.setJuridiction(vo.getJuridiction());
            }
            if (StringUtil.isNotEmpty(vo.getStatuejuridique())) {
                item.setStatuejuridique(vo.getStatuejuridique());
            }
            if (StringUtil.isNotEmpty(vo.getAdresse())) {
                item.setAdresse(vo.getAdresse());
            }
            if (vo.getId() != null) {
                item.setId(NumberUtil.toLong(vo.getId()));
            }

            if (StringUtil.isNotEmpty(vo.getIdentifiantFiscal())) {
                item.setIdentifiantFiscal(vo.getIdentifiantFiscal());
            }
            if (StringUtil.isNotEmpty(vo.getIce())) {
                item.setIce(vo.getIce());
            }

            if (ListUtil.isNotEmpty(vo.getFacturesVo()) && facture) {
                item.setFactures(factureConverter.toItem(vo.getFacturesVo()));
            }
            if (vo.getAdherantVo()!=null && adherant) {
                item.setAdherant(adherantConverter.toItem(vo.getAdherantVo()));
            }
            if (vo.getComptableVo()!=null && comptable) {
                item.setComptable(comptableConverter.toItem(vo.getComptableVo()));
            }
            return item;
        }
    }

    @Override
    public SocieteVo toVo(Societe item) {
        if (item == null) {
            return null;
        } else {
            SocieteVo vo=new SocieteVo();


            if (StringUtil.isNotEmpty(item.getRaisonSocial())) {
                vo.setRaisonSocial(item.getRaisonSocial());
            }

            if (StringUtil.isNotEmpty(item.getContratBail())) {
                vo.setContratBail(item.getContratBail());
            }

            if (StringUtil.isNotEmpty(item.getCertificatNegatif())) {
                vo.setCertificatNegatif(item.getCertificatNegatif());
            }

            if (StringUtil.isNotEmpty(item.getRegistreCommerceimage())) {
                vo.setRegistreCommerceimage(item.getRegistreCommerceimage());
            }

            if (StringUtil.isNotEmpty(item.getPatente())) {
                vo.setPatente(item.getPatente());
            }

            if (StringUtil.isNotEmpty(item.getStatuet())) {
                vo.setStatuet(item.getStatuet());
            }

            if (StringUtil.isNotEmpty(item.getReleverBanquaire())) {
                vo.setReleverBanquaire(item.getReleverBanquaire());
            }

            if (StringUtil.isNotEmpty(item.getPublicationCreationBo())) {
                vo.setPublicationCreationBo(item.getPublicationCreationBo());
            }



            if (StringUtil.isNotEmpty(item.getAdresse())) {
                vo.setAdresse(item.getAdresse());
            }
            if (StringUtil.isNotEmpty(item.getRegistreComerce())) {
                vo.setRegistreComerce(item.getRegistreComerce());
            }

            if (StringUtil.isNotEmpty(item.getAuthentificationCnss())) {
                vo.setAuthentificationCnss(item.getAuthentificationCnss());
            }

            if (StringUtil.isNotEmpty(item.getJuridiction())) {
                vo.setJuridiction(item.getJuridiction());
            }

            if (StringUtil.isNotEmpty(item.getStatuejuridique())) {
                vo.setStatuejuridique(item.getStatuejuridique());
            }
            if (item.getId() != null) {
                vo.setId(NumberUtil.toString(item.getId()));
            }

            if (StringUtil.isNotEmpty(item.getIdentifiantFiscal())) {
                vo.setIdentifiantFiscal(item.getIdentifiantFiscal());
            }
            if (StringUtil.isNotEmpty(item.getIce())) {
                vo.setIce(item.getIce());
            }

            if (ListUtil.isNotEmpty(item.getFactures()) && facture) {
                vo.setFacturesVo(factureConverter.toVo(item.getFactures()));
            }
            if (item.getAdherant()!=null && adherant) {
                vo.setAdherantVo(adherantConverter.toVo(item.getAdherant()));
            }
            if (item.getComptable() != null && comptable) {
                vo.setComptableVo(comptableConverter.toVo(item.getComptable()));
            }
            return vo;
        }
    }

    public void init() {

        facture = true;
    }

    public AdherantConverter getAdherantConverter() {
        return adherantConverter;
    }

    public void setAdherantConverter(AdherantConverter adherantConverter) {
        this.adherantConverter = adherantConverter;
    }

    public ComptableConverter getComptableConverter() {
        return comptableConverter;
    }

    public void setComptableConverter(ComptableConverter comptableConverter) {
        this.comptableConverter = comptableConverter;
    }

    public boolean isAdherant() {
        return adherant;
    }

    public void setAdherant(boolean adherant) {
        this.adherant = adherant;
    }

    public boolean isComptable() {
        return comptable;
    }

    public void setComptable(boolean comptable) {
        this.comptable = comptable;
    }

    public FactureConverter getFactureConverter() {
        return factureConverter;
    }

    public void setFactureConverter(FactureConverter factureConverter) {
        this.factureConverter = factureConverter;
    }

    public boolean isFacture() {
        return facture;
    }

    public void setFacture(boolean facture) {
        this.facture = facture;
    }
}
