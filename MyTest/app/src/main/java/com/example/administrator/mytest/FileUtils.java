package com.example.administrator.mytest;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 管理文件的类
 */
public class FileUtils {

    private static String mFilesDirPath;



    public final static String LOACAT_BASEPATH = "/log/";




    public static void deleteAllFiles(File root) {
        File files[] = root.listFiles();
        if (files != null)
            for (File f : files) {
                if (f.isDirectory()) { // 判断是否为文件夹
                    deleteAllFiles(f);
                    try {
                        f.delete();
                    } catch (Exception e) {
                    }
                } else {
                    if (f.exists()) { // 判断是否存在
                        deleteAllFiles(f);
                        try {
                            f.delete();
                        } catch (Exception e) {
                        }
                    }
                }
            }
    }
    public static long getFileSize(File file) throws Exception {
        if (file == null) {
            return 0;
        }
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        }
        return size;
    }


    public static String getFilesDir(Context context) {
        if(!TextUtils.isEmpty(mFilesDirPath)) {
            return mFilesDirPath;
        } else {
            File bc = null;
            if(Environment.getExternalStorageState().equals("mounted")) {
                bc = new File(Environment.getExternalStorageDirectory(), "Android/data/" + context.getApplicationInfo().packageName + "/files");
                if(!bc.exists()) {
                    bc.mkdirs();
                }
            }

            if(bc == null || !bc.exists()) {
                bc = context.getFilesDir();
            }

            return bc.getAbsolutePath();
        }
    }

}
