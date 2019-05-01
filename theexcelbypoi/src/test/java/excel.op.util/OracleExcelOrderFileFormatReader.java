package excel.op.util;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.List;

public class OracleExcelOrderFileFormatReader {
    private ExcelReader excelReader;
    private List<String> completeRowCellList= new ArrayList<>();
    private List<String> previousRowCellList= new ArrayList<>();
    private List<String> currentRowCellList= new ArrayList<>();
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
        if(headerCellCount!=currentRowCellList.size()){
            for(int countGap=0; countGap<(headerCellCount-currentRowCellList.size()); countGap++){
                currentRowCellList.add(countGap, completeRowCellList.get(countGap));
            }
            System.out.println("Updated :currentRowCellList: "+currentRowCellList);
            previousRowCellList=currentRowCellList;
        }else{
            System.out.println("Regular :completeRowCellList: " + currentRowCellList);
            completeRowCellList = currentRowCellList;
        }
        previousRowCellList=currentRowCellList;

    }
}
