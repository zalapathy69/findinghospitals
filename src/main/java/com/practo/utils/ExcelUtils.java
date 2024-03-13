package com.practo.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// TODO: write comments
public class ExcelUtils {

	public static void write(String sheetName, int rownum, int colnum, String data) throws IOException {
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\Output.xlsx");

		XSSFWorkbook book = new XSSFWorkbook(file);

		if (book.getSheetIndex(sheetName) == -1) {
			book.createSheet(sheetName);
		}

		XSSFSheet sheet = book.getSheet(sheetName);

		if (sheet.getRow(rownum) == null) {
			sheet.createRow(rownum);
		}

		XSSFRow row = sheet.getRow(rownum);
		XSSFCell cell = row.createCell(colnum);
		cell.setCellValue(data);
		FileOutputStream fo = new FileOutputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\Output.xlsx");

		book.write(fo);
		book.close();
		file.close();
		fo.flush();
		fo.close();

	}
}
