package com.github.catstiger.mvc.converter;

import org.apache.commons.lang3.StringUtils;

public class LongValueConverter extends PrimitiveConverter<Long> {

  @Override
  public Long convert(Object value) {
    if(value == null) {
      return null;
    }
    
    String trimmed = StringUtils.trimToEmpty(value.toString());
    return (isHexNumber(trimmed) ? Long.decode(trimmed) : Long.valueOf(trimmed));
  }

}
