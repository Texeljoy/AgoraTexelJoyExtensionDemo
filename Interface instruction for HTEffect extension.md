# Instruction of HTEffect extension interface

## API Reference for Agora SDK

This section provides reference documents for extension related APIs in Agora SDK.

### Java

- [addExtension](https://doc.shengwang.cn/api-ref/rtc/android/API/api_irtcengine_addextension#addExtension) in `RtcEngineConfig`
- [enableExtension](https://doc.shengwang.cn/api-ref/rtc/android/API/toc_extension#enableExtension) in `RtcEngine`
- [setExtensionProperty](https://doc.shengwang.cn/api-ref/rtc/android/API/toc_extension#setExtensionProperty) in `RtcEngine`
- [onEvent](https://doc.shengwang.cn/api-ref/rtc/android/API/toc_extension#onEvent) in `IMediaExtensionObserver`

### Objective-C

- [enableExtensionWithVendor](https://doc.shengwang.cn/api-ref/rtc/ios/API/toc_extension#enableExtensionWithVendor) in `AgoraRtcEngineKit`
- [setExtensionPropertyWithVendor](https://doc.shengwang.cn/api-ref/rtc/ios/API/toc_extension#setExtensionPropertyWithVendor) in `AgoraRtcEngineKit`
- [onEvent](https://doc.shengwang.cn/api-ref/rtc/ios/API/toc_extension#onEvent) in `AgoraMediaFilterEventDelegate`

## Overview of extension key

### Function key

When calling the function `setExtensionProperty`/`setExtensionPropertyWithVendor` of Agora SDK, it is supported to pass in the following keys and corresponding functions:

#### Initialization

| key in function setExtensionProperty/setExtensionPropertyWithVendor | description |
| :------------------------------------------------------------------ | :----------------- |
| [htInitHTEffectOnline](#htinithteffectonline)                       | Initialization, online authentication. |
| [htInitHTEffectOffline](#htinithteffectoffline)                     | Initialization, offline authentication. |

#### Destruction

| key in function setExtensionProperty/setExtensionPropertyWithVendor | description |
| :------------------------------------------------------------------ | :------------- |
| [htReleaseBufferRenderer](#htreleasebufferrenderer)                 | Function for resource release. |

#### Functional interface

| key in function setExtensionProperty/setExtensionPropertyWithVendor | description |
| :------------------------------------------------------------------ | :------------------------------ |
| [htSetRenderEnable](#htsetrenderenable)                             | Turn on/off all effects. |
| [htReInit](#htreinit)                                               | Reinitialize rendering. |
| [htSetBeauty](#htsetbeauty)                                         | Set beauty effect. |
| [htSetReshape](#htsetreshape)                                       | Set reshape effect. |
| [htSetHairStyling](#htsethairstyling)                               | Set hairstyling effect. |
| [htSetMakeup](#htsetmakeup)                                         | Set makeup effect. |
| [htSetStyle](#htsetstyle)                                           | Set makeup style effect. |
| [htSetBodyBeauty](#htsetbodybeauty)                                 | Set bodybeauty effect. |
| [htSetFilter](#htsetfilter)                                         | Set filter effect. |
| [htSetARItem](#htsetaritem)                                         | Set AR item effect. |
| [htSetWatermarkParam](#htsetwatermarkparam)                         | Set parameter of watermark position. |
| [htSetAISegEffect](#htsetaisegeffect)                               | Set portrait matting effect. |
| [htSetChromaKeyingScene](#htsetchromakeyingscene)                   | Set scene in chroma keying effect. |
| [htSetChromaKeyingCurtain](#htsetchromakeyingcurtain)               | Set curtain color in chroma keying effect. |
| [htSetChromaKeyingParams](#htsetchromakeyingparams)                 | Set parameters in chroma keying effect. |
| [htSetGestureEffect](#htsetgestureeffect)                           | Set gesture recognition effect. |
| [htSetExtremeLimitEnable](#htsetextremelimitenable)                 | Set parameter extremum limit on/off. |
| [htSetIsMirror](#htsetismirror)                                     | Set mirror. |
| [htSetMaxFaces](#htsetmaxfaces)                                     | Set the maximum number of faces detected. |

### Callback key

This section lists the keys returned by the `onEvent` callback of Agora SDK.

| key in onEvent callback                                        | description |
| :------------------------------------------------------------- | :------------------------------ |
| [htInitHTEffectOfflineResult](#htinithteffectofflineresult)    | Obtain result of offline authentication. |
| [htIsTracking](#htistracking)                                  | Obtain the number of faces detected. |
| [htErrorCallback](#hterrorcallback)                            | error information。 |

## Description of the value corresponding to the key in following functions

### htInitHTEffectOnline

value contains the following parameters:

| parameter value | description |
| :-------------- | :---------------------------- |
| `appId`         | The appId of HTEffect online authentication. String type. |

### htInitHTEffectOffline

value contains the following parameters:

| parameter value | description |
| :-------------- | :---------------------------- |
| `license`       | The license of HTEffect offline authentication. String type. |

### htReleaseBufferRenderer

value with no corresponding parameter.

### htSetRenderEnable

value contains the following parameters:

| parameter value | description |
| :-------------- | :------------------------ |
| `enable`        | Whether to set beauty effect. BOOL type. The default is `true`. |

### htReInit

value with no corresponding parameter.

### htSetBeauty

value contains the following parameters:

| parameter value | description |
| :-------------- | :---------------------- |
| `type`          | The enumeration value of beauty effect. Int type. Please refer to [HTBeautyTypes](https://github.com/Texeljoy/HTEffect_iOS/blob/master/HTEffect.framework/Headers/HTEffectInterface.h) for details. |
| `value`         | The parameter of beauty effect. Int type. The default range is [0, 100]. |

### htSetReshape

value contains the following parameters:

| parameter value | description |
| :-------------- | :----------------------- |
| `type`          | The enumeration value of reshape effect. Int type. Please refer to [HTReshapeTypes](https://github.com/Texeljoy/HTEffect_iOS/blob/master/HTEffect.framework/Headers/HTEffectInterface.h) for details. |
| `value`         | The parameter of reshape effect. Int type. The default range is [0, 100]. |

### htSetHairStyling

value contains the following parameters:

| parameter value | description |
| :-------------- | :------------------------ |
| `type`          | The enumeration value of hairstyling effect. Int type. Please refer to [HTHairTypes](https://github.com/Texeljoy/HTEffect_iOS/blob/master/HTEffect.framework/Headers/HTEffectInterface.h) for details. |
| `value`         | The parameter of hairstyling effect. Int type. The default range is [0, 100]. |

### htSetMakeup

value contains the following parameters:

| parameter value | description |
| :-------------- | :------------------------- |
| `type`          | The enumeration value of makeup effect. Int type. Please refer to [HTMakeupTypes](https://github.com/Texeljoy/HTEffect_iOS/blob/master/HTEffect.framework/Headers/HTEffectInterface.h) for details. |
| `name`          | The name of makeup style effect. String type. You can find it from `assets` (Android) or `HTEffect.bundle` (iOS). Taking the effect of lipstick "Lipstick1" as an example, the value of "name" can be found from resource folder/makeup/lipstick/ht_makeup_lipstick_config.json, where it is "ht_makeup_lipstick_fanqie". |
| `value`         | The parameter of makeup effect. Int type. The default range is [0, 100]. |

### htSetStyle

value contains the following parameters:

| parameter value | description |
| :-------------- | :-------------------------- |
| `type`          | The enumeration value of makeup style effect. Int type. Please refer to [HTStyleTypes](https://github.com/Texeljoy/HTEffect_iOS/blob/master/HTEffect.framework/Headers/HTEffectInterface.h) for details. |

### htSetBodyBeauty

value contains the following parameters:

| parameter value | description |
| :-------------- | :---------------------------- |
| `type`          | The enumeration value of bodybeautya effect. Int type. Please refer to [HTBodyBeautyTypes](https://github.com/Texeljoy/HTEffect_iOS/blob/master/HTEffect.framework/Headers/HTEffectInterface.h) for details. |
| `value`         | The parameter of bodybeauty effect. Int type. The default range is [0, 100]. |

### htSetFilter

value contains the following parameters:

| parameter value | description |
| :-------------- | :----------------------------- |
| `type`          | The enumeration value of filter effect. Int type. Please refer to [HTFilterTypes](https://github.com/Texeljoy/HTEffect_iOS/blob/master/HTEffect.framework/Headers/HTEffectInterface.h) for details. |
| `name`          | The name of filter effect. String type. You can find it from `assets` (Android) or `HTEffect.bundle` (iOS). Taking the effect of filter "Nature3" as an example, the value of "name" can be found from resource folder/filter/ht_style_filter_config.json, where it is "ziran3". |

### htSetARItem

value contains the following parameters:

| parameter value | description |
| :-------------- | :--------------------------------------- |
| `type`          | The enumeration value of AR item effect. Int type. Please refer to [HTARItemTypes](https://github.com/Texeljoy/HTEffect_iOS/blob/master/HTEffect.framework/Headers/HTEffectInterface.h) for details. |
| `name`          | The name of AR item effect. String type. You can find it from `assets` (Android) or `HTEffect.bundle` (iOS). Taking the effect of filter "Rabbit bowknot" as an example, the value of "name" can be found from resource folder/sticker/ht_sticker_config.json, where it is "ht_sticker_effect_rabbit_bowknot". |

### htSetWatermarkParam

value contains the following parameters:

| parameter value | description |
| :-------------- | :-------------------------------- |
| `x1`            | The value of the coordinate point x in the upper left corner of the watermark. Float type. |
| `y1`            | The value of the coordinate point y in the upper left corner of the watermark. Float type. |
| `x2`            | The value of the coordinate point x in the bottom left corner of the watermark. Float type. |
| `y2`            | The value of the coordinate point y in the bottom left corner of the watermark. Float type. |
| `x3`            | The value of the coordinate point x in the bottom right corner of the watermark. Float type. |
| `y3`            | The value of the coordinate point y in the bottom right corner of the watermark. Float type. |
| `x4`            | The value of the coordinate point x in the upper right corner of the watermark. Float type. |
| `y4`            | The value of the coordinate point y in the upper right corner of the watermark. Float type. |

### htSetAISegEffect

value contains the following parameters:

| parameter value | description |
| :-------------- | :-------------------------------- |
| `name`          | The name of portrait matting effect. String type. You can find it from `assets` (Android) or `HTEffect.bundle` (iOS). Taking the effect of "Adore" as an example, the value of "name" can be found from resource folder/aiseg_effect/ht_aiseg_effect_config.json, where it is "ht_aiseg_effect_adoreme". |

### htSetChromaKeyingScene

value contains the following parameters:

| parameter value | description |
| :-------------- | :--------------------------------- |
| `name`          | The name of scene in chroma keying effect. String type. You can find it from `assets` (Android) or `HTEffect.bundle` (iOS). Taking the effect of "Ancientry" as an example, the value of "name" can be found from resource folder/gsseg_effect/ht_gsseg_effect_config.json, where it is "ht_gsseg_effect_ancientry". |

### htSetChromaKeyingCurtain

value contains the following parameters:

| parameter value | description |
| :-------------- | :---------------------------------- |
| `color`         | The color value of curtain in chroma keying effect. String type. Currently, only green curtain (`#00ff00`), blue curtain (`#0000ff`), and red curtain (`#ff0000`) are supported, as well as transparent curtain. The default color is green. |

### htSetChromaKeyingParams

value contains the following parameters:

| parameter value | description |
| :-------------- | :--------------------------------------- |
| `type`          | The type of parameter in chroma keying effect. 0-similarity, 1-smoothness, 2-achromaticity, 3-accuracy |
| `value`         | The value of parameter type in chroma keying effect. Int type. The default range is [0, 100]. |

### htSetGestureEffect

value contains the following parameters:

| parameter value | description |
| :-------------- | :--------------------------------------- |
| `name`          | The name of gesture recognition effect. String type. You can find it from `assets` (Android) or `HTEffect.bundle` (iOS). Taking the effect of "Hand heart" as an example, the value of "name" can be found from resource folder/gesture_effect/ht_gesture_effect_config.json, where it is "ht_gesture_effect_bixin". |

### htSetExtremeLimitEnable

value contains the following parameters:

| parameter value | description |
| :-------------- | :--------------------------------------- |
| `enable`        | Whether to enable extreme limit of parameter. BOOL type. The default is `true`. |

### htSetIsMirror

value contains the following parameters:

| parameter value | description |
| :-------------- | :--------------------------------------- |
| `isMirror`      | Whether it is a mirror image. BOOL type. The default is `true`. |

### htSetMaxFaces

value contains the following parameters:

| parameter value | description |
| :-------------- | :--------------------------------------- |
| `maxFaces`      | The maximum number of faces detected. Int type. The range is [1, 5]. |

##  Description of the value corresponding to the key in following callback functions

### htInitHTEffectOfflineResult

value contains the following parameters:

| parameter value | description |
| :-------------- | :---------------------------------------- |
| `initInfo`      | The result of offline authentication. Int type. Greater than 0=successful, 0=uninitialized, -1=certificate error, -2=package name error, -3=application name error, -4=certificate expiration, -5=path error, -6=model file error, -7=certificate expiration, -8=version error. |

### htIsTracking

value contains the following parameters:

| parameter value | description |
| :-------------- | :---------------------------------------- |
| `faceNumber`    | The number of faces being tracked. Int type. |

### htErrorCallback

This callback is only available on iOS. For Android search error messages, please filter "htErrorCode" in logcat. -10=parameter error,-100=invalid json，-101=invalid json type。

value contains the following parameters:

| parameter value | description |
| :-------- | :-------------------------------------- |
| `key`     | The name of error methond, String type。 |
| `code`    | error code, Int type, -1=value error。 |