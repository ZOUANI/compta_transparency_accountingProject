package com.zsmart.accountingProject.service.facade;

import com.zsmart.accountingProject.bean.CompteBanquaire;

import java.math.BigDecimal;
import java.util.List;

public interface CompteBanquaireService {

    public CompteBanquaire save(CompteBanquaire comptebanquaire);

    public CompteBanquaire saveWithOperationComptables(CompteBanquaire comptebanquaire);

    public List<CompteBanquaire> findAll();

    public CompteBanquaire findById(Long id);

    public int delete(CompteBanquaire comptebanquaire);

    public void deleteById(Long id);

    public void clone(CompteBanquaire comptebanquaire, CompteBanquaire comptebanquaireClone);

    public CompteBanquaire clone(CompteBanquaire comptebanquaire);

    public List<CompteBanquaire> clone(List<CompteBanquaire> comptebanquaires);

    public List<CompteBanquaire> findByCriteria(String libelle, String code, String banqueName, Long adherentId, Long idMin, Long idMax, BigDecimal soldeMin, BigDecimal soldeMax);

    public List<CompteBanquaire> findCompteBanquairesByAdherantId(Long id);

    public CompteBanquaire findByAdherantIdAndId(Long adherentId, Long id);

    public void deleteByAdherantIdAndId(Long adherentId, Long id);
}
