package com.wg.wxrzz;

import java.util.HashMap;
import java.util.Map.Entry;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

import com.wg.wxrzz.R;

public class Player {
	/** ��Ƶ�ͷ�״̬ */
	public static int DIS = 0;
	/** ��Ƶ����״̬ */
	public static int LOAD = 1;

	// =======================

	/** ��ť */
	public static int AU_0 = 0;
	/** ��ը */
	public static int AU_1 = 1;
	/** ��� */
	public static int AU_2 = 2;
	/** ʤ�� */
	public static int AU_3 = 3;
	MediaPlayer mup;// ��������
	MediaPlayer mup1;// �˵�
	MediaPlayer mup2;// ��Ϸ��
	MediaPlayer mup3;// �̵�
	SoundPool aup;// ������Ч
	HashMap<Integer, Integer> aupm;// ��ЧID����

	private int muauStatus = DIS;

	private int which;

	/**
	 * ���ֲ���
	 */
	public void mupStart() {
		switch (which) {
		case 0:
			mup = mup1;
			break;
		case 1:
			mup = mup2;
			break;
		case 2:
			mup = mup3;
			break;
		}
		if (muauStatus == LOAD) {
			if (mup != null && !mup.isPlaying()) {
				mup.start();
			}
		}
	}

	/**
	 * ���ֲ���
	 */
	public void mupStart(int i) {
		if (mup != null && mup.isPlaying()) {
			mup.pause();
		}
		switch (i) {
		case 0:
			mup = mup1;
			which = 0;
			break;
		case 1:
			mup = mup2;
			which = 1;
			break;
		case 2:
			mup = mup3;
			which = 2;
			break;
		}
		if (muauStatus == LOAD) {
			if (mup != null && !mup.isPlaying()) {
				mup.start();
			}
		}
	}

	/**
	 * ����ֹͣ
	 */
	public void mupStop() {
		if (muauStatus == LOAD) {
			if (mup != null && mup.isPlaying()) {
				mup.stop();
			}
		}
	}

	/**
	 * ��Ч����
	 */
	public void aupStart(int auid) {
		if (muauStatus == LOAD) {
			if (aup != null) {
				AudioManager mgr = (AudioManager) WGActivity.activity
						.getSystemService(Context.AUDIO_SERVICE);
				float streamVolumeCurrent = mgr
						.getStreamVolume(AudioManager.STREAM_MUSIC);
				float streamVolumeMax = mgr
						.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
				float volume = streamVolumeCurrent / streamVolumeMax;
				aup.play(aupm.get(auid), volume, volume, 1, 0, 1f);// ��������
			}
		}
	}

	/**
	 * ��Чֹͣ
	 */
	public void aupStop(int auid) {
		if (muauStatus == LOAD) {
			if (aup != null) {
				aup.pause(aupm.get(auid));
			}
		}
	}

	/**
	 * ��Чֹͣ
	 */
	public void aupStopAll() {
		if (muauStatus == LOAD) {
			for (Entry<Integer, Integer> set : aupm.entrySet()) {
				aup.stop(set.getValue());
			}
		}
	}

	/**
	 * ��ʼ����Ƶ
	 */
	public void loadMAData() {
		if (muauStatus == DIS) {
			mup = MediaPlayer.create(WGActivity.activity, R.raw.mu0);
			mup.setLooping(true);// ѭ������
			mup1 = MediaPlayer.create(WGActivity.activity, R.raw.mu0);
			mup1.setLooping(true);// ѭ������
			mup2 = MediaPlayer.create(WGActivity.activity, R.raw.mu0);
			mup2.setLooping(true);// ѭ������
			mup3 = MediaPlayer.create(WGActivity.activity, R.raw.mu0);
			mup3.setLooping(true);// ѭ������
			aup = new SoundPool(6, AudioManager.STREAM_MUSIC, 10);
			aupm = new HashMap<Integer, Integer>();
			aupm.put(AU_0, aup.load(WGActivity.activity, R.raw.au0, 1));
			aupm.put(AU_1, aup.load(WGActivity.activity, R.raw.au1, 1));
			aupm.put(AU_2, aup.load(WGActivity.activity, R.raw.au2, 1));
			aupm.put(AU_3, aup.load(WGActivity.activity, R.raw.au3, 1));
			muauStatus = LOAD;
		}
	}

	/**
	 * �ͷ���Ƶ
	 */
	public void disMAData() {
		if (muauStatus == LOAD) {
			mupStop();
			aupStopAll();
			mup = null;
			mup1 = null;
			mup2 = null;
			mup3 = null;
			aup = null;
			aupm.clear();
			aupm = null;
			muauStatus = DIS;
		}
	}

	// ===================================================

	public static Player muaup = new Player();

	public Player() {
		muaup = this;
	}

}
