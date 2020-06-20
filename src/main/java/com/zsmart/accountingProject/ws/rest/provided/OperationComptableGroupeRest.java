package com.zsmart.accountingProject.ws.rest.provided ;


import com.zsmart.accountingProject.bean.OperationComptableGroupe;
import com.zsmart.accountingProject.service.facade.OperationComptableGroupeService;
import com.zsmart.accountingProject.ws.rest.converter.OperationComptableGroupeConverter;
import com.zsmart.accountingProject.ws.rest.vo.OperationComptableGroupeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/accountingProject/OperationComptableGroupe")
@CrossOrigin(origins = {"http://localhost:4200"})
public class OperationComptableGroupeRest {

    @Autowired
    private OperationComptableGroupeService operationComptableGroupeService;

    @Autowired
    private OperationComptableGroupeConverter operationComptableGroupeConverter;

    @PreAuthorize("hasRole('ADMIN') ")
    @PostMapping("/")
    public OperationComptableGroupeVo save(@RequestBody OperationComptableGroupeVo operationComptableGroupeVo) {
        OperationComptableGroupe operationComptableGroupe = operationComptableGroupeConverter.toItem(operationComptableGroupeVo);
        return operationComptableGroupeConverter.toVo(operationComptableGroupeService.save(operationComptableGroupe));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('COMPTABLE') ")
    @PostMapping("/saveWithOps/")
    public OperationComptableGroupeVo saveWithOperations(@RequestBody OperationComptableGroupeVo operationComptableGroupeVo) {
        operationComptableGroupeConverter.setOperationComptables(true);
        operationComptableGroupeConverter.setAdherant(true);
        operationComptableGroupeConverter.getOperationComptableConverter().setOperationComptableGroupe(false);
        operationComptableGroupeConverter.getOperationComptableConverter().setTypeOperationComptable(true);
        operationComptableGroupeConverter.getOperationComptableConverter().setCaisse(true);
        operationComptableGroupeConverter.getOperationComptableConverter().setCompteBanquaire(true);
        operationComptableGroupeConverter.getOperationComptableConverter().setSociete(true);
        operationComptableGroupeConverter.getOperationComptableConverter().setAdherent(true);
        operationComptableGroupeConverter.getOperationComptableConverter().setOperationComptableGroupe(false);
        operationComptableGroupeConverter.getOperationComptableConverter().getSocieteConverter().setFacture(false);
        OperationComptableGroupe operationComptableGroupe = operationComptableGroupeConverter.toItem(operationComptableGroupeVo);
        return operationComptableGroupeConverter.toVo(operationComptableGroupeService.saveWithOperationComptables(operationComptableGroupe));
    }

    @PreAuthorize("hasRole('ADMIN') ")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        operationComptableGroupeService.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN') ")
    @GetMapping("/")
    public List<OperationComptableGroupeVo> findAll() {
        operationComptableGroupeConverter.getOperationComptableConverter().setOperationComptableGroupe(false);
        return operationComptableGroupeConverter.toVo(operationComptableGroupeService.findAll());
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('COMPTABLE') ")
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

    @GetMapping("/findByUser/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('COMPTABLE')")
    public List<OperationComptableGroupeVo> findByAdherent(@PathVariable Long id) {
        return operationComptableGroupeConverter.toVo(operationComptableGroupeService.findByAdherantId(id));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('COMPTABLE')")
    @GetMapping("/adherent/{adherentId}/id/{id}")
    public OperationComptableGroupeVo findByAdherentAndId(@PathVariable Long adherentId, @PathVariable Long id) {
        try {
            operationComptableGroupeConverter.setOperationComptables(true);
            operationComptableGroupeConverter.setAdherant(true);
            operationComptableGroupeConverter.getOperationComptableConverter().setTypeOperationComptable(true);
            operationComptableGroupeConverter.getOperationComptableConverter().setCompteComptable(true);
            return operationComptableGroupeConverter.toVo(operationComptableGroupeService.findByAdherantIdAndId(adherentId, id));
        } catch (EntityNotFoundException e) {
            return null;
        }

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