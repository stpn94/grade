package grade_ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import grade.Main;

import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import grade_ui_content.SubjectPanel;
import grade_ui_content.BanPanel;

public class Sub_BanManagerUI extends JFrame {

	private JPanel contentPane;
	private Main main;
	public JTabbedPane tabbedPane;
	private SubjectPanel pSubj;
	public BanPanel pBan;

	public Sub_BanManagerUI() {
		initialize();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 458, 210);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);
		pSubj = new SubjectPanel();
		tabbedPane.addTab("과목관리", null, pSubj, null);
		tabbedPane.setDisplayedMnemonicIndexAt(0, 0);
		
		pBan = new BanPanel();
		tabbedPane.addTab("분반관리", null, pBan, null);
		tabbedPane.setDisplayedMnemonicIndexAt(1, 1);
		
		
		System.out.println(pBan);
	}

}
