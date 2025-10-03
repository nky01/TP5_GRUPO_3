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
    <title>Bienvenido</title>
</head>
<body>
    <h2>Bienvenido, <%= u.getUsuario() %></h2>
    <a href="LogoutServlet">Cerrar sesión</a>
</body>
</html>