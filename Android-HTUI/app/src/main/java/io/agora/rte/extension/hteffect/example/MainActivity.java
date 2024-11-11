package io.agora.rte.extension.hteffect.example;

import android.Manifest;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.Observable;
import androidx.databinding.ObservableBoolean;
import com.texeljoy.ht_effect.HTPanelLayout;
import io.agora.rtc2.IMediaExtensionObserver;
import io.agora.rtc2.IRtcEngineEventHandler;
import io.agora.rtc2.RtcEngine;
import io.agora.rtc2.RtcEngineConfig;
import java.io.File;
import java.util.Arrays;
import javax.security.auth.PrivateCredentialPermission;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements IMediaExtensionObserver {
    private Context applicationContext;
    private static final String TAG = "DEBUG";

    private RtcEngineManager rtcEngineManager;
    // private Button button;
    // private Button buttonStyleFilters;
    // private Button buttonStickers;
    // private TextView tvFaceNumber;
    // private int faceNumber;
    private final ObservableBoolean enableExtension = new ObservableBoolean(false);
    public Context getContext() {
        return applicationContext;
    }
    public static String getResourcePath(Context context) {
        String path = null;
        File dataDir = context.getApplicationContext().getFilesDir();
        if (dataDir != null) {
            path = dataDir.getAbsolutePath() + File.separator + "hteffect";
        }
        return path;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("getResourcePath", "onCreate: "+getResourcePath(this));
        // initUI();
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
                        Log.i(TAG, "enableExtension: " + enabled);
                        if (rtcEngineManager != null) {
                            rtcEngineManager.enableExtension(enabled);
                        }

                        // if (enabled) {
                        //     button.setText(R.string.disable_extension);
                        //     buttonStyleFilters.setEnabled(true);
                        //     buttonStickers.setEnabled(true);
                        // } else {
                        //     button.setText(R.string.enable_extension);
                        //     buttonStyleFilters.setEnabled(false);
                        //     buttonStickers.setEnabled(false);
                        // }
                    }
                }
            });
    }

    // private void initUI() {
    //     button = findViewById(R.id.button_enable);
    //     button.setOnClickListener(view -> enableExtension.set(!enableExtension.get()));
    //
    //     tvFaceNumber = findViewById(R.id.tv_facenumber);
    //     buttonStyleFilters = findViewById(R.id.button_style_filters);
    //     buttonStyleFilters.setOnClickListener(view -> choiceStyleFilter());
    //
    //     buttonStickers = findViewById(R.id.button_stickers);
    //     buttonStickers.setOnClickListener(view -> choiceSticker());
    // }

    private void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[] { Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO }, 0);
        } else {
            Log.i(TAG, "initRtcEngine");
            initRtcEngine();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
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
            public void onJoinChannelSuccess(String channel, int uid, int elapsed) {
                Log.i(TAG, String.format("onJoinChannelSuccess %s %d %d", channel, uid, elapsed));
            }
        };
        try {
            RtcEngine rtcEngine = RtcEngine.create(config);
            rtcEngineManager = new RtcEngineManager(rtcEngine);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        if (rtcEngineManager != null) {
            rtcEngineManager.enableExtension(true);
            rtcEngineManager.setupLocalVideo(findViewById(R.id.surfaceView));
            rtcEngineManager.initExtension();
        }
        addContentView(
            new HTPanelLayout(this).init(getSupportFragmentManager()),
            new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
    }

    @Override
    public void onEvent(String provider, String extension, String key, String value) {
        Log.d(TAG, "onEvent provider: " + provider + "  extension: " + extension + "  key: " + key + "  value: " + value);

        try {
            JSONObject jsonObject = new JSONObject(value);
            if (jsonObject.has("path")) {
                String path = jsonObject.getString("path");
                Log.d(TAG, "path is: " + path);
                BeautyManager.updatePath(key, path);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStarted(String provider, String extension) {}

    @Override
    public void onStopped(String provider, String extension) {}

    @Override
    public void onError(String provider, String extension, int errCode, String errMsg) {}
}
