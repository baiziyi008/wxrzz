package com.wg.wxrzz;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Paint;


public class LoadingCanvas extends Paper {
	private int png[] = new int[] { 1, 2 };
	private int jpg[] = new int[] {};
	private ArrayList<Integer> pngimage = new ArrayList<Integer>();
	private ArrayList<Integer> jpgimage = new ArrayList<Integer>();

	private int progresscur = 0;// 当前进度
	private int progresstem = 0;// 进度临时参数
	private int progressall = 100;// 总进度

	private int delay = 1000;
	private long time;
	private long timeLast;

	public LoadingCanvas() {
		loadingImage();
	}

	@Override
	public void keyAction() {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		if (!MS.isLoading && progresstem == progressall) {
			switch (MS.GMS) {
			case MS.GMS_1:
				WGActivity.activity.gameMenuCanvas.show();
				break;
			case MS.GMS_2:
				WGActivity.activity.gameCanvas.show();
				break;
			}
		}
		// TODO Auto-generated method stub
		if (!MS.isLoading) {
			progresscur = progressall;
		} else {
			if (progresscur < progressall - 10) {
				timeLast = MS.getTime();
				if (timeLast - time >= delay) {
					time = timeLast;
					progresscur += (MS.getRandom(5) + 6);
				}
			}
		}

	}

	private int index;
	private long temtime;

	@Override
	public void paint(Canvas g, Paint p) {
		if (progresstem < progresscur) {
			if (progresscur - progresstem >= 5 && progresscur == progressall) {
				progresstem += 5;
			} else {
				progresstem++;
			}
		}
		// TODO Auto-generated method stub
		Eff.eff.paintImage(g, p, Pic.P.getBitmap(1), 520, 425, MS.ANCHOR_CHV);
		for (int i = 0; i < index; i++) {
			Eff.eff.paintImage(g, p, Pic.P.getBitmap(2), 620 + i * 20, 430,
					MS.ANCHOR_CHV);
		}
		if (MS.getTime() - temtime >= 500) {
			temtime = MS.getTime();
			index++;
			if (index > 6) {
				index = 0;
			}
		}
	}

	public void show() {
		initData();
		super.show();
	}

	private void initData() {
		progresscur = 0;
		progresstem = 0;
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
