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
import javax.swing.SpinnerNumberModel;

public class SearchPanel extends AbstractContentPanel<StudentDto> {
	private JComboBox<SubjectDto> cmbOrders;
	private JComboBox<BanDto> cmbBan;

	private StudentService service = new StudentService();
	private JSpinner tfLimit;

	public void setService(StudentService service) {
		this.service = service;

		BanDto ban = new BanDto("전체");
		List<BanDto> banList = service.showBanList();
	
		banList.add(ban);
//		System.out.println(banList);
		DefaultComboBoxModel<BanDto> banModel = new DefaultComboBoxModel<>(new Vector<>(banList));
		cmbBan.setModel(banModel);
		cmbBan.setSelectedIndex(2);

		SubjectDto subj = new SubjectDto("평균");
		List<SubjectDto> subjList = service.showSubjectList();
		subjList.add(subj);
//		System.out.println(subjList);
		DefaultComboBoxModel<SubjectDto> subjModel = new DefaultComboBoxModel<>(new Vector<>(subjList));
		cmbOrders.setModel(subjModel);
		cmbOrders.setSelectedIndex(5);
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
		
		List<StudentDto> list = service.showStudents();
		int stds = list.size();
			
		tfLimit = new JSpinner();
		tfLimit.setModel(new SpinnerNumberModel(stds, 1, stds, 1));
		pItem.add(tfLimit);

		JLabel lblOrders = new JLabel("과목명");
		lblOrders.setHorizontalAlignment(SwingConstants.RIGHT);
		pItem.add(lblOrders);

		cmbOrders = new JComboBox();
		pItem.add(cmbOrders);
	}
	

	@Override
	public void setItem(StudentDto item) {
		cmbBan.setSelectedItem(item.getBan().getBanCode());
		tfLimit.setToolTipText(item.getOrder());
		cmbOrders.setSelectedItem(new SubjectDto().getSubjName());
	}
	@Override 
	public StudentDto getItem() {
		validCheck();
		BanDto ban = (BanDto)cmbBan.getSelectedItem();
		System.out.println(ban);
		int limit = (int) tfLimit.getValue();
		System.out.println(limit);
		SubjectDto a = (SubjectDto)cmbOrders.getSelectedItem();
		System.out.println(a);
		return new StudentDto(ban, a.getSubjName(), limit);
	}
	@Override
	public void validCheck() {
//		if (tfLimit.getToolTipText().equals("")) {
//			throw new InvalidCheckException();
//		}
	}
	@Override
	public void clearTf() {
		cmbBan.setSelectedIndex(-1);
		tfLimit.setToolTipText("1");
		cmbOrders.setSelectedIndex(-1);

	}

}
