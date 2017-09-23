package com.michong.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;


public class ExcelReadUtil {
	
	private static Logger log = Logger.getLogger(ExcelReadUtil.class);

	/**
	 * 读取Excel测试，兼容 Excel 2003/2007/2010
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List readExcel(String path) {
		SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd");
		File excelFile;
		FileInputStream is;
		List sheetlist = new ArrayList();;//sheet集合
		List rowlist;//row集合
		List celllist;//cell集合
		try {
			// 同时支持Excel 2003、2007
			excelFile = new File(path); // 创建文件对象
			is = new FileInputStream(excelFile); // 文件流
			Workbook workbook = WorkbookFactory.create(is); // 这种方式 Excel 2003/2007/2010 都是可以处理的
			int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量
			// 遍历每个Sheet
			for (int s = 0; s < sheetCount; s++) {
				Sheet sheet = workbook.getSheetAt(s);
				int rowCount = sheet.getPhysicalNumberOfRows(); // 获取总行数
				// 遍历每一行
				rowlist = new ArrayList();
				for (int r = 0; r < rowCount; r++) {
					Row row = sheet.getRow(r);
					int cellCount = row.getPhysicalNumberOfCells(); // 获取总列数
					// 遍历每一列
					celllist= new ArrayList();
					for (int c = 0; c < cellCount; c++) {
						Cell cell = row.getCell(c);
						int cellType = cell.getCellType();
						String cellValue = null;
						switch (cellType) {
						case Cell.CELL_TYPE_STRING: // 文本
							cellValue = cell.getStringCellValue();
							break;
						case Cell.CELL_TYPE_NUMERIC: // 数字、日期
							if (DateUtil.isCellDateFormatted(cell)) {
								cellValue = dateFmt.format(cell.getDateCellValue()); // 日期型
							} else {
								cellValue = String.valueOf(cell.getNumericCellValue()); // 数字
							}
							break;
						case Cell.CELL_TYPE_BOOLEAN: // 布尔型
							cellValue = String.valueOf(cell.getBooleanCellValue());
							break;
						case Cell.CELL_TYPE_BLANK: // 空白
							cellValue = cell.getStringCellValue();
							break;
						case Cell.CELL_TYPE_ERROR: // 错误
							cellValue = "错误";
							break;
						case Cell.CELL_TYPE_FORMULA: // 公式
							cellValue = "错误";
							break;
						default:
							cellValue = "错误";
						}
						celllist.add(cellValue);
						log.info(cellValue + "\t");
						//System.out.print(cellValue + "\t");
					}
					rowlist.add(celllist);
					log.info(celllist);
					//System.out.println();
				}
				sheetlist.add(rowlist);
				log.info(rowlist);
			}
			if(null != is){
				is.close();
			}
		} catch (Exception e) {
			log.error("读excel出错:",e);
			//System.out.println("读excel出错");
		}
		return sheetlist;
	}
	
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		String path = "C:\\Users\\liuxin_PC\\Desktop\\test.xlsx";
		List excel = readExcel(path);
		System.out.println(excel);
	}
}
