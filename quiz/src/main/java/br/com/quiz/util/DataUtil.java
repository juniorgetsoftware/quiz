package br.com.quiz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtil {

	public static String dateParaString(Date data) {
		return new SimpleDateFormat("dd/MM/yyyy").format(data);
	}

	public static Date stringParaDate(String data) {
		Date date = null;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
