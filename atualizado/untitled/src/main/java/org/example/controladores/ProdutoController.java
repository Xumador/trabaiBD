package org.example.controladores;

import org.example.ConnectionManager;
import org.example.tabelas.Produto;

import java.sql.*;

public class ProdutoController {
    private static Connection conn = ConnectionManager.getInstance().getConnection();

    public static void getAllProdutos() throws SQLException {

        String sql = "SELECT idProduto, nome, preco FROM produto";
        try (
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
        ) {

            while (rs.next()) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(rs.getInt("idProduto") + ": ");
                stringBuffer.append(rs.getString("nome") + ", ");
                stringBuffer.append(rs.getDouble("preco"));
                System.out.println(stringBuffer.toString());
            }
        }
    }

    public static Produto getProduto(int idProduto) throws SQLException {

        String sql = "SELECT * FROM produto WHERE idProduto = ?";
        ResultSet rs = null;

        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, idProduto);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Produto bean = new Produto();
                bean.setIdProduto(idProduto);
                bean.setNome(rs.getString("nome"));
                bean.setPreco(rs.getDouble("preco"));
                return bean;
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
    }

    public static boolean insert(Produto bean) throws Exception {

        String sql = "INSERT into produto (nome, preco, idCategoria) " +
                "VALUES (?, ?, ? )";
        ResultSet keys = null;
        try (
                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ) {

            stmt.setString(1, bean.getNome());
            stmt.setDouble(2, bean.getPreco());
            stmt.setInt(3, bean.getIdCategoria());
            int affected = stmt.executeUpdate();

            if (affected == 1) {
                keys = stmt.getGeneratedKeys();
                keys.next();
                int newKey = keys.getInt(1);
                bean.setIdProduto(newKey);
            } else {
                System.err.println("No rows affected");
                return false;
            }

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            if (keys != null) {
                keys.close();
            }
        }
        return true;
    }

    public static boolean update(Produto bean) throws SQLException {
        String sql = "UPDATE produto SET nome = ?, preco = ?, idCategoria = ? " +
                "WHERE idProduto = ?";

        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, bean.getNome());
            stmt.setDouble(2, bean.getPreco());
            stmt.setInt(3, bean.getIdCategoria());
            stmt.setInt(4, bean.getIdProduto());
            int affected = stmt.executeUpdate();

            if (affected == 1) {
                return true;
            } else {
                System.err.println("No rows affected");
                return false;
            }

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }

    }

    public static boolean delete(int idProduto) throws SQLException {
        String sql = "DELETE FROM produto WHERE idProduto = ?";

        try (
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, idProduto);
            int affected = stmt.executeUpdate();

            if (affected == 1) {
                return true;
            } else {
                System.err.println("No rows affected");
                return false;
            }

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }

    }

    public static void close() throws SQLException {
        conn.close();
    }
}