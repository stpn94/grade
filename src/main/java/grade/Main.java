package grade;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import grade_ui.ScoreManager;
import grade_ui.StudentManagerUI;
import grade_ui_list.StudentTablePanel;

public class Main extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnVerifyByAll;
	private StudentManagerUI studentFrame;
	private JButton btnStudent;
	private JButton btnScore;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		studentFrame = new StudentManagerUI();
		studentFrame.setTitle("학생 관리");
		
		initialize();
	}
	private void initialize() {
		setTitle("성적관리프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 578, 530);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pMain = new JPanel();
		contentPane.add(pMain, BorderLayout.NORTH);
		
		JPanel pBtns = new JPanel();
		contentPane.add(pBtns, BorderLayout.WEST);
		pBtns.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnStudent = new JButton("학생 관리");
		btnStudent.addActionListener(this);
		pBtns.add(btnStudent);
		
		btnScore = new JButton("성적 입력");
		btnScore.addActionListener(this);
		pBtns.add(btnScore);
		
		JButton btnSubject = new JButton("New button");
		pBtns.add(btnSubject);
		
		JButton btnBan = new JButton("New button");
		pBtns.add(btnBan);
		
		JButton btnVerifyByOne = new JButton("");
		pBtns.add(btnVerifyByOne);
		
		btnVerifyByAll = new JButton("전체학생조회");
		btnVerifyByAll.addActionListener(this);
		pBtns.add(btnVerifyByAll);
		
		StudentTablePanel pList = new StudentTablePanel();
		contentPane.add(pList, BorderLayout.CENTER);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnScore) {
			actionPerformedBtnScore(e);
		}
		if (e.getSource() == btnStudent) {
			actionPerformedBtnStudent(e);
		}
		if (e.getSource() == btnVerifyByAll) {
			actionPerformedBtnVerifyByAll(e);
		}
	}
	protected void actionPerformedBtnVerifyByAll(ActionEvent e) {
	}
	protected void actionPerformedBtnStudent(ActionEvent e) {
		studentFrame.setVisible(true);

	}
	protected void actionPerformedBtnScore(ActionEvent e) {
		ScoreManager frame = new ScoreManager();
		frame.setVisible(true);
	}
}
