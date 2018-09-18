package com.wps.poc.ged.simpleged.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.ZonedDateTime;
import java.util.Date;

public class ZonedDateTimeToDateConverter implements Converter<ZonedDateTime, Date> {

    public static final ZonedDateTimeToDateConverter INSTANCE = new ZonedDateTimeToDateConverter();

    private ZonedDateTimeToDateConverter() {
    }

    public Date convert(ZonedDateTime source) {
        return source == null ? null : Date.from(source.toInstant());
    }
}
