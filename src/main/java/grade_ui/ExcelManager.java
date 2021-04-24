package grade_ui;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import grade_dto.BanDto;
import grade_dto.ScoreDto;
import grade_dto.StudentDto;
import grade_dto.SubjectDto;

public class ExcelManager {
/*	public static void main(String[] args) throws EncryptedDocumentException, IOException, InvalidFormatException {
		List<StudentDto> studentList = getStudentList();

//        // id 기준 오름차순 정렬
//        Collections.sort(studentList, new Comparator<StudentDto>() {
//            @Override
//            public int compare(StudentDto o1, StudentDto o2) {
//                // TODO Auto-generated method stub
//                return o1.getStdNo().compareTo((int)o1.getStdNo());
//            }
//        });

		for (StudentDto studentDTO : studentList) {
			System.out.println(
					studentDTO.getStdNo() + ", " + studentDTO.getStdName() + ", " + studentDTO.getBan().getBanNo());
		}
		for (StudentDto studentDTO : studentList) {
			System.out.println(
					
					studentDTO.getJumsu().get(0).getJumsu()+ ", "+
					studentDTO.getJumsu().get(1).getJumsu()+ ", "+
					studentDTO.getJumsu().get(2).getJumsu()+ ", "+
					studentDTO.getJumsu().get(3).getJumsu()+ ", "+
					studentDTO.getJumsu().get(4).getJumsu());
		}
//        writeExcelFile(studentList);
	}
*/
	public void writeExcelFile(List<StudentDto> list) throws EncryptedDocumentException, IOException {
		String filePath = "student_transfer.xlsx"; // 저장할 파일 경로
		System.out.println(filePath);
		FileOutputStream fos = new FileOutputStream(filePath);

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("studentList"); // sheet 생성

		XSSFRow curRow;

		int row = list.size(); // list 크기
		for (int i = 0; i < row; i++) {
			curRow = sheet.createRow(i); // row 생성
			curRow.createCell(0).setCellValue(list.get(i).getStdNo()); // row에 각 cell 저장
			curRow.createCell(1).setCellValue(list.get(i).getStdName());
			curRow.createCell(2).setCellValue(list.get(i).getBan().getBanNo());
		}

		workbook.write(fos);
		fos.close();
	}

	public static List<StudentDto> getStudentList()
			throws EncryptedDocumentException, IOException, InvalidFormatException {
		List<StudentDto> studentList = new ArrayList<StudentDto>();

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
			List<ScoreDto> list = new ArrayList<ScoreDto>();
			StudentDto student = new StudentDto();
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
					student.setStdNo(((Double) getValueFromCell(cell)).intValue());
					break;
				case 1: // 성명
					student.setStdName((String) getValueFromCell(cell));
					break;
				case 2: // 반
					student.setBan(new BanDto(((Double) getValueFromCell(cell)).intValue()));
					break;
				case 3: // 반
					list.add(new ScoreDto(new SubjectDto(4),((Double) getValueFromCell(cell)).intValue()));
					break;
				case 4: // 반
					list.add(new ScoreDto(new SubjectDto(5),((Double) getValueFromCell(cell)).intValue()));
					break;
				case 5: // 반
					list.add(new ScoreDto(new SubjectDto(6),((Double) getValueFromCell(cell)).intValue()));
					break;
				case 6: // 반
					list.add(new ScoreDto(new SubjectDto(7),((Double) getValueFromCell(cell)).intValue()));
					break;
				case 7: // 반
					list.add(new ScoreDto(new SubjectDto(8),((Double) getValueFromCell(cell)).intValue()));
					break;
				}
				student.setJumsu(list);
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
//			if (DateUtil.isCellDateFormatted(cell)) {
//				return cell.getDateCellValue();
//			}
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
