/*
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import sun.net.ftp.FtpClient;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FtpUtil {

    private static ThreadLocal<Map<String,FTPClient>> local=new ThreadLocal(){
        @Override
        protected Object initialValue() {
            return new HashMap<String,FtpClient>();
        }
    };

    private static Map<String,FTPClient> localMap=new HashMap<>();

    private FtpUtil(){}

    private static FTPClient getFtp(String key){
        FTPClient ftp=local.get().get(key);
        if(ftp==null){
            ftp=localMap.get(key);
        }
        return ftp;
    }

    public static void close(String key){
        try {
            getFtp(key).disconnect();
        } catch (IOException e) {
            LogUtil.getLogger(LogUtil.frameName).error("FTP连接未正常断开",e);
        }
        local.get().remove(key);
    }

    public static String init(String host,Integer port,String username,String password,boolean useLocal) throws Exception {
        Map<String,FTPClient> map=null;
        if(useLocal){
            map=local.get();
        }else{
            map=localMap;
        }
        FTPClient ftp=null;
        String key=Md5Util.toMd5((host+port+username+password).getBytes());
        if(map.containsKey(key)){
            ftp=map.get(key);
        }else{
            ftp=new FTPClient();
            map.put(key,ftp);
        }

        if(ftp.isConnected()){
            try {
                ftp.noop();
            }catch(Exception e){
                LogUtil.getLogger(LogUtil.frameName).error("ftp noop error",e);
                ftp.connect(host, port);
            }
        }else {
            ftp.connect(host, port);
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.setControlEncoding("UTF-8");
            ftp.setCharset(Charset.forName("UTF-8"));
            ftp.setControlKeepAliveTimeout(1000*60*10);
            ftp.setAutodetectUTF8(true);
        }
        if(!ftp.login(username,password)) {
            throw new Exception("ftp登录失败.");
        }
        return key;
    }

    public static void chanage(String key,String basePath)throws Exception{
        FTPClient ftp=getFtp(key);
        if(null==basePath || "/".equals(basePath) || "\\".equals(basePath)){
            ftp.changeWorkingDirectory("/");
        }else{
            StringBuffer base=new StringBuffer();
            String[] path=null;
            if(basePath.indexOf("\\")!=-1){
                path=basePath.split("\\\\");
            }else{
                path=basePath.split("/");
            }
            for(String s : path){
                ftp.makeDirectory(s);
                base.append("/"+s);
                ftp.changeWorkingDirectory(base.toString());
            }
        }
    }

    public static void upload(String key,String fileName,InputStream input,boolean close)throws Exception{
        getFtp(key).storeFile(new String(fileName.getBytes(),"ISO-8859-1"),input);
        input.close();
        if(close){
            close(key);
        }
    }

    public static void download(String key,String fileName,OutputStream output,boolean close)throws Exception{
        getFtp(key).retrieveFile(new String(fileName.getBytes(),"ISO-8859-1"),output);
        output.close();
        if(close){
            close(key);
        }
    }

    public static void delete(String key,String fileName,boolean close)throws Exception{
        getFtp(key).deleteFile(new String(fileName.getBytes(),"ISO-8859-1"));
        if(close){
            close(key);
        }
    }

    public static List<String> listFiles(String key, String basePath,boolean close)throws Exception{
        List<String> fileNames=new ArrayList<>();
        for (FTPFile f : getFtp(key).listFiles(basePath)) {
           fileNames.add(f.getName());
        }
        if(close){
            close(key);
        }
        return fileNames;
    }
}
*/