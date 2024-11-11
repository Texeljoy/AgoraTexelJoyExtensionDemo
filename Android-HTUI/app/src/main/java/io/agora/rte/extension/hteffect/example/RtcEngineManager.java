package io.agora.rte.extension.hteffect.example;

import android.util.Log;
import android.view.View;
import io.agora.rte.extension.hteffect.example.HtKey;
import org.json.JSONException;
import org.json.JSONObject;
import io.agora.rtc2.RtcEngine;
import io.agora.rtc2.video.VideoCanvas;

public class RtcEngineManager {

    private static final String TAG = "RtcEngineManager";
    private static RtcEngine mRtcEngine;
    private boolean isMirror = true;

    public RtcEngineManager(RtcEngine rtcEngine) {
        this.mRtcEngine = rtcEngine;
    }

    public void enableExtension(boolean enabled) {
        if (mRtcEngine != null) {
            mRtcEngine.enableExtension("Texeljoy", "HTEffect", enabled);
        }
    }

    public static void setExtensionProperty(String key, String property) {
        if (mRtcEngine != null) {
            mRtcEngine.setExtensionProperty("Texeljoy", "HTEffect", key, property);
        }
    }

    public static String getExtensionProperty(String key) {
        if (mRtcEngine != null) {
            return mRtcEngine.getExtensionProperty("Texeljoy", "HTEffect", key);
        }
        return null;
    }

    // public static String getExtensionProperty(String key) {
    //     if (mRtcEngine != null) {
    //         String propertyValue = mRtcEngine.getExtensionProperty("Texeljoy", "HTEffect", key);
    //         if (propertyValue != null) {
    //             return propertyValue;
    //         }
    //     }
    //     return "";
    // }

    public void initExtension() {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("appId", HtKey.HT_APPID); // 替换为你的应用 ID
            setExtensionProperty("htInitHTEffectOnline", jsonObject.toString());
        } catch (JSONException e) {
            Log.e(TAG, "initExtension: " + e.toString());
        }
    }

    // public void choiceStyleFilter() {
    //     try {
    //         JSONObject jsonObject = new JSONObject();
    //         jsonObject.put("type", 0);
    //         jsonObject.put("name", "ziran3"); // 替换为你想要的滤镜名称
    //         setExtensionProperty("htSetFilter", jsonObject.toString());
    //     } catch (JSONException e) {
    //         Log.e(TAG, "choiceStyleFilter: " + e.toString());
    //     }
    // }
    //
    //
    // public void choiceSticker() {
    //     try {
    //         JSONObject jsonObject = new JSONObject();
    //         jsonObject.put("type", 0);
    //         jsonObject.put("name", "ht_sticker_effect_rabbit_bowknot"); // 替换为你想要的贴纸名称
    //         setExtensionProperty("htSetARItem", jsonObject.toString());
    //     } catch (JSONException e) {
    //         Log.e(TAG, "choiceSticker: " + e.toString());
    //     }
    // }

    public void setupLocalVideo(View surfaceView) {
        if (mRtcEngine != null) {
            VideoCanvas canvas = new VideoCanvas(surfaceView);
            mRtcEngine.setupLocalVideo(canvas);
            mRtcEngine.startPreview();
        }
    }
    // public void updateAITrackingResult(int faces) {
    //     // 实现你需要的功能，比如更新 UI 或通知其他组件
    // }


}
