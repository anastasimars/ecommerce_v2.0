package pl.akademiaspecjalistowit.ecommerce.email.service;

public interface EmailService {
    void sendEmail(String receiver, String subject, String text);
}
