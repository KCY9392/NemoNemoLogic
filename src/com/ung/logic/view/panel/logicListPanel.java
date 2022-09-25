package com.ung.logic.view.panel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class logicListPanel {
    public logicListPanel prevPage;
    public logicListPanel nextPage;
    public JPanel panel;
    public JLabel[] nameLabel;
    public JLabel[] sizeLabel;
    public JButton[] buttonArray;
    public JButton BackButton;
    public JButton prevButton;
    public JButton nextButton;
    public logicListPanel(logicListPanel prevPage,JButton[] buttonArray,JLabel[] nameLabel ,JLabel[] sizeLabel){
        //첫페이지 인경우
        if(prevPage!=null){
            this.prevPage=prevPage;
            prevPage.nextPage=this;
        }
        this.buttonArray=buttonArray;
        this.nameLabel=nameLabel;
        this.sizeLabel=sizeLabel;
        setPenel();
    }
    private void setPenel(){
        panel = new JPanel();
        panel.setBounds(0, 10, 874, 546);
        panel.setLayout(null);
        panel.setVisible(true);
        panel.setForeground(Color.DARK_GRAY);
        panel.setBackground(new Color(230, 255, 204));
        panel.setBorder(new EmptyBorder(6, 6, 6, 6));

        for (int i = 0; i <10 ; i++) {
           if(i<5){
               buttonArray[i].setBounds(40+(150*i),20,100,100);
               nameLabel[i].setBounds(40+(150*i),120,100,100);
               sizeLabel[i].setBounds(40+(150*i),150,100,100);
           }else{
               buttonArray[i].setBounds(40+(150*(i-5)),270,100,100);
               nameLabel[i].setBounds(40+(150*(i-5)),350,100,100);
               sizeLabel[i].setBounds(40+(150*(i-5)),380,100,100);
           }
            buttonArray[i].setBackground(new Color(152, 251, 152));
            nameLabel[i].setBackground(new Color(152, 251, 152));
            sizeLabel[i].setBackground(new Color(152, 251, 152));
            buttonArray[i].setForeground(Color.WHITE);
            nameLabel[i].setForeground(new Color(0,51,0));
            sizeLabel[i].setForeground(new Color(0,51,0));
            nameLabel[i].setFont(new Font("Segoe UI Black", Font.BOLD, 13));
            sizeLabel[i].setFont(new Font("Segoe UI Black", Font.BOLD, 13));

            panel.add(buttonArray[i]);
            panel.add(nameLabel[i]);
            panel.add(sizeLabel[i]);
        }

        BackButton = new JButton();
        prevButton=new JButton();
        nextButton=new JButton();
        BackButton.setBackground(new Color(152, 251, 152));
        prevButton.setBackground(new Color(152, 251, 152));
        nextButton.setBackground(new Color(152, 251, 152));
        BackButton.setBounds(760, 450, 100, 60);
        prevButton.setBounds(277, 450, 116, 80);
        nextButton.setBounds(434, 450, 116, 80);
        panel.add(BackButton);
        panel.add(prevButton);
        panel.add(nextButton);
    }


}
