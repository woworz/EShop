package com.example.eshop.util;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.IOException;

public class SaveUtil {

    public static void saveData(Context context, String fileName, String data) {
        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fos.write(data.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
