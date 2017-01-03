package com.wg.wxrzz;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;


public class Eff {
	public static Eff eff = new Eff();
	private int w_fixed = 800;
	private int h_fixed = 480;

	public Eff() {
		eff = this;
	}

	private String string1 = "";

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				Toast.makeText(WGActivity.activity, string1, Toast.LENGTH_SHORT)
						.show();
				break;
			default:
				break;
			}
			super.handleMessage(msg);
		}
	};

	public void init() {
	}

	public void showTip(final String s) {
		string1 = s;
		Message message = new Message();
		message.what = 1;
		handler.sendMessage(message);
	}

	public void paintShabby(Canvas g, Paint p) {
		p.setColor(Color.BLACK);
		p.setAlpha(150);
		p.setStyle(Style.FILL);
		g.drawRect(0, 0, w_fixed, h_fixed, p);
		p.setAlpha(255);
	}

	public void paintShabby1(Canvas g, Paint p) {
		p.setColor(Color.BLACK);
		p.setAlpha(150);
		p.setStyle(Style.FILL);
		g.drawRect(0, 0, w_fixed, h_fixed, p);
		p.setAlpha(255);
	}

	/***** ÓÎÏ·°ïÖú²¿·Ö ****/
	private Bitmap bitmap;
	private Canvas canvas;
	private Paint paint;

	private void initBitmap() {
		bitmap = Bitmap.createBitmap(w_fixed, h_fixed, Config.ARGB_4444);
		canvas = new Canvas(bitmap);
		paint = new Paint();
	}

	private int helpstatus;
	private int helpx;
	private int alphacount = 10;
	private int alphawidth = 10;
	private int helpspeed = 20;

	public void initHelp() {
		initBitmap();
		paintHelp1(canvas, paint);
		helpx = 0;
		helpstatus = 0;
	}

	private void paintHelp1(Canvas g, Paint p) {
		paintShabby(g, p);
		paintImage(g, p, Pic.P.getBitmap(7), 430, 255, MS.ANCHOR_CHV);
		paintImage(g, p, Pic.P.getBitmap(12), 404, 45, MS.ANCHOR_CHV);
		paintImage(g, p, Pic.P.getBitmap(13), 655, 448, MS.ANCHOR_CHV);
		paintImage(g, p, Pic.P.getBitmap(14), w_fixed / 2, h_fixed / 2,
				MS.ANCHOR_CHV);
	}

	private void runHelp() {
		if (helpstatus == 0) {
			helpx += helpspeed;
			if (helpx >= w_fixed) {
				helpx = w_fixed;
				helpstatus++;
			}
		}
	}

	public void paintHelp(Canvas g, Paint p) {
		g.save();
		g.clipRect(0, 0, helpx, h_fixed);
		paintImage(g, p, bitmap, w_fixed / 2, h_fixed / 2, MS.ANCHOR_CHV);
		g.restore();
		if (helpstatus == 0) {
			for (int i = 0; i < alphacount; i++) {
				g.save();
				g.clipRect(helpx + i * alphawidth, 0, helpx + (i + 1)
						* alphawidth, h_fixed);
				int temalpha = 255 - (i + 1) * 255 / alphacount > 0 ? 255
						- (i + 1) * 255 / alphacount : 0;
				p.setAlpha(temalpha);
				paintImage(g, p, bitmap, w_fixed / 2, h_fixed / 2,
						MS.ANCHOR_CHV);
				p.setAlpha(255);
				g.restore();
			}
			runHelp();
		}
	}

	public void paintImage(Canvas g, Paint p, Bitmap bitmap, int x, int y,
			int anchor) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		switch (anchor) {
		case MS.ANCHOR_CHD:
			x -= width / 2;
			y -= height;
			break;
		case MS.ANCHOR_CHU:
			x -= width / 2;
			break;
		case MS.ANCHOR_CHV:
			x -= width / 2;
			y -= height / 2;
			break;
		case MS.ANCHOR_CVL:
			y -= height / 2;
			break;
		case MS.ANCHOR_CVR:
			x -= width;
			y -= height / 2;
			break;
		case MS.ANCHOR_LD:
			y -= height;
			break;
		case MS.ANCHOR_LU:
			break;
		case MS.ANCHOR_RD:
			x -= width;
			y -= height;
			break;
		case MS.ANCHOR_RU:
			x -= width;
			break;
		}
		paintImage(g, p, bitmap, x, y);
	}

	private void paintImage(Canvas g, Paint p, Bitmap bitmap, int x, int y) {
		g.drawBitmap(bitmap, x, y, p);
	}

	public void paintImage_H(Canvas g, Paint p, Bitmap bitmap, int x, int y,
			int index, int frame) {
		int width = bitmap.getWidth();
		int height = bitmap.getHeight() / frame;
		g.save();
		g.clipRect(x - width / 2, y - height / 2, x + width / 2, y + height / 2);
		paintImage(g, p, bitmap, x - width / 2, y - index * height - height / 2);
		g.restore();
	}

	public void paintImage_V(Canvas g, Paint p, Bitmap bitmap, int x, int y,
			int index, int frame) {
		int width = bitmap.getWidth() / frame;
		int height = bitmap.getHeight();
		g.save();
		g.clipRect(x - width / 2, y - height / 2, x + width / 2, y + height / 2);
		paintImage(g, p, bitmap, x - index * width - width / 2, y - height / 2);
		g.restore();
	}

	public void paintImage_H_Rotate(Canvas g, Paint p, Bitmap bitmap, int x,
			int y, int index, int frame, int rotatenum) {
		g.save();
		g.rotate(rotatenum, x, y);
		paintImage_H(g, p, bitmap, x, y, index, frame);
		g.restore();
	}

	public void paintImage_V_Zoom(Canvas g, Paint p, Bitmap bitmap, int x,
			int y, int index, int frame, int zoomw, int zoomh) {
		g.save();
		Matrix mtx = new Matrix();
		float scaleWidth = (float) zoomw * frame / (float) bitmap.getWidth();
		float scaleHeight = (float) zoomh / (float) bitmap.getHeight();
		mtx.postScale(scaleWidth, scaleHeight);
		mtx.postTranslate(x - zoomw / 2 - zoomw * index, y - zoomh / 2);
		g.clipRect(x - zoomw / 2, y - zoomh / 2, x + zoomw / 2, y + zoomh / 2);
		g.drawBitmap(bitmap, mtx, p);
		mtx = null;
		g.restore();
	}

	public void paintImageRotate(Canvas g, Paint p, Bitmap bitmap,
			int rotatenum, int rotatex, int rotatey, int x, int y) {
		g.save();
		Matrix mtx = new Matrix();
		mtx.setRotate(rotatenum, rotatex, rotatey);
		mtx.postTranslate(x - rotatex, y - rotatey);
		g.drawBitmap(bitmap, mtx, p);
		mtx = null;
		g.restore();
	}

	public void paintImageRotateM(Canvas g, Paint p, Bitmap b, int rotatenum,
			int rotatex, int rotatey, int x, int y) {
		g.save();
		Matrix mtx = new Matrix();
		mtx.setRotate(rotatenum, rotatex, rotatey);
		mtx.postScale(-1F, 1.0F);
		mtx.postTranslate(x + rotatex, y - rotatey);
		g.drawBitmap(b, mtx, p);
		mtx = null;
		g.restore();
	}

	public void paintImageZoom(Canvas g, Paint p, Bitmap b, int x, int y,
			int w, int h) {
		g.save();
		Matrix mtx = new Matrix();
		float scaleWidth = (float) w / (float) b.getWidth();
		float scaleHeight = (float) h / (float) b.getHeight();
		mtx.postScale(scaleWidth, scaleHeight);
		mtx.postTranslate(x - w / 2, y - h / 2);
		g.drawBitmap(b, mtx, p);
		mtx = null;
		g.restore();
	}

	public void paintNumber(Canvas g, Paint p, Bitmap bitmap, int num,
			int offset, int x, int y, int anchor) {
		if (num >= 0) {
			String tems = String.valueOf(num);
			int w = bitmap.getWidth() / 10 + offset;
			int l = tems.length();
			for (int i = 0; i < l; i++) {
				switch (anchor) {
				case MS.ANCHOR_CVL:
					paintImage_V(g, p, bitmap, x + i * w + w / 2, y,
							Integer.parseInt((new StringBuilder(String
									.valueOf(tems.charAt(i)))).toString()), 10);
					break;
				case MS.ANCHOR_CVR:
					paintImage_V(g, p, bitmap, x - w * l + i * w + w / 2, y,
							Integer.parseInt((new StringBuilder(String
									.valueOf(tems.charAt(i)))).toString()), 10);
					break;
				case MS.ANCHOR_CHV:
					paintImage_V(g, p, bitmap, x - w * l / 2 + i * w + w / 2,
							y, Integer.parseInt((new StringBuilder(String
									.valueOf(tems.charAt(i)))).toString()), 10);
					break;
				}
			}
		} else {
			String tems = String.valueOf(Math.abs(num));
			int w = bitmap.getWidth() / 10 + offset;
			int l = tems.length();
			for (int i = 0; i < l; i++) {
				switch (anchor) {
				case MS.ANCHOR_CVL:
					paintImage_V(g, p, bitmap, x + i * w, y,
							Integer.parseInt((new StringBuilder(String
									.valueOf(tems.charAt(i)))).toString()), 10);
					break;
				case MS.ANCHOR_CVR:
					paintImage_V(g, p, bitmap, x - w * l + i * w, y,
							Integer.parseInt((new StringBuilder(String
									.valueOf(tems.charAt(i)))).toString()), 10);
					break;
				case MS.ANCHOR_CHV:
					paintImage_V(g, p, bitmap, x - w * l / 2 + i * w, y,
							Integer.parseInt((new StringBuilder(String
									.valueOf(tems.charAt(i)))).toString()), 10);
					break;
				}
			}

		}
	}

	private ArrayList<String> string = new ArrayList<String>();
	private String tems = "";
	private long temtime;

	public int getHeight(Paint p) {
		android.graphics.Paint.FontMetrics fm = p.getFontMetrics();
		return (int) Math.ceil(fm.descent - fm.ascent);
	}

	public int getHeight1(Paint p) {
		android.graphics.Paint.FontMetrics fm = p.getFontMetrics();
		return (int) Math.ceil(-fm.ascent);
	}

	public int getWidth(Paint p, String string) {
		return (int) p.measureText(string);
	}

	public void paintString(Canvas g, Paint p, String s, int x, int y, int w,
			int h, int color, float speed) {
		string.clear();
		if (!tems.equals(s)) {
			temtime = MS.getTime();
			tems = s;
		}
		g.save();
		g.clipRect(x - 1, y - 1, x + w + 1, y + h + 1);
		int start = 0;
		int height = getHeight(p);
		String tems = s.trim();
		for (int i = 0; i < tems.length(); i++) {
			if (tems.charAt(i) == '*') {
				string.add(tems.substring(start, i));
				start = i + 1;
			} else {
				if (getWidth(p, tems.substring(start, i + 1)) > w) {
					string.add(tems.substring(start, i));
					start = i;
				}
				if (i == tems.length() - 1) {
					string.add(tems.substring(start, i + 1));
				}
			}
		}

		for (int i = 0; i < string.size(); i++) {
			p.setColor(color);
			g.drawText(string.get(i), x, y + height * (i + 1) + temy, p);
		}
		g.restore();
		if ((string.size() + 1) * height > h) {
			if (MS.getTime() - temtime >= 3000) {
				temy -= speed;
			}
			if (temy < -(string.size() + 1) * height) {
				temy = h;
			}
		}
	}

	private float temy;

	public void paintString(Canvas g, Paint p, String s, int x, int y, int w,
			int h, int color, boolean auto, float speed) {
		string.clear();
		if (!tems.equals(s)) {
			temtime = MS.getTime();
			tems = s;
		}
		g.save();
		g.clipRect(x - 1, y - 1, x + w + 1, y + h + 1);
		int start = 0;
		int height = getHeight(p);
		String tems = s.trim();
		for (int i = 0; i < tems.length(); i++) {
			if (tems.charAt(i) == '*') {
				string.add(tems.substring(start, i));
				start = i + 1;
			} else {
				if (getWidth(p, tems.substring(start, i + 1)) > w) {
					string.add(tems.substring(start, i));
					start = i;
				}
				if (i == tems.length() - 1) {
					string.add(tems.substring(start, i + 1));
				}
			}
		}

		for (int i = 0; i < string.size(); i++) {
			p.setColor(color);
			g.drawText(string.get(i), x, y + height * (i + 1) + temy, p);
		}
		g.restore();
		if (auto) {
			if (MS.getTime() - temtime >= 3000) {
				temy -= speed;
			}
			if (temy < -(string.size() + 1) * height) {
				temy = h;
			}
		}
	}

}
