package com.ung.logic.run;

import com.ung.logic.model.vo.Player;
import com.ung.logic.view.PlaySite;

public class Run {
	public static void main(String[] args) {
		Player p=new Player("admin");
		PlaySite ps=new PlaySite(2000,p);
		
	}
}
