package com.yunussemree.multimailsender.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class CompanyData {
    private int id;
    private String companyMail;
    private ArrayList<String> parameters;
}
//{"id":0, "companyMail": "company0@gmail.com", "parameters": ["companyName": "company0", "companyNumber": "1234567890"]}