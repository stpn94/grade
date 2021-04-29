package grade_ui_content;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import grade_dto.SubjectDto;
import grade_service.SubjectService;

public class SubjectPanel extends JPanel implements ActionListener {

	private SubjContentPanel pContent;
	private JButton btnAdd;
	private JButton btnCheck;
	private JButton btnModify;
	private JButton btnDelete;
	private JPanel pBtns2;
	private int idx;
	private JList<SubjectDto> subjList;
	private DefaultListModel<SubjectDto> model;
	private SubjectDto selSubj;
	private SubjectService service;
	
	public SubjectPanel() {
		initialize();
		service = new SubjectService();
		List<SubjectDto> list = service.showSubjects();
		for(SubjectDto i : list){
			model.addElement(i);
		}
		System.out.println(service.showSubjects());
	}

	private void initialize() {
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\uACFC\uBAA9 \uC815\uBCF4",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(new GridLayout(0, 2, 10, 0));

		JPanel pLeft = new JPanel();
		pLeft.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(pLeft);
		pLeft.setLayout(new BorderLayout(0, 0));

		pContent = new SubjContentPanel();
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

		subjList = new JList<>();
		subjList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		subjList.setBorder(new EmptyBorder(0, 0, 0, 0));
		model = new DefaultListModel<SubjectDto>();
		pRight.setLayout(new BorderLayout(0, 0));
		subjList.setModel(model);
		pRight.add(subjList);

		pBtns2 = new JPanel();
		pRight.add(pBtns2, BorderLayout.SOUTH);

		btnCheck = new JButton("확인");
		btnCheck.addActionListener(this);
		pBtns2.add(btnCheck);



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
				SubjectDto subj = pContent.getSubj();
				pContent.clearTf();
				model.addElement(subj);
				service.addSubject(subj);
				JOptionPane.showMessageDialog(null, "추가되었습니다.");
			} else {
				idx = subjList.getSelectedIndex();
				SubjectDto subj = pContent.getSubj();
				model.setElementAt(subj, idx);
				service.addSubject(subj);
				pContent.clearTf();
				btnAdd.setText("추가");
				String message = String.format(".__%s", selSubj.getSubjName());
				JOptionPane.showMessageDialog(null, message);
			}
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "공백이 존재합니다.");
		}
	}

	protected void actionPerformedBtnCheck(ActionEvent e) {
		try {
			idx = subjList.getSelectedIndex();
			selSubj = model.get(idx);
			String message = String.format("%d%s", selSubj.getSubjNo(), selSubj.getSubjName());
			JOptionPane.showMessageDialog(null, message);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "확인할 부서를 선택해 주세요.");
		}
	}

//	protected void actionPerformedBtnModify(ActionEvent e) {
//		try {
//			SubjectDto subj = subjList.getSelectedValue();
//			pContent.setSubj(subj);
//			btnAdd.setText("수정");
//		} catch (Exception e2) {
//			JOptionPane.showMessageDialog(null, "수정할 부서를 선택해 주세요.");
//		}
//	}

	protected void actionPerformedBtnDelete(ActionEvent e) {
		try {
			idx = subjList.getSelectedIndex();
			selSubj = model.remove(idx);
			service.removeSubject(selSubj.getSubjName());
			
			
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "삭제할 부서를 선택해 주세요.");
		}

	}
}
