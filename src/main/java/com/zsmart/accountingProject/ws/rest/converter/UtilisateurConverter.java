package com.zsmart.accountingProject.ws.rest.converter;

import com.zsmart.accountingProject.bean.Utilisateur;
import com.zsmart.accountingProject.service.util.DateUtil;
import com.zsmart.accountingProject.service.util.NumberUtil;
import com.zsmart.accountingProject.service.util.StringUtil;
import com.zsmart.accountingProject.ws.rest.vo.UtilisateurVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component

public class UtilisateurConverter extends AbstractConverter<Utilisateur, UtilisateurVo> {
    @Autowired
    private AdherantConverter adherantConverter;
    @Autowired
    private ComptableConverter comptableConverter;
    @Autowired
    private RoleConverter roleConverter;

    @Override
    public Utilisateur toItem(UtilisateurVo vo) {
        if (vo == null) {
            return null;
        } else {
            Utilisateur item = new Utilisateur();
            if (vo.getId() != null) {
                item.setId(NumberUtil.toLong(vo.getId()));
            }

            if (StringUtil.isNotEmpty(vo.getPrenom())) {
                item.setPrenom(vo.getPrenom());
            }
            if (StringUtil.isNotEmpty(vo.getNom())) {
                item.setNom(vo.getNom());
            }
            if (StringUtil.isNotEmpty(vo.getEmail())) {
                item.setEmail(vo.getEmail());
            }
            if (StringUtil.isNotEmpty(vo.getAdresse())) {
                item.setAdresse(vo.getAdresse());
            }
            if (StringUtil.isNotEmpty(vo.getVille())) {
                item.setVille(vo.getVille());
            }
            if (vo.getAuthorities() != null) {
                item.setAuthorities(roleConverter.toItem(vo.getAuthorities()));
            }

            if (vo.getDatenaissance() != null) {
                item.setDatenaissance(DateUtil.parse(vo.getDatenaissance()));
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
            if (StringUtil.isNotEmpty(item.getNom())) {
                vo.setNom(item.getNom());
            }
            if (StringUtil.isNotEmpty(item.getEmail())) {
                vo.setEmail(item.getEmail());
            }

            if (StringUtil.isNotEmpty(item.getAdresse())) {
                vo.setAdresse(item.getAdresse());
            }
            if (StringUtil.isNotEmpty(item.getVille())) {
                vo.setVille(item.getVille());
            }
            if (item.getAuthorities() != null) {
                vo.setAuthorities(roleConverter.toVo(new ArrayList<>(item.getAuthorities())));
            }

            if (item.getDatenaissance() != null) {
                vo.setDatenaissance(DateUtil.formateDate(item.getDatenaissance()));
            }


            return vo;
        }
    }

}
