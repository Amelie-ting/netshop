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
	 * ��ⷽ��
	 */
	@Override
	public List<Items> readXlsCome(String excelurl) throws IOException, SQLException {
		//����ϴ���excel����
		String url=excelurl;
		
		//������
		InputStream is = new FileInputStream(url);
	
		//д���ļ�
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		
		//�����յ�ʵ����
		Items items=null;
		
		List<Items> list = new ArrayList<Items>();
		// ѭ��������Sheet
		for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
			//ʹ��ÿһ��sheet
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
			if (hssfSheet == null) {
				continue;
			}
			// ѭ����Row
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow != null) {
					items = new Items();
					//��ȡ������				
					HSSFCell no = hssfRow.getCell(0);
					//��ȡ����
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
		//����ϴ���excel����
				String url=excelurl;
				
				//������
				InputStream is = new FileInputStream(url);
			
				//д���ļ�
				HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
				
				//�����յ�ʵ����
				Items items=null;
				
				List<Items> list = new ArrayList<Items>();
				// ѭ��������Sheet
				for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
					//ʹ��ÿһ��sheet
					HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
					if (hssfSheet == null) {
						continue;
					}
					// ѭ����Row
					for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
						HSSFRow hssfRow = hssfSheet.getRow(rowNum);
						if (hssfRow != null) {
							items = new Items();
							//��ȡ������				
							HSSFCell no = hssfRow.getCell(0);
							//��ȡ����
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
		            // ���ز������͵�ֵ
		            return String.valueOf(hssfCell.getBooleanCellValue());
		        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
		            // ������ֵ���͵�ֵ
		            return String.valueOf(hssfCell.getNumericCellValue());
		        } else {
		            // �����ַ������͵�ֵ
		            return String.valueOf(hssfCell.getStringCellValue());
		        }
		    }



}
