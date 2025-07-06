package com.yunussemree.multimailsender.controller;

import com.yunussemree.multimailsender.model.ApiResponse;
import com.yunussemree.multimailsender.service.MailSenderService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MailSenderController {

    private final MailSenderService mailSenderService;

    public MailSenderController(MailSenderService mailSenderService) {
        this.mailSenderService = mailSenderService;
    }


    @GetMapping("/mail-sender")
    public ResponseEntity<ApiResponse> mailSender(@RequestParam String to, @RequestParam String subject, @RequestParam String body, @RequestParam String username, @RequestParam String password) {
        try{
            if (to == null || subject == null || body == null || username == null || password == null) {
                return ResponseEntity.badRequest().body(new ApiResponse("All fields are required", null));
            } else if(!to.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$") || !username.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                    return ResponseEntity.badRequest().body(new ApiResponse("Invalid email format", null));
            } else if (subject.length() > 25500) {
                return ResponseEntity.badRequest().body(new ApiResponse("Subject is too long", null));
            } else if (body.length() > 1000) {
                return ResponseEntity.badRequest().body(new ApiResponse("Body is too long", null));
            } else {
                mailSenderService.sendEmail(to, subject, body, username, password);
                return ResponseEntity.ok(new ApiResponse("Mail sent successfully to: " + to, null));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Error expected", e.getMessage()));
        }
    }
}
