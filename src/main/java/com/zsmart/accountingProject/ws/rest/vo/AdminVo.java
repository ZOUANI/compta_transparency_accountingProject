package com.zsmart.accountingProject.ws.rest.vo;

import java.util.List;

public class AdminVo {
    private List<AdherantVo> adherantVos;
    private List<ComptableVo> comptableVos;

    public List<AdherantVo> getAdherantVos() {
        return adherantVos;
    }

    public void setAdherantVos(List<AdherantVo> adherantVos) {
        this.adherantVos = adherantVos;
    }

    public List<ComptableVo> getComptableVos() {
        return comptableVos;
    }

    public void setComptableVos(List<ComptableVo> comptableVos) {
        this.comptableVos = comptableVos;
    }
}
