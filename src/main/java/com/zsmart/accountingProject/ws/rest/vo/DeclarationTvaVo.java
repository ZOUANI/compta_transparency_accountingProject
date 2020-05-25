package com.zsmart.accountingProject.ws.rest.vo;

import java.util.List;

public class DeclarationTvaVo {
    private Long id;
    private String totalTvaGain;
    private String totalTvaCharge;
    private String cotisation;
    private String differenceChargeGain;
    private List<FactureVo> facturesVo;
    private SocieteVo societeVo;

    public SocieteVo getSocieteVo() {
        return societeVo;
    }

    public void setSocieteVo(SocieteVo societeVo) {
        this.societeVo = societeVo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTotalTvaGain() {
        return totalTvaGain;
    }

    public void setTotalTvaGain(String totalTvaGain) {
        this.totalTvaGain = totalTvaGain;
    }

    public String getTotalTvaCharge() {
        return totalTvaCharge;
    }

    public void setTotalTvaCharge(String totalTvaCharge) {
        this.totalTvaCharge = totalTvaCharge;
    }

    public String getCotisation() {
        return cotisation;
    }

    public void setCotisation(String cotisation) {
        this.cotisation = cotisation;
    }

    public String getDifferenceChargeGain() {
        return differenceChargeGain;
    }

    public void setDifferenceChargeGain(String differenceChargeGain) {
        this.differenceChargeGain = differenceChargeGain;
    }

    public List<FactureVo> getFacturesVo() {
        return facturesVo;
    }

    public void setFacturesVo(List<FactureVo> facturesVo) {
        this.facturesVo = facturesVo;
    }
}
