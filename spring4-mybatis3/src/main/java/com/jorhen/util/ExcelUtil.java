package com.jorhen.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelUtil {
	
 
	/**
	 * 导出Excel
	 * @param sheetName sheet名称
	 * @param title 标题
	 * @param values 内容
	 * @param wb HSSFWorkbook对象
	 * @return
	 */
	public static HSSFWorkbook getHSSFWorkbook(String sheetName,String []title,String [][]values, HSSFWorkbook wb){
 
		// 第一步，创建一个HSSFWorkbook，对应一个Excel文件
		if(wb == null){
			wb = new HSSFWorkbook();
		}
 
		// 第二步，在workbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(sheetName);
 
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制
		HSSFRow row = sheet.createRow(0);
 
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
 
		//声明列对象
		HSSFCell cell = null;
 
		//创建标题
		for(int i=0;i<title.length;i++){
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
			cell.setCellStyle(style);
		}
 
		//创建内容
		for(int i=0;i<values.length;i++){
			row = sheet.createRow(i + 1);
			if(values[i]!=null){
				for(int j=0;j<values[i].length;j++){
					//将内容按顺序赋给对应的列对象
					row.createCell(j).setCellValue(values[i][j]);
				}
			}else{
				continue;
			}
 
		}
		return wb;
	}
 

 
	/**
	 * 描述：对表格中数值进行格式化
	 *
	 * @param cell
	 * @return
	 */
	public static Object getCellValue(Cell cell) {
		Object value = null;
		DecimalFormat df = new DecimalFormat("0"); // 格式化number String字符
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd"); // 日期格式化
		DecimalFormat df2 = new DecimalFormat("0"); // 格式化数字
 
		switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				value = cell.getRichStringCellValue().getString();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if ("General".equals(cell.getCellStyle().getDataFormatString())) {
					value = df.format(cell.getNumericCellValue());
				} else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
					value = sdf.format(cell.getDateCellValue());
				} else {
					value = df2.format(cell.getNumericCellValue());
				}
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				value = cell.getBooleanCellValue();
				break;
			case Cell.CELL_TYPE_BLANK:
				value = "";
				break;
			default:
				break;
		}
		return value;
	}


}
