package com.formatiointerne.springmvc.pratiq1.Conversion;

import org.springframework.core.convert.converter.Converter;

public class ConvertStringToDouble implements Converter<String, Double> {

	@Override
	public Double convert(String source) {
		Double d =0.0;
		try{
			 d = Double.parseDouble(source);
		} catch (NumberFormatException e) {
			e.getMessage();
		}
		
		return d;
	}

}
