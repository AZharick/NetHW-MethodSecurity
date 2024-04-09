package com.example.hiber.repository;

import com.example.hiber.domain.Persons;
import com.example.hiber.domain.PersonsPKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomRepo extends JpaRepository<Persons, PersonsPKey> {

   Persons save(Persons persons);

   Persons saveAndFlush(Persons persons);

   void deleteById(PersonsPKey personsPKey);

   List<Persons> findAll();

   // ================= AUX METHODS ======================
   List<Persons> getPersonsByCityOfLiving(String city);

   List<Persons> getPersonsByPersonsPKey_AgeLessThanOrderByPersonsPKey_AgeAsc(int age);

   Optional<Persons> findByPersonsPKey_NameAndPersonsPKey_Surname(String name, String surname);
}