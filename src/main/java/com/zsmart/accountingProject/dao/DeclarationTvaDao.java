package com.zsmart.accountingProject.dao;

import com.zsmart.accountingProject.bean.DeclarationTva;
import com.zsmart.accountingProject.bean.EtatFacture;
import com.zsmart.accountingProject.bean.Facture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeclarationTvaDao extends JpaRepository<DeclarationTva,Long> {

    public DeclarationTva findByDifferenceChargeGain(Integer differenceChargeGain);
    public int deleteByid(Long id);
    public DeclarationTva findByTotalTvaGain(Integer totalTvaGain);
    public DeclarationTva findByTotalTvaCharge(Integer totalTvaCharge);


}
