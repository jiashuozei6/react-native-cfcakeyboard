//
//  CFCASIPInputField.h
//  SIPInputField
//
//  Implement all functions of SIP input field
//
//  Created by XiaoQianyu on 06/06/18.
//  Copyright 2018 CFCA, Inc. All rights reserved.
// 

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

typedef NS_ENUM(NSUInteger, SIPKeyboardType) {
  SIP_KEYBOARD_TYPE_COMPLETE = 0,
  SIP_KEYBOARD_TYPE_STANDARD_DIGITAL
};

typedef NS_ENUM(NSUInteger, SIPOutputValueType) {
  OUTPUT_VALUE_TYPE_HASH_DATA = 1,
  OUTPUT_VALUE_TYPE_PLAIN_DATA = 2
};

typedef NS_ENUM(NSUInteger, SIPCipherType) {
  SIP_KEYBOARD_CIPHER_TYPE_SM2 = 0,
  SIP_KEYBOARD_CIPHER_TYPE_RSA = 1
};

typedef NS_ENUM(NSUInteger, SIPDisorderType) {
  SIP_FULLKEYBOARDDISORDER_TYPE_NONE = 0,
  SIP_FULLKEYBOARDDISORDER_TYPE_DIGITAL_ONLY = 1,
  SIP_FULLKEYBOARDDISORDER_TYPE_ALL = 2
};

typedef NS_ENUM(NSUInteger, SIPOperateType) {
  SIP_INPUTFIELD_OPERATE_CLEAR = 0,
  SIP_INPUTFIELD_OPERATE_INSERT,
  SIP_INPUTFIELD_OPERATE_DELETE
};

@class CFCASIPInputField;

@protocol CFCASIPInputFieldDelegate<NSObject>

- (BOOL)onKeyDone:(CFCASIPInputField *)sipInputField;

- (void)onSIPInputFieldTextDidChanged:(CFCASIPInputField *)sipInputField
                      withOperateType:(SIPOperateType)operateType;

@end

@interface CFCASIPInputField : UITextField

@property(nonatomic, assign) NSInteger nMinInputLength;
@property(nonatomic, assign) NSInteger nMaxInputLength;
@property(nonatomic, assign) BOOL bIsShowLastCharacter;
@property(nonatomic, assign) BOOL bIsNeedInputEncrypt;
@property(nonatomic, assign) SIPKeyboardType emSipKeyboardType;
@property(nonatomic, assign) BOOL bIsNeedKeyboardAnimation;
@property(nonatomic, assign) BOOL bHaveButtonClickSound;
@property(nonatomic, copy) NSString *strServerRandom;
@property(nonatomic, copy) NSString *strInputRegex;
@property(nonatomic, assign) SIPOutputValueType emOutputValueType;
@property(nonatomic, assign) SIPCipherType cipherType;
@property(nonatomic, assign) SIPDisorderType disorderType;
@property(nonatomic, assign, readonly) NSInteger lastErrorCode;
@property(nonatomic, weak) id<CFCASIPInputFieldDelegate> sipInputFieldDelegate;

/*!
 @function
 @abstract   set image for Space Key ,eg. company Logo
 @param      [in]spaceImage : The image you want to set for Space Key ,default or set nil is
 CFCALogo,image should be size of
 @param      [out]errorCode : when an error occur,errorCode will be set
 @result     [BOOL]success : YES , fail : NO
 */
- (BOOL)setImageForSpaceKey:(UIImage *)spaceImage;

/*!
 @function
 @abstract   Check value of other SIPInputField's value wether match selfs
 @param      [out]errorCode : when an error occur,errorCode will be set
 @result     [BOOL]match : YES , not match : NO
 */
- (BOOL)checkInputValueEqual:(CFCASIPInputField *)otherSIPInputField
                   withError:(NSInteger *)errorCode;

/*!
 @function
 @abstract   Get cipher attributes
 @param      [out]errorCode : When an error occur, errorCode would be set
 @result     [NSArray]length: 8, type:NSNumber, each element value represents as following:
             [0]: contains upper letter (0: NO, 1: YES)
             [1]: contains lower letter (0: NO, 1: YES)
             [2]: contains number (0: NO, 1: YES)
             [3]: contains symbol (0: NO, 1: YES)
             [4]: all are consecutive letters (0: NO, 1: YES)
             [5]: all are duplicated letters (0: NO, 1: YES)
             [6]: longest size for the consecutive letters
             [7]: longest size for the duplicated letters
 */
- (NSArray *)cipherAttributesWithError:(NSInteger *)errorCode;

/*!
 @function
 @abstract   get encrypted input data when needEncrypt is YES or plain data when needEncrypt is NO.
 @param      [out]errorCode : when an error occur,errorCode will be set
 @result     [NSString*]the encrypted data when needEncrypt is YES or plain data when needEncrypt is
 NO
 */
- (NSString *)getEncryptedDataWithError:(NSInteger *)errorCode;

/*!
 @function
 @abstract   get encrypted input data when needEncrypt is YES or plain data when needEncrypt is NO.
 @param      [out]errorCode : when an error occur,errorCode will be set
 @result     [NSString*]the encrypted data when needEncrypt is YES or plain data when needEncrypt is
 NO
 */
- (NSString *)getEncryptedClientRandomWithError:(NSInteger *)errorCode;

/*!
 @function
 @abstract   get length of input data
 @param      [out]errorCode : when an error occur,errorCode will be set
 @result     [NSInteger] length of input data
 */
- (NSInteger)getInputLengthWithError:(NSInteger *)errorCode;

/*!
 @function
 @abstract   get version of current app
 @result     [NSInteger] length of input data
 */
- (NSString *)getVersion;

/*!
 @function
 @abstract   clear all input data
 @result     [NSInteger] success:0, failed:error code
 */
- (NSInteger)clearAllInputCharacters;

@end

@interface CFCAGridSIPInputField : CFCASIPInputField

@property(nonatomic, assign) NSUInteger gridNumber;
@property(nonatomic, assign) int gridBorderWidth;
@property(nonatomic, assign) int gridTextSize;
@property(nonatomic, strong) UIColor *gridBorderColor;
@property(nonatomic, strong) UIColor *gridTextColor;

@end
