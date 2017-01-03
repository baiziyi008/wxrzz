package com.wg.wxrzz;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Paint;


public class GameMenuCanvas extends Paper {
	private int png[] = new int[] { 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };
	private int jpg[] = new int[] { 3 };
	private ArrayList<Integer> pngimage = new ArrayList<Integer>();
	private ArrayList<Integer> jpgimage = new ArrayList<Integer>();

	@Override
	public void keyAction() {
		// TODO Auto-generated method stub
		switch (MS.Menu2) {
		case MS.Menu2_0:
			Button.button.tbsl.setTBData(190, 370, 100, 100);
			if (Button.button.tbsl.isPressed(mPoint)) {
				DB.db.delDB();
				setMenu2(MS.Menu2_);
				WGActivity.activity.showGameCanvas();
				Player.muaup.aupStart(Player.AU_0);
				return;
			}
			Button.button.tbsr.setTBData(610, 370, 100, 100);
			if (Button.button.tbsr.isPressed(mPoint)) {
				setMenu2(MS.Menu2_);
				Player.muaup.aupStart(Player.AU_0);
				return;
			}
			break;
		case MS.Menu2_1:
			break;
		case MS.Menu2_2:
			break;
		case MS.Menu2_3:
			break;
		default:
			switch (MS.MenuSel) {
			case MS.Menu_0:
				break;
			case MS.Menu_1:
				break;
			case MS.Menu_2:
				break;
			case MS.Menu_3:
				break;
			case MS.Menu_4:
			case MS.Menu_5:
				Button.button.tb_d.setTBData(655, 448, 100, 100);
				if (Button.button.tb_d.isPressed(mPoint)) {
					setMenuSel(MS.Menu_);
					Player.muaup.aupStart(Player.AU_0);
					return;
				}
				break;
			case MS.Menu_6:
				break;
			default:
				Button.button.tbsr.setTBData(763, 43, 80, 80);
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
				// Button.button.tb_d.setTBData(w_fixed / 2, h_fixed / 2,
				// w_fixed,
				// h_fixed);
				// if (Button.button.tb_d.isPressed(mPoint)) {
				// initMenu();
				// }
				Button.button.tb_u.setTBData(w_fixed / 2, 260, 200, 70);
				if (Button.button.tb_u.isPressed(mPoint)) {
					if (DB.db.isold()) {
						setMenu2(MS.Menu2_0);
					} else {
						WGActivity.activity.showGameCanvas();
					}
					Player.muaup.aupStart(Player.AU_0);
					return;
				}
				Button.button.tb_s.setTBData(w_fixed / 2, 335, 200, 70);
				if (Button.button.tb_s.isPressed(mPoint)) {
					if (!DB.db.isold()) {
						Eff.eff.showTip("没有存档");
					} else {
						WGActivity.activity.showGameCanvas();
					}
					Player.muaup.aupStart(Player.AU_0);
					return;
				}
				Button.button.tb_d.setTBData(w_fixed / 2, 410, 200, 70);
				if (Button.button.tb_d.isPressed(mPoint)) {
					setMenuSel(MS.Menu_4);
					Player.muaup.aupStart(Player.AU_0);
					return;
				}
				break;
			}
			break;
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		switch (MS.Menu2) {
		case MS.Menu2_0:
			break;
		case MS.Menu2_1:
			break;
		case MS.Menu2_2:
			break;
		case MS.Menu2_3:
			break;
		default:
			switch (MS.MenuSel) {
			case MS.Menu_0:
				break;
			case MS.Menu_1:
				break;
			case MS.Menu_2:
				break;
			case MS.Menu_3:
				break;
			case MS.Menu_4:
				break;
			case MS.Menu_5:
				break;
			case MS.Menu_6:
				break;
			default:
				break;
			}
			break;
		}
	}

	@Override
	public void paint(Canvas g, Paint p) {
		paintMenu(g, p);
		// TODO Auto-generated method stub
		switch (MS.Menu2) {
		case MS.Menu2_0:
			paintDelete(g, p);
			break;
		case MS.Menu2_1:
			break;
		case MS.Menu2_2:
			break;
		case MS.Menu2_3:
			break;
		default:
			switch (MS.MenuSel) {
			case MS.Menu_0:
				break;
			case MS.Menu_1:
				break;
			case MS.Menu_2:
				break;
			case MS.Menu_3:
				break;
			case MS.Menu_4:
			case MS.Menu_5:
				Eff.eff.paintHelp(g, p);
				break;
			case MS.Menu_6:
				break;
			default:
				break;
			}
			break;
		}

	}

	/**************************** 游戏封面部分 ***************************************************/
	private int menuzoomw, menuzoomh;
	private int menuspeed;
	private int menuadd = 10;
	/** 0标题由大变小,1抖动,2,3,4,5,6 */
	private int menustatus;
	private long menutemtime;
	private int menuwidth = 430, menuheight = 147;

	private void initMenu() {
		menuzoomw = menuwidth + 700;
		menuzoomh = menuzoomw * menuheight / menuwidth;
		menuspeed = -menuadd;
		menustatus = 0;
	}

	private void runMenu() {
		switch (menustatus) {
		case 0:
			menuspeed += menuadd;
			menuzoomw -= menuspeed;
			if (menuzoomw <= menuwidth) {
				menuzoomw = menuwidth;
				menustatus++;
				menutemtime = MS.getTime();
			}
			menuzoomh = menuzoomw * menuheight / menuwidth;
			break;
		case 1:
			if (MS.getTime() - menutemtime >= 500) {
				menustatus++;
			}
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		}
	}

	private void paintMenu(Canvas g, Paint p) {
		int temx = 0, temy = 0;
		if (menustatus == 1) {
			temx = MS.getRandom(0, 5) - 2;
			temy = MS.getRandom(0, 5) - 2;
		}
		Eff.eff.paintImage(g, p, Pic.P.getBitmap(3), w_fixed / 2 + temx,
				h_fixed / 2 + temy, MS.ANCHOR_CHV);
		Eff.eff.paintImageZoom(g, p, Pic.P.getBitmap(4), w_fixed / 2 + temx,
				100 + temy, menuzoomw, menuzoomh);
		if (MS.IS_SoundAU) {
			Eff.eff.paintImage_H(g, p, Pic.P.getBitmap(5), 763 + temx,
					43 + temy, 0, 2);
		} else {
			Eff.eff.paintImage_H(g, p, Pic.P.getBitmap(5), 763 + temx,
					43 + temy, 1, 2);
		}

		for (int i = 0; i < 3; i++) {
			Eff.eff.paintImage_H(g, p, Pic.P.getBitmap(6), w_fixed / 2 + temx,
					260 + i * 75 + temy, i, 3);
		}
		runMenu();
	}

	/**************** 删除存档提示 ********************************/
	private int deletex;
	private int deletestatus;
	private int deletespeed = 100;

	private void initDelete() {
		deletex = -w_fixed / 2;
		deletestatus = 0;
	}

	private void runDelete() {
		deletex += deletespeed;
		if (deletex >= 0) {
			deletex = 0;
			deletestatus++;
		}
	}

	private void paintDelete(Canvas g, Paint p) {
		Eff.eff.paintShabby(g, p);
		Eff.eff.paintImage(g, p, Pic.P.getBitmap(8), w_fixed / 2 + deletex,
				190, MS.ANCHOR_CHV);
		Eff.eff.paintImage(g, p, Pic.P.getBitmap(11), w_fixed / 2 - deletex,
				190, MS.ANCHOR_CHV);
		Eff.eff.paintImage(g, p, Pic.P.getBitmap(9), 190, 370, MS.ANCHOR_CHV);
		Eff.eff.paintImage(g, p, Pic.P.getBitmap(10), 610, 370, MS.ANCHOR_CHV);
		if (deletestatus == 0) {
			runDelete();
		}
	}

	private void setMenu2(int status) {
		MS.Menu2 = status;
		switch (MS.Menu2) {
		case MS.Menu2_0:
			initDelete();
			break;
		case MS.Menu2_1:
			break;
		case MS.Menu2_2:
			break;
		case MS.Menu2_3:
			break;
		default:
			break;
		}
	}

	private void setMenuShow(int menu) {
		MS.MenuSh = menu;
		switch (MS.MenuSh) {
		case MS.Menu_0:
			break;
		case MS.Menu_1:
			break;
		case MS.Menu_2:
			break;
		case MS.Menu_3:
			break;
		case MS.Menu_4:
			break;
		case MS.Menu_5:
			break;
		case MS.Menu_6:
			break;
		default:
			break;
		}
	}

	private void setMenuSel(int menu) {
		MS.MenuSel = menu;
		switch (MS.MenuSel) {
		case MS.Menu_0:
			break;
		case MS.Menu_1:
			break;
		case MS.Menu_2:
			break;
		case MS.Menu_3:
			break;
		case MS.Menu_4:
		case MS.Menu_5:
			Eff.eff.initHelp();
			break;
		case MS.Menu_6:
			break;
		default:
			break;
		}
	}

	public void loadingData() {
		loadingImage();
		initData();
	}

	private void initData() {
		initMenu();
	}

	public void disingData() {
		Pic.P.disImage(pngimage, jpgimage);
		pngimage.clear();
		jpgimage.clear();

	}

	private void loadingImage() {
		for (int p : png) {
			pngimage.add(p);
		}
		for (int j : jpg) {
			jpgimage.add(j);
		}

		Pic.P.loadImage(pngimage, jpgimage);
	}
}
