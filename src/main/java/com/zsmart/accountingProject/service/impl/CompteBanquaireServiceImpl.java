
package com.zsmart.accountingProject.service.impl;

import com.zsmart.accountingProject.bean.CompteBanquaire;
import com.zsmart.accountingProject.bean.OperationComptable;
import com.zsmart.accountingProject.dao.CompteBanquaireDao;
import com.zsmart.accountingProject.service.facade.BanqueService;
import com.zsmart.accountingProject.service.facade.CompteBanquaireService;
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

public class CompteBanquaireServiceImpl implements CompteBanquaireService {


    @Autowired

    private CompteBanquaireDao comptebanquaireDao;

    @Autowired

    private EntityManager entityManager;

    @Autowired

    private OperationComptableService operationcomptableService;

    @Autowired

    private BanqueService banqueService;

    @Override
    public CompteBanquaire save(CompteBanquaire comptebanquaire) {

        if (comptebanquaire == null) {
            return null;
        } else {
            if (comptebanquaire.getBanque() != null && comptebanquaire.getLibelle() != null) {
                banqueService.save(comptebanquaire.getBanque());
            }
            comptebanquaireDao.save(comptebanquaire);
            return comptebanquaire;
        }
    }

    @Override
    public CompteBanquaire saveWithOperationComptables(CompteBanquaire comptebanquaire) {

        if (comptebanquaire == null) {
            return null;
        } else {
            if (comptebanquaire.getOperationComptables().isEmpty()) {
                return null;
            } else {
                comptebanquaireDao.save(comptebanquaire);
                for (OperationComptable operationcomptable : comptebanquaire.getOperationComptables()) {
                    operationcomptable.setCompteBanquaire(comptebanquaire);
                    operationcomptableService.save(operationcomptable);
                }
                return comptebanquaire;
            }
        }
    }

    @Override
    public List<CompteBanquaire> findAll() {
        return comptebanquaireDao.findAll();
    }

    @Override
    public CompteBanquaire findById(Long id) {
        return comptebanquaireDao.getOne(id);
    }

    @Override
    public int delete(CompteBanquaire comptebanquaire) {
        if (comptebanquaire == null) {
            return -1;
        } else {
            comptebanquaireDao.delete(comptebanquaire);
            return 1;
        }
    }

    @Override
    public void deleteById(Long id) {
        comptebanquaireDao.deleteById(id);
    }

    public void clone(CompteBanquaire comptebanquaire, CompteBanquaire comptebanquaireClone) {
        if (comptebanquaire != null && comptebanquaireClone != null) {
            comptebanquaireClone.setId(comptebanquaire.getId());
            comptebanquaireClone.setLibelle(comptebanquaire.getLibelle());
            comptebanquaireClone.setCode(comptebanquaire.getCode());
            comptebanquaireClone.setSolde(comptebanquaire.getSolde());
            comptebanquaireClone.setBanque(banqueService.clone(comptebanquaire.getBanque()));
            comptebanquaireClone.setOperationComptables(operationcomptableService.clone(comptebanquaire.getOperationComptables()));
        }
    }

    public CompteBanquaire clone(CompteBanquaire comptebanquaire) {
        if (comptebanquaire == null) {
            return null;
        } else {
            CompteBanquaire comptebanquaireClone = new CompteBanquaire();
            clone(comptebanquaire, comptebanquaireClone);
            return comptebanquaireClone;
        }
    }

    public List<CompteBanquaire> clone(List<CompteBanquaire> comptebanquaires) {
        if (comptebanquaires == null) {
            return null;
        } else {
            List<CompteBanquaire> comptebanquairesClone = new ArrayList();
            comptebanquaires.forEach((comptebanquaire) -> {
                comptebanquairesClone.add(clone(comptebanquaire));
            });
            return comptebanquairesClone;
        }
    }

    @Override
    public List<CompteBanquaire> findByCriteria(String libelle, String code, String banqueName, Long adherentId, Long idMin, Long idMax, BigDecimal soldeMin, BigDecimal soldeMax) {
        return entityManager.createQuery(constructQuery(libelle, code, banqueName, adherentId, idMin, idMax, soldeMin, soldeMax)).getResultList();
    }

    @Override
    public List<CompteBanquaire> findCompteBanquairesByAdherantId(Long id) {
        return comptebanquaireDao.findCompteBanquairesByAdherantId(id);
    }

    @Override
    public CompteBanquaire findByAdherantIdAndId(Long adherentId, Long id) {
        return comptebanquaireDao.findByAdherantIdAndId(adherentId, id);
    }

    @Transactional
    @Override
    public void deleteByAdherantIdAndId(Long adherentId, Long id) {
        comptebanquaireDao.deleteByAdherantIdAndId(adherentId, id);
    }

    private String constructQuery(String libelle, String code, String banqueName, Long adherentId, Long idMin, Long idMax, BigDecimal soldeMin, BigDecimal soldeMax) {
        String query = "SELECT c FROM CompteBanquaire c where 1=1 ";
        query += SearchUtil.addConstraint("c", "libelle", "LIKE", libelle);
        query += SearchUtil.addConstraint("c", "banque.libelle", "LIKE", banqueName);
        query += SearchUtil.addConstraint("c", "adherant.id", "=", adherentId);
        query += SearchUtil.addConstraint("c", "code", "=", code);
        query += SearchUtil.addConstraintMinMax("c", "id", idMin, idMax);
        query += SearchUtil.addConstraintMinMax("c", "solde", soldeMin, soldeMax);

        return query;
    }
}
