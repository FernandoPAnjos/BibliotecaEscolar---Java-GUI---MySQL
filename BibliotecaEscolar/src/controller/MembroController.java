/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.MembroDAO;
import model.Membro;
import java.util.List;
/**
 *
 * @author PCFernando
 */
public class MembroController {
    private MembroDAO membroDAO;

    public MembroController() {
        this.membroDAO = new MembroDAO();
    }
    
    public void cadastrarMembro(String nome, String matricula, String contato) throws Exception {//Apenas verificações de campos vazios e então chama a função no DAO
        if(nome == null || nome.trim().isEmpty()){
            throw new Exception("O campo 'Nome' é obrigatório");
        }
        if(matricula == null || matricula.trim().isEmpty()){
            throw new Exception("O campo 'Matrícula' é obrigatório");
        }
        
        Membro membro = new Membro(nome, matricula, contato);
        membroDAO.salvar(membro);
    }
    
    public void alterarMembro(int id, String nome, String matricula, String contato) throws Exception { //Apenas verificações de campos vazios e então chama a função no DAO
         if(nome == null || nome.trim().isEmpty()){
            throw new Exception("O campo 'Nome' é obrigatório");
        }
         
        Membro membro = new Membro(id, nome, matricula, contato);
        membroDAO.atualizar(membro);
    }
    
    public List<Membro> listarMembros() {
        return membroDAO.listarTodos();
    }
    
    public void excluirMembro(int id) {
        membroDAO.deletar(id);
    }
}
