/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import view.TelaPrincipal;

/**
 *
 * @author PCFernando
 */
public class main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            TelaPrincipal tela = new TelaPrincipal();
            tela.setLocationRelativeTo(null); 
            tela.setVisible(true);
        });
    }
}
