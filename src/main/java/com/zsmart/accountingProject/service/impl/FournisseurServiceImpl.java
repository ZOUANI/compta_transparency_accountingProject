
package com.zsmart.accountingProject.service.impl;

import com.zsmart.accountingProject.bean.Adherant;
import com.zsmart.accountingProject.bean.FactureFournisseur;
import com.zsmart.accountingProject.bean.Fournisseur;
import com.zsmart.accountingProject.dao.FournisseurDao;
import com.zsmart.accountingProject.service.facade.FactureFournisseurService;
import com.zsmart.accountingProject.service.facade.FournisseurService;
import com.zsmart.accountingProject.service.util.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service

public class FournisseurServiceImpl implements FournisseurService {


    @Autowired

    private FournisseurDao fournisseurDao;

    @Autowired

    private EntityManager entityManager;

    @Autowired

    private FactureFournisseurService facturefournisseurService;

    @Override
    public Fournisseur save(Fournisseur fournisseur) {

        if (fournisseur == null) {
            return null;
        } else {
            fournisseurDao.save(fournisseur);
            return fournisseur;
        }
    }

    @Override
    public Fournisseur saveWithFactureFournisseurs(Fournisseur fournisseur) {

        if (fournisseur == null) {
            return null;
        } else {
            if (fournisseur.getFactureFournisseurs().isEmpty()) {
                return null;
            } else {
                fournisseurDao.save(fournisseur);
                for (FactureFournisseur facturefournisseur : fournisseur.getFactureFournisseurs()) {
                    facturefournisseur.setFournisseur(fournisseur);
                    facturefournisseurService.save(facturefournisseur);
                }
                return fournisseur;
            }
        }
    }

    @Override
    public List<Fournisseur> findAll() {
        return fournisseurDao.findAll();
    }

    @Override
    public Fournisseur findById(Long id) {
        return fournisseurDao.getOne(id);
    }

    @Override
    public List<Fournisseur> findByAdherent(Adherant adherant) {
        return fournisseurDao.findByAdherant(adherant);
    }

    @Override
    public int delete(Fournisseur fournisseur) {
        if (fournisseur == null) {
            return -1;
        } else {
            fournisseurDao.delete(fournisseur);
            return 1;
        }
    }

    @Override
    public void deleteById(Long id) {
        fournisseurDao.deleteById(id);
    }

    @Override
    public Fournisseur findByAdherantIdAndId(Long adherentId, Long id) {
        return fournisseurDao.findByAdherantIdAndId(adherentId, id);
    }

    public void clone(Fournisseur fournisseur, Fournisseur fournisseurClone) {
        if (fournisseur != null && fournisseurClone != null) {
            fournisseurClone.setId(fournisseur.getId());
            fournisseurClone.setIce(fournisseur.getIce());
            fournisseurClone.setIdentifiantFiscale(fournisseur.getIdentifiantFiscale());
            fournisseurClone.setRc(fournisseur.getRc());
            fournisseurClone.setLibelle(fournisseur.getLibelle());
            fournisseurClone.setCode(fournisseur.getCode());
            fournisseurClone.setFactureFournisseurs(facturefournisseurService.clone(fournisseur.getFactureFournisseurs()));
        }
    }

    public Fournisseur clone(Fournisseur fournisseur) {
        if (fournisseur == null) {
            return null;
        } else {
            Fournisseur fournisseurClone = new Fournisseur();
            clone(fournisseur, fournisseurClone);
            return fournisseurClone;
        }
    }

    public List<Fournisseur> clone(List<Fournisseur> fournisseurs) {
        if (fournisseurs == null) {
            return null;
        } else {
            List<Fournisseur> fournisseursClone = new ArrayList();
            fournisseurs.forEach((fournisseur) -> {
                fournisseursClone.add(clone(fournisseur));
            });
            return fournisseursClone;
        }
    }

    @Override
    public List<Fournisseur> findByCriteria(String ice, String identifiantFiscale, String rc, String libelle, String code, Long adherentId) {
        return entityManager.createQuery(constructQuery(ice, identifiantFiscale, rc, libelle, code, adherentId)).getResultList();
    }

    @Transactional
    @Override
    public void deleteByAdherantIdAndId(Long adherentId, Long id) {
        fournisseurDao.deleteByAdherantIdAndId(adherentId, id);
    }

    private String constructQuery(String ice, String identifiantFiscale, String rc, String libelle, String code, Long adherentId) {
        String query = "SELECT f FROM Fournisseur f where 1=1 ";
        query += SearchUtil.addConstraint("f", "ice", "=", ice);
        query += SearchUtil.addConstraint("f", "identifiantFiscale", "=", identifiantFiscale);
        query += SearchUtil.addConstraint("f", "rc", "=", rc);
        query += SearchUtil.addConstraint("f", "libelle", "LIKE", libelle);
        query += SearchUtil.addConstraint("f", "code", "=", code);
        query += SearchUtil.addConstraint("f", "adherant.id", "=", adherentId);

        return query;
    }
}
