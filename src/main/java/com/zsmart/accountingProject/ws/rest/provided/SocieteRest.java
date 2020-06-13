package com.zsmart.accountingProject.ws.rest.provided;

import com.zsmart.accountingProject.bean.Societe;
import com.zsmart.accountingProject.service.facade.SocieteService;
import com.zsmart.accountingProject.ws.rest.converter.SocieteConverter;
import com.zsmart.accountingProject.ws.rest.vo.SocieteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public SocieteVo save(@RequestPart(value = "societe", required = true) SocieteVo societeVo,
                          @RequestPart(value = "contratBail", required = true) MultipartFile contratBail,
                          @RequestPart(value = "certificatNegatif", required = true) MultipartFile certificatnegatif,
                          @RequestPart(value = "registreComercialImage", required = true) MultipartFile registreComercialImage,
                          @RequestPart(value = "patente", required = true) MultipartFile patente,
                          @RequestPart(value = "statue", required = true) MultipartFile statue,
                          @RequestPart(value = "releverBanquaire", required = true) MultipartFile releverBanquaire,
                          @RequestPart(value = "publicationCreationBO", required = true) MultipartFile publicationCreationBO
                          ){

        Societe societe= societeConverter.toItem(societeVo);
        societeService.uploadfiles(
                contratBail,
                certificatnegatif,
                registreComercialImage,
                patente,
                statue,
                releverBanquaire,
                publicationCreationBO,
                societe
                );
        return societeConverter.toVo(societeService.save(societe));
    }
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        societeService.deleteById(id);
    }
    @GetMapping("/")
    public List<SocieteVo> findAll(){
        societeConverter.setFacture(false);
        return societeConverter.toVo(societeService.findAll());
    }

    @GetMapping("/{id}")
    public SocieteVo findById(@PathVariable Long id){
        societeConverter.getFactureConverter().setOperationComptable(true);
        societeConverter.getFactureConverter().setFactureItems(true);
        societeConverter.getFactureConverter().getFactureItemConverter().setFacture(false);
        societeConverter.getFactureConverter().getOperationComptableConverter().setTypeOperationComptable(true);
        societeConverter.getFactureConverter().getOperationComptableConverter().setSociete(false);
        societeConverter.getFactureConverter().getOperationComptableConverter().setFacture(false);
        societeConverter.setFacture(true);
        societeConverter.getFactureConverter().setSociete(false);
        return societeConverter.toVo(societeService.findById(id));
    }

    @GetMapping("/findbyscraping/{ice}")
    public SocieteVo findbyscraping(@PathVariable String ice) {

        return societeConverter.toVo(societeService.findbyscraping(ice));
    }

}
