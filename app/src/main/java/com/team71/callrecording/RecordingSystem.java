package com.team71.callrecording;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class RecordingSystem extends BroadcastReceiver {
    private MediaRecorder mediaRecorder;
    String recording = "start", startRecord = "off", endRecord = "off";
    @Override
    public void onReceive(Context context, Intent intent) {
        //Toast.makeText(context, "work", Toast.LENGTH_SHORT).show();
        if (intent.getAction().equals(TelephonyManager.ACTION_PHONE_STATE_CHANGED)) {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            System.out.println(state);
            if (state.equals("RINGING")) {
                Toast.makeText(context, "Ringing", Toast.LENGTH_SHORT).show();
            } else if (state.equals("OFFHOOK")) {
                if (startRecord.equals("off")){
                    Toast.makeText(context, "start record", Toast.LENGTH_SHORT).show();
                    System.out.println(startRecord);
                    context.startForegroundService(new Intent(context, FloatingViewService.class));
                    startRecord = "on";
                }

            } else if (state.equals("IDLE")) {
                if (endRecord.equals("off")){
                    Toast.makeText(context, "end record", Toast.LENGTH_SHORT).show();
                    new StartRecording().stopRecording();
                    new StartRecording().showPath();
                    System.out.println(endRecord);
                    endRecord = "on";
                }
            }
        }
    }
}
