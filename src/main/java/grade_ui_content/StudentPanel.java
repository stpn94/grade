package grade_ui_content;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import grade_dto.BanDto;
import grade_dto.StudentDto;
import grade_service.StudentService;
import grade_ui.SearchManagerUI;
import grade_ui.exception.InvalidCheckException;

public class StudentPanel extends AbstractContentPanel<StudentDto> {
	public JTextField tfStdNo;
	private JTextField tfStdName;
	private JComboBox<BanDto> cmbBan;
	
	private StudentService service;
	
	public JTextField getTfStdNo() {
		return tfStdNo;
	}

	public void setService(StudentService service) {
		this.service =service;
		
		List<BanDto> banList = service.showBanList();
		System.out.println(banList);
		DefaultComboBoxModel<BanDto> banModel = new DefaultComboBoxModel<>(new Vector<>(banList));
		cmbBan.setModel(banModel);
		
		cmbBan.setSelectedIndex(-1);
	}
	
	public StudentPanel() {
		initialize();
	}
	
	public void initialize() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel pItem = new JPanel();
		pItem.setBorder(new TitledBorder(null, "학생정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(pItem);
		pItem.setLayout(new GridLayout(0, 2, 10, 30));
		
		JLabel lblStdNo = new JLabel("학번");
		lblStdNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblStdNo);
		
		tfStdNo = new JTextField();
		pItem.add(tfStdNo);
		tfStdNo.setColumns(10);
		
		JLabel lblStdName = new JLabel("이름");
		lblStdName.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblStdName);
		
		tfStdName = new JTextField();
		pItem.add(tfStdName);
		tfStdName.setColumns(10);
		
		JLabel lblBan = new JLabel("반");
		lblBan.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblBan);
		
		cmbBan = new JComboBox();
//		cmbBan.addItemListener(this);
		pItem.add(cmbBan);
	}

	@Override
	public void setItem(StudentDto item) {
		tfStdNo.setText(item.getStdNo() + "");
		tfStdName.setText(item.getStdName());
		cmbBan.setSelectedItem(item.getBan().getBanCode());
	}

	@Override
	public StudentDto getItem() {
		validCheck();
		int stdNo = Integer.parseInt(tfStdNo.getText().trim());
		String stdName = tfStdName.getText().trim();
		BanDto ban = (BanDto) cmbBan.getSelectedItem();
		return new StudentDto(stdNo, stdName, ban);
	}

	@Override
	public void validCheck() {
		if (tfStdNo.getText().contentEquals("") || tfStdName.getText().equals("") 
				|| cmbBan.getSelectedIndex() == -1) {
			throw new InvalidCheckException();
		}	
	}

	@Override
	public void clearTf() {
		tfStdNo.setText("");
		tfStdName.setText("");
		cmbBan.setSelectedIndex(-1);
	}


//	public void itemStateChanged(ItemEvent e) {
//		if (e.getSource() == cmbBan) {
//			itemStateChangedCmbBan(e);
//		}
//	}
//	protected void itemStateChangedCmbBan(ItemEvent e) {
//		if(e.getStateChange()==ItemEvent.SELECTED) {
//			BanDto ban = (BanDto) cmbBan.getSelectedItem();
//			List<StudentDto> stdList = service.showStudentsByBan(ban);
//		}
//	}
}
