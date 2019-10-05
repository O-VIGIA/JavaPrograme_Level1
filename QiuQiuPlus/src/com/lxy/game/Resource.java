package com.lxy.game;

import java.awt.Color;
import java.awt.Graphics;
/**
 * �����ƶ���Դ
 * @author ½Сү
 *
 */
public class Resource extends GameObject {

	public Resource(){
		width = 8;
		height = 8;
		degree = Math.random()*Math.PI*2;
	}
	//�����ڵ�
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.BLUE);
		//�����ɫ
		g.fillOval((int)x, (int)y, width, height);
		//�ӵ��ƶ�
		x += speed*Math.cos(degree);
		y += speed*Math.sin(degree);
		//�ӵ�����
		if(x<0||x>Constant.SCREEN_WIDTH-width) {
			degree = Math.PI-degree;
		}
		if(y<28||y>Constant.SCREEN_HEIGHT-height) {
			degree = -degree;
		}
		g.setColor(c);
	}
}
