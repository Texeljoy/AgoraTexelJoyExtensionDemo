package io.agora.rte.extension.hteffect.example;

import android.util.Log;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;


public class BeautyManager {

    private static final String TAG = "BeautyManager";

    private static final Map<String, String> paths = new HashMap<>();
    private static final Map<String, PathCallback> callbacks = new HashMap<>();

    // 设置美颜
    public static void setBeautyProperty(int type, int value) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", type);
            jsonObject.put("value", value);
            RtcEngineManager.setExtensionProperty("htSetBeauty", jsonObject.toString());
        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }
    }

    // 设置美型
    public static void setReshapeProperty(int type, int value) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", type);
            jsonObject.put("value", value);
            RtcEngineManager.setExtensionProperty("htSetReshape", jsonObject.toString());
        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }
    }

    // 设置美妆
    public static void setMakeupProperty(int type, String key, String value) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", type);
            jsonObject.put("name", key);
            jsonObject.put("value", value);
            RtcEngineManager.setExtensionProperty("htSetMakeup", jsonObject.toString());
        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }
    }

    // 设置滤镜
    public static void setFilterProperty(int type, String name, int value) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", type);
            jsonObject.put("name", name);
            jsonObject.put("value", value);
            RtcEngineManager.setExtensionProperty("htSetFilter", jsonObject.toString());
        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }
    }

    // 设置美体
    public static void setBodyProperty(int type, int value) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", type);
            jsonObject.put("value", value);
            RtcEngineManager.setExtensionProperty("htSetBodyBeauty", jsonObject.toString());
        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }
    }

    // 设置美发
    public static void setHairProperty(int type, int value) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", type);
            jsonObject.put("value", value);
            RtcEngineManager.setExtensionProperty("htSetHairStyling", jsonObject.toString());
        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }
    }

    // 设置妆容推荐
    public static void setStyleProperty(String name, int value) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", name);
            jsonObject.put("value", value);
            RtcEngineManager.setExtensionProperty("htSetStyle", jsonObject.toString());
        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }
    }

    // 设置AR道具
    public static void setARItemProperty(int type, String name) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", type);
            jsonObject.put("name", name);
            RtcEngineManager.setExtensionProperty("htSetARItem", jsonObject.toString());
        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }
    }

    // 设置AI抠图
    public static void setAISegEffectProperty(String name) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", name);
            RtcEngineManager.setExtensionProperty("htSetAISegEffect", jsonObject.toString());
        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }
    }

    // 设置手势特效
    public static void setGestureEffectProperty(String name) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", name);
            RtcEngineManager.setExtensionProperty("htSetGestureEffect", jsonObject.toString());
        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }
    }

    // 设置绿幕抠图
    public static void setChromaKeyingSceneProperty(String name) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", name);
            RtcEngineManager.setExtensionProperty("htSetChromaKeyingScene", jsonObject.toString());
        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }
    }

    // 设置绿幕抠图
    public static void setChromaKeyingParamsProperty(int type, int value) {
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("type", type);
            jsonObject.put("value", value);
            RtcEngineManager.setExtensionProperty("htSetChromaKeyingParams", jsonObject.toString());
        } catch (Exception e) {
            Log.e("TAG", e.toString());
        }
    }


    public interface PathCallback {
        void onPathReceived(String path);
    }

    public static void updatePath(String key, String path) {
        paths.put(key, path);
        Log.d("updatePath", "Updated path for " + key + ": " + path);
        PathCallback callback = callbacks.get(key);
        if (callback != null) {
            callback.onPathReceived(path);
        }
    }

    public static void getPath(String key, PathCallback callback) {
        String path = paths.get(key);
        if (path != null) {
            callback.onPathReceived(path);
        } else {
            callbacks.put(key, callback);
            requestPath(key);
        }
    }

    public static void requestPath(String key) {
        try {
            JSONObject jsonObject = new JSONObject();
            RtcEngineManager.setExtensionProperty(key, jsonObject.toString());
            Log.d("requestPath", "Requesting path for " + key);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }


}
