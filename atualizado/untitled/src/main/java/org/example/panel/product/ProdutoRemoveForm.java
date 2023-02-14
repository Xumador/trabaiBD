package org.example.panel.product;

import org.example.controladores.ProdutoController;

import javax.swing.*;
import java.awt.*;

public class ProdutoRemoveForm extends JFrame {
    private ProdutoController produtoController;

    public ProdutoRemoveForm() {
        produtoController = new ProdutoController();
        setSize(500, 310);
        setTitle("Remover Produto");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new FlowLayout());
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2, 0, 3));

        JLabel idLabel = new JLabel("Id Produto");
        idLabel.setForeground(Color.decode("#6F4A8E"));
        idLabel.setHorizontalAlignment(JLabel.LEFT);
        panel.add(idLabel);

        JTextField idInput = new JTextField(20);
        idInput.setBackground(Color.decode("#EBEBEB"));
        panel.add(idInput);

        JButton submitButton = new JButton("Confirmar");
        submitButton.setBackground(Color.decode("#6F4A8E"));
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(e -> {
            try {
                produtoController.delete(Integer.parseInt(idInput.getText()));
            } catch (Exception exc) {
                System.out.println(exc.getMessage());
            }

        });

        add(panel);
        add(submitButton);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ProdutoRemoveForm();
    }
}