package com.ung.logic.view.panel;


import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;

public class LevelPanel {
    public JPanel Panel;
    public JButton HardButton;
    public JButton NomalButton;
    public JButton EasyButton;
    public JButton backButton;
    public JLabel logoLabel;
    public LevelPanel() {
        this.Panel = new JPanel();
        Panel.setForeground(Color.DARK_GRAY);
        Panel.setBackground(new Color(230, 255, 204));
        Panel.setBorder(new EmptyBorder(6, 6, 6, 6));
        Panel.setLayout(null);
        Panel.setVisible(true);


        //easy 버튼
        EasyButton = new JButton("EASY");
        EasyButton.setBackground(Color.WHITE);
        EasyButton.setForeground(Color.BLACK);
        EasyButton.setFont(new Font("Segoe UI Black", Font.BOLD, 27));
        EasyButton.setBounds(315, 210, 239, 94);


        //normal 버튼
        NomalButton = new JButton("NORMAL");
        NomalButton.setBackground(Color.WHITE);
        NomalButton.setForeground(Color.BLACK);
        NomalButton.setFont(new Font("Segoe UI Black", Font.BOLD, 27));
        NomalButton.setBounds(315, 320, 239, 94);





        //hard 버튼
        HardButton = new JButton("HARD");
        HardButton.setBackground(Color.WHITE);
        HardButton.setForeground(Color.BLACK);
        HardButton.setFont(new Font("Segoe UI Black", Font.BOLD, 27));
        HardButton.setBounds(315, 439, 239, 94);





        //되돌아가기 버튼
        backButton = new JButton();
        backButton.setBounds(734, 438, 125, 99);
        backButton.setBorderPainted(false);
        backButton.setBackground(Color.WHITE);



        // 로고 이미지
        logoLabel = new JLabel();
        logoLabel.setBounds(146, 20, 580, 139);
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);

        Panel.add(logoLabel);
        Panel.add(EasyButton);
        Panel.add(backButton);
        Panel.add(HardButton);
        Panel.add(NomalButton);
    }




}
