/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.LivroDAO;
import model.Livro;
import java.util.List;
/**
 *
 * @author PCFernando
 */
public class LivroController {
    private LivroDAO livroDAO;

    public LivroController() {
        this.livroDAO = new LivroDAO();
    }

    public void cadastrarLivro(String titulo, String autor, String anoStr, String isbn) throws Exception { //Verificações para o cadastro
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new Exception("O campo 'Título' é obrigatório");
        }
        
        if (anoStr == null || anoStr.trim().isEmpty()) {
            throw new Exception("O campo 'Ano' é obrigatório ");
        }

        int ano;
        try {
            ano = Integer.parseInt(anoStr);
        } catch (NumberFormatException e) {
            throw new Exception("O Ano deve ser um valor inteiro (Ex: 1999)");
        }

        Livro livro = new Livro(titulo, autor, ano, isbn);
        livroDAO.salvar(livro); //Se tudo passar, chama de fato a função de cadastro no DAO
    }

    public void alterarLivro(int id, String titulo, String autor, String anoStr, String isbn) throws Exception {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new Exception("O campo  'Título' é obrigatório");
        }
        
        int ano;
        try {
            ano = Integer.parseInt(anoStr);
        } catch (NumberFormatException e) {
            throw new Exception("O Ano deve ser um valor inteiro (Ex: 1999)");
        }

        Livro livro = new Livro(id, titulo, autor, ano, isbn);
        livroDAO.atualizar(livro);
    }

    public List<Livro> listarLivros() { //No caso a listagem não possui nenhum filtro porém é ele quem faz o controle para as chamadas do DAO
        return livroDAO.listarTodos();
    }

    public void excluirLivro(int id) {
        livroDAO.deletar(id);
    }
}
