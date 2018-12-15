package com.example.administrator.mytest;

import java.security.MessageDigest;

/**
 * Created by Administrator on 2018/7/3.
 */

public class CRCUtils {
    public static byte[] getCRCByte(int number,String filename){
        try {
            String key = "Chivox2016.DON.Exam";
            String checkstr = key+filename+number;
            byte[]  b =  checkstr.getBytes("UTF-8");
            MessageDigest digest = MessageDigest.getInstance("MD5"); // Get a MD5 instance
            digest.update(b);
            byte[] res = digest.digest();
            return res;
        }catch (Exception ep){
            return null;
        }

    }
    public static Boolean CRCCheck(int number, String filename, byte[] crcbytes){
        try {
            byte[] res = getCRCByte(number,filename);

            if(res.length == 16){
                for(int i = 0;i<16;i++){
                    byte a1 = crcbytes[i];
                    byte a2 = res[i];
                    if(a1!=a2){
                        return  false;
                    }
                }
                return true;
            }else{
                return  false;
            }
        }catch (Exception ep){
            return false;
        }

    }
}
