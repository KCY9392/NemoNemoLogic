package com.ung.logic.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import com.ung.logic.controller.PlaySiteController;
import com.ung.logic.model.service.PlayImageFile;
import com.ung.logic.model.vo.Player;
import com.ung.logic.model.vo.Question;

public class PlaySite extends JFrame {
    private PlaySiteController pc;
    private Question Q;
    private Character cursorType;
    private Integer life;
    private Integer hint;
    private Integer blank;
    private int QCode;
    private Player p;
    private char[][] current;
    private int size[];
    private PlayImageFile Img;
    private JLabel[] Heart;
    private Dimension buttonDimension;
    private Dimension iconDimension;
    private final int buttonSize;
    private final int imgSize;
    private int FrameHeight;
    private int cursorHeight;
    private int FrameWidth;
    private JButton[][] JBArr;

    public PlaySite(int QCode, Player p) {
        this.QCode = QCode;
        this.p = p;
        pc = new PlaySiteController(QCode);
        life = 3;
        cursorType = 'O';
        hint = 3;
        blank = pc.getBlank();
        FrameHeight = pc.getFrameHeight();
        FrameWidth = pc.getFrameWidht();
        cursorHeight = pc.getCursorHeight();
        buttonSize = pc.getButtonSize();
        imgSize = pc.getImgSize();
        Img = new PlayImageFile(buttonSize, imgSize);
        buttonDimension = new Dimension(buttonSize, buttonSize);
        iconDimension = new Dimension(imgSize, imgSize);
        setSize(FrameWidth + 20, FrameHeight);
        getContentPane().setBounds(0, 0, FrameWidth + 10, FrameHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		addWindowListener(new WindowListener() {
//		//진행상황이 있는경우를 위한 경우	
//		
//			@Override
//			public void windowClosed(WindowEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			@Override
//			public void windowActivated(WindowEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			@Override
//			public void windowOpened(WindowEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			@Override
//	        public void windowClosing(WindowEvent e) {
//	            if (JOptionPane.showConfirmDialog(, "Are you sure you want to quit?", "Confirm exit.", JOptionPane.OK_OPTION, 0, new ImageIcon("")) != 0) {
//	                return;
//	            }
//	            System.exit(-1);
//	        }
//			@Override
//			public void windowIconified(WindowEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			@Override
//			public void windowDeiconified(WindowEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//			@Override
//			public void windowDeactivated(WindowEvent e) {
//				// TODO Auto-generated method stub
//				
//			}
//		});
        if (p.getCurrentData() == null) {
            this.JBArr = makeJButtonArray();
        } else {
            p.setCurrentData(current);
            this.JBArr = makePlayedJButtonArray();
        }
        PlayPanel();


        setResizable(false);
        getContentPane().setLayout(null);

        setVisible(true);
        getContentPane().setBackground(new Color(152, 251, 152));
        getContentPane().setForeground(new Color(144, 238, 144));
        topPanel();
        cursorPanel();

    }

    public void cursorPanel() {
        JPanel bottom = new JPanel();
        bottom.setBounds(0, FrameHeight - 100, FrameWidth, 70);
        Dimension cursorDimension = new Dimension(60, 60);
        bottom.setBackground(new Color(152, 251, 152));
        getContentPane().add(bottom, BorderLayout.SOUTH);

        JButton Right = new JButton("");
        Right.setBackground(new Color(152, 251, 152));
        Right.setForeground(new Color(144, 238, 144));
        Right.setSize(cursorDimension);

        JButton Wrong = new JButton("");
        Wrong.setBackground(new Color(152, 251, 152));
        Wrong.setForeground(new Color(152, 251, 152));
        Wrong.setSize(cursorDimension);
        JButton Check = new JButton("");
        Check.setBackground(new Color(152, 251, 152));
        Check.setForeground(new Color(152, 251, 152));
        Check.setSize(cursorDimension);

        Right.setIcon(Img.BRightImg);
        Right.setBorderPainted(false);
        Wrong.setIcon(Img.WrongImg);
        Wrong.setBorderPainted(false);
        Check.setIcon(Img.CheckImg);
        Check.setBorderPainted(false);
        Wrong.setMargin(new Insets(0, 0, 0, 0));
        Check.setMargin(new Insets(0, 0, 0, 0));
        Right.setMargin(new Insets(0, 0, 0, 0));
        // 버튼색을 바꿔주고 커서의 속성을 바꿔준다.
        Right.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cursorType == 'X') {
                    Wrong.setIcon(Img.WrongImg);
                } else if (cursorType == '?') {
                    Check.setIcon(Img.CheckImg);
                }
                Right.setIcon(Img.BRightImg);
                cursorType = 'O';
            }
        });
        Wrong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cursorType == 'O') {
                    Right.setIcon(Img.RightImg);
                } else if (cursorType == '?') {
                    Check.setIcon(Img.CheckImg);
                }
                Wrong.setIcon(Img.BWrongImg);
                cursorType = 'X';
            }
        });
        Check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cursorType == 'O') {
                    Right.setIcon(Img.RightImg);
                } else if (cursorType == 'X') {
                    Wrong.setIcon(Img.WrongImg);
                }
                Check.setIcon(Img.BCheckImg);
                cursorType = '?';
            }
        });

        bottom.add(Right);
        bottom.add(Wrong);
        bottom.add(Check);

    }

    public void topPanel() {
        JPanel Top = new JPanel();
        Top.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));
        Top.setBounds(0, 0, FrameWidth, pc.getTopheight());

        Top.setBackground(new Color(152, 251, 152));
        getContentPane().add(Top);
        this.Heart = new JLabel[3];
        for (int i = 0; i < Heart.length; i++) {
            Heart[i] = new JLabel("");
            Heart[i].setIcon(Img.fullHeart);
            Top.add(Heart[i]);
        }
        JButton Back = new JButton("");
        Back.setBackground(new Color(152, 251, 152));
        Back.setIcon(Img.BackImg);
        Back.setSize(iconDimension);
        Back.setBorderPainted(false);
        Top.add(Back);
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        JButton Reset = new JButton("");
        Reset.setForeground(new Color(152, 251, 152));
        Reset.setBackground(new Color(152, 251, 152));
        Reset.setIcon(Img.ResetImg);
        Reset.setSize(iconDimension);
        Reset.setBorderPainted(false);
        Reset.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                p.setCurrentData(null);
                p.setCurrentCode(0);
                PlaySite ps = new PlaySite(QCode, p);
            }
        });

        JButton Hint = new JButton("3");
        Hint.setForeground(new Color(152, 251, 152));
        Hint.setBackground(new Color(152, 251, 152));
        Hint.setIcon(Img.HintImg);
        Hint.setBorderPainted(false);
        Hint.setSize(iconDimension);
        Hint.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JButton b = (JButton) e.getSource();
                hint--;
                int[] point = pc.getHint(current);
                int x = point[0];
                int y = point[1];
                JBArr[x][y].setIcon(Img.PRightImg);
                current[x][y] = 'O';
                JBArr[x][y].setEnabled(false);
                blank--;
                if (hint < 1) {
                    Hint.setIcon(Img.GHintImg);
                    Hint.setEnabled(false);
                }
                b.setText((hint) + "");
                if (blank == 0) {
                    pc.clearGame(p, QCode);
                    JOptionPane.showMessageDialog(null, "메인메뉴로돌갑니다.", "성공", JOptionPane.PLAIN_MESSAGE);
                    new MainUi(p);
                    setVisible(false);
                }
            }
        });
        JLabel Logo = new JLabel("");
        Logo.setIcon(Img.LogoImg);
        Logo.setSize(iconDimension);
        Top.add(Reset);
        Top.add(Hint);
        Top.add(Logo);
    }

    private void PlayPanel() {
        JPanel Play = new JPanel();
        getContentPane().add(Play);
        Play.setBounds(5, pc.getTopheight(), FrameWidth - 5, FrameHeight - pc.getTopheight() - 90);
        Play.setLayout(null);
        int[][] Answer = pc.getAnswer();
        int[][] row = pc.getRow();
        int[][] column = pc.getColumn();
        // 가로 최대개수 세로 최대개수 생성
        int rowMax = 0;
        int columnMax = 0;
        for (int i = 0; i < row.length; i++) {
            if (row[i].length > rowMax) {
                rowMax = row[i].length;
            }
        }
        for (int i = 0; i < row.length; i++) {
            if (column[i].length > columnMax) {
                columnMax = column[i].length;
            }
        }
        JPanel Blank = new JPanel();
        Blank.setBounds(0, 0, rowMax * buttonSize, columnMax * buttonSize);
        JLabel jb = new JLabel("");
        jb.setSize(new Dimension(rowMax * buttonSize, columnMax * buttonSize));
        jb.setBackground(Color.gray);
        Blank.add(jb);
        JPanel rowHint = new JPanel();
        rowHint.setBounds(columnMax * buttonSize, 0, row.length * buttonSize, rowMax * buttonSize);
        rowHint.setLayout(new GridLayout(rowMax, row.length));
        JButton[][] rowButton = new JButton[rowMax][row.length];
        System.out.println(rowMax);
        System.out.println(row.length);
        for (int i = 0; i < rowMax; i++) {
            for (int j = 0; j < row.length; j++) {
                if (rowMax - i > row[j].length) {
                    rowButton[i][j] = new JButton("");
                } else {

                    rowButton[i][j] = new JButton(row[j][rowMax - 1 - i] + "");

                }
                rowButton[i][j].setBackground(new Color(255, 255, 204));
                rowButton[i][j].setPreferredSize(buttonDimension);
                rowButton[i][j].setBorderPainted(false);
                rowButton[i][j].setMargin(new Insets(0, 0, 0, 0));
                rowButton[i][j].setEnabled(false);
                rowButton[i][j].setBackground(new Color(255, 255, 204));
                rowHint.add(rowButton[i][j]);
            }
        }

        JPanel columnHint = new JPanel();
        columnHint.setBounds(0, rowMax * buttonSize, columnMax * buttonSize, column.length * buttonSize);
        columnHint.setLayout(new GridLayout(column.length, columnMax));
        JButton[][] columnButton = new JButton[column.length][columnMax];

        for (int i = 0; i < column.length; i++) {
            for (int j = 0; j < columnMax; j++) {
                if (columnMax - j > column[i].length) {
                    columnButton[i][j] = new JButton(" ");
                } else {
                    columnButton[i][j] = new JButton(column[i][columnMax - j - 1] + "");
                }
                columnButton[i][j].setBackground(new Color(255, 255, 204));
                columnButton[i][j].setBorderPainted(false);
                columnButton[i][j].setMargin(new Insets(0, 0, 0, 0));
                columnButton[i][j].setEnabled(false);
                columnHint.add(columnButton[i][j]);
            }
        }

        JPanel center = new JPanel();
        center.setBounds(columnMax * buttonSize, rowMax * buttonSize, row.length * buttonSize,
                column.length * buttonSize);
        center.setLayout(new GridLayout(row.length, column.length));
        for (int i = 0; i < JBArr.length; i++) {
            for (int j = 0; j < JBArr[i].length; j++) {
                center.add(JBArr[i][j]);
            }
        }

        Play.add(Blank);
        Play.add(rowHint);
        Play.add(columnHint);
        Play.add(center);
    }

    private void PlayedPlayPanel() {

    }

    // 새게임을위한 게임판
    private JButton[][] makeJButtonArray() {
        int[][] Answer = pc.getAnswer();
        current = new char[Answer.length][Answer[0].length];
        p.setCurrentData(current);
        p.setCurrentCode(QCode);
        Border border = BorderFactory.createLineBorder(Color.black);
        JButton[][] JBArr = new JButton[Answer.length][Answer[0].length];
        for (int i = 0; i < JBArr.length; i++) {
            for (int j = 0; j < JBArr.length; j++) {
                JBArr[i][j] = new JButton();
                JBArr[i][j].setPreferredSize(buttonDimension);
                JBArr[i][j].setBackground(Color.white);
                JBArr[i][j].setBorderPainted(true);
                JBArr[i][j].setBorder(border);
                JBArr[i][j].setMargin(new Insets(0, 0, 0, 0));
                Integer x = i;
                Integer y = j;
                JBArr[i][j].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton b = (JButton) e.getSource();
                        if (cursorType == 'O') {
                            System.out.println("is O");
                            if (pc.checkRight(x, y)) {
                                b.setIcon(Img.PRightImg);
                                current[x][y] = 'O';
                                b.setEnabled(false);
                                blank--;
                            } else {
                                b.setIcon(Img.PWrongImg);

                                current[x][y] = 'X';
                                b.setEnabled(false);
                                life--;
                                Heart[life].setIcon(Img.BrokenHeart);
                            }
                            ;

                        } else if (cursorType == 'X') {
                            System.out.println("is X");
                            if (pc.checkWrong(x, y)) {
                                b.setIcon(Img.PWrongImg);
                                ;
                                current[x][y] = 'X';
                                b.setEnabled(false);
                            } else {
                                b.setIcon(Img.PRightImg);
                                current[x][y] = 'O';
                                b.setEnabled(false);
                                life--;
                                Heart[life].setIcon(Img.BrokenHeart);
                                blank--;
                            }
                            ;
                        } else if (cursorType == '?') {
                            if (current[x][y] != '?') {
                                b.setIcon(Img.PCheckImg);
                                current[x][y] = '?';
                            } else {
                                b.setIcon(null);
                                current[x][y] = '\0';
                            }
                        }
                        if (life == 0) {
                            int result = JOptionPane.showConfirmDialog(null, "다시하시겠습니까?.", "Game Over",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
                            // 다시하기 메세지 보내고싶음
                            setVisible(false);
                            if (result == 0) {

                                p.setCurrentData(null);
                                p.setCurrentCode(0);
                                PlaySite ps = new PlaySite(QCode, p);
                            } else {
                                new MainUi(p);
                            }
                        }
                        if (blank == 0) {
                            JOptionPane.showMessageDialog(null, "메인메뉴로돌갑니다.", "성공", JOptionPane.PLAIN_MESSAGE);
                            setVisible(false);
                            pc.clearGame(p, QCode);
                            new MainUi(p);
                        }
                    }
                });
            }
        }
        return JBArr;
    }

    // 이어하기를 위한 게임판
    private JButton[][] makePlayedJButtonArray() {
        int[][] Answer = pc.getAnswer();
        Border border = BorderFactory.createLineBorder(Color.black);
        JButton[][] JBArr = new JButton[Answer.length][Answer[0].length];
        for (int i = 0; i < JBArr.length; i++) {
            for (int j = 0; j < JBArr.length; j++) {
                JBArr[i][j] = new JButton();
                JBArr[i][j].setPreferredSize(buttonDimension);
                JBArr[i][j].setBackground(Color.white);
                JBArr[i][j].setBorderPainted(true);
                JBArr[i][j].setBorder(border);
                JBArr[i][j].setMargin(new Insets(0, 0, 0, 0));
                Integer x = i;
                Integer y = j;
                JBArr[i][j].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JButton b = (JButton) e.getSource();
                        if (cursorType == 'O') {
                            System.out.println("is O");
                            if (pc.checkRight(x, y)) {
                                b.setIcon(Img.PRightImg);
                                current[x][y] = 'O';
                                b.setEnabled(false);
                                blank--;
                            } else {
                                b.setIcon(Img.PWrongImg);

                                current[x][y] = 'X';
                                b.setEnabled(false);
                                life--;
                                Heart[life].setIcon(Img.BrokenHeart);
                            }
                            ;

                        } else if (cursorType == 'X') {
                            System.out.println("is X");
                            if (pc.checkWrong(x, y)) {
                                b.setIcon(Img.PWrongImg);
                                ;
                                current[x][y] = 'X';
                                b.setEnabled(false);
                            } else {
                                b.setIcon(Img.PRightImg);
                                current[x][y] = 'O';
                                b.setEnabled(false);
                                life--;
                                Heart[life].setIcon(Img.BrokenHeart);
                                blank--;
                            }
                            ;
                        } else if (cursorType == '?') {
                            if (current[x][y] != '?') {
                                b.setIcon(Img.PCheckImg);
                                current[x][y] = '?';
                            } else {
                                b.setIcon(null);
                                current[x][y] = '\0';
                            }
                        }
                        if (life == 0) {
                            int result = JOptionPane.showConfirmDialog(null, "다시하시겠습니까?.", "Game Over",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
                            // 다시하기 메세지 보내고싶음
                            setVisible(false);
                            if (result == 0) {
                                PlaySite ps = new PlaySite(QCode, p);
                            } else {
                                new MainUi(p);
                            }
                        }
                        if (blank == 0) {
                            JOptionPane.showMessageDialog(null, "메인메뉴로돌갑니다.", "성공", JOptionPane.PLAIN_MESSAGE);
                            setVisible(false);
                            pc.clearGame(p, QCode);
                            new MainUi(p);
                        }
                    }
                });
            }
        }
        for (int i = 0; i < JBArr.length; i++) {
            for (int j = 0; j < JBArr[0].length; j++) {
                if (current[i][j] != '\0') {
                    if (current[i][j] != '?') {
                        JBArr[i][j].setEnabled(false);
                        if (current[i][j] == 'O') {
                            JBArr[i][j].setIcon(Img.PRightImg);
                        }
                    } else if (current[i][j] == 'X') {
                        JBArr[i][j].setIcon(Img.PWrongImg);
                    } else {
                        JBArr[i][j].setIcon(Img.PCheckImg);
                    }
                }
            }
        }
        return JBArr;
    }


}