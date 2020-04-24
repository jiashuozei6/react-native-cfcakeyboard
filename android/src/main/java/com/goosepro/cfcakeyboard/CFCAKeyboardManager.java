package com.goosepro.cfcakeyboard;

import android.graphics.Color;
import android.os.Build;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.cfca.mobile.log.CodeException;
import com.cfca.mobile.sipedit.SipEditText;
import com.cfca.mobile.sipkeyboard.DisorderType;
import com.cfca.mobile.sipkeyboard.SIPKeyboardType;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.util.ReactFindViewUtil;

import java.util.Map;

public class CFCAKeyboardManager extends SimpleViewManager<SipEditText> {

    public static final String REACT_CLASS = "SipEditText";
    ReactApplicationContext mCallerContext;
    public SipEditText sipEditText;
    String psw = "";
    String hintStr = "请输入";
    int hintSize = 16;

    public CFCAKeyboardManager(ReactApplicationContext reactContext) {
        mCallerContext = reactContext;
    }
    @Override
    public String getName() {
        return REACT_CLASS;
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public SipEditText createViewInstance(ThemedReactContext context) {
        sipEditText = new SipEditText(context);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200,200);
        sipEditText.setBackground(context.getDrawable(R.drawable.bg_input));
        sipEditText.setHintTextColor(Color.parseColor("#CCCCCC"));
        sipEditText.setLayoutParams(layoutParams);
        sipEditText.setSipDelegator(new SipEditTextDelegatorAdapter() {
            @Override
            public void beforeKeyboardShow(SipEditText sip, int keyboardHeight) {
                super.beforeKeyboardShow(sip, keyboardHeight);
                adjustContainerBeforeKeyboardShow(sip, keyboardHeight);
            }

            @Override
            public void afterKeyboardHidden(SipEditText sip, int keyboardHeight) {
                super.afterKeyboardHidden(sip, keyboardHeight);
                mCallerContext.getCurrentActivity().getWindow().getDecorView().scrollTo(0, 0);
            }
        });
        return sipEditText;
    }
    /**
     * 组件唯一标识
     */
    @ReactProp(name = "nativeID")
    public void setnativeID(SipEditText sipEditText, @Nullable String sources) {
        sipEditText.setTag(R.id.view_tag_native_id, sources);
        ReactFindViewUtil.notifyViewRendered(sipEditText);
    }
    /**输入数据是否需要加密。设置false则会将输入数据明文显示在输入框中，设置为true则会将输入数据内存加密保存
     * 并可以通过getEncryptData()接口获取加密数据。默认为true。
     */
    @ReactProp(name = "encryptState")
    public void setEncryptState(SipEditText sipEditText, @Nullable Boolean sources) {
        sipEditText.setEncryptState(sources);
    }
    /**
     * 组件提示文本
     */
    @ReactProp(name = "hint")
    public void setHint(SipEditText sipEditText, @Nullable String sources) {
        hintStr = sources;
        setHint();
    }
    /**
     * 组件提示文本颜色
     */
    @ReactProp(name = "hintColor")
    public void setTextColorHint(SipEditText sipEditText, @Nullable String sources) {
        sipEditText.setHintTextColor(Color.parseColor(sources));
    }
    /**
     * 组件提示文本大小
     */
    @ReactProp(name = "hintSize")
    public void setHintSize(SipEditText sipEditText, @Nullable int sources) {
        hintSize = sources;
        setHint();
    }
    /**
     * 安全输入控件对应的键盘类型，目前支持的键盘类型包括：
     * 标准纯数字键盘SipKeyboardType .NUMBER_KEYBOARD，
     * 标准全键盘SipKeyboardType .QWERT_KEYBOARD。默认为全键盘。
     */
    @ReactProp(name = "sipkeyBoardType")
    public void setSipkeyBoardType(SipEditText sipEditText, @Nullable String sources) {
        if(sources != null && sources != "" && !sources.equals("")){
            if(sources.equals("NUMBER")){
                sipEditText.setSipKeyBoardType(SIPKeyboardType.NUMBER_KEYBOARD);
            }else if(sources.equals("QWERT")){
                sipEditText.setSipKeyBoardType(SIPKeyboardType.QWERT_KEYBOARD);
            }
        }
    }
    /**
     * 键盘乱序类型。（只支持在设置时乱序，乱序后不可复原）
     * DisorderType.ALL 全部乱序
     * DisorderType.NONE 不乱序
     * DisorderType.ONLY_DIGITAL 仅数字乱序
     */
    @ReactProp(name = "disorderType")
    public void setDisorderType (SipEditText sipEditText, @Nullable String sources) {
        if(sources != null && sources != "" && !sources.equals("")){
            if(sources.equals("ALL")){
                sipEditText.setDisorderType(DisorderType.ALL);
            }else if(sources.equals("NONE")){
                sipEditText.setDisorderType(DisorderType.NONE);
            }else if(sources.equals("ONLY_DIGITAL")){
                sipEditText.setDisorderType(DisorderType.ONLY_DIGITAL);
            }
        }
    }
    /**
     * 点设置键盘输入正则规则，用来过滤正在输入的字符。如果设置该参数，则不匹配该正则表达式的数据皆不会被输入；
     * 否则均可输入。默认为null
     */
    @ReactProp(name = "inputRegex")
    public void setInputRegex(SipEditText sipEditText, @Nullable String sources) {
        sipEditText.setInputRegex(sources);
    }
    /**
     * 设置键盘的按键状态，按键是否有特效。true表示有按键状态，false表示无按键状态。默认为false
     */
    @ReactProp(name = "isWithKayAnimation")
    public void setKeyAnimation(SipEditText sipEditText, @Nullable Boolean sources) {
        sipEditText.setKeyAnimation(sources);
    }
    /**
     * 设置键盘输入的最小口令长度，传入大于0的整数，当长度小于该值时获取加密结果会出错。默认值为0。
     */
    @ReactProp(name = "minLength")
    public void setPasswordMinLength(SipEditText sipEditText, @Nullable int sources) {
        sipEditText.setPasswordMinLength(sources);
    }
    /**
     * 设置键盘输入的最大口令长度，传入大于0的整数，当输入内容超过该长度后则不会再提交至输入框内。默认值为100
     */
    @ReactProp(name = "maxLength")
    public void setPasswordMaxLength(SipEditText sipEditText, @Nullable int sources) {
        sipEditText.setPasswordMaxLength(sources);
    }
    /**
     * 设置键盘按键是否有声音。true表示有声音，false表示没有声音。默认为false
     */
    @ReactProp(name = "hasButtonClickSound")
    public void setHasButtonClickSound(SipEditText sipEditText, @Nullable Boolean sources) {
        sipEditText.setHasButtonClickSound(sources);
    }
    /**
     * 设置输入框密码是否延时显示最后一位明文。true表示最后一位显示，false表示直接输入密文。默认为false
     */
    @ReactProp(name = "isLastCharacterShown")
    public void setLastCharacterShown(SipEditText sipEditText, @Nullable Boolean sources) {
        sipEditText.setLastCharacterShown(sources);
    }
    /**
     * 设置输出类型，1为对原文SHA1哈希再Base64编码后的数据进行加密；2是对原文直接加密。默认为1
     */
    @ReactProp(name = "outputValueType")
    public void setOutputValueType(SipEditText sipEditText, @Nullable int sources) {
        sipEditText.setOutputValueType(sources);
    }
    /**
     * 点击键盘外部键盘是否消失。True：消失；false：不消失；默认false
     */
    @ReactProp(name = "isOutSideDisappear")
    public void setOutSideDisappear(SipEditText sipEditText, @Nullable Boolean sources) {
        sipEditText.setOutSideDisappear(sources);
    }
    /**
     * 加密算法类型。0：SM2；1：RSA；默认0
     */
    @ReactProp(name = "cipherType")
    public void setCipherType(SipEditText sipEditText, @Nullable int sources) {
        sipEditText.setCipherType(sources);
    }
    /**
     * 设置空格键的Icon(注意：需传入图片的Base64编码信息)
     */
    @ReactProp(name = "spaceKeyIcon")
    public void setSpaceKeyIcon(SipEditText sipEditText, @Nullable String sources) {
        sipEditText.setSpaceKeyIcon(sources);
    }

    /**
     * 校验两个输入框输入原文是否匹配,非加密状态下默认返回false
     */
    @ReactMethod
    public void inputEqualsWith (final ReadableMap readableMap, final Callback successCallback,final Callback errorCallback) {
        try {
            ReadableNativeMap readableNativeMap = (ReadableNativeMap) readableMap;
            Map map = readableNativeMap.toHashMap();
            if(isHasAndNotEmpty("nativeID1", map) && map.get("nativeID1") instanceof String &&
                    isHasAndNotEmpty("nativeID2", map) && map.get("nativeID2") instanceof String) {
                String nativeID1 = (String) map.get("nativeID1");
                String nativeID2 = (String) map.get("nativeID2");
                View view =  mCallerContext.getCurrentActivity().getWindow().getDecorView();
                SipEditText sipEditText1 = (SipEditText) ReactFindViewUtil.findView(view, nativeID1);
                SipEditText sipEditText2 = (SipEditText) ReactFindViewUtil.findView(view, nativeID2);
                boolean equalResult = sipEditText1.inputEqualsWith(sipEditText2);

                WritableMap successCallResult = CFCAKeyboardErrorCode.pushMapBoolean("0", equalResult);
                successCallback.invoke(successCallResult);
            }
        } catch (CodeException e) {
            e.printStackTrace();
            WritableMap errorCallResult = CFCAKeyboardErrorCode.getErrorCallResult(Integer.toHexString(e.getCode()).toUpperCase());
            errorCallback.invoke(errorCallResult);
        }
    }

    /**
     * 获取输入内容长度
     */
    @ReactMethod
    public void getInputLength (final ReadableMap readableMap, final Callback successCallback,final Callback errorCallback) {
        try {
            SipEditText sipEditText = getSipEditText(readableMap);
            if (null != sipEditText) {
                int inputLength = sipEditText.getInputLength();
                WritableMap successCallResult = CFCAKeyboardErrorCode.pushMapInt("0", inputLength);
                successCallback.invoke(successCallResult);
            }else {
                errorCallback.invoke(CFCAKeyboardErrorCode.getErrorCallResult("80070057"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            WritableMap errorCallResult = CFCAKeyboardErrorCode.getErrorCallResult("default");
            errorCallback.invoke(errorCallResult);
        }
    }
    /**
     * 获取版本号
     */
    @ReactMethod
    public void getVersion (final ReadableMap readableMap, final Callback successCallback,final Callback errorCallback) {
        try {
            SipEditText sipEditText = getSipEditText(readableMap);
            if (null != sipEditText) {
                String version = sipEditText.getVersion();
                WritableMap successCallResult = CFCAKeyboardErrorCode.pushMap("0", version);
                successCallback.invoke(successCallResult);
            }else {
                errorCallback.invoke(CFCAKeyboardErrorCode.getErrorCallResult("80070057"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            WritableMap errorCallResult = CFCAKeyboardErrorCode.getErrorCallResult("default");
            errorCallback.invoke(errorCallResult);
        }
    }
    /**
     * 清空所有输入数据 注意：屏幕旋转时会自动调用该方法
     */
    @ReactMethod
    public void clear ( final ReadableMap readableMap, final Callback successCallback,final Callback errorCallback) {
        try {
            SipEditText sipEditText = getSipEditText(readableMap);
            if (null != sipEditText) {
                sipEditText.clear();
                WritableMap successCallResult = CFCAKeyboardErrorCode.pushMap("0", "成功");
                successCallback.invoke(successCallResult);
            }else {
                errorCallback.invoke(CFCAKeyboardErrorCode.getErrorCallResult("80070057"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            WritableMap errorCallResult = CFCAKeyboardErrorCode.getErrorCallResult("default");
            errorCallback.invoke(errorCallResult);
        }
    }
    /**
     * 获取键盘是否显示
     */
    @ReactMethod
    public void isKeyBoardShowing ( final ReadableMap readableMap, final Callback successCallback,final Callback errorCallback) {
        try {
            SipEditText sipEditText = getSipEditText(readableMap);
            if (null != sipEditText) {
                Boolean isKeyBoardShowing = sipEditText.isKeyBoardShowing();
                WritableMap successCallResult = CFCAKeyboardErrorCode.pushMapBoolean("0", isKeyBoardShowing);
                successCallback.invoke(successCallResult);
            }else {
                errorCallback.invoke(CFCAKeyboardErrorCode.getErrorCallResult("80070057"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            WritableMap errorCallResult = CFCAKeyboardErrorCode.getErrorCallResult("default");
            errorCallback.invoke(errorCallResult);
        }
    }
    /**
     * 获取键盘高度
     */
    @ReactMethod
    public void getKeyBoardHeight (final ReadableMap readableMap,  final Callback successCallback,final Callback errorCallback) {
        try {
            SipEditText sipEditText = getSipEditText(readableMap);
            if (null != sipEditText) {
                int keyBoardHeight = sipEditText.getKeyBoardHeight();
                WritableMap successCallResult = CFCAKeyboardErrorCode.pushMapInt("0", keyBoardHeight);
                successCallback.invoke(successCallResult);
            }else {
                errorCallback.invoke(CFCAKeyboardErrorCode.getErrorCallResult("80070057"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            WritableMap errorCallResult = CFCAKeyboardErrorCode.getErrorCallResult("default");
            errorCallback.invoke(errorCallResult);
        }
    }
    /**
     * 设置空格键的Icon(注意：需传入图片的Base64编码信息)  PS：修改后不可返回，即设置什么显示什么
     */
    @ReactMethod
    public void setSpaceKeyIcon ( final ReadableMap readableMap, final Callback successCallback,final Callback errorCallback) {
        try {
            ReadableNativeMap readableNativeMap = (ReadableNativeMap) readableMap;
            Map map = readableNativeMap.toHashMap();
            if(isHasAndNotEmpty("spaceKeyIcon", map) && map.get("spaceKeyIcon") instanceof String) {
                SipEditText sipEditText = getSipEditText(readableMap);
                if (null != sipEditText) {
                    String spaceKeyIcon = (String) map.get("spaceKeyIcon");
                    sipEditText.setSpaceKeyIcon(spaceKeyIcon);
                    WritableMap successCallResult = CFCAKeyboardErrorCode.pushMap("0", "成功");
                    successCallback.invoke(successCallResult);
                }else {
                    errorCallback.invoke(CFCAKeyboardErrorCode.getErrorCallResult("80070057"));
                }
            }else {
                errorCallback.invoke(CFCAKeyboardErrorCode.getErrorCallResult("80070057"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            WritableMap errorCallResult = CFCAKeyboardErrorCode.getErrorCallResult("default");
            errorCallback.invoke(errorCallResult);
        }
    }
    /**
     * 设置键盘类型
     */
    @ReactMethod
    public void setSipkeyBoardType ( final ReadableMap readableMap, final Callback successCallback,final Callback errorCallback) {
        try {
            ReadableNativeMap readableNativeMap = (ReadableNativeMap) readableMap;
            Map map = readableNativeMap.toHashMap();
            if(isHasAndNotEmpty("sipkeyBoardType", map) && map.get("sipkeyBoardType") instanceof String) {
                SipEditText sipEditText = getSipEditText(readableMap);
                if (null != sipEditText) {
                    String sipkeyBoardType = (String) map.get("sipkeyBoardType");
                    if(sipkeyBoardType.equals("NUMBER")){
                        sipEditText.setSipKeyBoardType(SIPKeyboardType.NUMBER_KEYBOARD);
                    }else if(sipkeyBoardType.equals("QWERT")){
                        sipEditText.setSipKeyBoardType(SIPKeyboardType.QWERT_KEYBOARD);
                    }
                    WritableMap successCallResult = CFCAKeyboardErrorCode.pushMap("0", "成功");
                    successCallback.invoke(successCallResult);
                }else {
                    errorCallback.invoke(CFCAKeyboardErrorCode.getErrorCallResult("80070057"));
                }
            }else {
                errorCallback.invoke(CFCAKeyboardErrorCode.getErrorCallResult("80070057"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            WritableMap errorCallResult = CFCAKeyboardErrorCode.getErrorCallResult("default");
            errorCallback.invoke(errorCallResult);
        }
    }
    /**
     * 键盘乱序类型。（只支持在设置时乱序，乱序后不可复原）
     * DisorderType.ALL 全部乱序
     * DisorderType.NONE 不乱序
     * DisorderType.ONLY_DIGITAL 仅数字乱序
     */
    @ReactMethod
    public void setDisorderType ( final ReadableMap readableMap, final Callback successCallback,final Callback errorCallback) {
        try {
            ReadableNativeMap readableNativeMap = (ReadableNativeMap) readableMap;
            Map map = readableNativeMap.toHashMap();
            if(isHasAndNotEmpty("disorderType",map) && map.get("disorderType") instanceof String){
                SipEditText sipEditText = getSipEditText(readableMap);
                if(null != sipEditText){
                    String disorderType = (String) map.get("disorderType");
                    if(disorderType.equals("ALL")){
                        sipEditText.setDisorderType(DisorderType.ALL);
                    }else if(disorderType.equals("NONE")){
                        sipEditText.setDisorderType(DisorderType.NONE);
                    }else if(disorderType.equals("ONLY_DIGITAL")){
                        sipEditText.setDisorderType(DisorderType.ONLY_DIGITAL);
                    }
                    WritableMap successCallResult = CFCAKeyboardErrorCode.pushMap("0", "成功");
                    successCallback.invoke(successCallResult);
                }else {
                    errorCallback.invoke(CFCAKeyboardErrorCode.getErrorCallResult("80070057"));
                }
            }else {
                errorCallback.invoke(CFCAKeyboardErrorCode.getErrorCallResult("80070057"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            WritableMap errorCallResult = CFCAKeyboardErrorCode.getErrorCallResult("default");
            errorCallback.invoke(errorCallResult);
        }
    }

    /**
     * 获取弱密码属性，数组长度为8，前6元素为0或者1，分别表示是否包含大写，小写，数字，特殊字符，是否全部连续，
     * 是否全部重复。最后两位表示最大连续字符数目，最大重复字符数目。
     */
    @ReactMethod
    public void getCipherAttributes (final ReadableMap readableMap,  final Callback successCallback,final Callback errorCallback) {
        try {
            SipEditText sipEditText = getSipEditText(readableMap);
            if(null != sipEditText){
                int[] cipherAttributes = sipEditText.getCipherAttributes();
                String cipherAttributesStr = "";
                for (int i=0; i< cipherAttributes.length; i++){
                    cipherAttributesStr = cipherAttributesStr + cipherAttributes[i] + ",";
                }
                WritableMap successCallResult = CFCAKeyboardErrorCode.pushMap("0", cipherAttributesStr);
                successCallback.invoke(successCallResult);
            }else {
                errorCallback.invoke(CFCAKeyboardErrorCode.getErrorCallResult("80070057"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            WritableMap errorCallResult = CFCAKeyboardErrorCode.getErrorCallResult("default");
            errorCallback.invoke(errorCallResult);
        }
    }
    /**
     * 设置加密算法类型
     */
    @ReactMethod
    public void setCipherType ( final ReadableMap readableMap, final Callback successCallback,final Callback errorCallback) {
        try {
            ReadableNativeMap readableNativeMap = (ReadableNativeMap) readableMap;
            Map map = readableNativeMap.toHashMap();
            if(isHasAndNotEmpty("cipherType",map) && map.get("cipherType") instanceof Integer){
                SipEditText sipEditText = getSipEditText(readableMap);
                if(null != sipEditText){
                    int cipherType = (Integer) map.get("cipherType");
                    sipEditText.setCipherType(cipherType);
                    WritableMap successCallResult = CFCAKeyboardErrorCode.pushMap("0", "成功");
                    successCallback.invoke(successCallResult);
                }else {
                    errorCallback.invoke(CFCAKeyboardErrorCode.getErrorCallResult("80070057"));
                }
            }else {
                errorCallback.invoke(CFCAKeyboardErrorCode.getErrorCallResult("80070057"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            WritableMap errorCallResult = CFCAKeyboardErrorCode.getErrorCallResult("default");
            errorCallback.invoke(errorCallResult);
        }
    }
    /**
     * 获取客户端随机数加密结果
     */
    @ReactMethod
    public void getEncryptRandomNum ( final ReadableMap readableMap, final Callback successCallback,final Callback errorCallback) {
        try {
            SipEditText sipEditText = getSipEditTextServerRandom(readableMap);
            if(null != sipEditText) {
                String encryptRandomNum = sipEditText.getEncryptData().getEncryptRandomNum();
                WritableMap successCallResult = CFCAKeyboardErrorCode.pushMap("0", encryptRandomNum);
                successCallback.invoke(successCallResult);
            } else {
                errorCallback.invoke("获取客户端随机数加密结果失败，失败原因为传参错误");
            }
        } catch (CodeException e) {
            e.printStackTrace();
            WritableMap errorCallResult = CFCAKeyboardErrorCode.getErrorCallResult(Integer.toHexString(e.getCode()).toUpperCase());
            errorCallback.invoke(errorCallResult);
        }
    }
    /**
     * 获取原文加密结果
     */
    @ReactMethod
    public void getEncryptInput (final ReadableMap readableMap,  final Callback successCallback,final Callback errorCallback) {
        try {
            SipEditText sipEditText = getSipEditTextServerRandom(readableMap);
            if(null != sipEditText){
                String encryptInput = sipEditText.getEncryptData().getEncryptInput();
                WritableMap successCallResult = CFCAKeyboardErrorCode.pushMap("0", encryptInput);
                successCallback.invoke(successCallResult);
            } else {
                errorCallback.invoke(CFCAKeyboardErrorCode.getErrorCallResult("80070057"));
            }
        } catch (CodeException e) {
            e.printStackTrace();
            WritableMap errorCallResult = CFCAKeyboardErrorCode.getErrorCallResult(Integer.toHexString(e.getCode()).toUpperCase());
            errorCallback.invoke(errorCallResult);
        }
    }
    private SipEditText getSipEditTextServerRandom(final ReadableMap readableMap){
        ReadableNativeMap readableNativeMap = (ReadableNativeMap) readableMap;
        Map map = readableNativeMap.toHashMap();
        if(isHasAndNotEmpty("nativeID",map) && isHasAndNotEmpty("serverRandom",map)){
            if(map.get("nativeID") instanceof String && map.get("serverRandom") instanceof String){
                String nativeID = (String) map.get("nativeID");
                String serverRandom = (String) map.get("serverRandom");
                View view =  mCallerContext.getCurrentActivity().getWindow().getDecorView();
                SipEditText sipEditText = (SipEditText) ReactFindViewUtil.findView(view, nativeID);
                if(null != sipEditText){
                    sipEditText.setServerRandom(serverRandom);
                    return sipEditText;
                }
            }
        }
        return null;
    }

    private SipEditText getSipEditText(final ReadableMap readableMap){
        ReadableNativeMap readableNativeMap = (ReadableNativeMap) readableMap;
        Map map = readableNativeMap.toHashMap();
        if(isHasAndNotEmpty("nativeID", map) && map.get("nativeID") instanceof String){
            String nativeID = (String) map.get("nativeID");
            View view = mCallerContext.getCurrentActivity().getWindow().getDecorView();
            SipEditText sipEditText = (SipEditText) ReactFindViewUtil.findView(view, nativeID);
            if(null != sipEditText){
                return sipEditText;
            }
        }
        return null;
    }

    public void setHint(){
        SpannableString sHintStr = new SpannableString(hintStr);
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(hintSize,true);
        sHintStr.setSpan(ass, 0, sHintStr.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sipEditText.setHint(sHintStr);

    }

    public boolean isHasAndNotEmpty(String key, Map map){
        if(map.containsKey(key)) {
            String keyValue = (String) map.get(key);
            if (keyValue != null && keyValue != "" && !keyValue.equals("")) {
                return true;
            }
        }
        return false;
    }
    public boolean isNotEmpty(String params){
        if(params != null && params != "" && !params.equals("")){
            return true;
        }
        return false;
    }
    // 判断弹出的安全键盘是否会遮掩输入框, 如果会遮挡，自动调整
    private void adjustContainerBeforeKeyboardShow(View sip, int keyboardHeight) {
        int[] location = new int[2]; // index 0 is x, index 1 is y
        sip.getLocationOnScreen(location);
        // 距底距离
        int distanceToScreenBottom = mCallerContext.getResources().getDisplayMetrics().heightPixels
                - location[1]
                - sip.getMeasuredHeight();
        if (distanceToScreenBottom < keyboardHeight) {
            mCallerContext.getCurrentActivity().getWindow().getDecorView().scrollTo(0, keyboardHeight - distanceToScreenBottom);
        }
    }
}
