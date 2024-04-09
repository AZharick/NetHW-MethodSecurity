package com.example.hiber.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class PersonsPKey implements Serializable {

   @Column
   private String name;

   @Column
   private String surname;

   @Column
   private int age;
}