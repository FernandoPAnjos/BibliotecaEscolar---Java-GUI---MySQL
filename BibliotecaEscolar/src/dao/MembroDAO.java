/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.ConnectionFactory;
import model.Membro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author PCFernando
 */
public class MembroDAO {
    
    public void salvar(Membro membro) {
        String sql = "INSERT INTO membro (nome, matricula, contato) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, membro.getNome());
            stmt.setString(2, membro.getMatricula());
            stmt.setString(3, membro.getContato());

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar o membro: " + e.getMessage());
        }
    }

    public List<Membro> listarTodos() {
        String sql = "SELECT * FROM membro";
        List<Membro> membros = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Membro m = new Membro();
                m.setId(rs.getInt("id"));
                m.setNome(rs.getString("nome"));
                m.setMatricula(rs.getString("matricula"));
                m.setContato(rs.getString("contato"));

                membros.add(m);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar os membros: " + e.getMessage());
        }
        return membros;
    }
    
    public void atualizar(Membro membro) {
        String sql = "UPDATE membro SET nome = ?, matricula = ?, contato = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, membro.getNome());
            stmt.setString(2, membro.getMatricula());
            stmt.setString(3, membro.getContato());
            stmt.setInt(4, membro.getId());

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o membro: " + e.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM membro WHERE id = ?";

        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir o membro: " + e.getMessage());
        }
    }
      
    public Membro buscarPorId(int id) { //Método para buscar o membro pelo ID (chave única)  para realizar o empréstimo
        String sql = "SELECT * FROM membro WHERE id = ?";
        Membro m = null;

        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                m = new Membro();
                m.setId(rs.getInt("id"));
                m.setNome(rs.getString("nome"));
                m.setMatricula(rs.getString("matricula"));
                m.setContato(rs.getString("contato"));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar o membro pelo ID: " + e.getMessage());
        }
        return m;
    }
}
