package com.goosepro.cfcakeyboard;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;

public class CFCAKeyboardErrorCode {
    /** 参数错误 */
    public static final String ERROR_INVALID_PARAMETER = "80070057";

    /** 加密失败 */
    public static final String ERROR_ENCRYPTION_FAILED = "80071770";

    /** 解密失败 */
    public static final String ERROR_DECRYPTION_FAILED = "80071771";

    /** 错误的哈希 */
    public static final String NTE_BAD_HASH = "80090002";

    /** 签名错误 */
    public static final String NTE_BAD_SIGNATURE = "80090006";

    /** 错误的算法 */
    public static final String NTE_BAD_ALGID = "80090008";

    /** ASN1编码错误 */
    public static final String CRYPT_E_ASN1_ERROR = "80093100";

    /** BASE64编码错误 */
    public static final String ERROR_BASE64_ENCODING_FAILED = "A0071004";

    /** BASE64解码错误 */
    public static final String ERROR_BASE64_DECODING_FAILED = "A0071005";

    /** 加密句柄无效 */
    public static final String ERROR_INVALID_SIP_HANDLE = "E0010001";

    /** 不支持的算法 */
    public static final String ERROR_ALG_TYPE_NOT_SUPPORTED = "E0010002";

    /** 输入数据为空 */
    public static final String ERROR_INPUT_VALUE_NULL = "E0010003";

    /** 服务端随机数错误 */
    public static final String ERROR_SERVER_RANDOM_NULL = "E0010004";

    /** 输入数据不匹配正则表达式 */
    public static final String ERROR_INPUT_VALUE_NOT_MATCH_REG = "E0010005";

    /** 输出数据类型未知 */
    public static final String ERROR_UNKNOWN_OUTPUT_VALUE_TYPE = "E0010006";

    /** 服务器端随机数长度错误 */
    public static final String ERROR_SERVER_RANDOM_LENGTH = "E0010007";

    /** 不匹配的字符 */
    public static final String ERROR_INVALID_MAPPED_CHARACTER = "E0010008";

    /** 字符不匹配输入正则表达式 */
    public static final String ERROR_CHARACTER_NOT_MATCH_INPUT_REGEX = "E0010009";

    /** 不可用的公钥 */
    public static final String ERROR_INVALID_PUBLIC_KEY = "E001000A";

    /** 创建输入框失败 */
    public static final String ERROR_CREATE_INPUT_FIELD_FAILURE = "C0010001";

    /** 输入长度超出范围 */
    public static final String ERROR_INPUT_LENGTH_OUT_RANGE = "C0010002";

    /** 输入内容不匹配正则表达式 */
    public static final String ERROR_INPUT_NOT_MATCH_REGEX = "C0010003";

    /** 未知的键盘类型 */
    public static final String ERROR_UNKNOWN_KEYBOARD_TYPE = "C0010004";

    /** 键盘为空 */
    public static final String SIP_KEYBOARD_IS_NULL = "C0010005";

    /** 待比较内容的格式不支持 */
    public static final String ERROR_COMPARE_CONTENT_FORMAT = "C0010006";

    /** 输入内容过长 */
    public static final String ERROR_INPUT_LENGTH_OUT_RANGE2 = "B0010001";

    /** 释放加密模块句柄失败 */
    public static final String ERROR_RELEASE_SIP_KERNAL_FAILURE = "B0010002";

    /** 创建键盘失败 */
    public static final String ERROR_CREATE_KEYBOARD_FAILURE = "B0010003";

    /** 创建加密模块句柄失败 */
    public static final String ERROR_CREATE_SIP_KERNAL_FAILURE = "B0010004";

    /** 清空全部内容失败 */
    public static final String ERROR_CLEAR_ALL_CONTENT_FAILURE = "B0010005";

    /** 安全状态被禁止 */
    public static final String ERROR_SECURITY_STATE_OFF = "B0010006";

    /** 输入内容过短 */
    public static final String ERROR_INPUT_LENGTH_IS_TOO_SHORT = "B0010007";


    public static WritableMap map;

    public static WritableMap getErrorCallResult(String errorCode) {
        switch (errorCode) {
            case ERROR_INVALID_PARAMETER:
                return pushMap(errorCode, "参数错误");
            case ERROR_ENCRYPTION_FAILED:
                return pushMap(errorCode, "加密失败");
            case ERROR_DECRYPTION_FAILED:
                return pushMap(errorCode, "解密失败");
            case NTE_BAD_HASH:
                return pushMap(errorCode, "错误的哈希");
            case NTE_BAD_SIGNATURE:
                return pushMap(errorCode, "签名错误");
            case NTE_BAD_ALGID:
                return pushMap(errorCode, "错误的算法");
            case CRYPT_E_ASN1_ERROR:
                return pushMap(errorCode, "ASN1编码错误");
            case ERROR_BASE64_ENCODING_FAILED:
                return pushMap(errorCode, "BASE64编码错误");
            case ERROR_BASE64_DECODING_FAILED:
                return pushMap(errorCode, "BASE64解码错误");
            case ERROR_INVALID_SIP_HANDLE:
                return pushMap(errorCode, "加密句柄无效");
            case ERROR_ALG_TYPE_NOT_SUPPORTED:
                return pushMap(errorCode, "不支持的算法");
            case ERROR_INPUT_VALUE_NULL:
                return pushMap(errorCode, "输入数据为空");
            case ERROR_SERVER_RANDOM_NULL:
                return pushMap(errorCode, "服务端随机数错误");
            case ERROR_INPUT_VALUE_NOT_MATCH_REG:
                return pushMap(errorCode, "输入数据不匹配正则表达式");
            case ERROR_UNKNOWN_OUTPUT_VALUE_TYPE:
                return pushMap(errorCode, "输出数据类型未知");
            case ERROR_SERVER_RANDOM_LENGTH:
                return pushMap(errorCode, "服务器端随机数长度错误");
            case ERROR_INVALID_MAPPED_CHARACTER:
                return pushMap(errorCode, "不匹配的字符");
            case ERROR_CHARACTER_NOT_MATCH_INPUT_REGEX:
                return pushMap(errorCode, "字符不匹配输入正则表达式");
            case ERROR_INVALID_PUBLIC_KEY:
                return pushMap(errorCode, "不可用的公钥");
            case ERROR_CREATE_INPUT_FIELD_FAILURE:
                return pushMap(errorCode, "创建输入框失败");
            case ERROR_INPUT_LENGTH_OUT_RANGE:
                return pushMap(errorCode, "输入长度超出范围");
            case ERROR_INPUT_NOT_MATCH_REGEX:
                return pushMap(errorCode, "输入内容不匹配正则表达式");
            case ERROR_UNKNOWN_KEYBOARD_TYPE:
                return pushMap(errorCode, "未知的键盘类型");
            case SIP_KEYBOARD_IS_NULL:
                return pushMap(errorCode, "键盘为空");
            case ERROR_COMPARE_CONTENT_FORMAT:
                return pushMap(errorCode, "待比较内容的格式不支持");
            case ERROR_INPUT_LENGTH_OUT_RANGE2:
                return pushMap(errorCode, "输入内容过长");
            case ERROR_RELEASE_SIP_KERNAL_FAILURE:
                return pushMap(errorCode, "释放加密模块句柄失败");
            case ERROR_CREATE_KEYBOARD_FAILURE:
                return pushMap(errorCode, "创建键盘失败");
            case ERROR_CREATE_SIP_KERNAL_FAILURE:
                return pushMap(errorCode, "创建加密模块句柄失败");
            case ERROR_CLEAR_ALL_CONTENT_FAILURE:
                return pushMap(errorCode, "清空全部内容失败");
            case ERROR_SECURITY_STATE_OFF:
                return pushMap(errorCode, "安全状态被禁止");
            case ERROR_INPUT_LENGTH_IS_TOO_SHORT:
                return pushMap(errorCode, "输入内容过短");
            default:
                return pushMap("default","未知错误");
        }
    }
    public static WritableMap pushMap(String errorCode, String errorMsg){
        map = new WritableNativeMap();
        map.putString("code", errorCode);
        map.putString("msg", errorMsg);
        return map;
    }
    public static WritableMap pushMapBoolean(String errorCode, Boolean errorMsg){
        map = new WritableNativeMap();
        map.putString("code", errorCode);
        map.putBoolean("msg", errorMsg);
        return map;
    }
    public static WritableMap pushMapInt(String errorCode, int errorMsg){
        map = new WritableNativeMap();
        map.putString("code", errorCode);
        map.putInt("msg", errorMsg);
        return map;
    }
    public static WritableMap pushMapArray(String errorCode, ReadableArray errorMsg){
        map = new WritableNativeMap();
        map.putString("code", errorCode);
        map.putArray("msg", errorMsg);
        return map;
    }
}
