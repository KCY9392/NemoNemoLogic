
package com.ung.logic.view;
import javax.swing.JPanel;
import javax.swing.JFrame;
import com.ung.logic.controller.MenuController;
import com.ung.logic.model.service.LogicService;
import com.ung.logic.model.vo.Player;
public class MainUi extends JFrame {
    public Player p;
    public LogicService ls;
    public JPanel startPanel;
    public JPanel LevelMenuPanel;
    public JPanel GallayPanel;

    public String inputId;
    public MainUi(Player p) {
        p=this.p;
        setBounds(100, 100, 900, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MenuController mc=new MenuController(this);
    }



}