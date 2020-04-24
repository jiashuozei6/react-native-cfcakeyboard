//
//  SipEditText.m
//  SipEditText
//
//  Created by Elvis on 2020/4/20.
//  Copyright © 2020 Elvis. All rights reserved.
//

#import "SipEditText.h"
//#import "CFCASIPInputField.h"
#import "DLInputField.h"
#import "ErrorCode.h"

@interface SipEditText () <CFCASIPInputFieldDelegate>

@property(nonatomic, strong) DLInputField *inputField;

@property(nonatomic, strong) RCTResponseSenderBlock keyboardHeightSuccess;
@property(nonatomic, strong) RCTResponseSenderBlock keyboardHeightFailure;

@end
@implementation SipEditText
RCT_EXPORT_MODULE(SipEditText)

RCT_EXPORT_VIEW_PROPERTY(sipkeyBoardType, NSString *)
RCT_EXPORT_VIEW_PROPERTY(isEncryptState, BOOL)
RCT_EXPORT_VIEW_PROPERTY(serverRandom, NSString *)
RCT_EXPORT_VIEW_PROPERTY(inputRegex, NSString *)
RCT_EXPORT_VIEW_PROPERTY(isWithKayAnimation, BOOL)

RCT_EXPORT_VIEW_PROPERTY(minLength, int)
RCT_EXPORT_VIEW_PROPERTY(maxLength, int)
RCT_EXPORT_VIEW_PROPERTY(hasButtonClickSound, BOOL)
RCT_EXPORT_VIEW_PROPERTY(keyboardDisorderType, NSString *)
RCT_EXPORT_VIEW_PROPERTY(isLastCharacterShown, BOOL)
RCT_EXPORT_VIEW_PROPERTY(outputValueType, int)
RCT_EXPORT_VIEW_PROPERTY(isOutSideDisappear, BOOL)
RCT_EXPORT_VIEW_PROPERTY(keyboardCipherType, int)
RCT_EXPORT_VIEW_PROPERTY(spaceKeyIcon, NSString *)

RCT_EXPORT_VIEW_PROPERTY(hint, NSString *)
RCT_EXPORT_VIEW_PROPERTY(hintSize, CGFloat)
RCT_EXPORT_VIEW_PROPERTY(hintColor, NSString *)

RCT_EXPORT_VIEW_PROPERTY(nativeID, NSString *)

/**
 初始化输入框

 @return 输入框
 */
- (UIView *)view {
    
    self.inputField = [[DLInputField alloc] initWithFrame:CGRectMake(0, 0, 100, 30)];
    self.inputField.sipInputFieldDelegate = self;
    self.inputField.backgroundColor = [UIColor clearColor];
    return self.inputField;
}
- (BOOL)onKeyDone:(CFCASIPInputField *)sipInputField {
    
    return nil;
}

- (void)onSIPInputFieldTextDidChanged:(CFCASIPInputField *)sipInputField
                      withOperateType:(SIPOperateType)operateType {
    
    
}

RCT_EXPORT_METHOD(setSipKeyBoardType:(NSDictionary *)arguments success:(RCTResponseSenderBlock)successCallback failure:(RCTResponseSenderBlock)failureCallback) {
    
    NSUInteger nativeID = [arguments[@"nativeID"] integerValue];
    NSString *sipkeyBoardType = arguments[@"sipkeyBoardType"];
    
    UIView *view = [[self getRootView] viewWithTag:nativeID];
    if ([view isKindOfClass:[DLInputField class]]) {
        
        DLInputField *inputField = (DLInputField *)view;
        if ([sipkeyBoardType isEqualToString:@"NUMBER"]) {
            
            inputField.emSipKeyboardType = SIP_KEYBOARD_TYPE_STANDARD_DIGITAL;
        } else {
            
            inputField.emSipKeyboardType = SIP_KEYBOARD_TYPE_COMPLETE;
        }
        successCallback(@[@{@"code": @"0", @"msg": @"成功"}]);

    } else {
        
        failureCallback(@[@{@"code": @"80070057", @"msg": @"未找到该组件"}]);
    }
}

RCT_EXPORT_METHOD(setCipherType:(NSDictionary *)arguments success:(RCTResponseSenderBlock)successCallback failure:(RCTResponseSenderBlock)failureCallback) {
    
    NSUInteger nativeID = [arguments[@"nativeID"] integerValue];
    NSString *cipherType = arguments[@"cipherType"];
    
    UIView *view = [[self getRootView] viewWithTag:nativeID];
    if ([view isKindOfClass:[DLInputField class]]) {
        
        DLInputField *inputField = (DLInputField *)view;
        inputField.cipherType = [cipherType intValue];
        successCallback(@[@{@"code": @"0", @"msg": @"成功"}]);

    } else {
        
        failureCallback(@[@{@"code": @"80070057", @"msg": @"未找到该组件"}]);
    }
}

RCT_EXPORT_METHOD(inputEqualsWith:(NSDictionary *)arguments success:(RCTResponseSenderBlock)successCallback failure:(RCTResponseSenderBlock)failureCallback) {
    
    NSUInteger nativeID1 = [arguments[@"nativeID1"] integerValue];
    NSUInteger nativeID2 = [arguments[@"nativeID2"] integerValue];
    
    UIView *view1 = [[self getRootView] viewWithTag:nativeID1];
    UIView *view2 = [[self getRootView] viewWithTag:nativeID2];
    
    if ([view1 isKindOfClass:[DLInputField class]] && [view2 isKindOfClass:[DLInputField class]]) {
        
        BOOL isEqual = NO;
        DLInputField *inputField1 = (DLInputField *)view1;
        DLInputField *inputField2 = (DLInputField *)view2;
        NSInteger errorCode = SIP_INPUT_FIELD_OK;
        isEqual = [inputField1 checkInputValueEqual:inputField2 withError:&errorCode];
        if (isEqual) {
            
            successCallback(@[@{@"code": @"0", @"msg": @"安全键盘一内容与安全键盘二内容一致！"}]);
        } else {
            if (errorCode == SIP_INPUT_FIELD_OK) {
                
                failureCallback(@[@{@"code": @"0", @"msg": @"安全键盘一内容与安全键盘二内容不一致！"}]);
            } else {
                
                failureCallback(@[@{@"code": [NSString stringWithFormat:@"0x%08X", (int)errorCode], @"msg": [NSString stringWithFormat:@"安全键盘一内容与安全键盘二内容匹配检测出现异常，错误码为0x%08X！", (int)errorCode]}]);
            }
        }
    } else {
        
        failureCallback(@[@{@"code": @"80070057", @"msg": @"未找到该组件"}]);
    }
}

RCT_EXPORT_METHOD(getEncryptRandomNum:(NSDictionary *)arguments success:(RCTResponseSenderBlock)successCallback failure:(RCTResponseSenderBlock)failureCallback) {

    NSUInteger nativeID = [arguments[@"nativeID"] integerValue];
    NSString *serverRandom = arguments[@"serverRandom"];
    
    UIView *view = [[self getRootView] viewWithTag:nativeID];
    if ([view isKindOfClass:[DLInputField class]]) {
        
        DLInputField *inputField = (DLInputField *)view;
        inputField.strServerRandom = serverRandom;
        NSString *clientRandomEncryptedData = nil;
        NSInteger clientErrorCode = SIP_INPUT_FIELD_OK;
        
        clientRandomEncryptedData = [inputField getEncryptedClientRandomWithError:&clientErrorCode];
        if (clientErrorCode != SIP_INPUT_FIELD_OK) {

            failureCallback(@[@{@"code": [NSString stringWithFormat:@"0x%08X\n", (int)clientErrorCode], @"msg": [NSString stringWithFormat:@"获取客户端随机数加密失败，错误码:0x%08X\n", (int)clientErrorCode]}]);
        } else {
            
            successCallback(@[@{@"code": @"0", @"msg": clientRandomEncryptedData}]);
        }
    } else {
        
        failureCallback(@[@{@"code": @"80070057", @"msg": @"未找到该组件"}]);
    }
}

RCT_EXPORT_METHOD(getEncryptInput:(NSDictionary *)arguments success:(RCTResponseSenderBlock)successCallback failure:(RCTResponseSenderBlock)failureCallback) {

    NSUInteger nativeID = [arguments[@"nativeID"] integerValue];
        NSString *serverRandom = arguments[@"serverRandom"];
        
        UIView *view = [[self getRootView] viewWithTag:nativeID];
        if ([view isKindOfClass:[DLInputField class]]) {
            
            DLInputField *inputField = (DLInputField *)view;
            inputField.strServerRandom = serverRandom;
            NSString *encryptedData = nil;
            NSInteger outputValueErrorCode = SIP_INPUT_FIELD_OK;
            encryptedData = [inputField getEncryptedDataWithError:&outputValueErrorCode];
            
            if (outputValueErrorCode != SIP_INPUT_FIELD_OK) {

                failureCallback(@[@{@"code": [NSString stringWithFormat:@"0x%08X\n", (int)outputValueErrorCode], @"msg": [NSString stringWithFormat:@"获取加密内容失败，错误码0x%08X\n", (int)outputValueErrorCode]}]);
            } else {
                
                successCallback(@[@{@"code": @"0", @"msg": encryptedData}]);
            }
        } else {
            
            failureCallback(@[@{@"code": @"80070057", @"msg": @"未找到该组件"}]);
        }
}

//
RCT_EXPORT_METHOD(getInputLength:(NSDictionary *)arguments success:(RCTResponseSenderBlock)successCallback failure:(RCTResponseSenderBlock)failureCallback) {
    
    NSUInteger nativeID = [arguments[@"nativeID"] integerValue];
    
    UIView *view = [[self getRootView] viewWithTag:nativeID];
    if ([view isKindOfClass:[DLInputField class]]) {
        
        DLInputField *inputField = (DLInputField *)view;
        successCallback(@[@{@"code": @"0", @"msg": [NSString stringWithFormat:@"%lu", (unsigned long)inputField.text.length]}]);
    } else {
        
        failureCallback(@[@{@"code": @"80070057", @"msg": @"未找到该组件"}]);
    }
}

RCT_EXPORT_METHOD(getVersion:(NSDictionary *)arguments success:(RCTResponseSenderBlock)successCallback failure:(RCTResponseSenderBlock)failureCallback) {
    NSUInteger nativeID = [arguments[@"nativeID"] integerValue];
    
    UIView *view = [[self getRootView] viewWithTag:nativeID];
    if ([view isKindOfClass:[DLInputField class]]) {
        
        DLInputField *inputField = (DLInputField *)view;
        NSString *version = [inputField getVersion];
        if (version.length != 0) {
            
            successCallback(@[@{@"code": @"0", @"msg": version}]);
        } else {
            
            failureCallback(@[@{@"code": @"0", @"msg": @"未获取到版本号"}]);
        }
    } else {
        
        failureCallback(@[@{@"code": @"80070057", @"msg": @"未找到该组件"}]);
    }
}

RCT_EXPORT_METHOD(clear:(NSDictionary *)arguments success:(RCTResponseSenderBlock)successCallback failure:(RCTResponseSenderBlock)failureCallback) {
    
    NSUInteger nativeID = [arguments[@"nativeID"] integerValue];
    
    UIView *view = [[self getRootView] viewWithTag:nativeID];
    if ([view isKindOfClass:[DLInputField class]]) {
        
        DLInputField *inputField = (DLInputField *)view;
        NSUInteger code = [inputField clearAllInputCharacters];
        if (code == 0) {
            
            successCallback(@[@{@"code": @"0", @"msg": @"成功"}]);
        } else {
            
            failureCallback(@[@{@"code": [NSString stringWithFormat:@"%lu", (unsigned long)code], @"msg": @"失败"}]);
        }
    } else {
        
        failureCallback(@[@{@"code": @"80070057", @"msg": @"未找到该组件"}]);
    }
}

RCT_EXPORT_METHOD(isKeyBoardShowing:(NSDictionary *)arguments success:(RCTResponseSenderBlock)successCallback failure:(RCTResponseSenderBlock)failureCallback) {

    NSUInteger nativeID = [arguments[@"nativeID"] integerValue];
    
    UIView *view = [[self getRootView] viewWithTag:nativeID];
    if ([view isKindOfClass:[DLInputField class]]) {
        
        DLInputField *inputField = (DLInputField *)view;
        if ([inputField isFirstResponder]) {
            
            successCallback(@[@{@"code": @"0", @"msg": @"键盘显示"}]);
        } else {
            
            failureCallback(@[@{@"code": @"0", @"msg": @"键盘隐藏"}]);
        }
    } else {
        
        failureCallback(@[@{@"code": @"80070057", @"msg": @"未找到该组件"}]);
    }
}

RCT_EXPORT_METHOD(getKeyBoardHeight:(NSDictionary *)arguments success:(RCTResponseSenderBlock)successCallback failure:(RCTResponseSenderBlock)failureCallback) {

    self.keyboardHeightSuccess = successCallback;
    self.keyboardHeightFailure = failureCallback;

    NSUInteger nativeID = [arguments[@"nativeID"] integerValue];
    UIView *view = [[self getRootView] viewWithTag:nativeID];
    if ([view isKindOfClass:[DLInputField class]]) {
        
        [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(keyboardWillShow:) name:UIKeyboardWillShowNotification object:nil];
    } else {
        
        failureCallback(@[@{@"code": @"80070057", @"msg": @"未找到该组件"}]);
    }
}
//当键盘出现或改变时调用
- (void)keyboardWillShow:(NSNotification *)note
{
    //取出键盘最终的frame
    CGRect rect = [note.userInfo[UIKeyboardFrameEndUserInfoKey] CGRectValue];
    self.keyboardHeightSuccess(@[@{@"code": @"0", @"msg": [NSString stringWithFormat:@"%f", rect.size.height]}]);
}

RCT_EXPORT_METHOD(setSpaceKeyIcon:(NSDictionary *)arguments success:(RCTResponseSenderBlock)successCallback failure:(RCTResponseSenderBlock)failureCallback) {

    NSUInteger nativeID = [arguments[@"nativeID"] integerValue];
    NSString *spaceKeyIcon = arguments[@"spaceKeyIcon"];

    UIView *view = [[self getRootView] viewWithTag:nativeID];
    if ([view isKindOfClass:[DLInputField class]]) {
        
        DLInputField *inputField = (DLInputField *)view;
        NSData *imageData = [[NSData alloc] initWithBase64EncodedString:spaceKeyIcon options:0];
        UIImage *image = [UIImage imageWithData:imageData];
        [inputField setImageForSpaceKey:image];
    } else {
        
        failureCallback(@[@{@"code": @"80070057", @"msg": @"未找到该组件"}]);
    }
}

RCT_EXPORT_METHOD(setDisorderType:(NSDictionary *)arguments success:(RCTResponseSenderBlock)successCallback failure:(RCTResponseSenderBlock)failureCallback) {

    NSUInteger nativeID = [arguments[@"nativeID"] integerValue];
    NSString *disorderType = arguments[@"disorderType"];

    UIView *view = [[self getRootView] viewWithTag:nativeID];
    if ([view isKindOfClass:[DLInputField class]]) {
        
        DLInputField *inputField = (DLInputField *)view;
        if ([disorderType isEqualToString:@"ALL"]) {
            
            inputField.disorderType = SIP_FULLKEYBOARDDISORDER_TYPE_ALL;
        } else if ([disorderType isEqualToString:@"NONE"]) {
            
            inputField.disorderType = SIP_FULLKEYBOARDDISORDER_TYPE_NONE;
        } else {
            
            inputField.disorderType = SIP_FULLKEYBOARDDISORDER_TYPE_DIGITAL_ONLY;
        }
        successCallback(@[@{@"code": @"0", @"msg": @"设置成功"}]);
    } else {
        
        failureCallback(@[@{@"code": @"80070057", @"msg": @"未找到该组件"}]);
    }
}

RCT_EXPORT_METHOD(getCipherAttributes:(NSDictionary *)arguments success:(RCTResponseSenderBlock)successCallback failure:(RCTResponseSenderBlock)failureCallback) {

    NSUInteger nativeID = [arguments[@"nativeID"] integerValue];

    UIView *view = [[self getRootView] viewWithTag:nativeID];
    if ([view isKindOfClass:[DLInputField class]]) {
        
        DLInputField *inputField = (DLInputField *)view;
        
        successCallback(@[@{@"code": @"0", @"msg": @"设置成功"}]);
    } else {
        
        failureCallback(@[@{@"code": @"80070057", @"msg": @"未找到该组件"}]);
    }
}

- (UIView *)getRootView {
    
    UIViewController *viewController = [[[UIApplication sharedApplication] keyWindow] rootViewController];
    return viewController.view;
}
@end
