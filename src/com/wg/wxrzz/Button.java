package com.wg.wxrzz;

import java.util.Iterator;
import java.util.TreeMap;

import android.graphics.Point;

public class Button {

	public TouchButton tbsl = new TouchButton(0, 0, 2, 2);
	public TouchButton tbsr = new TouchButton(0, 0, 2, 2);
	public TouchButton tb_u = new TouchButton(0, 0, 2, 2);
	public TouchButton tb_d = new TouchButton(0, 0, 2, 2);
	public TouchButton tb_l = new TouchButton(0, 0, 2, 2);
	public TouchButton tb_r = new TouchButton(0, 0, 2, 2);
	public TouchButton tb_s = new TouchButton(0, 0, 2, 2);

	public void initData() {
		tbsl = new TouchButton(0, 0, 2, 2);
		tbsr = new TouchButton(0, 0, 2, 2);
		tb_u = new TouchButton(0, 0, 2, 2);
		tb_d = new TouchButton(0, 0, 2, 2);
		tb_l = new TouchButton(0, 0, 2, 2);
		tb_r = new TouchButton(0, 0, 2, 2);
		tb_s = new TouchButton(0, 0, 2, 2);
	}

	// ======================================
	public static Button button = new Button();

	public Button() {
		button = this;
	}

	public class TouchButton {
		private int touchBabsX;
		private int touchBabsY;
		private int collisW;
		private int collisH;
		private int isTouch;

		public TouchButton(int touchBabsX, int touchBabsY, int collisW,
				int collisH) {
			isTouch = -1;
			this.touchBabsX = touchBabsX;
			this.touchBabsY = touchBabsY;
			this.collisW = collisW;
			this.collisH = collisH;
		}

		public void setTBData(int touchBabsX, int touchBabsY, int collisW,
				int collisH) {
			this.touchBabsX = touchBabsX;
			this.touchBabsY = touchBabsY;
			this.collisW = collisW;
			this.collisH = collisH;
		}

		private Point tempoint;

		private boolean intersectRectWithRect(int x1, int y1, int width1,
				int height1, int x2, int y2, int width2, int height2) {
			return y2 + height2 >= y1 && y2 <= y1 + height1
					&& x2 + width2 >= x1 && x2 <= x1 + width1;
		}

		public boolean isPressed(TreeMap mPoint) {
			for (Iterator iterator = mPoint.entrySet().iterator(); iterator
					.hasNext();) {
				java.util.Map.Entry e = (java.util.Map.Entry) iterator.next();
				Point point = (Point) e.getValue();
				if (intersectRectWithRect(touchBabsX - collisW / 2, touchBabsY
						- collisH / 2, collisW, collisH, point.x, point.y, 1, 1)) {
					if (tempoint == null) {
						isTouch = ((Integer) e.getKey()).intValue();
						tempoint = point;
						return true;
					} else {
						return false;
					}
				} else if (tempoint != null
						&& (!mPoint.containsKey(isTouch) || !intersectRectWithRect(
								touchBabsX - collisW / 2, touchBabsY - collisH
										/ 2, collisW, collisH, point.x,
								point.y, 1, 1))) {
					tempoint = null;
					return false;
				}
			}
			if (mPoint.size() <= 0) {
				tempoint = null;

			}
			return false;
		}

	}

}
