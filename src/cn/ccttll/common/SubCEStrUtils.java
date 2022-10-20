package cn.ccttll.common;

import java.io.UnsupportedEncodingException;

public class SubCEStrUtils {

    public static String splitChineseEnglish(final int chinese_char_len, final String str, final int sum) throws Exception
    {
        final int charset = chinese_char_len;
        if (charset < 2 || 3 < charset)
        {
            throw new Exception("chinese_char_len可选值为2和3,2:Unicode编码,一个汉字占2个字节, 3:UTF-8编码,一个汉字占3个字节");
        }
        int index = sum - 1;
        if (null == str || "".equals(str))
        {
            return str;
        }
        if (index <= 0)
        {
            return str;
        }

        byte[] bt = null;
        try
        {
            if (charset == 2)
            {
                bt = str.getBytes();
            }
            else
            {
                bt = str.getBytes("UTF-8");
            }
        }
        catch (final UnsupportedEncodingException e)
        {
            e.getMessage();
        }
        if (null == bt)
        {
            return str;
        }
        if (index > bt.length - 1)
        {
            index = bt.length - 1;
        }

        if (bt[index] < 0)
        {
            int jsq = 0;
            int num = index;
            while (num >= 0)
            {
                if (bt[num] < 0)
                {
                    jsq += 1;
                }
                else
                {
                    break;
                }
                num -= 1;
            }

            int m = 0;
            if (charset == 2)
            {
                m = jsq % 2;
                index -= m;
                final String substrx = new String(bt, 0, index + 1);
                return substrx;
            }
            else
            {
                m = jsq % 3;
                index -= m;
                String substrx = null;
                try
                {
                    substrx = new String(bt, 0, index + 1, "UTF-8");
                }
                catch (final UnsupportedEncodingException e)
                {
                    e.getMessage();
                }
                return substrx;
            }
        }
        else
        {
            String substrx = null;
            if (charset == 2)
            {
                substrx = new String(bt, 0, index + 1);
                return substrx;
            }
            else
            {
                try
                {
                    substrx = new String(bt, 0, index + 1, "UTF-8");
                }
                catch (final UnsupportedEncodingException e)
                {
                    e.getMessage();
                }
                return substrx;
            }
        }
    }
}
