package org.gc.web;

import org.gc.entities.Compte;
import org.gc.entities.Operation;
import org.gc.services.IBanqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        model.addAttribute("codeCompte", codeCompte);
        try {
            Compte cp = banqueService.consulterCompte(codeCompte);
            Page<Operation> pageOperations = banqueService.listOperation(codeCompte, 0, 4);
            model.addAttribute("listOperations", pageOperations.getContent());
            model.addAttribute("compte", cp);
        } catch (Exception e){
            model.addAttribute("exception", e);
        }
        return "comptes";
    }

    @RequestMapping(value = "/saveOperation", method = RequestMethod.POST)
    public String saveOperation(Model model, String typeOperation, String codeCompte, double montant, String codeCompte2){
        try {
            if (typeOperation.equals("versement")){
                banqueService.verser(codeCompte, montant);
            }
            else if (typeOperation.equals("retrait")){
                banqueService.retirer(codeCompte, montant);
            }
            else if (typeOperation.equals("virement")){
                banqueService.virement(codeCompte, codeCompte2, montant);
            }
        } catch (Exception e){
            model.addAttribute("error", e);
        }
        return "redirect:/consulterCompte?codeCompte="+codeCompte;
    }
}
