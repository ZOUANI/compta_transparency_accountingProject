
package com.zsmart.accountingProject.service.impl;

import com.zsmart.accountingProject.bean.OperationComptable;
import com.zsmart.accountingProject.bean.OperationComptableGroupe;
import com.zsmart.accountingProject.dao.OperationComptableGroupeDao;
import com.zsmart.accountingProject.service.facade.OperationComptableGroupeService;
import com.zsmart.accountingProject.service.facade.OperationComptableService;
import com.zsmart.accountingProject.service.util.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service

public class OperationComptableGroupeServiceImpl implements OperationComptableGroupeService {


    @Autowired

    private OperationComptableGroupeDao operationcomptablegroupeDao;

    @Autowired

    private EntityManager entityManager;

    @Autowired

    private OperationComptableService operationcomptableService;

    @Override
    public OperationComptableGroupe save(OperationComptableGroupe operationcomptablegroupe) {

        if (operationcomptablegroupe == null) {
            return null;
        } else {
            if (operationcomptablegroupe.getOperationComptables() != null && !operationcomptablegroupe.getOperationComptables().isEmpty()) {
                for (OperationComptable element : operationcomptablegroupe.getOperationComptables()) {
                    operationcomptablegroupe.setDateSaisie(element.getDateOperationComptable());
                }
            }
            operationcomptablegroupeDao.save(operationcomptablegroupe);
            return operationcomptablegroupe;
        }

    }

    @Override
    public OperationComptableGroupe saveWithOperationComptables(OperationComptableGroupe operationcomptablegroupe) {

        if (operationcomptablegroupe == null) {
            return null;
        } else {
            if (operationcomptablegroupe.getOperationComptables().isEmpty()) {
                return null;
            } else {
                operationcomptablegroupeDao.save(operationcomptablegroupe);
                for (OperationComptable operationcomptable : operationcomptablegroupe.getOperationComptables()) {
                    operationcomptable.setOperationComptableGroupe(operationcomptablegroupe);
                    operationcomptableService.save(operationcomptable);
                }
                return operationcomptablegroupe;
            }
        }
    }

    @Override
    public List<OperationComptableGroupe> findAll() {
        return operationcomptablegroupeDao.findAll();
    }

    @Override
    public OperationComptableGroupe findById(Long id) {
        return operationcomptablegroupeDao.getOne(id);
    }

    @Override
    public int delete(OperationComptableGroupe operationcomptablegroupe) {
        if (operationcomptablegroupe == null) {
            return -1;
        } else {
            operationcomptablegroupeDao.delete(operationcomptablegroupe);
            return 1;
        }
    }

    @Override
    public void deleteById(Long id) {
        operationcomptablegroupeDao.deleteById(id);
    }

    public void clone(OperationComptableGroupe operationcomptablegroupe, OperationComptableGroupe operationcomptablegroupeClone) {
        if (operationcomptablegroupe != null && operationcomptablegroupeClone != null) {
            operationcomptablegroupeClone.setId(operationcomptablegroupe.getId());
            operationcomptablegroupeClone.setLibelle(operationcomptablegroupe.getLibelle());
            operationcomptablegroupeClone.setCode(operationcomptablegroupe.getCode());
            operationcomptablegroupeClone.setOperationComptables(operationcomptableService.clone(operationcomptablegroupe.getOperationComptables()));
        }
    }

    public OperationComptableGroupe clone(OperationComptableGroupe operationcomptablegroupe) {
        if (operationcomptablegroupe == null) {
            return null;
        } else {
            OperationComptableGroupe operationcomptablegroupeClone = new OperationComptableGroupe();
            clone(operationcomptablegroupe, operationcomptablegroupeClone);
            return operationcomptablegroupeClone;
        }
    }

    public List<OperationComptableGroupe> clone(List<OperationComptableGroupe> operationcomptablegroupes) {
        if (operationcomptablegroupes == null) {
            return null;
        } else {
            List<OperationComptableGroupe> operationcomptablegroupesClone = new ArrayList();
            operationcomptablegroupes.forEach((operationcomptablegroupe) -> {
                operationcomptablegroupesClone.add(clone(operationcomptablegroupe));
            });
            return operationcomptablegroupesClone;
        }
    }

    @Override
    public List<OperationComptableGroupe> findByCriteria(String libelle, String code, Long idMin, Long idMax, Date dateSaisieMin, Date dateSaisieMax) {
        return entityManager.createQuery(constructQuery(libelle, code, idMin, idMax, dateSaisieMin, dateSaisieMax)).getResultList();
    }

    @Override
    public List<OperationComptableGroupe> findByAdherantId(Long adherentId) {
        return operationcomptablegroupeDao.findByAdherantId(adherentId);
    }

    @Override
    public OperationComptableGroupe findByAdherantIdAndId(Long adherentId, Long id) {
        return operationcomptablegroupeDao.findByAdherantIdAndId(adherentId, id);
    }


    private String constructQuery(String libelle, String code, Long idMin, Long idMax, Date dateSaisieMin, Date dateSaisieMax) {
        String query = "SELECT o FROM OperationComptableGroupe o where 1=1 ";
        query += SearchUtil.addConstraint("o", "libelle", "=", libelle);
        query += SearchUtil.addConstraint("o", "code", "=", code);
        query += SearchUtil.addConstraintMinMax("o", "id", idMin, idMax);
        query += SearchUtil.addConstraintMinMaxDate("o", " dateSaisie", dateSaisieMin, dateSaisieMax);
        return query;
    }
}
