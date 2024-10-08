package CustomerController;


import java.io.IOException;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.Properties;

//@WebServlet("/ForgotPasswordServlet")
public class ForgetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the email from the form
        String email = request.getParameter("emailForOTP");

        // Generate a random 6-digit OTP
        Random rand = new Random();
        int otp = rand.nextInt(900000) + 100000;

        // Store the OTP in the session
        HttpSession session = request.getSession();
        session.setAttribute("generatedOTP", otp);
        session.setAttribute("emailForOTP", email);

        // Send the OTP via email
        boolean emailSent = sendOTPEmail(email, otp);

        if (emailSent) {
            // Redirect to OTP verification page
            response.sendRedirect("verifyOtp.jsp");
        } else {
            // Error handling
            response.getWriter().println("Error in sending email. Please try again.");
        }
    }

    // Method to send the OTP via email
    private boolean sendOTPEmail(String toEmail, int otp) {
        String fromEmail = "shaikasif938341@gmail.com";  // Replace with your email
        String password = "Shaikasif@8341";  // Replace with your email password
        String subject = "RevShop - OTP for Password Reset";
        String message = "Your OTP for password reset is: " + otp;

        // Set up email properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");  // Change as per your email provider
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");

        // Set up session with authentication
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            // Create the email message
            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(fromEmail));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);

            // Send the email
            Transport.send(mimeMessage);
            System.out.println("OTP email sent successfully");
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}