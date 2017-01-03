package com.wg.wxrzz;

import java.util.TreeMap;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Rect;

public class Paper {
	public final String LOGKEY = "ICanvas";
	public static TreeMap mPoint = new TreeMap();
	public static TreeMap tempoint = new TreeMap();
	public static int w_fixed = 800;
	public static int h_fixed = 480;
	public static int system_w = 0;
	public static int system_h = 0;
	private Canvas g;
	private Paint p;

	public Paper() {
		g = null;
		p = null;
	}

	public void show() {
		GameView.cv.show(this);
	}

	public void keyAction() {
	}

	public void run() {
	}

	public void repaint() {
		if (MS.screen != null) {
			if (g == null) {
				g = new Canvas(MS.screen);
				g.setDrawFilter(new PaintFlagsDrawFilter(0, 3));
			}
			if (p == null)
				p = new Paint();
			g.save();
			p.setStyle(Style.STROKE);
			p.setTextSize(MS.fontSize);
			p.setAntiAlias(true);
			MS.cls(g, p, new Rect(0, 0, w_fixed, h_fixed));
			paint(g, p);
			g.restore();
		}
	}

	public void paint(Canvas canvas, Paint paint1) {
	}

}
