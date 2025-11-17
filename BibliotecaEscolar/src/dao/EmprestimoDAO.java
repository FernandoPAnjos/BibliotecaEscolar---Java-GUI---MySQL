/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.ConnectionFactory;
import model.Emprestimo;
import model.Livro; //Utiliza tambem os imports de Livro e Membro para lidar com o emprestimo
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
public class EmprestimoDAO {
    
    public void salvar(Emprestimo emprestimo) {
        
        String sql = "INSERT INTO emprestimo (livro_id, membro_id, data_inicio, data_fim) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, emprestimo.getLivro().getId());
            stmt.setInt(2, emprestimo.getMembro().getId());
            
            java.sql.Date dataInicio = new java.sql.Date(emprestimo.getDataInicio().getTime());
            stmt.setDate(3, dataInicio);
            
            if (emprestimo.getDataFim() != null) { //Verificação em DataFim pois ele permite valor nulo caso o livro ainda não tenha sido devolvido
                java.sql.Date dataFim = new java.sql.Date(emprestimo.getDataFim().getTime());
                stmt.setDate(4, dataFim);
            } else {
                stmt.setDate(4, null);
            }

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao realizar empréstimo: " + e.getMessage());
        }
    }

    public List<Emprestimo> listarTodos() {
        
        List<Emprestimo> emprestimos = new ArrayList<>(); //Utilizo um SQL com join para navegar entre a table N:N entre livro e membro 
        String sql = "SELECT e.id AS e_id, e.data_inicio, e.data_fim, " +
                 "l.id AS l_id, l.titulo, " +
                 "m.id AS m_id, m.nome " +
                 "FROM emprestimo e, livro l, membro m " +
                 "WHERE e.livro_id = l.id " +
                 "AND e.membro_id = m.id";

        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Emprestimo e = new Emprestimo();
                e.setId(rs.getInt("e_id"));
                e.setDataInicio(rs.getDate("data_inicio"));
                e.setDataFim(rs.getDate("data_fim"));

                Livro l = new Livro(); //"Reconstroi" o objeto livro para poder pegar o Titulo além do ID 
                l.setId(rs.getInt("l_id"));
                l.setTitulo(rs.getString("titulo"));
                e.setLivro(l);

                
                Membro m = new Membro(); //"Reconstroi" o objeto membro para poder pegar o Nome além do ID 
                m.setId(rs.getInt("m_id"));
                m.setNome(rs.getString("nome"));
                e.setMembro(m);

                emprestimos.add(e); //Adiciona de fato o emprestimo na lista para visualização 
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar os empréstimos: " + e.getMessage());
        }
        return emprestimos;
    }

    public void encerrarEmprestimo(int idEmprestimo, java.util.Date dataDevolucao) {
        String sql = "UPDATE emprestimo SET data_fim = ? WHERE id = ?"; //Altera o valor de data_fim para não possui null mais caso o empréstimo tenha sido encerrado e o livro devolvido

        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            java.sql.Date dataSql = new java.sql.Date(dataDevolucao.getTime());
            stmt.setDate(1, dataSql);
            stmt.setInt(2, idEmprestimo);

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao encerrar o empréstimo: " + e.getMessage());
        }
    }
    
    
    public void deletar(int id) {
        String sql = "DELETE FROM emprestimo WHERE id = ?"; //Exclui o registro de algum empréstimo ja encerrado ou existente
        try (Connection conn = ConnectionFactory.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar o empréstimo: " + e.getMessage());
        }
    }
}
