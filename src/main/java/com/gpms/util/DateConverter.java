package com.gpms.util;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        if (s==null && s.equals("")){
            throw  new RuntimeException("日期不正确");
        }else {
            SimpleDateFormat sdf=null;
            if (s.contains(":")){
                 sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            }else {
                sdf=new SimpleDateFormat("yyyy-MM-dd");
            }
            try {
                return sdf.parse(s);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}