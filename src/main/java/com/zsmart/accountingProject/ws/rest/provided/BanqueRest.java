package com.zsmart.accountingProject.ws.rest.provided;


import com.zsmart.accountingProject.bean.Banque;
import com.zsmart.accountingProject.service.facade.BanqueService;
import com.zsmart.accountingProject.ws.rest.converter.BanqueConverter;
import com.zsmart.accountingProject.ws.rest.vo.BanqueVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountingProject/Banque")
@CrossOrigin(origins = {"http://localhost:4200"})
public class BanqueRest {

    @Autowired
    private BanqueService banqueService;

    @Autowired
    private BanqueConverter banqueConverter;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public BanqueVo save(@RequestBody BanqueVo banqueVo) {
        Banque banque = banqueConverter.toItem(banqueVo);
        return banqueConverter.toVo(banqueService.save(banque));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        banqueService.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public List<BanqueVo> findAll() {
        return banqueConverter.toVo(banqueService.findAll());
    }

    public BanqueConverter getBanqueConverter() {
        return banqueConverter;
    }

    public void setBanqueConverter(BanqueConverter banqueConverter) {
        this.banqueConverter = banqueConverter;
    }

    public BanqueService getBanqueService() {
        return banqueService;
    }

    public void setBanqueService(BanqueService banqueService) {
        this.banqueService = banqueService;
    }

}