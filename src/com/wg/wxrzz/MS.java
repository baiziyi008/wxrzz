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

	/** �������ĸ����� */
	public static int GMS = -1;
	public static int oldGMS = -1;
	public static boolean isLoading = false;
	/** logo���� */
	public static final int GMS_0 = 1;
	/** �˵����� */
	public static final int GMS_1 = 2;
	/** ��Ϸ���� */
	public static final int GMS_2 = 3;
	/** �̵���� */
	public static final int GMS_3 = 4;

	/** �˵�ѡ��״̬ */
	public static int MenuSel = -1;
	/** �˵���ʾ״̬ */
	public static int MenuSh = -1;
	/** δѡ�� */
	public static final int Menu_ = -1;
	/** ��ʼ��Ϸ */
	public static final int Menu_0 = 0;
	/** ������Ϸ */
	public static final int Menu_1 = 1;
	/** ������Ϸ */
	public static final int Menu_2 = 2;
	/** ��Ϸ���� */
	public static final int Menu_3 = 3;
	/** ��Ϸ���� */
	public static final int Menu_4 = 4;
	/** ��Ϸ���� */
	public static final int Menu_5 = 5;
	/** �˳���Ϸ */
	public static final int Menu_6 = 6;

	/*** �����˵� */
	public static int Menu2 = -1;
	/** δѡ�� */
	public static final int Menu2_ = -1;
	/** ɾ���浵 */
	public static final int Menu2_0 = 0;
	/** �浵ѡ�� */
	public static final int Menu2_1 = 1;
	/** ����ѡ�� */
	public static final int Menu2_2 = 2;
	/** �ؿ�ѡ�� */
	public static final int Menu2_3 = 3;

	/*** ��Ϸ״̬ */
	public static int Game = -1;
	/*** δѡ�� */
	public static final int Game_ = -1;
	/*** ��Ϸ���� */
	public static final int Game_0 = 0;
	/*** ��Ϸ�̳� */
	public static final int Game_1 = 1;
	/*** ��Ϸ���� */
	public static final int Game_2 = 2;
	/*** ��Ϸ�� */
	public static final int Game_3 = 3;
	/*** ��Ϸ��ͣ */
	public static final int Game_4 = 4;
	/*** ��Ϸʧ�� */
	public static final int Game_5 = 5;
	/*** ��Ϸ�ɹ� */
	public static final int Game_6 = 6;
	/*** ��Ϸͨ�� */
	public static final int Game_7 = 7;

	/*** ��ͣ״̬ */
	public static int Pause = -1;
	/*** δѡ�� */
	public static int Pause_ = -1;
	/*** ������Ϸ */
	public static final int Pause_0 = 0;
	/*** ��Ϸ���� */
	public static final int Pause_1 = 1;
	/*** ��Ϸ���� */
	public static final int Pause_2 = 2;
	/*** �����˵� */
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
