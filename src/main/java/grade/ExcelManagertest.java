package grade;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelManagertest {
    public static void main(String[] args) throws EncryptedDocumentException, IOException, InvalidFormatException {
        List<StudentDTO> studentList = getStudentList();
        
        // id 기준 오름차순 정렬
        Collections.sort(studentList, new Comparator<StudentDTO>() {
            @Override
            public int compare(StudentDTO o1, StudentDTO o2) {
                // TODO Auto-generated method stub
                return o1.getId().compareTo(o2.getId());
            }
        });
        
        for (StudentDTO StudentDTO : studentList) {
            System.out.println(StudentDTO.getId() + ", " + StudentDTO.getName() + ", " + StudentDTO.getBirthDate());
        }
        
//        writeExcelFile(studentList);
    }
 
    public static void writeExcelFile(List<StudentDTO> list) throws EncryptedDocumentException, IOException {
        String filePath = "student_transfer.xlsx";    // 저장할 파일 경로
        System.out.println(filePath);
        FileOutputStream fos = new FileOutputStream(filePath);
        
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("studentList");    // sheet 생성
        
        XSSFRow curRow;
        
        int row = list.size();    // list 크기
        for (int i = 0; i < row; i++) {
            curRow = sheet.createRow(i);    // row 생성
            curRow.createCell(0).setCellValue(list.get(i).getId());    // row에 각 cell 저장
            curRow.createCell(1).setCellValue(list.get(i).getName());
            curRow.createCell(2).setCellValue(list.get(i).getBirthDate());
        }
        
        workbook.write(fos);
        fos.close();
    }
    
    public static List<StudentDTO> getStudentList() throws EncryptedDocumentException, IOException, InvalidFormatException {
        List<StudentDTO> studentList = new ArrayList<StudentDTO>();
 
        String filePath = "student.xlsx";
        // String filePath = "C:\\student.xls";
        InputStream inputStream = new FileInputStream(filePath);
 
        // 엑셀 로드
        Workbook workbook = WorkbookFactory.create(inputStream);
        // 시트 로드 0, 첫번째 시트 로드
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowItr = sheet.iterator();
        // 행만큼 반복
        while (rowItr.hasNext()) {
            StudentDTO student = new StudentDTO();
            Row row = rowItr.next();
            // 첫 번째 행이 헤더인 경우 스킵, 2번째 행부터 data 로드
            if (row.getRowNum() == 0) {
                continue;
            }
            Iterator<Cell> cellItr = row.cellIterator();
            // 한 행이 한열 씩 읽기 (셀 읽기)
            while (cellItr.hasNext()) {
                Cell cell = cellItr.next();
                int index = cell.getColumnIndex();
                switch (index) {
                case 0: // 번호
                    // 셀이 숫자형인 경우 Double형으로 변환 후 int형으로 변환
                    student.setId(((Double) getValueFromCell(cell)).intValue());
                    break;
                case 1: // 성명
                    student.setName((String) getValueFromCell(cell));
                    break;
                case 2: // 생년월일
                    student.setBirthDate((Date) getValueFromCell(cell));
                    break;
                }
            }
            studentList.add(student);
        }
        return studentList;
    }
 
    private static Object getValueFromCell(Cell cell) {
        switch (cell.getCellType()) {
        case Cell.CELL_TYPE_STRING:
            return cell.getStringCellValue();
        case Cell.CELL_TYPE_BOOLEAN:
            return cell.getBooleanCellValue();
        case Cell.CELL_TYPE_NUMERIC:
            if (DateUtil.isCellDateFormatted(cell)) {
                return cell.getDateCellValue();
            }
            return cell.getNumericCellValue();
        case Cell.CELL_TYPE_FORMULA:
            return cell.getCellFormula();
        case Cell.CELL_TYPE_BLANK:
            return "";
        default:
            return "";
        }
    }
}
