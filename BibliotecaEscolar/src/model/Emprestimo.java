/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author PCFernando
 */
public class Emprestimo {
    private int id;
    private Livro livro; //Diferente das outras classes, aqui guardo o objeto livro completo para acessar qualquer campo dos livros que eu precisar
    private Membro membro; //Diferente das outras classes, aqui guardo o objeto membro completo para acessar qualquer campo dos membros que eu precisar
    private Date dataInicio;
    private Date dataFim;

    public Emprestimo() {
    }

    public Emprestimo(Livro livro, Membro membro, Date dataInicio, Date dataFim) {
        this.livro = livro;
        this.membro = membro;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }
    
    public Emprestimo(int id, Livro livro, Membro membro, Date dataInicio, Date dataFim) {
        this.id = id;
        this.livro = livro;
        this.membro = membro;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Membro getMembro() {
        return membro;
    }

    public void setMembro(Membro membro) {
        this.membro = membro;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
}
