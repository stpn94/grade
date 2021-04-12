package grade_ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import grade_dto.StudentDto;
import grade_service.StudentService;
import grade_ui_content.AbstractContentPanel;
import grade_ui_content.ScorePanel;
import grade_ui_content.ScoreStdPanel;
import grade_ui_content.SearchOnePanel;
import grade_ui_list.AbstractCustomTablePanel;
import grade_ui_list.SearchTablePanel;

public class SearchOneManagerUI extends AbstractManagerUI<StudentDto> implements ActionListener {
	private StudentService service;
	private ScoreStdPanel pItem;
	private ScorePanel scorePanel;
	private ScoreManagerUI frame;
	
	public SearchOneManagerUI() {
		btnAdd.setText("검색");
		stdListByBanItem.setText(AbstractManagerUI.VIEW_MENU);
		
		updateItem.setVisible(false);
		deleteItem.setVisible(false);
		
	}

	@Override
	protected void setService() {
		service = new StudentService();
	}

	@Override
	protected void tableLoadData() {
		((SearchTablePanel) pList).setService(service);
		pList.loadData();
	}

	@Override
	protected AbstractContentPanel<StudentDto> createContentPanel() {
		return new SearchOnePanel();
	}

	@Override
	protected AbstractCustomTablePanel<StudentDto> createTablePanel() {
		return new SearchTablePanel();
	}

	@Override
	protected void actionPerformedMenuGubun() {
		System.out.println(222);
		StudentDto std = pList.getItem();
		frame = new ScoreManagerUI();
		frame.setStdNo(std);
		frame.setVisible(true);
		frame.extracted();
	}

	@Override
	protected void actionPerformedMenuUpdate() {
		
	}

	@Override
	protected void actionPerformedMenuDelete() {
		
	}

	@Override
	protected void actionPerformedBtnUpdate(ActionEvent e) {
		StudentDto search = pContent.getItem();
		System.out.println(search);
		pList.initList3(search);
	}

	@Override
	protected void actionPerformedBtnAdd(ActionEvent e) {
		
	}

//	public void actionPerformed(ActionEvent e) {
//		if (e.getSource() == btnAdd) {
//			
//			actionPerformedThisBtnAdd(e);
//		}
//	}
	protected void actionPerformedThisBtnAdd(ActionEvent e) {
//		StudentDto search = pContent.getItem();
//		System.out.println(search);
//		pList.initList3(search);
	}
}
