package com.lxy.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 坦克类:碰壁按键即反
 * @author 陆小爷
 *
 */
public class Tank extends GameObject {
	
	//定义方向
	int dir_x = 1;
	int dir_y = 1;
	//定义坦克生命
	boolean live = true;
	//定义坦克方向
	boolean left, up, right, down;
	//重写画方法
	public void drawSelf(Graphics g) {
//		g.drawImage(img, (int)x, (int)y, null);
		Color c = g.getColor();
		g.setColor(Color.GRAY);
		g.fillOval((int)x, (int)y, width, height);
		if(x<0||x>Constant.SCREEN_WIDTH-width) 
			dir_x = -dir_x;
		if(y<28||y>Constant.SCREEN_HEIGHT-height) 
			dir_y = -dir_y;
		//根据方向移动
		if(live) {
			if(left) {
				x -= dir_x*speed;
			}
			if(right) {
				x += dir_x*speed;
			}
			if(up) {
				y -= dir_y*speed;
			}
			if(down) {
				y += dir_y*speed;
			}
		}else {
			//System.out.println("Die!");
		}
		g.setColor(c);
		
	}
	//构造器
	public Tank(/*Image img, */double x, double y) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.width = 30;
		this.height = 30;
//		this.width = img.getWidth(null);
//		this.height = img.getHeight(null);
	}
	//按下某个键，增加相应方向
	public void addDirection(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		}	
	}
	//松开某个键，取消相应方向
	public void minusDirection(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		}
	}
	//获取速度
	public int getSpeed() {
		return this.speed;
	}
	//改变速度
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	//改变大小
	public void setSize(int r) {
		this.width = r;
		this.height = r;
	}
}
