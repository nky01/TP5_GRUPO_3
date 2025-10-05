<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="entidades.Usuario" %>
<%@ page session="true" %>
<%
    Usuario u = (Usuario) session.getAttribute("usuarioLogueado");
    if (u == null) {
        response.sendRedirect("Login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alta de Cliente - Validaciones</title>
</head>
<body>
    <h1>Formulario de Cliente</h1>
	<label>Usuario: <%= u.getUsuario() %></label>
    <form method="post" action="ClienteServlet">
        <label>DNI:</label>
        <input type="text" name="Dni"><br>

        <label>CUIL:</label>
        <input type="text" name="Cuil"><br>

        <label>Nombre:</label>
        <input type="text" name="Nombre"><br>

        <label>Apellido:</label>
        <input type="text" name="Apellido"><br>

        <label>Sexo:</label>
        <select name="Sexo">
            <option value="default">-- Seleccione --</option>
            <option value="M">Masculino</option>
            <option value="F">Femenino</option>
            <option value="O">Otro</option>
        </select><br>

        <label>Nacionalidad:</label>
        <input type="text" name="Nacionalidad"><br>

        <label>Correo electrónico:</label>
        <input type="email" name="Correo"><br>

        <label>Teléfono:</label>
        <input type="text" name="Telefono"><br>

        <label>Dirección:</label>
        <input type="text" name="Direccion"><br>

        <label>Localidad:</label>
        <input type="text" name="Localidad"><br>

        <label>Provincia:</label>
        <input type="text" name="Provincia"><br>

        <label>Fecha de nacimiento:</label>
        <input type="date" name="FechaNacimiento"><br><br>

        <input type="submit" name="btnGuardarCliente" value="Guardar cliente">
    </form>

   
    <% if(request.getAttribute("Error") != null) { %>
        <p style="color:red;"><%= request.getAttribute("Error") %></p>
    <% } %>

    <% if(request.getAttribute("Exito") != null) { %>
        <p style="color:green;"><%= request.getAttribute("Exito") %></p>
    <% } %>

</body>
</html>
