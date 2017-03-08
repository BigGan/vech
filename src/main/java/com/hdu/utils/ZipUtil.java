/*package context.support.utilities;

import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.io.output.ByteArrayOutputStream;

import java.io.IOException;
import java.util.Map;

public class ZipUtil {

    public static byte[] toZip(Map<String,byte[]> entrys){
        if(null==entrys || entrys.size()==0){
            return null;
        }
        ByteArrayOutputStream zipOut = new ByteArrayOutputStream();
        try {
            ZipArchiveOutputStream zipArc = new ZipArchiveOutputStream(zipOut);
            zipArc.setUseZip64(Zip64Mode.AsNeeded);
            for (String name : entrys.keySet()) {
                ZipArchiveEntry entry = new ZipArchiveEntry(name);
                byte[] bytes = entrys.get(name);
                entry.setSize(bytes.length);
                zipArc.putArchiveEntry(entry);
                zipArc.write(bytes);
                zipArc.closeArchiveEntry();
            }
            zipArc.flush();
            zipArc.finish();
            zipArc.close();
            return zipOut.toByteArray();
        }catch(Exception e){
            LogUtil.getLogger(LogUtil.frameName).error("生成zip压缩文件失败.",e);
            return null;
        }finally {
            try {
                zipOut.close();
            } catch (IOException e) {
            }
        }
    }

}
*/