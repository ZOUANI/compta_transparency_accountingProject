package com.zsmart.accountingProject.ws.rest.converter;

import com.zsmart.accountingProject.bean.TauxTva;
import com.zsmart.accountingProject.service.util.ListUtil;
import com.zsmart.accountingProject.service.util.NumberUtil;
import com.zsmart.accountingProject.service.util.StringUtil;
import com.zsmart.accountingProject.ws.rest.vo.TauxTvaVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TauxTvaConverter extends AbstractConverter<TauxTva, TauxTvaVo> {
    private boolean factures;

    @Autowired
    private FactureConverter factureConverter;
    @Autowired
    private AdherantConverter adherantConverter;
    private boolean adherent;

    @Override
    public TauxTva toItem(TauxTvaVo vo) {
        if (vo == null) {
            return null;
        } else {
            TauxTva item = new TauxTva();

            if (StringUtil.isNotEmpty(vo.getLibelle())) {
                item.setLibelle(vo.getLibelle());
            }

            if (vo.getTaux() != null) {
                item.setTaux(NumberUtil.toInt(vo.getTaux()));
            }

            if (vo.getId() != null) {
                item.setId(NumberUtil.toLong(vo.getId()));
            }
            if (vo.getAdherantVo() != null && adherent) {
                item.setAdherant(adherantConverter.toItem(vo.getAdherantVo()));
            }

            if (ListUtil.isNotEmpty(vo.getFactureVoList()) && factures) {
                item.setFactures(factureConverter.toItem(vo.getFactureVoList()));
            }

            return item;
        }
    }

    @Override
    public TauxTvaVo toVo(TauxTva item) {
        if (item == null) {
            return null;
        } else {
            TauxTvaVo vo = new TauxTvaVo();

            if (StringUtil.isNotEmpty(item.getLibelle())) {
                vo.setLibelle(item.getLibelle());
            }

            if (item.getTaux() != null) {
                vo.setTaux(NumberUtil.toString(item.getTaux()));
            }

            if (item.getId() != null) {
                vo.setId(NumberUtil.toString(item.getId()));
            }

            if (ListUtil.isNotEmpty(item.getFactures()) && factures) {
                vo.setFactureVoList(factureConverter.toVo(item.getFactures()));
            }
            if (item.getAdherant() != null && adherent) {
                vo.setAdherantVo(adherantConverter.toVo(item.getAdherant()));
            }

            return vo;
        }
    }

    public boolean isFactures() {
        return factures;
    }

    public void setFactures(boolean factures) {
        this.factures = factures;
    }

    public AdherantConverter getAdherantConverter() {
        return adherantConverter;
    }

    public void setAdherantConverter(AdherantConverter adherantConverter) {
        this.adherantConverter = adherantConverter;
    }

    public boolean isAdherent() {
        return adherent;
    }

    public void setAdherent(boolean adherent) {
        this.adherent = adherent;
    }

    public FactureConverter getFactureConverter() {
        return factureConverter;
    }

    public void setFactureConverter(FactureConverter factureConverter) {
        this.factureConverter = factureConverter;
    }
}
