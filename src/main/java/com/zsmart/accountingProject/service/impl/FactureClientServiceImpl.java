
package com.zsmart.accountingProject.service.impl;

import com.zsmart.accountingProject.bean.*;
import com.zsmart.accountingProject.service.facade.FactureClientService;
import com.zsmart.accountingProject.dao.FactureClientDao;
import com.zsmart.accountingProject.service.facade.FactureService;
import com.zsmart.accountingProject.service.facade.OperationComptableService;
import com.zsmart.accountingProject.service.util.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

import org.springframework.stereotype.Service;

import java.util.List;

import com.zsmart.accountingProject.service.facade.ClientService;

@Service

public class FactureClientServiceImpl implements FactureClientService {


    @Autowired

    private FactureClientDao factureclientDao;

    @Autowired

    private EntityManager entityManager;

    @Autowired

    private ClientService clientService;



    @Autowired

    private OperationComptableService operationcomptableService;


    @Override
    public FactureClient save(FactureClient factureclient) {

        if (factureclient == null) {
            return null;
        } else {
            factureclientDao.save(factureclient);
            return factureclient;
        }
    }

    @Override
    public FactureClient saveWithOperations(FactureClient factureclient) {
        if (factureclient == null) {
            return null;
        } else {
            if (factureclient.getOperationComptable()==null || factureclient.getOperationComptable().isEmpty()) {
                return null;
            } else {
                factureclientDao.save(factureclient);
                for (OperationComptable operationcomptable : factureclient.getOperationComptable()) {
                    operationcomptable.setFacture(factureclient);
                    operationcomptableService.save(operationcomptable);
                }
                return factureclient;
            }
        }
    }

    @Override
    public List<FactureClient> findAll() {
        return factureclientDao.findAll();
    }

    @Override
    public FactureClient findById(Long id) {
        return factureclientDao.getOne(id);
    }

    @Override
    public FactureClient findByReferenceSocieteAndReference(String refsoc, String ref) {
        return factureclientDao.findByReferenceSocieteAndReference(refsoc, ref);
    }

    @Override
    public List<BigDecimal> calculateGainParAnneeEtRefSociete(int annee, String refsoc) {
        List<BigDecimal> data=new ArrayList<>();
        for (int i = 0; i <12 ; i++) {
            List<FactureClient> factureFournisseurs=factureclientDao.findByAnneeAndReferenceSocieteAndMois(annee,refsoc,i);
            BigDecimal total=BigDecimal.ZERO;
            for (FactureClient f:factureFournisseurs
            ) {
                total=total.add(f.getTotalTtc());
            }
            data.add(total);
        }
        return data;
    }

    @Override
    public int delete(FactureClient factureclient) {
        if (factureclient == null) {
            return -1;
        } else {
            factureclientDao.delete(factureclient);
            return 1;
        }
    }

    @Override
    public void deleteById(Long id) {
        factureclientDao.deleteById(id);
    }

    public void clone(FactureClient factureclient, FactureClient factureclientClone) {
        if (factureclient != null && factureclientClone != null) {
            factureclientClone.setId(factureclient.getId());
            factureclientClone.setClient(clientService.clone(factureclient.getClient()));
        }
    }

    public FactureClient clone(FactureClient factureclient) {
        if (factureclient == null) {
            return null;
        } else {
            FactureClient factureclientClone = new FactureClient();
            clone(factureclient, factureclientClone);
            return factureclientClone;
        }
    }

    public List<FactureClient> clone(List<FactureClient> factureclients) {
        if (factureclients == null) {
            return null;
        } else {
            List<FactureClient> factureclientsClone = new ArrayList();
            factureclients.forEach((factureclient) -> {
                factureclientsClone.add(clone(factureclient));
            });
            return factureclientsClone;
        }
    }

    @Override
    public List<FactureClient> findByCriteria(Long idMin, Long idMax) {
        return entityManager.createQuery(constructQuery(idMin, idMax)).getResultList();
    }

    private String constructQuery(Long idMin, Long idMax) {
        String query = "SELECT f FROM FactureClient f where 1=1 ";
        query += SearchUtil.addConstraintMinMax("f", "id", idMin, idMax);

        return query;
    }
}
