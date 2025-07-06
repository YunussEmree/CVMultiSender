package com.yunussemree.multimailsender.model;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.HashMap;

@Data
public class CompanyData {

    @Min(0)
    @NotNull
    private int id;

    @NotNull
    @Email
    private String companyMail;

    private HashMap<String, String> parameters;
}

/*
{
            "id": 1,
            "companyMail": "company1@gmail.com",
            "parameters": {
                "companyName": "company1",
                "companyNumber": "0987654321"
            }
        }
 */