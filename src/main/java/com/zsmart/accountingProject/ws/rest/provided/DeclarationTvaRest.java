package com.zsmart.accountingProject.ws.rest.provided;

import com.zsmart.accountingProject.bean.DeclarationTva;
import com.zsmart.accountingProject.bean.Facture;
import com.zsmart.accountingProject.bean.Societe;
import com.zsmart.accountingProject.service.facade.DeclarationTvaService;
import com.zsmart.accountingProject.ws.rest.converter.DeclarationTvaConverter;
import com.zsmart.accountingProject.ws.rest.converter.FactureConverter;
import com.zsmart.accountingProject.ws.rest.converter.SocieteConverter;
import com.zsmart.accountingProject.ws.rest.vo.DeclarationTvaVo;
import com.zsmart.accountingProject.ws.rest.vo.FactureVo;
import com.zsmart.accountingProject.ws.rest.vo.SocieteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountingProject/DeclarationTva")
@CrossOrigin(origins ="http://localhost:4200")

public class DeclarationTvaRest {
    @Autowired
    private DeclarationTvaConverter declarationTvaConverter;
    @Autowired
    private DeclarationTvaService declarationTvaService;
    @Autowired
    private FactureConverter factureConverter;
    @Autowired
    private SocieteConverter societeConverter;


    @GetMapping("/findbyid/{id}")
    public DeclarationTvaVo findById(@PathVariable Long id) {
        declarationTvaConverter.setSociete(true);
        declarationTvaConverter.setFacture(true);

        return declarationTvaConverter.toVo(declarationTvaService.findById(id));
    }
    @PostMapping("/save/")
    public int save(@RequestBody DeclarationTvaVo declarationTvaVo) {
        declarationTvaConverter.setSociete(true);
        declarationTvaConverter.setFacture(true);
        factureConverter.setDeclarationtva(false);
        DeclarationTva declaration= declarationTvaConverter.toItem(declarationTvaVo);
        declarationTvaService.save(declaration);
    return 1;
    }
    @PostMapping("/findBySocieteAnneeTrimestre" )
    public DeclarationTvaVo findBySocieteAnneeTrimestre(@RequestBody FactureVo factureVo) {
    factureConverter.setDeclarationtva(false);
    factureConverter.setSociete(true);

        Facture facture= factureConverter.toItem(factureVo);
        declarationTvaConverter.setFacture(true);
        declarationTvaConverter.setSociete(true);
        return declarationTvaConverter.toVo(declarationTvaService.findbyFacture(facture));
    }
    @GetMapping("/")
    public List<DeclarationTva> findAll() {
        return declarationTvaService.findAll();
    }
    @PostMapping("/update/")
    public DeclarationTvaVo update(@RequestBody DeclarationTvaVo declarationTvaVo)  {
        declarationTvaConverter.setSociete(true);
        System.out.println(declarationTvaConverter.isFacture());
        DeclarationTva declaration= declarationTvaConverter.toItem(declarationTvaVo);
        declarationTvaConverter.setFacture(true);

        return declarationTvaConverter.toVo(declarationTvaService.update(declaration));    }

    @PostMapping("/findbysociete/" )
    public List<DeclarationTvaVo> findbysociete(@RequestBody SocieteVo societeVo) {

        Societe societe = societeConverter.toItem(societeVo);
        declarationTvaConverter.setFacture(true);
        declarationTvaConverter.setSociete(true);
        return declarationTvaConverter.toVo(declarationTvaService.findByCriteria(societe));
    }

}
