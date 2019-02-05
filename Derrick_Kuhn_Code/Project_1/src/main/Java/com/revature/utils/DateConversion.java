package com.revature.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

//Class used to convert date retreived from DBS to LocalDateTime and send LocalDateTime as Date to DBS.
//Implemented using information retreived from https://www.baeldung.com/java-date-to-localdate-and-localdatetime

public class DateConversion {

    private static DateConversion instance = new DateConversion();

    private DateConversion(){}

    public static DateConversion getInstance(){
        return instance;
    }

    public Date convertLDTtoDate(LocalDateTime ldt) {
        return java.util.Date
                .from(ldt.atZone(ZoneId.systemDefault())
                        .toInstant());
    }

    public LocalDateTime convertDateToLDT(Date date){
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
