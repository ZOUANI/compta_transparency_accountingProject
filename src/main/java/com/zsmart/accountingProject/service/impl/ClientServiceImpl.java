
package com.zsmart.accountingProject.service.impl;

import com.zsmart.accountingProject.bean.Client;
import com.zsmart.accountingProject.bean.FactureClient;
import com.zsmart.accountingProject.dao.ClientDao;
import com.zsmart.accountingProject.service.facade.ClientService;
import com.zsmart.accountingProject.service.facade.FactureClientService;
import com.zsmart.accountingProject.service.util.SearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service

public class ClientServiceImpl implements ClientService {


    @Autowired

    private ClientDao clientDao;

    @Autowired

    private EntityManager entityManager;

    @Autowired

    private FactureClientService factureclientService;

    @Override
    public Client save(Client client) {

        if (client == null) {
            return null;
        } else {
            clientDao.save(client);
            return client;
        }
    }

    @Override
    public Client saveWithFactureClients(Client client) {

        if (client == null) {
            return null;
        } else {
            if (client.getFactureClients().isEmpty()) {
                return null;
            } else {
                clientDao.save(client);
                for (FactureClient factureclient : client.getFactureClients()) {
                    factureclient.setClient(client);
                    factureclientService.save(factureclient);
                }
                return client;
            }
        }
    }

    @Override
    public List<Client> findAll() {
        return clientDao.findAll();
    }

    @Override
    public Client findById(Long id) {
        return clientDao.getOne(id);
    }

    @Override
    public int delete(Client client) {
        if (client == null) {
            return -1;
        } else {
            clientDao.delete(client);
            return 1;
        }
    }

    @Override
    public void deleteById(Long id) {
        clientDao.deleteById(id);
    }

    public void clone(Client client, Client clientClone) {
        if (client != null && clientClone != null) {
            clientClone.setId(client.getId());
            clientClone.setIce(client.getIce());
            clientClone.setIdentifiantFiscale(client.getIdentifiantFiscale());
            clientClone.setRc(client.getRc());
            clientClone.setLibelle(client.getLibelle());
            clientClone.setCode(client.getCode());
            clientClone.setFactureClients(factureclientService.clone(client.getFactureClients()));
        }
    }

    public Client clone(Client client) {
        if (client == null) {
            return null;
        } else {
            Client clientClone = new Client();
            clone(client, clientClone);
            return clientClone;
        }
    }

    public List<Client> clone(List<Client> clients) {
        if (clients == null) {
            return null;
        } else {
            List<Client> clientsClone = new ArrayList();
            clients.forEach((client) -> {
                clientsClone.add(clone(client));
            });
            return clientsClone;
        }
    }

    @Override
    public List<Client> findByCriteria(String ice, String identifiantFiscale, String rc, String libelle, String code, Long adherentId) {
        return entityManager.createQuery(constructQuery(ice, identifiantFiscale, rc, libelle, code, adherentId)).getResultList();
    }

    @Override
    public List<Client> findByAdherantId(Long id) {
        return clientDao.findByAdherantId(id);
    }

    @Transactional
    @Override
    public void deleteByAdherantIdAndId(Long adherentId, Long id) {
        clientDao.deleteByAdherantIdAndId(adherentId, id);
    }

    @Override
    public Client findByAdherantIdAndId(Long adherentId, Long id) {
        return clientDao.findByAdherantIdAndId(adherentId, id);
    }

    private String constructQuery(String ice, String identifiantFiscale, String rc, String libelle, String code, Long adherentId) {
        String query = "SELECT c FROM Client c where 1=1 ";
        query += SearchUtil.addConstraint("c", "ice", "=", ice);
        query += SearchUtil.addConstraint("c", "identifiantFiscale", "=", identifiantFiscale);
        query += SearchUtil.addConstraint("c", "rc", "=", rc);
        query += SearchUtil.addConstraint("c", "libelle", "LIKE", libelle);
        query += SearchUtil.addConstraint("c", "code", "=", code);
        query += SearchUtil.addConstraint("c", "adherant.id", "=", adherentId);

        return query;
    }
}
