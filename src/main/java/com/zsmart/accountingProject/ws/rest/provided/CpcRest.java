package com.zsmart.accountingProject.ws.rest.provided;

import com.zsmart.accountingProject.bean.Cpc;
import com.zsmart.accountingProject.service.facade.CpcService;
import com.zsmart.accountingProject.ws.rest.converter.CpcConverter;
import com.zsmart.accountingProject.ws.rest.vo.CpcVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accountingProject/Cpc")
@CrossOrigin(origins = {"http://localhost:4200"})
public class CpcRest {

	@Autowired
	private CpcService cpcService;

	@Autowired
	private CpcConverter cpcConverter;

	@PostMapping("/generateCpc/")
	public CpcVo findCpcClasseComptable(@RequestBody CpcVo cpcVo) {
		cpcConverter.getSocieteConverter().setFacture(false);
		cpcConverter.setCpcSousClasses(true);
		cpcConverter.getCpcSousClasseConverter().setCpcCompteComptables(true);
		cpcConverter.getCpcSousClasseConverter().setSousClasseComptable(true);
		cpcConverter.getCpcSousClasseConverter().getCpcCompteComptableConverter().setCompteComptable(true);
		Cpc cpc = cpcConverter.toItem(cpcVo);
		return cpcConverter.toVo(cpcService.findCpcClasseComptable(cpc.getDateDebut(), cpc.getDateFin(), cpc.getSociete().getId()));
	}

	@PostMapping("/")
	public Cpc save(@RequestBody Cpc cpc) {
		return cpcService.saveWithCpcSousClasses(cpc);
	}

	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		cpcService.deleteById(id);
	}
	
	@DeleteMapping("/")
	public void delete(@RequestBody Cpc cpc) {
		cpcService.delete(cpc);
	}

	@GetMapping("/")
	public List<CpcVo> findAll() {
		return cpcConverter.toVo(cpcService.findAll());
	}

	public CpcConverter getCpcConverter() {
		return cpcConverter;
	}

	public void setCpcConverter(CpcConverter cpcConverter) {
		this.cpcConverter = cpcConverter;
	}

	public CpcService getCpcService() {
		return cpcService;
	}

	public void setCpcService(CpcService cpcService) {
		this.cpcService = cpcService;
	}

}