package com.zsmart.accountingProject.ws.rest.converter;

import com.zsmart.accountingProject.bean.Admin;
import com.zsmart.accountingProject.ws.rest.vo.AdminVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminConverter extends AbstractConverter<Admin, AdminVo> {
    @Autowired
    private ComptableConverter comptableConverter;

    @Autowired
    private AdherantConverter adherantConverter;

    private boolean adherent;
    private boolean comptable;

    @Override
    public Admin toItem(AdminVo vo) {
        if (vo == null) {
            return null;
        } else {
            Admin item = new Admin();
            if (vo.getComptableVos() != null && comptable) {
                item.setComptables(comptableConverter.toItem(vo.getComptableVos()));
            }
            if (vo.getAdherantVos() != null && adherent) {
                item.setAdherants(adherantConverter.toItem(vo.getAdherantVos()));
            }

            return item;
        }
    }

    @Override
    public AdminVo toVo(Admin item) {
        if (item == null) {
            return null;
        } else {
            AdminVo vo = new AdminVo();
            if (item.getComptables() != null && comptable) {
                vo.setComptableVos(comptableConverter.toVo(item.getComptables()));
            }
            if (item.getAdherants() != null && adherent) {
                vo.setAdherantVos(adherantConverter.toVo(item.getAdherants()));
            }

            return vo;
        }
    }

    public ComptableConverter getComptableConverter() {
        return comptableConverter;
    }

    public void setComptableConverter(ComptableConverter comptableConverter) {
        this.comptableConverter = comptableConverter;
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

    public boolean isComptable() {
        return comptable;
    }

    public void setComptable(boolean comptable) {
        this.comptable = comptable;
    }
}
