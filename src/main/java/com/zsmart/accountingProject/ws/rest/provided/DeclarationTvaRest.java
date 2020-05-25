package com.zsmart.accountingProject.ws.rest.provided;

import com.zsmart.accountingProject.bean.DeclarationTva;
import com.zsmart.accountingProject.service.facade.DeclarationTvaService;
import com.zsmart.accountingProject.ws.rest.converter.DeclarationTvaConverter;
import com.zsmart.accountingProject.ws.rest.vo.DeclarationTvaVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountingProject/DeclarationTva")
@CrossOrigin(origins ="http://localhost:4200")

public class DeclarationTvaRest {

    @Autowired
    private DeclarationTvaService declarationTvaService;

    @GetMapping("/findbyid/{id}")
    public DeclarationTva findById(@PathVariable Long id) {

        return declarationTvaService.findById(id);
    }
    @PostMapping("/save/")
    public DeclarationTvaVo save(@RequestBody DeclarationTvaVo declarationTvaVo) {
    DeclarationTva declaration= new DeclarationTvaConverter().toItem(declarationTvaVo);

    return new DeclarationTvaConverter().toVo(declarationTvaService.save(declaration));
    }
    @GetMapping("/")
    public List<DeclarationTva> findAll() {
        return declarationTvaService.findAll();
    }


}
