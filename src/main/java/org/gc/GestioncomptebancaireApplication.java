package org.gc;

import org.gc.dao.ClientRepository;
import org.gc.dao.CompteRepository;
import org.gc.dao.OperationRepository;
import org.gc.entities.*;
import org.gc.services.IBanqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class GestioncomptebancaireApplication implements CommandLineRunner {

    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private IBanqueService banqueService;

    public static void main(String[] args) {

        SpringApplication.run(GestioncomptebancaireApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Client c1 = clientRepository.save(new Client("Doudou", "doudou@gmail.com"));
        Client c2 = clientRepository.save(new Client("Arame", "arame@gmail.com"));

        Compte cp1 = compteRepository.save(new CompteCourant("c1", new Date(), 90000.00, c1, 6000.00));
        Compte cp2 = compteRepository.save(new CompteEpargne("c1", new Date(), 90000.00, c2, 5.5));

        Operation op1 = operationRepository.save(new Versement(new Date(), 4000.00, cp1));
        Operation op2 = operationRepository.save(new Versement(new Date(), 2300.00, cp1));
        Operation op3 = operationRepository.save(new Versement(new Date(), 7100.00, cp2));
        Operation op4 = operationRepository.save(new Versement(new Date(), 2500.00, cp2));

        Operation op5 = operationRepository.save(new Retrait(new Date(), 1400.00, cp1));
        Operation op6 = operationRepository.save(new Retrait(new Date(), 200.00, cp1));
        Operation op7 = operationRepository.save(new Retrait(new Date(), 100.00, cp2));
        Operation op8 = operationRepository.save(new Retrait(new Date(), 3000.00, cp2));

        banqueService.verser("c1", 111111.00);
    }
}

