package grade_ui_content;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import grade_dto.BanDto;

public class BanContentPanel extends JPanel {
	private JTextField tfBanNo;
	private JTextField tfBanName;

	public BanContentPanel() {

		initialize();
	}
	
	private void initialize() {
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\uBC18 \uC815\uBCF4", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblBanNo = new JLabel("반 번호");
		lblBanNo.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblBanNo);
		
		tfBanNo = new JTextField();
		add(tfBanNo);
		tfBanNo.setColumns(10);
		
		JLabel lblBanName = new JLabel("반 코드");
		lblBanName.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblBanName);
		
		tfBanName = new JTextField();
		tfBanName.setColumns(10);
		add(tfBanName);
	}
	
	public BanDto getBan() {
		int banNo =Integer.parseInt(tfBanNo.getText().trim());
		String banName = tfBanName.getText().trim();
		
		return new BanDto(banNo, banName);
	}
	
	public void setBan(BanDto ban) {
//		tfDeptNo.setText(String.valueOf(department.getDeptNo()));
		tfBanNo.setText(ban.getBanNo()+"");
		tfBanName.setText(ban.getBanCode()+"");
		
	}
	public void clearTf() {
		tfBanNo.setText("");
		tfBanName.setText("");
		
	}
	
}
