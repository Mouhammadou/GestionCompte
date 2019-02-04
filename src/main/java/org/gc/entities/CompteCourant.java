package org.gc.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte{

    private Long decouvert;

    public CompteCourant(String codeCompte, Date dateCreation, Double solde, Client client) {
        super(codeCompte, dateCreation, solde, client);
        this.decouvert = decouvert;
    }

    public Long getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(Long decouvert) {
        this.decouvert = decouvert;
    }
}
