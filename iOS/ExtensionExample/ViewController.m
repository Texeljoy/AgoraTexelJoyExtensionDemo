//
//  ViewController.m
//  ExtensionExample
//
//  Created by N17 on 2022/8/5.
//

#import "ViewController.h"
#import "Config.h"
#import <AgoraRtcKit/AgoraRtcEngineKit.h>
#import "HTKey.h"

@interface ViewController ()<AgoraRtcEngineDelegate,AgoraMediaFilterEventDelegate>

@property (strong, nonatomic) AgoraRtcEngineKit *agoraKit;
@property (assign, nonatomic) BOOL enable;
@property (weak, nonatomic) IBOutlet UIView *localVideoView;
@property (weak, nonatomic) IBOutlet UILabel *faces;

@property (strong, nonatomic)IBOutlet UIButton *switchCameraBtn;
@property (strong, nonatomic)IBOutlet UIButton *enableExtensionBtn;
@property (strong, nonatomic)IBOutlet UIButton *setFilterBtn;
@property (strong, nonatomic)IBOutlet UIButton *setARItmeBtn;

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    self.view.backgroundColor = UIColor.blackColor;
    
    [self.switchCameraBtn setTitle:@"switchCamera" forState:UIControlStateNormal];
    [self.switchCameraBtn setHidden:YES];
    [self.switchCameraBtn setTintColor:[UIColor colorWithRed:0 green:1.0 blue:51.0/255 alpha:1.0]];
    
    [self.enableExtensionBtn setTitle:@"enableExtension" forState:UIControlStateNormal];
    [self.setFilterBtn setTitle:@"setStyleFilter" forState:UIControlStateNormal];
    [self.setARItmeBtn setTitle:@"setSticker" forState:UIControlStateNormal];
    
    self.faces.text = @"faces:0";
    [self initRtcEngine];
    
}

- (void)initExtension{
    // 在线鉴权方式
    [self.agoraKit setExtensionPropertyWithVendor:@"Texeljoy"
                                        extension:@"HTEffect"
                                              key:@"htInitHTEffectOnline"
                                            value:[self toJson:@{
                                                @"appId":HT_APPID
                                            }]];
    
    
    // 离线鉴权方式
//    [self.agoraKit setExtensionPropertyWithVendor:@"Texeljoy"
//                                        extension:@"HTEffect"
//                                              key:@"htInitHTEffectOffline"
//                                            value:[self toJson:@{
//                                                @"license":HT_LICENSE
//                                            }]];
}

- (void)initRtcEngine {
    AgoraRtcEngineConfig *config = [AgoraRtcEngineConfig new];
    config.appId = appID;
    config.eventDelegate = self;
    self.agoraKit = [AgoraRtcEngineKit sharedEngineWithConfig:config
                                                     delegate:self];
    AgoraVideoEncoderConfiguration *configuration = [AgoraVideoEncoderConfiguration new];
    configuration.dimensions = AgoraVideoDimension640x360;
    configuration.orientationMode = 0;
    [self.agoraKit setVideoEncoderConfiguration:configuration];
    [self enableExtension:nil];
    [self.agoraKit enableVideo];
    [self.agoraKit setChannelProfile:AgoraChannelProfileLiveBroadcasting];
    [self.agoraKit setClientRole:AgoraClientRoleBroadcaster];
    [self.agoraKit startPreview];
}


- (IBAction)switchCamera:(id)sender {
    [self.agoraKit switchCamera];
    [self.agoraKit setExtensionPropertyWithVendor:@"Texeljoy" extension:@"HTEffect" key:@"htReinit" value:@""];
}

- (IBAction)enableExtension:(id)sender {
    self.enable = !self.enable;
    [self.agoraKit enableExtensionWithVendor:@"Texeljoy"
                                   extension:@"HTEffect"
                                     enabled:self.enable];
    
    [self.agoraKit setExtensionPropertyWithVendor:@"Texeljoy"
                                        extension:@"HTEffect"
                                              key:@"htSetRenderEnable"
                                            value:[self toJson:@{
                                                @"enable":@(self.enable)
                                            }]];
    
    if (self.enable) {
        [self.enableExtensionBtn setTintColor:[UIColor colorWithRed:0 green:1.0 blue:51.0/255 alpha:1.0]];
    } else {
        [self.enableExtensionBtn setTintColor:UIColor.blackColor];
    }
}

- (IBAction)setFilter:(id)sender {
    self.setFilterBtn.tag += 1;
    NSString *name = @"";
    int value = 0;
    if (self.setFilterBtn.tag % 2 == 0) {
        name = @"";
        value = 0;
        [self.setFilterBtn setTintColor:UIColor.blackColor];
    } else {
        name = @"ziran3";
        value = 100;
        [self.setFilterBtn setTintColor:[UIColor colorWithRed:0 green:1.0 blue:51.0/255 alpha:1.0]];
    }
    
    [self.agoraKit setExtensionPropertyWithVendor:@"Texeljoy"
                                       extension:@"HTEffect"
                                             key:@"htSetFilter"
                                           value:[self toJson:@{
                                               @"type": @0, 
                                               @"name": name,
                                               @"value": @(value)
                                           }]];
}

- (IBAction)setARItme:(id)sender {
    self.setARItmeBtn.tag += 1;
    NSString *name = @"";
    if (self.setARItmeBtn.tag%2 == 0) {
        name = @"";
        [self.setARItmeBtn setTintColor:UIColor.blackColor];
    }else{
        name = @"ht_sticker_effect_smallcat";
        [self.setARItmeBtn setTintColor:[UIColor colorWithRed:0 green:1.0 blue:51.0/255 alpha:1.0]];
    }
      
    [self.agoraKit setExtensionPropertyWithVendor:@"Texeljoy" extension:@"HTEffect" key:@"htSetARItem" value:[self toJson:@{
        @"type":@0,//贴纸
        @"name":name
    }]];

}

- (NSString *)toJson:(NSDictionary *)dic {
    NSError *error;
    NSData *data =
    [NSJSONSerialization dataWithJSONObject:dic
                                    options:NSJSONWritingPrettyPrinted
                                      error:&error];
    return [[NSString alloc] initWithData:data encoding:NSUTF8StringEncoding];
}

- (void)onEvent:(NSString *)provider extension:(NSString *)extension key:(NSString *)key value:(NSString *)value{
//    NSLog(@"onEvent provider: %@ extension: %@ key: %@ value: %@", provider, extension, key, value);
    
    if([key isEqualToString:@"htInitHTEffectOfflineResult"]){
        //value
        NSLog(@"onEvent provider: %@ extension: %@ key: %@ value: %@", provider, extension, key, value);
    }
    
    if([key isEqualToString:@"htErrorCallback"]){
        //value
        NSLog(@"onEvent provider: %@ extension: %@ key: %@ value: %@", provider, extension, key, value);
    }
    
    if([key isEqualToString:@"htIsTracking"]){
        
        NSDictionary *dic = [NSJSONSerialization JSONObjectWithData:[value dataUsingEncoding:NSUTF8StringEncoding] options:NSJSONReadingMutableContainers error:nil];
    
        dispatch_async(dispatch_get_main_queue(), ^{
                // 需要在主线程执行的代码
            self.faces.text = [NSString stringWithFormat:@"faces:%@",dic[@"faces"]?:@""];
        });
        
        
    }
    
     
}

- (void)onExtensionStarted:(NSString *)provider extension:(NSString *)extension {
    if([extension isEqualToString:@"HTEffect"]){
        [self initExtension];
        AgoraRtcVideoCanvas *canvas = [AgoraRtcVideoCanvas new];
        canvas.view = self.localVideoView;
        [self.agoraKit setupLocalVideo:canvas];
        [self.agoraKit setLocalRenderMode:AgoraVideoRenderModeHidden mirror:AgoraVideoMirrorModeAuto];
    }
}

@end
