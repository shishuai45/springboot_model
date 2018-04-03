package com.liuss.model.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class String2DateConverter implements Converter<String,Date> {
    private static final String FORMAT_0 = "yyyy-MM-dd HH:mm:ss";
    private static final String FORMAT_1 = "yyyy/MM/dd HH:mm:ss";
    private static final String FORMAT_2 = "yyyy-MM-dd";
    private static final String FORMAT_3 = "yyyy/MM/dd";
    private static final String FORMAT_4 = "HH:mm:ss";
    private static final String FORMAT_5 = "yyyyMMddHHmmss";

    @Override
    public Date convert(String source) {
        return stringToDate(source);
    }
    private Date stringToDate(String time){
        if(StringUtils.isEmptyOrWhitespace(time))
            return null;
        SimpleDateFormat formatter;
        time=time.trim() ;
        if(time.contains("-")&&time.contains(":")){
            formatter= new SimpleDateFormat (FORMAT_0);
        }else if(time.contains("/")&&time.contains(":")){
            formatter= new SimpleDateFormat (FORMAT_1);
        }else if(time.contains("-")){
            formatter= new SimpleDateFormat (FORMAT_2);
        }else if(time.contains("/")){
            formatter= new SimpleDateFormat (FORMAT_3);
        }else if(time.contains(":")){
            formatter= new SimpleDateFormat (FORMAT_4);
        }else{
            formatter= new SimpleDateFormat (FORMAT_5);
        }
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(time, pos);
    }
}
