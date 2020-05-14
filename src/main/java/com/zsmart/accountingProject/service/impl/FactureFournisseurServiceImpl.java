
package com.zsmart.accountingProject.service.impl;

import com.zsmart.accountingProject.bean.FactureClient;
import com.zsmart.accountingProject.bean.OperationComptable;
import com.zsmart.accountingProject.service.facade.FactureFournisseurService;
import com.zsmart.accountingProject.dao.FactureFournisseurDao;
import com.zsmart.accountingProject.service.facade.OperationComptableService;
import com.zsmart.accountingProject.service.util.SearchUtil;
import com.zsmart.accountingProject.bean.FactureFournisseur;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

import org.springframework.stereotype.Service;

import java.util.List;

import com.zsmart.accountingProject.bean.Fournisseur;
import com.zsmart.accountingProject.service.facade.FournisseurService;

@Service

public class FactureFournisseurServiceImpl implements FactureFournisseurService {


    @Autowired

    private FactureFournisseurDao facturefournisseurDao;

    @Autowired

    private EntityManager entityManager;

    @Autowired

    private FournisseurService fournisseurService;

    @Autowired

    private OperationComptableService operationcomptableService;

    @Override
    public FactureFournisseur save(FactureFournisseur facturefournisseur) {

        if (facturefournisseur == null) {
            return null;
        } else {
            facturefournisseurDao.save(facturefournisseur);
            return facturefournisseur;
        }
    }

    @Override
    public FactureFournisseur saveWithOperations(FactureFournisseur facturefournisseur) {
        if (facturefournisseur == null) {
            return null;
        } else {
            if (facturefournisseur.getOperationComptable()==null || facturefournisseur.getOperationComptable().isEmpty()) {
                return null;
            } else {
                facturefournisseurDao.save(facturefournisseur);
                for (OperationComptable operationcomptable : facturefournisseur.getOperationComptable()) {
                    operationcomptable.setFacture(facturefournisseur);
                    operationcomptableService.save(operationcomptable);
                }
                return facturefournisseur;
            }
        }
    }


    @Override
    public List<FactureFournisseur> findAll() {
        return facturefournisseurDao.findAll();
    }

    @Override
    public FactureFournisseur findById(Long id) {

        return facturefournisseurDao.getOne(id);
    }

    @Override
    public FactureFournisseur findByReferenceSocieteAndReference(String refsoc, String ref) {
        return facturefournisseurDao.findByReferenceSocieteAndReference(refsoc, ref);
    }

    @Override
    public List<BigDecimal> calculateChargeParAnneeEtRefSociete(int annee, String refsoc) {
        List<BigDecimal> data=new ArrayList<>();
        for (int i = 0; i <12 ; i++) {
            List<FactureFournisseur> factureFournisseurs=facturefournisseurDao.findByAnneeAndReferenceSocieteAndMois(annee,refsoc,i);
            BigDecimal total=BigDecimal.ZERO;
            for (FactureFournisseur f:factureFournisseurs
                 ) {
                total=total.add(f.getTotalTtc());
            }
            data.add(total);
        }
        return data;

    }

    @Override
    public int delete(FactureFournisseur facturefournisseur) {
        if (facturefournisseur == null) {
            return -1;
        } else {
            facturefournisseurDao.delete(facturefournisseur);
            return 1;
        }
    }

    @Override
    public void deleteById(Long id) {
        facturefournisseurDao.deleteById(id);
    }

    public void clone(FactureFournisseur facturefournisseur, FactureFournisseur facturefournisseurClone) {
        if (facturefournisseur != null && facturefournisseurClone != null) {
            facturefournisseurClone.setId(facturefournisseur.getId());
            facturefournisseurClone.setFournisseur(fournisseurService.clone(facturefournisseur.getFournisseur()));
        }
    }

    public FactureFournisseur clone(FactureFournisseur facturefournisseur) {
        if (facturefournisseur == null) {
            return null;
        } else {
            FactureFournisseur facturefournisseurClone = new FactureFournisseur();
            clone(facturefournisseur, facturefournisseurClone);
            return facturefournisseurClone;
        }
    }

    public List<FactureFournisseur> clone(List<FactureFournisseur> facturefournisseurs) {
        if (facturefournisseurs == null) {
            return null;
        } else {
            List<FactureFournisseur> facturefournisseursClone = new ArrayList();
            facturefournisseurs.forEach((facturefournisseur) -> {
                facturefournisseursClone.add(clone(facturefournisseur));
            });
            return facturefournisseursClone;
        }
    }

    @Override
    public List<FactureFournisseur> findByCriteria(Long idMin, Long idMax) {
        return entityManager.createQuery(constructQuery(idMin, idMax)).getResultList();
    }

    private String constructQuery(Long idMin, Long idMax) {
        String query = "SELECT f FROM FactureFournisseur f where 1=1 ";
        query += SearchUtil.addConstraintMinMax("f", "id", idMin, idMax);

        return query;
    }
}
