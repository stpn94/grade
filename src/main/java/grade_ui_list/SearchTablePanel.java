package grade_ui_list;

import javax.swing.SwingConstants;

import grade_dto.StudentDto;
import grade_service.StudentService;
import grade_ui.exception.NotSelectedException;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SearchTablePanel extends AbstractCustomTablePanel<StudentDto> {
	private StudentService service;
	private int koravg;
	private int engavg;
	private int mathavg;
	private int socavg;
	private int sciavg;
	private JTextField tfKor;
	private JTextField tfEng;
	private JTextField tfMath;
	private JTextField tfSoc;
	private JTextField tfSic;
	public int stds;

	public SearchTablePanel() {
		initialize();
	}

	private void initialize() {

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 7, 0, 0));

		JLabel label = new JLabel("");
		panel_1.add(label);

		JLabel label_1 = new JLabel("과목별 평균");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(label_1);

		tfKor = new JTextField();
		tfKor.setEditable(false);
//		tfKor.setText(String.valueOf(koravg));

		tfKor.setColumns(10);
		panel_1.add(tfKor);

		tfEng = new JTextField();
		tfEng.setEditable(false);
		tfEng.setColumns(10);
		panel_1.add(tfEng);

		tfMath = new JTextField();
		tfMath.setEditable(false);
		tfMath.setColumns(10);
		panel_1.add(tfMath);

		tfSoc = new JTextField();
		tfSoc.setEditable(false);
		tfSoc.setColumns(10);
		panel_1.add(tfSoc);

		tfSic = new JTextField();
		tfSic.setEditable(false);
		tfSic.setColumns(10);
		panel_1.add(tfSic);
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
	public void initList2(StudentDto student) {
		list = service.showStudnetsByTotal(student);
		System.out.println(list);
		setList();
		setavg();
	}

	public void setavg() {
		int k = 0;
		int e = 0;
		int m = 0;
		int s = 0;
		int si = 0;
		koravg = 0;
		engavg = 0;
		mathavg = 0;
		socavg = 0;
		sciavg = 0;
		for (StudentDto t : list) {
			int korscore = t.getJumsu().get(0).getJumsu();
			k += korscore;
			koravg = k / list.size();
//			System.out.println("koravg=" + koravg);

			int engscore = t.getJumsu().get(1).getJumsu();
			e += engscore;
			engavg = e / list.size();
//			System.out.println("engavg=" + engavg);

			int mathscore = t.getJumsu().get(2).getJumsu();
			m += mathscore;
			mathavg = m / list.size();
//			System.out.println("mathavg=" + mathavg);

			int socscore = t.getJumsu().get(3).getJumsu();
			s += socscore;
			socavg = s / list.size();
//			System.out.println("socavg=" + socavg);

			int sciscore = t.getJumsu().get(4).getJumsu();
			si += sciscore;
			sciavg = si / list.size();
//			System.out.println("sciavg=" + sciavg);

		}
		tfKor.setText(String.valueOf(koravg));
		tfEng.setText(String.valueOf(engavg));
		tfMath.setText(String.valueOf(mathavg));
		tfSoc.setText(String.valueOf(socavg));
		tfSic.setText(String.valueOf(sciavg));
	}

	@Override
	public void initList3(StudentDto student) {
		list = service.showStudentsByName(student);
		setList();
	}

	@Override
	public void initList() {
		list = service.showStudents();
		stds = list.size();
		setavg();
	}

	@Override
	protected void setAlignAndWidth() {
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4, 5, 6, 7, 8);
		setTableCellWidth(80, 100, 80, 60, 60, 60, 60, 70);
	}

	@Override
	public Object[] toArray(StudentDto t) {
		return new Object[] { t.getStdNo(), t.getStdName(), String.format("%s", t.getBan().getBanCode()),
				t.getJumsu().get(0).getJumsu(), t.getJumsu().get(1).getJumsu(), t.getJumsu().get(2).getJumsu(),
				t.getJumsu().get(3).getJumsu(), t.getJumsu().get(4).getJumsu(), t.getAvg() };
	}

	@Override
	public String[] getColumnNames() {
		return new String[] { "학번", "이름", "분반", "국어", "영어", "수학", "사회", "과학", "평균" };
	}

	public void setService(StudentService service) {
		this.service = service;
	}
}
