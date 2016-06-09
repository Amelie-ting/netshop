package com.netshop.dao.implement;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.netshop.dao.ControlDao;
import com.netshop.model.Items;

public class ControlDaoImpl implements ControlDao {
	/**
	 * 入库方法
	 */
	@Override
	public List<Items> readXlsCome(String excelurl) throws IOException, SQLException {
		//获得上传的excel数据
		String url=excelurl;
		
		//读入流
		InputStream is = new FileInputStream(url);
	
		//写入文件
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		
		//创建空的实体类
		Items items=null;
		
		List<Items> list = new ArrayList<Items>();
		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			//使用每一张sheet
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// 循环行Row
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow != null) {
					items = new Items();
					//获取条形码				
					HSSFCell no = hssfRow.getCell(0);
					//获取数量
					HSSFCell num = hssfRow.getCell(1);
					
					items.setBarcode(getValue(no));
					
					items.setItem_stock(getValue(num));
					
					list.add(items);
				}
			}
		}
		return list;
	}
	
	@Override
	public List<Items> readXlsGo(String excelurl) throws IOException, SQLException {
		//获得上传的excel数据
				String url=excelurl;
				
				//读入流
				InputStream is = new FileInputStream(url);
			
				//写入文件
				HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
				
				//创建空的实体类
				Items items=null;
				
				List<Items> list = new ArrayList<Items>();
				// 循环工作表Sheet
				for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
					//使用每一张sheet
					HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
					if (hssfSheet == null) {
						continue;
					}
					// 循环行Row
					for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
						HSSFRow hssfRow = hssfSheet.getRow(rowNum);
						if (hssfRow != null) {
							items = new Items();
							//获取条形码				
							HSSFCell no = hssfRow.getCell(0);
							//获取数量
							HSSFCell num = hssfRow.getCell(1);
							items.setBarcode(getValue(no));
							items.setItem_stock(getValue(num));
							list.add(items);
						}
					}
				}
				return list;
	}
	
	 @SuppressWarnings("static-access")
		private String getValue(HSSFCell hssfCell) {
		        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
		            // 返回布尔类型的值
		            return String.valueOf(hssfCell.getBooleanCellValue());
		        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
		            // 返回数值类型的值
		            return String.valueOf(hssfCell.getNumericCellValue());
		        } else {
		            // 返回字符串类型的值
		            return String.valueOf(hssfCell.getStringCellValue());
		        }
		    }



}
