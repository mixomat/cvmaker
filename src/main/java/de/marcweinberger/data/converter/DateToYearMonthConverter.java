package de.marcweinberger.data.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;

import static java.time.Instant.ofEpochMilli;
import static java.time.LocalDateTime.ofInstant;
import static java.time.YearMonth.from;

/**
 * Customer @{Converter} for @{Date} to @{YearMonth} conversion.
 *
 * @author Marc Weinberger, marc.weinberger@me.com
 * @since 18.08.15
 */
public class DateToYearMonthConverter implements Converter<Date, YearMonth> {
  @Override
  public YearMonth convert(Date source) {
    return source == null ? null : from(ofInstant(ofEpochMilli(source.getTime()), ZoneId.of("UTC")));
  }
}
