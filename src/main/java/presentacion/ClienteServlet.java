package presentacion;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import datos.ClienteDAO;
import entidades.Cliente;

@WebServlet("/ClienteServlet")
public class ClienteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ClienteServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("btnGuardarCliente") != null) {
            eventobtnGuardarCliente(request, response);
        }
    }

    private void eventobtnGuardarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String error = "";
        String exito = "";
        java.sql.Date fechaNacimiento = null;

        try {
            String dni = request.getParameter("Dni");
            String cuil = request.getParameter("Cuil");
            String nombre = request.getParameter("Nombre");
            String apellido = request.getParameter("Apellido");
            String sexoStr = request.getParameter("Sexo");
            String nacionalidad = request.getParameter("Nacionalidad");
            String correo = request.getParameter("Correo");
            String telefono = request.getParameter("Telefono");
            String direccion = request.getParameter("Direccion");
            String localidad = request.getParameter("Localidad");
            String provincia = request.getParameter("Provincia");
            String fechaStr = request.getParameter("FechaNacimiento");
            
            if (dni == null || dni.isEmpty() ||
                cuil == null || cuil.isEmpty() ||
                nombre == null || nombre.isEmpty() ||
                apellido == null || apellido.isEmpty() ||
                sexoStr == null || sexoStr.equals("") ||
                nacionalidad == null || nacionalidad.isEmpty() ||
                correo == null || correo.isEmpty() ||
                telefono == null || telefono.isEmpty() ||
                direccion == null || direccion.isEmpty() ||
                localidad == null || localidad.isEmpty() ||
                provincia == null || provincia.isEmpty() ||
                fechaStr == null || fechaStr.isEmpty()) {

                error = "⚠️ Complete todos los campos obligatorios.<br>";
            }
                        
            if(dni != null) {if(!dni.matches("\\d{6,8}")) {error += "⚠️ El DNI debe ser numérico y tener entre 6 y 8 dígitos.<br>";}}
            if(cuil != null) {if(!cuil.matches("\\d{11}")) {error += "⚠️ El CUIL debe tener exactamente 11 dígitos numéricos.<br>";}}
            if(nombre != null) {if(!nombre.matches("^[A-Za-zÁÉÍÓÚáéíóúñÑ ]+$")) {error += "⚠️ El nombre solo puede contener letras.<br>";}}
            if(apellido != null) {if(!apellido.matches("^[A-Za-zÁÉÍÓÚáéíóúñÑ ]+$")) {error += "⚠️ El apellido solo puede contener letras.<br>";}}
            if(sexoStr != null) {if(!sexoStr.equals("M") && !sexoStr.equals("F") && !sexoStr.equals("Otro")) {error += "⚠️ Seleccione un sexo válido.<br>";}}
            if(direccion != null) {if(!direccion.matches("^[A-Za-z0-9ÁÉÍÓÚáéíóúñÑ ,.\\-]+$")) {error += "⚠️ La dirección solo puede contener letras, números y signos básicos.<br>";}}
            if(nacionalidad != null) {if (!nacionalidad.matches("^[A-Za-zÁÉÍÓÚáéíóúñÑ ]+$")) {error += "⚠️ La nacionalidad solo puede contener letras.<br>";}}
            if(localidad != null) {if(!localidad.matches("^[A-Za-zÁÉÍÓÚáéíóúñÑ ]+$")) {error += "⚠️ La localidad solo puede contener letras.<br>";}}
            if(provincia != null) {if (!provincia.matches("^[A-Za-zÁÉÍÓÚáéíóúñÑ ]+$")) {error += "⚠️ La provincia solo puede contener letras.<br>";}}
            if(correo != null) {if (!correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {error += "⚠️ Ingrese un correo electrónico válido.<br>";}}
            if(telefono != null) {if (!telefono.matches("\\d{10}")) {error += "⚠️ El teléfono debe tener exactamente 10 dígitos numéricos.<br>";}}
            if(fechaStr != null) {
            	try {
                    java.util.Date parsed = new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr);
                    fechaNacimiento = new java.sql.Date(parsed.getTime());
                    java.util.Date hoy = new java.util.Date();
                	if(fechaNacimiento.after(hoy)) {error += "<br> ⚠️ La fecha de nacimiento no puede ser futura.";}
                }catch(Exception e) { 
                    error += "⚠️ Error inesperado: " + e.getMessage();
                    e.printStackTrace();         	
                }            	
            }
            
            
            if(error.isEmpty()) {
            	ClienteDAO dao = new ClienteDAO();
                if (dao.existeCorreo(correo)) {
                    error = "⚠️ El correo ingresado ya está registrado.";
                } else {
                    Cliente c = new Cliente();
                    c.setDni(dni.trim());
                    c.setCuil(cuil.trim());
                    c.setNombre(nombre.trim());
                    c.setApellido(apellido.trim());
                    c.setSexo(sexoStr.charAt(0));
                    c.setNacionalidad(nacionalidad.trim());
                    c.setCorreo_electronico(correo.trim());
                    c.setTelefono(telefono.trim());
                    c.setDireccion(direccion.trim());
                    c.setLocalidad(localidad.trim());
                    c.setProvincia(provincia.trim());
                    c.setFecha_nacimiento(fechaNacimiento);

                    boolean insertado = dao.insertarCliente(c);

                    if (insertado) {
                        exito = "✅ Cliente registrado correctamente.";
                    } else {
                        error = "❌ Error al guardar el cliente en la base de datos.";}
                }
            }
        }catch(Exception ex) {
        	
        }
        request.setAttribute("Error", error);
        request.setAttribute("Exito", exito);
        RequestDispatcher rd = request.getRequestDispatcher("/AltaCliente.jsp");
        rd.forward(request, response);
    }
}
