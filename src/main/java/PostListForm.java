import business.PostBusiness;
import entity.PostEntity;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class PostListForm extends JFrame implements ListSelectionListener {
    private JTable tablePost;
    private JPanel rootPanel;

    private PostBusiness mPostBusiness = new PostBusiness();

    public PostListForm() {
        super("Minha Janela");

        rootPanel = new JPanel();
        this.tablePost = new JTable();
        this.getAllPost();
        this.rootPanel.add(this.tablePost);

        this.setSize(250, 250);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setContentPane(rootPanel);

        this.setEvents();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int postId;
        if(e.getValueIsAdjusting()){
            postId = Integer.parseInt(this.tablePost.getValueAt(this.tablePost.getSelectedRow(), 0).toString());
        }
    }

    private void setEvents(){
        this.tablePost.getSelectionModel().addListSelectionListener(this);
    }

    private void getAllPost() {

        String[] columnNames = {"id", "Titulo"};
        List<PostEntity> listaPost = mPostBusiness.getAllPosts();

        DefaultTableModel model = new DefaultTableModel(new Object[0][0], columnNames);

        //TODO Flag para limitar tamnho do retorno de dados para a tela
        int i = 0;

        for (PostEntity entity : listaPost) {
            Object[] o = new Object[2];
            o[0] = entity.getId();
            o[1] = entity.getTitle();

            model.addRow(o);

            i++;
            if (i > 10) {
                break;
            }
        }
        System.out.println("ColumnCountModel: >" + model.getColumnCount());
        System.out.println("ColumnCountModel: >" + model.getRowCount());
        this.tablePost.setModel(model);
    }
}
