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
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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

import grade_dto.StudentDto;
import grade_service.ScoreService;
import grade_service.StudentService;
import grade_ui.exception.InvalidCheckException;
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
	private JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));

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
		if (e.getSource() == btnLoadExcel) {
			if (e.getActionCommand().equals("불러오기")) {
				actionPerformedBtnLoadExcel(e);
			} else {
				actionPerformedBtnSave(e);
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
		FileNameExtensionFilter filter = new FileNameExtensionFilter("xlsx", "xlsx", "xls");
		chooser.setMultiSelectionEnabled(false);
		chooser.setFileFilter(filter);

		int res = chooser.showOpenDialog(null);
		if (res != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}

		String chooseFilePath = chooser.getSelectedFile().getPath();
		System.out.println(chooseFilePath + "<<chooseFilePath");
		if (btnLoadExcel.getText().equals("불러오기")) {
			btnLoadExcel.setText("저장하기");
		}
	}
	private void actionPerformedBtnSave(ActionEvent e) {


		if (btnLoadExcel.getText().equals("저장하기")) {
			btnLoadExcel.setText("불러오기");
		}

	}
}
