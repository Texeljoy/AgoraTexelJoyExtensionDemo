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
| [htGetARItemUrlBy](#htGetARItemUrlBy) | Return the network path of AR prop materials. |
| [htGetARItemPathBy](#htGetARItemPathBy) | Return the sandbox path of AR prop materials. |
| [htGetMakeupUrl](#htGetMakeupUrl) | Return the network path for makeup materials. |
| [htGetMakeupPath](#htGetMakeupPath) | Return the sandbox path for makeup materials. |
| [htGetAISegEffectUrl](#htGetAISegEffectUrl) | Return the network path for AISegEffect materials. |
| [htGetAISegEffectPath](#htGetAISegEffectPath) | Return the sandbox path for AISegEffect materials. |
| [htGetGestureEffectUrl](#htGetGestureEffectUrl) | Return the network path for GestureEffect materials. |
| [htGetGestureEffectPath](#htGetGestureEffectPath) | Return the sandbox path for GestureEffect materials. |
| [htGetChromaKeyingUrl](#htGetChromaKeyingUrl) | Return the network path for chroma key image segmentation materials. |
| [htGetChromaKeyingPath](#htGetChromaKeyingPath) | Return the sandbox path for chroma key image segmentation materials. |
| [htGetFilterUrl](#htGetFilterUrl) | Return the network path for filter materials. |
| [htGetFilterPath](#htGetFilterPath) | Return the sandbox path for filter materials. |
| [htGetStyleUrl](#htGetStyleUrl) | Return the network path for Style materials. |
| [htGetStylePath](#htGetStylePath) | Return the sandbox path for Style materials. |
| [htGetResourceUrl](#htGetResourceUrl) | Return the network path for materials. |
| [htSetResourceUrl](#htSetResourceUrl) | Set the URL for the network path of materials. |
| [htSetResourcePath](#htSetResourcePath) | Set the path. |
| [htSetAuthNetworkNode](#htSetAuthNetworkNode) | Set the overseas node. |
| [htSetTransparencyRenderEnable](#htSetTransparencyRenderEnable) | Partial transparency rendering support switch. |
| [htSetFaceDetecionCPUPowersaveEnable](#htSetFaceDetecionCPUPowersaveEnable) | Set the multi-core CPU operation switch for the face detection algorithm. |
| [htSetPerformancePriorityEnable](#htSetPerformancePriorityEnable) | Set the performance priority mode switch. |
| [htSetFaceDetectionDistanceLevel](#htSetFaceDetectionDistanceLevel) | Set the face detection distance level. |
| [htSetFaceDetectorType](#htSetFaceDetectorType) | Set the face detector type. |
| [htGetVersionCode](#htGetVersionCode) | Get the current SDK version number. |
| [htGetVersion](#htGetVersion) | Get the current SDK version information. |

### Callback key

This section lists the keys returned by the `onEvent` callback of Agora SDK.

| key in onEvent callback                                        | description |
| :------------------------------------------------------------- | :------------------------------ |
| [htInitHTEffectOfflineResult](#htinithteffectofflineresult)    | Obtain result of offline authentication. |
| [htInitHTEffectOnlineResult](#htInitHTEffectOnlineResult) | Get the online authorization result function. |
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

### htInitHTEffectOnlineResult

value contains the following parameters:

| value 参数   | 描述                                                         |
| :----------- | :----------------------------------------------------------- |
| `initStatus` | Int type, online authorization result value. 1 = initialization successful, 0 = initialization failed. |

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

### htGetARItemUrlBy

value contains the following parameters:

| parameter value | description                                                  |
| :-------------- | :----------------------------------------------------------- |
| `type`          | Int type, enum value for AR prop feature types. For specific values, see [HTARItemTypes](https://github.com/Texeljoy/HTEffect_iOS/blob/master/HTEffect.framework/Headers/HTEffectInterface.h). |

### htGetARItemPathBy

value contains the following parameters:

| parameter value | description                                                  |
| :-------------- | :----------------------------------------------------------- |
| `type`          | Int type, enum value for AR prop feature types. For specific values, see  [HTARItemTypes](https://github.com/Texeljoy/HTEffect_iOS/blob/master/HTEffect.framework/Headers/HTEffectInterface.h). |

### htGetMakeupUrl

value contains the following parameters:

| parameter value | description                                                  |
| :-------------- | :----------------------------------------------------------- |
| `type`          | Int type, enum value for make up types.For specific values, see   [HTMakeupTypes](https://github.com/Texeljoy/HTEffect_iOS/blob/master/HTEffect.framework/Headers/HTEffectInterface.h)。 |

### htGetMakeupPath

value contains the following parameters:

| parameter value | description                                                  |
| :-------------- | :----------------------------------------------------------- |
| `type`          | Int type, enum value for make up types.For specific values, see    [HTMakeupTypes](https://github.com/Texeljoy/HTEffect_iOS/blob/master/HTEffect.framework/Headers/HTEffectInterface.h)。 |

### htGetAISegEffectUrl

value has no parameters.

### htGetAISegEffectPath

value has no parameters.

### htGetGestureEffectUrl

value has no parameters.

### htGetGestureEffectPath

value has no parameters.

### htGetChromaKeyingUrl

value has no parameters.

### htGetChromaKeyingPath

value has no parameters.

### htGetFilterUrl

value has no parameters.

### htGetFilterPath

value has no parameters.

### htGetStyleUrl

value has no parameters.

### htGetStylePath

value has no parameters.

### htGetResourceUrl

value has no parameters.

### htSetResourceUrl

value contains the following parameters:

| parameter value | description                            |
| :-------------- | :------------------------------------- |
| `url`           | String type, set the network path URL. |

### htSetResourcePath

value contains the following parameters:

| parameter value | description                        |
| :-------------- | :--------------------------------- |
| `path`          | String type, set the sandbox path. |

### htSetAuthNetworkNode

value contains the following parameters:

| parameter value | description                                                  |
| :-------------- | :----------------------------------------------------------- |
| `node`          | String type, node name. Default "cn" for domestic node. "sg" for overseas node - Singapore. |

### htSetTransparencyRenderEnable

value contains the following parameters:

| parameter value | description                                                  |
| :-------------- | :----------------------------------------------------------- |
| `enable`        | BOOL 型，部分透明渲染支持开关。开启为`true`，关闭为`false`，默认关闭。 |

### htSetFaceDetecionCPUPowersaveEnable

value contains the following parameters:

| parameter value | description                                                  |
| :-------------- | :----------------------------------------------------------- |
| `enable`        | BOOL type, partial transparency rendering support switch. `true` to enable, `false` to disable. Default is `false`. |

### htSetPerformancePriorityEnable

value contains the following parameters:

| parameter value | description                                                  |
| :-------------- | :----------------------------------------------------------- |
| `enable`        | BOOL type, set the performance priority mode switch. Default is `true` |

### htSetFaceDetectionDistanceLevel

value contains the following parameters:

| parameter value | description                                                  |
| :-------------- | :----------------------------------------------------------- |
| `level`         | Int type, set the face detection distance level (3 levels available: 1, 2, 3). Default is level 1. |

### htSetFaceDetectorType

value contains the following parameters:

| parameter value | description                                         |
| :-------------- | :-------------------------------------------------- |
| `type`          | Int type, set the face detector type. Default is 0. |

### htGetVersionCode

value has no parameters.

### htGetVersion

value has no parameters.
