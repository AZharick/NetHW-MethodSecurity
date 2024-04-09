package com.example.hiber.service;

import com.example.hiber.domain.Persons;
import com.example.hiber.domain.PersonsPKey;
import com.example.hiber.repository.CustomRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DBService {

   private CustomRepo customRepo;

   public DBService(CustomRepo customRepo) {
      this.customRepo = customRepo;
   }

   public List<Persons> findAll() {
      return customRepo.findAll();
   }

   public Persons createPerson(Persons person) {
      return customRepo.save(person);
   }

   public Persons updatePerson(Persons person) {
      return customRepo.saveAndFlush(person);
   }

   public void deletePerson(String name, String surname, int age) {
      PersonsPKey personsPKey = new PersonsPKey(name, surname, age);
      customRepo.deleteById(personsPKey);
   }

   // ================= AUX METHODS ======================
   public List<Persons> getPersonsByCityOfLiving(String city) {
      return customRepo.getPersonsByCityOfLiving(city);
   }

   public List<Persons> getPersonsByPersonsPKey_AgeLessThanOrderByPersonsPKey_AgeAsc(int age) {
      return customRepo.getPersonsByPersonsPKey_AgeLessThanOrderByPersonsPKey_AgeAsc(age);
   }

   public Optional<Persons> findByPersonsPKey_NameAndPersonsPKey_Surname(String name, String surname) {
      return customRepo.findByPersonsPKey_NameAndPersonsPKey_Surname(name, surname);
   }
}