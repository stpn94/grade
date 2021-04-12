package grade_ui_list;

import javax.swing.SwingConstants;

import grade_dto.StudentDto;
import grade_service.StudentService;
import grade_ui.exception.NotSelectedException;

public class StudentTablePanel extends AbstractCustomTablePanel<StudentDto> {
	
	public StudentTablePanel() {
	}
	private StudentService service;
	
	
	@Override
	public String[] getColumnNames() {
		return new String[] { "학번", "이름", "분반", "국어", "영어", "수학", "사회", "과학", "평균" };
	}
	@Override
	protected void setAlignAndWidth() {
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4, 5, 6);
		setTableCellWidth(150, 150, 100 ,100, 100, 100, 100);
	}
	
	@Override
	public Object[] toArray(StudentDto t) {
		return new Object[] { 
				t.getStdNo(),
				t.getStdName(),
				String.format("%s", t.getBan().getBanCode()),
				t.getJumsu().get(0).getJumsu(),
				t.getJumsu().get(1).getJumsu(),
				t.getJumsu().get(2).getJumsu(),
				t.getJumsu().get(3).getJumsu(),
				t.getJumsu().get(4).getJumsu(),
				t.getAvg()};
	}
	
	@Override
	public StudentDto getItem() {
		int row = table.getSelectedRow();
		int stdNo = (int) table.getValueAt(row, 0);
		if (row == -1) {
			throw new NotSelectedException();
		}
		return list.get(list.indexOf(new StudentDto(stdNo)));
	}

	@Override
	public void initList() {
		list = service.showStudents();
	}
	
	public void setService(StudentService service) {
		this.service = service;
	}
	@Override
	public void initList2(StudentDto student) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void initList3(StudentDto student) {
		// TODO Auto-generated method stub
		
	}


}
