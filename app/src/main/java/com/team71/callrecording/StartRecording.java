package com.team71.callrecording;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioFocusRequest;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioRecordingConfiguration;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Environment;

import androidx.annotation.UiThread;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class StartRecording {

    private MediaRecorder mediaRecorder;
    private boolean isRecording = false;
    AudioManager manager;
    private AudioRecord audioRecord;

    public void showPath() {
        File outputFilePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        System.out.println(outputFilePath);
    }

    public void startRecord(Context context) throws FileNotFoundException {

        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        audioManager.setMode(AudioManager.MODE_IN_CALL);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            audioManager.getAllowedCapturePolicy();
        }
        String error = "";
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_CALL);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setAudioEncodingBitRate(128);
        mediaRecorder.setAudioSamplingRate(44100);



        // Set the output file path
        String outputFilePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/test.mp3";
        System.out.println(outputFilePath);
        isRecording = true;

       /* int audioSource = MediaRecorder.AudioSource.VOICE_COMMUNICATION;
        //audioRecord.getAudioSource() = MediaRecorder.AudioSource.VOICE_CALL;// Use appropriate source
        int sampleRate = 44100; // Sample rate
        int channelConfig = AudioFormat.CHANNEL_IN_DEFAULT;
        int audioFormat = AudioFormat.ENCODING_PCM_16BIT;
        int bufferSize = AudioRecord.getMinBufferSize(sampleRate, channelConfig, audioFormat);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath);
        audioRecord = new AudioRecord(audioSource, sampleRate, channelConfig, audioFormat, bufferSize);
        *//*try {
            fileOutputStream = new FileOutputStream(outputFilePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*//*
        byte[] buffer = new byte[bufferSize];

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int bytesRead;
                    while (isRecording) { // You should have a flag to control the recording state
                        bytesRead = audioRecord.read(buffer, 0, bufferSize);
                        fileOutputStream.write(buffer, 0, bytesRead);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });*/
        mediaRecorder.setOutputFile(outputFilePath);


        try {

            mediaRecorder.prepare();
            mediaRecorder.start();
            mediaRecorder.setOnErrorListener(new MediaRecorder.OnErrorListener() {
                @Override
                public void onError(MediaRecorder mediaRecorder, int i, int i1) {
                    System.out.println(mediaRecorder);
                    System.out.println(i);
                    System.out.println(i1);
                }
            });
            error = "Start Record";

        } catch (IOException e) {
            e.printStackTrace();
            error = e.getMessage();
            System.out.println(e.getMessage());

        }

    }

    private int getChoose() {
        return MediaRecorder.AudioSource.MIC;
    }

    public void stopRecording(){
        /*if (audioRecord != null){
            audioRecord.stop();
            audioRecord.release();
            audioRecord = null;
            manager.setMode(AudioManager.MODE_NORMAL);
            isRecording = false;
        }*/
        if (mediaRecorder != null) {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            manager.setMode(AudioManager.MODE_NORMAL);
        }
    }

    public boolean isRecording() {
        return isRecording;
    }
}
