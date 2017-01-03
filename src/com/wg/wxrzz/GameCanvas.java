package com.wg.wxrzz;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Message;

/**
 * 游戏主界面
 */
public class GameCanvas extends Paper {
	private int png[] = new int[] { 7, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21,
			22, 23, 24, 25, 26, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 40, 41,
			42, 43, 44, 45, 46, 49, 50, 51, 52, 53, 54 };
	private int jpg[] = new int[] { 27 };
	private ArrayList<Integer> pngimage = new ArrayList<Integer>();
	private ArrayList<Integer> jpgimage = new ArrayList<Integer>();
	public int layerc;

	@Override
	public void keyAction() {
		// TODO Auto-generated method stub
		switch (MS.Game) {
		case MS.Game_0:
			break;
		case MS.Game_1:
			Button.button.tb_d.setTBData(655, 448, 100, 100);
			if (Button.button.tb_d.isPressed(mPoint)) {
				setGame(MS.Game_3);
				Player.muaup.aupStart(Player.AU_0);
				return;
			}
			break;
		case MS.Game_2:
			break;
		case MS.Game_3:
			if (isshowshop) {
				selectMoney((Point) mPoint.get(0));
				Button.button.tb_r.setTBData(750, 450, 100, 80);
				if (Button.button.tb_r.isPressed(mPoint)) {
					isshowshop = false;
				}
				return;
			}
			Button.button.tbsl.setTBData(40, 40, 100, 100);
			if (Button.button.tbsl.isPressed(mPoint)) {
				setGame(MS.Game_4);
				Player.muaup.aupStart(Player.AU_0);
				return;
			}
			Button.button.tb_s.setTBData(40, 200, 100, 100);
			if (Button.button.tb_s.isPressed(mPoint)) {
				if (boomcount > 0) {
					People temagg = agg.get(0);
					temagg.setLife(temagg.getLife() - 1);
					boomcount--;
				} else {
					isshowshop = true;
				}
				return;
			}
			Button.button.tbsr.setTBData(760, 40, 100, 100);
			if (Button.button.tbsr.isPressed(mPoint)) {
				if (MS.IS_SoundAU && MS.IS_SoundMU) {
					MS.IS_SoundMU = false;
					MS.IS_SoundAU = false;
					Player.muaup.mupStop();
					Player.muaup.aupStopAll();
					Player.muaup.disMAData();
				} else {
					MS.IS_SoundMU = true;
					MS.IS_SoundAU = true;
					Player.muaup.loadMAData();
					Player.muaup.mupStart();
				}

				return;
			}
			// if (mPoinMS.get(0) != null) {
			// bulleMS.add(new Bullet(400, 240, 0, 0));
			if (lead != null) {
				keyDir((Point) mPoint.get(0));
			}
			// }
			break;
		case MS.Game_4:
			switch (MS.Pause) {
			case MS.Pause_0:
				break;
			case MS.Pause_1:
				break;
			case MS.Pause_2:
				break;
			case MS.Pause_3:
				break;
			default:
				Button.button.tbsl.setTBData(w_fixed / 2, 240, 250, 100);
				if (Button.button.tbsl.isPressed(mPoint)) {
					setGame(MS.Game_3);
					Player.muaup.aupStart(Player.AU_0);
					return;
				}
				Button.button.tbsr.setTBData(w_fixed / 2, 360, 250, 100);
				if (Button.button.tbsr.isPressed(mPoint)) {
					DB.db.setOtherData(6, boomcount);
					DB.db.saveDB();
					WGActivity.activity.showGameMenuCanvas();
					Player.muaup.aupStart(Player.AU_0);
					return;
				}
				break;
			}
			break;
		case MS.Game_5:
			if (MS.getTime() - temtime < 1000) {
				return;
			}
			Button.button.tbsl.setTBData(240, 360, 150, 100);
			if (Button.button.tbsl.isPressed(mPoint)) {
				WGActivity.activity.showGameCanvas();
				Player.muaup.aupStart(Player.AU_0);
				return;
			}
			Button.button.tbsr.setTBData(560, 360, 150, 100);
			if (Button.button.tbsr.isPressed(mPoint)) {
				WGActivity.activity.showGameMenuCanvas();
				Player.muaup.aupStart(Player.AU_0);
				return;
			}
			break;
		case MS.Game_6:
			if (MS.getTime() - temtime < 1000) {
				return;
			}
			Button.button.tbsl.setTBData(240, 360, 150, 100);
			if (Button.button.tbsl.isPressed(mPoint)) {
				WGActivity.activity.showGameCanvas();
				Player.muaup.aupStart(Player.AU_0);
				return;
			}
			Button.button.tbsr.setTBData(560, 360, 150, 100);
			if (Button.button.tbsr.isPressed(mPoint)) {
				WGActivity.activity.showGameMenuCanvas();
				Player.muaup.aupStart(Player.AU_0);
				return;
			}
			break;
		case MS.Game_7:
			break;
		default:
			break;
		}

	}

	private Point temp2;

	private void selectMoney(Point p) {
		if (p != null) {
			if (temp2 == null && MS.getTime() - temtime > 2000) {
				temp2 = p;
			} else {
			}
		} else if (temp2 != null) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 2; j++) {
					if (intersectRectWithRect(temp2.x, temp2.y, 1, 1, 250 + i
							* 150 - 100 / 2, 170 + j * 150 - 100 / 2, 100, 100)) {
						Message message = new Message();
						message.what = j * 3 + i + 1;
						//WGActivity.activity.buyinfo.sendMessage(message);
						WGActivity.activity.buygold.sendMessage(message);
						temp2 = null;
						return;
					}
				}
			}
			temp2 = null;
		}
	}

	private boolean intersectRectWithRect(int x1, int y1, int width1,
			int height1, int x2, int y2, int width2, int height2) {
		return y2 + height2 >= y1 && y2 <= y1 + height1 && x2 + width2 >= x1
				&& x2 <= x1 + width1;
	}

	private Point temp;

	private void keyDir(Point p) {
		if (p != null) {
			if (intersectRectWithRect(p.x, p.y, 1, 1, centerx - dirwidth / 2,
					centery - dirheight / 2, dirwidth, dirheight)) {

				double tempX = (double) p.x - centerx;
				double tempY = (double) p.y - centery;
				if (tempX != 0.0D || tempY != 0.0D)
					if (tempX < 0.0D && tempY < 0.0D) {
						double a = Math.abs(tempX);
						double b = Math.abs(tempY);
						double c = Math.sqrt(a * a + b * b);
						int d = (int) Math.toDegrees(Math.asin(b / c));
						if (d > 0 && d <= 23)
							lead.keyPeople(6);
						else if (d > 23 && d <= 67)
							lead.keyPeople(7);
						else if (d > 67 && d < 90)
							lead.keyPeople(0);
					} else if (tempX < 0.0D && tempY > 0.0D) {
						double a = Math.abs(tempX);
						double b = Math.abs(tempY);
						double c = Math.sqrt(a * a + b * b);
						int d = (int) Math.toDegrees(Math.asin(b / c));
						if (d > 0 && d <= 23)
							lead.keyPeople(6);
						else if (d > 23 && d <= 67)
							lead.keyPeople(5);
						else if (d > 67 && d < 90)
							lead.keyPeople(4);
					} else if (tempX > 0.0D && tempY < 0.0D) {
						double a = Math.abs(tempX);
						double b = Math.abs(tempY);
						double c = Math.sqrt(a * a + b * b);
						int d = (int) Math.toDegrees(Math.asin(b / c));
						if (d > 0 && d <= 23)
							lead.keyPeople(2);
						else if (d > 23 && d <= 67)
							lead.keyPeople(1);
						else if (d > 67 && d < 90)
							lead.keyPeople(0);
					} else if (tempX > 0.0D && tempY > 0.0D) {
						double a = Math.abs(tempX);
						double b = Math.abs(tempY);
						double c = Math.sqrt(a * a + b * b);
						int d = (int) Math.toDegrees(Math.asin(b / c));
						if (d > 0 && d <= 23)
							lead.keyPeople(2);
						else if (d > 23 && d <= 67)
							lead.keyPeople(3);
						else if (d > 67 && d < 90)
							lead.keyPeople(4);
					} else if (tempX == 0.0D && tempY < 0.0D) {
						lead.keyPeople(0);
					} else if (tempX == 0.0D && tempY > 0.0D) {
						lead.keyPeople(4);
					} else if (tempX < 0.0D && tempY == 0.0D) {
						lead.keyPeople(6);
					} else if (tempX > 0.0D && tempY == 0.0D) {
						lead.keyPeople(2);
					}

				// int testX = p.x - centerx;
				// int testY = p.y - centery;
				// float testA = (float) testY / (float) testX;
				// if ((testA <= -1) || (testA >= 1)) {
				// if (testY <= 0) {
				// lead.keyPeople(0);
				// } else {
				// lead.keyPeople(4);
				// }
				// } else {
				// if (testX <= 0) {
				// lead.keyPeople(6);
				// } else {
				// lead.keyPeople(2);
				// }
				// }
			} else {
				lead.keyPeople(-1);
			}
		} else {
			lead.keyPeople(-1);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		switch (MS.Game) {
		case MS.Game_0:
			break;
		case MS.Game_1:
			break;
		case MS.Game_2:
			break;
		case MS.Game_3:
			if (isshowshop) {
				return;
			}
			if (hasbuy == 0 && layerc == 2) {
				///setGame(MS.Game_4);
				Message message = new Message();
				message.what = 0;
				 WGActivity.activity.buygold.sendMessage(message);
				//WGActivity.activity.buyinfo1.sendMessage(message);
				return;
			}
			runGame();
			break;
		case MS.Game_4:
			switch (MS.Pause) {
			case MS.Pause_0:
				break;
			case MS.Pause_1:
				break;
			case MS.Pause_2:
				break;
			case MS.Pause_3:
				break;
			default:
				break;
			}
			break;
		case MS.Game_5:
			break;
		case MS.Game_6:
			break;
		case MS.Game_7:
			break;
		default:
			break;
		}
	}

	public ArrayList<Bullet> bullet = new ArrayList<Bullet>();
	public ArrayList<People> agg = new ArrayList<People>();
	public People lead;
	private int image43 = 340;
	private int diecount;

	private void paintDown(Canvas g, Paint p) {
		Eff.eff.paintImage(g, p, Pic.P.getBitmap(27), w_fixed / 2, h_fixed / 2,
				MS.ANCHOR_CHV);
		Eff.eff.paintImage(g, p, Pic.P.getBitmap(40), 40, 40, MS.ANCHOR_CHV);
		Eff.eff.paintImage(g, p, Pic.P.getBitmap(42), w_fixed / 2, 40,
				MS.ANCHOR_CHV);
		g.save();
		if (lead != null) {
			g.clipRect(
					w_fixed / 2 - image43 / 2,
					0,
					w_fixed / 2 + image43 / 2
							- (lead.getTotallife() - lead.getLife()) * image43
							/ lead.getTotallife(), 100);
			Eff.eff.paintImage(g, p, Pic.P.getBitmap(43), w_fixed / 2, 40,
					MS.ANCHOR_CHV);
		}
		g.restore();
		if (MS.IS_SoundAU) {
			Eff.eff.paintImage_H(g, p, Pic.P.getBitmap(41), 760, 40, 0, 2);
		} else {
			Eff.eff.paintImage_H(g, p, Pic.P.getBitmap(41), 760, 40, 1, 2);
		}
		Eff.eff.paintImage(g, p, Pic.P.getBitmap(45), w_fixed / 2, h_fixed / 2,
				MS.ANCHOR_CHV);
		Eff.eff.paintNumber(g, p, Pic.P.getBitmap(44), layerc + 1, 0,
				w_fixed / 2, h_fixed / 2 + 5, MS.ANCHOR_CHV);
		Eff.eff.paintNumber(g, p, Pic.P.getBitmap(44), diecount, 0,
				w_fixed / 2 - 15, 100, MS.ANCHOR_CVR);
		Eff.eff.paintNumber(g, p, Pic.P.getBitmap(44), totalaggcount, 0,
				w_fixed / 2 + 15, 100, MS.ANCHOR_CVL);
		Eff.eff.paintImage(g, p, Pic.P.getBitmap(46), w_fixed / 2, 95,
				MS.ANCHOR_CHV);
	}

	private int firindex = 0;
	private long firtime;

	public int boomcount = 0;
	public int hasbuy = 0;

	@Override
	public void paint(Canvas g, Paint p) {
		paintDown(g, p);
		Eff.eff.paintImage(g, p, Pic.P.getBitmap(49), 40, 200, MS.ANCHOR_CHV);
		Eff.eff.paintImage(g, p, Pic.P.getBitmap(50 + firindex), 40 - 25,
				200 - 25, MS.ANCHOR_CHV);
		Eff.eff.paintNumber(g, p, Pic.P.getBitmap(54), boomcount, 0, 50, 240,
				MS.ANCHOR_CHV);
		if (MS.getTime() - firtime > 100) {
			firtime = MS.getTime();
			firindex++;
			if (firindex >= 4) {
				firindex = 0;
			}
		}
		// TODO Auto-generated method stub
		switch (MS.Game) {
		case MS.Game_0:
			break;
		case MS.Game_1:
			Eff.eff.paintHelp(g, p);
			break;
		case MS.Game_2:
			break;
		case MS.Game_3:
			paintGame(g, p);
			paintDir(g, p);
			if (isshowshop) {
				paintMoney(g, p);
			}
			break;
		case MS.Game_4:
			switch (MS.Pause) {
			case MS.Pause_0:
				break;
			case MS.Pause_1:
				break;
			case MS.Pause_2:
				break;
			case MS.Pause_3:
				break;
			default:
				paintPause(g, p);
				break;
			}
			break;
		case MS.Game_5:
			paintErr(g, p);
			break;
		case MS.Game_6:
			paintPass(g, p);
			break;
		case MS.Game_7:
			break;
		default:
			break;
		}

	}

	/*********************** 游戏部分 ******************************************************/
	private void runGame() {
		if (!canadd) {
			if (lead.getStatus() == 0) {
				canadd = true;
			}
		}
		if (canadd && MS.getTime() - addtime >= 2000) {
			addAgg();
		}
		if (agg.size() <= 0 && aggcount <= 0) {
			setGame(MS.Game_6);
			return;
		}
		for (int i = 0; i < bullet.size(); i++) {
			if (lead != null) {
				bullet.get(i).run(lead.getX(), lead.getY());
			} else {
				bullet.get(i).setStatus(1);
			}
			if (bullet.get(i).getStatus() >= 2) {
				bullet.remove(i);
				i--;
			}
		}
		for (int i = 0; i < agg.size(); i++) {
			if (lead != null) {
				agg.get(i).run(lead.getX(), lead.getY());
			} else {
				agg.get(i).run();
			}
			if (agg.get(i).getStatus() >= 3) {
				diecount++;
				agg.remove(i);
				i--;
			}
		}
		if (lead != null) {
			lead.run();
			if (lead.getStatus() >= 3) {
				lead = null;
				setGame(MS.Game_5);
			}
		}
		// runCollion();
	}

	private long addtime;
	private boolean canadd;
	private int aggtype = 3;

	private void addAgg() {
		if (agg.size() < minagg && aggcount > 0) {
			int type = 1;
			if (layerc >= 1) {
				int temcount = 10 - layerc > 2 ? 10 - layerc : 2;
				if (MS.getRandom(temcount) == 0) {
					type = 2;
				}
				if (layerc >= 2) {
					int temcount1 = 15 - layerc > 2 ? 15 - layerc : 2;
					if (type == 1 && MS.getRandom(temcount1) == 0) {
						type = 3;
					}
				}
			}
			agg.add(new People(type));
			aggcount--;
			addtime = MS.getTime();
		}
	}

	private void runCollion() {
		for (int t = 0; t < bullet.size(); t++) {
			Bullet tembullet = bullet.get(t);
			for (int i = 0; i < agg.size(); i++) {
				People temagg = agg.get(i);
				if (tembullet.getStatus() == 0 && (temagg.getStatus() == 0)
						|| (temagg.getStatus() == 1)) {
					if (intersectRectWithRect(
							tembullet.getX() - tembullet.getWidth() / 2,
							tembullet.getY() - tembullet.getHeight() / 2,
							tembullet.getWidth(), tembullet.getHeight(),
							temagg.getX() - temagg.getWidth() / 2,
							temagg.getY() - temagg.getHeight() / 2,
							temagg.getWidth(), temagg.getHeight())) {
						agg.get(t).setStatus(2);
						bullet.get(t).setStatus(1);
					}
				}
			}
			if (bullet.get(t).getStatus() == 0 && lead != null) {
				if (intersectRectWithRect(
						tembullet.getX() - tembullet.getWidth() / 2,
						tembullet.getY() - tembullet.getHeight() / 2,
						tembullet.getWidth(), tembullet.getHeight(),
						lead.getX() - lead.getWidth() / 2,
						lead.getY() - lead.getHeight() / 2, lead.getWidth(),
						lead.getHeight())) {
					lead.setLife(lead.getLife() - 1);
					bullet.get(t).setStatus(1);
				}
			}
		}
	}

	private void paintGame(Canvas g, Paint p) {
		for (int i = 0; i < agg.size(); i++) {
			agg.get(i).paint(g, p);
		}
		if (lead != null) {
			lead.paint(g, p);
		}
		for (int i = 0; i < bullet.size(); i++) {
			bullet.get(i).paint(g, p);
		}
	}

	/********************** 游戏暂停部分 ****************************************/

	private Bitmap bitmap;
	private Canvas canvas;
	private Paint paint;

	private int pausespeed;
	private int pauseadd = 5;
	private boolean runpause;
	private int pausey;

	private void initBitmap() {
		disBitmap();
		bitmap = Bitmap.createBitmap(w_fixed, h_fixed, Config.ARGB_4444);
		canvas = new Canvas(bitmap);
		paint = new Paint();
	}

	private void disBitmap() {
		if (bitmap != null && !bitmap.isRecycled()) {
			bitmap.recycle();
			bitmap = null;
			canvas = null;
			paint = null;
		}
	}

	private void initPause() {
		initBitmap();
		pausespeed = 0;
		runpause = true;
		paintPausetem(canvas, paint);
		pausey = -h_fixed;
	}

	private void runPause() {
		pausespeed += pauseadd;
		pausey += pausespeed;
		if (pausey >= 0) {
			pausey = 0;
			runpause = false;
		}
	}

	private void paintPausetem(Canvas g, Paint p) {
		Eff.eff.paintShabby1(g, p);
		Eff.eff.paintImage(g, p, Pic.P.getBitmap(29), w_fixed / 2, 110,
				MS.ANCHOR_CHV);
		Eff.eff.paintImage_H(g, p, Pic.P.getBitmap(28), w_fixed / 2, 240, 0, 2);
		Eff.eff.paintImage_H(g, p, Pic.P.getBitmap(28), w_fixed / 2, 360, 1, 2);
	}

	private void paintPause(Canvas g, Paint p) {
		Eff.eff.paintImage(g, p, bitmap, w_fixed / 2, h_fixed / 2 + pausey,
				MS.ANCHOR_CHV);
		if (runpause) {
			runPause();
		}
	}

	/************************* 游戏成功部分 ***************************************/
	private int passzoomw, passzoomh;
	private boolean runpass;
	private int passspeed;
	private int passadd = 5;

	private void initPass() {
		initBitmap();
		passzoomw = 0;
		passzoomh = 0;
		runpass = true;
		passspeed = 20;
		paintPasstem(canvas, paint);
	}

	private void runPass() {
		passspeed += passadd;
		passzoomw += passspeed;
		if (passzoomw >= w_fixed) {
			passzoomw = w_fixed;
			runpass = false;
		}
		passzoomh = passzoomw * h_fixed / w_fixed;
	}

	private void paintPasstem(Canvas g, Paint p) {
		Eff.eff.paintShabby(g, p);
		Eff.eff.paintImage(g, p, Pic.P.getBitmap(30), w_fixed / 2, 185,
				MS.ANCHOR_CHV);
		Eff.eff.paintImage_H(g, p, Pic.P.getBitmap(32), 240, 360, 1, 2);
		Eff.eff.paintImage_H(g, p, Pic.P.getBitmap(31), 560, 360, 1, 2);
	}

	private void paintPass(Canvas g, Paint p) {
		Eff.eff.paintImageZoom(g, p, bitmap, w_fixed / 2, h_fixed / 2,
				passzoomw, passzoomh);
		if (runpass) {
			runPass();
		}
	}

	/********************* 游戏失败部分 **************************************/
	private int erralpha;
	private boolean runerr;
	private int errspeed;

	private void initErr() {
		initBitmap();
		erralpha = 0;
		runerr = true;
		errspeed = 10;
		paintErrtem(canvas, paint);
	}

	private void runErr() {
		erralpha += errspeed;
		if (erralpha >= 225) {
			erralpha = 225;
			runerr = false;
		}
	}

	private void paintErrtem(Canvas g, Paint p) {
		Eff.eff.paintShabby(g, p);
		Eff.eff.paintImage(g, p, Pic.P.getBitmap(33), w_fixed / 2, 185,
				MS.ANCHOR_CHV);
		Eff.eff.paintImage_H(g, p, Pic.P.getBitmap(31), 240, 360, 0, 2);
		Eff.eff.paintImage_H(g, p, Pic.P.getBitmap(31), 560, 360, 1, 2);
	}

	private void paintErr(Canvas g, Paint p) {
		p.setAlpha(erralpha);
		Eff.eff.paintImage(g, p, bitmap, w_fixed / 2, h_fixed / 2,
				MS.ANCHOR_CHV);
		p.setAlpha(225);
		if (runerr) {
			runErr();
		}
	}

	/***************************** 方向按钮部分 *******************************************************/
	private int centerx = 680, centery = 360;
	private int dirwidth = 250, dirheight = 250;
	/** 0上,1右上,2右,3右下,4下,5左下,6左,7左上 **/
	private int dir;
	private int inte = 10;

	private void paintDir(Canvas g, Paint p) {
		Eff.eff.paintImage(g, p, Pic.P.getBitmap(37), centerx, centery,
				MS.ANCHOR_CHV);
	}

	private long temtime;

	private void setGame(int game) {
		switch (game) {
		case MS.Game_0:
			break;
		case MS.Game_1:
			Eff.eff.initHelp();
			break;
		case MS.Game_2:
			break;
		case MS.Game_3:
			break;
		case MS.Game_4:
			setPause(MS.Pause_);
			initPause();
			break;
		case MS.Game_5:
			initErr();
			DB.db.setOtherData(6, boomcount);
			DB.db.saveDB();
			temtime = MS.getTime();
			break;
		case MS.Game_6:
			temtime = MS.getTime();
			Player.muaup.aupStart(Player.AU_3);
			layerc++;
			DB.db.setGameData(1, layerc);
			DB.db.setOtherData(6, boomcount);
			DB.db.saveDB();
			initPass();
			break;
		case MS.Game_7:
			break;
		default:
			break;
		}
		MS.Game = game;
	}

	private void setPause(int pause) {
		MS.Pause = pause;
		switch (MS.Pause) {
		case MS.Pause_0:
			break;
		case MS.Pause_1:
			break;
		case MS.Pause_2:
			break;
		case MS.Pause_3:
			break;
		default:
			break;
		}
	}

	public void pauseGame() {
		if (MS.Game == MS.Game_3 && !isshowshop) {
			setGame(MS.Game_4);
		}
	}

	public void loadingData() {
		loadingImage();
		initStatus();
		initData();
	}

	private int aggcount;
	private int minagg = 5;
	private int totalaggcount;

	private void initData() {
		hasbuy = DB.db.getGameData()[6];
		boomcount = DB.db.getOtherData()[6];
		diecount = 0;
		agg.clear();
		bullet.clear();
		canadd = false;
		layerc = DB.db.getGameData()[1];
		aggcount = 4 + layerc * 5;
		minagg = 3 + layerc;
		if (minagg >= 10) {
			minagg = 10;
		}
		totalaggcount = aggcount;
		lead = new People(0);
	}

	private void initStatus() {
		// if (MS.IS_DEBUG) {
		if (DB.db.isold()) {
			setGame(MS.Game_3);
		} else {
			setGame(MS.Game_1);
		}
		// }
	}

	public void disingData() {
		Pic.P.disImage(pngimage, jpgimage);
		pngimage.clear();
		jpgimage.clear();
		disBitmap();
		agg.clear();
		bullet.clear();
	}

	private void loadingImage() {
		for (int p : png) {
			pngimage.add(p);
		}
		for (int j : jpg) {
			jpgimage.add(j);
		}

		Pic.P.loadImage(pngimage, jpgimage);
	}// **************************************************

	private int moneynum[] = { 5, 10, 15, 20, 25, 40, 70, 100 };
	private boolean isshowshop = false;

	private void paintMoney(Canvas g, Paint p) {
		Eff.eff.paintShabby(g, p);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 2; j++) {
				Eff.eff.paintImage(g, p, Pic.P.getBitmap(49), 250 + i * 150,
						170 + j * 150, MS.ANCHOR_CHV);
				Eff.eff.paintNumber(g, p, Pic.P.getBitmap(54), moneynum[i + j
						* 3], 0, 250 + i * 150, 200 + j * 150, MS.ANCHOR_CHV);
			}
		}
		Eff.eff.paintImage(g, p, Pic.P.getBitmap(13), 750, 450, MS.ANCHOR_CHV);
	}
}
