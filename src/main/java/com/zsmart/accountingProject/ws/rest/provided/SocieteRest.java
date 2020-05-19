package com.zsmart.accountingProject.ws.rest.provided;

import com.zsmart.accountingProject.bean.PaiementFacture;
import com.zsmart.accountingProject.bean.Societe;
import com.zsmart.accountingProject.service.facade.SocieteService;
import com.zsmart.accountingProject.ws.rest.converter.SocieteConverter;
import com.zsmart.accountingProject.ws.rest.vo.PaiementFactureVo;
import com.zsmart.accountingProject.ws.rest.vo.SocieteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountingProject/Societe")
@CrossOrigin(origins = {"http://localhost:4200"})
public class SocieteRest {

    @Autowired
    private SocieteService societeService;
    @Autowired
    private SocieteConverter societeConverter;


    @PostMapping("/")
    public SocieteVo save(@RequestBody SocieteVo societeVo){
        Societe societe= societeConverter.toItem(societeVo);
        return societeConverter.toVo(societeService.save(societe));
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        societeService.deleteById(id);
    }
    @GetMapping("/")
    public List<SocieteVo> findAll(){
        return societeConverter.toVo(societeService.findAll());
    }

    @GetMapping("/{id}")
    public SocieteVo findById(@PathVariable Long id){
        societeConverter.getFactureConverter().setOperationComptable(true);
        societeConverter.getFactureConverter().setFactureItems(true);
        societeConverter.getFactureConverter().getOperationComptableConverter().setTypeOperationComptable(true);
        societeConverter.setFacture(true);
        return societeConverter.toVo(societeService.findById(id));
    }


}
