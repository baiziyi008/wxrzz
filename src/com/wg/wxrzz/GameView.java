package com.wg.wxrzz;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements
		android.view.SurfaceHolder.Callback {
	SurfaceHolder SurfaceHolder;
	private Paint paint;
	private PaintFlagsDrawFilter paintFlagsDrawFilter;
	public int delay = 1000 / 30;
	private Thread thread;
	private long time;
	private Paper icanvas;
	private long looptime;
	public static GameView cv;

	public GameView(Context context) {
		super(context);
		SurfaceHolder = null;
		SurfaceHolder = getHolder();
		SurfaceHolder.addCallback(this);
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		Paper.system_w = width;
		Paper.system_h = height;
	}

	public void surfaceCreated(SurfaceHolder surfaceholder) {
	}

	public void surfaceDestroyed(SurfaceHolder surfaceholder) {
	}

	public void bufferDraw() {
		Canvas canvas = SurfaceHolder.lockCanvas();
		if (SurfaceHolder == null || canvas == null)
			return;
		if (paintFlagsDrawFilter == null)
			paintFlagsDrawFilter = new PaintFlagsDrawFilter(0, 3);
		canvas.setDrawFilter(paintFlagsDrawFilter);
		if (paint == null)
			paint = new Paint();
		paint.reset();
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);
		if (MS.screen != null) {
			Eff.eff.paintImageZoom(canvas, paint, MS.screen,
					Paper.system_w / 2, Paper.system_h / 2, Paper.system_w,
					Paper.system_h);
		}
		SurfaceHolder.unlockCanvasAndPost(canvas);
	}

	public boolean onTouchEvent(MotionEvent e) {
		int action = e.getAction() & MotionEvent.ACTION_MASK;
		int touchcount = e.getPointerCount();
		int pointerIndex = (e.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
		switch (action) {
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_POINTER_DOWN:
		case MotionEvent.ACTION_MOVE:
			if (touchcount <= 2) {
				for (int i = 0; i < touchcount; i++) {
					int id1 = e.getPointerId(i);
					if (id1 < 2) {
						float x1 = e.getX(i);
						float y1 = e.getY(i);

						Point p1 = new Point();
						p1.x = (int) ((x1 * Paper.w_fixed) / Paper.system_w);
						p1.y = (int) ((y1 * Paper.h_fixed) / Paper.system_h);
						Paper.tempoint.put(id1, p1);
					}
				}
			}
			break;

		case MotionEvent.ACTION_UP:
			Paper.tempoint.clear();
			break;

		case MotionEvent.ACTION_POINTER_UP:
			int id1 = e.getPointerId(pointerIndex);
			if (Paper.tempoint.containsKey(id1)) {
				Paper.tempoint.remove(id1);
			}
			break;
		case MotionEvent.ACTION_CANCEL:
			break;
		}
		return true;
	}

	public void init() {
		thread = null;
		if (thread == null) {
			Runnable r1 = new Runnable() {

				public void run() {
					while (MS.isAlive)
						mainthread();
				}

			};
			thread = new Thread(r1);
			thread.setPriority(10);
			thread.start();
		}
	}

	private void mainthread() {
		try {
			looptime = MS.getTime();
			if (icanvas != null)
				mainrun();
			time = MS.getTime() - looptime;
			Thread.sleep(Math.max(1L, (long) delay - time));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void mainrun() throws Exception {
		Paper.mPoint.clear();
		Paper.mPoint.putAll(Paper.tempoint);
		icanvas.keyAction();
		icanvas.run();
		icanvas.repaint();
		bufferDraw();
	}

	public void show(Paper canvas) {
		if (icanvas != canvas) {
			icanvas = canvas;
			icanvas.mPoint.clear();
			icanvas.tempoint.clear();
		}
	}

	public Bitmap getBuffImage() {
		return MS.screen;
	}

}
