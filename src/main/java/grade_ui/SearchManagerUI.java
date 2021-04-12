package grade_ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import grade_dto.StudentDto;
import grade_service.StudentService;
import grade_ui_content.AbstractContentPanel;
import grade_ui_content.ScorePanel;
import grade_ui_content.ScoreStdPanel;
import grade_ui_content.SearchPanel;
import grade_ui_list.AbstractCustomTablePanel;
import grade_ui_list.SearchTablePanel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

public class SearchManagerUI extends AbstractManagerUI<StudentDto> implements ActionListener {
	private StudentService service;
	private ScoreStdPanel pItem;
	private ScorePanel scorePanel;
	private ScoreManagerUI frame;
	
	public SearchManagerUI() {
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
		SearchPanel searchPanel = new SearchPanel();
		searchPanel.setService(service);
		return searchPanel;
	}

	@Override
	protected AbstractCustomTablePanel<StudentDto> createTablePanel() {
		return new SearchTablePanel();
	}

	@Override
	protected void actionPerformedMenuGubun() {
		System.out.println(222);
		StudentDto std = pList.getItem();
		System.out.println(std);
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
		pList.initList2(search);
		
	}

	@Override
	protected void actionPerformedBtnAdd(ActionEvent e) {
		
	}

}
