/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author PCFernando
 */
public class ConnectionFactory {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/biblioteca"; //DATABASE biblioteca
    private static final String USER = "SEU_USUARIO";
    private static final String PASS = "SUA_SENHA"; 

    private static ConnectionFactory instance;

    private ConnectionFactory() { 
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Erro: ", e);
        }
    }

    public static ConnectionFactory getInstance() { //método carrega o Driver apenas uma vez e evita que toda vez que for abrir a conexão com o banco ele tenha que carregar o Driver
        if (instance == null) {
            instance = new ConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() { //Método para abrir a conexão com o banco
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }
}
