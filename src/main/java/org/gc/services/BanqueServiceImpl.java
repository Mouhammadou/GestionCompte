package org.gc.services;

import org.gc.dao.CompteRepository;
import org.gc.dao.OperationRepository;
import org.gc.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class BanqueServiceImpl implements IBanqueService {

    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private OperationRepository operationRepository;


    @Override
    public Compte consulterCompte(String cdeCpte) {
        Compte compte = compteRepository.getOne(cdeCpte);
        if (compte == null)
            throw new RuntimeException("Compte introuvable");
        return compte;
    }

    @Override
    public void verser(String cdeCpte, double montant) {
        Compte cp = consulterCompte(cdeCpte);
        Versement v = new Versement(new Date(), montant, cp);
        operationRepository.save(v);
        cp.setSolde(cp.getSolde() + montant);
        compteRepository.save(cp);
    }

    @Override
    public void retirer(String cdeCpte, double montant) {
        double caisse=0;
        Compte cp = consulterCompte(cdeCpte);
        if (cp instanceof CompteCourant)
            caisse=((CompteCourant) cp).getDecouvert();
        if (cp.getSolde() + caisse < montant)
            throw new RuntimeException("Solde insuffisant");
        Retrait v = new Retrait(new Date(), montant, cp);
        operationRepository.save(v);
        cp.setSolde(cp.getSolde() - montant);
        compteRepository.save(cp);
    }

    @Override
    public void virement(String c1, String c2, double montant) {
        retirer(c1, montant);
        verser(c2, montant);
    }

    @Override
    public Page<Operation> listOperation(String cdeCpte, int page, int size) {
        return operationRepository.listOperation(cdeCpte, new PageRequest(page, size));
    }
}
