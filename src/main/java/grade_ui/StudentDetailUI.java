package grade_ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import grade_dto.StudentDetailDto;
import grade_dto.StudentDto;
import grade_service.StudentDetailService;
import grade_ui_content.StudentDetailPanel;

public class StudentDetailUI extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JPanel pBtns;
	private JButton btnAdd;
	private JButton btnCencel;
	private StudentDetailPanel pItem;
	private StudentDetailService service;

	public StudentDetailUI(boolean isBtns, StudentDetailService service) {
		this.service = service;
		initialize(isBtns);
	}

	private void initialize(boolean isBtns) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		pItem = new StudentDetailPanel();
		contentPane.add(pItem, BorderLayout.NORTH);

		pBtns = new JPanel();
		contentPane.add(pBtns, BorderLayout.SOUTH);

		btnAdd = new JButton();
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);

		btnCencel = new JButton();
		btnCencel.addActionListener(this);
		pBtns.add(btnCencel);

		if (isBtns) {
			btnAdd.setText("추가");
			btnCencel.setText("취소");
		} else {
			btnAdd.setText("삭제");
			btnCencel.setText("수정");
		}
	}

	public void setStdNo(StudentDto stdNo) {
		pItem.setTfEmpno(stdNo);

	}

	public void setDetailItems(StudentDetailDto stdDetail) {
		pItem.setItem(stdDetail);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().contentEquals("삭제")) {
			actionPerformedBtnDel(e);
		}
		if (e.getActionCommand().contentEquals("취소")) {
			actionPerformedBtnCencel(e);
		}
		if (e.getActionCommand().contentEquals("추가")) {
			actionPerformedBtnAdd(e);
		}
		if (e.getActionCommand().contentEquals("수정")) {
			actionPerformedBtnUpdate(e);
		}
	}

	private void actionPerformedBtnUpdate(ActionEvent e) {
		StudentDetailDto updateStdDetail = pItem.getItem();
		service.modifyStudentDetail(updateStdDetail);
		pItem.clearTf();
		JOptionPane.showMessageDialog(null, "수정 완료");
		dispose();
	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		StudentDetailDto newStdDetail = pItem.getItem();
		service.addStudentDetail(newStdDetail);
		JOptionPane.showMessageDialog(null, "추가 완료");
		pItem.clearTf();
		dispose();
	}
	protected void actionPerformedBtnCencel(ActionEvent e) {
		pItem.clearTf();
		if (btnAdd.getText().contentEquals("수정")) {
			btnAdd.setText("추가");
		}
	}
	
	private void actionPerformedBtnDel(ActionEvent e) {
		StudentDetailDto stdDetail = pItem.getItem();
		service.removeStudentDetail(new StudentDto(stdDetail.getStdNo()));
		JOptionPane.showMessageDialog(null, "삭제완료");
		pItem.clearTf();
	}


}
