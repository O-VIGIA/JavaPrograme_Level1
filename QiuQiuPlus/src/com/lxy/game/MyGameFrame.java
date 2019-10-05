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
 * ��Ϸ������
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
	//����ͼƬ
//	Image tankImg = GameUtil.getImage("images/tank.jpg");
	Image bgImg = GameUtil.getImage("images/bg.jpg");
	//������
	Tank tank = new Tank(/*tankImg, */400, 450);
	GameObject bg = new GameObject(bgImg, 0, 20);
	Shell[] shells = new Shell[25];
	Resource[] resources = new Resource[10];
	Big[] bigs = new Big[20];
	//����
	@Override
	public void paint(Graphics g) { //�Զ����û���g
		//���� g.color
		Color c = g.getColor();
		//����
		bg.drawSelf(g);
		//����̹��
		tank.setSpeed(speed);
		if(tank.live)
			tank.drawSelf(g);
		else {
			//������ʱ���ܸ�����ʾ
			g.setColor(Color.YELLOW);
			Font f = new Font("����",Font.BOLD,50);
			g.setFont(f);
			g.drawString("�������"+period+"�룡", (int)tank.x, (int)tank.y);
		}
		//���ڵ�
		for(int i=0;i<shells.length;++i) {
			boolean peng = false;
			shells[i].draw(g);
			//��ײ���
			peng = shells[i].getRect().intersects(tank.getRect());
			if(peng) {
				//��ʱ��
				endTime = new Date();
				period = (int)((endTime.getTime()-startTime.getTime())/1000);
				tank.live = false;
			}
		}
		//��������Դ
		for(int j=0;j<resources.length;++j) {
			boolean peng1 = false;
			//�������ͻ�
			if(resources[j].live)
				resources[j].draw(g);
			//��ײ���
			peng1 = resources[j].getRect().intersects(tank.getRect());
			if(peng1) {
				speed = speed + 1;
				tank.setSpeed(speed);
				resources[j].live = false;
			}
		}
		//�������Դ
		for(int k=0;k<bigs.length;++k) {
			boolean peng2 = false;
			//�������ͻ�
			if(bigs[k].live)
				bigs[k].draw(g);
			//��ײ���
			peng2 = bigs[k].getRect().intersects(tank.getRect());
			if(peng2) {
				r = Math.random()*50+10;
				tank.setSize((int)r);
				bigs[k].live=false;
			}
		}
		//�ָ�g.color
		g.setColor(c);
		
	}
	
	//�ڲ���������Ƿ���������
	class PaintThread extends Thread{
		
		@Override
		public void run() {
			while(true) {
				//�ػ�
//				System.out.println("�ػ���һ�Σ�");
				repaint(); 
				//��ʱ
				try {      
					Thread.sleep(35);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	//�������̵��ڲ���
	class KeyMonitor extends KeyAdapter{
		//���ذ��¼��������¼�
		@Override
		public void keyPressed(KeyEvent e) {
			tank.addDirection(e);
		}
		//�����ɿ����������¼�
		@Override
		public void keyReleased(KeyEvent e) {
			tank.minusDirection(e);
		}
		
	}
	
	//��ʼ������
	public void launchFrame() {
		//��Ϸ��ʼ��
		this.setTitle("#��Ϸ������Ĵ���   #���ߣ�Lxy");
		this.setVisible(true);
		this.setSize(Constant.SCREEN_WIDTH,Constant.SCREEN_HEIGHT);
		this.setLocation(100,10);	
		//�Զ��ر�
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		//�����ػ����ڵ��߳�
		new PaintThread().start(); 
		//�����Լ��̵ļ���
		addKeyListener(new KeyMonitor()); 
		//��ʼ���ڵ�
		for(int i=0;i<shells.length;++i) {
			shells[i] = new Shell();
			double tmp_x = Math.random()*Constant.SCREEN_WIDTH;
			double tmp_y = Math.random()*Constant.SCREEN_HEIGHT;
			double tmp_speed = Math.random()*10+3;
			shells[i].setPosition(tmp_x, tmp_y);
			shells[i].setSpeed((int)tmp_speed);
		}
		//��ʼ���ٶ���Դ
		for(int j=0;j<resources.length;++j) {
			resources[j] = new Resource();
			double tmp_x = Math.random()*Constant.SCREEN_WIDTH;
			double tmp_y = Math.random()*Constant.SCREEN_HEIGHT;
			double tmp_speed = Math.random()*10+5;
			resources[j].setPosition(tmp_x, tmp_y);
			resources[j].setSpeed((int)tmp_speed);
		}
		//��ʼ����С��Դ
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
	//˫��������˸
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
