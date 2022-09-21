package com.ung.logic.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.ung.logic.model.service.LogicService;
import com.ung.logic.model.vo.Player;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;

public class LevelMenu extends JPanel{

    JPanel levelMenu;
    private Player p;
    private LogicService ls;
    private MainUi mu;

    public LevelMenu(){
        levelMenu = new JPanel();
        levelMenu.setForeground(Color.DARK_GRAY);
        levelMenu.setBackground(new Color(230, 255, 204));
        levelMenu.setBorder(new EmptyBorder(6, 6, 6, 6));
        mu.setContentPane(levelMenu);
        levelMenu.setLayout(null);
        

        //easy 버튼
        JButton easybtn = new JButton("EASY");
        easybtn.setFont(new Font("Segoe UI Black", Font.BOLD, 27));

        // easybtn 이벤트
        easybtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        easybtn.setBounds(331, 181, 239, 94);
        levelMenu.add(easybtn);


        //normal 버튼
        JButton normalbtn = new JButton("NORMAL");
        normalbtn.setFont(new Font("Segoe UI Black", Font.BOLD, 27));
        normalbtn.setBounds(331, 320, 239, 94);

        // normalbtn 이벤트
        normalbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        levelMenu.add(normalbtn);


        //hard 버튼
        JButton hardbtn = new JButton("HARD");
        hardbtn.setFont(new Font("Segoe UI Black", Font.BOLD, 27));
        hardbtn.setBounds(331, 457, 239, 94);

        // hardbtn 이벤트
        hardbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        levelMenu.add(hardbtn);



        // 로고 이미지
        JLabel levellogolabel = new JLabel();
        levellogolabel.setBounds(146, 20, 580, 139);
        levellogolabel.setHorizontalAlignment(SwingConstants.CENTER);

        ImageIcon logo = new ImageIcon("C:\\workspace\\gitgit\\NemoNemoLogic\\resources\\image\\logo\\LevelLogo.png");

        Image img = logo.getImage();
        Image updateImg = img.getScaledInstance(500, 100, Image.SCALE_SMOOTH);
        ImageIcon realLogo = new ImageIcon(updateImg);

        levellogolabel.setIcon(realLogo);
        levelMenu.add(levellogolabel);
    }

    public JPanel getlevelMenuPanel() {
        return this;

    }
}