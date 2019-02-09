package org.gc.web;

import org.gc.entities.Compte;
import org.gc.services.IBanqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BanqueController {

    @Autowired
    private IBanqueService banqueService;

    @RequestMapping("/operations")
    public String index(){
        return "comptes";
    }

    @RequestMapping("/consulterCompte")
    public String consulter(Model model, String codeCompte){
        try {
            Compte cp = banqueService.consulterCompte(codeCompte);
            model.addAttribute("compte", cp);
        } catch (Exception e){
            model.addAttribute("exception", e);
        }
        return "comptes";
    }
}
