//
//  DLInputField.h
//  DoubleConversion
//
//  Created by Elvis on 2020/4/20.
//

#import "CFCASIPInputField.h"

NS_ASSUME_NONNULL_BEGIN

@interface DLInputField : CFCASIPInputField

- (void)setSipkeyBoardType:(NSString *)sipkeyBoardType;

- (void)setIsEncryptState:(BOOL)isEncryptState;

- (void)setServerRandom :(NSString *)serverRandom;

- (void)setInputRegex:(NSString *)inputRegex;

- (void)setIsWithKayAnimation:(BOOL)isWithKayAnimation;

- (void)setMinLength:(int)minLength;

- (void)setMaxLength:(int)maxLength;

- (void)setHasButtonClickSound:(BOOL)hasButtonClickSound;

- (void)setKeyboardDisorderType:(NSString *)disorderType;

- (void)setIsLastCharacterShown:(BOOL)isLastCharacterShown;

- (void)setOutputValueType:(int)outputValueType;

- (void)setIsOutSideDisappear:(BOOL)isOutSideDisappear;

- (void)setKeyboardCipherType:(int)cipherType;

- (void)setSpaceKeyIcon:(NSString *)spaceKeyIcon;

- (void)setHint:(NSString *)hint;

- (void)setHintSize:(CGFloat)hintSize;

- (void)setHintColor:(NSString *)hintColor;

- (void)setNativeID:(NSString *)nativeID;

@end

NS_ASSUME_NONNULL_END
