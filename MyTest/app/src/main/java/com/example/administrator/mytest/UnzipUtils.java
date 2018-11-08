package com.example.administrator.mytest;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;


/**
 * Created by Administrator on 2018/11/7.
 */

public class UnzipUtils {
    public  File [] unzip(File zipFile, String dest, String passwd) throws ZipException {
        ZipFile zFile = new ZipFile(zipFile);
        zFile.setFileNameCharset("GBK");
        if (!zFile.isValidZipFile()) {
            throw new ZipException("压缩文件不合法,可能被损坏.");
        }
        File destDir = new File(dest);
        if (destDir.isDirectory() && !destDir.exists()) {
            destDir.mkdir();
        }
        if (zFile.isEncrypted()) {
            zFile.setPassword(passwd.toCharArray());
        }
        zFile.extractAll(dest);

        List<FileHeader> headerList = zFile.getFileHeaders();
        List<File> extractedFileList = new ArrayList<File>();
        for(FileHeader fileHeader : headerList)
            if (!fileHeader.isDirectory()) {
                extractedFileList.add(new File(destDir, fileHeader.getFileName()));
            }
        File [] extractedFiles = new File[extractedFileList.size()];
        extractedFileList.toArray(extractedFiles);
        return extractedFiles;
    }


    public  Boolean UnPackTaskFile(Context context, String keyHeader, String absoluteTaskPackageFilePath, String unpackPath)
    {
        try
        {
            File Taskfile  = new File(absoluteTaskPackageFilePath);

            if (!Taskfile.exists())
            {
                return false;
            }
            FileInputStream inputStream = new FileInputStream(Taskfile);//context.openFileInput(absoluteTaskPackageFilePath);

//            FileStream taskFS = new FileStream(absoluteTaskPackageFilePath, FileMode.Open);
            //
            byte[] keyHeaderBytes = keyHeader.getBytes("UTF-8");//Xml.Encoding.UTF8.GetBytes(keyHeader);
            int keyHeaderBytesLength = keyHeaderBytes.length;

            byte[] readheader = new byte[keyHeaderBytesLength];
            inputStream.read(readheader, 0, keyHeaderBytesLength);

            String readKeyHeader = new String(readheader,"UTF-8");
            if (!readKeyHeader.equals(keyHeader))
            {
                Log.e("","文件头不匹配");
                return false;
            }

            //write zip file
            String zipFilePath = unpackPath + "/demo";
            FileOutputStream zipfs = new FileOutputStream(zipFilePath);

            int blockSize = 4 * 1024;
            byte[] buffer = new byte[blockSize];
            int sizeRead = 0;
            try
            {
                do
                {
                    sizeRead = inputStream.read(buffer, 0, buffer.length);
                    zipfs.write(buffer, 0, sizeRead);
                }
                while (sizeRead > 0);
            }
            catch (Exception ex)
            {
                return false;
            }

            zipfs.close();
            inputStream.close();

//            if (CLEAN_TASK_PACK_TEMP_FILE)
//            {
//                File.Delete(absoluteTaskPackageFilePath);
//            }

            return true;
        }
        catch (Exception ex)
        {
//            Log4NetHelper.Error(ex.Message, ex);
            return false;
        }
    }


    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

}
