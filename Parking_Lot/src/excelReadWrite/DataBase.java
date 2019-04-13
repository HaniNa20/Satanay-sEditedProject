package excelReadWrite;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataBase{
	
	public File file;
	public FileInputStream is;
	public Workbook workbook;
	public Sheet sheet;
	
	private String fileName;
	private String sheetName;
	
	private FileOutputStream outputStream;
	
	private int sheetID;
	
	public DataBase(String fileName, String sheetName){
		makeConnection(fileName);
		connectToSheet(sheetName);
		this.fileName = fileName;
		this.sheetName = sheetName;
	}
	
	
	public void makeConnection(String fileName){
		try {
		file = new File("C:\\Users\\Hani\\Documents\\NetBeansProjects\\Parking_Lot\\src\\excelReadWrite", fileName);
		is = new FileInputStream(file);
		workbook = null;
		String fileExtension = fileName.substring(fileName.indexOf("."));
		if(fileExtension.equals(".xlsx")) {
			workbook = new XSSFWorkbook (is);
		}
		else if(fileExtension.equals(".xls")) {
			workbook = new HSSFWorkbook(is);
		}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void connectToSheet(String sheetName) {
		sheet = workbook.getSheet(sheetName);
		sheetID = 0;
	}
	
	//get methods
	
	public String getSheetName() {
		return this.sheetName;
	}
	
	public int getSheetID() {
		return this.sheetID;
	}
	
	public Object getCellValue(int rowNum, int cellNum) {
		Row row = sheet.getRow(rowNum);
                                if(row.getCell(cellNum).getCellType() == CellType.STRING) {
					return (String) row.getCell(cellNum).getStringCellValue();
				}
				if(row.getCell(cellNum).getCellType() == CellType.NUMERIC) {
					return (int) row.getCell(cellNum).getNumericCellValue();
				}
				if(row.getCell(cellNum).getCellType() == CellType.BOOLEAN) {
					return (boolean) row.getCell(cellNum).getBooleanCellValue();
                                }
                return null;
	}
	
	
	public int getRowCount() {
		return sheet.getLastRowNum()  - sheet.getFirstRowNum();
	}
	
	public Sheet getSheet() {
		return sheet;
		
	}
        
        public int getNumbOfRows() {
            return sheet.getPhysicalNumberOfRows();
        }
	
	//Read Methods
	
	public void printAllData() {
		makeConnection(fileName);
		connectToSheet(sheetName);
		
		int rowCount = sheet.getLastRowNum()  - sheet.getFirstRowNum();
		for(int i = 0; i < rowCount+1; i++) {
			Row row = sheet.getRow(i);
			for(int j = 0; j < row.getLastCellNum(); j++) {
				if(row.getCell(j).getCellType() == CellType.STRING) {
					System.out.print(row.getCell(j).getStringCellValue() + "\t");
				}
				if(row.getCell(j).getCellType() == CellType.NUMERIC) {
					System.out.print(row.getCell(j).getNumericCellValue() + "\t");
				}
				if(row.getCell(j).getCellType() == CellType.BOOLEAN) {
					System.out.print(row.getCell(j).getBooleanCellValue() + "\t");
				}
			}
			System.out.println();
		}
		try {
		is.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void printRow(int rowNum) {
		makeConnection(fileName);
		connectToSheet(sheetName);
		
		Row row = sheet.getRow(rowNum);
		for(int i = 0; i < row.getLastCellNum(); i++) {
			System.out.print(row.getCell(i) + " ");
		}
		System.out.println();
		
		try {
			is.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void printCell(int rowNum, int cellNum) {
		makeConnection(fileName);
		connectToSheet(sheetName);
		
		Row row = sheet.getRow(rowNum);
		System.out.println(row.getCell(cellNum));
		
		try {
			is.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	//Write methods
	
	public void writeInNewRow(Object Data[]){
		makeConnection(fileName);
		connectToSheet(sheetName);
		
		int rowCount = sheet.getPhysicalNumberOfRows();
                Row newRow = sheet.createRow(rowCount);
		int cellNum = 0;
		for(Object temp : Data) {
			Cell cell = newRow.createCell(cellNum++);
			if(temp instanceof String) {
				cell.setCellValue((String) temp);
			}
			else if(temp instanceof Integer) {
				cell.setCellValue((Integer) temp);
			}
			else if(temp instanceof Boolean) {
				cell.setCellValue((boolean) temp);
			}
		}	
		try {
			is.close();
			outputStream = new FileOutputStream(file);
			workbook.write(outputStream);
			outputStream.close();
		}
		catch(FileNotFoundException e) {
                    e.printStackTrace();
		}
		catch(IOException e) {
                    e.printStackTrace();
		}
	}
	
	public void writeInCell(int rowNum, int cellNum, Object Data){
		makeConnection(fileName);
		connectToSheet(sheetName);
		
		Row row = sheet.getRow(rowNum);
		Cell cell = row.createCell(cellNum);
		if(Data instanceof String) {
			cell.setCellValue((String) Data);
		}
		else if(Data instanceof Integer) {
			cell.setCellValue((Integer) Data);
		}
		else if(Data instanceof Boolean) {
			cell.setCellValue((boolean) Data);
		}
		try {
			is.close();
			outputStream = new FileOutputStream(file);
			workbook.write(outputStream);
			outputStream.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void deleteRow(int rowNum) {
		Row deltRow = sheet.getRow(rowNum-1);
		Row lastRow = sheet.getRow(sheet.getLastRowNum());
		int i = 0;
		for(Cell c : lastRow) {
			Cell x = deltRow.getCell(i);
			if(c.getCellType() == CellType.STRING) {
				x.setCellValue( c.getStringCellValue());
			}
			else if(c.getCellType() == CellType.NUMERIC) {
				x.setCellValue( c.getNumericCellValue());
			}
			else if (c.getCellType() == CellType.BOOLEAN) {
				x.setCellValue( c.getBooleanCellValue());
			}
			i++;
		}
		sheet.removeRow(sheet.getRow(sheet.getLastRowNum()));
		
		try {
			is.close();
			outputStream = new FileOutputStream(file);
			workbook.write(outputStream);
			outputStream.close();
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
