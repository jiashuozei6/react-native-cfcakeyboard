/*
 * Author: wufan
 * Created: 2018/5/24
 * Copyright (c) 2018 www.cfca.com.cn All rights reserved.
 */

package com.goosepro.cfcakeyboard;

import android.util.Log;

import com.cfca.mobile.sipedit.SipEditText;
import com.cfca.mobile.sipedit.SipEditTextDelegator;

/**
 * Simple implementation of SipEditTextDelegator.
 * All method is emtpy.
 */
public class SipEditTextDelegatorAdapter implements SipEditTextDelegator {
  String TAG = this.getClass().getName();
  @Override
  public void beforeKeyboardShow(SipEditText sip, int keyboardHeight) {
    Log.d(TAG, "SipEditText beforeKeyboardShow " + keyboardHeight);
  }

  @Override
  public void afterKeyboardHidden(SipEditText sip, int keyboardHeight) {
    Log.d(TAG, "SipEditText afterKeyboardHidden " + keyboardHeight);
  }

  @Override
  public void afterClickDown(SipEditText sip) {
    Log.d(TAG, "SipEditText afterClickDown");
  }

  @Override
  public void onTextChangeListener(int lengthBefore, int lengthAfter) {
    Log.d(TAG, "SipEditText onTextChangeListener: " + lengthBefore + ", " + lengthAfter);
  }
}
