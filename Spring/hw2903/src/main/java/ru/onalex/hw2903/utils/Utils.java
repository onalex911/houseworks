package ru.onalex.hw2903.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Utils {
    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
