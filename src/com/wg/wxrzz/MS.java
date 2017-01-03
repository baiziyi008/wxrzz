package com.wg.wxrzz;

import java.util.Random;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class MS {
	public MS() {
	}

	public static boolean IS_SoundMU = false;
	public static boolean IS_SoundAU = false;

	/** 现在是哪个界面 */
	public static int GMS = -1;
	public static int oldGMS = -1;
	public static boolean isLoading = false;
	/** logo界面 */
	public static final int GMS_0 = 1;
	/** 菜单界面 */
	public static final int GMS_1 = 2;
	/** 游戏界面 */
	public static final int GMS_2 = 3;
	/** 商店界面 */
	public static final int GMS_3 = 4;

	/** 菜单选择状态 */
	public static int MenuSel = -1;
	/** 菜单显示状态 */
	public static int MenuSh = -1;
	/** 未选择 */
	public static final int Menu_ = -1;
	/** 开始游戏 */
	public static final int Menu_0 = 0;
	/** 继续游戏 */
	public static final int Menu_1 = 1;
	/** 更多游戏 */
	public static final int Menu_2 = 2;
	/** 游戏设置 */
	public static final int Menu_3 = 3;
	/** 游戏帮助 */
	public static final int Menu_4 = 4;
	/** 游戏关于 */
	public static final int Menu_5 = 5;
	/** 退出游戏 */
	public static final int Menu_6 = 6;

	/*** 二级菜单 */
	public static int Menu2 = -1;
	/** 未选择 */
	public static final int Menu2_ = -1;
	/** 删除存档 */
	public static final int Menu2_0 = 0;
	/** 存档选择 */
	public static final int Menu2_1 = 1;
	/** 人物选择 */
	public static final int Menu2_2 = 2;
	/** 关卡选择 */
	public static final int Menu2_3 = 3;

	/*** 游戏状态 */
	public static int Game = -1;
	/*** 未选择 */
	public static final int Game_ = -1;
	/*** 游戏故事 */
	public static final int Game_0 = 0;
	/*** 游戏教程 */
	public static final int Game_1 = 1;
	/*** 游戏开场 */
	public static final int Game_2 = 2;
	/*** 游戏中 */
	public static final int Game_3 = 3;
	/*** 游戏暂停 */
	public static final int Game_4 = 4;
	/*** 游戏失败 */
	public static final int Game_5 = 5;
	/*** 游戏成功 */
	public static final int Game_6 = 6;
	/*** 游戏通关 */
	public static final int Game_7 = 7;

	/*** 暂停状态 */
	public static int Pause = -1;
	/*** 未选择 */
	public static int Pause_ = -1;
	/*** 继续游戏 */
	public static final int Pause_0 = 0;
	/*** 游戏设置 */
	public static final int Pause_1 = 1;
	/*** 游戏帮助 */
	public static final int Pause_2 = 2;
	/*** 回主菜单 */
	public static final int Pause_3 = 3;

	public static Resources resources;
	public static final int ANCHOR_CHV = 0;
	public static final int ANCHOR_CHU = 1;
	public static final int ANCHOR_CHD = 2;
	public static final int ANCHOR_CVL = 3;
	public static final int ANCHOR_CVR = 4;
	public static final int ANCHOR_LU = 5;
	public static final int ANCHOR_RU = 6;
	public static final int ANCHOR_LD = 7;
	public static final int ANCHOR_RD = 8;
	public static boolean isAlive = false;
	public static boolean isSecret = false;
	public static Bitmap screen;
	public static int fontSize = 25;
	private static Random rand = new Random();

	public static long getTime() {
		return System.currentTimeMillis();
	}

	public static int getRandom(int begin, int end) {
		if (Math.abs(begin - end) == 0)
			return 0;
		else
			return Math.abs(rand.nextInt()) % Math.abs(end - begin) + begin;
	}

	public static int getRandom(int end) {
		return Math.abs(rand.nextInt()) % end;
	}

	public static void cls(Canvas g, Paint p, Rect r) {
		cls(g, p, r, Color.rgb(0, 0, 0));
	}

	public static void cls(Canvas g, Paint p, Rect r, int color) {
		p.setStyle(android.graphics.Paint.Style.FILL);
		p.setColor(color);
		g.drawRect(r, p);
	}

	public static void setResources(Resources r) {
		resources = r;
	}

}
