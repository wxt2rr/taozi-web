package util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamUtil {
    static int BUFFER_SIZE = 1024;

    /**
     * 将InputStream转换成某种字符编码的String
     *
     * @param in
     * @param encoding
     * @return
     * @throws Exception
     * @author yunheng E-mail: admin@yyh.hk
     * @version 创建时间：2016年2月16日 上午11:01:05
     */
    public static String inputStreamToString(InputStream in, String encoding) throws Exception {
        byte [] bytes=inputStreamToByte(in);
        String result=null;
        if (bytes==null||bytes.length<=0) {
            return result;
        }
        result=new String(bytes, encoding);
        return result;
    }

    /**
     * 使用默认UTF-8编码,将InputStream转换成某种字符编码的String
     *
     * @param in
     * @param
     * @return
     * @throws Exception
     * @author yunheng E-mail: admin@yyh.hk
     * @version 创建时间：2016年2月16日 上午11:01:05
     */
    public static String inputStreamToString(InputStream in) throws Exception {
        return inputStreamToString(in,"UTF-8");
    }

    /**
     * 将InputStream转换成byte数组
     *
     * @param in
     * @return
     * @throws Exception
     * @author yunheng E-mail: admin@yyh.hk
     * @version 创建时间：2016年2月16日 上午11:00:55
     */
    public static byte[] inputStreamToByte(InputStream in) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int count = -1;
        while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
            outStream.write(data, 0, count);

        data = null;
        return outStream.toByteArray();
    }

    /**
     * 将String转换成InputStream
     *
     * @param str
     * @param encoding
     * @return
     * @throws Exception
     * @author yunheng E-mail: admin@yyh.hk
     * @version 创建时间：2016年2月16日 上午11:01:11
     */
    public static InputStream stringToInputStream(String str, String encoding) throws Exception {
        InputStream is=null;
        if (str==null) {
            return is;
        }
        is=byteToInputStream(str.getBytes(encoding));
        return is;
    }
    /**
     * 将byte数组转换成InputStream
     * @param bytes
     * @return
     * @throws Exception
     * @author yunheng E-mail: admin@yyh.hk
     * @version 创建时间：2016年2月16日 上午11:02:46
     */
    public static InputStream byteToInputStream(byte[] bytes) throws Exception {
        ByteArrayInputStream is =null;
        if (bytes==null||bytes.length<=0) {
            return is;
        }
        is= new ByteArrayInputStream(bytes);
        return is;
    }
}
