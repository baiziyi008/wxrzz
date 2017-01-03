package com.wg.wxrzz;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;

public class WGActivity extends Activity {

	/** ��Ϸ��Ļ */
	public GameCanvas gameCanvas;
	/** �˵���Ļ */
	public GameMenuCanvas gameMenuCanvas;
	public LoadingCanvas loadingCanvas;
	/** ������� */
	public static WGActivity activity;

	/**
	 * ���캯��
	 */
	public WGActivity() {
		activity = this;
		Eff.eff.init();
	}

	/**
	 * ��ʼ����������
	 */
	private void initTData() {
		MS.setResources(getResources());
		MS.screen = Bitmap.createBitmap(Paper.w_fixed, Paper.h_fixed,
				Config.RGB_565);
	}

	/**
	 * ��ʼ����Դ
	 */
	private void initData() {
		if (loadingCanvas == null) {
			loadingCanvas = new LoadingCanvas();
		}
		if (gameMenuCanvas == null) {
			gameMenuCanvas = new GameMenuCanvas();
		}
		if (gameCanvas == null) {
			gameCanvas = new GameCanvas();
		}
	}

	/**
	 * LOGO����Դ���봦��
	 */
	public Runnable rb1 = new Runnable() {
		public void run() {
			refData();
			MS.isLoading = false;
		}
	};

	/**
	 * MENU����Դ���봦��
	 */
	public Runnable rb2 = new Runnable() {
		public void run() {
			refData();
			MS.isLoading = false;
		}
	};

	/**
	 * GAME����Դ���봦��
	 */
	public Runnable rb3 = new Runnable() {
		public void run() {
			refData();
			MS.isLoading = false;
		}
	};
	public Runnable rb4 = new Runnable() {
		public void run() {
			refData();
			MS.isLoading = false;
		}
	};

	private void disData() {
		switch (MS.oldGMS) {
		case MS.GMS_1:
			gameMenuCanvas.disingData();
			break;
		case MS.GMS_2:
			gameCanvas.disingData();
			break;
		default:
			break;
		}
	}

	/**
	 * ��Դ�����ۺϹ���
	 */
	private void refData() {
		switch (MS.GMS) {
		case MS.GMS_1:
			gameMenuCanvas.loadingData();
			break;
		case MS.GMS_2:
			gameCanvas.loadingData();
			break;
		default:
			break;
		}
	}

	/**
	 * LOGO����
	 */
	public void showGameLogoCanvas() {
		MS.isLoading = true;
		MS.oldGMS = MS.GMS;
		MS.GMS = MS.GMS_0;
		showLoadingCanvas();
		new Thread(rb1).start();
	}

	/**
	 * �˵�����(���˵�����)
	 */
	public void showGameMenuCanvas() {
		MS.isLoading = true;
		MS.oldGMS = MS.GMS;
		MS.GMS = MS.GMS_1;
		showLoadingCanvas();
		new Thread(rb2).start();
	}

	/**
	 * ����Ϸ����
	 */
	public void showGameCanvas() {
		MS.isLoading = true;
		MS.oldGMS = MS.GMS;
		MS.GMS = MS.GMS_2;
		showLoadingCanvas();
		new Thread(rb3).start();
	}

	/**
	 * �̵����
	 */
	public void showShopCanvas() {
		MS.isLoading = true;
		MS.oldGMS = MS.GMS;
		MS.GMS = MS.GMS_3;
		showLoadingCanvas();
		new Thread(rb4).start();
	}

	/**
	 * SHOW �ȴ�����
	 */
	public void showLoadingCanvas() {
		loadingCanvas.show();
		disData();
	}

	/**
	 * ����
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MS.isAlive = true;
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		GameView.cv = new GameView(this);
		GameView.cv.setFocusable(true);
		GameView.cv.setFocusableInTouchMode(true);
		getWindow().setBackgroundDrawable(null);
///		initSMS();
	}

	public static VideoView videoView;

	/**
	 * ��ʾ�������
	 */
	private void showViewCtrl() {
		showMainView();
	}

	/**
	 * ��ʾ������
	 */
	private void showMainView() {
		setContentView(GameView.cv);
		isStarted = true;
		initTData();
		GameView.cv.init();
		initData();
		showGameMenuCanvas();
	}

	private boolean isStarted = false;

	/**
	 * ��ʼ
	 */
	protected void onStart() {
		super.onStart();
		if (!isStarted) {
			showViewCtrl();
		}
	}

	/**
	 * ����
	 */
	protected void onResume() {
		super.onResume();
		if (MS.IS_SoundMU && MS.IS_SoundAU) {
			Player.muaup.loadMAData();
			Player.muaup.mupStart();
		}
	}

	/**
	 * ��ͣ
	 */
	protected void onPause() {
		super.onPause();
		if (gameCanvas != null)
			gameCanvas.pauseGame();
		Player.muaup.mupStop();
		Player.muaup.aupStopAll();
		Player.muaup.disMAData();
	}

	/**
	 * ֹͣ
	 */
	protected void onStop() {
		super.onStop();
	}

	/**
	 * �ͷ�
	 */
	protected void onDestroy() {
		super.onDestroy();
		disApp();
	}

	/**
	 * �����ͷų���
	 */
	private void disApp() {
		MS.isAlive = false;
		Player.muaup.disMAData();
		MS.screen = null;
		GameView.cv.destroyDrawingCache();
		GameView.cv = null;
		System.exit(0);
		MS.oldGMS = -1;
		MS.GMS = -1;
	}

	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// �жϰ��µļ��Ƿ��ؼ�.
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			switch (MS.GMS) {
			case MS.GMS_1:
				new AlertDialog.Builder(this)
						.setTitle("��ʾ")
						.setMessage("ȷ���˳���Ϸ��")
						.setPositiveButton("ȷ��",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
										// �����ȷ�ϡ���Ĳ���
										finish();

									}
								})
						.setNegativeButton("����",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface dialog,
											int which) {
									}
								}).show();
				break;
			case MS.GMS_2:
				gameCanvas.pauseGame();
				break;
			default:
				break;
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);

	}

	// *****************************************
//	public Purchase purchase;
//	private String code[] = { "30000888349708", "30000888349709",
//			"30000888349710", "30000888349711", "30000888349712",
//			"30000888349713", "30000888349714", "30000888349714",
//			"30000888349714", "30000888349714", "30000888349714" };
//	// �Ʒ���Ϣ
//	// �Ʒ���Ϣ (��������)
//	private final String APPID = "300008883497";
//	private final String APPKEY = "3B678E8AB7328761144C7BC62F729ED1";
//	// �Ʒѵ���Ϣ
//	private IAPListener mListener;
//	private final String PAYCODE = "Paycode";
//
//	private void initSMS() {
//		IAPHandler iapHandler = new IAPHandler(activity);
//		mListener = new IAPListener(activity, iapHandler);
//		/**
//		 * step2.��ȡPurchaseʵ����
//		 */
//		purchase = Purchase.getInstance();
//		/**
//		 * step3.��Purhase����Ӧ����Ϣ��APPID��APPKEY�� ��Ҫ�������APPID��APPKEY�� APPID�����������ĵ�
//		 * APPKEY�����������ĵ�
//		 */
//		try {
//			purchase.setAppInfo(APPID, APPKEY);
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
//		/**
//		 * step4. IAP�����ʼ����ʼ�� ����PurchaseListener����ʼ�������贫��step1ʱʵ������
//		 * PurchaseListener��
//		 */
//		try {
//			purchase.init(activity, mListener);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public int buystatus;
	public Handler buygold = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			buystatus = msg.what;
			///order(code[buystatus]);
			order();
		}
	};

	private static int moneynum[] = { 5, 10, 15, 20, 25, 40, 70, 100 };
	public void onBillingFinishFree(){
		int tem = this.buystatus;
		switch (tem) {
		case 0:
			this.gameCanvas.hasbuy = 1;
			DB.db.setGameData(6, 1);
			DB.db.saveDB();
			break;
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
			this.gameCanvas.boomcount += moneynum[tem - 1];
			break;
		}
	}
	public void order() {
		try {
			//purchase.order(activity, code, mListener);
			onBillingFinishFree();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int count[] = { 5, 10, 15, 20, 25, 40, 70, 100 };
	private String money[] = { "2", "4", "5", "6", "8", "10" };

	public Handler buyinfo = new Handler(new Handler.Callback() {
		@Override
		public boolean handleMessage(Message msg) {
			buystatus = msg.what;
			String info = "����";
			info += count[buystatus - 1];
			info += "��ը������,��Ҫ";
			info += money[buystatus - 1];
			info += "Ԫ,�Ƿ�ȷ������?";
			AlertDialog mDialog = new AlertDialog.Builder(activity).create();
			mDialog.setButton("����", new AlertDialog.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Message message = new Message();
					message.what = buystatus;
					WGActivity.activity.buygold.sendMessage(message);
				}
			});

			mDialog.setButton2("ȡ��", new AlertDialog.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
				}
			});
			mDialog.setTitle("������ʾ");
			mDialog.setMessage(info);
			mDialog.show();
			return true;
		}
	});
	public Handler buyinfo1 = new Handler(new Handler.Callback() {
		@Override
		public boolean handleMessage(Message msg) {
			buystatus = msg.what;
			String info = "�������Ϸ��Ҫ0.1Ԫ���Ƿ�ȷ�����";
			AlertDialog mDialog = new AlertDialog.Builder(activity).create();
			mDialog.setButton("����", new AlertDialog.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Message message = new Message();
					message.what = buystatus;
					WGActivity.activity.buygold.sendMessage(message);
				}
			});

			mDialog.setButton2("ȡ��", new AlertDialog.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
				}
			});
			mDialog.setTitle("������ʾ");
			mDialog.setMessage(info);
			mDialog.show();
			return true;
		}
	});
}