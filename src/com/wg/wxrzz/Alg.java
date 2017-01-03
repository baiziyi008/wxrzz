package com.wg.wxrzz;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

public class Alg {
	public static Alg alg = new Alg();

	public Alg() {
		alg = this;
	}

	/** ��ȡ���� */
	public int getDirect(float x, float y, float x1, float y1) {
		if (x == x1) {
			return -1;
		} else {
			float a = (y1 - y) / (x1 - x);
			if (a <= -2 || a >= 2) {// ����
				if (y1 > y) {
					return 1;
				} else {
					return 0;
				}
			}

			if (a >= -0.5 && a <= 0.5) {// ����
				if (x1 > x) {
					return 3;
				} else {
					return 2;
				}
			}

			if (a >= 0.5 && a <= 2) {// ��������
				if (x1 > x) {
					return 7;
				} else {
					return 4;
				}
			}

			if (a >= -2 && a <= -0.5) {// ��������
				if (x1 > x) {
					return 5;
				} else {
					return 6;
				}
			}
			return -1;
		}
	}

	/** �ߵĴ�С */
	public int lineS(double x1, double y1, double x2, double y2) {
		return (int) Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
	}

	/** �����ţ�����ת */
	public void paintImageRotateZoom(Canvas g, Paint p, Bitmap b, int srcRotx,
			int srcRoty, int roteNum, int x, int y, int w, int h) {
		g.save();
		Matrix mtx = new Matrix();
		float scaleWidth = (float) w / (float) b.getWidth();
		float scaleHeight = (float) h / (float) b.getHeight();
		mtx.setScale(scaleWidth, scaleHeight);
		mtx.postRotate(roteNum, srcRotx, srcRoty);
		mtx.postTranslate((float) x - (float) srcRotx * scaleWidth, (float) y
				- (float) srcRoty * scaleHeight);
		g.drawBitmap(b, mtx, p);
		mtx = null;
		g.restore();
	}

	/** Բ�Ĺ켣 */
	public int[][] getRound(int x, int y, int r) {
		ArrayList<double[]> round = new ArrayList<double[]>();
		for (int i = 0; i < 360; i++) {
			if (i == 0) {
				round.add(new double[] { x + r, y });
			}
			if (i == 90) {
				round.add(new double[] { x, y - r });
			}
			if (i == 180) {
				round.add(new double[] { x - r, y });
			}
			if (i == 270) {
				round.add(new double[] { x, y + r });
			}
			if (i >= 1 && i <= 89) {
				round.add(new double[] { x + r * MathData.cos[i],
						y - r * MathData.sin[i] });
			}
			if (i >= 91 && i <= 179) {
				round.add(new double[] { x - r * MathData.sin[i - 90],
						y - r * MathData.cos[i - 90] });
			}
			if (i >= 181 && i <= 269) {
				round.add(new double[] { x - r * MathData.cos[i - 180],
						y + r * MathData.sin[i - 180] });
			}
			if (i >= 271 && i <= 359) {
				round.add(new double[] { x + r * MathData.sin[i - 270],
						y + r * MathData.cos[i - 270] });
			}
		}
		int a[][] = new int[round.size()][2];
		for (int i = 0; i < a.length; i++) {
			a[i][0] = (int) round.get(i)[0];
			a[i][1] = (int) round.get(i)[1];
		}
		round.clear();
		round = null;
		return a;
	}

	private double x, y;
	private double hyp;
	private double cos, rad, deg;

	/** �ߵĽǶ� */
	public int lineAngle(double x1, double y1, double x2, double y2) {
		x = x2 - x1;
		y = y2 - y1;
		hyp = Math.sqrt(x * x + y * y);
		cos = x / hyp;
		rad = Math.acos(cos);// �ó����ǻ���
		deg = 180 * rad / Math.PI;// ����ת��Ϊ�Ƕ�
		if (y < 0) {
			deg = 360 - deg;
		}
		return (int) deg;
	}

	/** һ���㰴һ���Ƕ��ƶ� [0]x [1]y [2]�Ƕ� [3]���� */
	public int[] pointMove(int x, int y, int angle, int r) {
		if (angle >= 360) {
			angle -= 360;
		}

		if (angle < 0) {
			angle += 360;
		}

		double x2, y2;
		int[] point = new int[2];
		if (angle == 0) {
			point[0] = x + r;
			point[1] = y;
			return point;
		}

		if (angle == 180) {
			point[0] = x - r;
			point[1] = y;
			return point;
		}

		if (angle == 90) {
			point[0] = x;
			point[1] = y + r;
			return point;
		}

		if (angle == 270) {
			point[0] = x;
			point[1] = y - r;
			return point;
		}

		double tan = MathData.tan[angle];
		if (angle > 90 && angle < 270) {
			x2 = x - r / (Math.sqrt(tan * tan + 1));
		} else {
			x2 = r / (Math.sqrt(tan * tan + 1)) + x;
		}
		y2 = tan * (x2 - x) + y;
		point[0] = (int) x2;
		point[1] = (int) y2;
		return point;
	}
}
