package com.zsmart.accountingProject.ws.rest.provided;

import com.zsmart.accountingProject.bean.Adherant;
import com.zsmart.accountingProject.bean.Comptable;
import com.zsmart.accountingProject.service.facade.ComptableService;
import com.zsmart.accountingProject.ws.rest.converter.AdherantConverter;
import com.zsmart.accountingProject.ws.rest.converter.ComptableConverter;
import com.zsmart.accountingProject.ws.rest.vo.AdherantVo;
import com.zsmart.accountingProject.ws.rest.vo.ComptableVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountingProject/Comptable")
@CrossOrigin(origins = "http://localhost:4200")

public class ComptableRest {
    @Autowired
    private ComptableService comptableService;
    @Autowired
    private ComptableConverter comptableConverter;
    @Autowired
    private AdherantConverter adherantConverter;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ComptableVo findById(@PathVariable Long id) {
        comptableConverter.setAdherant(true);
        comptableConverter.getAdherantConverter().setSociete(true);
        return comptableConverter.toVo(comptableService.findById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/findByCriteria")
    public List<ComptableVo> findByCriteria(@RequestBody ComptableVo comptableVo) {
        return comptableConverter.toVo(comptableService.findByCriteria(comptableVo.getNom(), comptableVo.getPrenom(), comptableVo.getEmail()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ComptableVo update(@RequestBody ComptableVo comptableVo) {
        comptableConverter.setAdherant(true);
        comptableConverter.getAdherantConverter().setSociete(true);
        Comptable comptable = comptableConverter.toItem(comptableVo);
        return comptableConverter.toVo(comptableService.updateAdherents(comptable));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/remove")
    public int remove(@RequestBody AdherantVo adherantVo) {

        adherantConverter.setSociete(true);
        Adherant adherant = adherantConverter.toItem(adherantVo);
        return comptableService.remove(adherant);
    }

}
