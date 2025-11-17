/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author PCFernando
 */
public class Membro {
    private int id;
    private String nome;
    private String matricula;
    private String contato;

    public Membro() {
    }

    public Membro(int id, String nome, String matricula, String contato) {//Construtor completo para métodos que utilizam o ID
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.contato = contato;
    }

    public Membro(String nome, String matricula, String contato) {//Construtor sem id para o método de inserção
        this.nome = nome;
        this.matricula = matricula;
        this.contato = contato;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    @Override
    public String toString() {
        return this.nome; 
    }
}
