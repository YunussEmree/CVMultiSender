package com.yunussemree.multimailsender.controller;

import com.yunussemree.multimailsender.model.ApiResponse;
import com.yunussemree.multimailsender.model.Request;
import com.yunussemree.multimailsender.service.MailSenderService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MailSenderController {

    private final MailSenderService mailSenderService;

    public MailSenderController(MailSenderService mailSenderService) {
        this.mailSenderService = mailSenderService;
    }
    
     /**     * Endpoint to send multiple emails with attachments based on the provided request.
     *
     * @param request The request containing email details and company data.
     * @param files   The files to be attached to the emails.
     * @return ResponseEntity with a message indicating success or failure.
     */
    @PostMapping(value = "/send-mails-with-attachment", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse> sendMails(
            @RequestPart("request") Request request,
            @RequestPart("files") MultipartFile[] files
    ) {
        try {
            mailSenderService.sendEmailsWithAttachments(request, files);
            return ResponseEntity.ok(new ApiResponse("Mails sent successfully", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse("Error expected", e.getMessage()));
        }

    }

    /**
     * Health check endpoint to verify if the service is running.
     *
     * @return ResponseEntity with a message indicating the service status.
     */
    @GetMapping("/health")
    public ResponseEntity<ApiResponse> healthCheck() {
        return ResponseEntity.ok(new ApiResponse("Server is running", null));
    }
}
