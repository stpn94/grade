package grade_ui_list;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import grade_dto.StudentDto;
import grade_service.StudentService;

public class ScoreTablePanel extends JPanel {
	private JTable table;
	private List<StudentDto> list;
	private StudentService service;
	
	public void setService(StudentService service) {
		this.service = service;
	}
	
	private class CustomTableModel extends DefaultTableModel {

		public CustomTableModel() {
		}

		public CustomTableModel(Object[][] data, Object[] columnNames) {
			super(data, columnNames);
		}

		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}

	public ScoreTablePanel() {
		initialize();

	}

	private void initialize() {
		setLayout(new GridLayout(0, 1, 0, 0));

		JPanel pScoreTable = new JPanel();
		add(pScoreTable);
		pScoreTable.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		pScoreTable.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setModel(getModel());
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// setmodel
		scrollPane.setViewportView(table);
	}

	public DefaultTableModel getModel() {
		CustomTableModel model = new CustomTableModel();
		return model;
	}

	public String[] getColumnNames() {
		return new String[] { "학번", "이름", "분반", "국어", "영어", "수학", "사회", "과학", "평균" };
	}

	public void loadData() {
		initList();
		setList();
	}
	

	private void initList() {
		list = service.showStudents();
	}

	private void setList() {
		Object[][] data = new Object[list.size()][];
		for (int i = 0; i < data.length; i++) {
			data[i] = toArray(list.get(i));
		}

		CustomTableModel model = new CustomTableModel(data, getColumnNames());
		table.setModel(model);

		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		table.setRowSorter(sorter);

		setAlignAndWidth();
	}

	private Object[] toArray(StudentDto t) {
		return new Object[] { t.getStdNo(), t.getStdName(), String.format("%s", t.getBan().getBanCode()),
				t.getJumsu().get(0).getJumsu(), t.getJumsu().get(1).getJumsu(), t.getJumsu().get(2).getJumsu(),
				t.getJumsu().get(3).getJumsu(), t.getJumsu().get(4).getJumsu(), t.getAvg() };
	}

	private void setAlignAndWidth() {
		setTableCellAlign(SwingConstants.CENTER, 0, 1, 2, 3, 4, 5, 6);
		setTableCellWidth(150, 150, 100, 100, 100, 100, 100);
	}

	private void setTableCellWidth(int... width) {
		TableColumnModel tcm = table.getColumnModel();

		for (int i = 0; i < width.length; i++) {
			tcm.getColumn(i).setPreferredWidth(width[i]);
		}
	}

	private void setTableCellAlign(int align, int... idx) {
		TableColumnModel tcm = table.getColumnModel();

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align);

		for (int i = 0; i < idx.length; i++) {
			tcm.getColumn(idx[i]).setCellRenderer(dtcr);
		}
	}

}
