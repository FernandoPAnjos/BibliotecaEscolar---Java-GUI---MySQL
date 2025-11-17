/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.EmprestimoDAO;
import dao.LivroDAO;
import dao.MembroDAO;
import model.Emprestimo;
import model.Livro;
import model.Membro;
import java.util.Date;
import java.util.List;
/**
 *
 * @author PCFernando
 */
public class EmprestimoController {
    
    private EmprestimoDAO emprestimoDAO;
    private LivroDAO livroDAO; //Aqui ele também precisa do DAO de Livro e Membro
    private MembroDAO membroDAO;

    public EmprestimoController() {
        this.emprestimoDAO = new EmprestimoDAO();
        this.livroDAO = new LivroDAO();
        this.membroDAO = new MembroDAO();
    }
    
    public void registrarEmprestimo(Livro livro, Membro membro) throws Exception {
        if(livro == null) throw new Exception("Você precisa selecionar um Livro");
        if(membro == null) throw new Exception("Você precisa selecionar um Membro");
        
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setLivro(livro);
        emprestimo.setMembro(membro);
        emprestimo.setDataInicio(new Date()); //Coloca a data de hoje
        emprestimo.setDataFim(null); //Inicializa como null
        
        emprestimoDAO.salvar(emprestimo);
    }
    
    public void devolverLivro(int idEmprestimo) {
        emprestimoDAO.encerrarEmprestimo(idEmprestimo, new Date()); //Coloca a data de devolução como a data de hoje
    }
    
    public List<Emprestimo> listarEmprestimos() {
        return emprestimoDAO.listarTodos();
    }
    
    public List<Livro> listarLivrosParaCombo() { //Método que vai listar os Livros visualmente na ComboBox
        return livroDAO.listarDisponiveis();
    }
    
    public List<Membro> listarMembrosParaCombo() { //Método que vai listar os Membros visualmente na ComboBox
        return membroDAO.listarTodos();
    }
}
