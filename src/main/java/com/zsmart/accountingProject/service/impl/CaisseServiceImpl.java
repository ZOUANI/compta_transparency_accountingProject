
package com.zsmart.accountingProject.service.impl;

import com.zsmart.accountingProject.bean.Caisse;
import com.zsmart.accountingProject.bean.OperationComptable;
import com.zsmart.accountingProject.dao.CaisseDao;
import com.zsmart.accountingProject.service.facade.CaisseService;
import com.zsmart.accountingProject.service.facade.OperationComptableService;
import com.zsmart.accountingProject.service.util.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service

public class CaisseServiceImpl implements CaisseService {


    @Autowired

    private CaisseDao caisseDao;

    @Autowired

    private EntityManager entityManager;

    @Autowired

    private OperationComptableService operationcomptableService;

    @Override
    public Caisse save(Caisse caisse) {

        if (caisse == null) {
            return null;
        } else {
            caisseDao.save(caisse);
            return caisse;
        }
    }

    @Override
    public Caisse saveWithOperationComptables(Caisse caisse) {

        if (caisse == null) {
            return null;
        } else {
            if (caisse.getOperationComptables().isEmpty()) {
                return null;
            } else {
                caisseDao.save(caisse);
                for (OperationComptable operationcomptable : caisse.getOperationComptables()) {
                    operationcomptable.setCaisse(caisse);
                    operationcomptableService.save(operationcomptable);
                }
                return caisse;
            }
        }
    }

    @Override
    public List<Caisse> findAll() {
        return caisseDao.findAll();
    }

    @Override
    public Caisse findById(Long id) {
        return caisseDao.getOne(id);
    }

    @Override
    public int delete(Caisse caisse) {
        if (caisse == null) {
            return -1;
        } else {
            caisseDao.delete(caisse);
            return 1;
        }
    }

    @Override
    public void deleteById(Long id) {
        caisseDao.deleteById(id);
    }

    public void clone(Caisse caisse, Caisse caisseClone) {
        if (caisse != null && caisseClone != null) {
            caisseClone.setId(caisse.getId());
            caisseClone.setLibelle(caisse.getLibelle());
            caisseClone.setCode(caisse.getCode());
            caisseClone.setSolde(caisse.getSolde());
            caisseClone.setOperationComptables(operationcomptableService.clone(caisse.getOperationComptables()));
        }
    }

    public Caisse clone(Caisse caisse) {
        if (caisse == null) {
            return null;
        } else {
            Caisse caisseClone = new Caisse();
            clone(caisse, caisseClone);
            return caisseClone;
        }
    }

    @Override
    public Caisse findByAdherantIdAndId(Long adherentId, Long id) {
        try {
            return caisseDao.findByAdherantIdAndId(adherentId, id);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Caisse> clone(List<Caisse> caisses) {
        if (caisses == null) {
            return null;
        } else {
            List<Caisse> caissesClone = new ArrayList();
            caisses.forEach((caisse) -> {
                caissesClone.add(clone(caisse));
            });
            return caissesClone;
        }
    }

    @Override
    public List<Caisse> findByCriteria(String libelle, String code, Long adherentId, Long idMin, Long idMax, BigDecimal soldeMin, BigDecimal soldeMax) {
        return entityManager.createQuery(constructQuery(libelle, code, adherentId, idMin, idMax, soldeMin, soldeMax)).getResultList();
    }

    @Override
    public List<Caisse> findCaissesByAdherantId(Long id) {
        return caisseDao.findCaissesByAdherantId(id);
    }

    @Transactional
    @Override
    public void deleteByAdherantIdAndId(Long adherentId, Long id) {
        caisseDao.deleteByAdherantIdAndId(adherentId, id);
    }

    private String constructQuery(String libelle, String code, Long adherentId, Long idMin, Long idMax, BigDecimal soldeMin, BigDecimal soldeMax) {
        String query = "SELECT c FROM Caisse c where 1=1 ";
        query += SearchUtil.addConstraint("c", "libelle", "LIKE", libelle);
        query += SearchUtil.addConstraint("c", "adherant.id", "=", adherentId);
        query += SearchUtil.addConstraint("c", "code", "=", code);
        query += SearchUtil.addConstraintMinMax("c", "id", idMin, idMax);
        query += SearchUtil.addConstraintMinMax("c", "solde", soldeMin, soldeMax);

        return query;
    }
}
