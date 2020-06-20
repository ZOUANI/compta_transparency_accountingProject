package com.zsmart.accountingProject.dao;

import com.zsmart.accountingProject.bean.TauxTva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TauxTvaDao extends JpaRepository<TauxTva, Long> {
    public List<TauxTva> findByAdherantIdAndId(Long adherentId, Long id);

    public List<TauxTva> findByAdherantId(Long id);
}
