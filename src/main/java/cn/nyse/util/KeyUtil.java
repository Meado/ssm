package cn.nyse.util;

import java.util.Random;

public class KeyUtil {
    public static String keyUtil() {
        String str="ABCDEFGHIJKLMNOPQRSTUVWXYZqwertyuiopasdfghjklzxcvbnm0123456789";
        StringBuilder st=new StringBuilder(8);
        for(int i=0;i<8;i++){
            char ch=str.charAt(new Random().nextInt(str.length()));
            st.append(ch);
        }
        String findkey=st.toString().toLowerCase();
        System.out.println("key为："+st.toString());
        return findkey;
    }
}
