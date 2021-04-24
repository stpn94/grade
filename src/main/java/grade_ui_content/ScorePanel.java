package grade_ui_content;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import grade_dto.StudentDto;
import grade_ui.exception.InvalidCheckException;
import java.awt.Color;

public class ScorePanel extends JPanel {
	private JTextField tfKor;
	private JTextField tfEng;
	private JTextField tfMath;
	private JTextField tfSoc;
	private JTextField tfSci;
	private StudentDto student;
	
	public ScorePanel() {
		initialize();
	}
	
	private void initialize() {
		setBackground(Color.GREEN);
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel pScore = new JPanel();
		add(pScore);
		pScore.setLayout(new GridLayout(0, 6, 10, 0));
		

		JLabel lblKor = new JLabel("국어");
		lblKor.setHorizontalAlignment(SwingConstants.TRAILING);
		pScore.add(lblKor);
		
		tfKor = new JTextField();
		pScore.add(tfKor);
		tfKor.setColumns(10);
		
		JLabel lblEng = new JLabel("영어");
		lblEng.setHorizontalAlignment(SwingConstants.TRAILING);
		pScore.add(lblEng);
		
		tfEng = new JTextField();
		pScore.add(tfEng);
		tfEng.setColumns(10);
		
		JLabel lblMath = new JLabel("수학");
		lblMath.setHorizontalAlignment(SwingConstants.TRAILING);
		pScore.add(lblMath);
		
		tfMath = new JTextField();
		pScore.add(tfMath);
		tfMath.setColumns(10);
		
		JLabel lblSoc = new JLabel("사회");
		lblSoc.setHorizontalAlignment(SwingConstants.TRAILING);
		pScore.add(lblSoc);
		
		tfSoc = new JTextField();
		pScore.add(tfSoc);
		tfSoc.setColumns(10);
		
		JLabel lblSci = new JLabel("과학");
		lblSci.setHorizontalAlignment(SwingConstants.TRAILING);
		pScore.add(lblSci);
		
		tfSci = new JTextField();
		pScore.add(tfSci);
		tfSci.setColumns(10);
	}
	
	

	public void setItem(StudentDto std) {
		this.student = std;
		
		tfKor.setText(std.getJumsu().get(0).getJumsu()+"");
		tfEng.setText(std.getJumsu().get(1).getJumsu()+"");
		tfMath.setText(std.getJumsu().get(2).getJumsu()+"");
		tfSoc.setText(std.getJumsu().get(3).getJumsu()+"");
		tfSci.setText(std.getJumsu().get(4).getJumsu()+"");
		
	}

	public StudentDto getItem() {			
		if(tfKor.getText().equals("")||tfEng.getText().equals("")||tfMath.getText().equals("")||
				tfSoc.getText().equals("")||tfSci.getText().equals("")) {
			throw new InvalidCheckException();
		}
		try {
			student.getJumsu().get(0).setJumsu(Integer.parseInt(tfKor.getText().trim()));
			student.getJumsu().get(1).setJumsu(Integer.parseInt(tfEng.getText().trim()));
			student.getJumsu().get(2).setJumsu(Integer.parseInt(tfMath.getText().trim()));
			student.getJumsu().get(3).setJumsu(Integer.parseInt(tfSoc.getText().trim()));
			student.getJumsu().get(4).setJumsu(Integer.parseInt(tfSci.getText().trim()));
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "성적을 확인해 주세요");
			return null;
		}
		return student;
	}

	public void clearTf() {
		tfKor.setText("");
		tfEng.setText("");
		tfMath.setText("");
		tfSoc.setText("");
		tfSci.setText("");
	}
	
	public void validCheck() {
		
	}

}
