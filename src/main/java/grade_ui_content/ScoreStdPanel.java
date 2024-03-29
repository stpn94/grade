package grade_ui_content;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import grade_dto.BanDto;
import grade_dto.StudentDto;

public class ScoreStdPanel extends JPanel{
	private JTextField tfStdNo;
	private JTextField tfStdName;
	private JTextField tfBan;
	private JPanel pStudent;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel;
	
	public JTextField getTfStdNo() {
		return tfStdNo;
	}

	public ScoreStdPanel() {
		initialize();
	}
	
	private void initialize() {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		pStudent = new JPanel();
		add(pStudent);
		pStudent.setLayout(new GridLayout(0, 6, 0, 0));
		
		panel_1 = new JPanel();
		pStudent.add(panel_1);
		
		panel_2 = new JPanel();
		pStudent.add(panel_2);
		
		panel_3 = new JPanel();
		pStudent.add(panel_3);
		
		panel_4 = new JPanel();
		pStudent.add(panel_4);
		
		panel_5 = new JPanel();
		pStudent.add(panel_5);
		
		panel = new JPanel();
		pStudent.add(panel);
		
		JLabel lblStdNo = new JLabel("학 번");
		lblStdNo.setHorizontalAlignment(SwingConstants.TRAILING);
		pStudent.add(lblStdNo);
		
		tfStdNo = new JTextField();
		tfStdNo.setColumns(10);
		pStudent.add(tfStdNo);
		
		JLabel lblStdName = new JLabel("학생 명");
		lblStdName.setHorizontalAlignment(SwingConstants.TRAILING);
		pStudent.add(lblStdName);
		
		tfStdName = new JTextField();
		tfStdName.setEditable(false);
		tfStdName.setColumns(10);
		pStudent.add(tfStdName);
		
		JLabel lblBan = new JLabel("분반");
		lblBan.setHorizontalAlignment(SwingConstants.TRAILING);
		pStudent.add(lblBan);
		
		tfBan = new JTextField();
		tfBan.setEditable(false);
		pStudent.add(tfBan);
		tfBan.setColumns(10);
		
	}
	
	public void setTfStdno(StudentDto stdNo) {
		tfStdNo.setText(String.valueOf(stdNo.getStdNo()));
	}

	public void setItem(StudentDto std) {
		tfStdNo.setText(String.valueOf(std.getStdNo()));
		tfStdName.setText(std.getStdName());
		tfBan.setText(std.getBan().getBanCode());
	}

	public StudentDto getItem() {
		int stdNo = Integer.parseInt(tfStdNo.getText().trim());
		String stdName = tfStdName.getText().trim();
		BanDto banCode = null;
		banCode = new BanDto(tfStdName.getText());
		return new StudentDto(stdNo,stdName,banCode);
	}
	

	public void clearTf() {
		tfStdNo.setText("");
		tfStdName.setText("");
		tfBan.setText("");
	}
	
	public void validCheck() {
		// TODO Auto-generated method stub
		
	}
	
}
