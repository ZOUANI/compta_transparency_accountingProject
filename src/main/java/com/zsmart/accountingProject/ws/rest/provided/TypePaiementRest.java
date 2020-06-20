package com.zsmart.accountingProject.ws.rest.provided;


import com.zsmart.accountingProject.bean.TypePaiement;
import com.zsmart.accountingProject.service.facade.TypePaiementService;
import com.zsmart.accountingProject.ws.rest.converter.TypePaiementConverter;
import com.zsmart.accountingProject.ws.rest.vo.TypePaiementVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountingProject/TypePaiement")
@CrossOrigin(origins = {"http://localhost:4200"})
public class TypePaiementRest {

    @Autowired
    private TypePaiementService typePaiementService;

    @Autowired
    private TypePaiementConverter typePaiementConverter;

    @PreAuthorize("hasRole('ADMIN') OR hasRole('ADHERENT') ")
    @PostMapping("/")
    public TypePaiementVo save(@RequestBody TypePaiementVo typePaiementVo) {
        TypePaiement typePaiement = typePaiementConverter.toItem(typePaiementVo);
        return typePaiementConverter.toVo(typePaiementService.save(typePaiement));
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('ADHERENT') ")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        typePaiementService.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('ADHERENT') or hasRole('COMPTABLE') ")
    @GetMapping("/")
    public List<TypePaiementVo> findAll() {
        return typePaiementConverter.toVo(typePaiementService.findAll());
    }

    public TypePaiementConverter getTypePaiementConverter() {
        return typePaiementConverter;
    }

    public void setTypePaiementConverter(TypePaiementConverter typePaiementConverter) {
        this.typePaiementConverter = typePaiementConverter;
    }

    public TypePaiementService getTypePaiementService() {
        return typePaiementService;
    }

    public void setTypePaiementService(TypePaiementService typePaiementService) {
        this.typePaiementService = typePaiementService;
    }

}