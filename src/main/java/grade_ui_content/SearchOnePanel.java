package grade_ui_content;

import grade_dto.StudentDto;
import grade_service.StudentService;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import grade_ui.exception.InvalidCheckException;

public class SearchOnePanel extends AbstractContentPanel<StudentDto> {
	private JTextField tfStdName;
	
	public SearchOnePanel() {
		initialize();
	}
	private void initialize() {
		setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblStdName = new JLabel("학생 이름 :");
		lblStdName.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblStdName);
		
		tfStdName = new JTextField();
		tfStdName.setColumns(10);
		panel.add(tfStdName);
	}

	@Override
	public void setItem(StudentDto item) {
		tfStdName.setText(item.getStdName());
		
	}

	@Override
	public StudentDto getItem() {
		validCheck();
		String stdName = tfStdName.getText().trim();
		return new StudentDto(stdName);
	}

	@Override
	public void validCheck() {
		if (tfStdName.getText().equals("")) {
			throw new InvalidCheckException();
		}	
	}

	@Override
	public void clearTf() {
		tfStdName.setText("");
		
	}
	public void setService(StudentService service) {
		// TODO Auto-generated method stub
		
	}

}
