package com.zsmart.accountingProject.service.impl;

import com.zsmart.accountingProject.bean.Adherant;
import com.zsmart.accountingProject.bean.Comptable;
import com.zsmart.accountingProject.bean.Societe;
import com.zsmart.accountingProject.dao.AdherantDao;
import com.zsmart.accountingProject.dao.ComptableDao;
import com.zsmart.accountingProject.service.facade.AdherantService;
import com.zsmart.accountingProject.service.facade.ComptableService;
import com.zsmart.accountingProject.service.facade.SocieteService;
import com.zsmart.accountingProject.service.util.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ComptableServiceImpl implements ComptableService {
    @Autowired
    private ComptableDao comptableDao;
    @Autowired
    private SocieteService societeService;
    @Autowired
    private AdherantDao adherantDao;
    @Autowired
    private AdherantService adherantService;
    @Autowired

    private EntityManager entityManager;

    @Override
    public Comptable findById(Long id) {
        try {
            return comptableDao.getOne(id);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    @Override
    public Comptable updateAdherents(Comptable comptable) {
        if (comptable == null) {
            return null;
        } else {
            Comptable c = findById(comptable.getId());
            if (c == null) {
                return null;
            } else {
                if (comptable.getAdherants() == null) {
                    return null;
                } else {

                    for (Adherant adherant : comptable.getAdherants()
                    ) {
                        Adherant a = adherantService.findById(adherant.getId());
                        a.setComptable(comptable);
                        adherantDao.save(a);
                        if (a.getSocietes() != null) {
                            for (Societe societe : a.getSocietes()
                            ) {
                                societe.setComptable(comptable);
                                societeService.save(societe);
                            }
                        }
                    }
                    c = comptableDao.save(c);
                    return c;
                }
            }
        }
    }

    @Override
    public List<Comptable> findByCriteria(String nom, String prenom, String email) {
        return entityManager.createQuery(constructQuery(nom, prenom, email)).getResultList();
    }

    private String constructQuery(String nom, String prenom, String email) {
        String query = "SELECT c FROM Comptable c where 1=1 ";
        query += SearchUtil.addConstraint("c", "nom", "LIKE", nom);
        query += SearchUtil.addConstraint("c", "prenom", "LIKE", prenom);
        query += SearchUtil.addConstraint("c", "email", "LIKE", email);

        return query;
    }

    @Override
    public int remove(Adherant adherant) {
        if (adherant == null) {
            return -1;
        } else {
            Adherant a = adherantService.findById(adherant.getId());
            a.setComptable(null);
            if (a.getSocietes() != null) {
                for (Societe societe : a.getSocietes()
                ) {
                    societe.setComptable(null);
                    societeService.save(societe);
                }
            }
            adherantDao.save(a);
            return 0;

        }
    }
}
