# MultiMailSender

MultiMailSender is an open-source bulk email sending application with a modern Angular-based frontend and a robust Spring Boot backend. It allows you to send personalized emails to multiple recipients, each with custom parameters and optional attachments.

![image](https://github.com/user-attachments/assets/2166bd99-db4a-401a-bc18-c9f11e82c905)

## Features

- Bulk email sending with per-recipient personalization
- Supports PDF and other file attachments
- Secure authentication using Gmail App Password
- User-friendly, modern, and well-documented interface
- Server health check endpoint
- Easy deployment with Docker support

## Technologies Used

- **Backend:** Java 17, Spring Boot 3, Spring Mail, Maven, Lombok
- **Frontend:** Angular 20, Bootstrap, RxJS
- **Other:** Docker (optional)

## Installation

### Backend (Spring Boot)

1. **Requirements:** Java 17+, Maven
2. **Setup:**
   ```bash
   cd backend/MultiMailSender
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```
   or
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
3. **To run with Docker:**
   - Update the `image` field in `compose.yaml` with your own image name.
   - Then run:
     ```bash
     docker compose -f compose.yaml up
     ```

### Frontend (Angular)

1. **Requirements:** Node.js (18+), npm
2. **Setup:**
   ```bash
   cd frontend/multi-mail-sender
   npm install
   npm start
   ```
   or
   ```bash
   ng serve
   ```
3. Access the app at [http://localhost:4200](http://localhost:4200)

## Usage

1. Enter your Gmail address and App Password.
2. Fill in the email subject and body. You can use placeholders like `{companyName}` in the body for personalization.
3. Add recipient companies/people with their email addresses and parameters.
4. Optionally, attach files (e.g., PDF).
5. Click the "Send Mails" button.

## API Reference

### Bulk Mail Sending

- **Endpoint:** `POST /send-mails-with-attachment`
- **Content-Type:** `multipart/form-data`
- **Parameters:**
  - `request`: JSON containing email and recipient details
  - `files`: (optional) Files to attach

#### Example Request JSON

```json
{
  "username": "example@gmail.com",
  "password": "examplePassword",
  "subject": "Internship Application - John Doe",
  "bodydraft": "Dear Hiring Manager,\n\nI am writing to express my interest in the internship position at your {companyName} company. I called you but you didn't answer on this number: {companyNumber}\n\nBest regards,\nJohn Doe",
  "companyData": [
    {
      "id": 0,
      "companyMail": "company0@gmail.com",
      "parameters": {
        "companyName": "company0",
        "companyNumber": "1234567890"
      }
    }
  ]
}
```

### Health Check

- **Endpoint:** `GET /health`
- **Response:**
  ```json
  {
    "message": "Server is running",
    "data": null
  }
  ```

## Contribution

1. Fork the repository and create a new branch.
2. Make and test your changes.
3. Submit a pull request.

## License

This project is licensed under the MIT License.
