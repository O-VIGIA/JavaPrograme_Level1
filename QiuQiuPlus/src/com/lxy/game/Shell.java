package com.lxy.game;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 炮弹类
 * @author 陆小爷
 *
 */
public class Shell extends GameObject {
	
	public Shell(){
		width = 10;
		height = 10;
		degree = Math.random()*Math.PI*2;
	}
	//生产炮弹
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.ORANGE);
		//填充颜色
		g.fillOval((int)x, (int)y, width, height);
		//子弹移动
		x += speed*Math.cos(degree);
		y += speed*Math.sin(degree);
		//子弹反弹
		if(x<0||x>Constant.SCREEN_WIDTH-width) {
			degree = Math.PI-degree;
		}
		if(y<28||y>Constant.SCREEN_HEIGHT-height) {
			degree = -degree;
		}
		g.setColor(c);
	}
}
