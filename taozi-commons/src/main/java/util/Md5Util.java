package util;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {
    @SuppressWarnings("unused")
    private final static int ITERATIONS = 20;
    //***********************************************MD5
    private static MessageDigest getMD5Messagedigest() {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getByteMD5(byte[] bytes) {
        InputStream is = new ByteArrayInputStream(bytes);
        return getInputStreamMD5(is);
    }
    /**
     * md5加密
     *
     * @param str
     * @return
     */
    public static String getStringMD5(String str) {
        StringBuffer sb = new StringBuffer(32);
        try {
            MessageDigest md = getMD5Messagedigest();
            byte[] array = md.digest(str.getBytes("UTF-8"));
            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .toUpperCase().substring(1, 3));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static String getInputStreamMD5(InputStream is) {
        MessageDigest digest = getMD5Messagedigest();
        try {
            byte buffer[] = new byte[1024];
            int len;
            while ((len = is.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//		BigInteger bigInt = new BigInteger(1, digest.digest());
        return  toHexString(digest.digest());
//		return bigInt.toString(16).length() < 32 ;
    }

    public static String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for(byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static String getFileMD5(MultipartFile file) {
        try {
            return getInputStreamMD5(file.getInputStream());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }

    /**
     * 获取单个文件的hash值
     *
     * @param file
     * @return
     */
    public static String getFileMD5(File file) {
        MessageDigest digest = getMD5Messagedigest();
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            in = new FileInputStream(file);

            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (IOException e) {
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }

    /**
     * 获取单个文件的md5
     *
     * @param
     */
    public static String getFileMD5(String filepath) {
        File file = new File(filepath);
        return getFileMD5(file);
    }


    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

    //**********************************SHA1

    public static String getStringSha1(String str, String charSet){
        if (null == str || 0 == str.length()){
            return null;
        }
        char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes(charSet));

            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] buf = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
