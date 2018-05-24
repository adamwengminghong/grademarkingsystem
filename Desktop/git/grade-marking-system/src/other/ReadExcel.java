package other;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import core.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReadExcel {
	
	private static final String EXCEL_XLS = "xls";
	private static final String EXCEL_XLSX = "xlsx";

	@SuppressWarnings("resource")
	public static ObservableList<Student> readStudentExcelFile(File input) throws IOException {
		ObservableList<Student> data = FXCollections.observableArrayList();
		FileInputStream inputStream = new FileInputStream(input);
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator <Cell> cellIterator = nextRow.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				System.out.print(cell.getStringCellValue());
				System.out.print("\t");
			}
			data.add(new Student(nextRow.getCell(0).getStringCellValue(), nextRow.getCell(1).getStringCellValue(), nextRow.getCell(2).getStringCellValue(), nextRow.getCell(3).getStringCellValue()));
		}
		return data;
	}
	
	public static boolean checkExcelValid(String filename) {
		boolean status = false;
		if (filename.endsWith(EXCEL_XLS) || filename.endsWith(EXCEL_XLSX)) 
		{
			status = true;
		}
		return status;
	}

}
