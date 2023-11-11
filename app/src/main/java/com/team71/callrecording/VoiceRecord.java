package com.team71.callrecording;


import android.accessibilityservice.AccessibilityService;
import android.app.Notification;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.accessibility.AccessibilityEvent;
import android.widget.PopupWindow;

public class VoiceRecord extends AccessibilityService {

    private Context c;
    private PopupWindow popupWindow;
    public void window(){

    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
    }

    @Override
    public void onInterrupt() {

    }
}
