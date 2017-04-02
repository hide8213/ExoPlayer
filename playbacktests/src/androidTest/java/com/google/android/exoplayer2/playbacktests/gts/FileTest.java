package com.google.android.exoplayer2.playbacktests.gts;

import android.util.Log;

import junit.framework.Test;
import junit.framework.TestCase;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * Created by rexhuang on 2017/3/26.
 */

public class FileTest extends TestCase {

    public void testFile()throws Exception{
        try {
            URL website = new URL("https://storage.googleapis.com/eason-media/en3_sintel_vod.mpd");
            ReadableByteChannel rbc;
            rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream("/sdcard/sintel.mpd");
//            URL url = new URL("https://storage.googleapis.com/eason-media/en3_sintel_vod.mpd");
//            String file = url.getFile();
//            File myFile = new File("/sdcard/sintel.mpd");
//            myFile.createNewFile();
//            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fos);
            myOutWriter.close();
            fos.close();
        } catch (Exception e) {
            Log.e("ERRR", "Could not create file",e);
        }
    }

    public void testDownload(){
        try {
            URL url = new URL("https://storage.googleapis.com/eason-media/sintel_vod.mpd");
            URLConnection conexion = url.openConnection();
            conexion.connect();

            int lenghtOfFile = conexion.getContentLength();
            Log.d("ANDRO_ASYNC", "Lenght of file: " + lenghtOfFile);

            InputStream input = new BufferedInputStream(url.openStream());
            OutputStream output = new FileOutputStream("/sdcard/sintel_vod.mpd");
            OutputStreamWriter myOutWriter = new OutputStreamWriter(output);
            byte[] buffer = new byte[1024];
            int read = input.read(buffer);

            while(read != -1){
                output.write(buffer, 0, read);
                read = input.read(buffer);
            }
            input.close();
            output.close();
            myOutWriter.close();
        } catch (Exception e) {
            Log.e("ERRR", "Could not create file",e);
        }
    }
}
