package grade_ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import grade_dto.StudentDto;
import grade_service.StudentService;
import grade_ui_content.StudentPanel;
import grade_ui_list.StudentTablePanel;

public class SearchManagerUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private StudentService service;
	private StudentDto students;
	private StudentPanel pContent;
	private JPanel pScore;
	private StudentTablePanel pList;
	private JPanel pBtns;
	private JButton btnSearch;
	private void setService() {
		service = new StudentService();
	}
	private void tableLoadData() {
		pList.setService(service);
		pList.loadData();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchManagerUI frame = new SearchManagerUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public SearchManagerUI() {
		setService();
		initialize();
		tableLoadData();
	}
	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 603, 637);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		pContent = new StudentPanel();
		contentPane.add(pContent, BorderLayout.WEST);
		
		pBtns = new JPanel();
		pContent.add(pBtns, BorderLayout.SOUTH);
		
		btnSearch = new JButton("조회");
		btnSearch.addActionListener(this);
		pBtns.add(btnSearch);
		
//		pContent.tfStdNo = new JTextField();
//		pContent.tfStdNo.addActionListener(this);
		
		pScore = new JPanel();
		contentPane.add(pScore, BorderLayout.CENTER);
		
		pList = new StudentTablePanel();
		contentPane.add(pList, BorderLayout.SOUTH);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSearch) {
			actionPerformedBtnSearch(e);
		}
	}

	protected void actionPerformedBtnSearch(ActionEvent e) {
		StudentDto getInfo=pContent.getItem();
		System.out.println(getInfo);
	}
	public JButton getBtnSearch() {
		return btnSearch;
	}
	
}
