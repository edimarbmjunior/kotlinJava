package ui;

import javax.swing.*;

public class postDetailForm extends JFrame{
    private JPanel rootPanel;
    private JLabel Titulo;
    private JLabel lblTitulo;
    private JLabel lblBody;

    public postDetailForm(int id) {
        super("Minha Janela");

        rootPanel = new JPanel();

        this.setSize(500, 250);
        this.setVisible(true);
        this.setContentPane(rootPanel);
    }
}
