package com.zsmart.accountingProject.ws.rest.converter;

import com.zsmart.accountingProject.bean.Journal;
import com.zsmart.accountingProject.service.util.DateUtil;
import com.zsmart.accountingProject.service.util.ListUtil;
import com.zsmart.accountingProject.service.util.NumberUtil;
import com.zsmart.accountingProject.service.util.StringUtil;
import com.zsmart.accountingProject.ws.rest.vo.JournalVo;
import com.zsmart.accountingProject.ws.rest.vo.OperationComptableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class JournalConverter extends AbstractConverter<Journal, JournalVo>{
    @Autowired
    private OperationComptableConverter operationComptableConverter;
    private Boolean operationComptable;
    @Override
    public Journal toItem(JournalVo vo) {
        if (vo == null) {
            return null;
        } else {
            Journal item = new Journal();
            if (StringUtil.isNotEmpty(vo.getLibele())) {
                item.setLibele(vo.getLibele());
            }
            if (vo.getId() != null) {
                item.setId(NumberUtil.toLong(vo.getId()));
            }
            if (vo.getDatedebut() != null) {
                item.setDatedebut(DateUtil.parse(vo.getDatedebut()));
            }
            if (vo.getDatefin() != null) {
                item.setDatefin(DateUtil.parse(vo.getDatefin()));
            }
            if (ListUtil.isNotEmpty(vo.getOperationComptableVos ()) && operationComptable) {
                item.setOperationComptables(operationComptableConverter.toItem(vo.getOperationComptableVos()));
            }
            return item;
        }

    }

    @Override
    public JournalVo toVo(Journal item) {
        if (item == null) {
            return null;
        } else {
            JournalVo vo = new JournalVo();
            if (item.getId() != null) {
                vo.setId(NumberUtil.toString(item.getId()));
            }
            if (StringUtil.isNotEmpty(item.getLibele())) {
                vo.setLibele(item.getLibele());
            }
            if (item.getDatefin() != null) {
                vo.setDatefin(DateUtil.formateDate(item.getDatefin()));
            }
            if (item.getDatedebut() != null) {
                vo.setDatedebut(DateUtil.formateDate(item.getDatedebut()));
            }
            if(ListUtil.isNotEmpty(item.getOperationComptables()) && operationComptable) {
                vo.setOperationComptableVos(operationComptableConverter.toVo(item.getOperationComptables()));
            }
            return vo;

        }
    }

    public Boolean getOperationComptable() {
        return operationComptable;
    }

    public void setOperationComptable(Boolean operationComptable) {
        this.operationComptable = operationComptable;
    }
}
