package grade_ui;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import grade_dto.StudentDto;
import grade_service.ScoreService;
import grade_service.StudentService;
import grade_ui.exception.InvalidCheckException;
import grade_ui_content.ScorePanel;
import grade_ui_content.ScoreStdPanel;

public class ScoreManager extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnVerify;

	private StudentService service;
	private ScoreService service1;
	private ScoreStdPanel pStudent;
	private JButton btnAdd;
	private ScorePanel scorePanel;
	private JButton btnClear;

	private void setService() {
		service = new StudentService();
		service1 = new ScoreService();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScoreManager frame = new ScoreManager();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ScoreManager() {
		setService();

		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 629, 222);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 10, 40));

		JPanel pStd = new JPanel();
		contentPane.add(pStd);
		pStd.setLayout(new GridLayout(2, 2, 10, 0));

		pStudent = new ScoreStdPanel();
		pStd.add(pStudent);
		pStudent.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel pBtns = new JPanel();
		pStd.add(pBtns);

		btnVerify = new JButton("조회");
		btnVerify.addActionListener(this);
		FlowLayout fl_pBtns = new FlowLayout(FlowLayout.CENTER, 5, 5);
		pBtns.setLayout(fl_pBtns);
		pBtns.add(btnVerify);

		JPanel pScore = new JPanel();
		contentPane.add(pScore);
		pScore.setLayout(new GridLayout(0, 1, 0, 0));

		scorePanel = new ScorePanel();
		pScore.add(scorePanel);

		JPanel pBtns2 = new JPanel();
		pScore.add(pBtns2);
		pBtns2.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

		btnAdd = new JButton("성적 입력");
		btnAdd.addActionListener(this);
		pBtns2.add(btnAdd);

		btnClear = new JButton("초기화");
		btnClear.addActionListener(this);
		pBtns2.add(btnClear);
	}

	public void actionPerformed(ActionEvent e) {
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
		StudentDto std = new StudentDto(Integer.parseInt(pStudent.getTfStdNo().getText().trim()));
		// 검색
		StudentDto stdList = service.showStudentsByNo(std);
		pStudent.setItem(stdList);
		scorePanel.setItem(stdList);

	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		StudentDto student = scorePanel.getItem();
		System.out.println(student);
		if (student != null) {
			service1.modifyScore(student);
		}
	}

	protected void actionPerformedBtnClear(ActionEvent e) {
		pStudent.clearTf();
		scorePanel.clearTf();
	}
}
