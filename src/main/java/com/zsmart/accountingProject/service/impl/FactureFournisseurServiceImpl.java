
package com.zsmart.accountingProject.service.impl;

import com.zsmart.accountingProject.bean.*;
import com.zsmart.accountingProject.service.facade.*;
import com.zsmart.accountingProject.dao.FactureFournisseurDao;
import com.zsmart.accountingProject.service.util.SearchUtil;
import org.aspectj.weaver.JoinPointSignature;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @Autowired
    private SocieteService societeService;
    @Autowired
    private FactureItemService factureItemService;
    private final Path root= Paths.get("src\\main\\resources\\uploads");

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
    public FactureFournisseur saveWithOperationsAndFactureItems(FactureFournisseur facturefournisseur) {
        if (facturefournisseur==null){
            return null;
        }else {
            if (facturefournisseur.getOperationComptable()==null
                    || facturefournisseur.getOperationComptable().isEmpty()
                    || facturefournisseur.getFactureItems()==null
                    || facturefournisseur.getFactureItems().isEmpty()){
                return null;
            }else{
                facturefournisseurDao.save(facturefournisseur);
                for (OperationComptable operationcomptable : facturefournisseur.getOperationComptable()) {
                    operationcomptable.setFacture(facturefournisseur);
                    operationcomptableService.save(operationcomptable);
                }
                for (FactureItem factureItem : facturefournisseur.getFactureItems()) {
                    factureItem.setFacture(facturefournisseur);
                    factureItemService.save(factureItem);
                }
                return facturefournisseur;
            }
        }
    }

    @Override
    public void uploadScan(MultipartFile file,FactureFournisseur factureFournisseur) {
      if (factureFournisseur!=null){
            try {
                    if (factureFournisseur.getId()!=null){
                        Files.delete(this.root.resolve(factureFournisseur.getSociete().getRaisonSocial() + "Fournisseur" + factureFournisseur.getReference()));
                    }

                    Files.copy(file.getInputStream(), this.root.resolve(factureFournisseur.getSociete().getRaisonSocial() + "Fournisseur" + factureFournisseur.getReference()));
                    factureFournisseur.setScanPath(factureFournisseur.getSociete().getRaisonSocial() + "Fournisseur" + factureFournisseur.getReference());

            } catch (Exception e) {
                throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
            }
        }
    }

    @Override
    public Resource getScan(Long id) {
        FactureFournisseur factureFournisseur=findById(id);
        try {
                Path file = root.resolve(factureFournisseur.getScanPath());
                Resource resource = new UrlResource(file.toUri());
                if (resource.exists() || resource.isReadable()) {
                    return resource;
                } else {
                    throw new RuntimeException("Could not read the file!");
                }
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error: " + e.getMessage());
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
    public FactureFournisseur findBySocieteIdAndReference(Long id, String ref) {
        Societe societe=societeService.findById(id);
        return facturefournisseurDao.findBySocieteAndReference(societe, ref);
    }

    @Override
    public List<BigDecimal> calculateChargeParAnneeEtSocieteId(int annee, Long id) {
        List<BigDecimal> data=new ArrayList<>();
        Societe societe=societeService.findById(id);
        for (int i = 1; i <13 ; i++) {
            List<FactureFournisseur> factureFournisseurs=facturefournisseurDao.findByAnneeAndSocieteAndMois(annee,societe,i);
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
