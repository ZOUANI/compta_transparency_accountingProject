package com.zsmart.accountingProject.ws.rest.converter;

import com.zsmart.accountingProject.bean.Comptable;
import com.zsmart.accountingProject.service.util.ListUtil;
import com.zsmart.accountingProject.service.util.NumberUtil;
import com.zsmart.accountingProject.service.util.StringUtil;
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
            if (StringUtil.isNotEmpty(vo.getEmail())) {
                item.setEmail(vo.getEmail());
            }
            if (StringUtil.isNotEmpty(vo.getNom())) {
                item.setNom(vo.getNom());
            }
            if (StringUtil.isNotEmpty(vo.getPrenom())) {
                item.setPrenom(vo.getPrenom());
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
            if (StringUtil.isNotEmpty(item.getEmail())) {
                vo.setEmail(item.getEmail());
            }
            if (StringUtil.isNotEmpty(item.getNom())) {
                vo.setNom(item.getNom());
            }
            if (StringUtil.isNotEmpty(item.getPrenom())) {
                vo.setPrenom(item.getPrenom());
            }


            if (item.getId() != null) {
                vo.setId(NumberUtil.toString(item.getId()));
            }
            return vo;

        }

    }

    public UtilisateurConverter getUtilisateurConverter() {
        return utilisateurConverter;
    }

    public void setUtilisateurConverter(UtilisateurConverter utilisateurConverter) {
        this.utilisateurConverter = utilisateurConverter;
    }

    public AdherantConverter getAdherantConverter() {
        return adherantConverter;
    }

    public void setAdherantConverter(AdherantConverter adherantConverter) {
        this.adherantConverter = adherantConverter;
    }

    public SocieteConverter getSocieteConverter() {
        return societeConverter;
    }

    public void setSocieteConverter(SocieteConverter societeConverter) {
        this.societeConverter = societeConverter;
    }

    public boolean isSociete() {
        return societe;
    }

    public void setSociete(boolean societe) {
        this.societe = societe;
    }

    public boolean isAdherant() {
        return adherant;
    }

    public void setAdherant(boolean adherant) {
        this.adherant = adherant;
    }
}
