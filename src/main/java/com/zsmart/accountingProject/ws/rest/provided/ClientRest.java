package com.zsmart.accountingProject.ws.rest.provided;


import com.zsmart.accountingProject.bean.Client;
import com.zsmart.accountingProject.service.facade.ClientService;
import com.zsmart.accountingProject.service.util.NumberUtil;
import com.zsmart.accountingProject.ws.rest.converter.ClientConverter;
import com.zsmart.accountingProject.ws.rest.vo.ClientVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/accountingProject/Client")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ClientRest {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientConverter clientConverter;

    @PreAuthorize("hasRole('ADHERENT') or hasRole('ADMIN')")
    @PostMapping("/")
    public ClientVo save(@RequestBody ClientVo clientVo) {
        clientConverter.setAdherant(true);
        Client client = clientConverter.toItem(clientVo);
        return clientConverter.toVo(clientService.save(client));
    }

    @PreAuthorize("hasRole('ADHERENT') or hasRole('ADMIN')")
    @PostMapping("/findByCriteria")
    public List<ClientVo> findByCriteria(@RequestBody ClientVo clientVo) {
        clientConverter.setAdherant(true);
        Client client = clientConverter.toItem(clientVo);
        return clientConverter.toVo(clientService.findByCriteria(client.getIce(), client.getIdentifiantFiscale(), client.getRc(), client.getLibelle(), client.getCode(), client.getAdherant().getId()));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        clientService.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/findall")
    public List<ClientVo> findAll() {
        return clientConverter.toVo(clientService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT') or hasRole('COMPTABLE')")
    public List<ClientVo> findByAdherent(@PathVariable Long id) {
        return clientConverter.toVo(clientService.findByAdherantId(id));
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT')")
    @DeleteMapping("/delete/adherent/{adherentId}/id/{id}")
    public void deleteById(@PathVariable Long adherentId, @PathVariable Long id) {
        clientService.deleteByAdherantIdAndId(adherentId, id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT')")
    @PostMapping("/deleteAll/")
    public void deleteAllByAdherentId(@RequestBody List<ClientVo> clientVos) {
        for (ClientVo clientVo : clientVos
        ) {
            if (clientVo.getId() != null)
                clientService.deleteByAdherantIdAndId(NumberUtil.toLong(clientVo.getAdherantVo().getId()), NumberUtil.toLong(clientVo.getId()));
        }

    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('ADHERENT')")
    @GetMapping("/adherent/{adherentId}/id/{id}")
    public ClientVo findByAdherentAndId(@PathVariable Long adherentId, @PathVariable Long id) {
        try {
            return clientConverter.toVo(clientService.findByAdherantIdAndId(adherentId, id));
        } catch (EntityNotFoundException e) {
            return null;
        }

    }

    public ClientConverter getClientConverter() {
        return clientConverter;
    }

    public void setClientConverter(ClientConverter clientConverter) {
        this.clientConverter = clientConverter;
    }

    public ClientService getClientService() {
        return clientService;
    }

    public void setClientService(ClientService clientService) {
        this.clientService = clientService;
    }

}