package grade;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import grade_ui.ScoreManagerUI;
import grade_ui.SearchManagerUI;
import grade_ui.SearchOneManagerUI;
import grade_ui.StudentManagerUI;
import grade_ui.Sub_BanManagerUI;

public class Main extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnVerifyByAll;
	private StudentManagerUI studentFrame;
	private JButton btnStudent;
	private JButton btnScore;
	private ScoreManagerUI scoreframe;
	private JButton btnBan;
	private Sub_BanManagerUI subjbanframe;
	private SearchManagerUI searchframe;
	private SearchOneManagerUI searchOneframe;
	private JButton btnSubject;
	private JButton btnVerifyByOne;

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

		

		initialize();

	}

	private void initialize() {
		setTitle("성적관리프로그램");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 530);
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
		btnStudent.setSelectedIcon(null);
		btnStudent.setIcon(new ImageIcon("C:\\workspace_java\\grade\\image\\학생관리.png"));
		btnStudent.addActionListener(this);
		pBtns.add(btnStudent);

		btnScore = new JButton("성적 관리");
		btnScore.setIcon(new ImageIcon("C:\\workspace_java\\grade\\image\\성적추가.png"));
		btnScore.addActionListener(this);
		pBtns.add(btnScore);

		btnBan = new JButton("과목 관리");
		btnBan.addActionListener(this);
		btnBan.setIcon(new ImageIcon("C:\\workspace_java\\grade\\image\\학반추가.png"));
		pBtns.add(btnBan);

		btnSubject = new JButton("학반관리");
		btnSubject.addActionListener(this);
		btnSubject.setIcon(new ImageIcon("C:\\workspace_java\\grade\\image\\과목추가.png"));
		pBtns.add(btnSubject);

		btnVerifyByOne = new JButton("학생조회");
		btnVerifyByOne.addActionListener(this);
		btnVerifyByOne.setIcon(new ImageIcon("C:\\workspace_java\\grade\\image\\전체성적 조회.png"));
		pBtns.add(btnVerifyByOne);

		btnVerifyByAll = new JButton("전체 조회");
		btnVerifyByAll.setIcon(new ImageIcon("C:\\workspace_java\\grade\\image\\성적입력.png"));
		btnVerifyByAll.addActionListener(this);
		pBtns.add(btnVerifyByAll);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnVerifyByOne) {
			actionPerformedBtnVerifyByOne(e);
		}
		if (e.getSource() == btnSubject) {
			actionPerformedBtnBan(e);
		}
		if (e.getSource() == btnBan) {
			actionPerformedBtnSubject(e);
		}
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
		searchframe = new SearchManagerUI();
		searchframe.setTitle("전체 조회");
		searchframe.setBounds(312, 100, 748, 524);
		
		searchframe.setVisible(true);
	}

	protected void actionPerformedBtnStudent(ActionEvent e) {
		studentFrame = new StudentManagerUI();
		studentFrame.setTitle("학생 관리");
		studentFrame.setBounds(312, 100, 748, 524);
		
		studentFrame.setVisible(true);

	}

	protected void actionPerformedBtnScore(ActionEvent e) {
		scoreframe = new ScoreManagerUI();
		scoreframe.setTitle("성적 관리");
		scoreframe.setBounds(312, 100, 748, 524);
		scoreframe.setVisible(true);
	}
	protected void actionPerformedBtnSubject(ActionEvent e) {
		subjbanframe = new Sub_BanManagerUI();
		subjbanframe.setTitle("성적 및 반 관리");
		subjbanframe.setBounds(320, 100, 748, 280);
		subjbanframe.setVisible(true);
		
	}
	protected void actionPerformedBtnBan(ActionEvent e) {
		subjbanframe = new Sub_BanManagerUI();
		subjbanframe.setTitle("반 및 성적 관리");
		subjbanframe.setBounds(320, 350, 748, 280);
		subjbanframe.tabbedPane.setSelectedComponent(subjbanframe.pBan);
		subjbanframe.setVisible(true);
	}
	protected void actionPerformedBtnVerifyByOne(ActionEvent e) {
		searchOneframe = new SearchOneManagerUI();
		searchOneframe.setTitle("학생 조회");
		searchOneframe.setBounds(312, 100, 748, 524);
		
		searchOneframe.setVisible(true);
	}
}
