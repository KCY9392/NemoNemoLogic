package com.ung.logic.view;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageFile {
	public final ImageIcon fullHeart; 
	public final ImageIcon BrokenHeart;
	public final ImageIcon ResetImg;
	public final ImageIcon BackImg ;
	public final  ImageIcon HintImg; 
	public final ImageIcon GHintImg;
	public final ImageIcon LogoImg;
	public final ImageIcon PRightImg; 
	public final ImageIcon PWrongImg;
	public final ImageIcon PCheckImg;
	public final ImageIcon RightImg;
	public final ImageIcon WrongImg;
	public final ImageIcon CheckImg;
	public final ImageIcon BRightImg;
	public final ImageIcon BWrongImg;
	public final ImageIcon BCheckImg;
	private int iconSize;
	private int buttonSize;
/** 
 * @param icon : 정사각형 아이콘들의 한변의길이
 * @param button : 버튼 정사각형 한변의 길이
 */
	public ImageFile(int icon,int button) {
		this.iconSize=icon;
		this.buttonSize=button;
		this.BrokenHeart = imageSetSize(new ImageIcon("resources\\image\\icon\\brokenHeart.png"));
		this.fullHeart = imageSetSize(new ImageIcon("resources\\image\\icon\\fullHeart.png"));
		this.ResetImg = imageSetSize( new ImageIcon("resources\\image\\icon\\reset.png"));
		this.BackImg = imageSetSize( new ImageIcon("resources\\image\\icon\\back.png"));
		this.HintImg = imageSetSize(new ImageIcon("resources\\image\\icon\\Hint.png"));
		this.GHintImg= imageSetSize(new ImageIcon("resources\\image\\icon\\GHint.png"));
		this.LogoImg= imageSetSize(new ImageIcon("resources\\image\\icon\\Logo.png"));
		
		this.PRightImg = buttonSetSize(new ImageIcon("resources\\image\\icon\\buttonRight.png"));
		this.PWrongImg = buttonSetSize(new ImageIcon("resources\\image\\icon\\buttonWrong.png"));
		this.PCheckImg = buttonSetSize(new ImageIcon("resources\\image\\icon\\buttonCheck.png"));

		this.RightImg = CursorSetSize(new ImageIcon("resources\\image\\icon\\Right.png"));
		this.WrongImg = CursorSetSize(new ImageIcon("resources\\image\\icon\\Wrong.png"));
		this.CheckImg = CursorSetSize(new ImageIcon("resources\\image\\icon\\Check.png"));
		this.BRightImg = CursorSetSize(new ImageIcon("resources\\image\\icon\\BRight.png"));
		this.BWrongImg = CursorSetSize(new ImageIcon("resources\\image\\icon\\BWrong.png"));
		this.BCheckImg = CursorSetSize(new ImageIcon("resources\\image\\icon\\BCheck.png"));
	}
	private ImageIcon CursorSetSize(ImageIcon icon) { // image Size Setting
		Image ximg = icon.getImage(); // ImageIcon을 Image로 변환.
		Image yimg = ximg.getScaledInstance(50,50, Image.SCALE_SMOOTH);
		ImageIcon xyimg = new ImageIcon(yimg);
		return xyimg;
	}
	
	private ImageIcon buttonSetSize(ImageIcon icon) { // image Size Setting
		Image ximg = icon.getImage(); // ImageIcon을 Image로 변환.
		Image yimg = ximg.getScaledInstance(buttonSize-1,buttonSize-1, Image.SCALE_SMOOTH);
		ImageIcon xyimg = new ImageIcon(yimg);
		return xyimg;
	}
	
	private ImageIcon imageSetSize(ImageIcon icon) { // image Size Setting
		Image ximg = icon.getImage(); // ImageIcon을 Image로 변환.
		Image yimg = ximg.getScaledInstance(iconSize,iconSize, Image.SCALE_SMOOTH);
		ImageIcon xyimg = new ImageIcon(yimg);
		return xyimg;
	}
}
