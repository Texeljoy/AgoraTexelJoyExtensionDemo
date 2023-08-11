# Instruction of HTEffect extension interface

## API Reference for Agora SDK

This section provides reference documents for extension related APIs in Agora SDK.

### Java

- [addExtension](https://docs.agora.io/cn/video-call-4.x/API%20Reference/java_ng/API/api_irtcengine_addextension.html) in `RtcEngineConfig`
- [enableExtension](https://api-ref.agora.io/en/video-sdk/android/4.x/API/class_irtcengine.html#api_irtcengine_enableextension) in `RtcEngine`
- [setExtensionProperty](https://api-ref.agora.io/en/video-sdk/android/4.x/API/class_irtcengine.html#api_irtcengine_setextensionproperty) in `RtcEngine`
- [onEvent](https://api-ref.agora.io/en/video-sdk/android/4.x/API/class_imediaextensionobserver.html#callback_irtcengineeventhandler_onextensionevent) in `IMediaExtensionObserver`

### Objective-C

- [enableExtensionWithVendor](https://api-ref.agora.io/en/video-sdk/ios/4.x/API/class_irtcengine.html#api_irtcengine_enableextension) in `AgoraRtcEngineKit`
- [setExtensionPropertyWithVendor](https://api-ref.agora.io/en/video-sdk/ios/4.x/API/class_irtcengine.html#api_irtcengine_setextensionproperty) in `AgoraRtcEngineKit`
- [onEvent](https://api-ref.agora.io/en/video-sdk/ios/4.x/API/class_imediaextensionobserver.html#callback_irtcengineeventhandler_onextensionevent) in `AgoraMediaFilterEventDelegate`

## Overview of extension key

### Function key

When calling the function `setExtensionProperty`/`setExtensionPropertyWithVendor` of Agora SDK, it is supported to pass in the following keys and corresponding functions:

#### Initialization

|  key in function setExtensionProperty/setExtensionPropertyWithVendor | description               |
| :------------------------------------------------------------- | :----------------- |
| [htInitHTEffectOnline](#htinithteffectonline)                                          | Initialization, online authentication. |
| [htInitHTEffectOffline](#htinithteffectoffline)                                          | Initialization, offline authentication. |

#### Destruction

| key in function setExtensionProperty/setExtensionPropertyWithVendor | description           |
| :------------------------------------------------------------- | :------------- |
| [htARReleaseBufferRenderer](#htreleasebufferrenderer)                                      | Function for resource release. |

#### Functional interface

| key in function setExtensionProperty/setExtensionPropertyWithVendor | description                            |
| :------------------------------------------------------------- | :------------------------------ |
| [htSetRenderEnable](#htsetrenderenable)                                              | Turn on/off all effects.             |
|                 [htReInit](#htreinit)                                                       | Reinitialize rendering.              |
| [htSetBeauty](#htsetbeauty)                                                    | Set beauty effect.              |
| [htSetReshape](#htsetreshape)                                                   | Set reshape effect.              |
| [htSetHairStyling](#htsethairstyling)                                               | Set hairstyling effect.                |
| [htSetFilter](#htsetfilter)                                                    | Set filter effect.              |
|[htSetStyle](#htsetstyle)                                                      | Set style effect.              |
| [htSetARItem](#htsetaritem)                                                    | Set AR item effect.            |
| [htSetWatermarkParam](#htsetwatermarkparam)                                            | Set parameter of watermark position.          |
| [htSetAISegEffect](#htsetaisegeffect)                                               | Set portrait matting effect.          |
| [htSetGSSegEffectScene](#htsetgssegeffectscene)                                          | Set scene in green screen matting effect.      |
| [htSetGSSegEffectCurtain](#htsetgssegeffectcurtain)                                        | Set screen in green screen matting effect.      |
| [htSetGSSegEffectSimilarity](#htsetgssegeffectsimilariry)                                     | Set similarity in green screen matting effect.    |
| [htSetGSSegEffectSmoothness](#htsetgssegeffectsmoothness)                                     | Set smoothness in green screen matting effect.    |
| [htSetGSSegEffectTransparency](#htsetgssegeffecttransparency)                                   | Set transparency in green screen matting effect.    |
| [htSetGestureEffect](#htsetgestureeffect)                                             | Set gesture recognition effect.          |
| [htSetExtremeLimitEnable](#htsetextremelimitenable)                                        | Set parameter extremum limit on/off. |
| [htSetIsMirror](#htsetismirror)                                                  | Set mirror.                  |
| [htSetMaxFaces](#htsetmaxfaces)                                                  | Set the maximum number of faces detected.                  |

### Callback key

This section lists the keys returned by the `onEvent` callback of Agora SDK.

| key in onEvent callback | description                            |
| :------------------------------------------------------------- | :------------------------------ |
| [htInitHTEffectOfflineResult](#htinithteffectofflineresult)                                                   | Obtain result of offline authentication.        |
| [htIsTracking](#htistracking)                                                   | Obtain the number of faces detected.        |

## Description of the value corresponding to the key in following functions

### htInitHTEffectOnline

value contains the following parameters:

| parameter value  | description                          |
| :---------- | :---------------------------- |
| `appId` | The appId of HTEffect online authentication |

### htInitHTEffectOffline

value contains the following parameters:

| parameter value  | description                          |
| :---------- | :---------------------------- |
| `license` | The secret key for AR offline authentication. String type. |

### htReleaseBufferRenderer

value contains the following parameters:

| parameter value | description                        |
| :--------- | :-------------------------- |
| `enable` | Whether to release resources. BOOL type. |

### htSetRenderEnable

value contains the following parameters:

| parameter value | description                        |
| :--------- | :------------------------ |
| `enable` | Whether to set beauty effect. BOOL type. The default is `true`. |

### htReInit

value with no corresponding parameter.

### htSetBeauty

value contains the following parameters:

| parameter value | description                    |
| :--------- | :---------------------- |
| `type`   | The enumeration value of beauty effect. Int type. Please refer to [HTBeautyTypes](https://github.com/Texeljoy/HTEffect_iOS/blob/master/HTEffect.framework/Headers/HTEffectInterface.h) for details. |
| `value`   | The parameter of beauty effect. Int type. The default range is [0, 100]. |

### htSetReshape

value contains the following parameters:

| parameter value | description                        |
| :----------- | :---------------------------------------------- |
| `type`   | The enumeration value of reshape effect. Int type. Please refer to [HTReshapeTypes](https://github.com/Texeljoy/HTEffect_iOS/blob/master/HTEffect.framework/Headers/HTEffectInterface.h) for details. |
| `value`   | The parameter of reshape effect. Int type. The default range is [0, 100]. |

### htSetHairStyling

value contains the following parameters:

| parameter value | description                        |
| :-------------- | :-------------------------------------- |
| `type`   | The enumeration value of hairstyling effect. Int type. Please refer to [HTHairTypes](https://github.com/Texeljoy/HTEffect_iOS/blob/master/HTEffect.framework/Headers/HTEffectInterface.h) for details. |
| `value`   | The parameter of hairstyling effect. Int type. The default range is [0, 100]. |

### htSetFilter

value contains the following parameters:

| parameter value   | description         |
| :----------- | :---------------------------------------------------- |
| `type`   | The enumeration value of filter effect. Int type. Please refer to [HTFilterTypes](https://github.com/Texeljoy/HTEffect_iOS/blob/master/HTEffect.framework/Headers/HTEffectInterface.h) for details. |
| `name`   | The name of filter effect. String type. You can find it from `assets` (Android) or `HTEffect.bundle` (iOS). |

### htSetStyle

value contains the following parameters:

| parameter value   | description         |
| :----------- | :---------------------------------------------------- |
| `type`   | The enumeration value of style effect. Int type. Please refer to [HTStyleTypes](https://github.com/Texeljoy/HTEffect_iOS/blob/master/HTEffect.framework/Headers/HTEffectInterface.h) for details. |
| `name`   | The name of style effect. String type. You can find it from `assets` (Android) or `HTEffect.bundle` (iOS). |

### htSetARItem

value contains the following parameters:

| parameter value   | description                                     |
| :----------- | :--------------------------------------- |
| `type`   | The enumeration value of AR item effect. Int type. Please refer to [HTARItemTypes](https://github.com/Texeljoy/HTEffect_iOS/blob/master/HTEffect.framework/Headers/HTEffectInterface.h) for details. |
| `name`   | The name of AR item effect. String type. You can find it from `assets` (Android) or `HTEffect.bundle` (iOS). |

### htSetWatermarkParam

value contains the following parameters:

| parameter value | description                              |
| :--------- | :-------------------------------- |
| `x1`   | The value of the coordinate point x in the upper left corner of the watermark. Float type. |
| `y1`   | The value of the coordinate point y in the upper left corner of the watermark. Float type. |
| `x2`   | The value of the coordinate point x in the bottom left corner of the watermark. Float type. |
| `y2`   | The value of the coordinate point y in the bottom left corner of the watermark. Float type. |
| `x3`   | The value of the coordinate point x in the bottom right corner of the watermark. Float type. |
| `y3`   | The value of the coordinate point y in the bottom right corner of the watermark. Float type. |
| `x4`   | The value of the coordinate point x in the upper right corner of the watermark. Float type. |
| `y4`   | The value of the coordinate point y in the upper right corner of the watermark. Float type. |

### htSetAISegEffect

value contains the following parameters:

| parameter value   | description                                     |
| :----------- | :--------------------------------------- |
| `name`   | The name of portrait matting effect. String type. You can find it from `assets` (Android) or `HTEffect.bundle` (iOS). |

### htSetGSSegEffectScene

value contains the following parameters:

| parameter value   | description                                     |
| :----------- | :--------------------------------------- |
| `name`   | The name of scene in green screen matting effect. String type. You can find it from `assets` (Android) or `HTEffect.bundle` (iOS). |

### htSetGSSegEffectCurtain

value contains the following parameters:

| parameter value   | description                                     |
| :----------- | :--------------------------------------- |
| `color`   | The color value of screen in green screen matting effect. String type. Currently, only green screen (`#00ff00`), blue screen (`#0000ff`), and white screen (`#ffffff`) are supported, as well as transparent screen. The default color is green. |

### htSetGSSegEffectSimilariry

value contains the following parameters:

| parameter value   | description                                     |
| :----------- | :--------------------------------------- |
| `value`   | The parameter of similarity in green screen matting effect. Int type. The default range is [0, 100]. |

### htSetGSSegEffectSmoothness

value contains the following parameters:

| parameter value   | description                                     |
| :----------- | :--------------------------------------- |
| `value`   | The parameter of smoothness in green screen matting effect. Int type. The default range is [0, 100]. |

### htSetGSSegEffectTransparency

value contains the following parameters:

| parameter value   | description                                     |
| :----------- | :--------------------------------------- |
| `value`   | The parameter of transparency in green screen matting effect. Int type. The default range is [0, 100]. |

### htSetGestureEffect

value contains the following parameters:

| parameter value   | description                                     |
| :----------- | :--------------------------------------- |
| `name`   | The name of gesture recognition effect. String type. You can find it from `assets` (Android) or `HTEffect.bundle` (iOS). |

### htSetExtremeLimitEnable

value contains the following parameters:

| parameter value   | description                                     |
| :----------- | :--------------------------------------- |
| `enable`   | Whether to enable extreme limit of parameter. BOOL type. The default is `true`. |

### htSetIsMirror

value contains the following parameters:

| parameter value   | description                                     |
| :----------- | :--------------------------------------- |
| `isMirror`   | Whether it is a mirror image. BOOL type. The default is `true`. |

### htSetMaxFaces

value contains the following parameters:

| parameter value   | description                                     |
| :----------- | :--------------------------------------- |
| `maxFaces`   | The maximum number of faces detected. Int type. The range is [1, 5]. |

##  Description of the value corresponding to the key in following callback functions

### htInitHTEffectOfflineResult

value contains the following parameters:

| parameter value | description |
| :------- | :---- |
| `initInfo` | The result of offline authentication. Int type. |

### htIsTracking

value contains the following parameters:

| parameter value | description |
| :------- | :---- |
| `faceNumber` | The number of faces being tracked. Int type. |