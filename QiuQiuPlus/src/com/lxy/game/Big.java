package com.lxy.game;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 变大资源
 * @author 陆小爷
 *
 */
public class Big extends GameObject {
	
	public Big() {
		this.width = 15;
		this.height = 15;
		this.degree = Math.random()*Math.PI*2;
	}
	//生产炮弹
		public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.PINK);
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
