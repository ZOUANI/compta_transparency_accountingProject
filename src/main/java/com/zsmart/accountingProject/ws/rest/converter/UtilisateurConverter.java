package com.zsmart.accountingProject.ws.rest.converter;

import com.zsmart.accountingProject.bean.Utilisateur;
import com.zsmart.accountingProject.service.util.DateUtil;
import com.zsmart.accountingProject.service.util.NumberUtil;
import com.zsmart.accountingProject.service.util.StringUtil;
import com.zsmart.accountingProject.ws.rest.vo.UtilisateurVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class UtilisateurConverter extends AbstractConverter<Utilisateur, UtilisateurVo> {
    @Autowired
    private AdherantConverter adherantConverter;
    @Autowired
    private ComptableConverter comptableConverter;
    private Boolean adherant;
    private Boolean comptable;

    @Override
    public Utilisateur toItem(UtilisateurVo vo) {
        if(vo==null) {
            return null;
        }
        else{
            Utilisateur item=new Utilisateur();
            if (vo.getId() != null) {
                item.setId(NumberUtil.toLong(vo.getId()));
            }

            if (StringUtil.isNotEmpty(vo.getPrenom())) {
                item.setPrenom(vo.getPrenom());
            }
            if (StringUtil.isNotEmpty(vo.getNom())){
                item.setNom(vo.getNom());
            }
            if (StringUtil.isNotEmpty(vo.getEmail())){
                item.setEmail(vo.getEmail());
            }
            if (StringUtil.isNotEmpty(vo.getMdp())){
                item.setMdp(vo.getMdp());
            }
            if (StringUtil.isNotEmpty(vo.getAdresse())){
                item.setAdresse(vo.getAdresse());
            }
            if (StringUtil.isNotEmpty(vo.getVille())){
                item.setVille(vo.getVille());
            }
            if (vo.getRole() != null){
                item.setRole(NumberUtil.toInt(vo.getRole()));
            }
            if (vo.getDatenaissance() != null) {
                item.setDatenaissance(DateUtil.parse(vo.getDatenaissance()));
            }
            if (vo.getAdherantVo()!=null && adherant) {
                item.setAdherant(adherantConverter.toItem(vo.getAdherantVo()));
            }
            if (vo.getComptableVo()!=null && comptable) {
                item.setComptable(comptableConverter.toItem(vo.getComptableVo()));
            }

            return item;
        }    }

    @Override
    public UtilisateurVo toVo(Utilisateur item) {
        if(item==null) {
            return null;
        }
        else{
            UtilisateurVo vo=new UtilisateurVo();
            if (item.getId() != null) {
                vo.setId(NumberUtil.toString(item.getId()));
            }

            if (StringUtil.isNotEmpty(item.getPrenom())) {
                vo.setPrenom(item.getPrenom());
            }
            if (StringUtil.isNotEmpty(item.getNom())){
                vo.setNom(item.getNom());
            }
            if (StringUtil.isNotEmpty(item.getEmail())){
                vo.setEmail(item.getEmail());
            }
            if (StringUtil.isNotEmpty(item.getMdp())){
                vo.setMdp(item.getMdp());
            }
            if (StringUtil.isNotEmpty(item.getAdresse())){
                vo.setAdresse(item.getAdresse());
            }
            if (StringUtil.isNotEmpty(item.getVille())){
                vo.setVille(item.getVille());
            }
            if (item.getRole() != null){
                vo.setRole(NumberUtil.toString(item.getRole()));
            }
            if (item.getDatenaissance() != null) {
                vo.setDatenaissance(DateUtil.formateDate(item.getDatenaissance()));
            }
            if (item.getAdherant()!=null && adherant) {
                vo.setAdherantVo(adherantConverter.toVo(item.getAdherant()));
            }
            if (item.getComptable()!=null && comptable) {
                vo.setComptableVo(comptableConverter.toVo(item.getComptable()));
            }


            return vo;
    }
    }
    public Boolean isAdherant() {
        return adherant;
    }

    public void setAdherant(Boolean adherant) {
        this.adherant = adherant;
    }

    public Boolean isComptable() {
        return comptable;
    }

    public void setComptable(Boolean comptable) {
        this.comptable = comptable;
    }
}
