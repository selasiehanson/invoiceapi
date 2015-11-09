package com.soundlabz.invoices.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        return DateUtils.asDate(localDate);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date value) {
        return DateUtils.asLocalDate(value);
    }
}
