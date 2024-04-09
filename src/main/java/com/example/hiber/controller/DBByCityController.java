package com.example.hiber.controller;

import com.example.hiber.domain.Persons;
import com.example.hiber.domain.PersonsPKey;
import com.example.hiber.service.DBService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping()
public class DBByCityController {
   private DBService service;

   public DBByCityController(DBService service) {
      this.service = service;
   }

   @GetMapping("/hi")  //page available for everyone
   String hi() {
      return "<h1> Hi! This page is available for everyone! </h1>";
   }

   @PostMapping("/create")
   @RolesAllowed("WRITE")
   Persons createPerson(@RequestParam String name, @RequestParam String surname, @RequestParam int age,
                        @RequestParam String cityOfLiving, @RequestParam String phoneNumber) {
      PersonsPKey personsPKey = new PersonsPKey(name, surname, age);
      Persons person = new Persons(personsPKey, cityOfLiving, phoneNumber);
      return service.createPerson(person);
   }

   @PutMapping("/update")
   Persons updatePerson(@RequestParam String name, @RequestParam String surname, @RequestParam int age,
                        @RequestParam String cityOfLiving, @RequestParam String phoneNumber) {
      PersonsPKey personsPKey = new PersonsPKey(name, surname, age);
      Persons person = new Persons(personsPKey, cityOfLiving, phoneNumber);
      return service.updatePerson(person);
   }

   @DeleteMapping("/delete")
   void deletePerson(@RequestParam String name, @RequestParam String surname, @RequestParam int age) {
      service.deletePerson(name, surname, age);
   }

   @GetMapping("/getAll")
   @Secured("ROLE_READ")
   List<Persons> findAll() {
      return service.findAll();
   }

   // ================= AUX METHODS ======================
   @GetMapping("/getByCity")
   @PreAuthorize("#username==authentication.name")
   List<Persons> getPersonsByCityOfLiving(@RequestParam String city, @RequestParam String username) {
      return service.getPersonsByCityOfLiving(city);
   }

   @GetMapping("/youngerThan")
   List<Persons> getPersonsByPersonsPKey_AgeLessThanOrderByPersonsPKey_AgeAsc(@RequestParam int age) {
      return service.getPersonsByPersonsPKey_AgeLessThanOrderByPersonsPKey_AgeAsc(age);
   }

   @GetMapping("/getByPK")
   Optional<Persons> findByPersonsPKey_NameAndPersonsPKey_Surname(@RequestParam String name, String surname) {
      return service.findByPersonsPKey_NameAndPersonsPKey_Surname(name, surname);
   }
}