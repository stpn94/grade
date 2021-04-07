package grade_ui_content;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import grade_dto.BanDto;
import grade_dto.SubjectDto;
import grade_service.BanService;

public class BanPanel extends JPanel implements ActionListener {
	
	private JPanel pBtns2;
	private JButton btnCheck;
	private JButton btnModify;
	private JButton btnDelete;
	private JList<BanDto> banList;
	private JButton btnAdd;
	private BanContentPanel pContent;
	private DefaultListModel<BanDto> model;
	private BanDto selBan;
	private int idx;
	private BanService service;

	public BanPanel() {
		initialize();
		service = new BanService();
		List<BanDto> list = service.showBans();
		for(BanDto i : list){
			model.addElement(i);
		}
	}

	private void initialize() {
		setBorder(new TitledBorder(null, "\uBC18 \uC815\uBCF4", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new GridLayout(0, 2, 10, 0));

		JPanel pLeft = new JPanel();
		pLeft.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(pLeft);
		pLeft.setLayout(new BorderLayout(0, 0));

		pContent = new BanContentPanel();
		pLeft.add(pContent, BorderLayout.CENTER);
		pContent.setLayout(new GridLayout(0, 2, 10, 10));

		JPanel pBtns = new JPanel();
		pLeft.add(pBtns, BorderLayout.SOUTH);

		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);

		JPanel pRight = new JPanel();
		add(pRight);
		pRight.setLayout(new BorderLayout(0, 0));

		banList = new JList();
		banList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		banList.setBorder(new EmptyBorder(0, 0, 0, 0));
		model = new DefaultListModel<BanDto>();
		pRight.setLayout(new BorderLayout(0, 0));
		banList.setModel(model);
		pRight.add(banList);

		pBtns2 = new JPanel();
		pRight.add(pBtns2, BorderLayout.SOUTH);

		btnCheck = new JButton("확인");
		btnCheck.addActionListener(this);
		pBtns2.add(btnCheck);

//		btnModify = new JButton("수정");
//		btnModify.addActionListener(this);
//		pBtns2.add(btnModify);

		btnDelete = new JButton("삭제");
		btnDelete.addActionListener(this);
		pBtns2.add(btnDelete);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnDelete) {
			actionPerformedBtnDelete(e);
		}
//		if (e.getSource() == btnModify) {
//			actionPerformedBtnModify(e);
//		}
		if (e.getSource() == btnCheck) {
			actionPerformedBtnCheck(e);
		}
		if (e.getSource() == btnAdd) {
			actionPerformedBtnAdd(e);
		}
	}

	// 여기서부터 버튼 추가 하고 마무리 하기
	protected void actionPerformedBtnAdd(ActionEvent e) {
		try {
			if (btnAdd.getText().equals("추가")) {
				BanDto ban = pContent.getBan();
				pContent.clearTf();
				model.addElement(ban);
				service.addBan(ban);
				JOptionPane.showMessageDialog(null, "추가되었습니다.");
			} else {
				idx = banList.getSelectedIndex();
				BanDto ban = pContent.getBan();
				model.setElementAt(ban, idx);
				service.addBan(ban);
				pContent.clearTf();
				btnAdd.setText("추가");
				String message = String.format("%d%s", selBan.getBanNo(), selBan.getBanCode());
				JOptionPane.showMessageDialog(null, message);
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "공백이 존재합니다.");
		}
	}

	protected void actionPerformedBtnCheck(ActionEvent e) {
		try {
			idx = banList.getSelectedIndex();
			selBan = model.get(idx);
			String message = String.format("%d%s", selBan.getBanNo(), selBan.getBanCode());
			JOptionPane.showMessageDialog(null, message);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "확인할 부서를 선택해 주세요.");
		}
	}

//	protected void actionPerformedBtnModify(ActionEvent e) {
//		try {
//			BanDto ban = banList.getSelectedValue();
//			pContent.setBan(ban);
//			btnAdd.setText("수정");
//		} catch (Exception e2) {
//			JOptionPane.showMessageDialog(null, "수정할 부서를 선택해 주세요.");
//		}
//	}

	protected void actionPerformedBtnDelete(ActionEvent e) {
		try {
			idx = banList.getSelectedIndex();
			selBan = model.remove(idx);
			service.removeBan(selBan.getBanCode());
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "삭제할 부서를 선택해 주세요.");
		}

	}
}
