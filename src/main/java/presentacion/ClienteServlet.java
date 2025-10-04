package presentacion;

import java.io.IOException;

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
        
        if(request.getParameter("btnGuardarCliente")!=null) {
        	eventobtnGuardarCliente(request,response);
        }
    }
    
    public void eventobtnGuardarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String dniStr = request.getParameter("Dni");
        String cuilStr = request.getParameter("Cuil");
        String nombre = request.getParameter("Nombre");
        String apellido = request.getParameter("Apellido");

        String error = "";
        String exito = "";

        if(dniStr.isEmpty() || cuilStr.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
            error = "Debe ingresar todos los campos para poder continuar.";
        } else if(!dniStr.matches("\\d{6,8}")) {
            error = "DNI inválido. Debe tener entre 6 y 8 dígitos.";
        } else if(!cuilStr.matches("\\d{11}")) {
            error = "CUIL inválido. Debe tener 11 dígitos.";
        } else if(!nombre.matches("[a-zA-Z]+")) {
            error = "Nombre inválido. Solo se permiten letras.";
        } else if(!apellido.matches("[a-zA-Z]+")) {
            error = "Apellido inválido. Solo se permiten letras.";
        } else {
            int dni = Integer.parseInt(dniStr);
            long cuil = Long.parseLong(cuilStr);
            Cliente cliente = new Cliente(dni, cuil, nombre, apellido);

            ClienteDAO dao = new ClienteDAO();
            if(dao.insertarCliente(cliente)) {
                exito = "Genial! el cliente guardado correctamente";
            } else {
                error = "Error al guardar el cliente en la bd";
            }
        }

        request.setAttribute("error", error);
        request.setAttribute("exito", exito);

        RequestDispatcher miDispatcher = request.getRequestDispatcher("/AltaCliente.jsp");
        miDispatcher.forward(request, response);
    }

}

