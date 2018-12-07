package com.DDS.utils;

import java.util.Calendar;

public class MonthUtil {
	
    public static String MonthYear(){
        String[] monthName = {"January", "February",
                "March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December"};

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        String monthYear = monthName[cal.get(Calendar.MONTH)] + ", "+Integer.toString(year);
        
        return monthYear;

    }

}
