package com.BankApplication.Validations;

import com.BankApplication.Entity.Customer;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

@Service
public class ValidationAccount {

    public boolean validateDetails(Customer customeraccount){

        if(StringUtils.isEmpty(customeraccount.getCustomerEmail()) ||
                StringUtils.isEmpty(customeraccount.getAadharNo()) ||
                StringUtils.isEmpty(customeraccount.getAnnualIncome()) ||
                StringUtils.isEmpty(customeraccount.getFatherName()) ||
                StringUtils.isEmpty(customeraccount.getCustomerName()) ||
                StringUtils.isEmpty(customeraccount.getPanNo()) ||
                StringUtils.isEmpty(customeraccount.getCustomerPhoneNo())) {
            return false;
        }
        // sun ye konsa function h string emply
        // Ye check karega "", "    ", null ye sabokk


        else if(ValidateEmail(customeraccount.getCustomerEmail())==false){
           return false;
        }

        else if(ValidateAadharNo(customeraccount.getAadharNo())==true){
            return false;
        }

        else if(ValidateIncome(customeraccount.getAnnualIncome())==false){
            return false;
        }

        else if(Validatephone(customeraccount.getCustomerPhoneNo())==false){
            return false;
        }

        return true;
    }

    public boolean ValidateEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }


    public boolean ValidateAadharNo(Long Aadhar_No) {
        int length = String.valueOf(Aadhar_No).length();
        if(length<15)
            return true;

        return false;
    }

    public boolean ValidateIncome(Double Income){
        if(Income<30000)
            return false;

        return true;
    }

    public boolean Validatephone(Long number){
        int phone_no = String.valueOf(number).length();
        if(phone_no==10)
           return true;
        return false;
    }

    public Long validateCardNo(){
        Random random = new Random();
        return Math.round(random.nextFloat() * Math.pow(10,15));
    }


    public int validateCvv() {
        double n = Math.random();
        int n3 = (int) Math.round(Math.random()*1000);
        return n3;
    }

    public String validateDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
        Date currentDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.YEAR, 5);
        c.add(Calendar.MONTH, 5*12);
        Date currentDatePlusOne = c.getTime();
        return dateFormat.format(currentDatePlusOne);
    }
}
