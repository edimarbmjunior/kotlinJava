package ui;

import business.PostBusiness;
import entity.PostEntity;

import javax.swing.*;

public class postDetailForm extends JFrame{
    private JPanel rootPanel;
    private JLabel Titulo;
    private JLabel lblTitulo = new JLabel();
    private JLabel lblBody = new JLabel();

    private PostBusiness mPostBusiness = new PostBusiness();

    public postDetailForm(int id) {
        super("Minha Janela");

        rootPanel = new JPanel();
        this.loadPost(id);
        /*this.rootPanel.add(this.lblBody);
        this.rootPanel.add(this.lblTitulo);*/

        this.setSize(500, 250);
        this.setVisible(true);
        this.setContentPane(rootPanel);
    }

    private  void loadPost(int id){
        PostEntity entity = this.mPostBusiness.getSinglePost(id);

        this.lblTitulo.setText(entity.getTitle());
        this.lblBody.setText("<html>" + entity.getBody() + "</html>");
    }
}
