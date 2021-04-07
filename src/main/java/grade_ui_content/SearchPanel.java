package grade_ui_content;

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
import grade_dto.SubjectDto;
import grade_service.StudentService;

public class SearchPanel extends JPanel {
	private JComboBox<SubjectDto> cmbOrders;
	private JTextField tfLimit;
	private JComboBox<BanDto> cmbBan;

	private StudentService service = new StudentService();

	public void setService(StudentService service) {
		this.service = service;

		List<BanDto> banList = service.showBanList();
		System.out.println(banList);
		DefaultComboBoxModel<BanDto> banModel = new DefaultComboBoxModel<>(new Vector<>(banList));
		cmbBan.setModel(banModel);
		cmbBan.setSelectedIndex(-1);
		
		List<SubjectDto> subjList = service.showSubjectList();
		System.out.println(subjList);
		DefaultComboBoxModel<SubjectDto> subjModel = new DefaultComboBoxModel<>(new Vector<>(subjList));
		cmbOrders.setModel(subjModel);
		cmbOrders.setSelectedIndex(-1);
	}

	public SearchPanel() {
		initialize();


	}

	private void initialize() {
		setLayout(new GridLayout(0, 1, 0, 0));

		JPanel pItem = new JPanel();
		pItem.setBorder(new TitledBorder(null, "검색조건", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(pItem);
		pItem.setLayout(new GridLayout(0, 6, 5, 0));

		JLabel lblBan = new JLabel("분반");
		lblBan.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblBan);

		cmbBan = new JComboBox();
		pItem.add(cmbBan);

		JLabel lblLimit = new JLabel("검색 인원수");
		lblLimit.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblLimit);

		tfLimit = new JTextField();
		pItem.add(tfLimit);
		tfLimit.setColumns(10);

		JLabel lblOrders = new JLabel("과목명");
		lblOrders.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblOrders);

		cmbOrders = new JComboBox();
		pItem.add(cmbOrders);
	}

	public void setItem(StudentDto item) {
		cmbBan.setSelectedItem(item.getBan().getBanCode());
		tfLimit.setText(item.getOrder());
		cmbOrders.setSelectedItem(new SubjectDto().getSubjName());
	}

	public StudentDto getItem() {
		BanDto ban = (BanDto) cmbBan.getSelectedItem();
		int limit = Integer.parseInt(tfLimit.getText());
		String order = (String)cmbOrders.getSelectedItem();
		return new StudentDto(ban,order,limit);
	}

	public void validCheck() {
		// TODO Auto-generated method stub

	}

	public void clearTf() {
		cmbBan.setSelectedIndex(-1);
		tfLimit.setText("");
		cmbOrders.setSelectedIndex(-1);

	}

}
