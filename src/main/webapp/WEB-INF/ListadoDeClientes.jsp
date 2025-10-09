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
    int paginaActual = (request.getAttribute("paginaActual") != null) 
                        ? (Integer) request.getAttribute("paginaActual") : 1;
    ArrayList<Cliente> clientes = (ArrayList<Cliente>) request.getAttribute("clientes");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listado de Clientes</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/StyleSheet.css">
</head>
<body>

<div class="sidebar">
    <h3>Menú</h3>
    <a href="AltaCliente.jsp" class="active">Alta de Cliente</a>
    <a href="ListarServlet">Listado de Clientes</a>
</div>

<div class="content">
    <h1>Listado de Clientes</h1>
    <br>
    <p class="user-info">Usuario: <strong><%= u.getUsuario() %></strong></p>

	<p class="user-info">Listado de Clientes <strong>(<%= request.getAttribute("totalRegistros") != null 
        ? request.getAttribute("totalRegistros") 
        : (clientes != null ? clientes.size() : 0) %>)</strong></p>
        
	<div class="d-flex align-items-center mb-3">
	    <label for="cantidad" class="me-2 mb-0 fw-normal">Mostrar</label>
	    <select id="cantidad" name="cantidad" class="form-select form-select-sm me-2" style="width: auto;" onchange="cambiarCantidad(this.value)">
	        <option value="5"  <%= (request.getAttribute("tamañoPagina") != null && (int)request.getAttribute("tamañoPagina") == 5)  ? "selected" : "" %>>5</option>
	        <option value="10" <%= (request.getAttribute("tamañoPagina") != null && (int)request.getAttribute("tamañoPagina") == 10) ? "selected" : "" %>>10</option>
	    </select>
	    <span class="fw-normal">registros</span>
	</div>

	
	
    <div class="form-container">
        <table class="table table-striped table-bordered">
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
                            <td><%= c.getDni() %></td>
                            <td><%= c.getCuil() %></td>
                            <td><%= c.getNombre() %></td>
                            <td><%= c.getApellido() %></td>
                            <td><%= c.getSexo() %></td>
                            <td><%= c.getFecha_nacimiento() %></td>
                            <td><%= c.getDireccion() %></td>
                            <td><%= c.getNacionalidad() %></td>
                            <td><%= c.getLocalidad() %></td>
                            <td><%= c.getProvincia() %></td>
                        </tr>
                <%  } } %>
            </tbody>
        </table>
        
        <%
		    int totalRegistros = (request.getAttribute("totalRegistros") != null) 
		                            ? (Integer) request.getAttribute("totalRegistros") : 0;
		    int tamañoPagina = (request.getAttribute("tamañoPagina") != null) 
		                            ? (Integer) request.getAttribute("tamañoPagina") : 5;
		
		    int inicio = (paginaActual - 1) * tamañoPagina + 1;
		    int fin = inicio + clientes.size() - 1;
		    if (fin > totalRegistros) fin = totalRegistros;
		%>
		
		<p class="text-muted">
		    Mostrando <%= inicio %> a <%= fin %> de <%= totalRegistros %> registros
		</p>
		        

        <div class="d-flex justify-content-center mt-3">
            <% if (paginaActual > 1) { %>
                <a class="btn btn-primary me-2" href="ListarServlet?pagina=<%=paginaActual-1%>&cantidad=<%=request.getAttribute("tamañoPagina")%>">Anterior</a>
            <% } else { %>
                <button class="btn btn-secondary me-2" disabled>Anterior</button>
            <% } %>

            <span class="btn btn-light disabled">Página <%=paginaActual%></span>

            <a class="btn btn-primary ms-2" href="ListarServlet?pagina=<%=paginaActual+1%>&cantidad=<%=request.getAttribute("tamañoPagina")%>">Siguiente</a>
        </div>

    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
function cambiarCantidad(valor) {
    const paginaActual = <%= paginaActual %>;
    window.location.href = "ListarServlet?cantidad=" + valor + "&pagina=" + paginaActual;
}
</script>

</body>
</html>
