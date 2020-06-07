package com.zsmart.accountingProject.service.facade;

import com.zsmart.accountingProject.bean.CpcCompteComptable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface CpcCompteComptableService {

	public CpcCompteComptable save(CpcCompteComptable cpccomptecomptable);

	public List<CpcCompteComptable> findAll();

	public CpcCompteComptable findById(Long id);

    public int delete(CpcCompteComptable cpccomptecomptable);

    public void deleteById(Long id);

    public void clone(CpcCompteComptable cpccomptecomptable, CpcCompteComptable cpccomptecomptableClone);

    public CpcCompteComptable clone(CpcCompteComptable cpccomptecomptable);

    public List<CpcCompteComptable> clone(List<CpcCompteComptable> cpccomptecomptables);

    public List<CpcCompteComptable> findByCriteria(Long idMin, Long idMax, BigDecimal montantMin,
                                                   BigDecimal montantMax);

    List<CpcCompteComptable> findCpcCompteComptable(Date dateDebut, Date dateFin, int codeSousClasseComptable, Long socId);

    public List<CpcCompteComptable> find(int numeroSousClasse);
}
