package com.zsmart.accountingProject.ws.rest.converter;

import com.zsmart.accountingProject.bean.Comptable;
import com.zsmart.accountingProject.service.util.ListUtil;
import com.zsmart.accountingProject.service.util.NumberUtil;
import com.zsmart.accountingProject.ws.rest.vo.ComptableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class ComptableConverter extends AbstractConverter<Comptable, ComptableVo> {
    @Autowired
    private UtilisateurConverter utilisateurConverter;
    @Autowired
    private AdherantConverter adherantConverter;
    @Autowired
    private SocieteConverter societeConverter;

    private boolean societe;

    private boolean adherant;

    @Override
    public Comptable toItem(ComptableVo vo) {
        if(vo==null) {

            return null;
        }
        else {
            Comptable item = new Comptable();
            if (vo.getId() != null) {
                item.setId(NumberUtil.toLong(vo.getId()));
            }

            if (ListUtil.isNotEmpty(vo.getSocietesVo()) && societe) {
                item.setSocietes(societeConverter.toItem(vo.getSocietesVo()));
            }
            if (ListUtil.isNotEmpty(vo.getAdherantsVo()) && adherant) {
                item.setAdherants(adherantConverter.toItem(vo.getAdherantsVo()));
            }


            return item;
        }

    }

    @Override
    public ComptableVo toVo(Comptable item) {
        if (item == null) {
            return null;
        } else {
            ComptableVo vo = new ComptableVo();

            if (ListUtil.isNotEmpty(item.getAdherants()) && adherant) {
                vo.setAdherantsVo(adherantConverter.toVo(item.getAdherants()));
            }
            if (ListUtil.isNotEmpty(item.getSocietes()) && societe) {
                vo.setSocietesVo(societeConverter.toVo(item.getSocietes()));
            }


            if (item.getId() != null) {
                vo.setId(NumberUtil.toString(item.getId()));
            }
            return vo;

        }

    }

}
