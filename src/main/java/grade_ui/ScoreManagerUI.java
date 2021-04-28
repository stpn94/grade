package grade_ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

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
import grade_service.ScoreService;
import grade_service.StudentService;
import grade_ui.exception.InvalidCheckException;
import grade_ui.exception.SqlConstraintException;
import grade_ui_content.ScorePanel;
import grade_ui_content.ScoreStdPanel;
import grade_ui_list.ScoreTablePanel;

public class ScoreManagerUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	public JButton btnVerify;

	private StudentService service;
	private ScoreService service1;
	private ScoreStdPanel pStudent;
	private JButton btnAdd;
	private ScorePanel scorePanel;
	private JButton btnClear;
	private ScoreTablePanel pList;
	private JPanel pContent;
	private JPanel pRight;
	private JButton btnLoadExcel;
	private JTextArea textArea;
	private JLabel lblNewLabel;
	private JFileChooser chooser;
	private static String chooseFilePath;
	private JButton btnExport;

	private void setService() {
		service = new StudentService();
		service1 = new ScoreService();
	}

	public ScoreManagerUI() {
		setService();

		initialize();

		tableLoadData();
	}

	protected void tableLoadData() {
		pList.setService(service);
		pList.loadData();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 854, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 10, 10));

		pContent = new JPanel();
		contentPane.add(pContent);
		pContent.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel pLeft = new JPanel();
		pLeft.setBorder(new LineBorder(new Color(0, 0, 0)));
		pContent.add(pLeft);
		pLeft.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel pStd = new JPanel();
		pLeft.add(pStd);
		pStd.setLayout(new GridLayout(2, 2, 10, 0));

		pStudent = new ScoreStdPanel();
		pStd.add(pStudent);
		pStudent.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel pBtns = new JPanel();
		pStd.add(pBtns);

		btnVerify = new JButton("조회");
		btnVerify.addActionListener(this);
		pBtns.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 3));
		pBtns.add(btnVerify);

		JPanel pScore = new JPanel();
		pLeft.add(pScore);
		pScore.setLayout(new GridLayout(0, 1, 0, 0));

		scorePanel = new ScorePanel();
		scorePanel.setPreferredSize(new Dimension(100, 100));

		pScore.add(scorePanel);

		JPanel pBtns2 = new JPanel();
		pScore.add(pBtns2);
		pBtns2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));

		btnAdd = new JButton("성적 입력");
		btnAdd.addActionListener(this);
		pBtns2.add(btnAdd);

		btnClear = new JButton("초기화");
		btnClear.addActionListener(this);
		pBtns2.add(btnClear);

		pRight = new JPanel();
		pRight.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		pContent.add(pRight);
		pRight.setLayout(new BorderLayout(0, 0));

		JPanel pExcelBtn = new JPanel();
		pRight.add(pExcelBtn, BorderLayout.SOUTH);

		btnLoadExcel = new JButton("불러오기");
		btnLoadExcel.addActionListener(this);
		btnLoadExcel.setHorizontalAlignment(SwingConstants.LEFT);
		pExcelBtn.add(btnLoadExcel);
		
		btnExport = new JButton("내보내기");
		btnExport.addActionListener(this);
		pExcelBtn.add(btnExport);

		textArea = new JTextArea();
		textArea.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		textArea.setLineWrap(true);
		textArea.setBackground(SystemColor.control);
		textArea.setFont(new Font("Monospaced", Font.ITALIC, 16));
		textArea.setEditable(false);
		textArea.setText("주의!!\r\n엑셀 파일 입력시\r\n(학번,이름,학반,점수)순으로\r\n입력하였는지 확인해주세요.\r\n\r\n불러오기를 눌러\r\n엑셀파일을 가져와 주세요.");
		pRight.add(textArea, BorderLayout.CENTER);

		lblNewLabel = new JLabel("엑셀파일로 성적 입력하기");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		pRight.add(lblNewLabel, BorderLayout.NORTH);

		pList = new ScoreTablePanel();
		contentPane.add(pList);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnExport) {
			try {
				actionPerformedBtnExport(e);
			} catch (EncryptedDocumentException | InvalidFormatException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == btnLoadExcel) {
			if (e.getActionCommand().equals("불러오기")) {
				actionPerformedBtnLoadExcel(e);
			} else {
				try {
					actionPerformedBtnSave(e);
				} catch (EncryptedDocumentException | InvalidFormatException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if (e.getSource() == btnClear) {
			actionPerformedBtnClear(e);
		}
		try {
			if (e.getSource() == btnAdd) {
				actionPerformedBtnAdd(e);
			}
			if (e.getSource() == btnVerify) {
				actionPerformedBtnVerify(e);
			}
		} catch (InvalidCheckException e2) {
			JOptionPane.showMessageDialog(null, "공란이 존재합니다.");
		} catch (NumberFormatException e3) {
			JOptionPane.showMessageDialog(null, "형식을 확인해 주세요.");
		} catch (NullPointerException e3) {
			JOptionPane.showMessageDialog(null, "존재하지 않는 학생입니다.");
		}

	}

	protected void actionPerformedBtnVerify(ActionEvent e) {
//		StudentDto std = new StudentDto(Integer.parseInt(tfStdNo.getText()));
		// 내가 입력 한 번호
		extracted();

	}

	public void extracted() {
		StudentDto std = new StudentDto(Integer.parseInt(pStudent.getTfStdNo().getText().trim()));
		// 검색
		StudentDto stdList = service.showStudentsByNo(std);
		pStudent.setItem(stdList);
		scorePanel.setItem(stdList);
	}

	public void setStdNo(StudentDto stdNo) {
		pStudent.setTfStdno(stdNo);

	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		StudentDto student = scorePanel.getItem();
		System.out.println(student);
		if (student != null) {
			System.out.println(student);
			service1.modifyScore(student);
		}
		pList.loadData();
	}

	protected void actionPerformedBtnClear(ActionEvent e) {
		pStudent.clearTf();
		scorePanel.clearTf();
	}

	protected void actionPerformedBtnLoadExcel(ActionEvent e) {
		chooser = new JFileChooser(System.getProperty("user.dir"));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("xlsx", "xlsx", "xls");
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileFilter(filter);
		

		int res = chooser.showOpenDialog(null);
		if (res != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}

		chooseFilePath = chooser.getSelectedFile().getPath();
		System.out.println(chooseFilePath + "<<chooseFilePath");
		if (btnLoadExcel.getText().equals("불러오기")) {
			btnLoadExcel.setText("저장하기");
		}
	}

	private void actionPerformedBtnSave(ActionEvent e) throws EncryptedDocumentException, InvalidFormatException, IOException {
		List<StudentDto> studentList = getStudentList();
		
		for (StudentDto studentDTO : studentList) {
//			System.out.println(studentDTO.getStdNo() + ", " + studentDTO.getStdName() + ", " + studentDTO.getBan().getBanNo());
//			System.out.println(studentDTO);
			int stdNo = studentDTO.getStdNo();
			String stdName = studentDTO.getStdName();
			BanDto ban = new BanDto(studentDTO.getBan().getBanNo());
			StudentDto newStd=new StudentDto(stdNo, stdName, ban);
			try {
				service.addStudent(newStd);
				System.out.println("입력함");
			} catch (SqlConstraintException e2) {
				service.modifyStudent(newStd);
				System.out.println("업데이트함");
			}
			
		}
		
		for (StudentDto studentDTO : studentList) {
//			System.out.println(
//					studentDTO.getJumsu().get(0).getJumsu()+ ", "+
//					studentDTO.getJumsu().get(1).getJumsu()+ ", "+
//					studentDTO.getJumsu().get(2).getJumsu()+ ", "+
//					studentDTO.getJumsu().get(3).getJumsu()+ ", "+
//					studentDTO.getJumsu().get(4).getJumsu());
			List<ScoreDto> list = new ArrayList<ScoreDto>();
			StudentDto student =new StudentDto(studentDTO.getStdNo(), list);
			list.add(new ScoreDto(new SubjectDto(4),studentDTO.getJumsu().get(0).getJumsu()));
			list.add(new ScoreDto(new SubjectDto(5),studentDTO.getJumsu().get(1).getJumsu()));
			list.add(new ScoreDto(new SubjectDto(6),studentDTO.getJumsu().get(2).getJumsu()));
			list.add(new ScoreDto(new SubjectDto(7),studentDTO.getJumsu().get(3).getJumsu()));
			list.add(new ScoreDto(new SubjectDto(8),studentDTO.getJumsu().get(4).getJumsu()));
			
			service1.modifyScore(student);
		}
		pList.loadData();
		if (btnLoadExcel.getText().equals("저장하기")) {
			btnLoadExcel.setText("불러오기");
		}

	}

	public void writeExcelFile(List<StudentDto> list) throws EncryptedDocumentException, IOException {
		String filePath = chooseFilePath+".xlsx"; // 저장할 파일 경로
		System.out.println(filePath);
		FileOutputStream fos = new FileOutputStream(filePath);

		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("studentList"); // sheet 생성

		XSSFRow curRow = null;
		String A = "학번";
		String B = "학생 명";
		String C = "학반";
		String D = "국어";
		String E = "영어";
		String F = "수학";
		String G = "사회";
		String H = "과학";
		String I = "평균";
		curRow = sheet.createRow(0);
		curRow.createCell(0).setCellValue("학번");
		curRow.createCell(1).setCellValue(B);
		curRow.createCell(2).setCellValue(C);
		curRow.createCell(3).setCellValue(D);
		curRow.createCell(4).setCellValue(E);
		curRow.createCell(5).setCellValue(F);
		curRow.createCell(6).setCellValue(G);
		curRow.createCell(7).setCellValue(H);
		curRow.createCell(8).setCellValue(I);
		int row = list.size(); // list 크기
		for (int i = 0; i < row; i++) {
			curRow = sheet.createRow(i+1); // row 생성
			curRow.createCell(0).setCellValue(list.get(i).getStdNo()); // row에 각 cell 저장
			curRow.createCell(1).setCellValue(list.get(i).getStdName());
			curRow.createCell(2).setCellValue(list.get(i).getBan().getBanNo());
			curRow.createCell(3).setCellValue(list.get(i).getJumsu().get(0).getJumsu());
			curRow.createCell(4).setCellValue(list.get(i).getJumsu().get(1).getJumsu());
			curRow.createCell(5).setCellValue(list.get(i).getJumsu().get(2).getJumsu());
			curRow.createCell(6).setCellValue(list.get(i).getJumsu().get(3).getJumsu());
			curRow.createCell(7).setCellValue(list.get(i).getJumsu().get(4).getJumsu());
			curRow.createCell(8).setCellValue(list.get(i).getAvg());
		}

		workbook.write(fos);
		fos.close();
	}

	public static List<StudentDto> getStudentList()
			throws EncryptedDocumentException, IOException, InvalidFormatException {
		List<StudentDto> studentList = new ArrayList<StudentDto>();

		String filePath = chooseFilePath;
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
					list.add(new ScoreDto(new SubjectDto(4), ((Double) getValueFromCell(cell)).intValue()));
					break;
				case 4: // 반
					list.add(new ScoreDto(new SubjectDto(5), ((Double) getValueFromCell(cell)).intValue()));
					break;
				case 5: // 반
					list.add(new ScoreDto(new SubjectDto(6), ((Double) getValueFromCell(cell)).intValue()));
					break;
				case 6: // 반
					list.add(new ScoreDto(new SubjectDto(7), ((Double) getValueFromCell(cell)).intValue()));
					break;
				case 7: // 반
					list.add(new ScoreDto(new SubjectDto(8), ((Double) getValueFromCell(cell)).intValue()));
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
	protected void actionPerformedBtnExport(ActionEvent e) throws EncryptedDocumentException, InvalidFormatException, IOException {
		chooser=new JFileChooser();//FileChooser 선언
		FileNameExtensionFilter filter = new FileNameExtensionFilter("xlsx", "xlsx", "xls");
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileFilter(filter);
		
		chooser.setDialogTitle("Choose path");//FileChooser 창 제목
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); //경로만
		int option=chooser.showSaveDialog(null); //FileChooser 창안의 버튼 인텍스 반환
		
		if(option == JFileChooser.APPROVE_OPTION) { //승인버트
			File f=chooser.getSelectedFile();
			String directory=f.getAbsolutePath();//File f의 절대경로
			System.out.println(directory);
			chooseFilePath=directory;
		}else {
			System.out.println("저장 취소");
		}
		
		
		
		List<StudentDto> studentList = service.showStudents();
		writeExcelFile(studentList);
	}
}
