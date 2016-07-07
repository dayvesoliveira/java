package com.java.test.git;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Classe utilitária para manipulação de números.
 *
 */
public class NumberUtils implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 *
	 * @param value - Valor a formatar
	 * @return
	 */
	public String format(BigDecimal value) {
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		return format(value, decimalFormat);
	}
	
	/**
	 *
	 * @param value - Valor a formatar
	 * @param decimalSeparator - Separador decimal
	 *
	 * @return
	 */
	public String format(BigDecimal value, char decimalSeparator) {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator(decimalSeparator);
		DecimalFormat decimalFormat = new DecimalFormat("0.00", symbols);
		return format(value, decimalFormat);
	}

	/**
	 *
	 * @param value - Valor a formatar
	 * @param decimalFormat - Formato
	 * @return
	 */
	public String format(BigDecimal value, DecimalFormat decimalFormat) {
		String result = null;
		if (value != null && decimalFormat != null) {
			result = decimalFormat.format(value);
		}
		return result;
	}

	/**
	 *
	 * @param value - Valor a formatar
	 * @param fractionDigits - Número de casa decimal
	 * @return
	 */
	public String format(BigDecimal value, int fractionDigits) {
		String format = getFormat(fractionDigits);
		DecimalFormat decimalFormat = new DecimalFormat(format);
		return format(value, decimalFormat);
	}

	/**
	 *
	 * @param value - Valor a formatar
	 * @param fractionDigits - Número de casa decimal
	 * @param decimalSeparator - Separador decimal
	 * @return
	 */
	public String format(BigDecimal value, int fractionDigits, char decimalSeparator) {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator(decimalSeparator);
		String format = getFormat(fractionDigits);
		DecimalFormat decimalFormat = new DecimalFormat(format, symbols);
		return format(value, decimalFormat);
	}

	/**
	 * Formata um BigDecimal em uma String, utilizando os símbolos de um locale específico.
	 *
	 * @param bigDecimalToFormat
	 * @param locale
	 * @return String com o valor do BigDecimal formatado com duas casas decimais.
	 * @author Fabiano Joubert Fonseca e Araujo
	 */
	public String format(BigDecimal bigDecimalToFormat, Locale locale) {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);
		DecimalFormat decimalFormat = new DecimalFormat("0.00", symbols);
		decimalFormat.setRoundingMode(RoundingMode.DOWN);

		return format(bigDecimalToFormat, decimalFormat);
	}

	/**
	 * @param value
	 * @param decimalFormat
	 * @return
	 */
	public String formatBigDecimal(String value, DecimalFormat decimalFormat) {
		if (value == null || "".equals(value)) return value;

		return format(new BigDecimal(value), decimalFormat);
	}

	/**
	 *
	 * @param value - Valor a formatar
	 *
	 * @param fractionDigits - Número de casa decimal
	 * @param decimalSeparator - Separador decimal
	 *
	 * @return
	 */
	public String formatBigDecimal(String value, int fractionDigits, char decimalSeparator) {
		if (value == null || "".equals(value)) return value;

		return format(new BigDecimal(value), decimalSeparator);
	}

	/**
	 *
	 * @param value - Valor a formatar
	 * @param integerDigits - Número inteiro
	 * @param fractionDigits - Número de casa decimal
	 * @param decimalSeparator - Separador decimal
	 *
	 * @return
	 */
	public String formatBigDecimal(String value, int integerDigits, int fractionDigits, char decimalSeparator) {
		if (value == null || "".equals(value)) return value;

		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator(decimalSeparator);
		String format = getFormat(fractionDigits);
		DecimalFormat decimalFormat = new DecimalFormat(format, symbols);
		decimalFormat.setMaximumIntegerDigits(integerDigits);
		return decimalFormat.format(new BigDecimal(value));
	}

	/**
	 *  Retorna a parte fracionaria do valor informado.
	 *
	 * @param value
	 * @return
	 */
	public BigDecimal fractionDigits(BigDecimal value) {
		BigDecimal result = null;
		if (value != null) {
			String aux = value.toString();
			int indexOf = aux.indexOf(".");
			if (indexOf > 0) {
				result = new BigDecimal(aux.substring(indexOf + 1));
			}
		}

		return result;
	}

	/**
	 * Exemplo: Value = 40817, Resultado = 40.817
	 *
	 * @param value
	 * @return
	 */
	public String formatNumber(Long value) {
		String result = null;
		if (value != null) {
			Formatter formatter = new Formatter();
			result = formatter.format("%,d", value).toString();
		}

		return result;
	}

	public String getOnlyNumber(String value){
		if (value == null || "".equals(value)) {
			return null;
		}
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < value.length(); i++) {
			if (Character.isDigit(value.charAt(i))){
				str.append((value.charAt(i)));
			}
		}
		return str.toString() != null && !str.toString().equals("") ? str.toString() : null ;
	}

	/**
	 *
	 * @param value
	 * @param valueDe
	 * @param valueAte
	 * @return
	 */
	public boolean isBetween(BigDecimal value, BigDecimal valueDe, BigDecimal valueAte) {
		if (value == null || valueDe == null || valueAte == null) {
			return false;
		}
		if (value.compareTo(valueDe) >= 0 && value.compareTo(valueAte) <= 0) {
			return true;
		}
		return false;
	}

	/**
	 *
	 * @param value
	 * @param valueDe
	 * @param valueAte
	 * @return
	 */
	public boolean isBetween(Integer value, Integer valueDe, Integer valueAte) {
		if (value == null || valueDe == null || valueAte == null) {
			return false;
		}
		if (value.intValue() >= valueDe.intValue() && value.intValue() <= valueAte.intValue()) {
			return true;
		}
		return false;
	}

	/**
	 * Compara se <b>value1</b> e <b>value2</b> são iguais.
	 *
	 * @param value1
	 * @param value2
	 * @return
	 */
	public boolean isEqualsBigDecimal(
			BigDecimal value1,
			BigDecimal value2) {

		if (value1 == null) {
			if (value2 == null) {
				return true;
			} else {
				return false;
			}
		} else if (value2 == null) {
			return false;
		}

		return (value1.compareTo(value2) == 0);
	}

	/**
	 * Compara se <b>value1</b> e <b>value2</b> são iguais.
	 *
	 * @param value1
	 * @param value2
	 * @param scale
	 * @return
	 */
	public boolean isEqualsBigDecimal(
			BigDecimal value1,
			BigDecimal value2,
			int scale) {

		return isEqualsBigDecimal(round(value1, scale), round(value2, scale));
	}

	public boolean isEqualsInteger(Integer value1, Integer value2) {
		if (value1 == null) {
			if (value2 == null) {
				return true;
			} else {
				return false;
			}
		} else if (value2 == null) {
			return false;
		}

		return (value1.intValue() == value2.intValue());
	}

	public boolean isEqualsLong(Long value1, Long value2) {
		if (value1 == null) {
			if (value2 == null) {
				return true;
			} else {
				return false;
			}
		} else if (value2 == null) {
			return false;
		}

		return (value1.longValue() == value2.longValue());
	}


	public boolean isEqualsString(String value1, String value2) {
		if (value1 == null) {
			if (value2 == null) {
				return true;
			} else {
				return false;
			}
		} else if (value2 == null) {
			return false;
		}

		return (value1.equals(value2));
	}

	/**
	 *
	 * @param value
	 * @param valueDe
	 * @return
	 */
	public boolean isGreaterEqualsThan(BigDecimal value, BigDecimal valueDe) {
		if (value == null || valueDe == null) {
			return false;
		}
		if (value.compareTo(valueDe) >= 0) {
			return true;
		}
		return false;
	}

	/**
	 *
	 * @param value
	 * @param valueDe
	 * @return
	 */
	public boolean isGreaterEqualsThan(Integer value, Integer valueDe) {
		if (value == null || valueDe == null) {
			return false;
		}
		if (value.intValue() >= valueDe.intValue()) {
			return true;
		}
		return false;
	}

	/**
	 *
	 * @param value
	 * @param valueDe
	 * @return
	 */
	public boolean isGreaterEqualsThan(Long value, Long valueDe) {
		if (value == null || valueDe == null) {
			return false;
		}
		if (value.longValue() >= valueDe.longValue()) {
			return true;
		}
		return false;
	}
	
	/**
	 *
	 * @param value
	 * @param valueDe
	 * @return
	 */
	public boolean isGreaterThan(BigDecimal value, BigDecimal valueDe) {
		if (value == null || valueDe == null) {
			return false;
		}
		if (value.compareTo(valueDe) > 0) {
			return true;
		}
		return false;
	}

	/**
	 *
	 * @param value
	 * @param valueDe
	 * @return
	 */
	public boolean isGreaterThan(Integer value, Integer valueDe) {
		if (value == null || valueDe == null) {
			return false;
		}
		if (value.intValue() > valueDe.intValue()) {
			return true;
		}
		return false;
	}

	
	/**
	 *
	 * @param value
	 * @param valueDe
	 * @return
	 */
	public boolean isGreaterThan(Long value, Long valueDe) {
		if (value == null || valueDe == null) {
			return false;
		}
		if (value.longValue() > valueDe.longValue()) {
			return true;
		}
		return false;
	}

	/**
	 *
	 * @param value
	 * @param valueAte
	 * @return
	 */
	public boolean isLessEqualsThan(BigDecimal value, BigDecimal valueAte) {
		if (value == null|| valueAte == null) {
			return false;
		}
		if (value.compareTo(valueAte) <= 0) {
			return true;
		}
		return false;
	}

	/**
	 *
	 * @param value
	 * @param valueAte
	 * @return
	 */
	public boolean isLessEqualsThan(Integer value, Integer valueAte) {
		if (value == null|| valueAte == null) {
			return false;
		}
		if (value.intValue() <= valueAte.intValue()) {
			return true;
		}
		return false;
	}
	
	/**
	 *
	 * @param value
	 * @param valueAte
	 * @return
	 */
	public boolean isLessEqualsThan(Long value, Long valueAte) {
		if (value == null|| valueAte == null) {
			return false;
		}
		if (value.longValue() <= valueAte.longValue()) {
			return true;
		}
		return false;
	}

	/**
	 *
	 * @param value
	 * @param valueAte
	 * @return
	 */
	public boolean isLessThan(BigDecimal value, BigDecimal valueAte) {
		if (value == null || valueAte == null) {
			return false;
		}
		if (value.compareTo(valueAte) < 0) {
			return true;
		}
		return false;
	}

	/**
	 *
	 * @param value
	 * @param valueAte
	 * @return
	 */
	public boolean isLessThan(Integer value, Integer valueAte) {
		if (value == null|| valueAte == null) {
			return false;
		}
		if (value.intValue() < valueAte.intValue()) {
			return true;
		}
		return false;
	}
	
	
	/**
	 *
	 * @param value
	 * @param valueAte
	 * @return
	 */
	public boolean isLessThan(Long value, Long valueAte) {
		if (value == null|| valueAte == null) {
			return false;
		}
		if (value.longValue() < valueAte.longValue()) {
			return true;
		}
		return false;
	}

	/**
	 * Verifica se o value informado é um número.
	 *
	 * @param value
	 * @return
	 */
	public boolean isNumber(String value) {
		return (value != null && Pattern.matches("[0-9]+", value));
	}

	/**
	 * Verifica se o value informado está no formato de um BigDecimal.
	 *
	 * @param value
	 * @return
	 */
	public boolean isBigDecimal(String value) {
		return (value != null && Pattern.matches("[0-9]+(\\.[0-9]*){0,1}", value));
	}

	/**
	 * Verifica se o number informado é igual a "1".
	 *
	 * @param number
	 * @return
	 */
	public boolean isTrue(Number number) {
		return (number != null && number.longValue() == 1);
	}

	/**
	 * @param <I>
	 *            - Tipo que implementa {@link Comparable}.
	 * @param valueA
	 * @param valueB
	 * @return <b>True</b> se o valor de <u>A</u> for <u>igual</u> ao valor de <u>B</u>, ou se os <u>dois</u> valores forem <u>nulos</u>; <b>false</b> caso contrário.
	 */
	public <I extends Comparable<I>> boolean isValueEquals(I valueA, I valueB) {
		if ((valueA == null) && (valueB == null)) {
			return true;

		} else if ((valueA == null) || (valueB == null)) {
			return false;

		} else {
			return (valueA.compareTo(valueB) == 0);
		}
	}

	/**
	 * @param <I>
	 *            - Tipo que implementa {@link Comparable}.
	 * @param valueA
	 * @param valueB
	 * @return <b>True</b> se o valor de <u>A</u> for <u>maior ou igual</u> ao valor de <u>B</u>; <b>false</b> caso contrário.
	 */
	public <I extends Comparable<I>> boolean isValueGreaterEqualsThan(I valueA, I valueB) {
		if ((valueA == null) || (valueB == null)) {
			return false;

		} else if (valueA.compareTo(valueB) >= 0) {
			return true;

		} else {
			return false;
		}
	}

	/**
	 * @param <I>
	 *            - Tipo que implementa {@link Comparable}.
	 * @param valueA
	 * @param valueB
	 * @return <b>True</b> se o valor de <u>A</u> for <u>maior</u> que o valor de <u>B</u>; <b>false</b> caso contrário.
	 */
	public <I extends Comparable<I>> boolean isValueGreaterThan(I valueA, I valueB) {
		if ((valueA == null) || (valueB == null)) {
			return false;

		} else if (valueA.compareTo(valueB) > 0) {
			return true;

		} else {
			return false;
		}
	}

	/**
	 * @param <I>
	 *            - Tipo que implementa {@link Comparable}.
	 * @param valueA
	 * @param valueB
	 * @return <b>True</b> se o valor de <u>A</u> for <u>menor ou igual</u> ao valor de <u>B</u>; <b>false</b> caso contrário.
	 */
	public <I extends Comparable<I>> boolean isValueLessEqualsThan(I valueA, I valueB) {
		if ((valueA == null) || (valueB == null)) {
			return false;

		} else if (valueA.compareTo(valueB) <= 0) {
			return true;

		} else {
			return false;
		}
	}

	/**
	 * @param <I>
	 *            - Tipo que implementa {@link Comparable}.
	 * @param valueA
	 * @param valueB
	 * @return <b>True</b> se o valor de <u>A</u> for <u>menor</u> que o valor de <u>B</u>; <b>false</b> caso contrário.
	 */
	public <I extends Comparable<I>> boolean isValueLessThan(I valueA, I valueB) {
		if ((valueA == null) || (valueB == null)) {
			return false;

		} else if (valueA.compareTo(valueB) < 0) {
			return true;

		} else {
			return false;
		}
	}

	public BigDecimal multiplyMinusOne(BigDecimal value) {
		if (value != null) {
			return value.multiply(BigDecimal.ONE.negate());
		}

		return value;
	}

	public BigDecimal negativeValue(BigDecimal value) {
		if (value != null && value.compareTo(BigDecimal.ZERO) > 0) {
			return value.negate();
		}

		return value;
	}

	public BigDecimal positiveValue(BigDecimal value) {
		if (value != null && value.compareTo(BigDecimal.ZERO) < 0) {
			return value.negate();
		}

		return value;
	}
	
	public BigDecimal parseBigDecimal(String value) {
		return parseBigDecimal(value, null);
	}

	public BigDecimal parseBigDecimal(String value, BigDecimal defaultValue) {
		if (value == null || "".equals(value)) {
			return defaultValue;
		}

		return new BigDecimal(value);
	}

	public BigInteger parseBigInteger(String value) {
		if (value == null || "".equals(value)) {
			return null;
		}

		return new BigInteger(value);
	}

	public Integer parseIntSeNumber(String value) {
		if (isNumber(value)) {
			return parseInt(value);
		}
		return null;
	}

	public Integer parseInt(String value) {
		if (value == null || "".equals(value)) {
			return null;
		}

		return Integer.parseInt(value);
	}

	public Integer parseInt(BigDecimal value) {
		if (value == null) {
			return null;
		}

		if (value.longValue() < Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		} else if (value.longValue() > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		} else {
			return value.intValue();
		}
	}

	public Integer parseIntOnlyNumber(String value) {
		if (value == null || "".equals(value)) {
			return null;
		}
		String newValue = getOnlyNumber(value);
		if (newValue == null || "".equals(newValue)) {
			return null;
		}
		return Integer.parseInt(newValue);
	}

	public Long parseLongSeNumber(String value) {
		if (isNumber(value)) {
			return parseLong(value);
		}
		return null;
	}

	public Long parseLong(String value) {
		if (isEmpty(value)) {
			return null;
		}

		return Long.parseLong(value);
	}

	public Long parseLongOnlyNumber(String value) {
		if (value == null || "".equals(value)) {
			return null;
		}
		String newValue = getOnlyNumber(value);
		if (newValue == null || "".equals(newValue)) {
			return null;
		}
		return Long.parseLong(newValue);
	}

	public List<Long> parseLong(String[] values) {
		if (values == null || values.length <= 0) {
			return null;
		}

		List<Long> result = new ArrayList<Long>();
		for (String value : values) {
			result.add(parseLong(value));
		}

		return result;
	}

	public List<Long> parseLong(List<String> values) {
		if (values == null || values.isEmpty()) {
			return null;
		}

		List<Long> result = new ArrayList<Long>();
		for (String value : values) {
			result.add(parseLong(value));
		}

		return result;
	}

	public BigDecimal round(BigDecimal value, int scale) {
		BigDecimal result = null;

		if (value != null) {
			result = value.setScale(scale, BigDecimal.ROUND_HALF_EVEN);
		}

		return result;
	}

	public BigDecimal trunc(BigDecimal value, int scale) {
		BigDecimal result = null;

		if (value != null) {
			result = value.setScale(scale, BigDecimal.ROUND_DOWN);
		}

		return result;
	}

	public BigDecimal divide(BigDecimal dividendo, BigDecimal divisor, int scale) {
		BigDecimal result = null;
		if (dividendo != null && divisor != null) {
			result = dividendo.divide(divisor, scale, BigDecimal.ROUND_HALF_EVEN);
		}

		return result;
	}

	public String toString(Integer value) {
		return (value == null ? null : value.toString());
	}

	public String toString(Long value) {
		return (value == null ? null : value.toString());
	}

	public String toString(BigInteger value) {
		return (value == null ? null : value.toString());
	}

	private String getFormat(int fractionDigits) {
		String format = "0";
		if (fractionDigits > 0) {
			format += ".";
			for (int i = 0; i < fractionDigits; i++) {
				format += "0";
			}
		}
		return format;
	}

	public long base36ToDecimal(String s) {
        String digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        s = s.toUpperCase();
        long val = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int d = digits.indexOf(c);
            val = 36*val + d;
        }
        return val;
    }


    // precondition:  d is a nonnegative integer
    public String decimalToBase36(long d) {
        String digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if (d == 0) return "0";
        String hex = "";
        while (d > 0) {
            int digit = (int)(d % 36);                // rightmost digit
            hex = digits.charAt(digit) + hex;  // string concatenation
            d = d / 36;
        }
        return hex;
    }

    public Integer coalesce(Integer value, Integer defaultValue) {
    	if (value != null) {
    		return value;
    	}
    	return defaultValue;
    }

    public Long coalesce(Long value, Long defaultValue) {
    	if (value != null) {
    		return value;
    	}
    	return defaultValue;
    }

    public BigDecimal coalesce(BigDecimal value, BigDecimal defaultValue) {
    	if (value != null) {
    		return value;
    	}
    	return defaultValue;
    }

    public String coalesce(String value, String defaultValue) {
    	if (value != null) {
    		return value;
    	}
    	return defaultValue;
    }    

    public BigDecimal add(BigDecimal... valores) {
    	return add(2, valores);
    }
    
    public BigDecimal add(int scale, BigDecimal... valores) {
    	if (valores == null)
    		return null;
    	
		BigDecimal result = BigDecimal.ZERO;
		
		for (BigDecimal valor : valores) {
			valor = coalesce(valor, BigDecimal.ZERO);
			result = result.add(valor);
		}
		return setScale(result, scale);
	}
    
	public BigDecimal add(BigDecimal value1, BigDecimal value2, int scale) {
		BigDecimal result = null;

		if (value1 != null || value2 != null) {
			result = (value1 == null ? BigDecimal.ZERO : value1);
			if (value2 != null) {
				result = result.add(value2);
			}

			if (scale >= 0) {
				result = setScale(result, scale);
			}
		}

		return result;
	}
	
	public BigDecimal subtract(BigDecimal value1, BigDecimal value2, int scale) {
		BigDecimal result = null;

		if (value1 != null || value2 != null) {
			result = (value1 == null ? BigDecimal.ZERO : value1);
			if (value2 != null) {
				result = result.subtract(value2);
			}

			if (scale >= 0) {
				result = setScale(result, scale);
			}
		}

		return result;
	}
	
	public BigDecimal setScale(BigDecimal value, int scale) {
		if (value == null) {
			return null;
		}
		return value.setScale(scale, BigDecimal.ROUND_HALF_EVEN);
	}

	
	public boolean isEqualsBigInteger(BigInteger value1, BigInteger value2) {
		if (value1 == null) {
			if (value2 == null) {
				return true;
			} else {
				return false;
			}
		} else if (value2 == null) {
			return false;
		}

		return (value1.compareTo(value2) == 0);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public boolean isEmpty(String value) {
		if (value == null || "".equals(value)) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param value
	 * @param length
	 * @return
	 */
	public String trunc(String value, int length) {
		if (value == null) {
			return null;
		}
		if (length < 0){
			throw new StringIndexOutOfBoundsException("String index out of range: " + length + " for string " + value);
		}

		if (value.length() > length) {
			value = value.substring(0, length);
		}

		return value;
	}

	/**
	 * 
	 * @param valueA
	 * @param valueB
	 * @return
	 */
	public BigDecimal minValue(BigDecimal valueA, BigDecimal valueB) {
		if (valueA == null) {
			return valueB;
		}
		if (valueB == null) {
			return valueA;
		}
		
		if (valueB.compareTo(valueA) < 0) {
			return valueB;
		}
		return valueA;
	}

	/*public static void main(String[] args) {
		NumberUtils numberUtils = new NumberUtils();
		BigDecimal valueA = new BigDecimal("4"); 
		BigDecimal valueB = new BigDecimal("3");

		System.out.println("minValue = " + numberUtils.minValue(valueA, valueB));	
	}*/

}