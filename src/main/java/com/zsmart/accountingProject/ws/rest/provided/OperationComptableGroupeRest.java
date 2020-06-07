package com.zsmart.accountingProject.ws.rest.provided ;


import com.zsmart.accountingProject.bean.OperationComptableGroupe;
import com.zsmart.accountingProject.service.facade.OperationComptableGroupeService;
import com.zsmart.accountingProject.ws.rest.converter.OperationComptableGroupeConverter;
import com.zsmart.accountingProject.ws.rest.vo.OperationComptableGroupeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountingProject/OperationComptableGroupe")
@CrossOrigin(origins = {"http://localhost:4200"})
public class OperationComptableGroupeRest {

    @Autowired
    private OperationComptableGroupeService operationComptableGroupeService;

    @Autowired
    private OperationComptableGroupeConverter operationComptableGroupeConverter;

    @PostMapping("/")
    public OperationComptableGroupeVo save(@RequestBody OperationComptableGroupeVo operationComptableGroupeVo) {
        OperationComptableGroupe operationComptableGroupe = operationComptableGroupeConverter.toItem(operationComptableGroupeVo);
        return operationComptableGroupeConverter.toVo(operationComptableGroupeService.save(operationComptableGroupe));
    }

    @PostMapping("/saveWithOps/")
    public OperationComptableGroupeVo saveWithOperations(@RequestBody OperationComptableGroupeVo operationComptableGroupeVo) {
        operationComptableGroupeConverter.setOperationComptables(true);
        operationComptableGroupeConverter.getOperationComptableConverter().setOperationComptableGroupe(false);
        operationComptableGroupeConverter.getOperationComptableConverter().setTypeOperationComptable(true);
        operationComptableGroupeConverter.getOperationComptableConverter().setSociete(true);
        operationComptableGroupeConverter.getOperationComptableConverter().setOperationComptableGroupe(false);
        operationComptableGroupeConverter.getOperationComptableConverter().getSocieteConverter().setFacture(false);
        OperationComptableGroupe operationComptableGroupe = operationComptableGroupeConverter.toItem(operationComptableGroupeVo);
        return operationComptableGroupeConverter.toVo(operationComptableGroupeService.saveWithOperationComptables(operationComptableGroupe));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        operationComptableGroupeService.deleteById(id);
    }

    @GetMapping("/")
    public List<OperationComptableGroupeVo> findAll() {
        operationComptableGroupeConverter.getOperationComptableConverter().setOperationComptableGroupe(false);
        return operationComptableGroupeConverter.toVo(operationComptableGroupeService.findAll());
    }

    @GetMapping("/id/{id}")
    public OperationComptableGroupeVo findById(@PathVariable Long id) {
        operationComptableGroupeConverter.getOperationComptableConverter().setOperationComptableGroupe(false);
        operationComptableGroupeConverter.getOperationComptableConverter().setSociete(true);
        operationComptableGroupeConverter.getOperationComptableConverter().setCaisse(true);
        operationComptableGroupeConverter.getOperationComptableConverter().setCompteBanquaire(true);
        operationComptableGroupeConverter.getOperationComptableConverter().setTypeOperationComptable(true);
        operationComptableGroupeConverter.setOperationComptables(true);
        return operationComptableGroupeConverter.toVo(operationComptableGroupeService.findById(id));
    }

    public OperationComptableGroupeConverter getOperationComptableGroupeConverter() {
        return operationComptableGroupeConverter;
    }

    public void setOperationComptableGroupeConverter(OperationComptableGroupeConverter operationComptableGroupeConverter) {
        this.operationComptableGroupeConverter = operationComptableGroupeConverter;
    }

    public OperationComptableGroupeService getOperationComptableGroupeService() {
        return operationComptableGroupeService;
    }

    public void setOperationComptableGroupeService(OperationComptableGroupeService operationComptableGroupeService) {
        this.operationComptableGroupeService = operationComptableGroupeService;
    }

}