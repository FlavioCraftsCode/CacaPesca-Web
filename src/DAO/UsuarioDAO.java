package DAO;

import java.sql.*;
import model.Usuario;

public class UsuarioDAO {
    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_usuarios", "root", "jesusefiel123");
    }

    public Usuario autenticar(String email, String senha, String tipo) {
        String query = "SELECT * FROM login WHERE email=? and password=? and tipo_usuario=?";
        try (Connection con = connect(); 
             PreparedStatement pst = con.prepareStatement(query)) {
            
            pst.setString(1, email);
            pst.setString(2, senha);
            pst.setString(3, tipo);
            
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(rs.getString("email"), null, rs.getString("tipo_usuario"));
                }
            }
        } catch (SQLException ex) {
            System.err.println("Erro na autenticação: " + ex.getMessage());
        }
        return null;
    }
}
