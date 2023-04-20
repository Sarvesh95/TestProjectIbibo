package excelAndreports;

import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	public static XSSFWorkbook book;
	 public static   XSSFSheet sheet;
		

		
		public XSSFSheet getWorksheet(String pathname, String sheetname)  {
			try {
				book = new XSSFWorkbook(pathname);
				sheet = book.getSheet(sheetname);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return sheet;
			
			
		
		}
		
	/*	public void setCellType(XSSFSheet worksheet, int row, int col) {
			
			worksheet.setCe
		}*/
		
		public void closeWorkbook() throws IOException {
			
			book.close();
		}
		
		public int getRowCount(XSSFSheet worksheet) {
			
			return worksheet.getPhysicalNumberOfRows();
		}
		
		 public int getColCount(XSSFSheet worksheet) {
			 
			 return worksheet.getRow(0).getPhysicalNumberOfCells();
		 }
		 
		 
		 
		 public CellType readCellType(XSSFSheet worksheet, int row, int col) {
			 
			 CellType celltype = worksheet.getRow(row).getCell(col).getCellType();
			 
			 return celltype;
		 }
		 
		 public String readStringCell(XSSFSheet worksheet, int row, int col) {
			 
			  worksheet.getRow(row).getCell(col).setCellType(CellType.STRING);;
			      
			      
			     String Celldata = worksheet.getRow(row).getCell(col).getStringCellValue();
			 
			 return Celldata;
		 }
		 
		 public int readNumericCell(XSSFSheet worksheet, int row, int col) {
			 
			 
			int celldata = (int) worksheet.getRow(row).getCell(col).getNumericCellValue();
			
			return celldata;
		 }
	
}
