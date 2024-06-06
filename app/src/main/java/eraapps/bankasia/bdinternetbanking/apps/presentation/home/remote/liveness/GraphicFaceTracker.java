package eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.liveness;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;

import eraapps.bankasia.bdinternetbanking.apps.R;
import eraapps.bankasia.bdinternetbanking.apps.util.GlobalVariable;


class GraphicFaceTracker extends Tracker<Face> implements SensorEventListener, View.OnClickListener {

    private static final float OPEN_THRESHOLD = 0.85f;
    private static final float CLOSE_THRESHOLD = 0.4f;
    public final CapturePhotoLivenessActivity mainActivity;
    private int state = 0;
    private int blink = 0;
    private static final int FORCE_THRESHOLD = 100;
    private static final int TIME_THRESHOLD = 100;
    private static final int SHAKE_TIMEOUT = 100;
    private static final int SHAKE_DURATION = 50;
    private static final int SHAKE_COUNT = 1;
    TextView txtHint3;
    TextView txtHint2;
    TextView txtHint;
    Button btnNext;
    Button btnTry;
    LinearLayout llCapturing;
    GlobalVariable globalVariable;
    Button btnLanguageChange;
    ToolDotProgress dots_progress;
    private SensorManager mSensorMgr;
    private float mLastX = -1.0f, mLastY = -1.0f, mLastZ = -1.0f;
    private long mLastTime;
    private OnShakeListener mShakeListener;
    private Context mContext;
    private int mShakeCount = 0;
    private long mLastShake;
    private long mLastForce;
    private Toast toastMessage;
    private boolean capture = false;

    @Override
    public void onClick(View v) {

    }

    public interface OnShakeListener {
        public void onShake();
    }

    public void ShakeListener(Context context) {

        // Log.d("XXX","ShakeListener invoked---->");
        mContext = context;
        resume();
    }

    public void setOnShakeListener(OnShakeListener listener) {
        //  Log.d("XXX","ShakeListener setOnShakeListener invoked---->");
        mShakeListener = listener;
    }

    public void resume() {
        mSensorMgr = (SensorManager) mContext
                .getSystemService(Context.SENSOR_SERVICE);
        if (mSensorMgr == null) {
            throw new UnsupportedOperationException("Sensors not supported");
        }
        boolean supported = false;
        try {
            supported = mSensorMgr.registerListener(this,
                    mSensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                    SensorManager.SENSOR_DELAY_GAME);
        } catch (Exception e) {
            // Toast.makeText(mContext, "Shaking not supported", Toast.LENGTH_LONG).show();
        }

        if ((!supported) && (mSensorMgr != null))
            mSensorMgr.unregisterListener(this);
    }

    public void pause() {
        if (mSensorMgr != null) {

            mSensorMgr.unregisterListener(this);
            mSensorMgr = null;
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() != Sensor.TYPE_ACCELEROMETER)
            return;
        long now = System.currentTimeMillis();

        if ((now - mLastForce) > SHAKE_TIMEOUT) {
            mShakeCount = 0;
        }

        if ((now - mLastTime) > TIME_THRESHOLD) {
            long diff = now - mLastTime;
            float speed = Math.abs(event.values[SensorManager.DATA_X]
                    + event.values[SensorManager.DATA_Y]
                    + event.values[SensorManager.DATA_Z] - mLastX - mLastY
                    - mLastZ)
                    / diff * 10000;
            if (speed > FORCE_THRESHOLD) {
                if ((++mShakeCount >= SHAKE_COUNT) && (now - mLastShake > SHAKE_DURATION)) {
                    mLastShake = now;
                    mShakeCount = 0;
                    // Log.d("XXX","ShakeListener mShakeListener---->"+mShakeListener);
                    blink = 0;
                    if (toastMessage == null) {
                        if (capture) {
                        } else {
                           /* toastMessage = Toast.makeText(mContext, "Do not shake your phone", Toast.LENGTH_SHORT);
                            toastMessage.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 350);
                            toastMessage.show();*/
                        }
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                toastMessage = null;
                            }
                        }, 1000);
                    }
                    if (mShakeListener != null) {
                        mShakeListener.onShake();
                    }
                }
                mLastForce = now;
            }
            mLastTime = now;
            mLastX = event.values[SensorManager.DATA_X];
            mLastY = event.values[SensorManager.DATA_Y];
            mLastZ = event.values[SensorManager.DATA_Z];
        }
    }

    public GraphicFaceTracker(CapturePhotoLivenessActivity mainActivity) {
        this.mainActivity = mainActivity;
        ShakeListener(this.mainActivity);

        btnNext = (Button) this.mainActivity.findViewById(R.id.btnNext);
        btnTry = (Button) this.mainActivity.findViewById(R.id.btnTry);
        txtHint3 = (TextView) this.mainActivity.findViewById(R.id.txtHint3);
        txtHint2 = (TextView) this.mainActivity.findViewById(R.id.txtHint2);
        txtHint = (TextView) this.mainActivity.findViewById(R.id.txtHint);
        // btnLanguageChange = (Button) this.mainActivity.findViewById(R.id.btnLanguageChange);
        llCapturing = (LinearLayout) this.mainActivity.findViewById(R.id.llCapturing);
        globalVariable = ((GlobalVariable) this.mainActivity.getApplicationContext());
        dots_progress = this.mainActivity.findViewById(R.id.dots_progress);
        //translet();
        /*btnLanguageChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (globalVariable.getLanguageBool()) {
                    globalVariable.setLanguageBool(false);
                    globalVariable.setLanguageCode("BAN");
                    translet();
                } else {
                    globalVariable.setLanguageBool(true);
                    globalVariable.setLanguage("ENG");
                    translet();
                }
            }
        });*/
    }

   /* private void translet() {
        if (globalVariable.getLanguage().equals("BAN")) {
            btnLanguageChange.setText("ENGLISH");
            txtHint.setText("আপনার মুখটি ক্যামেরায় রাখুন");
            txtHint2.setText("ছবি তুলতে কয়েক বার আপনার চোখের পলক ফেলুন");
            btnNext.setText("পরবর্তী");
            btnTry.setText("আবার চেষ্টা করুন");
        } else {
            btnLanguageChange.setText("বাংলা");
            txtHint.setText("Keep your face into the camera");
            txtHint2.setText("Blink your eyes several times");
            btnNext.setText("Next");
            btnTry.setText("Try Again");
        }
    }*/

    private void blink(float value) {
        switch (state) {
            case 0:
                if (value > OPEN_THRESHOLD) {
                    // Both eyes are initially open
                    state = 1;
                }
                break;
            case 1:
                if (value < CLOSE_THRESHOLD) {
                    // Both eyes become closed
                    state = 2;
                }
                break;
            case 2:
                if (value > OPEN_THRESHOLD) {
                    // Both eyes are open again
                    // Log.i("Camera Demo", "blink has occurred!");
                    state = 0;

                    blink++;
                    // Log.d("XXX"+blink,blink+"");
                    if (blink == 2) {
                        //llCapturing.setVisibility(View.VISIBLE);

                    }
                    if (blink > 2) {
                        //toastMessage= Toast.makeText(mContext, "Please wait to capture an image", Toast.LENGTH_SHORT);
                        //toastMessage.show();
                        blink = 0;
                        ((Activity) this.mainActivity).runOnUiThread(new Runnable() {
                            public void run() {
                                dots_progress.setVisibility(View.VISIBLE);

                                if (globalVariable.getLanguageCode().equals("BAN")) {
                                    // btnLanguageChange.setText("ENGLISH");

                                    txtHint3.setText("অনুগ্রহ করে অপেক্ষা করুন, ক্যামেরা একটি ফটো ক্যাপচার করছে");
                                } else {
                                    //btnLanguageChange.setText("বাংলা");
                                    txtHint3.setText("Please wait, the camera is  capturing an image");
                                }
                                // txtHint3.setText("Please wait, the camera is  capturing an image");
                                txtHint2.setText("");
                                txtHint.setText("");
                            }
                        });
                        mainActivity.captureImage();
                        capture = true;

                        /*Handler handler = new Handler(getMainLooper());
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                Log.d("XXX insides2",blink+"");
                            }
                        }, 500);*/
                        // Toast.makeText(mContext, "Capture", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
            default:
                break;
        }
    }

    /**
     * Update the position/characteristics of the face within the overlay.
     */
    @Override
    public void onUpdate(FaceDetector.Detections<Face> detectionResults, Face face) {
        float left = face.getIsLeftEyeOpenProbability();
        float right = face.getIsRightEyeOpenProbability();
        if ((left == Face.UNCOMPUTED_PROBABILITY) ||
                (right == Face.UNCOMPUTED_PROBABILITY)) {
            // One of the eyes was not detected.
            return;
        }

        float value = Math.min(left, right);
        blink(value);
    }
}