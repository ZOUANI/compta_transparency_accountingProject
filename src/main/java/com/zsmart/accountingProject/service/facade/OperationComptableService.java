package com.zsmart.accountingProject.service.facade;

import com.zsmart.accountingProject.bean.OperationComptable;
import org.springframework.core.io.Resource;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface OperationComptableService {

    public OperationComptable save(OperationComptable operationcomptable);

    public List<OperationComptable> findAll();

    public OperationComptable findById(Long id);

    public List<OperationComptable> findBySocId(Long id);

    public int delete(OperationComptable operationcomptable);

    public void deleteById(Long id);

    public OperationComptable findByAdherantIdAndId(Long adherentId, Long id);

    public void deleteByAdherantIdAndId(Long adherentId, Long id);

    public void clone(OperationComptable operationcomptable, OperationComptable operationcomptableClone);

    public OperationComptable clone(OperationComptable operationcomptable);

    public List<OperationComptable> clone(List<OperationComptable> operationcomptables);

    public List<OperationComptable> findByCriteria(String libelle, String raisonSocial, String referenceFacture, Long idMin, Long idMax, BigDecimal montantMin, BigDecimal montantMax, Date dateOperationComptableMin, Date dateOperationComptableMax, Date dateSaisieMin, Date dateSaisieMax, String codeCompteComptable, String codeTypeOperation, Long adherentId, String opGroupeLibelle);

    public List<OperationComptable> findOperationComptable(Date dateDebut, Date dateFin, String code);

    public List<OperationComptable> findOperationComptablee(Date dateDebut, Date dateFin);

    public Resource generateExcelFile(List<OperationComptable> operationComptables);

    List<Object[]> findGroupeBySousClasseCompteComptable(Date dateDebut, Date dateFin, String code, Long socId);

    List<Object[]> findGroupeByClasseCompteComptable(Date dateDebut, Date dateFin, String code, Long socId);

    List<Object[]> findGroupeByCompteComptable(Date dateDebut, Date dateFin, int code, Long socId);
}
