package com.sample;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



public class ReadExcelByColumnName {

	public static void main(String[] args) {
		
		try{

			//FileInputStream file = new FileInputStream(new File("C:/Users/Thangavel_Loganathan/Desktop/Sample.xls"));
			InputStream file = new URL("http://opendatakit.org/wp-content/uploads/static/sample.xls").openStream();  // Read from HTTP URL
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheetAt(0);
			int totalRows = sheet.getPhysicalNumberOfRows();
			List<HashMap<String, String>> rowList = new ArrayList<>();
			Map<String,Integer> map = new LinkedHashMap<String,Integer>();
			HSSFRow row = sheet.getRow(0);
			int minColIndex = row.getFirstCellNum();
			int maxColIndex = row.getLastCellNum();
			
     		for(int index = minColIndex; index < maxColIndex; index++) {
				HSSFCell cell = row.getCell(index);
				map.put(cell.getStringCellValue().toString().replace("\n", "").trim(), cell.getColumnIndex());
			} 
			
			for(int x = 1; x < totalRows; x++){
				Map<String, String> rowMap = new HashMap<>();	
				HSSFRow dataRow = sheet.getRow(x);
				rowMap.put("Date",dataRow.getCell(map.get("Date")).getStringCellValue());
				// You can iterate the column which you want
				//jsonValueMap = new com.google.gson.Gson().toJson(rowMap);
				rowList.add(rowMap);
			} 
			file.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
