package com.yunussemree.multimailsender.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;

@Data
public class Request {
    private String username;
    private String password;
    private String subject;
    private String bodydraft;
    private ArrayList<CompanyData> companyData;


    public Request(String to, String subject, String bodydraft, String username, String password, ArrayList<CompanyData> companyData) {
        this.username = username;
        this.password = password;
        this.subject = subject;
        this.bodydraft = bodydraft;
        this.companyData = companyData;
    }
}
//{
//        "username": "example@gmail.com",
//        "password": "examplePassword",
//        "subject": "Internship Application - Yunus Emre Şenyiğit",
//        "bodydraft": "Dear Hiring Manager,\\n\\nI am writing to express my interest in the internship position at your {companyName} company. I called you but you didn't answer on this number: {companyNumber}\\n\\nBest regards,\\nYunus Emre Şenyiğit",
//        "companyData": {
//        {"id":0, "companyMail": "company0@gmail.com", "parameters": ["companyName": "company0", "companyNumber": "1234567890"]},
//        {"id":1, "companyMail": "company1@gmail.com", "companyName": "company1"},
//        {"id":2, "companyMail": "company2@gmail.com", "companyName": "company2"}
//        }
//}