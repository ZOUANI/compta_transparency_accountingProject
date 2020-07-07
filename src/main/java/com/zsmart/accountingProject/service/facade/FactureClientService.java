package com.zsmart.accountingProject.service.facade;

import com.zsmart.accountingProject.bean.FactureClient;
import com.zsmart.accountingProject.bean.OperationComptable;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface FactureClientService {

    public FactureClient save(FactureClient factureclient);

    public FactureClient saveWithOperations(FactureClient factureclient);

    public FactureClient saveWithOperationsAndFactureItems(FactureClient factureClient);

    public void uploadScan(MultipartFile file, FactureClient factureClient);

    public Resource getScan(Long id);

    public List<FactureClient> findAll();

    public FactureClient findById(Long id);

    public FactureClient findBySocieteIdAndReference(Long id, String ref);

    public List<BigDecimal> calculateGain(int annee, Long socId, Long adherentId);

    public int delete(FactureClient factureclient);

    public void deleteById(Long id);

    public void deleteByAdherantIdAndId(Long adherentId, Long id);

    public int setEtatFacture(Long etatId, Long adhrentId, Long id);

    public void clone(FactureClient factureclient, FactureClient factureclientClone);

    public FactureClient clone(FactureClient factureclient);

    public List<FactureClient> clone(List<FactureClient> factureclients);

    public List<FactureClient> findByCriteria(FactureClient facture, Date dateFactureMin, Date dateFactureMax, Date dateSaisieMin, Date dateSaisieMax);

    public List<OperationComptable> generateOperationsForFacture(FactureClient factureClient);

    public Resource exportExcelFile(List<FactureClient> factureClients);

    public FactureClient findByAdherantIdAndId(Long adhrentId, Long id);

    public FactureClient findByAdherantIdAndIdAndSocieteId(Long adhrentId, Long id, Long socId);

    public void deleteFacWithAll(Long adherentId, Long facId) throws IOException;

    public Resource toPdf(FactureClient factureClient) throws IOException;

    public BigDecimal getTotalIncome(Long adherentId, Long socId, Date dateDebut, Date dateFin);

}
