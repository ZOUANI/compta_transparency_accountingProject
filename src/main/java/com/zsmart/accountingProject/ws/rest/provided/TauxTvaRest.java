package com.zsmart.accountingProject.ws.rest.provided;

import com.zsmart.accountingProject.bean.TauxTva;
import com.zsmart.accountingProject.service.facade.TauxTvaService;
import com.zsmart.accountingProject.ws.rest.converter.TauxTvaConverter;
import com.zsmart.accountingProject.ws.rest.vo.TauxTvaVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountingProject/TauxTva")
@CrossOrigin(origins = {"http://localhost:4200"})
public class TauxTvaRest {
    @Autowired
    private TauxTvaConverter tauxTvaConverter;
    @Autowired
    private TauxTvaService tauxTvaService;

    @PreAuthorize("hasRole('ADMIN') OR hasRole('ADHERENT') ")
    @PostMapping("/")
    public TauxTvaVo save(@RequestBody TauxTvaVo tauxTvaVo) {
        tauxTvaConverter.setAdherent(true);
        TauxTva tauxTva = tauxTvaConverter.toItem(tauxTvaVo);
        return tauxTvaConverter.toVo(tauxTvaService.save(tauxTva));
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('ADHERENT') ")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        tauxTvaService.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN') ")
    @GetMapping("/")
    public List<TauxTvaVo> findAll() {
        return tauxTvaConverter.toVo(tauxTvaService.findAll());
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('ADHERENT') ")
    @GetMapping("/adherent/{adherentId}/id/{id}")
    public List<TauxTvaVo> findByAdherantIdAndId(@PathVariable Long adherentId, @PathVariable Long id) {
        return tauxTvaConverter.toVo(tauxTvaService.findByAdherantIdAndId(adherentId, id));
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('ADHERENT') ")
    @GetMapping("/{id}")
    public List<TauxTvaVo> findByAdherantId(@PathVariable Long id) {
        return tauxTvaConverter.toVo(tauxTvaService.findByAdherantId(id));
    }
}
