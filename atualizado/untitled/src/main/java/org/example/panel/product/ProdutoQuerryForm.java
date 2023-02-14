package org.example.panel.product;

import org.example.controladores.ProdutoController;
import org.example.controladores.QuerryController;

import javax.swing.*;
import java.awt.*;

public class ProdutoQuerryForm extends JFrame {
    private ProdutoController produtoQuerryController;
    private JTextArea areaDeTexto;
    private JTextField campoNome;
    private JTextField campoPreco;

    public ProdutoQuerryForm() {
        produtoQuerryController = new ProdutoController();

        setSize(500, 310);
        setTitle("Consultas: Produto");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setResizable(false);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        areaDeTexto = new JTextArea();
        campoNome = new JTextField(15);
        campoPreco = new JTextField(15);

        JButton botao1 = new JButton("Consultar Produtos por Nome");
        botao1.setBackground(Color.decode("#6F4A8E"));
        botao1.setForeground(Color.WHITE);
        botao1.addActionListener(e -> {
            try {
                areaDeTexto.setText(QuerryController.buscarProdutosPorNome(campoNome.getText()));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });

        JButton botao2 = new JButton("Consultar Produtos por Preço");
        botao2.setBackground(Color.decode("#6F4A8E"));
        botao2.setForeground(Color.WHITE);
        botao2.addActionListener(e -> {
            try {
                areaDeTexto.setText(QuerryController.buscarProdutosPorPreco(Double.parseDouble(campoPreco.getText())));
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });

        JButton botao3 = new JButton("Listar Todos Produtos");
        botao3.setBackground(Color.decode("#6F4A8E"));
        botao3.setForeground(Color.WHITE);
        botao3.addActionListener(e -> {
            try {
                areaDeTexto.setText(QuerryController.todosProdutos());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });

        add(campoNome);
        add(botao1);
        add(campoPreco);
        add(botao2);
        add(areaDeTexto);
        add(botao3);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ProdutoQuerryForm();
    }
}
