package org.example.panel.product;

import org.example.controladores.ProdutoController;
import org.example.tabelas.Produto;

import javax.swing.*;
import java.awt.*;

public class ProdutoUpdateForm extends JFrame {
    private ProdutoController produtoController;
    public ProdutoUpdateForm() {
        produtoController = new ProdutoController();
        setSize(500, 310);
        setTitle("Atualizar Produto");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new FlowLayout());
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 0, 3));

        JLabel idLabel = new JLabel("Id Produto");
        idLabel.setForeground(Color.decode("#6F4A8E"));
        idLabel.setHorizontalAlignment(JLabel.LEFT);
        panel.add(idLabel);

        JTextField idInput = new JTextField(20);
        idInput.setBackground(Color.decode("#EBEBEB"));
        panel.add(idInput);

        JLabel nameLabel = new JLabel("Nome");
        nameLabel.setForeground(Color.decode("#6F4A8E"));
        nameLabel.setHorizontalAlignment(JLabel.LEFT);
        panel.add(nameLabel);

        JTextField nameInput = new JTextField(20);
        nameInput.setBackground(Color.decode("#EBEBEB"));
        panel.add(nameInput);

        JLabel precoLabel = new JLabel("Preço");
        precoLabel.setForeground(Color.decode("#6F4A8E"));
        precoLabel.setHorizontalAlignment(JLabel.LEFT);
        panel.add(precoLabel);

        JTextField precoInput = new JTextField(20);
        precoInput.setBackground(Color.decode("#EBEBEB"));
        panel.add(precoInput);

        JLabel descricaoLabel = new JLabel("Id Categoria");
        descricaoLabel.setForeground(Color.decode("#6F4A8E"));
        descricaoLabel.setHorizontalAlignment(JLabel.LEFT);
        panel.add(descricaoLabel);

        JTextField descricaoInput = new JTextField(20);
        descricaoInput.setBackground(Color.decode("#EBEBEB"));
        panel.add(descricaoInput);

        JButton submitButton = new JButton("Confirmar");
        submitButton.setBackground(Color.decode("#6F4A8E"));
        submitButton.setVerticalAlignment(JLabel.CENTER);
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(e -> {
            Produto p = new Produto();
            p.setIdProduto(Integer.parseInt(idInput.getText()));
            p.setNome(nameInput.getText());
            p.setPreco(Double.parseDouble(precoInput.getText()));
            p.setIdCategoria(Integer.parseInt(descricaoInput.getText()));

            try{
                produtoController.update(p);
            } catch (Exception exc){
                System.out.println(exc.getMessage());
            }

        });

        add(panel);
        add(submitButton);
        setVisible(true);
    }
    public static void main(String[] args) {
        new ProdutoUpdateForm();
    }
}
