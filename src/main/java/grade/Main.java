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
import grade_ui.StudentManagerUI;
import grade_ui.Sub_BanManagerUI;
import grade_ui_list.ScoreTablePanel;
import javax.swing.JTabbedPane;
import grade_ui_content.ScorePanel;

public class Main extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnVerifyByAll;
	private StudentManagerUI studentFrame;
	private JButton btnStudent;
	private JButton btnScore;
	private ScoreManagerUI scoreframe;
	private JButton btnSubject;
	private Sub_BanManagerUI subjbanframe;
	private JButton btnBan;

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

		btnSubject = new JButton("학반 추가");
		btnSubject.addActionListener(this);
		btnSubject.setIcon(new ImageIcon("C:\\workspace_java\\grade\\image\\학반추가.png"));
		pBtns.add(btnSubject);

		btnBan = new JButton("과목추가");
		btnBan.addActionListener(this);
		btnBan.setIcon(new ImageIcon("C:\\workspace_java\\grade\\image\\과목추가.png"));
		pBtns.add(btnBan);

		JButton btnVerifyByOne = new JButton("학생조회");
		btnVerifyByOne.setIcon(new ImageIcon("C:\\workspace_java\\grade\\image\\전체성적 조회.png"));
		pBtns.add(btnVerifyByOne);

		btnVerifyByAll = new JButton("전체 조회");
		btnVerifyByAll.setIcon(new ImageIcon("C:\\workspace_java\\grade\\image\\성적입력.png"));
		btnVerifyByAll.addActionListener(this);
		pBtns.add(btnVerifyByAll);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBan) {
			actionPerformedBtnBan(e);
		}
		if (e.getSource() == btnSubject) {
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
		subjbanframe.setVisible(true);
		
	}
	protected void actionPerformedBtnBan(ActionEvent e) {
		subjbanframe = new Sub_BanManagerUI();
		subjbanframe.setTitle("성적 및 반 관리");
		subjbanframe.setVisible(true);
		subjbanframe.tabbedPane.setSelectedComponent(subjbanframe.pBan);
	}
}
