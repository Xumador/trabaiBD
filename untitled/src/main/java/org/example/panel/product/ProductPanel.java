package org.example.panel.product;

import javax.swing.*;
import java.awt.*;

public class ProductPanel extends JFrame {
    public ProductPanel(){
        setSize(500, 310);
        setTitle("Products");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new FlowLayout());
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 4, 0));

        JButton cadastro = new JButton("Cadastrar");
        cadastro.setBackground(Color.decode("#6F4A8E"));
        cadastro.setForeground(Color.WHITE);
        cadastro.addActionListener(e -> {
            new ProdutoForm();
        });
        add(panel);
        add(cadastro);
        setVisible(true);

        JButton atualizar = new JButton("Atualizar");
        atualizar.setBackground(Color.decode("#6F4A8E"));
        atualizar.setForeground(Color.WHITE);
        atualizar.addActionListener(e -> {
            new ProdutoUpdateForm();
        });
        add(panel);
        add(atualizar);
        setVisible(true);

        JButton delete = new JButton("Deletar");
        delete.setBackground(Color.decode("#6F4A8E"));
        delete.setForeground(Color.WHITE);
        delete.addActionListener(e -> {
            new ProdutoRemoveForm();
        });
        add(panel);
        add(delete);
        setVisible(true);

        JButton consulta = new JButton("Consultar");
        consulta.setBackground(Color.decode("#6F4A8E"));
        consulta.setForeground(Color.WHITE);
        consulta.addActionListener(e -> {
            new ProdutoQuerryForm();
        });
        add(panel);
        add(consulta);
        setVisible(true);
    }

    public static void main(String[] args) {
        new ProductPanel();
    }
}
