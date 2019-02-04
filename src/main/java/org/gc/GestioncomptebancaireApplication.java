package org.gc;

import org.gc.dao.ClientRepository;
import org.gc.dao.CompteRepository;
import org.gc.dao.OperationRepository;
import org.gc.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestioncomptebancaireApplication implements CommandLineRunner {

    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private ClientRepository clientRepository;

    public static void main(String[] args) {

        SpringApplication.run(GestioncomptebancaireApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


    }
}

