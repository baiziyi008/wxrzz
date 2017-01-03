package com.wg.wxrzz;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Pic {
	private HashMap image = new HashMap();
	private ArrayList index = new ArrayList();
	private ArrayList<Long> skip = new ArrayList<Long>();
	private ArrayList<Integer> length = new ArrayList<Integer>();
	public static int[] index1 = new int[] { 1, 10, 11, 12, 13, 14, 15, 16, 17,
			18, 19, 2, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 3, 30, 31, 32,
			33, 34, 35, 36, 37, 38, 39, 4, 40, 41, 42, 43, 44, 45, 46, 47, 48,
			49, 5, 50, 51, 52, 53, 54, 6, 7, 8, 9 };
	public static int[] length1 = new int[] { 12282, 3903, 17063, 11908, 7363,
			46448, 2275, 4189, 4232, 227, 4114, 704, 246, 3757, 123, 261, 98,
			4572, 998, 40798, 33970, 12320, 76966, 21143, 13130, 14445, 23839,
			178, 879, 3081, 37583, 6363, 4216, 51237, 7281, 16479, 2146, 474,
			13938, 7912, 578, 8310, 8168, 5859, 16476, 1577, 1138, 1651, 2121,
			17284, 37184, 82593, 61734, 4030 };
	public static long[] skip1 = new long[] { 0, 12282, 16185, 33248, 45156,
			52519, 98967, 101242, 105431, 109663, 109890, 114004, 114708,
			114954, 118711, 118834, 119095, 119193, 123765, 124763, 165561,
			199531, 211851, 288817, 309960, 323090, 337535, 361374, 361552,
			362431, 365512, 403095, 409458, 413674, 464911, 472192, 488671,
			490817, 491291, 505229, 513141, 513719, 522029, 530197, 536056,
			552532, 554109, 555247, 556898, 559019, 576303, 613487, 696080,
			757814 };
	public static Pic P = new Pic();

	public Pic() {
		for (int i = 0; i < index1.length; i++) {
			index.add(index1[i]);
		}
		for (int i = 0; i < skip1.length; i++) {
			skip.add(skip1[i]);
		}
		for (int i = 0; i < length1.length; i++) {
			length.add(length1[i]);
		}
		P = this;
	}

	public Bitmap getBitmap(int index) {
		if (image.containsKey(index)) {
			return (Bitmap) image.get(index);
		}
		return null;
	}

	public void loadImage(ArrayList tem1, ArrayList tem2) {
		if (MS.isSecret) {
			if (tem1 != null) {
				for (int i = 0; i < tem1.size(); i++) {
					if (!image.containsKey(tem1.get(i))) {
						InputStream in = null;

						try {
							in = MS.resources.getAssets().open(
									"Data/" + "data.txt");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						int index = this.index.indexOf(tem1.get(i));
						int len = length.get(index);
						byte[] tem = new byte[len];
						try {
							in.skip(skip.get(index));
							in.read(tem, 0, len);
							in.close();
							in = null;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						image.put(tem1.get(i),
								BitmapFactory.decodeByteArray(tem, 0, len));
						// image.put(tem1.get(i),
						// createImage((Integer) tem1.get(i), ".wg"));
					}
				}
			}

			if (tem2 != null) {
				for (int i = 0; i < tem2.size(); i++) {
					if (!image.containsKey(tem2.get(i))) {
						InputStream in = null;
						try {
							in = MS.resources.getAssets().open(
									"Data/" + "data.txt");
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						int index = this.index.indexOf(tem2.get(i));
						int len = length.get(index);
						byte[] tem = new byte[len];
						try {
							in.skip(skip.get(index));
							in.read(tem, 0, len);
							in.close();
							in = null;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						image.put(tem2.get(i),
								BitmapFactory.decodeByteArray(tem, 0, len));
						// image.put(tem2.get(i),
						// createImage((Integer) tem2.get(i), ".wg"));
					}
				}
			}
		} else {
			if (tem1 != null) {
				for (int i = 0; i < tem1.size(); i++) {
					if (!image.containsKey(tem1.get(i))) {
						image.put(tem1.get(i),
								createImage((Integer) tem1.get(i), ".png"));
					}
				}
			}

			if (tem2 != null) {
				for (int i = 0; i < tem2.size(); i++) {
					if (!image.containsKey(tem2.get(i))) {
						image.put(tem2.get(i),
								createImage((Integer) tem2.get(i), ".jpg"));
					}
				}
			}

		}
	}

	public void disImage(ArrayList tem1, ArrayList tem2) {
		if (tem1 != null) {
			for (int i = 0; i < tem1.size(); i++) {
				if (image.containsKey(tem1.get(i))) {
					Bitmap tem = (Bitmap) image.get(tem1.get(i));
					tem.recycle();
					tem = null;
					image.remove(tem1.get(i));
				}
			}
		}

		if (tem2 != null) {
			for (int i = 0; i < tem2.size(); i++) {
				if (image.containsKey(tem2.get(i))) {
					Bitmap tem = (Bitmap) image.get(tem2.get(i));
					tem.recycle();
					tem = null;
					image.remove(tem2.get(i));
				}
			}
		}
		System.gc();
	}

	private Bitmap createImage(int index, String type) {
		try {
			return BitmapFactory.decodeStream(MS.resources.getAssets().open(
					"image/" + index + type));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
