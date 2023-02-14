package org.example.panel.product;

import org.example.controladores.ProdutoController;
import org.example.tabelas.Produto;

import javax.swing.*;
import java.awt.*;

public class ProdutoForm extends JFrame {
    private ProdutoController produtoController;

    public ProdutoForm() {
        produtoController = new ProdutoController();

        setSize(500, 310);
        setTitle("Cadastrar Produto");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new FlowLayout());
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 0, 3));

        JLabel nomeLabel = new JLabel("Nome");
        nomeLabel.setForeground(Color.decode("#6F4A8E"));
        nomeLabel.setHorizontalAlignment(JLabel.LEFT);
        panel.add(nomeLabel);

        JTextField nomeInput = new JTextField(20);
        nomeInput.setBackground(Color.decode("#EBEBEB"));
        panel.add(nomeInput);

        JLabel precoLabel = new JLabel("Preço");
        precoLabel.setForeground(Color.decode("#6F4A8E"));
        precoLabel.setHorizontalAlignment(JLabel.LEFT);
        panel.add(precoLabel);

        JTextField precoInput = new JTextField(20);
        precoInput.setBackground(Color.decode("#EBEBEB"));
        panel.add(precoInput);

        JLabel categoriaLabel = new JLabel("Id Categoria");
        categoriaLabel.setForeground(Color.decode("#6F4A8E"));
        categoriaLabel.setHorizontalAlignment(JLabel.LEFT);
        panel.add(categoriaLabel);

        JTextField categoriaInput = new JTextField(20);
        categoriaInput.setBackground(Color.decode("#EBEBEB"));
        panel.add(categoriaInput);

        JButton submitButton = new JButton("Confirmar");
        submitButton.setBackground(Color.decode("#6F4A8E"));
        submitButton.setVerticalAlignment(JLabel.CENTER);
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(e -> {
            Produto p = new Produto();
            try {
                p.setNome(nomeInput.getText());
                p.setPreco(Double.parseDouble(categoriaInput.getText()));
                p.setIdCategoria(Integer.parseInt(categoriaInput.getText()));
            } catch (Exception exc1){
                JOptionPane.showMessageDialog(this, "Tipo incompatível. Por favor, verifique os dados inseridos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            try{
                ProdutoController.insert(p);
            } catch (Exception exc){
                JOptionPane.showMessageDialog(this, "Erro SQL.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        });

        add(panel);
        add(submitButton);
        setVisible(true);
    }
    public static void main(String[] args) {
        new ProdutoForm();
    }
}