package com.yunussemree.multimailsender.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@Data
@RequiredArgsConstructor
public class Request {

    @Size(min = 1, max = 255)
    @NotNull
    @NotBlank
    @Email
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 255)
    private String password;

    @Size(min = 1, max = 255)
    private String subject;

    @Size(min = 1, max = 10000)
    private String bodydraft;

    @Valid
    private ArrayList<CompanyData> companyData;

}

//{
//        "username": "example@gmail.com",
//        "password": "examplePassword",
//        "subject": "Internship Application - Yunus Emre Şenyiğit",
//        "bodydraft": "Dear Hiring Manager,\n\nI am writing to express my interest in the internship position at your {companyName} company. I called you but you didn't answer on this number: {companyNumber}\n\nBest regards,\nYunus Emre Şenyiğit",
//        "companyData": [
//        {
//        "id": 0,
//        "companyMail": "company0@gmail.com",
//        "parameters": {
//        "companyName": "company0",
//        "companyNumber": "1234567890"
//        }
//        },
//        {
//        "id": 1,
//        "companyMail": "company1@gmail.com",
//        "parameters": {
//        "companyName": "company1",
//        "companyNumber": "0987654321"
//        }
//        },
//        {
//        "id": 2,
//        "companyMail": "company2@gmail.com",
//        "parameters": {
//        "companyName": "company2",
//        "companyNumber": "5554443322"
//        }
//        }
//        ]
//        }
