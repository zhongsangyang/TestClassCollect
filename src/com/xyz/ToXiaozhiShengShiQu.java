package com.xyz;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToXiaozhiShengShiQu {
    public static void main(String[] args) {
       String []findStr=new String[]{"北京市北京市东城区","广东省深圳市宝安区","香港香港北区","广西南宁市江南区"};
        String []result=null;
        for (String s: findStr) {
            result=chuliStrforShengShiQu(s);
        }

    }
    public static String returnFindStr(String findStr,String hanzi){
        Pattern pattern=Pattern.compile("\\W+"+hanzi);
        Matcher matcher=pattern.matcher(findStr);
        if (matcher.find()){
            return matcher.group();
        }
        return "";
    }
    public static String[] chuliStrforShengShiQu(String s){
        String shi="市";
        String sheng="省";
        String []result=new String[3];
        String []chuliStr=s.split(sheng);
        String []spiltStr=s.split(shi);
        String shengzi=returnFindStr(s,sheng);

        if(spiltStr.length==1&&chuliStr.length==1){
            StringBuilder stringBuilder=new StringBuilder();
            stringBuilder.append(s);
            stringBuilder.insert(2,sheng);
            stringBuilder.insert(5,shi);
            s=stringBuilder.toString();
        }
        if(spiltStr.length==2&&chuliStr.length==1){
            StringBuilder stringBuilder=new StringBuilder();
            stringBuilder.append(s);
            stringBuilder.insert(2,sheng);

            s=stringBuilder.toString();
        }

        if (spiltStr.length > 2) {
            result[0]=spiltStr[0]+shi;
            result[1]=spiltStr[1]+shi;
            result[2]=spiltStr[2];
        }else {
            spiltStr=s.split(shi);
            result[0]=returnFindStr(s,sheng);
            if(shengzi.length()==0){
                result[0]=result[0].replaceFirst(sheng,"");
            }

            result[1]=spiltStr[0].split(sheng)[1]+shi;
            result[2]=spiltStr[1];
        }
        Arrays.stream(result).forEach(System.out::println);
        return result;
    }
}
