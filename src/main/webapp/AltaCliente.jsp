<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Alta de Cliente - Validaciones</title>

    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

   
    <link rel="stylesheet" href="https://cdn.datatables.net/2.3.4/css/dataTables.dataTables.css">

    
    <link rel="stylesheet" href="css/StyleSheet.css">
</head>
<body>

   
    <div class="sidebar">
    <h3>Menú</h3>
    <a href="AltaCliente.jsp" class="active">Alta de Cliente</a>
    <a href="ListadoClientes.jsp">Listado de Clientes</a>
	</div>

 
    <div class="content">
        <h1>Formulario de Cliente</h1>
        <p class="user-info">Usuario: <strong><%= u.getUsuario() %></strong></p>

        <div class="form-container">
            <form method="post" action="ClienteServlet" class="row g-3">
                <div class="col-md-6">
                    <label class="form-label">DNI *</label>
                    <input type="text" name="Dni" class="form-control" placeholder="Solo números. 6 a 8 dígitos (ej: 12345678)">
                </div>

                <div class="col-md-6">
                    <label class="form-label">CUIL *</label>
                    <input type="text" name="Cuil" class="form-control" placeholder="Formato: 11 dígitos (ej: 20333444555)">
                </div>

                <div class="col-md-6">
                    <label class="form-label">Nombre *</label>
                    <input type="text" name="Nombre" class="form-control">
                </div>

                <div class="col-md-6">
                    <label class="form-label">Apellido *</label>
                    <input type="text" name="Apellido" class="form-control">
                </div>

                <div class="col-md-6">
                    <label class="form-label">Sexo *</label>
                    <select name="Sexo" class="form-select">
                        <option value="">-- Seleccione --</option>
                        <option value="M">Masculino</option>
                        <option value="F">Femenino</option>
                        <option value="O">Otro</option>
                    </select>
                </div>

                <div class="col-md-6">
                    <label class="form-label">Nacionalidad *</label>
                    <input type="text" name="Nacionalidad" class="form-control">
                </div>

                <div class="col-md-6">
                    <label class="form-label">Fecha de nacimiento *</label>
                    <input type="date" name="FechaNacimiento" class="form-control">
                </div>

                <div class="col-md-6">
                    <label class="form-label">Dirección *</label>
                    <input type="text" name="Direccion" class="form-control">
                </div>

                <div class="col-md-6">
                    <label class="form-label">Localidad *</label>
                    <input type="text" name="Localidad" class="form-control">
                </div>

                <div class="col-md-6">
                    <label class="form-label">Provincia *</label>
                    <input type="text" name="Provincia" class="form-control">
                </div>

                <div class="col-md-6">
                    <label class="form-label">Correo electrónico *</label>
                    <input type="email" name="Correo" class="form-control" placeholder="Ej: nombre@dominio.com">
                </div>

                <div class="col-md-6">
                    <label class="form-label">Teléfono *</label>
                    <input type="tel" name="Telefono" class="form-control">
                </div>

                <div class="col-12 mt-4">
                    <button type="submit" name="btnGuardarCliente" class="btn btn-primary">Guardar cliente</button>
                </div>
            </form>
        </div>

      
        <div class="mt-3">
            <% if(request.getAttribute("Error") != null) { %>
                <p class="text-danger"><%= request.getAttribute("Error") %></p>
            <% } %>

            <% if(request.getAttribute("Exito") != null) { %>
                <p class="text-success"><%= request.getAttribute("Exito") %></p>
            <% } %>
        </div>
    </div>

 
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

 
    <script src="https://cdn.datatables.net/2.3.4/js/dataTables.js"></script>

</body>
</html>
