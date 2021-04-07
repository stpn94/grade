package grade_ui_content;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

import grade_dto.StudentDetailDto;
import grade_dto.StudentDto;

public class StudentDetailPanel extends AbstractContentPanel<StudentDetailDto> implements ActionListener {

	private String imgPath = System.getProperty("user.dir") + File.separator + "image" + File.separator;
	private JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));

	private JLabel lblPic;
	private JButton butAddPic;
	private JDateChooser dateBirthday;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnFemale;
	private JRadioButton rdbtnMale;
	private JTextField tfStdNo;

	public StudentDetailPanel() {
		initialize();
		loadPic(null);
	}

	private void loadPic(String imgFilePath) {
		Image changeImage = null;
		if (imgFilePath == null) {
			ImageIcon icon = new ImageIcon(imgPath + "NoImage.jpg");
			changeImage = icon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
		} else {
			ImageIcon icon = new ImageIcon(imgFilePath);
			changeImage = icon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
		}
		ImageIcon changeIcon =new ImageIcon(changeImage);
		lblPic.setIcon(changeIcon);
	}

	private void initialize() {
		setBorder(new TitledBorder(null, "세부 정보", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));

		JPanel pTop = new JPanel();
		add(pTop, BorderLayout.NORTH);

		JPanel pPic = new JPanel();
		pTop.add(pPic);
		pPic.setLayout(new BorderLayout(0, 0));

		lblPic = new JLabel();
		lblPic.setPreferredSize(new Dimension(100, 150));
		pPic.add(lblPic, BorderLayout.NORTH);

		butAddPic = new JButton("사진 추가");
		butAddPic.addActionListener(this);
		pPic.add(butAddPic, BorderLayout.SOUTH);

		JPanel pItem = new JPanel();
		add(pItem);
		pItem.setLayout(new GridLayout(0, 1, 0, 0));

		JPanel pContent = new JPanel();
		pItem.add(pContent);
		pContent.setLayout(new GridLayout(0, 2, 30, 30));

		JLabel lblStdNo = new JLabel("학생번호");
		lblStdNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblStdNo);

		tfStdNo = new JTextField();
		tfStdNo.setEditable(false);
		tfStdNo.setColumns(10);
		pContent.add(tfStdNo);

		JLabel lblBrithday = new JLabel("생일");
		lblBrithday.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblBrithday);

		dateBirthday = new JDateChooser((Date) null);
		pContent.add(dateBirthday);

		JLabel lblGender = new JLabel("성별");
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		pContent.add(lblGender);

		JPanel pGender = new JPanel();
		pContent.add(pGender);

		rdbtnFemale = new JRadioButton("여");
		rdbtnFemale.setSelected(true);
		buttonGroup.add(rdbtnFemale);
		pGender.add(rdbtnFemale);

		rdbtnMale = new JRadioButton("남");
		buttonGroup.add(rdbtnMale);
		pGender.add(rdbtnMale);
	}
	
	public void setTfEmpno(StudentDto stdNo) {
		tfStdNo.setText(String.valueOf(stdNo.getStdNo()));
	}

	@Override
	public void setItem(StudentDetailDto item) {
		tfStdNo.setText(String.valueOf(item.getStdNo()));
		byte[] iconBytes = item.getPic();
		System.out.println(iconBytes);
		ImageIcon icon = new ImageIcon(iconBytes);
		Image changeImage = icon.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
		ImageIcon changeIcon = new ImageIcon(changeImage);
		lblPic.setIcon(changeIcon);
		dateBirthday.setDate(item.getBirthday());
		if(item.isGender()) {
			rdbtnMale.setSelected(false);
		}
	}

	@Override
	public StudentDetailDto getItem() {
		validCheck();
		int stdNo=Integer.parseInt(tfStdNo.getText().trim());
		boolean gender = rdbtnFemale.isSelected() ? true : false;
		Date birthdayDate =dateBirthday.getDate();
		byte[] pic = getImage();
		
		return new StudentDetailDto(stdNo,gender,birthdayDate,pic);
	}

	private byte[] getImage() {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			ImageIcon icon = (ImageIcon) lblPic.getIcon();
			BufferedImage bi = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);

			// icon을 이미지로 만들기
			Graphics2D g2 = bi.createGraphics();
			g2.drawImage(icon.getImage(), 0, 0, null);
			g2.dispose();

			ImageIO.write(bi, "png", baos);
			return baos.toByteArray();

		} catch (IOException e) {
			e.printStackTrace();
		}
		;
		return null;
	}
	
	@Override
	public void validCheck() {
	}

	@Override
	public void clearTf() {
		loadPic(null);
		dateBirthday.setDate(new Date());
		rdbtnFemale.setSelected(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == butAddPic) {
			actionPerformedButAddPic(e);
		}
	}
	protected void actionPerformedButAddPic(ActionEvent e) {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG & GIF images", "jpg", "png", "gif");

		chooser.setFileFilter(filter);
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		int res = chooser.showOpenDialog(null);
		if (res != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다", "경고", JOptionPane.WARNING_MESSAGE);
			return;
		}

		String chooseFilePath = chooser.getSelectedFile().getPath();
		loadPic(chooseFilePath);
	}
}
