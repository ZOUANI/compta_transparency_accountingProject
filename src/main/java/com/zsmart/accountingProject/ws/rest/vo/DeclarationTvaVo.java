package com.zsmart.accountingProject.ws.rest.vo;

import java.util.List;

public class DeclarationTvaVo {
    private String id;
    private String totalTvaGain;
    private String totalTvaCharge;
    private String cotisation;
    private String differenceChargeGain;
    private List<FactureVo> facturesGain;
    private List<FactureVo> facturescharge;
    private SocieteVo societeVo;

    public SocieteVo getSocieteVo() {
        return societeVo;
    }

    public void setSocieteVo(SocieteVo societeVo) {
        this.societeVo = societeVo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public List<FactureVo> getFacturesGain() {
        return facturesGain;
    }

    public void setFacturesGain(List<FactureVo> facturesGain) {
        this.facturesGain = facturesGain;
    }

    public List<FactureVo> getFacturescharge() {
        return facturescharge;
    }

    public void setFacturescharge(List<FactureVo> facturescharge) {
        this.facturescharge = facturescharge;
    }
}
