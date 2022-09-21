
package com.ung.logic.view;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.ung.logic.model.service.LogicService;
import com.ung.logic.model.vo.Player;

import java.awt.Color;

    public class MainUi extends JFrame {

        private Player p;
        private LogicService ls;
        private JPanel startPanel;
        public String inputId;

        public MainUi() {
            ls = new LogicService();

            //프로그램 실행 시, 처음에 아이디 값을 입력하게함
            while(true) {
                inputId = JOptionPane.showInputDialog("ID를 입력하세요");
                if(inputId != null) {
                    break;
                }
            }
            // LogicService의 getPlayer메소드에 input(사용자가입력한id)를 넘김.
            this.p = ls.getPlayer(inputId);


            System.out.println(p);

            setBounds(100, 100, 900, 600);
            setResizable(false);
            setLocationRelativeTo(null);
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //아이디 입력 후, 시작 Panel(game start, 이어하기, 갤러리) 열기
            startPanel();
        }



        public MainUi(Player p) {
            p=this.p;
            setBounds(100, 100, 900, 600);
            setResizable(false);
            setLocationRelativeTo(null);
            setVisible(true);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ls = new LogicService();
            startPanel();

        }



        /**
         * 프로그램 시작 창 -> GameStart버튼, 이어하기버튼, 갤러리버튼
         */
        public void startPanel() {


            startPanel = new JPanel();
            startPanel.setForeground(Color.DARK_GRAY);
            startPanel.setBackground(new Color(230, 255, 204));
            startPanel.setBorder(new EmptyBorder(6, 6, 6, 6));
            setContentPane(startPanel);
            startPanel.setLayout(null);
            startPanel.setVisible(true);


            //MainLogo를 label에 붙이기
            JLabel titleLabel = new JLabel();
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

            titleLabel.setBounds(288, 35, 272, 232);

            //titleLabel에 붙일 logo 이미지 경로
            ImageIcon logo = new ImageIcon("C:\\workspace\\gitgit\\NemoNemoLogic\\resources\\image\\logo\\MainLogo.png");

            //이미지 사이즈 조절
            Image img = logo.getImage();
            Image updateImg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon realLogo = new ImageIcon(updateImg);
            titleLabel.setIcon(realLogo);
            startPanel.add(titleLabel);


            //Game start버튼
            JButton gameStartbtn = new JButton("Game Start");
            gameStartbtn.setBackground(Color.WHITE);
            gameStartbtn.setForeground(Color.BLACK);
            gameStartbtn.setFont(new Font("굴림", Font.BOLD, 25));
            gameStartbtn.setBounds(333, 289, 192, 54);

            startPanel.add(gameStartbtn);

            //Game start버튼 이벤트
            gameStartbtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("게임 시작!");
                    startPanel.setVisible(false);
                    levelMenuPanel();
                }
            });


            //이어하기 버튼
            JButton continuebtn = new JButton("이어하기");
            continuebtn.setFont(new Font("굴림", Font.BOLD, 24));
            continuebtn.setBackground(Color.WHITE);
            continuebtn.setBounds(333, 368, 192, 54);
            //지난기록이없으면 버튼비활성화
            continuebtn.addActionListener(new ActionListener() {

                //이어하기 버튼 이벤트
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(p.getCurrentData()==null) {
                        continuebtn.setEnabled(false);
                    }else {
                        new PlaySite(p.getCurrentCode(), p);
                        startPanel.setVisible(false);
                    }
                }

            });

            startPanel.add(continuebtn);


            //갤러리 버튼
            JButton gallerybtn = new JButton("갤러리");
            gallerybtn.setFont(new Font("굴림", Font.BOLD, 24));
            gallerybtn.setBackground(Color.WHITE);
            gallerybtn.setBounds(333, 453, 192, 54);
            startPanel.add(gallerybtn);


        }



        public void levelMenuPanel() {

            JPanel levelMenu = new JPanel();
            levelMenu.setForeground(Color.DARK_GRAY);
            levelMenu.setBackground(new Color(230, 255, 204));
            levelMenu.setBorder(new EmptyBorder(6, 6, 6, 6));
            setContentPane(levelMenu);
            levelMenu.setLayout(null);
            levelMenu.setVisible(true);


            //easy 버튼
            JButton easybtn = new JButton("EASY");
            easybtn.setBackground(Color.WHITE);
            easybtn.setForeground(Color.BLACK);
            easybtn.setFont(new Font("Segoe UI Black", Font.BOLD, 27));

            // easybtn 이벤트
            easybtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                }
            });
            easybtn.setBounds(376, 226, 239, 94);
            levelMenu.add(easybtn);


            //normal 버튼
            JButton normalbtn = new JButton("NORMAL");
            normalbtn.setBackground(Color.WHITE);
            normalbtn.setForeground(Color.BLACK);
            normalbtn.setFont(new Font("Segoe UI Black", Font.BOLD, 27));
            normalbtn.setBounds(376, 363, 239, 94);

            // normalbtn 이벤트
            normalbtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                }
            });
            levelMenu.add(normalbtn);


            //hard 버튼
            JButton hardbtn = new JButton("HARD");
            hardbtn.setBackground(Color.WHITE);
            hardbtn.setForeground(Color.BLACK);
            hardbtn.setFont(new Font("Segoe UI Black", Font.BOLD, 27));
            hardbtn.setBounds(376, 503, 239, 94);

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








    }
