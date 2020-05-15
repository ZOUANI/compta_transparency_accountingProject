package com.zsmart.accountingProject.ws.rest.vo;


import java.util.List;

public class SocieteVo {
    private String id;
    private String raisonSocial;
    private String ice;
    private String identifiantFiscal;
    private List<FactureVo> facturesVo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRaisonSocial() {
        return raisonSocial;
    }

    public void setRaisonSocial(String raisonSocial) {
        this.raisonSocial = raisonSocial;
    }

    public String getIce() {
        return ice;
    }

    public List<FactureVo> getFacturesVo() {
        return facturesVo;
    }

    public void setFacturesVo(List<FactureVo> facturesVo) {
        this.facturesVo = facturesVo;
    }

    public void setIce(String ice) {
        this.ice = ice;
    }

    public String getIdentifiantFiscal() {
        return identifiantFiscal;
    }

    public void setIdentifiantFiscal(String identifiantFiscal) {
        this.identifiantFiscal = identifiantFiscal;
    }
}
