package com.zsmart.accountingProject.ws.rest.converter;

import com.zsmart.accountingProject.bean.Societe;
import com.zsmart.accountingProject.bean.SousClasseComptable;
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

    @Override
    public Societe toItem(SocieteVo vo) {
        if (vo == null) {
            return null;
        } else {
           Societe item=new Societe();



            if (StringUtil.isNotEmpty(vo.getRaisonSocial())) {
                item.setRaisonSocial(vo.getRaisonSocial());
            }
            if (StringUtil.isNotEmpty(vo.getNom())) {
                item.setNom(vo.getNom());
            }
            if (StringUtil.isNotEmpty(vo.getPrenom())) {
                item.setPrenom(vo.getPrenom());
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


            if (StringUtil.isNotEmpty(item.getNom())) {
                vo.setNom(item.getNom());
            }

            if (StringUtil.isNotEmpty(item.getPrenom())) {
                vo.setPrenom(item.getPrenom());
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

            return vo;
        }
    }
    public void init() {

       facture=true;
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
