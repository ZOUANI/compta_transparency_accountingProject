package com.zsmart.accountingProject.ws.rest.converter;

import com.zsmart.accountingProject.bean.DeclarationTva;
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
            if (facture && vo.getFacturesVo() != null) {  item.setFactures(factureConverter.toItem(vo.getFacturesVo())); }
            if (vo.getSocieteVo()!=null && societe) {item.setSociete(societeConverter.toItem(vo.getSocieteVo())); }
            if (vo.getCotisation()!=null) item.setCotisation(NumberUtil.toBigDecimal(vo.getCotisation()));
            if(vo.getDifferenceChargeGain()!=null) item.setDifferenceChargeGain(NumberUtil.toBigDecimal(vo.getDifferenceChargeGain()));
            if (vo.getTotalTvaCharge()!=null) item.setTotalTvaCharge(NumberUtil.toBigDecimal(vo.getTotalTvaCharge()));
            if(vo.getTotalTvaGain()!=null) item.setTotalTvaGain(NumberUtil.toBigDecimal(vo.getTotalTvaGain()));

        return item;
    }
    }

    @Override
    public DeclarationTvaVo toVo(DeclarationTva item) {
        if (item==null) return null;
        else{
            DeclarationTvaVo vo= new DeclarationTvaVo();
            if (item.getSociete()!=null && societe) {vo.setSocieteVo(societeConverter.toVo(item.getSociete()));}
            if (facture && item.getFactures() != null) {vo.setFacturesVo(factureConverter.toVo(item.getFactures()));}
            if (item.getCotisation()!=null) vo.setCotisation(NumberUtil.toString(item.getCotisation()));
            if(item.getDifferenceChargeGain()!=null) vo.setDifferenceChargeGain(NumberUtil.toString(item.getDifferenceChargeGain()));
            if (item.getTotalTvaCharge()!=null) vo.setTotalTvaCharge(NumberUtil.toString(item.getTotalTvaCharge()));
            if(item.getTotalTvaGain()!=null) vo.setTotalTvaGain(NumberUtil.toString(item.getTotalTvaGain()));
            return vo;
        }
    }
}
