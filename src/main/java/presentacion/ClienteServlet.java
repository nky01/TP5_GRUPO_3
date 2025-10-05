package presentacion;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
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
                sexoStr == null || sexoStr.equals("default") ||
                nacionalidad == null || nacionalidad.isEmpty() ||
                correo == null || correo.isEmpty() ||
                telefono == null || telefono.isEmpty() ||
                direccion == null || direccion.isEmpty() ||
                localidad == null || localidad.isEmpty() ||
                provincia == null || provincia.isEmpty() ||
                fechaStr == null || fechaStr.isEmpty()) {

                error = "⚠️ Complete todos los campos obligatorios.";
            } else {

              
                Date fechaNacimiento = null;
                try {
                    java.util.Date parsed = new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr);
                    fechaNacimiento = new Date(parsed.getTime());
                } catch (ParseException e) {
                    error = "⚠️ Fecha inválida. Use el formato correcto.";
                }

             
                if (error.isEmpty()) {
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

                 
                    ClienteDAO dao = new ClienteDAO();
                    boolean insertado = dao.insertarCliente(c);

                    if (insertado) {
                        exito = "✅ Cliente registrado correctamente.";
                    } else {
                        error = "❌ Error al guardar el cliente en la base de datos.";
                    }
                }
            }

        } catch (Exception e) {
            error = "⚠️ Error inesperado: " + e.getMessage();
            e.printStackTrace();
        }

       
        request.setAttribute("Error", error);
        request.setAttribute("Exito", exito);

        RequestDispatcher rd = request.getRequestDispatcher("/AltaCliente.jsp");
        rd.forward(request, response);
    }
}
