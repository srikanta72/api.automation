package excel.op.util;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {
    private Workbook workbook;
    private Sheet currentSheet;

    public ExcelReader(String excelFilePath) {
        try(FileInputStream excelFile = new FileInputStream(new File(excelFilePath))){
            workbook = new XSSFWorkbook(excelFile);
            currentSheet=workbook.getSheetAt(0);    //By default reads firstSheet
        }catch (FileNotFoundException fnfe){
            System.err.println("The excel file does not exists in the provided path. "+fnfe);
        }catch (IOException ioe){
            System.err.println("Not able to read the Excel file provided. "+ioe);
        }
    }

    public Sheet getSheetByIndexStartsFromZero(int sheetIndex){
        currentSheet=workbook.getSheetAt(sheetIndex);
        return currentSheet;
    }
    public Sheet getSheetBySheetName(String sheetName){
        int sheetIndex=workbook.getSheetIndex(sheetName);
        currentSheet= getSheetByIndexStartsFromZero(sheetIndex);
        return currentSheet;
    }

    public List<String> getHeaders(){
        return getHeadersByRowIndex(0);
    }
    public List<String> getHeadersFromSheet(Sheet sheet){
        currentSheet= sheet;
        return getHeadersByRowIndex(0);
    }
    public List<String> getHeadersByRowIndex(int headerRowIndex){
        return getCellValuesByRowIndex(headerRowIndex);
    }
    public List<String> getCellValuesByRowIndex(int rowIndex){
        Row headerRow=currentSheet.getRow(rowIndex);
        List<String> headers= new ArrayList<>();
        headerRow.forEach(cell -> headers.add(cell.toString()));
        return headers;
    }



}
