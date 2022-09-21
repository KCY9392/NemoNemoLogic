package com.ung.logic.model.service;
import java.awt.Image;
import javax.swing.ImageIcon;

public class MenuImgFile {
    public ImageIcon MainLogo;
    public ImageIcon MenuLogo;
    public ImageIcon back;

    public MenuImgFile(){
        this.MainLogo=resizeImg(200,200,new ImageIcon("resources\\image\\logo\\MainLogo.png"));
        this.back=resizeImg(100,100,new ImageIcon("resources\\image\\icon\\back.png"));
        this.MenuLogo=resizeImg(500,100,new ImageIcon("resources\\image\\icon\\levelLogo.png"));

    }
    private ImageIcon resizeImg(int width,int height,ImageIcon icon) { // image Size Setting
        Image ximg = icon.getImage(); // ImageIcon을 Image로 변환.
        Image yimg = ximg.getScaledInstance(width,height, Image.SCALE_SMOOTH);
        ImageIcon xyimg = new ImageIcon(yimg);
        return xyimg;
    }
}
