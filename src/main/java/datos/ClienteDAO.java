package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import entidades.Cliente;

public class ClienteDAO extends DAO {

    public boolean insertarCliente(Cliente c) {
        boolean insertado = false;

        String sql = """
            INSERT INTO clientes 
            (dni, cuil, nombre, apellido, sexo, nacionalidad, correo_electronico, telefono, direccion, localidad, provincia, fecha_nacimiento)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

          
            ps.setString(1, c.getDni());
            ps.setString(2, c.getCuil());
            ps.setString(3, c.getNombre());
            ps.setString(4, c.getApellido());
            ps.setString(5, String.valueOf(c.getSexo()));
            ps.setString(6, c.getNacionalidad());
            ps.setString(7, c.getCorreo_electronico());
            ps.setString(8, c.getTelefono());
            ps.setString(9, c.getDireccion());
            ps.setString(10, c.getLocalidad());
            ps.setString(11, c.getProvincia());
            ps.setDate(12, c.getFecha_nacimiento());

        
            insertado = ps.executeUpdate() > 0;

            if (insertado) {
                System.out.println("✅ Cliente insertado correctamente: " + c.getNombre() + " " + c.getApellido());
            } else {
                System.out.println("⚠️ No se insertó ningún cliente.");
            }

        } catch (SQLException e) {
            System.err.println("❌ Error SQL al insertar cliente: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("⚠️ Error general al insertar cliente: " + e.getMessage());
        }

        return insertado;
    }
}
