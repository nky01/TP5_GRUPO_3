package presentacion;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.ClienteDAO;
import entidades.Cliente;

/**
 * Servlet implementation class ListarServlet
 */
@WebServlet("/ListarServlet")
public class ListarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ClienteDAO dao = new ClienteDAO();
            
            int pagina = 1;
            String parametroPagina = request.getParameter("pagina");
            if (parametroPagina != null) {
                try {
                    pagina = Integer.parseInt(parametroPagina);
                    if (pagina < 1) pagina = 1;
                } catch (NumberFormatException e) {
                    pagina = 1;
                }
            }
            
            int tamañoPagina = 5;
            
            String parametroCantidad = request.getParameter("cantidad");
            if (parametroCantidad != null) {
                try {
                    tamañoPagina = Integer.parseInt(parametroCantidad);
                } catch (NumberFormatException e) {
                    tamañoPagina = 5;
                }
            }
            
            ArrayList<Cliente> lista = dao.listarClientesPaginados(pagina, tamañoPagina);
            int totalRegistros = dao.contarClientes();
            
            request.setAttribute("clientes", lista);
            request.setAttribute("paginaActual", pagina);
            request.setAttribute("totalRegistros", totalRegistros);
            request.setAttribute("tamañoPagina", tamañoPagina);
            
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/ListadoDeClientes.jsp");
            rd.forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
