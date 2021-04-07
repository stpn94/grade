package grade_ui_content;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import grade_dto.SubjectDto;

public class SubjContentPanel extends JPanel {
	private JTextField tfSubjNo;
	private JTextField tfSubjName;

	public SubjContentPanel() {

		initialize();
	}
	
	private void initialize() {
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\uBD80\uC11C\uC815\uBCF4", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblSubjNo = new JLabel("과목번호");
		lblSubjNo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblSubjNo);
		
		tfSubjNo = new JTextField();
		add(tfSubjNo);
		tfSubjNo.setColumns(10);
		
		JLabel lblSubjName = new JLabel("과목명");
		lblSubjName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblSubjName);
		
		tfSubjName = new JTextField();
		tfSubjName.setColumns(10);
		add(tfSubjName);
	}
	
	public SubjectDto getSubj() {
		int subjNo =Integer.parseInt(tfSubjNo.getText().trim());
		String subjName = tfSubjName.getText().trim();
		
		return new SubjectDto(subjNo, subjName);
	}
	
	public void setSubj(SubjectDto subj) {
//		tfDeptNo.setText(String.valueOf(department.getDeptNo()));
		tfSubjNo.setText(subj.getSubjNo()+"");
		tfSubjName.setText(subj.getSubjName()+"");
		
	}
	public void clearTf() {
		tfSubjNo.setText("");
		tfSubjName.setText("");
		
	}
	
}
