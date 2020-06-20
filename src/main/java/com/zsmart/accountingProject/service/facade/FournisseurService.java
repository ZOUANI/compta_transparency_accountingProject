package com.zsmart.accountingProject.service.facade;

import com.zsmart.accountingProject.bean.Adherant;
import com.zsmart.accountingProject.bean.Fournisseur;

import java.util.List;

public interface FournisseurService {

    public Fournisseur save(Fournisseur fournisseur);

    public Fournisseur saveWithFactureFournisseurs(Fournisseur fournisseur);

    public List<Fournisseur> findAll();

    public Fournisseur findById(Long id);

    public List<Fournisseur> findByAdherent(Adherant adherant);

    public int delete(Fournisseur fournisseur);

    public void deleteById(Long id);

    public Fournisseur findByAdherantIdAndId(Long adherentId, Long id);

    public void clone(Fournisseur fournisseur, Fournisseur fournisseurClone);

    public Fournisseur clone(Fournisseur fournisseur);

    public List<Fournisseur> clone(List<Fournisseur> fournisseurs);

    public List<Fournisseur> findByCriteria(String ice, String identifiantFiscale, String rc, String libelle, String code, Long adherentId);

    public void deleteByAdherantIdAndId(Long adherentId, Long id);
}
