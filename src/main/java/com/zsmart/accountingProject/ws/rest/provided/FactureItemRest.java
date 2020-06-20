package com.zsmart.accountingProject.ws.rest.provided;


import com.zsmart.accountingProject.bean.FactureItem;
import com.zsmart.accountingProject.service.facade.FactureItemService;
import com.zsmart.accountingProject.ws.rest.converter.FactureItemConverter;
import com.zsmart.accountingProject.ws.rest.vo.FactureItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountingProject/FactureItem")
@CrossOrigin(origins = {"http://localhost:4200"})
public class FactureItemRest {

    @Autowired
    private FactureItemService factureItemService;

    @Autowired
    private FactureItemConverter factureItemConverter;

    @PreAuthorize("hasRole('ADMIN') ")
    @PostMapping("/")
    public FactureItemVo save(@RequestBody FactureItemVo factureItemVo) {
        FactureItem factureItem = factureItemConverter.toItem(factureItemVo);
        return factureItemConverter.toVo(factureItemService.save(factureItem));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT')")
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        factureItemService.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN') ")
    @GetMapping("/")
    public List<FactureItemVo> findAll() {
        return factureItemConverter.toVo(factureItemService.findAll());
    }

    public FactureItemConverter getFactureItemConverter() {
        return factureItemConverter;
    }

    public void setFactureItemConverter(FactureItemConverter factureItemConverter) {
        this.factureItemConverter = factureItemConverter;
    }

    public FactureItemService getFactureItemService() {
        return factureItemService;
    }

    public void setFactureItemService(FactureItemService factureItemService) {
        this.factureItemService = factureItemService;
    }

}