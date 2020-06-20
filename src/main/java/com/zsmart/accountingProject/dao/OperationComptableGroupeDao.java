package com.zsmart.accountingProject.dao;

import com.zsmart.accountingProject.bean.OperationComptableGroupe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OperationComptableGroupeDao extends JpaRepository<OperationComptableGroupe, Long> {


 public List<OperationComptableGroupe> findByAdherantId(Long adherentId);

 public OperationComptableGroupe findByAdherantIdAndId(Long adherentId, Long id);

}
