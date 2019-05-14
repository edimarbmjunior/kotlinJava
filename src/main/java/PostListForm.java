import javax.swing.*;

public class PostListForm extends JFrame{
    private JTable tablePost;
    private JPanel rootPanel;

    public PostListForm(){
        super("Minha Janela");
        this.setSize(480, 360);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        rootPanel = new JPanel();
        this.setContentPane(rootPanel);
    }
}
