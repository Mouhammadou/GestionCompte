package org.gc.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte{

    private double decouvert;

    public CompteCourant() {

        super();
    }

    public CompteCourant(String codeCompte, Date dateCreation, double solde, Client client, double decouvert) {
        super(codeCompte, dateCreation, solde, client);
        this.decouvert = decouvert;
    }

    public double getDecouvert() {

        return decouvert;
    }

    public void setDecouvert(Double decouvert) {

        this.decouvert = decouvert;
    }
}
