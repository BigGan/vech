package com.hdu.utils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

public class FtpUtil {
	
	private static Logger log = Logger.getLogger(DateUtil.class);
	
    private static ThreadLocal<Map<String, FTPClient>> local = new ThreadLocal() {
        protected Object initialValue() {
            return new HashMap();
        }
    };
    private static Map<String, FTPClient> localMap = new HashMap();

    private FtpUtil() {
    }

    private static FTPClient getFtp(String key) {
        FTPClient ftp = (FTPClient)((Map)local.get()).get(key);
        if(ftp == null) {
            ftp = (FTPClient)localMap.get(key);
        }

        return ftp;
    }

    public static void close(String key) {
        try {
            getFtp(key).disconnect();
        } catch (IOException var2) {
        	log.error("FTP连接未正常断开", var2);
        }

        ((Map)local.get()).remove(key);
    }

    public static String init(String host, Integer port, String username, String password, boolean useLocal) throws Exception {
        Map map = null;
        if(useLocal) {
            map = (Map)local.get();
        } else {
            map = localMap;
        }

        FTPClient ftp = null;
        String key = Md5Util.toMd5((host + port + username + password).getBytes());
        if(map.containsKey(key)) {
            ftp = (FTPClient)map.get(key);
        } else {
            ftp = new FTPClient();
            map.put(key, ftp);
        }

        if(ftp.isConnected()) {
            try {
                ftp.noop();
            } catch (Exception var9) {
            	log.error("ftp noop error", var9);
                ftp.connect(host, port.intValue());
            }
        } else {
            ftp.connect(host, port.intValue());
            ftp.setFileType(2);
            ftp.setControlEncoding("UTF-8");
/*            ftp.setCharset(Charset.forName("UTF-8"));
            ftp.setControlKeepAliveTimeout(600000L);
            ftp.setAutodetectUTF8(true);*/
        }

        if(!ftp.login(username, password)) {
            throw new Exception("ftp登录失败.");
        } else {
            return key;
        }
    }

    public static void chanage(String key, String basePath) throws Exception {
        FTPClient ftp = getFtp(key);
        if(null != basePath && !"/".equals(basePath) && !"\\".equals(basePath)) {
            StringBuffer base = new StringBuffer();
            String[] path = null;
            if(basePath.indexOf("\\") != -1) {
                path = basePath.split("\\\\");
            } else {
                path = basePath.split("/");
            }

            String[] var5 = path;
            int var6 = path.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                String s = var5[var7];
                ftp.makeDirectory(s);
                base.append("/" + s);
                ftp.changeWorkingDirectory(base.toString());
            }
        } else {
            ftp.changeWorkingDirectory("/");
        }

    }

    public static void upload(String key, String fileName, InputStream input, boolean close) throws Exception {
        getFtp(key).storeFile(new String(fileName.getBytes(), "ISO-8859-1"), input);
        input.close();
        if(close) {
            close(key);
        }

    }

    public static void download(String key, String fileName, OutputStream output, boolean close) throws Exception {
        getFtp(key).retrieveFile(new String(fileName.getBytes(), "ISO-8859-1"), output);
        output.close();
        if(close) {
            close(key);
        }

    }

    public static void delete(String key, String fileName, boolean close) throws Exception {
        getFtp(key).deleteFile(new String(fileName.getBytes(), "ISO-8859-1"));
        if(close) {
            close(key);
        }

    }

    public static List<String> listFiles(String key, String basePath, boolean close) throws Exception {
        ArrayList fileNames = new ArrayList();
        FTPFile[] var4 = getFtp(key).listFiles(basePath);
        int var5 = var4.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            FTPFile f = var4[var6];
            fileNames.add(f.getName());
        }

        if(close) {
            close(key);
        }

        return fileNames;
    }
}
