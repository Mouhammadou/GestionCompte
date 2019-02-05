package org.gc.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Entity
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte {

    private Double taux;

    public CompteEpargne(){
        super();
    }

    public CompteEpargne(String codeCompte, Date dateCreation, Double solde, Client client, Double taux) {
        super(codeCompte, dateCreation, solde, client);
        this.taux = taux;
    }

    public Double getTaux() {

        return taux;
    }

    public void setTaux(Double taux) {

        this.taux = taux;
    }
}
