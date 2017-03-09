package com.hdu.utils;




import java.security.MessageDigest;

import org.apache.log4j.Logger;

public class Md5Util {

    private static Logger logger= Logger.getLogger(Md5Util.class);

    private static  byte[] hash(byte[] bytes, byte[] salt, int hashIterations) throws Exception {
        MessageDigest digest =MessageDigest.getInstance("md5");
        if(salt != null) {
            digest.reset();
            digest.update(salt);
//            digest.reset();//没有reset就是追加update  然后将追加好的update直接加在bytes字符串的前面再计算出md5
//            digest.update("zlhuang2".getBytes());
        }
        byte[] hashed = digest.digest(bytes);
        int iterations = hashIterations - 1;
        for(int i = 0; i < iterations; ++i) {
            digest.reset();
            hashed = digest.digest(hashed);
        }
        return hashed;
    }

    private static String toHexString( byte[] b) {
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toString());
        }
        return sb.toString();
    }

    public static String toMd5(byte[] b){
        try {
            return toHexString(hash(b, null, 1));
        }catch(Exception e){
            logger.error("生成Md5失败.",e);
            return null;
        }
    }

    public static String toMd5(byte[] b,int times){
        try {
            return toHexString(hash(b, null,times));
        }catch(Exception e){
            logger.error("生成Md5失败.",e);
            return null;
        }
    }

    public static String toMd5(byte[] b,byte[] salt,int times){
        try {
            return toHexString(hash(b,salt,times));
        }catch(Exception e){
            logger.error("生成Md5失败.",e);
            return null;
        }
    }

    public static boolean equal(String md5,byte[] src){
        if(md5.equals(toMd5(src))){
            return true;
        }
        return false;
    }


}
