package com.zsmart.accountingProject.dao;

import com.zsmart.accountingProject.bean.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClientDao extends JpaRepository<Client, Long> {

    public List<Client> findByAdherantId(Long id);

    public Client findByAdherantIdAndId(Long adherentId, Long id);

    public void deleteByAdherantIdAndId(Long adherentId, Long id);

}
