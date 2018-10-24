/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.simplon.springboot.simplecrud2.controller;

import co.simplon.springboot.simplecrud2.model.Acteur;
import co.simplon.springboot.simplecrud2.repository.ActeurRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vanessa
 */
@RestController
@RequestMapping("/api")
public class ActeurController {

    @Autowired
    ActeurRepository acteurRepository;
    
    @RequestMapping("/welcome")
    @ResponseBody
    public String welcome() {
        return "Welcome to RestTemplate Example.";
    }

    @CrossOrigin
    @GetMapping("/acteurs")
    List<Acteur> getAllActeur() {
        return acteurRepository.findAll();
    }

    @CrossOrigin
    @GetMapping("/acteur/{id}")
    ResponseEntity<Acteur> getActeurById(@PathVariable(value = "id") long id) {
        Acteur acteur = acteurRepository.findOne(id);
        if (acteur == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(acteur);
    }

    @CrossOrigin
    @PostMapping(value = "/acteur")
    Acteur addActeur(@Valid @RequestBody Acteur acteur) {
        acteur = new Acteur(1, "Kristen", "Stewart", "adresse", "mail", "phone");
        return acteurRepository.save(acteur);
    }

    @CrossOrigin
    @PutMapping("/acteur/{id}")
    ResponseEntity<Acteur> updateActeur(@PathVariable(value = "id") long id, @Valid @RequestBody Acteur acteur) {
        Acteur acteurToUpdate = acteurRepository.findOne(id);
        if (acteurToUpdate == null) {
            return ResponseEntity.notFound().build();
        }

        // Update the mandatory attributes
        acteurToUpdate.setFirstname(acteur.getFirstname());
        acteurToUpdate.setName(acteur.getName());

        // Update all other not null attributes
        if (acteur.getAddress() != null) {
            acteurToUpdate.setAddress(acteur.getAddress());
        }

        if (acteur.getPhone() != null) {
            acteurToUpdate.setPhone(acteur.getPhone());
        }

        if (acteur.getEmail() != null) {
            acteurToUpdate.setEmail(acteur.getEmail());
        }

        Acteur updatedPeople = acteurRepository.save(acteurToUpdate);
        return ResponseEntity.ok(updatedPeople);
    }

    @CrossOrigin
    @DeleteMapping("/people/{id}")
    ResponseEntity<Acteur> deleteActeur(@PathVariable(value = "id") long id) {
        Acteur acteur = acteurRepository.findOne(id);
        if (acteur == null) {
            return ResponseEntity.notFound().build();
        }

        acteurRepository.delete(acteur);
        return ResponseEntity.ok().build();
    }

}
