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
