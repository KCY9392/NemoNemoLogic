package com.ung.logic.model.service;
import java.awt.Image;
import javax.swing.ImageIcon;

public class MenuImgFile {
    public ImageIcon MainLogo;
    public ImageIcon MenuLogo;
    public ImageIcon back;
    public ImageIcon clearIcon;
    public ImageIcon unClearIcon;
    public ImageIcon nextIcon;
    public ImageIcon prevIcon;

    public MenuImgFile(){
        this.MainLogo=resizeImg(200,200,new ImageIcon("resources\\image\\icon\\MainLogo.png"));
        this.back=resizeImg(100,100,new ImageIcon("resources\\image\\icon\\back.png"));
        this.MenuLogo=resizeImg(500,100,new ImageIcon("resources\\image\\icon\\levelLogo.png"));
        this.unClearIcon=resizeImg(100,100,new ImageIcon("resources\\image\\icon\\unClear.png"));
        this.clearIcon=resizeImg(100,100,new ImageIcon("resources\\image\\icon\\clear.png"));
        this.nextIcon=resizeImg(100,100,new ImageIcon("resources\\image\\icon\\next.png"));
        this.prevIcon=resizeImg(100,100,new ImageIcon("resources\\image\\icon\\prev.png"));

    }
    private static ImageIcon resizeImg(int width,int height,ImageIcon icon) { // image Size Setting
        Image ximg = icon.getImage(); // ImageIcon을 Image로 변환.
        Image yimg = ximg.getScaledInstance(width,height, Image.SCALE_SMOOTH);
        ImageIcon xyimg = new ImageIcon(yimg);
        return xyimg;
    }
}
