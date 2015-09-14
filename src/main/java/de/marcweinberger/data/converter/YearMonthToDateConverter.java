package de.marcweinberger.data.converter;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Date;

/**
 * Customer @{Converter} for @{YearMonth} to @{Date} conversion.
 *
 * @author Marc Weinberger, marc.weinberger@me.com
 * @since 18.08.15
 */
public class YearMonthToDateConverter implements Converter<YearMonth, Date> {

  @Override
  public Date convert(YearMonth source) {
    return source == null ? null : Date.from(LocalDate.of(source.getYear(), source.getMonth(), 1).atStartOfDay(ZoneId.of("UTC")).toInstant());
  }
}
