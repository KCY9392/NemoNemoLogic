package com.ung.logic.view.panel;

import com.ung.logic.view.PlaySite;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;

public class startPanel {
    public JPanel Panel;
    public JLabel titleLabel;
    public JButton gameStartButton;
    public JButton galleryButton;
    public JButton continueButton;
    public startPanel(){


        Panel = new JPanel();
        Panel.setForeground(Color.DARK_GRAY);
        Panel.setVisible(true);
        Panel.setBackground(new Color(230, 255, 204));
        Panel.setBorder(new EmptyBorder(6, 6, 6, 6));


        titleLabel = new JLabel();//controller에서 아이콘을바꿔준다
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(288, 35, 272, 232);
//        setContentPane(startPanel);

        Panel.setLayout(null);



        //Game start버튼
        gameStartButton = new JButton("Game Start");
        gameStartButton.setBackground(Color.WHITE);
        gameStartButton.setForeground(Color.BLACK);
        gameStartButton.setFont(new Font("굴림", Font.BOLD, 25));
        gameStartButton.setBounds(333, 289, 192, 54);


        //이어하기 버튼
        continueButton = new JButton("이어하기");
        continueButton.setFont(new Font("굴림", Font.BOLD, 24));
        continueButton.setBackground(Color.WHITE);
        continueButton.setBounds(333, 368, 192, 54);


        //갤러리 버튼
        galleryButton = new JButton("갤러리");
        galleryButton.setFont(new Font("굴림", Font.BOLD, 24));
        galleryButton.setBackground(Color.WHITE);
        galleryButton.setBounds(333, 453, 192, 54);
        Panel.add(continueButton);
        Panel.add(galleryButton);
        Panel.add(gameStartButton);
        Panel.add(titleLabel);


    }

}
