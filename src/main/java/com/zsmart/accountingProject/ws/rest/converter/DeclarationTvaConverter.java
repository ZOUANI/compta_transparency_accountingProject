package com.zsmart.accountingProject.ws.rest.converter;

import com.zsmart.accountingProject.bean.DeclarationTva;
import com.zsmart.accountingProject.service.util.ListUtil;
import com.zsmart.accountingProject.service.util.NumberUtil;
import com.zsmart.accountingProject.ws.rest.vo.DeclarationTvaVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class DeclarationTvaConverter extends AbstractConverter<DeclarationTva, DeclarationTvaVo> {
    @Autowired
    private SocieteConverter societeConverter;
    private boolean societe;
    @Autowired
    private FactureConverter factureConverter;
    private boolean facture;

    @Override
    public DeclarationTva toItem(DeclarationTvaVo vo) {
                if(vo==null) {
                    return null;
                }
    else{
        DeclarationTva item=new DeclarationTva();

         if (ListUtil.isNotEmpty(vo.getFacturescharge()) && isFacture()) {  item.setFacturescharge(factureConverter.toItem(vo.getFacturescharge())); }
          if (ListUtil.isNotEmpty(vo.getFacturesGain()) && isFacture()) {  item.setFacturesGain(factureConverter.toItem(vo.getFacturesGain())); }
           if (vo.getSocieteVo()!=null && isSociete()) {item.setSociete(societeConverter.toItem(vo.getSocieteVo())); }
            if (vo.getCotisation()!=null) item.setCotisation(NumberUtil.toBigDecimal(vo.getCotisation()));
            if(vo.getDifferenceChargeGain()!=null) item.setDifferenceChargeGain(NumberUtil.toBigDecimal(vo.getDifferenceChargeGain()));
            if (vo.getTotalTvaCharge()!=null) item.setTotalTvaCharge(NumberUtil.toBigDecimal(vo.getTotalTvaCharge()));
            if(vo.getTotalTvaGain()!=null) item.setTotalTvaGain(NumberUtil.toBigDecimal(vo.getTotalTvaGain()));
            if (vo.getId() != null) {item.setId(NumberUtil.toLong(vo.getId()));}
        return item;
    }
    }

    @Override
    public DeclarationTvaVo toVo(DeclarationTva item) {
        if (item==null) return null;
        else{
            DeclarationTvaVo vo= new DeclarationTvaVo();

            if (item.getId() != null) {vo.setId(NumberUtil.toString(item.getId()));}
            if (item.getSociete()!=null && isSociete()) {vo.setSocieteVo(societeConverter.toVo(item.getSociete()));}
            if (ListUtil.isNotEmpty(item.getFacturescharge()) && isFacture()) {vo.setFacturescharge(factureConverter.toVo(item.getFacturescharge()));}
            if (ListUtil.isNotEmpty(item.getFacturesGain()) && isFacture()) {vo.setFacturesGain(factureConverter.toVo(item.getFacturesGain()));}
            if (item.getCotisation()!=null) vo.setCotisation(NumberUtil.toString(item.getCotisation()));
            if(item.getDifferenceChargeGain()!=null) vo.setDifferenceChargeGain(NumberUtil.toString(item.getDifferenceChargeGain()));
            if (item.getTotalTvaCharge()!=null) vo.setTotalTvaCharge(NumberUtil.toString(item.getTotalTvaCharge()));
            if(item.getTotalTvaGain()!=null) vo.setTotalTvaGain(NumberUtil.toString(item.getTotalTvaGain()));
            return vo;
        }
    }
    public void setFacture(boolean facture) {
        this.facture = facture;
    }
    public boolean isFacture() {
        return facture;
    }

    public void setSociete(boolean societe) {
        this.societe = societe;
    }
    public boolean isSociete() {
        return societe;
    }
}
