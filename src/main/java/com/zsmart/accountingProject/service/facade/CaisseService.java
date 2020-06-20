package com.zsmart.accountingProject.service.facade;

import com.zsmart.accountingProject.bean.Caisse;

import java.math.BigDecimal;
import java.util.List;

public interface CaisseService {

 public Caisse save(Caisse caisse);

 public Caisse saveWithOperationComptables(Caisse caisse);

 public List<Caisse> findAll();

 public Caisse findById(Long id);

 public int delete(Caisse caisse);

 public void deleteById(Long id);

 public void clone(Caisse caisse, Caisse caisseClone);

 public Caisse clone(Caisse caisse);

 public Caisse findByAdherantIdAndId(Long adherentId, Long id);

 public List<Caisse> clone(List<Caisse> caisses);

 public List<Caisse> findByCriteria(String libelle, String code, Long adherentId, Long idMin, Long idMax, BigDecimal soldeMin, BigDecimal soldeMax);

 public List<Caisse> findCaissesByAdherantId(Long id);

 public void deleteByAdherantIdAndId(Long adherentId, Long id);


}
