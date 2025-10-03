<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h2>Iniciar sesión</h2>
    <form action="LoginServlet" method="post">
        <label>Usuario:</label><br>
        <input type="text" name="usuario"><br><br>
        
        <label>Contraseña:</label><br>
        <input type="password" name="contrasena"><br><br>
        
        <button type="submit">Ingresar</button>
    </form>
    
    <p style="color:red;">
        ${error}
    </p>
</body>
</html>