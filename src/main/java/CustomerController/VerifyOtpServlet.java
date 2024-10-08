package CustomerController;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//@WebServlet("/VerifyOtpServlet")
public class VerifyOtpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the entered OTP
        String enteredOtp = request.getParameter("otp");

        // Get the generated OTP from session
        HttpSession session = request.getSession();
        int generatedOtp = (int) session.getAttribute("generatedOTP");

        if (enteredOtp.equals(String.valueOf(generatedOtp))) {
            // OTP verified, redirect to password reset page
            response.sendRedirect("resetPassword.jsp");
        } else {
            // OTP mismatch, display error
            response.getWriter().println("Invalid OTP. Please try again.");
        }
    }
}
