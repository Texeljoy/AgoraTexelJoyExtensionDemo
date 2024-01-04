package io.agora.rte.extension.hteffect.example;

import android.Manifest;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.Observable;
import androidx.databinding.ObservableBoolean;

import org.json.JSONException;
import org.json.JSONObject;

import io.agora.rtc2.Constants;
import io.agora.rtc2.IMediaExtensionObserver;
import io.agora.rtc2.IRtcEngineEventHandler;
import io.agora.rtc2.RtcEngine;
import io.agora.rtc2.RtcEngineConfig;
import io.agora.rtc2.video.VideoCanvas;

import java.util.Arrays;


public class MainActivity
    extends AppCompatActivity implements IMediaExtensionObserver {

    private static final String TAG = "DEBUG";

    private RtcEngine mRtcEngine;

    private Button button;
    private Button buttonStyleFilters;
    private Button buttonStickers;
    private TextView tvFaceNumber;
    private int faceNumber;


    private RelativeLayout unityView;
    private final ObservableBoolean enableExtension =
        new ObservableBoolean(false);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       initUI();
       initData();
       initPermission();
    }

    private void initData() {
        enableExtension.addOnPropertyChangedCallback(
            new Observable.OnPropertyChangedCallback() {
                @Override
                public void onPropertyChanged(Observable sender, int propertyId) {
                    if (sender instanceof ObservableBoolean) {
                        boolean enabled = ((ObservableBoolean) sender).get();
                        Log.i("DEBUG: ", "enableExtension");
                        enableExtension(enabled);

                        if (enabled) {
                            button.setText(R.string.disable_extension);
                            buttonStyleFilters.setEnabled(true);
                            buttonStickers.setEnabled(true);


                        } else {
                            button.setText(R.string.enable_extension);
                            buttonStyleFilters.setEnabled(false);
                            buttonStickers.setEnabled(false);


                        }
                    }
                }
            });
    }

    private void initUI() {

        button = findViewById(R.id.button_enable);
        button.setOnClickListener(
            view -> enableExtension.set(!enableExtension.get()));
        tvFaceNumber = findViewById(R.id.tv_facenumber);
        buttonStyleFilters = findViewById(R.id.button_style_filters);
        buttonStyleFilters
            .setOnClickListener(view -> choiceStyleFilter());
        buttonStickers = findViewById(R.id.button_stickers);
        buttonStickers
            .setOnClickListener(view -> choiceSticker());


    }

    private void initExtension() {
        try {
            //在线鉴权方式(online authentication)
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("appId", HtKey.HT_APPID);
            setExtensionProperty("htInitHTEffectOnline", jsonObject.toString());
            //离线鉴权方式(offline authentication)
            // JSONObject jsonObject = new JSONObject();
            // jsonObject.put("license", HtKey.HT_LICENSE);
            // setExtensionProperty("htInitHTEffectOffline", jsonObject.toString());


        } catch (JSONException e) {
            Log.e(TAG, e.toString());
        }
    }
    private void choiceStyleFilter() {

        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type",0);
            jsonObject.put("name","ziran3");
            setExtensionProperty("htSetFilter", jsonObject.toString());


            buttonStyleFilters.setEnabled(false);
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
        }
    }
    private void choiceSticker() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type",0);
            jsonObject.put("name","ht_sticker_effect_rabbit_bowknot");
            setExtensionProperty("htSetARItem", jsonObject.toString());

            buttonStickers.setEnabled(false);
        } catch (JSONException e) {
            Log.e(TAG, e.toString());
        }
    }

    private void updateAITrackingResult(int faces) {
        runOnUiThread(() -> {
            String result = "faces: " + faces;
            tvFaceNumber.setText(result);
        });
    }
    private void enableExtension(boolean enabled) {
        mRtcEngine.enableExtension("Texeljoy", "HTEffect", enabled);
    }

    private void setExtensionProperty(String key, String property) {
        mRtcEngine.setExtensionProperty("Texeljoy", "HTEffect", key, property);
    }


    private void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[] { Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO },
                0);
        } else {
            Log.i("DEBUG: ", "initRtcEngine");
            initRtcEngine();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0) {
            if (Arrays.equals(grantResults, new int[] { 0, 0 })) {
                initRtcEngine();
            }
        }
    }


    private void initRtcEngine() {
        RtcEngineConfig config = new RtcEngineConfig();
        config.mContext = getApplicationContext();
        config.mAppId = io.agora.rte.extension.hteffect.example.Constants.mAppId;
        config.mExtensionObserver = this;
        config.addExtension("AgoraTexelJoyExtension");
        config.mEventHandler = new IRtcEngineEventHandler() {
            @Override
            public void onWarning(int warn) {
                Log.w(TAG, String.format("onWarning %d", warn));
            }

            @Override
            public void onError(int err) {
                Log.e(TAG, String.format("onError %d", err));
            }

            @Override
            public void onJoinChannelSuccess(String channel, int uid, int elapsed) {
                Log.i(TAG, String.format("onJoinChannelSuccess %s %d %d", channel, uid, elapsed));
            }
            @Override
            public void onLocalVideoStateChanged(Constants.VideoSourceType source, int state, int error) {
                super.onLocalVideoStateChanged(source, state, error);
                Log.i(TAG, String.format("onLocalVideoStateChanged %s %d %d", source.toString(), state, error));
            }
        };
        try {
            mRtcEngine = RtcEngine.create(config);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        if (mRtcEngine == null) {
            return;
        }
        enableExtension.set(true);
        mRtcEngine.enableVideo();
        mRtcEngine.setChannelProfile(Constants.CHANNEL_PROFILE_LIVE_BROADCASTING);

        mRtcEngine.setClientRole(Constants.CLIENT_ROLE_BROADCASTER);
        initExtension();
        VideoCanvas canvas = new VideoCanvas(findViewById(R.id.surfaceView));
        mRtcEngine.setupLocalVideo(canvas);
        mRtcEngine.startPreview();

    }


    @Override public void onEvent(String provider, String extension, String key, String value) {
        Log.d(TAG, "onEvent provider: " + provider + "  extension: " + extension + "  key: " + key + "  value: " + value);
        try {
            JSONObject jsonObject = new JSONObject(value);
            if ("htIsTracking".equals(key)) {
            faceNumber = jsonObject.getInt("faceNumber");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        updateAITrackingResult(faceNumber);

    }

    @Override public void onStarted(String provider, String extension) {

    }

    @Override public void onStopped(String provider, String extension) {

    }

    @Override public void onError(String provider, String extension, int errCode, String errMsg) {

    }
}