package com.zsmart.accountingProject.ws.rest.converter;

import com.zsmart.accountingProject.bean.Cpc;
import com.zsmart.accountingProject.service.util.DateUtil;
import com.zsmart.accountingProject.service.util.ListUtil;
import com.zsmart.accountingProject.service.util.NumberUtil;
import com.zsmart.accountingProject.ws.rest.vo.CpcVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CpcConverter extends AbstractConverter<Cpc, CpcVo> {

	private boolean cpcSousClasses;

	@Autowired
	private CpcSousClasseConverter cpcSousClasseConverter;

	@Autowired
	private SocieteConverter societeConverter;
	@Autowired
	private AdherantConverter adherantConverter;
	@Autowired
	private ComptableConverter comptableConverter;
	private Boolean adherant;
	private Boolean comptable;

	@Override
	public Cpc toItem(CpcVo vo) {
		if (vo == null) {
			return null;
		} else {
			Cpc item = new Cpc();

			if (vo.getSocieteVo()!=null) {
				item.setSociete(societeConverter.toItem(vo.getSocieteVo()));
			}

			if (vo.getId() != null) {
				item.setId(NumberUtil.toLong(vo.getId()));
			}

			if (vo.getDateDebut() != null) {
				item.setDateDebut(DateUtil.parse(vo.getDateDebut()));
			}

			if (vo.getDateFin() != null) {
				item.setDateFin(DateUtil.parse(vo.getDateFin()));
			}

			if (vo.getTotalCharge() != null) {
				item.setTotalCharge(NumberUtil.toBigDecimal(vo.getTotalCharge()));
			}

			if (vo.getTotalProduit() != null) {
				item.setTotalProduit(NumberUtil.toBigDecimal(vo.getTotalProduit()));
			}

			if (vo.getResultat() != null) {
				item.setResultat(NumberUtil.toBigDecimal(vo.getResultat()));
			}

			if (ListUtil.isNotEmpty(vo.getCpcSousClassesVo()) && cpcSousClasses) {
				item.setCpcSousClasses(cpcSousClasseConverter.toItem(vo.getCpcSousClassesVo()));
			}
			if (vo.getAdherantVo()!=null && adherant) {
				item.setAdherant(adherantConverter.toItem(vo.getAdherantVo()));
			}
			if (vo.getComptableVo()!=null && comptable) {
				item.setComptable(comptableConverter.toItem(vo.getComptableVo()));
			}
			return item;
		}
	}

	@Override
	public CpcVo toVo(Cpc item) {
		if (item == null) {
			return null;
		} else {
			CpcVo vo = new CpcVo();

			if (item.getSociete()!=null) {
				vo.setSocieteVo(societeConverter.toVo(item.getSociete()));
			}

			if (item.getId() != null) {
				vo.setId(NumberUtil.toString(item.getId()));
			}

			if (item.getDateDebut() != null) {
				vo.setDateDebut(DateUtil.formateDate(item.getDateDebut()));
			}

			if (item.getDateFin() != null) {
				vo.setDateFin(DateUtil.formateDate(item.getDateFin()));
			}

			if (item.getTotalCharge() != null) {
				vo.setTotalCharge(NumberUtil.toString(item.getTotalCharge()));
			}

			if (item.getTotalProduit() != null) {
				vo.setTotalProduit(NumberUtil.toString(item.getTotalProduit()));
			}

			if (item.getResultat() != null) {
				vo.setResultat(NumberUtil.toString(item.getResultat()));
			}

			if (ListUtil.isNotEmpty(item.getCpcSousClasses()) && cpcSousClasses) {
				vo.setCpcSousClassesVo(cpcSousClasseConverter.toVo(item.getCpcSousClasses()));
			}
			if (item.getAdherant()!=null && adherant) {
				item.setAdherant(adherantConverter.toItem(vo.getAdherantVo()));
			}
			if (item.getComptable()!=null && comptable) {
				item.setComptable(comptableConverter.toItem(vo.getComptableVo()));
			}
			return vo;
		}
	}

	public void init() {

		cpcSousClasses = true;
	}

	public SocieteConverter getSocieteConverter() {
		return societeConverter;
	}

	public void setSocieteConverter(SocieteConverter societeConverter) {
		this.societeConverter = societeConverter;
	}

	public boolean isCpcSousClasses() {
		return cpcSousClasses;
	}

	public void setCpcSousClasses(boolean cpcSousClasses) {
		this.cpcSousClasses = cpcSousClasses;
	}

	public CpcSousClasseConverter getCpcSousClasseConverter() {
		return cpcSousClasseConverter;
	}

	public void setCpcSousClasseConverter(CpcSousClasseConverter cpcSousClasseConverter) {
		this.cpcSousClasseConverter = cpcSousClasseConverter;
	}

	public Boolean getAdherant() {
		return adherant;
	}

	public void setAdherant(Boolean adherant) {
		this.adherant = adherant;
	}

	public Boolean getComptable() {
		return comptable;
	}

	public void setComptable(Boolean comptable) {
		this.comptable = comptable;
	}
}
