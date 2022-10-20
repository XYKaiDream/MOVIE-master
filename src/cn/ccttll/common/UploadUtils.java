package cn.ccttll.common;
import java.util.UUID;
public class UploadUtils {

    public static String getUUIDFileName(String fileName) {

        int idx=fileName.lastIndexOf(".");
        String extention=fileName.substring(idx);

        String uuidFileName= UUID.randomUUID().toString().replace("-","")+extention;
        return uuidFileName;
    }

    public static void main(String[] args) {

        System.out.println(UUID.randomUUID());
    }
}
