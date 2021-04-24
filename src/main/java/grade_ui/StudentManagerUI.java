package grade_ui;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import grade_dto.StudentDetailDto;
import grade_dto.StudentDto;
import grade_service.StudentDetailService;
import grade_service.StudentService;
import grade_ui_content.AbstractContentPanel;
import grade_ui_content.StudentPanel;
import grade_ui_list.AbstractCustomTablePanel;
import grade_ui_list.StudentTablePanel;

public class StudentManagerUI extends AbstractManagerUI<StudentDto> {
	private StudentService service;
	private StudentDetailService detailService;

	public StudentManagerUI() {
		stdListByBanItem.setText(AbstractManagerUI.STD_MENU);
	}

	@Override
	protected void setService() {
		service = new StudentService();
		detailService = new StudentDetailService();
	}

	@Override
	protected void tableLoadData() {
		((StudentTablePanel) pList).setService(service);
		pList.loadData();
	}

	@Override
	protected AbstractContentPanel<StudentDto> createContentPanel() {
		StudentPanel stdPanel = new StudentPanel();
		stdPanel.setService(service);
		return stdPanel;
	}

	@Override
	protected AbstractCustomTablePanel<StudentDto> createTablePanel() {
		return new StudentTablePanel();
	}

	@Override
	protected void actionPerformedMenuGubun() {
		StudentDto std = pList.getItem();

		StudentDetailDto stdDetail = detailService.selectStudentDetailByStdNo(std);

		StudentDetailUI frame;

		if (stdDetail == null) {
			frame = new StudentDetailUI(true, detailService);
		} else {
			frame = new StudentDetailUI(false, detailService);
			frame.setDetailItems(stdDetail);
		}
		frame.setStdNo(std);
		frame.setVisible(true);
	}

	@Override
	protected void actionPerformedMenuUpdate() {
		StudentDto updateStd = pList.getItem();
		pContent.setItem(updateStd);
		btnAdd.setText("수정");
	}

	@Override
	protected void actionPerformedMenuDelete() {
		StudentDto delStd = pList.getItem();
		service.removeStudent(delStd);
		pList.loadData();
		JOptionPane.showMessageDialog(null, delStd + "삭제 되었습니다.");

	}

	@Override
	protected void actionPerformedBtnUpdate(ActionEvent e) {
		StudentDto updateStd = pContent.getItem();
		service.modifyStudent(updateStd);
		pList.loadData();
		pContent.clearTf();
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, updateStd.getStdName() + "정보가 수정되었습니다.");
	}

	@Override
	protected void actionPerformedBtnAdd(ActionEvent e) {
		StudentDto stdAdd = pContent.getItem();
		System.out.println(stdAdd);
		service.addStudent(stdAdd);
		pList.loadData();
		pContent.clearTf();
		JOptionPane.showMessageDialog(null, stdAdd.getStdName() + " 추가했습니다.");
	}

	@Override
	protected void actionPerformedBtnClear(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
