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
import grade_ui.exception.InvalidCheckException;
import javax.swing.JSpinner;

public class SearchPanel extends AbstractContentPanel<StudentDto> {
	private JComboBox<SubjectDto> cmbOrders;
	private JTextField tfLimit;
	private JComboBox<BanDto> cmbBan;

	private StudentService service = new StudentService();

	public void setService(StudentService service) {
		this.service = service;

		BanDto ban = new BanDto("");
		List<BanDto> banList = service.showBanList();
		banList.add(ban);
		System.out.println(banList);
		DefaultComboBoxModel<BanDto> banModel = new DefaultComboBoxModel<>(new Vector<>(banList));
		cmbBan.setModel(banModel);
		cmbBan.setSelectedIndex(-1);

		SubjectDto subj = new SubjectDto("");
		List<SubjectDto> subjList = service.showSubjectList();
		subjList.add(subj);
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
		validCheck();
		BanDto ban = (BanDto) cmbBan.getSelectedItem();
		int limit = Integer.parseInt(tfLimit.getText());
		SubjectDto a = (SubjectDto) cmbOrders.getSelectedItem();
		return new StudentDto(ban, a.getSubjName(), limit);
	}

	public void validCheck() {
		if (tfLimit.getText().equals("")) {
			throw new InvalidCheckException();
		}
	}

	public void clearTf() {
		cmbBan.setSelectedIndex(-1);
		tfLimit.setText("");
		cmbOrders.setSelectedIndex(-1);

	}

}
