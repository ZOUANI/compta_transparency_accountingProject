package com.zsmart.accountingProject.ws.rest.provided;


import com.zsmart.accountingProject.bean.PaiementFacture;
import com.zsmart.accountingProject.service.facade.PaiementFactureService;
import com.zsmart.accountingProject.ws.rest.converter.PaiementFactureConverter;
import com.zsmart.accountingProject.ws.rest.vo.PaiementFactureVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountingProject/PaiementFacture")
@CrossOrigin(origins = {"http://localhost:4200"})
public class PaiementFactureRest {

    @Autowired
    private PaiementFactureService paiementFactureService;

    @Autowired
    private PaiementFactureConverter paiementFactureConverter;

    @PreAuthorize("hasRole('ADMIN') ")
    @PostMapping("/")
    public PaiementFactureVo save(@RequestBody PaiementFactureVo paiementFactureVo) {
        PaiementFacture paiementFacture = paiementFactureConverter.toItem(paiementFactureVo);
        return paiementFactureConverter.toVo(paiementFactureService.save(paiementFacture));
    }

    @PreAuthorize("hasRole('ADMIN') ")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        paiementFactureService.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN') ")
    @GetMapping("/")
    public List<PaiementFactureVo> findAll() {
        return paiementFactureConverter.toVo(paiementFactureService.findAll());
    }

    public PaiementFactureConverter getPaiementFactureConverter() {
        return paiementFactureConverter;
    }

    public void setPaiementFactureConverter(PaiementFactureConverter paiementFactureConverter) {
        this.paiementFactureConverter = paiementFactureConverter;
    }

    public PaiementFactureService getPaiementFactureService() {
        return paiementFactureService;
    }

    public void setPaiementFactureService(PaiementFactureService paiementFactureService) {
        this.paiementFactureService = paiementFactureService;
    }

}