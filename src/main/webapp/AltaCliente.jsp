<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alta de Cliente - Validaciones</title>
</head>
<body>
    <h1>Formulario de Cliente</h1>

    <form method="post" action="ClienteServlet">
        DNI: <input type="text" name="Dni"><br>
        CUIL: <input type="text" name="Cuil"><br>
        Nombre: <input type="text" name="Nombre"><br>
        Apellido: <input type="text" name="Apellido"><br><br>
        <input type="submit" name="btnGuardarCliente" value="Guardar cliente">
    </form>

    <%if(request.getAttribute("ControlesSeleccionados") != null){%>
        <p><%= request.getAttribute("ControlesSeleccionados") %></p>
    <%}%>
    <%if(request.getAttribute("error") != null) {%>
    <p style="color:red;"><%= request.getAttribute("error") %></p>
    <%}%>
    <%if(request.getAttribute("exito") != null) {
    %>
    <p style="color:green;"><%= request.getAttribute("exito") %></p>
    <%}%>
</body>
</html>
