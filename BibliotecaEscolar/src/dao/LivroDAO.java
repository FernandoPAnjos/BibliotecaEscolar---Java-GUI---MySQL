/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.ConnectionFactory;
import model.Livro;
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
public class LivroDAO {
    
    public void salvar(Livro livro) {
        String sql = "INSERT INTO livro (titulo, autor, ano, isbn) VALUES (?, ?, ?, ?)"; //SQL para inserção com os 4 campos necessários que serão substituidos pelos valores

        try (Connection conn = ConnectionFactory.getInstance().getConnection(); //Utilizo o try-with-resources para fechar a conexão automatico  
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo()); //Aqui as '?' no SQL são trocados pelo valor do usuário 
            stmt.setString(2, livro.getAutor()); 
            stmt.setInt(3, livro.getAno());
            stmt.setString(4, livro.getIsbn());

            stmt.execute(); //Executa o comando SQL

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar o livro: " + e.getMessage());
        }
    }
    
    public List<Livro> listarTodos() { //Método que lista todos os livros
        String sql = "SELECT * FROM livro";
        List<Livro> livros = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getInstance().getConnection(); //try-with-resources
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) { //Enquanto possuir próximo livro ele vai continuar rodando o while e recuperando todos os valores
                Livro livro = new Livro();
                livro.setId(rs.getInt("id"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setAno(rs.getInt("ano"));
                livro.setIsbn(rs.getString("isbn"));

                livros.add(livro);//Adiciona o objeto livro completo na lista
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar os livros: " + e.getMessage());
        }
        return livros;
    }
    
    public List<Livro> listarDisponiveis() { //Método que lista apenas os livros que não possuem empréstimos ativos 
    String sql = "SELECT * FROM livro " +
                 "WHERE id NOT IN (SELECT livro_id FROM emprestimo WHERE data_fim IS NULL)";
                 
    List<Livro> livros = new ArrayList<>();

    try (Connection conn = ConnectionFactory.getInstance().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Livro livro = new Livro();
            livro.setId(rs.getInt("id"));
            livro.setTitulo(rs.getString("titulo"));
            livro.setAutor(rs.getString("autor"));
            livro.setAno(rs.getInt("ano"));
            livro.setIsbn(rs.getString("isbn"));

            livros.add(livro);
        }

    } catch (SQLException e) {
        throw new RuntimeException("Erro ao listar livros disponíveis: " + e.getMessage());
    }
    return livros;
}
    
    public void atualizar(Livro livro) {
        String sql = "UPDATE livro SET titulo = ?, autor = ?, ano = ?, isbn = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getInstance().getConnection(); //try-with-resources
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAno());
            stmt.setString(4, livro.getIsbn());
            stmt.setInt(5, livro.getId()); // Update e Delete utilizam o ID como parâmetro para saber qual livro executar a ação

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o livro: " + e.getMessage());
        }
    }
    
    public void deletar(int id) {
        String sql = "DELETE FROM livro WHERE id = ?";

        try (Connection conn = ConnectionFactory.getInstance().getConnection(); //try-with-resources
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id); // Update e Delete utilizam o ID como parâmetro para saber qual livro executar a ação
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar o livro: " + e.getMessage());
        }
    }
}
