package grade_ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;

import grade_ui.exception.InvalidCheckException;
import grade_ui.exception.SqlConstraintException;
import grade_ui_content.AbstractContentPanel;
import grade_ui_list.AbstractCustomTablePanel;

@SuppressWarnings("serial")
public abstract class AbstractManagerUI<T> extends JFrame implements ActionListener {

	private JPanel contentPane;
	protected JButton btnAdd;
	private JButton btnClear;

	protected AbstractContentPanel<T> pContent;
	protected AbstractCustomTablePanel<T> pList;
	protected JMenuItem stdListByBanItem;

	protected static final String BAN_MENU = "동일 반 학생 보기";
	protected static final String STD_MENU = "학생 세부정보 보기";
	protected static final String VIEW_MENU = "성적 수정하로가기";
	protected JMenuItem updateItem;
	protected JMenuItem deleteItem;
	protected JPopupMenu popMenu;

	public AbstractManagerUI() {
		setService();// service 연결

		initialize();

		tableLoadData();// component load data
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		pContent = createContentPanel();
		contentPane.add(pContent);

		JPanel pBtns = new JPanel();
		contentPane.add(pBtns);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);

		btnClear = new JButton("취소");
		btnClear.addActionListener(this);
		pBtns.add(btnClear);

		pList = createTablePanel();
		contentPane.add(pList);

		JPopupMenu popupMenu = createPopupMenu();
		pList.setPopupMenu(popupMenu);
	}

	private JPopupMenu createPopupMenu() {
		popMenu = new JPopupMenu();

		updateItem = new JMenuItem("수정");
		updateItem.addActionListener(this);
		popMenu.add(updateItem);

		deleteItem = new JMenuItem("삭제");
		deleteItem.addActionListener(this);
		popMenu.add(deleteItem);

		stdListByBanItem = new JMenuItem("동일 반 학생 보기");
		stdListByBanItem.addActionListener(this);
		
		popMenu.add(stdListByBanItem);

		return popMenu;
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("1313131");
		try {
			if (e.getSource() instanceof JMenuItem) {
				if (e.getActionCommand().equals("삭제")) {
					actionPerformedMenuDelete();
				}

				if (e.getActionCommand().equals("수정")) {
					actionPerformedMenuUpdate();
				}

				if (e.getActionCommand().contentEquals(AbstractManagerUI.BAN_MENU)
						|| e.getActionCommand().contentEquals(AbstractManagerUI.STD_MENU)
						|| e.getActionCommand().contentEquals(AbstractManagerUI.VIEW_MENU)) {
					actionPerformedMenuGubun();
				}

			} else {
				if (e.getSource() == btnClear) {
					actionPerformedBtnClear(e);
				}

				if (e.getSource() == btnAdd) {
					if (e.getActionCommand().contentEquals("추가")) {
						actionPerformedBtnAdd(e);
					} else {
						actionPerformedBtnUpdate(e);
					}
				}
			}
		} catch (InvalidCheckException | SqlConstraintException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
//			pContent.clearTf();
		} catch (NumberFormatException e2) {
			JOptionPane.showMessageDialog(null, "형식을 확인해 주세요");
		}
	}

	protected abstract void setService();

	protected abstract void tableLoadData();

	protected abstract AbstractContentPanel<T> createContentPanel();

	protected abstract AbstractCustomTablePanel<T> createTablePanel();

	protected abstract void actionPerformedMenuGubun();

	protected abstract void actionPerformedMenuUpdate();

	protected abstract void actionPerformedMenuDelete();

	protected abstract void actionPerformedBtnUpdate(ActionEvent e);

	protected abstract void actionPerformedBtnAdd(ActionEvent e);

	protected void actionPerformedBtnClear(ActionEvent e) {
		pContent.clearTf();

		if (btnAdd.getText().contentEquals("수정")) {
			btnAdd.setText("추가");
		}
	}
}
