package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Pedido;

public class PedidoDAO {

    private Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/sistema_usuarios", "root", "jesusefiel123");
    }

    public boolean cadastrar(Pedido pedido) {
        String sql = "INSERT INTO pedidos (Cliente, Produto, Quantidade, Valor) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pedido.getCliente());
            pstmt.setString(2, pedido.getProduto());
            pstmt.setInt(3, pedido.getQuantidade());
            pstmt.setBigDecimal(4, pedido.getValor());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Pedido> listarTodos() {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedidos";
        try (Connection conn = connect(); 
             PreparedStatement pst = conn.prepareStatement(sql); 
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                Pedido p = new Pedido(
                        rs.getString("Cliente"),
                        rs.getString("Produto"),
                        rs.getInt("Quantidade"),
                        rs.getBigDecimal("Valor")
                );
                p.setId(rs.getInt("id"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Pedido> buscar(String termo) {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedidos WHERE id LIKE ? OR Cliente LIKE ?";
        try (Connection conn = connect(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, "%" + termo + "%");
            pst.setString(2, "%" + termo + "%");
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Pedido p = new Pedido(
                            rs.getString("Cliente"),
                            rs.getString("Produto"),
                            rs.getInt("Quantidade"),
                            rs.getBigDecimal("Valor")
                    );
                    p.setId(rs.getInt("id"));
                    lista.add(p);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean excluir(int id) {
        String sql = "DELETE FROM pedidos WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}