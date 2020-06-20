package com.zsmart.accountingProject.dao;

import com.zsmart.accountingProject.bean.Caisse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CaisseDao extends JpaRepository<Caisse, Long> {

    public List<Caisse> findCaissesByAdherantId(Long id);

    public Caisse findByAdherantIdAndId(Long adherentId, Long id);

    public void deleteByAdherantIdAndId(Long adherentId, Long id);
}
