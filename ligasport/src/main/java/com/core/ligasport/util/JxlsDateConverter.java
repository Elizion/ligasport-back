package com.core.ligasport.util;

import java.util.Calendar;
import java.util.Date;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.Converter;
import org.apache.poi.ss.usermodel.DateUtil;

public class JxlsDateConverter implements Converter {
   
	@Override
	public Object convert(@SuppressWarnings("rawtypes") Class type, Object value) {
		
        if( value == null ) {
            throw new ConversionException("No value specified");
        }
        
        double date;
        
        if( value instanceof Double ) {
        	
            date = ((Double)value).doubleValue();
            
        } else if( value instanceof Number) {
        	
            date = ((Number)value).doubleValue();
            
        } else if( value instanceof String ) {
        	
            try {
            	
                date = Double.parseDouble((String)value);
                
            } catch (NumberFormatException e) {
            	
                throw new ConversionException(e);
                
            }
            
        } else if(value instanceof java.util.Date) {
        	
            return value;
            
        } else {
        	
            throw new ConversionException("No value specified");
            
        }        
        /*
         * Apache POI bug: https://issues.apache.org/bugzilla/show_bug.cgi?id=56269
         * fixed in poi release 3.11-dev. when will be used version greater then 3.11-dev, please remove this code
         * 
         * 
         * This code is indeed to fix bug in date conversion from excel type to java type.
         * As Excel don't work with milliseconds, we convert time from excel first to seconds, rount them mathematically, and after convert back to excel time
         * just before convert to java.util.Date time*/
        int wholeDays = (int)Math.floor(date);       
        double excelSeconds = Double.valueOf(Math.round((date-Math.floor(date))*24*60*60)).intValue();        
        double adjustedDate = wholeDays+((excelSeconds/DateUtil.SECONDS_PER_DAY)+0.000000000005);      
        /*
         * end bugfix
         * */       
        Calendar calendar = DateUtil.getJavaCalendarUTC(adjustedDate, false);
        Date ret = calendar.getTime();
        
        return ret; 
    }
}

