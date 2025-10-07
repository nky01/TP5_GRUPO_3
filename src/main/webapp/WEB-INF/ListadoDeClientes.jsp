<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entidades.Usuario" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidades.Cliente" %>

<%@ page session="true" %>
<%
    Usuario u = (Usuario) session.getAttribute("usuarioLogueado");
    if (u == null) {
        response.sendRedirect("Login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<title>Listado de Clientes</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" href="https://cdn.datatables.net/2.3.4/css/dataTables.dataTables.css">
    
    <link rel="stylesheet" href="css/StyleSheet.css">
</head>
<body>

    <div class="sidebar">
    <h3>Menú</h3>
    <a href="AltaCliente.jsp" class="active">Alta de Cliente</a>
    <a href="ListarServlet">Listado de Clientes</a>
	</div>

 
    <div class="content">
        <h1>Formulario de Cliente</h1>
        <p class="user-info">Usuario: <strong><%= u.getUsuario() %></strong></p>

        <div class="form-container">
        <%
		ArrayList<Cliente> clientes = (ArrayList<Cliente>) request.getAttribute("clientes");
		%>
        <table id="tablaClientes" class="display table table-striped table-bordered">
        <thead>
            <tr>
                <th>DNI</th>
                <th>CUIL</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Sexo</th>
                <th>Fecha Nacimiento</th>
                <th>Direccion</th>
                <th>Nacionalidad</th>
                <th>Localidad</th>
                <th>Provincia</th>
            </tr>
        </thead>
        <tbody>
            <% if (clientes != null) {
                for (Cliente c : clientes) { %>
                    <tr>
                        <td><%= c.getDni() 					%></td>
                        <td><%= c.getCuil() 				%></td>
                        <td><%= c.getNombre() 				%></td>
                        <td><%= c.getApellido() 			%></td>
                        <td><%= c.getSexo() 				%></td>
                        <td><%= c.getFecha_nacimiento() 	%></td>
                        <td><%= c.getDireccion() 			%></td>
                        <td><%= c.getNacionalidad()			%></td>                        
                        <td><%= c.getLocalidad() 			%></td>
                        <td><%= c.getProvincia() 			%></td>

                    </tr>
            <%  } } %>
        </tbody>
    </table>
</div>
 <script> 
 	new DataTable('#tablaClientes')
 </script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

 
    <script src="https://cdn.datatables.net/2.3.4/js/dataTables.js"></script>

</body>
</html>
