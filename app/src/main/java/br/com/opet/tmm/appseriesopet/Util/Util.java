package br.com.opet.tmm.appseriesopet.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Diego on 29/05/2017.
 */

public class Util {


    private static InputStream toInputStream(Bitmap bitmap){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
        byte[] bitmapdata = bos.toByteArray();
        ByteArrayInputStream bs = new ByteArrayInputStream(bitmapdata);
        return  bs;
    }

    public static String ImagetoBase64(Bitmap bitmap){
        byte[] bytes;
        byte[] buffer = new byte[8192];
        int bytesRead;

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        InputStream is= toInputStream(bitmap);

        try{
            while((bytesRead = is.read(buffer)) != -1){
                output.write(buffer,0,bytesRead);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        bytes = output.toByteArray();
        String encodedString = Base64.encodeToString(bytes,Base64.DEFAULT);
        return encodedString;
    }

    public static Bitmap Base64toImage(String imgstr){
        byte[] decodedString = Base64.decode(imgstr,Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(decodedString,0,decodedString.length);
        return decodedImage;
    }
}
