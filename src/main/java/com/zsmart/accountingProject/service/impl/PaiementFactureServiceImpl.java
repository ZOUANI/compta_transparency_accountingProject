
package com.zsmart.accountingProject.service.impl;

import com.zsmart.accountingProject.bean.PaiementFacture;
import com.zsmart.accountingProject.dao.PaiementFactureDao;
import com.zsmart.accountingProject.service.facade.FactureService;
import com.zsmart.accountingProject.service.facade.PaiementFactureService;
import com.zsmart.accountingProject.service.facade.TypePaiementService;
import com.zsmart.accountingProject.service.util.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service

public class PaiementFactureServiceImpl implements PaiementFactureService {


    @Autowired

    private PaiementFactureDao paiementfactureDao;

    @Autowired

    private EntityManager entityManager;

    @Autowired

    private TypePaiementService typepaiementService;

    @Autowired

    private FactureService factureService;

    @Override
    public PaiementFacture save(PaiementFacture paiementfacture) {

        if (paiementfacture == null) {
            return null;
        } else {
            paiementfactureDao.save(paiementfacture);
            return paiementfacture;
        }
    }

    @Override
    public List<PaiementFacture> findAll() {
        return paiementfactureDao.findAll();
    }

    @Override
    public PaiementFacture findById(Long id) {
        return paiementfactureDao.getOne(id);
    }

    @Override
    public int delete(PaiementFacture paiementfacture) {
        if (paiementfacture == null) {
            return -1;
        } else {
            paiementfactureDao.delete(paiementfacture);
            return 1;
        }
    }

    @Override
    public void deleteById(Long id) {
        paiementfactureDao.deleteById(id);
    }

    public void clone(PaiementFacture paiementfacture, PaiementFacture paiementfactureClone) {
        if (paiementfacture != null && paiementfactureClone != null) {
            paiementfactureClone.setId(paiementfacture.getId());
            paiementfactureClone.setDatePaiment(paiementfacture.getDatePaiment());
            paiementfactureClone.setDateSaisie(paiementfacture.getDateSaisie());
            paiementfactureClone.setMontant(paiementfacture.getMontant());
            paiementfactureClone.setDescription(paiementfacture.getDescription());
            paiementfactureClone.setScan(paiementfacture.getScan());
            paiementfactureClone.setTypePaiment(typepaiementService.clone(paiementfacture.getTypePaiment()));
            paiementfactureClone.setFacture(factureService.clone(paiementfacture.getFacture()));
        }
    }

    public PaiementFacture clone(PaiementFacture paiementfacture) {
        if (paiementfacture == null) {
            return null;
        } else {
            PaiementFacture paiementfactureClone = new PaiementFacture();
            clone(paiementfacture, paiementfactureClone);
            return paiementfactureClone;
        }
    }

    public List<PaiementFacture> clone(List<PaiementFacture> paiementfactures) {
        if (paiementfactures == null) {
            return null;
        } else {
            List<PaiementFacture> paiementfacturesClone = new ArrayList();
            paiementfactures.forEach((paiementfacture) -> {
                paiementfacturesClone.add(clone(paiementfacture));
            });
            return paiementfacturesClone;
        }
    }

    @Override
    public List<PaiementFacture> findByCriteria(String description, String scan, Long idMin, Long idMax, Date datePaimentMin, Date datePaimentMax, Date dateSaisieMin, Date dateSaisieMax, BigDecimal montantMin, BigDecimal montantMax) {
        return entityManager.createQuery(constructQuery(description, scan, idMin, idMax, datePaimentMin, datePaimentMax, dateSaisieMin, dateSaisieMax, montantMin, montantMax)).getResultList();
    }

    private String constructQuery(String description, String scan, Long idMin, Long idMax, Date datePaimentMin, Date datePaimentMax, Date dateSaisieMin, Date dateSaisieMax, BigDecimal montantMin, BigDecimal montantMax) {
        String query = "SELECT p FROM PaiementFacture p where 1=1 ";
        query += SearchUtil.addConstraint("p", "description", "=", description);
        query += SearchUtil.addConstraint("p", "scan", "=", scan);
        query += SearchUtil.addConstraintMinMax("p", "id", idMin, idMax);
        query += SearchUtil.addConstraintMinMaxDate("p", " datePaiment", datePaimentMin, datePaimentMax);
        query += SearchUtil.addConstraintMinMaxDate("p", " dateSaisie", dateSaisieMin, dateSaisieMax);
        query += SearchUtil.addConstraintMinMax("p", "montant", montantMin, montantMax);

        return query;
    }
}
