<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true" import="java.util.logging.*"%>
<!DOCTYPE html>

<%
	Exception ex = (Exception)request.getAttribute("Exception");
%>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>RevShop</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            background: url('/Project_P0/images/ErrorPage.jpeg') no-repeat center center fixed;
            background-size: cover;
            font-family: Arial, sans-serif;
            color: #fff;
            height: 100vh;
        }

        .error-container {
            position: absolute;
            top: 20px;
            right: 20px;
            background: rgba(0, 0, 0, 0.7); /* Dark transparent background */
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
            max-width: 400px;
        }

        h2 {
            margin-top: 0;
            color: #ff4c4c;
        }

        p {
            margin: 0;
            padding-bottom: 10px;
        }

        blockquote {
            background: #333;
            padding: 15px;
            border-left: 5px solid #ff4c4c;
            margin: 0;
            color: #f5f5f5;
        }
    </style>
    <script type="text/javascript">
        setTimeout(function() {
            window.location.href = '/Project_P0/JSP/Login_page.jsp';
        }, 7000);
    </script>
</head>
<body>
    <div class="error-container">
        <h2>An error occurred</h2>
        <p>Sorry, something went wrong.</p>
        <blockquote>
            Exception: <%= ex != null ? ex.getClass().getName() : "N/A" %><br/>
            Message: <%= ex != null ? ex.getMessage() : "No message available" %>
        </blockquote>
        <p style="margin:10px">You will be redirected to the main page in 7 seconds</p>
    </div>
</body>
</html>
