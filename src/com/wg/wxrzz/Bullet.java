package com.wg.wxrzz;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Paint;


public class Bullet {
	private int life = 10;
	private long lifetemtime;
	private int x, y;
	private int width = 10, height = 10;
	/** 0红色的子弹,1线性的子弹,2直线的子弹 **/
	private int type;
	/** 0正常,1爆炸,2移除 */
	private int status;
	private ArrayList bullet = new ArrayList();
	private int image[] = { 20, 23, 23 };
	private int speed;
	private int minsize = 5;
	private int maxcount = 5;
	/** 子弹之间的比例 **/
	private int lastsize = 2;
	private long autotime;
	private boolean autoend;
	/** 0上,1右上,2右,3右下,4下,5左下,6左,7左上 **/
	private int dir;
	private int boomzoomw, boomzoomh;
	private int boomspeed = 5;
	private int maxzoomsize = 100;
	private int boomwidth = 50, boomheight = 50;
	/** 0增大,1缩小 */
	private int boomstatus;
	private int boomalpha;
	private int boomalphaspeed;
	private int boomalphaadd = 4;
	private People people;
	private boolean canboom;

	public Bullet(int x, int y, int type, int dir, People people) {
		this.people = people;
		autoend = true;
		this.type = type;
		this.x = x;
		this.y = y;
		this.dir = dir;
		bullet.add(new int[] { x, y });
		boomzoomw = 0;
		boomzoomh = 0;
		boomstatus = 0;
		boomalpha = 225;
		switch (type) {
		case 0:
			speed = 7 + WGActivity.activity.gameCanvas.layerc;
			if (speed >= 10) {
				speed = 10;
			}
			break;
		case 1:
			speed = 15;
			autotime = MS.getTime();
			autoend = false;
			break;
		case 2:
			break;
		}
		canboom = false;
		Player.muaup.aupStart(Player.AU_2);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
		if (status == 1) {
			Player.muaup.aupStart(Player.AU_1);
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	private ArrayList<People> getAgg() {
		return WGActivity.activity.gameCanvas.agg;
	}

	public People getLead() {
		return WGActivity.activity.gameCanvas.lead;
	}

	public void run(int leadx, int leady) {
		switch (status) {
		case 0:
			if (!autoend) {
				switch (dir) {
				case 0:
					y -= speed;
					break;
				case 1:
					y -= speed / 2;
					x += speed / 2;
					break;
				case 2:
					x += speed;
					break;
				case 3:
					y += speed / 2;
					x += speed / 2;
					break;
				case 4:
					y += speed;
					break;
				case 5:
					y += speed / 2;
					x -= speed / 2;
					break;
				case 6:
					x -= speed / 2;
					break;
				case 7:
					y -= speed / 2;
					x -= speed / 2;
					break;
				}
				if (MS.getTime() - autotime >= 500) {
					autoend = true;
				}
				bullet.add(new int[] { x, y });
				if (bullet.size() > maxcount) {
					bullet.remove(0);
				}
			} else {
				int temxy[] = Alg.alg.pointMove(x, y,
						Alg.alg.lineAngle(x, y, leadx, leady), speed);
				x = temxy[0];
				y = temxy[1];
				bullet.add(new int[] { x, y });
				if (bullet.size() > maxcount) {
					bullet.remove(0);
				}
			}
			if (MS.getTime() - lifetemtime >= 1000) {
				lifetemtime = MS.getTime();
				life--;
				if (life < 0) {
					setStatus(1);
				}
			}
			runCollion();
			break;
		case 1:
			switch (boomstatus) {
			case 0:
				boomzoomw += boomspeed;
				boomzoomh = boomzoomw * boomheight / boomwidth;
				if (boomzoomw >= maxzoomsize) {
					boomstatus++;
					boomspeed = 2;
				}
				break;
			case 1:
				boomzoomw -= boomspeed;
				boomzoomh = boomzoomw * boomheight / boomwidth;
				boomalphaspeed += boomalphaadd;
				boomalpha -= boomalphaspeed;
				if (boomzoomw < boomwidth || boomalpha < 0) {
					boomzoomw = 0;
					boomalpha = 0;
					boomstatus++;
					status = 2;
				}
				break;
			}

			break;
		case 2:
			break;
		}
	}

	public void paint(Canvas g, Paint p) {
		switch (status) {
		case 0:
			for (int i = bullet.size() - 1; i >= 0; i--) {
				int[] tem = (int[]) bullet.get(i);
				p.setAlpha(200 + i * 5);
				Eff.eff.paintImageZoom(g, p, Pic.P.getBitmap(image[type]),
						tem[0], tem[1], minsize + i * lastsize, minsize + i
								* lastsize);
				p.setAlpha(255);
			}
			break;
		case 1:
			if (boomstatus == 1) {
				p.setAlpha(boomalpha);
			}
			Eff.eff.paintImageZoom(g, p, Pic.P.getBitmap(15), x, y, boomzoomw,
					boomzoomh);
			if (boomstatus == 1) {
				p.setAlpha(255);
			}
			break;
		case 2:
			break;
		}
		switch (type) {
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		}

	}

	private boolean intersectRectWithRect(int x1, int y1, int width1,
			int height1, int x2, int y2, int width2, int height2) {
		return y2 + height2 >= y1 && y2 <= y1 + height1 && x2 + width2 >= x1
				&& x2 <= x1 + width1;
	}

	private void runCollion() {
		if (getStatus() == 0) {
			for (int i = 0; i < getAgg().size(); i++) {
				People temagg = getAgg().get(i);
				if (getStatus() == 0 && (temagg.getStatus() == 0)
						|| (temagg.getStatus() == 1)) {
					if (intersectRectWithRect(x - width / 2, y - height / 2,
							width, height, temagg.getX() - temagg.getWidth()
									/ 2,
							temagg.getY() - temagg.getHeight() / 2,
							temagg.getWidth(), temagg.getHeight())) {
						if (temagg.equals(people) && !canboom) {
						} else {
							temagg.setLife(temagg.getLife() - 1);
							setStatus(1);
							return;
						}
					} else if (temagg.equals(people) && !canboom) {
						canboom = true;
					}
				}
			}
			if (getStatus() == 0 && getLead() != null) {
				if (intersectRectWithRect(x - width / 2, y - height / 2, width,
						height, getLead().getX() - getLead().getWidth() / 2,
						getLead().getY() - getLead().getHeight() / 2, getLead()
								.getWidth(), getLead().getHeight())) {
					getLead().setLife(getLead().getLife() - 1);
					setStatus(1);
				}
			}
		}
	}

}
