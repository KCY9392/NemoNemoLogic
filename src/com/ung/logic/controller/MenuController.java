package com.ung.logic.controller;

import com.ung.logic.model.service.LogicService;
import com.ung.logic.model.service.MenuImgFile;
import com.ung.logic.model.vo.Player;
import com.ung.logic.model.vo.QuestionInfo;
import com.ung.logic.view.MainUi;
import com.ung.logic.view.PlaySite;
import com.ung.logic.view.panel.LevelPanel;
import com.ung.logic.view.panel.logicListPanel;
import com.ung.logic.view.panel.startPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.temporal.JulianFields;
import java.util.List;
import java.util.Set;

public class MenuController {
    private MainUi mu;
    LevelPanel lp;
    startPanel sp;
    private MenuImgFile mif;
    private Player p;
    private LogicService ls;

    private logicListPanel startPage;
    private List<QuestionInfo> QIlist;
    private Integer page;
    public MenuController(MainUi mu){
        ls=new LogicService();
        mif=new MenuImgFile();
        page=0;
        this.mu=mu;
        //player 생성 및 초기화
        if (mu.p==null){
            String inputId="admin";
//            while(true) {
//                inputId = JOptionPane.showInputDialog("ID를 입력하세요");
//                if(inputId != null) {
//                    break;
//                }
//            }
            this.p=ls.getPlayer(inputId);
        }else{
            this.p=mu.p;
        }
        sp=new startPanel();
        sp.titleLabel.setIcon(mif.MainLogo);
        mu.add(sp.Panel);
        setAction(sp);
    }


    //startPanel을 위한
    public void setAction(startPanel sp){
        sp.gameStartButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                sp.Panel.setVisible(false);
                if(lp==null){
                lp=new LevelPanel();
                lp.Panel.setVisible(true);
                lp.logoLabel.setIcon(mif.MenuLogo);
                lp.backButton.setIcon(mif.back);
                setAction(lp);
                mu.add(lp.Panel);
                }else{
                    lp.Panel.setVisible(true);
                }
            }
        });
        //이어하기 액션
        sp.continueButton.addActionListener(new ActionListener() {
            //이어하기 버튼 이벤트
            @Override
            public void actionPerformed(ActionEvent e) {
                if(p.getCurrentData()==null) {
                    sp.continueButton.setEnabled(false);
                }else {
                    sp.Panel.setVisible(false);
                    new PlaySite(p.getCurrentCode(), p);
                }
            }

        });

    }
    public void setAction(LevelPanel lp){
        lp.backButton.addActionListener(e -> {
            lp.Panel.setVisible(false);
            //메뉴선택으로 돌아간다.
            sp.Panel.setVisible(true);
        });
        lp.HardButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
                    makeQuestionSelect("HARD", null);
                    lp.Panel.setVisible(false);

           }
        });
        lp.NomalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeQuestionSelect("NOMAL", null);
                lp.Panel.setVisible(false);
            }
        });
        lp.EasyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeQuestionSelect("EASY", null);
                lp.Panel.setVisible(false);
            }
        });
    }

    private void makeQuestionSelect(String level,logicListPanel prevPage){
        //이미불러와저있다면 문제리스트를 안불러온다.
        if(QIlist==null){QIlist=ls.getQuestionList(level);}
        QuestionInfo[] QIArr;
        JButton[] buttonArray;
        JLabel[] nameLabel;
        JLabel[] sizeLabel;

        if(QIlist.size()<(page+1)*10){
            QIArr=new QuestionInfo[QIlist.size()-page*10];
            buttonArray=new JButton[QIlist.size()-page*10];
            nameLabel=new JLabel[QIlist.size()-page*10];
            sizeLabel=new JLabel[QIlist.size()-page*10];
        }else{
            QIArr=new QuestionInfo[10];
            buttonArray=new JButton[10];
            nameLabel=new JLabel[10];
            sizeLabel=new JLabel[10];

        }
        for (int i = 0; i < QIArr.length; i++){
            QIArr[i]=QIlist.get(page*10+i);
        }
        checkClear(QIArr);
        for (int i = 0; i < QIArr.length; i++) {
            buttonArray[i]=new JButton();
            nameLabel[i]=new JLabel(QIArr[i].getQName());
            sizeLabel[i]=new JLabel(QIArr[i].getSize());
            if(QIArr[i].isClear()){
                buttonArray[i].setIcon(mif.clearIcon);
            }else{
                buttonArray[i].setIcon(mif.unClearIcon);
            }
            Integer index=i;
            buttonArray[i].addActionListener(e -> {
                int Qcode=QIArr[index].getQCODE();
                mu.setVisible(false);
                new PlaySite(Qcode,p);
            });
        }


        logicListPanel current;

        if(startPage==null){
            startPage=new logicListPanel(null,buttonArray,nameLabel,sizeLabel);
            startPage.prevButton.setEnabled(false);
            current=startPage;
        }else{
            logicListPanel newPage=new logicListPanel(prevPage,buttonArray,nameLabel,sizeLabel);
            current=newPage;
        }
        current.panel.setVisible(true);
        mu.add(current.panel);
        current.nextButton.setIcon(mif.nextIcon);
        current.prevButton.setIcon(mif.prevIcon);
        current.BackButton.setIcon(mif.back);
        System.out.println(page);
        current.nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((page)*10>QIlist.size()){
                    current.nextButton.setEnabled(false);
                }else if(current.nextPage!=null){
                    current.nextPage.panel.setVisible(true);
                    current.panel.setVisible(false);
                    page++;
                }else{
                    page++;
                    makeQuestionSelect(level,current);
                }
            }
        });

        current.prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(current);
                current.prevPage.panel.setVisible(true);
                current.panel.setVisible(false);
                page--;
            }
        });
        current.BackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                current.panel.setVisible(false);
                sp.Panel.setVisible(true);
                QIlist=null;
                page=0;
                startPage=null;
            }
        });


    }
    private void checkClear(QuestionInfo[] QIArr){
        Set<Integer> clearSet= p.getClearList();
        for (int i = 0; i < QIArr.length; i++) {
            if(clearSet.contains(QIArr[i].getQCODE())){
                QIArr[i].setClear(true);
            }
        }
    }

}


