package org.gc.services;

import org.gc.entities.Compte;
import org.gc.entities.Operation;
import org.springframework.data.domain.Page;

public interface IBanqueService {

    public Compte consulterCompte (String cdeCpte);
    public void verser(String cdeCpte, Double montant);
    public void retirer(String cdeCpte, Double montant);
    public void virement(String c1, String c2, Double montant);
    public Page<Operation> listOperation(String cdeCpte, int page, Double montant);
}
