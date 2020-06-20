package com.zsmart.accountingProject.ws.rest.converter;

import com.zsmart.accountingProject.bean.CompteBanquaire;
import com.zsmart.accountingProject.service.util.ListUtil;
import com.zsmart.accountingProject.service.util.NumberUtil;
import com.zsmart.accountingProject.service.util.StringUtil;
import com.zsmart.accountingProject.ws.rest.vo.CompteBanquaireVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CompteBanquaireConverter extends AbstractConverter<CompteBanquaire, CompteBanquaireVo> {

    private boolean banque;

    @Autowired
    private BanqueConverter banqueConverter;
    private boolean operationComptables;

    @Autowired
    private OperationComptableConverter operationComptableConverter;
    @Autowired
    private AdherantConverter adherantConverter;
    @Autowired
    private ComptableConverter comptableConverter;
    private boolean adherant;
    private boolean comptable;

    @Override
    public CompteBanquaire toItem(CompteBanquaireVo vo) {
        if (vo == null) {
            return null;
        } else {
            CompteBanquaire item = new CompteBanquaire();

            if (banque && vo.getBanqueVo() != null) {
                item.setBanque(banqueConverter.toItem(vo.getBanqueVo()));
            }

            if (StringUtil.isNotEmpty(vo.getLibelle())) {
                item.setLibelle(vo.getLibelle());
            }

            if (StringUtil.isNotEmpty(vo.getCode())) {
                item.setCode(vo.getCode());
            }

            if (vo.getId() != null) {
                item.setId(NumberUtil.toLong(vo.getId()));
            }

            if (vo.getSolde() != null) {
                item.setSolde(NumberUtil.toBigDecimal(vo.getSolde()));
            }

            if (ListUtil.isNotEmpty(vo.getOperationComptablesVo()) && operationComptables) {
                item.setOperationComptables(operationComptableConverter.toItem(vo.getOperationComptablesVo()));
            }
            if (vo.getAdherantVo() != null && adherant) {
                item.setAdherant(adherantConverter.toItem(vo.getAdherantVo()));
            }

            return item;
        }
    }

    @Override
    public CompteBanquaireVo toVo(CompteBanquaire item) {
        if (item == null) {
            return null;
        } else {
            CompteBanquaireVo vo = new CompteBanquaireVo();

            if (banque && item.getBanque() != null) {
                vo.setBanqueVo(banqueConverter.toVo(item.getBanque()));
            }

            if (StringUtil.isNotEmpty(item.getLibelle())) {
                vo.setLibelle(item.getLibelle());
            }

            if (StringUtil.isNotEmpty(item.getCode())) {
                vo.setCode(item.getCode());
            }

            if (item.getId() != null) {
                vo.setId(NumberUtil.toString(item.getId()));
            }

            if (item.getSolde() != null) {
                vo.setSolde(NumberUtil.toString(item.getSolde()));
            }

            if (ListUtil.isNotEmpty(item.getOperationComptables()) && operationComptables) {
                vo.setOperationComptablesVo(operationComptableConverter.toVo(item.getOperationComptables()));
            }
            if (item.getAdherant() != null && adherant) {
                vo.setAdherantVo(adherantConverter.toVo(item.getAdherant()));
            }

            return vo;
        }
    }

    public void init() {

        banque = true;

        operationComptables = true;
    }

    public boolean isBanque() {
        return banque;
    }

    public void setBanque(boolean banque) {
        this.banque = banque;
    }

    public BanqueConverter getBanqueConverter() {
        return banqueConverter;
    }

    public void setBanqueConverter(BanqueConverter banqueConverter) {
        this.banqueConverter = banqueConverter;
    }

    public boolean isOperationComptables() {
        return operationComptables;
    }

    public void setOperationComptables(boolean operationComptables) {
        this.operationComptables = operationComptables;
    }

    public OperationComptableConverter getOperationComptableConverter() {
        return operationComptableConverter;
    }

    public void setOperationComptableConverter(OperationComptableConverter operationComptableConverter) {
        this.operationComptableConverter = operationComptableConverter;
    }

    public AdherantConverter getAdherantConverter() {
        return adherantConverter;
    }

    public void setAdherantConverter(AdherantConverter adherantConverter) {
        this.adherantConverter = adherantConverter;
    }

    public ComptableConverter getComptableConverter() {
        return comptableConverter;
    }

    public void setComptableConverter(ComptableConverter comptableConverter) {
        this.comptableConverter = comptableConverter;
    }

    public boolean isAdherant() {
        return adherant;
    }

    public boolean isComptable() {
        return comptable;
    }

    public Boolean getAdherant() {
        return adherant;
    }

    public void setAdherant(boolean adherant) {
        this.adherant = adherant;
    }

    public Boolean getComptable() {
        return comptable;
    }

    public void setComptable(boolean comptable) {
        this.comptable = comptable;
    }
}
