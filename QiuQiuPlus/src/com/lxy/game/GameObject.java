package com.lxy.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * ��Ϸ�ĸ���
 * @author LXY
 *
 */
public class GameObject {
	Image img;
	double x, y;
	int speed;
	int width,height;
	
	//�����ͻ���
	boolean live = true;
	double degree;
	
	//���Լ�
	public void drawSelf(Graphics g) {
		g.drawImage(img, (int)x, (int)y, null);
	}
	//������
	public GameObject(Image img,double x,double y,int speed,int width,int height){
		super();
		this.img = img;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = width;
		this.height = height;
	}
	//���ع�����
	public GameObject(Image img, double x, double y) {
		super();
		this.img = img;
		this.x = x;
		this.y = y;
	}
	//���ع�����
	public GameObject(){
	}	
	
	//���ؾ���ͼƬ��Ϣ
	public Rectangle getRect() {
		return new Rectangle((int)x, (int)y, width, height);
	}
	//����λ��
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	//�����ٶ�
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
}
