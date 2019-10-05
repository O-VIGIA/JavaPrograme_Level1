package com.lxy.game;

import java.awt.Color;
import java.awt.Graphics;
/**
 * 快速移动资源
 * @author 陆小爷
 *
 */
public class Resource extends GameObject {

	public Resource(){
		width = 8;
		height = 8;
		degree = Math.random()*Math.PI*2;
	}
	//生产炮弹
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.BLUE);
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
