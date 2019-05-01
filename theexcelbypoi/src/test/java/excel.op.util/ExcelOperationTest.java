package excel.op.util;
public class ExcelOperationTest{

    public static void main(String[] args){

        String filePath="C:\\Users\\Srikanta\\Documents\\Archive_01\\Lucky_archive\\ProgramRelated\\others\\ReadWriteOracleExcel.xlsx";
        ExcelReader excelReader= new ExcelReader(filePath);
//        System.out.println(excelReader.getHeaders());
        OracleExcelOrderFileFormatReader oracleExcelOrderFileFormatReader= new OracleExcelOrderFileFormatReader();
        oracleExcelOrderFileFormatReader.readOracleOrderExcelFile(filePath, 1);

    }

}