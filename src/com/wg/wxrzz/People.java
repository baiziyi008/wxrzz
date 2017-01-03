package com.wg.wxrzz;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class People {
	private int life;
	private int totallife;
	private int x, y;
	/** 每个对象占方块的大小 **/
	private int rect = 50;
	private int width, height;
	/** 0正常,1移动,2爆炸,3移除 */
	private int status;
	/** 0主角,1四角形的敌人,2圆形的敌人,3圆形的炸弹,4boss **/
	private int type;
	/** 旋转的角度 */
	private int ang;
	private int w_fixd = 800;
	private int h_fixd = 480;
	private int speed = 10;
	private int image[] = { 21, 16, 17, 19 };
	private int width18 = 12, height18 = 14;
	private int movedir;
	private int imagewidth, imageheight;
	private ArrayList list = new ArrayList();
	private long createtemtime;
	private int createtime;
	private int boomzoomw, boomzoomh;
	private int boomcount = 8;
	private long boomtemcount;
	private int boomspeed = 7;
	private int maxzoomsize = 200;
	private int boomwidth = 50, boomheight = 50;
	/** 0增大,1缩小 */
	private int boomstatus;
	private int boomalpha;
	private int boomalphaspeed;
	private int boomalphaadd = 4;
	private int outxy[] = { 50, 50 };
	private int startxy[][] = { { -outxy[0], -outxy[1] },
			{ w_fixd / 2, -outxy[1] }, { w_fixd + outxy[0], -outxy[1] },
			{ w_fixd + outxy[0], h_fixd / 2 },
			{ w_fixd + outxy[0], h_fixd + outxy[1] },
			{ w_fixd / 2, h_fixd + outxy[1] },
			{ -outxy[0], h_fixd + outxy[1] }, { -outxy[0], h_fixd / 2 } };
	private int endxy[] = new int[2];

	private void initBoom() {
		boomstatus = 0;
		boomalpha = 225;
		boomalphaspeed = 0;
		boomzoomw = 10;
		boomzoomh = 10;
	}

	public int getAng() {
		return ang;
	}

	public void setAng(int ang) {
		this.ang = ang;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
		if (this.life < 0) {
			setStatus(2);
		}
	}

	public int getTotallife() {
		return totallife;
	}

	public void setTotallife(int totallife) {
		this.totallife = totallife;
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
		if (status != 1) {
			list.clear();
		}
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public People(int type) {
		this.type = type;
		imagewidth = Pic.P.getBitmap(image[type]).getWidth();
		imageheight = Pic.P.getBitmap(image[type]).getHeight();
		width = imagewidth;
		height = imageheight;
		status = 1;
		life = 0;
		speed = 10;
		switch (type) {
		case 0:
			speed = 10;
			life = 50;
			x = 400;
			y = 480;
			break;
		case 1:
			int tem = MS.getRandom(startxy.length);
			x = startxy[tem][0];
			y = startxy[tem][1];
			endxy[0] = MS.getRandom(3, w_fixd / rect - 2) * rect;
			endxy[1] = MS.getRandom(2, h_fixd / rect - 2) * rect;
			break;
		case 2:
			int tem1 = MS.getRandom(startxy.length);
			x = startxy[tem1][0];
			y = startxy[tem1][1];
			speed = 2;
			break;
		case 3:
			int tem2 = MS.getRandom(startxy.length);
			x = startxy[tem2][0];
			y = startxy[tem2][1];
			endxy[0] = MS.getRandom(3, w_fixd / rect - 2) * rect;
			endxy[1] = MS.getRandom(2, h_fixd / rect - 2) * rect;
			break;
		}
		initBoom();
		totallife = life;
	}

	private Point tempoint;
	private int startend[][] = new int[2][2];
	private int maxsize = 10;

	public void keyPeople(int dir) {
		if (status == 2 || status == 1) {
			return;
		}
		switch (dir) {
		case 0:
			ang = 270;
			if (y <= 0) {
				dir = -1;
			}
			break;
		case 1:
			ang = 315;
			if (y <= 0 || x >= w_fixd) {
				dir = -1;
			}
			break;
		case 2:
			ang = 0;
			if (x >= w_fixd) {
				dir = -1;
			}
			break;
		case 3:
			ang = 45;
			if (x >= w_fixd || y >= h_fixd) {
				dir = -1;
			}
			break;
		case 4:
			ang = 90;
			if (y >= h_fixd) {
				dir = -1;
			}
			break;
		case 5:
			ang = 135;
			if (x <= 0 || y >= h_fixd) {
				dir = -1;
			}
			break;
		case 6:
			ang = 180;
			if (x <= 0) {
				dir = -1;
			}
			break;
		case 7:
			if (x <= 0 || y <= 0) {
				dir = -1;
			}
			ang = 225;
			break;
		default:
			break;
		}
		switch (dir) {
		case 0:
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
			int temxy[] = Alg.alg.pointMove(x, y, ang, speed);
			x = temxy[0];
			y = temxy[1];
			int temxy1[] = Alg.alg.pointMove(x, y, 180 + ang, width);
			list.add(temxy1);
			if (list.size() >= maxsize) {
				list.remove(0);
			}
			break;
		default:
			list.clear();
			break;
		}
		// if (p != null) {
		// if (tempoint == null) {
		// tempoint = p;
		// } else {
		// if (Math.abs(x - p.x) >= speed || Math.abs(y - p.y) >= speed) {
		// ang = Alg.alg.lineAngle(x, y, p.x, p.y);
		// int temxy[] = Alg.alg.pointMove(x, y, ang, speed);
		// x = temxy[0];
		// y = temxy[1];
		// int temxy1[] = Alg.alg.pointMove(x, y, 180 + ang, width);
		// list.add(temxy1);
		// if (list.size() >= maxsize) {
		// list.remove(0);
		// }
		// } else {
		// list.clear();
		// }
		// }
		// } else if (tempoint != null) {
		// list.clear();
		// tempoint = null;
		// }
	}

	private int changetime;
	private long changetemtime;

	public void run() {
		switch (status) {
		case 1:
			switch (type) {
			case 0:
				ang = -90;
				int temxy[] = Alg.alg.pointMove(x, y, ang, speed);
				x = temxy[0];
				y = temxy[1];
				int temxy1[] = Alg.alg.pointMove(x, y, 180 + ang, width);
				list.add(temxy1);
				if (list.size() >= maxsize) {
					list.remove(0);
				}
				if (y <= 240) {
					setStatus(0);
				}
				break;
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			}
			break;
		case 0:
			break;
		case 2:
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
					status = 3;
				}
				break;
			}
			break;
		case 3:
			break;
		}
	}

	private boolean intersectRectWithRect(int x1, int y1, int width1,
			int height1, int x2, int y2, int width2, int height2) {
		return y2 + height2 >= y1 && y2 <= y1 + height1 && x2 + width2 >= x1
				&& x2 <= x1 + width1;
	}

	public void run(int leadx, int leady) {
		switch (status) {
		case 1:
			switch (type) {
			case 0:
				break;
			case 1:
				list.add(new int[] { x, y });
				if (list.size() >= maxsize) {
					list.remove(0);
				}
				ang = Alg.alg.lineAngle(x, y, endxy[0], endxy[1]);
				int temxy[] = Alg.alg.pointMove(x, y, ang, speed);
				x = temxy[0];
				y = temxy[1];
				if (intersectRectWithRect(x, y, width, height, endxy[0],
						endxy[1], rect, rect)) {
					setStatus(0);
				}
				break;
			case 2:
				movedir = Alg.alg.lineAngle(x, y, leadx, leady);
				if (MS.getTime() - createtemtime >= createtime) {
					createtemtime = MS.getTime();
					createtime = MS.getRandom(2000, 6000);
					WGActivity.activity.gameCanvas.bullet.add(new Bullet(x, y,
							0, 0, this));
				}

				if (MS.getTime() - changetemtime > changetime) {
					changetemtime = MS.getTime();
					changetime = MS.getRandom(5, 10) * 1000;
					ang = MS.getRandom(0, 37) * 10;
				}
				int temxy2[] = Alg.alg.pointMove(x, y, ang, speed);
				if (temxy2[0] < rect && temxy2[1] < rect) {
					ang = 45;
				} else if (temxy2[0] < rect && temxy2[1] > h_fixd - rect) {
					ang = 315;
				} else if (temxy2[0] > w_fixd - rect && temxy2[1] < rect) {
					ang = 135;
				} else if (temxy2[0] > w_fixd - rect
						&& temxy2[1] > h_fixd - rect) {
					ang = 225;
				} else if (temxy2[0] <= 0) {
					ang = 0;
				} else if (temxy2[0] >= w_fixd) {
					ang = 180;
				} else if (temxy2[1] >= h_fixd) {
					ang = 270;
				} else if (temxy2[1] <= 0) {
					ang = 90;
				}
				// if (temxy2[0] < 0 || temxy2[0] > w_fixd || temxy2[1] < 0
				// || temxy2[1] > h_fixd) {
				// temxy2[0] = x;
				// temxy2[1] = y;
				// ang += 180;
				// if (ang >= 360) {
				// ang -= 360;
				// }
				//
				// if (ang < 0) {
				// ang += 360;
				// }
				// }
				x = temxy2[0];
				y = temxy2[1];
				// int temxy3[] = Alg.alg.pointMove(x, y, 180 + ang, width / 2);
				// list.add(temxy3);
				// if (list.size() >= maxsize) {
				// list.remove(0);
				// }
				break;
			case 3:
				list.add(new int[] { x, y });
				if (list.size() >= maxsize) {
					list.remove(0);
				}
				ang = Alg.alg.lineAngle(x, y, endxy[0], endxy[1]);
				int temxy4[] = Alg.alg.pointMove(x, y, ang, speed);
				x = temxy4[0];
				y = temxy4[1];
				if (intersectRectWithRect(x, y, width, height, endxy[0],
						endxy[1], rect, rect)) {
					setStatus(0);
				}
				break;
			case 4:
				break;
			}
			break;
		case 0:
			switch (type) {
			case 0:
				ang = Alg.alg.lineAngle(x, y, leadx, leady);
				int temxy[] = Alg.alg.pointMove(x, y, ang, speed);
				x = temxy[0];
				y = temxy[1];
				break;
			case 1:
				if (MS.getTime() - createtemtime >= createtime) {
					createtemtime = MS.getTime();
					createtime = MS.getRandom(2000, 6000);
					WGActivity.activity.gameCanvas.bullet.add(new Bullet(x, y,
							0, 0, this));
				}
				ang = Alg.alg.lineAngle(x, y, leadx, leady);
				break;
			case 2:
				break;
			case 3:
				if (MS.getTime() - boomtemcount >= 1000) {
					boomcount--;
					boomtemcount = MS.getTime();
					if (boomcount <= 0) {
						setStatus(2);
						WGActivity.activity.gameCanvas.bullet.add(new Bullet(x,
								y, 1, 0, this));
						WGActivity.activity.gameCanvas.bullet.add(new Bullet(x,
								y, 1, 1, this));
						WGActivity.activity.gameCanvas.bullet.add(new Bullet(x,
								y, 1, 2, this));
						WGActivity.activity.gameCanvas.bullet.add(new Bullet(x,
								y, 1, 3, this));
						WGActivity.activity.gameCanvas.bullet.add(new Bullet(x,
								y, 1, 4, this));
						WGActivity.activity.gameCanvas.bullet.add(new Bullet(x,
								y, 1, 5, this));
						WGActivity.activity.gameCanvas.bullet.add(new Bullet(x,
								y, 1, 6, this));
						WGActivity.activity.gameCanvas.bullet.add(new Bullet(x,
								y, 1, 7, this));
					}
				}
				break;
			case 4:
				break;
			}
			break;
		case 2:
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
					status = 3;
				}
				break;
			}
			break;
		case 3:
			break;
		}
	}

	/*** 炸弹显示时间间隔 **/
	private long temtime;
	private int listang;

	public void paint(Canvas g, Paint p) {
		switch (status) {
		case 1:
		case 0:
			for (int i = 0; i < list.size(); i++) {
				int tem[] = (int[]) list.get(i);
				p.setAlpha(i * 225 / maxsize);
				g.save();
				g.rotate(listang, tem[0], tem[1]);
				listang += 90;
				if (listang >= 180) {
					listang -= 180;
				}
				Eff.eff.paintImage(g, p, Pic.P.getBitmap(36), tem[0], tem[1],
						MS.ANCHOR_CHV);
				g.restore();
				p.setAlpha(225);
			}
			Eff.eff.paintImageRotate(g, p, Pic.P.getBitmap(image[type]), ang,
					imagewidth / 2, imageheight / 2, x, y);
			switch (type) {
			case 0:
				break;
			case 1:
				break;
			case 2:
				Eff.eff.paintImageRotate(g, p, Pic.P.getBitmap(18), movedir,
						width18, height18, x, y);
				break;
			case 3:
				if (MS.getTime() - temtime >= 500) {
					temtime = MS.getTime();
					Eff.eff.paintImage(g, p, Pic.P.getBitmap(34), x, y,
							MS.ANCHOR_CHV);
				}
				break;
			case 4:
				break;
			}
			break;
		case 2:
			if (boomstatus == 1) {
				p.setAlpha(boomalpha);
			}
			Eff.eff.paintImageZoom(g, p, Pic.P.getBitmap(35), x, y, boomzoomw,
					boomzoomh);
			if (boomstatus == 1) {
				p.setAlpha(255);
			}
			break;
		case 3:
			break;
		}

	}

}
