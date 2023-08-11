# Usage of HTEffect extension

This following content introduces how to integrate and use Hongtu HTEffect beauty extension (hereinafter referred to as "HTEffect extension") in your project, including Android and iOS platforms.

## Technical Principles

HTEffect extension encapsulates the core API of HTEffect. By calling the [setExtensionProperty](https://docs.agora.io/cn/video-call-4.x-beta/API%20Reference/java_ng/API/class_irtcengine.html#api_setextensionproperty) or [setExtensionPropertyWithVendor](https://docs.agora.io/cn/video-call-4.x-beta/API%20Reference/ios_ng/API/class_irtcengine.html#api_setextensionproperty) functions of the [Agora Video SDK v4.1.1](https://docs.agora.io/en/video-calling/overview/product-overview?platform=android) and passing in the specified `key` and `value`, you can quickly integrate the effects of HTEffect.

Taking `setExtensionProperty` as an example, the value parameter wraps some or all of the API parameters of HTEffect in JSON format. Therefore, when calling `setExtensionProperty`, as long as the specified key and value are passed in, the corresponding HTEffect API can be called to implement effects related functions. The same applies to setExtensionPropertyWithVendor.
The supported keys and values can be found in [Instruction of HTEffect extension interface](https://github.com/Texeljoy/AgoraTexelJoyExtension/tree/master).

## Prerequisites

- Android:
  - Android Studio version 4.1 or later.
  - Real machine running Android 5.0 or later (non emulator).
- iOS:
  - Xcode version 10.0 or later.
  - Real machine running iOS 10.0 or later (non emulator).

## Demo

View the complete sample demo and project structure on Gitee:

| platform    | language        | demo             |
| :------ | :---------- | :---------------- |
| Android     | Java | [AgoraTexelJoyExtensionDemo/Android](https://github.com/Texeljoy/AgoraTexelJoyExtensionDemo.git) |
| iOS     | Objective-C | [AgoraTexelJoyExtensionDemo/iOS](https://github.com/Texeljoy/AgoraTexelJoyExtensionDemo.git) |

Refer to the following steps to quickly get through the demo:

### Steps

**Android**

1. clone repository:
  ```shell
    git clone https://github.com/Texeljoy/AgoraTexelJoyExtensionDemo.git
  ```
2. Download the Android package for [HTEffect](https://github.com/Texeljoy/AgoraTexelJoyExtension.git). After decompression, save all the `.aar` files to the `agora-texel-joy-extension-demo/Android/app/libs` path (the `libs` folder needs to be created by yourself).
3. Contact Agora platform to obtain the certificate and resource package.
4. Save the required model and resource files from the resource package to the `agora-texel-joy-extension-demo/Android/app/src/main/assets` path in the project folder (the `assets` folder needs to be created by yourself).
5. Open the project in Android Studio `agora-texel-joy-extension-demo/Android`。
6. Synchronize project with Gradle file.
7. Open `agora-texel-joy-extension-demo/Android/app/src/main/java/io/agora/rte/extension/hteffect/example/Constants.java` and make the following modifications:
	- Replace `<YOUR_APP_ID>` with your App ID. To obtain the App ID, please refer to [Get Started with Agora](https://docs.agora.io/en/video-calling/reference/manage-agora-account?platform=android#get-the-app-id?platform=All%20Platforms).
	- `mToken` and `mLicenseName` do not need to be filled in when running demo.
    ```java
    public interface Constants {
        String mAppId = "<YOUR_APP_ID>";
        String mToken = null;
        String mLicenseName = "<YOUR_LICENSE_NAME>";
    }
    ```
8. Open `agora-texel-joy-extension-demo/Android/app/src/main/java/io/agora/rte/extension/hteffect/example/HtKey.java` and make the following modifications:
	- Replace `<YOUR_HT_SDK_KEY>` with the test key provided by HTEffect purchased from Agora platform:
    ```java
    public interface HtKey {
        String HTKey = "<YOUR_HT_SDK_KEY>";
    }
    ```
9. Connect an Android real machine (not an emulator) and run the project.

**iOS**

1. clone repository:
```shell
  git clone https://github.com/Texeljoy/AgoraTexelJoyExtensionDemo.git
```
2. Download the iOS package for [HTEffect](https://github.com/Texeljoy/AgoraTexelJoyExtension.git). After decompression, save all the `.framework` to the path `agora-texel-joy-extension-demo/iOS/ExtensionExample`.
3. Contact Agora platform to obtain the certificate and resource package.
4. Save the certificate `HTKey.h` and resource package `HTEffect.bundle` to the path `agora-texel-joy-extension-demo/iOS/ExtensionExample`.
5. Enter the path `agora-texel-joy-extension-demo/iOS` in Terminal and run the following command to install dependencies using CocoaPods:
 ```
	pod install
```
6. Open `ExtensionExample.xcworkspace` in Xcode.
7. Open `Config.h` and make the following modifications:
 - Replace `<YOUR_APP_ID>` with your App ID. To obtain the App ID, please refer to [Get Started with Agora](https://docs.agora.io/en/video-calling/reference/manage-agora-account?platform=android#get-the-app-id?platform=All%20Platforms).
 - `token` do not need to be filled in when running demo.
    ```objective-c
    NSString *const appID = @"<YOUR_APP_ID>";
    NSString *const token = nil;
    ```
8. Open `agora-texel-joy-extension-demo/iOS/ExtensionExample/HTKey.h` and make the following modifications:
 - Replace `<YOUR_HT_SDK_KEY>` with the test key provided by HTEffect purchased from Agora platform:
     ```objective-c
     NSString *const HTKey = @"<YOUR_HT_SDK_KEY>";
     ```
9. Connect an iOS real machine (not an emulator) and run the project.

### Results

After running, the project will be installed on your Android or iOS device.

1. After launching the app, effects is enabled by default. You can see the `enableExtension`, `setStyleFilter`, `setSticker` buttons, and `faces: 0` to display the number of faces.
2. Click on `setStyleFilter` to set the style filter effect. The default is Natural 3.
3. Click on `setSticker` to set the sticker effect. The default is rabbit style.

## Integration and call

### Preparation

#### Implementing video call using the Agora SDK

HTEffect needs to be used in conjunction with the [Agora SDK v4.1.1](https://docs.agora.io/en/video-calling/overview/product-overview?platform=android). Refer to the following documents to integrate the video SDK v4.1.1 and implement basic video calls:
- [Implement video call (Android)](https://docs.agora.io/en/video-calling/get-started/get-started-sdk?platform=android#project-setup)
- [Implement video call (iOS)](https://docs.agora.io/en/video-calling/get-started/get-started-sdk?platform=ios#project-setup)

#### Purchase and activate extension

In the Agora console [Purchase and Activate](https://docs.agora.io/en/extensions-marketplace/overview/product-overview?platform=android) HTEffect, it is necessary to save the obtained certificate for subsequent integration of extension when purchasing.

#### Integration

**Android**

1. Download the Android extension package for [HTEffect](https://github.com/Texeljoy/AgoraTexelJoyExtension.git). After decompression, save all the `.aar` to the project folder, such as the path `/app/libs`.

2. Contact Agora platform to obtain the resource package and save the required model and item files to the project folder, such as the path `/app/src/main/assets`.

3. Open the `app/build.gradle` and add the following code to `dependencies`

   ```java
   implementation fileTree(dir: "libs", include: ["*.jar", "*.aar"])
   ```
4. Import the required classes:

   ```java
   import io.agora.rtc2.Constants;
   import io.agora.rtc2.IMediaExtensionObserver;
   import io.agora.rtc2.IRtcEngineEventHandler;
   import io.agora.rtc2.RtcEngine;
   import io.agora.rtc2.RtcEngineConfig;
   import io.agora.rtc2.video.VideoCanvas;
   ```

**iOS**

1. Download the iOS extension package for [HTEffect](https://github.com/Texeljoy/AgoraTexelJoyExtension.git).
2. Unzip the folder and save all the `.framework` to your project folder.
3. Contact Agora platform to obtain the resource package and save `HTEffect.bundle` to the project folder.
4. In Xcode [Add Dynamic Library](https://help.apple.com/xcode/mac/current/#/dev51a648b07), ensure that **Embed** is set to **Embed & Sign**.
5. Import the required header file:

    ```objective-c
    #import <AgoraRtcKit/AgoraRtcEngineKit.h>
    #import "HTKey.h"
    ```

The following is an introduction to the call of extension related interfaces. The parameter explanation of the interface is detailed in [Interface Instruction](https://github.com/Texeljoy/AgoraTexelJoyExtension/tree/master).

### Step 1: Enable extension

**Android**

When initializing `RtcEngine`, call `enableExtension` to enable the extension.

```java
private void enableExtension(boolean enabled) {
    mRtcEngine.enableExtension("Texeljoy", "HTEffect", enabled);
}
   ```

**iOS**

When initializing `RtcEngine`, call `enableExtensionWithVendor` to enable the extension.。

```objective-c
-(void)enableExtension:(BOOL)enabled{
    [self.agoraKit enableExtensionWithVendor:@"Texeljoy"
                                   extension:@"HTEffect"
                                     enabled:enable];
}
```

### Step 2: Initialize the extension

To initialize the extension, you need to call `setExtensionProperty` (Android) or `setExtensionPropertyWithVendor` (iOS) and pass in the corresponding key and value, in the following order:

**Android**

```java
private void initExtension() {
    // initialization
    try {
        // online authentication
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appId",HtKey.HTKey);
        setExtensionProperty("htInitHTEffectOnline", jsonObject.toString());
    } catch (JSONException e) {
        Log.e(TAG, e.toString());
    }
}
  ```

```java
private void initExtension() {
    // initialization
    try {
        // offline authentication
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("license",HtKey.HTKey);
        setExtensionProperty("htInitHTEffectOffline", jsonObject.toString());
    } catch (JSONException e) {
        Log.e(TAG, e.toString());
    }
}
```

**iOS**

```objective-c
- (void)initExtension{
    // online authentication
    [self.agoraKit setExtensionPropertyWithVendor:@"Texeljoy"
                                        extension:@"HTEffect"
                                              key:@"htInitHTEffectOnline"
                                            value:[self toJson:@{
                                                @"license":HTSDKKey
                                            }]];
}
```

```objective-c
- (void)initExtension{
    // offline authentication
    [self.agoraKit setExtensionPropertyWithVendor:@"Texeljoy"
                                        extension:@"HTEffect"
                                              key:@"htInitHTEffectOffline"
                                            value:[self toJson:@{
                                                @"license":HTSDKKey
                                            }]];
}
```

### Step 3: Set up effects

Call `setExtensionProperty` (Android) or `setExtensionPropertyWithVendor` (iOS) and pass in the corresponding key and value to achieve the following functions:

1. Quick beauty
2. Precise beauty、Reshape、Hairstyling、Style
3. Sticker、Gift、Watermark
4. Portrait matting、Green screen matting
5. Gesture recognition
6. Style filter、Effect filter、Funny filter

You can make combined calls according to the needs of the scene. Please refer to [Interface Instruction](https://github.com/Texeljoy/AgoraTexelJoyExtension/tree/master) for the corresponding key and value.


## More references
### Interface instruction

Please refer to [Interface Instruction](https://github.com/Texeljoy/AgoraTexelJoyExtension/tree/master) for the parameter explanations of all interfaces related to the extension.
