package com.BankApplication.Validations;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class GenerateAccountNo {

   public Long genAccNo(){
       Random random = new Random();
       return Math.round(random.nextFloat() * Math.pow(10,15));

   }
}
