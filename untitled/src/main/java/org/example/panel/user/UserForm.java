package org.example.panel.user;

import org.example.controladores.UserController;
import org.example.tabelas.User;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class UserForm extends JFrame{
    private UserController userController;
    public UserForm() {
        userController = new UserController();

        setSize(500, 310);
        setTitle("Cadastrar User");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLayout(new FlowLayout());
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 0, 3));



        JLabel nameLabel = new JLabel("Nome");
        nameLabel.setForeground(Color.decode("#6F4A8E"));
        nameLabel.setHorizontalAlignment(JLabel.LEFT);
        panel.add(nameLabel);

        JTextField nameInput = new JTextField(20);
        nameInput.setBackground(Color.decode("#EBEBEB"));
        panel.add(nameInput);

        JLabel cpfLabel = new JLabel("CPF");
        cpfLabel.setForeground(Color.decode("#6F4A8E"));
        cpfLabel.setHorizontalAlignment(JLabel.LEFT);
        panel.add(cpfLabel);

        JTextField cpfInput = new JTextField(20);
        cpfInput.setBackground(Color.decode("#EBEBEB"));
        panel.add(cpfInput);

        JLabel dataNascLabel = new JLabel("Data Nascimento");
        dataNascLabel.setForeground(Color.decode("#6F4A8E"));
        dataNascLabel.setHorizontalAlignment(JLabel.LEFT);
        panel.add(dataNascLabel);


        JTextField dataNascInput = new JTextField(20);
        dataNascInput.setBackground(Color.decode("#EBEBEB"));
        panel.add(dataNascInput);

        JLabel enderecoLabel = new JLabel("Endereço");
        enderecoLabel.setForeground(Color.decode("#6F4A8E"));
        enderecoLabel.setHorizontalAlignment(JLabel.LEFT);
        panel.add(enderecoLabel);

        JTextField enderecoInput = new JTextField(20);
        enderecoInput.setBackground(Color.decode("#EBEBEB"));
        panel.add(enderecoInput);

        JLabel sexoLabel = new JLabel("sexo");
        sexoLabel.setForeground(Color.decode("#6F4A8E"));
        sexoLabel.setHorizontalAlignment(JLabel.LEFT);
        panel.add(sexoLabel);

        JTextField sexoInput = new JTextField(20);
        sexoInput.setBackground(Color.decode("#EBEBEB"));
        panel.add(sexoInput);

        JButton submitButton = new JButton("Confirmar");
        submitButton.setBackground(Color.decode("#6F4A8E"));
        submitButton.setVerticalAlignment(JLabel.CENTER);
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(e -> {
            User u = new User();
            try {
                u.setNome(nameInput.getText());
                u.setCpf(cpfInput.getText());
                u.setEndereco(enderecoInput.getText());
                u.setSexo(sexoInput.getText());
                u.setDataNasc(Date.valueOf(dataNascInput.getText()).toLocalDate());
            } catch (Exception exc1){
                JOptionPane.showMessageDialog(this, "Tipo incompatível. Por favor, verifique os dados inseridos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            try{
                userController.insert(u);
            } catch (Exception exc){
                JOptionPane.showMessageDialog(this, "Erro SQL.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        });

        add(panel);
        add(submitButton);
        setVisible(true);
    }
    public static void main(String[] args) {
        new UserForm();
    }
}
