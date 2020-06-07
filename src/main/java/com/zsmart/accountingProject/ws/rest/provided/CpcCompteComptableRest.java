package com.zsmart.accountingProject.ws.rest.provided;

import com.zsmart.accountingProject.bean.Cpc;
import com.zsmart.accountingProject.bean.CpcCompteComptable;
import com.zsmart.accountingProject.service.facade.CpcCompteComptableService;
import com.zsmart.accountingProject.ws.rest.converter.CpcCompteComptableConverter;
import com.zsmart.accountingProject.ws.rest.converter.CpcConverter;
import com.zsmart.accountingProject.ws.rest.vo.CpcCompteComptableVo;
import com.zsmart.accountingProject.ws.rest.vo.CpcVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/accountingProject/CpcCompteComptable")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CpcCompteComptableRest {

    @Autowired
    private CpcCompteComptableService cpcCompteComptableService;

    @Autowired
    private CpcCompteComptableConverter cpcCompteComptableConverter;

    @Autowired
    private CpcConverter cpcConverter;

    @PostMapping("/")
    public CpcCompteComptableVo save(@RequestBody CpcCompteComptableVo cpcCompteComptableVo) {
        CpcCompteComptable cpcCompteComptable = cpcCompteComptableConverter.toItem(cpcCompteComptableVo);
        return cpcCompteComptableConverter.toVo(cpcCompteComptableService.save(cpcCompteComptable));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        cpcCompteComptableService.deleteById(id);
    }

    @GetMapping("/")
    public List<CpcCompteComptableVo> findAll() {
        return cpcCompteComptableConverter.toVo(cpcCompteComptableService.findAll());
    }

    @PostMapping("/find/")
    public List<CpcCompteComptableVo> find(@RequestBody CpcVo cpcVo) {
        cpcConverter.getSocieteConverter().setFacture(false);
        Cpc cpc = cpcConverter.toItem(cpcVo);
        cpcCompteComptableConverter.setCompteComptable(true);
        cpcCompteComptableConverter.setCpcSousClasse(true);
        List<CpcCompteComptableVo> cpcCompteComptableVos = new ArrayList<>();
        cpcCompteComptableVos.addAll(cpcCompteComptableConverter.toVo(cpcCompteComptableService.findCpcCompteComptable(cpc.getDateDebut(), cpc.getDateFin(), 6, cpc.getSociete().getId())));
        cpcCompteComptableVos.addAll(cpcCompteComptableConverter.toVo(cpcCompteComptableService.findCpcCompteComptable(cpc.getDateDebut(), cpc.getDateFin(), 7, cpc.getSociete().getId())));
        return cpcCompteComptableVos;
    }

    public CpcCompteComptableConverter getCpcCompteComptableConverter() {
        return cpcCompteComptableConverter;
    }

    public void setCpcCompteComptableConverter(CpcCompteComptableConverter cpcCompteComptableConverter) {
        this.cpcCompteComptableConverter = cpcCompteComptableConverter;
    }

    public CpcCompteComptableService getCpcCompteComptableService() {
        return cpcCompteComptableService;
    }

	public void setCpcCompteComptableService(CpcCompteComptableService cpcCompteComptableService) {
		this.cpcCompteComptableService = cpcCompteComptableService;
	}

}