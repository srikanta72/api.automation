package excel.op.util;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.List;

public class OracleExcelOrderFileFormatReader {
    private ExcelReader excelReader;
    private List<String> currentRowCellList= new ArrayList<>();
    private List<List<String>> singleOrderWithAllItems= new ArrayList<>();
    private int headerCellCount;

    public void readOracleOrderExcelFile(String filePath, int sheetIndex){
        excelReader= new ExcelReader(filePath);
        Sheet currentSheet= excelReader.getSheetByIndexStartsFromZero(sheetIndex);
        List<String> headers= excelReader.getHeaders();
        headerCellCount =headers.size();
        currentSheet.forEach(row -> {
            processCurrentRow(excelReader, currentSheet, row, row.getRowNum());
        });
    }

    private void processCurrentRow(ExcelReader excelReader, Sheet sheet, Row row, int rowIndex){
        currentRowCellList= excelReader.getCellValuesByRowIndex(rowIndex);
        if(headerCellCount>currentRowCellList.size()){
            singleOrderWithAllItems.add(currentRowCellList);
//            System.out.println("Updated :currentRowCellList: "+singleOrderWithAllItems);
        }else if(headerCellCount==currentRowCellList.size()){
            System.out.println("Updated :currentRowCellList: "+singleOrderWithAllItems);
            executeSingleOrderWithItems(singleOrderWithAllItems);
            singleOrderWithAllItems=new ArrayList<>();
//            System.out.println("Regular :completeRowCellList: " + currentRowCellList);
            singleOrderWithAllItems.add(currentRowCellList);
        }else{
            System.out.println("Excel data is mismatching with header: " + currentRowCellList);
        }
//        System.out.println("Updated :currentRowCellList: "+singleOrderWithAllItems);
    }

    public void executeSingleOrderWithItems(List<List<String>> singleOrderWithAllSubItems){
        //execute your order
    }
}
