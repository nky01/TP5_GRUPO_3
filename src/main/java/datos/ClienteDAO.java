package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entidades.Cliente;

public class ClienteDAO extends DAO{

	public boolean insertarCliente(Cliente c) {
        boolean insertado = false;
        String sql = "INSERT INTO clientes (dni, cuil, nombre, apellido) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, c.getDni());
            ps.setLong(2, c.getCuil());
            ps.setString(3, c.getNombre());
            ps.setString(4, c.getApellido());

            insertado = ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return insertado;
    }
}
