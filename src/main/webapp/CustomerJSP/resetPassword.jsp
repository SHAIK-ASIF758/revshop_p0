<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Reset Password</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="../CSS/reset_password.css">
    
    <script>
        function togglePasswordVisibility(id, iconId) {
            var passwordField = document.getElementById(id);
            var icon = document.getElementById(iconId);
            if (passwordField.type === "password") {
                passwordField.type = "text";
                icon.classList.remove("fa-eye");
                icon.classList.add("fa-eye-slash");
            } else {
                passwordField.type = "password";
                icon.classList.remove("fa-eye-slash");
                icon.classList.add("fa-eye");
            }
        }
    </script>
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center mb-4">Reset Your Password</h2>
        <form action="/RevShop_Project_P0/PasswordResetServlet" method="post" class="form-group">

            <!-- Hidden input to pass OTP -->
            <input type="hidden" name="otp" value="${requestScope.otp}">

            <label for="newPassword"><b>New Password</b></label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text">
                        <i class="fa-solid fa-lock"></i>
                    </span>
                </div>
                <input type="password" id="newPassword" name="newPassword" class="form-control" required>
                <div class="input-group-append">
                    <span class="input-group-text" onclick="togglePasswordVisibility('newPassword', 'newPasswordIcon')" style="cursor: pointer;">
                        <i id="newPasswordIcon" class="fa-solid fa-eye"></i>
                    </span>
                </div>
            </div>

            <label for="confirmPassword"><b>Confirm Password</b></label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text">
                        <i class="fa-solid fa-lock"></i>
                    </span>
                </div>
                <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" required>
                <div class="input-group-append">
                    <span class="input-group-text" onclick="togglePasswordVisibility('confirmPassword', 'confirmPasswordIcon')" style="cursor: pointer;">
                        <i id="confirmPasswordIcon" class="fa-solid fa-eye"></i>
                    </span>
                </div>
            </div>

            <!-- Error message display if passwords don't match -->
            <div id="errorMessage" class="text-danger text-center mt-2" style="display:none;">
                Passwords do not match!
            </div>

            <button type="submit" class="btn btn-success btn-block mt-4" onclick="return validatePassword()">Reset Password</button>
        </form>
    </div>

    <script src="https://kit.fontawesome.com/2f6436cdea.js" crossorigin="anonymous"></script>
    <script>
        // Function to validate password match before submitting
        function validatePassword() {
            var newPassword = document.getElementById("newPassword").value;
            var confirmPassword = document.getElementById("confirmPassword").value;

            if (newPassword !== confirmPassword) {
                document.getElementById("errorMessage").style.display = "block";
                return false; // Prevent form submission
            } else {
                document.getElementById("errorMessage").style.display = "none";
                return true; // Allow form submission
            }
        }
    </script>
</body>
</html>