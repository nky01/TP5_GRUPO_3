package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.Usuario;

public class UsuarioDAO extends DAO {

    public Usuario validarUsuario(String user, String pass) {
        Usuario u = null;
        String sql = "SELECT * FROM usuarios WHERE usuario=? AND contrasena=?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                u = new Usuario();
                u.setId_usuario(rs.getInt("id_usuario"));
                u.setUsuario(rs.getString("usuario"));
                u.setContrasena(rs.getString("contrasena"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

}