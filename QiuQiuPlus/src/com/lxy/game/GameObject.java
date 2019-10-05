package com.lxy.game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * 游戏的父类
 * @author LXY
 *
 */
public class GameObject {
	Image img;
	double x, y;
	int speed;
	int width,height;
	
	//生命和弧度
	boolean live = true;
	double degree;
	
	//画自己
	public void drawSelf(Graphics g) {
		g.drawImage(img, (int)x, (int)y, null);
	}
	//构造器
	public GameObject(Image img,double x,double y,int speed,int width,int height){
		super();
		this.img = img;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = width;
		this.height = height;
	}
	//重载构造器
	public GameObject(Image img, double x, double y) {
		super();
		this.img = img;
		this.x = x;
		this.y = y;
	}
	//重载构造器
	public GameObject(){
	}	
	
	//返回矩形图片信息
	public Rectangle getRect() {
		return new Rectangle((int)x, (int)y, width, height);
	}
	//设置位置
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	//设置速度
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
}
