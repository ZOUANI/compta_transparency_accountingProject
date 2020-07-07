package com.zsmart.accountingProject.service.facade;

import com.zsmart.accountingProject.bean.FactureFournisseur;
import com.zsmart.accountingProject.bean.OperationComptable;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface FactureFournisseurService {

    public FactureFournisseur save(FactureFournisseur facturefournisseur);

    public FactureFournisseur saveWithOperations(FactureFournisseur facturefournisseur);

    public FactureFournisseur saveWithOperationsAndFactureItems(FactureFournisseur facturefournisseur);

    public void uploadScan(MultipartFile file, FactureFournisseur factureFournisseur);

    public Resource getScan(Long id);

    public List<FactureFournisseur> findAll();

    public FactureFournisseur findById(Long id);

    public FactureFournisseur findBySocieteIdAndReference(Long id, String ref);

    public List<BigDecimal> calculateChargeParAnneeEtSocieteId(int annee, Long id);

    public int delete(FactureFournisseur facturefournisseur);

    public void deleteById(Long id);

    public void deleteByAdherentIdAndId(Long adherentId, Long id);

    public int setEtatFacture(Long etatId, Long adhrentId, Long id);

    public void clone(FactureFournisseur facturefournisseur, FactureFournisseur facturefournisseurClone);

    public FactureFournisseur clone(FactureFournisseur facturefournisseur);

    public List<FactureFournisseur> clone(List<FactureFournisseur> facturefournisseurs);

    public List<FactureFournisseur> findByCriteria(FactureFournisseur factureFournisseur, Date dateFactureMin, Date dateFactureMax, Date dateSaisieMin, Date dateSaisieMa);

    public List<OperationComptable> generateOperationsForFacture(FactureFournisseur factureFournisseur);

    public Resource exportExcelFile(List<FactureFournisseur> factureFournisseurs);

    public FactureFournisseur findByAdherantIdAndId(Long adhrentId, Long id);

    public FactureFournisseur findByAdherantIdAndIdAndSocieteId(Long adhrentId, Long id, Long socId);

    public BigDecimal getTotalExpense(Long adherentId, Long socId, Date dateDebut, Date dateFin);

    public void deleteFacWithAll(Long adherentId, Long facId) throws IOException;

    public Resource toPdf(FactureFournisseur factureFournisseur) throws IOException;
}
