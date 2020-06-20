package com.zsmart.accountingProject.ws.rest.vo;

import java.util.List;

public class ComptableVo extends UtilisateurVo {
    private String id;
    private List<AdherantVo> adherantsVo;
    private List<SocieteVo> societesVo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<AdherantVo> getAdherantsVo() {
        return adherantsVo;
    }

    public void setAdherantsVo(List<AdherantVo> adherantsVo) {
        this.adherantsVo = adherantsVo;
    }


    public List<SocieteVo> getSocietesVo() {
        return societesVo;
    }

    public void setSocietesVo(List<SocieteVo> societesVo) {
        this.societesVo = societesVo;
    }


}
