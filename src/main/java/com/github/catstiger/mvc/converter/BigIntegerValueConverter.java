package com.github.catstiger.mvc.converter;

import java.math.BigInteger;

import com.github.catstiger.utils.StringUtils;

public class BigIntegerValueConverter extends PrimitiveConverter<BigInteger> {

  @Override
  public BigInteger convert(Object value) {
    if(value == null) {
      return null;
    }
    
    String trimmed = StringUtils.trimToEmpty(value.toString());
    if(isNull(trimmed) || "".equals(trimmed)) {
      return null;
    }
    return (isHexNumber(trimmed) ? decodeBigInteger(trimmed) : new BigInteger(trimmed));
  }
  
  private static BigInteger decodeBigInteger(String value) {
    int radix = 10;
    int index = 0;
    boolean negative = false;

    // Handle minus sign, if present.
    if (value.startsWith("-")) {
      negative = true;
      index++;
    }

    // Handle radix specifier, if present.
    if (value.startsWith("0x", index) || value.startsWith("0X", index)) {
      index += 2;
      radix = 16;
    }
    else if (value.startsWith("#", index)) {
      index++;
      radix = 16;
    }
    else if (value.startsWith("0", index) && value.length() > 1 + index) {
      index++;
      radix = 8;
    }

    BigInteger result = new BigInteger(value.substring(index), radix);
    return (negative ? result.negate() : result);
  }

}
