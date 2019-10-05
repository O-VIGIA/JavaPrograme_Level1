package com.lxy.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * ̹����:���ڰ�������
 * @author ½Сү
 *
 */
public class Tank extends GameObject {
	
	//���巽��
	int dir_x = 1;
	int dir_y = 1;
	//����̹������
	boolean live = true;
	//����̹�˷���
	boolean left, up, right, down;
	//��д������
	public void drawSelf(Graphics g) {
//		g.drawImage(img, (int)x, (int)y, null);
		Color c = g.getColor();
		g.setColor(Color.GRAY);
		g.fillOval((int)x, (int)y, width, height);
		if(x<0||x>Constant.SCREEN_WIDTH-width) 
			dir_x = -dir_x;
		if(y<28||y>Constant.SCREEN_HEIGHT-height) 
			dir_y = -dir_y;
		//���ݷ����ƶ�
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
	//������
	public Tank(/*Image img, */double x, double y) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.width = 30;
		this.height = 30;
//		this.width = img.getWidth(null);
//		this.height = img.getHeight(null);
	}
	//����ĳ������������Ӧ����
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
	//�ɿ�ĳ������ȡ����Ӧ����
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
	//��ȡ�ٶ�
	public int getSpeed() {
		return this.speed;
	}
	//�ı��ٶ�
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	//�ı��С
	public void setSize(int r) {
		this.width = r;
		this.height = r;
	}
}
