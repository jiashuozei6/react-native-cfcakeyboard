//
//  DLInputField.m
//  DoubleConversion
//
//  Created by Elvis on 2020/4/20.
//

#import "DLInputField.h"

static NSString * JFHint = @"请输入";
static CGFloat JFHintSize = 17.0f;
static NSString * JFHintColor = @"#CCCCCC";

@implementation DLInputField

- (void)setSipkeyBoardType:(NSString *)sipkeyBoardType {
  
    if ([sipkeyBoardType isEqualToString:@"NUMBER"]) {
        
        self.emSipKeyboardType = SIP_KEYBOARD_TYPE_STANDARD_DIGITAL;
    } else {
        
        self.emSipKeyboardType = SIP_KEYBOARD_TYPE_COMPLETE;
    }
}

- (void)setIsEncryptState:(BOOL)isEncryptState {
    
    self.bIsNeedInputEncrypt = isEncryptState;
}

- (void)setServerRandom :(NSString *)serverRandom {
    
    self.strServerRandom = serverRandom;
}
- (void)setInputRegex:(NSString *)inputRegex {
    
    self.strInputRegex = inputRegex;
}

- (void)setIsWithKayAnimation:(BOOL)isWithKayAnimation {
    
    self.bIsNeedKeyboardAnimation = isWithKayAnimation;
}

- (void)setMinLength:(int)minLength {
    
    self.nMinInputLength = minLength;
}
- (void)setMaxLength:(int)maxLength {
    
    self.nMaxInputLength = maxLength;
}
- (void)setHasButtonClickSound:(BOOL)hasButtonClickSound {
    
    self.bHaveButtonClickSound = hasButtonClickSound;
}
- (void)setKeyboardDisorderType:(NSString *)disorderTypeLocal {
    
    if ([disorderTypeLocal isEqualToString:@"ALL"]) {
        
        self.disorderType = SIP_FULLKEYBOARDDISORDER_TYPE_ALL;
    } else if ([disorderTypeLocal isEqualToString:@"NONE"]) {
        
        self.disorderType = SIP_FULLKEYBOARDDISORDER_TYPE_NONE;
    } else {
        
        self.disorderType = SIP_FULLKEYBOARDDISORDER_TYPE_DIGITAL_ONLY;
    }
}
- (void)setIsLastCharacterShown:(BOOL)isLastCharacterShown {
    
    self.bIsShowLastCharacter = isLastCharacterShown;
}
- (void)setOutputValueType:(int)outputValueType {
    
    self.emOutputValueType = outputValueType;
}
- (void)setIsOutSideDisappear:(BOOL)isOutSideDisappear {
    
    if (isOutSideDisappear) {
        
        UIViewController *rootController = [self getRootViewController];
        UITapGestureRecognizer *tap = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(viewTapped:)];
        [rootController.view addGestureRecognizer:tap];
    }
}
- (void)setKeyboardCipherType:(int)cipherKeyboardType {
    
    self.cipherType = cipherKeyboardType;
}

- (void)setSpaceKeyIcon:(NSString *)spaceKeyIcon {
    
    NSData *imageData = [[NSData alloc] initWithBase64EncodedString:spaceKeyIcon options:0];
    UIImage *image = [UIImage imageWithData:imageData];
    [self setImageForSpaceKey:image];
}

- (void)setHint:(NSString *)hint {
    
    JFHint = hint;
    self.placeholder = hint;
}

- (void)setHintSize:(CGFloat)hintSize {
    
    JFHintSize = hintSize;
    UIColor *color = [self colorWithHexString:JFHintColor alpha:1.0f];
    NSMutableParagraphStyle *style = [[NSMutableParagraphStyle alloc] init];
    NSAttributedString *attributedString = [[NSAttributedString alloc] initWithString:JFHint attributes:@{NSFontAttributeName : [UIFont systemFontOfSize:hintSize], NSForegroundColorAttributeName : color, NSParagraphStyleAttributeName : style}];
    self.attributedPlaceholder = attributedString;
}

- (void)setHintColor:(NSString *)hintColor {
    
    JFHintColor = hintColor;
    UIColor *color = [self colorWithHexString:JFHintColor alpha:1.0f];
    NSMutableParagraphStyle *style = [[NSMutableParagraphStyle alloc] init];
    NSAttributedString *attributedString = [[NSAttributedString alloc] initWithString:JFHint attributes:@{NSFontAttributeName : [UIFont systemFontOfSize:JFHintSize], NSForegroundColorAttributeName : color, NSParagraphStyleAttributeName : style}];
    self.attributedPlaceholder = attributedString;
}

- (void)setNativeID:(NSString *)nativeID {
    
    self.tag = [nativeID integerValue];
}
/// 点击事件监听
/// @param tap 点击事件
- (void)viewTapped:(UITapGestureRecognizer *)tap {
    
    if ([self isFirstResponder]) {
        
        [self resignFirstResponder];
    }
}

- (UIViewController *)getRootViewController {
    
    return [[[UIApplication sharedApplication] keyWindow] rootViewController];
}

- (UIColor *)colorWithHex:(int)hexNumber alpha:(CGFloat)alpha {
    
    if (hexNumber > 0xFFFFFF) {
        
        hexNumber = 0xFFFFFF;
    }
    CGFloat red   = ((hexNumber >> 16) & 0xFF) / 255.0;
    CGFloat green = ((hexNumber >> 8) & 0xFF) / 255.0;
    CGFloat blue  = (hexNumber & 0xFF) / 255.0;
    UIColor *color = [UIColor colorWithRed:red green:green blue:blue alpha:alpha];
    return color;
}
- (UIColor *)colorWithHexString:(NSString *)hexString alpha:(CGFloat)alpha {
  
  hexString = [[hexString stringByTrimmingCharactersInSet:[NSCharacterSet whitespaceAndNewlineCharacterSet]] uppercaseString];
  
  UIColor *defaultColor = [UIColor clearColor];
  if (hexString.length < 6) {
    return defaultColor;
  }
  if ([hexString hasPrefix:@"#"]) {
    
    hexString = [hexString substringFromIndex:1];
  }
  if ([hexString hasPrefix:@"0X"]) {
    
    hexString = [hexString substringFromIndex:2];
  }
  if (hexString.length != 6) {
    
    return defaultColor;
  }
  NSScanner *scanner = [NSScanner scannerWithString:hexString];
  unsigned int hexNumber;
  if (![scanner scanHexInt:&hexNumber]) {
    
    return defaultColor;
  }
  const char *char_str = [hexString cStringUsingEncoding:NSASCIIStringEncoding];
  int hexNum;
  sscanf(char_str, "%x", &hexNum);
  
  return [self colorWithHex:hexNumber alpha:alpha];
}
@end
