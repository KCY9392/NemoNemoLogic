package com.ung.logic.controller;

import com.ung.logic.model.service.LogicService;
import com.ung.logic.model.service.MenuImgFile;
import com.ung.logic.model.vo.Player;
import com.ung.logic.view.MainUi;
import com.ung.logic.view.PlaySite;
import com.ung.logic.view.panel.LevelPanel;
import com.ung.logic.view.panel.logicListPanel;
import com.ung.logic.view.panel.startPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuController {
    private MainUi mu;
    LevelPanel lp;
    startPanel sp;
    private logicListPanel startPage;
    private MenuImgFile mif;
    private Player p;
    private LogicService ls;

    public MenuController(MainUi mu){
        ls=new LogicService();
        mif=new MenuImgFile();
        this.mu=mu;
        //player 생성 및 초기화
        if (mu.p==null){
            String inputId;
            while(true) {
                inputId = JOptionPane.showInputDialog("ID를 입력하세요");
                if(inputId != null) {
                    break;
                }
            }
            this.p=ls.getPlayer(inputId);
        }else{
            this.p=mu.p;
        }

        startPanel sp=new startPanel();
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
                setAction(lp,sp);
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
        sp.galleryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    public void setAction(LevelPanel lp,startPanel sp){
        lp.backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lp.Panel.setVisible(false);
                //메뉴선택으로 돌아간다.
                sp.Panel.setVisible(true);
            }
        });
        lp.HardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        lp.NomalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        lp.EasyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}


