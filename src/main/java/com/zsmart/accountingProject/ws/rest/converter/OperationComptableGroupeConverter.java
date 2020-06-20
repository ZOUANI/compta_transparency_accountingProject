package com.zsmart.accountingProject.ws.rest.converter;

import com.zsmart.accountingProject.bean.OperationComptableGroupe;
import com.zsmart.accountingProject.service.util.DateUtil;
import com.zsmart.accountingProject.service.util.ListUtil;
import com.zsmart.accountingProject.service.util.NumberUtil;
import com.zsmart.accountingProject.service.util.StringUtil;
import com.zsmart.accountingProject.ws.rest.vo.OperationComptableGroupeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OperationComptableGroupeConverter extends AbstractConverter<OperationComptableGroupe, OperationComptableGroupeVo> {

    private boolean operationComptables;
    @Autowired
    private AdherantConverter adherantConverter;
    @Autowired
    private ComptableConverter comptableConverter;
    private boolean adherant;
    private boolean comptable;
    @Autowired
    private OperationComptableConverter operationComptableConverter;

    @Override
    public OperationComptableGroupe toItem(OperationComptableGroupeVo vo) {
        if (vo == null) {
            return null;
        } else {
            OperationComptableGroupe item = new OperationComptableGroupe();

            if (StringUtil.isNotEmpty(vo.getLibelle())) {
                item.setLibelle(vo.getLibelle());
            }

            if (StringUtil.isNotEmpty(vo.getCode())) {
                item.setCode(vo.getCode());
            }
            if (vo.getDateSaisie() != null) {
                item.setDateSaisie(DateUtil.parse(vo.getDateSaisie()));
            }
            if (vo.getId() != null) {
                item.setId(NumberUtil.toLong(vo.getId()));
            }

            if (ListUtil.isNotEmpty(vo.getOperationComptablesVo()) && operationComptables) {
                item.setOperationComptables(operationComptableConverter.toItem(vo.getOperationComptablesVo()));
            }
            if (vo.getAdherantVo()!=null && adherant) {
                item.setAdherant(adherantConverter.toItem(vo.getAdherantVo()));
            }

            return item;
        }
    }

    @Override
    public OperationComptableGroupeVo toVo(OperationComptableGroupe item) {
        if (item == null) {
            return null;
        } else {
            OperationComptableGroupeVo vo = new OperationComptableGroupeVo();

            if (StringUtil.isNotEmpty(item.getLibelle())) {
                vo.setLibelle(item.getLibelle());
            }

            if (StringUtil.isNotEmpty(item.getCode())) {
                vo.setCode(item.getCode());
            }

            if (item.getId() != null) {
                vo.setId(NumberUtil.toString(item.getId()));
            }

            if (ListUtil.isNotEmpty(item.getOperationComptables()) && operationComptables) {
                vo.setOperationComptablesVo(operationComptableConverter.toVo(item.getOperationComptables()));
            }
            if (item.getAdherant()!=null && adherant) {
                vo.setAdherantVo(adherantConverter.toVo(item.getAdherant()));
            }

            if (item.getDateSaisie() != null) {
                vo.setDateSaisie(DateUtil.formateDate(item.getDateSaisie()));
            }

            return vo;
        }
    }

    public void init() {

        operationComptables = true;
    }
    public Boolean isAdherant() {
        return adherant;
    }

    public void setAdherant(boolean adherant) {
        this.adherant = adherant;
    }

    public boolean isComptable() {
        return comptable;
    }

    public void setComptable(Boolean comptable) {
        this.comptable = comptable;
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
}
