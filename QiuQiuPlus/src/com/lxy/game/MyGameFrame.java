package com.lxy.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;



/**
 * 游戏主窗口
 * @author LXY
 *
 */
public class MyGameFrame extends Frame {
	//speed && r
	int speed = 3;
	double r;
	//time
	Date startTime = new Date();
	Date endTime;
	int period;
	//加载图片
//	Image tankImg = GameUtil.getImage("images/tank.jpg");
	Image bgImg = GameUtil.getImage("images/bg.jpg");
	//创建类
	Tank tank = new Tank(/*tankImg, */400, 450);
	GameObject bg = new GameObject(bgImg, 0, 20);
	Shell[] shells = new Shell[25];
	Resource[] resources = new Resource[10];
	Big[] bigs = new Big[20];
	//画笔
	@Override
	public void paint(Graphics g) { //自动调用画笔g
		//保存 g.color
		Color c = g.getColor();
		//背景
		bg.drawSelf(g);
		//设置坦克
		tank.setSpeed(speed);
		if(tank.live)
			tank.drawSelf(g);
		else {
			//启动计时功能给出提示
			g.setColor(Color.YELLOW);
			Font f = new Font("楷体",Font.BOLD,50);
			g.setFont(f);
			g.drawString("你持续了"+period+"秒！", (int)tank.x, (int)tank.y);
		}
		//画炮弹
		for(int i=0;i<shells.length;++i) {
			boolean peng = false;
			shells[i].draw(g);
			//碰撞检测
			peng = shells[i].getRect().intersects(tank.getRect());
			if(peng) {
				//计时器
				endTime = new Date();
				period = (int)((endTime.getTime()-startTime.getTime())/1000);
				tank.live = false;
			}
		}
		//画加速资源
		for(int j=0;j<resources.length;++j) {
			boolean peng1 = false;
			//有生命就画
			if(resources[j].live)
				resources[j].draw(g);
			//碰撞检测
			peng1 = resources[j].getRect().intersects(tank.getRect());
			if(peng1) {
				speed = speed + 1;
				tank.setSpeed(speed);
				resources[j].live = false;
			}
		}
		//画变大资源
		for(int k=0;k<bigs.length;++k) {
			boolean peng2 = false;
			//有生命就画
			if(bigs[k].live)
				bigs[k].draw(g);
			//碰撞检测
			peng2 = bigs[k].getRect().intersects(tank.getRect());
			if(peng2) {
				r = Math.random()*50+10;
				tank.setSize((int)r);
				bigs[k].live=false;
			}
		}
		//恢复g.color
		g.setColor(c);
		
	}
	
	//内部类帮助我们反复画窗口
	class PaintThread extends Thread{
		
		@Override
		public void run() {
			while(true) {
				//重画
//				System.out.println("重画了一次！");
				repaint(); 
				//延时
				try {      
					Thread.sleep(35);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	//监听键盘的内部类
	class KeyMonitor extends KeyAdapter{
		//重载按下键发生的事件
		@Override
		public void keyPressed(KeyEvent e) {
			tank.addDirection(e);
		}
		//重载松开键发生的事件
		@Override
		public void keyReleased(KeyEvent e) {
			tank.minusDirection(e);
		}
		
	}
	
	//初始化窗口
	public void launchFrame() {
		//游戏初始化
		this.setTitle("#游戏：生存的代价   #作者：Lxy");
		this.setVisible(true);
		this.setSize(Constant.SCREEN_WIDTH,Constant.SCREEN_HEIGHT);
		this.setLocation(100,10);	
		//自动关闭
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		//启动重画窗口的线程
		new PaintThread().start(); 
		//启动对键盘的监听
		addKeyListener(new KeyMonitor()); 
		//初始化炮弹
		for(int i=0;i<shells.length;++i) {
			shells[i] = new Shell();
			double tmp_x = Math.random()*Constant.SCREEN_WIDTH;
			double tmp_y = Math.random()*Constant.SCREEN_HEIGHT;
			double tmp_speed = Math.random()*10+3;
			shells[i].setPosition(tmp_x, tmp_y);
			shells[i].setSpeed((int)tmp_speed);
		}
		//初始化速度资源
		for(int j=0;j<resources.length;++j) {
			resources[j] = new Resource();
			double tmp_x = Math.random()*Constant.SCREEN_WIDTH;
			double tmp_y = Math.random()*Constant.SCREEN_HEIGHT;
			double tmp_speed = Math.random()*10+5;
			resources[j].setPosition(tmp_x, tmp_y);
			resources[j].setSpeed((int)tmp_speed);
		}
		//初始化大小资源
		for(int k=0;k<bigs.length;++k) {
			bigs[k] = new Big();
			double tmp_x = Math.random()*Constant.SCREEN_WIDTH;
			double tmp_y = Math.random()*Constant.SCREEN_HEIGHT;
			double tmp_speed = Math.random()*10+5;
			bigs[k].setPosition(tmp_x, tmp_y);
			bigs[k].setSpeed((int)tmp_speed);
		}
	}
	
	
	
	public static void main(String[] args) {
		MyGameFrame f = new MyGameFrame();	
		f.launchFrame();
		
	}
	//双缓冲解决闪烁
	private Image offScreenImage = null;
	
	public void update(Graphics g) {
		if(offScreenImage == null) {
			offScreenImage = this.createImage(Constant.SCREEN_WIDTH,Constant.SCREEN_HEIGHT);	
		}
		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
}
