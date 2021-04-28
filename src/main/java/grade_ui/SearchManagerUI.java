package grade_ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import grade_dto.StudentDto;
import grade_service.StudentService;
import grade_ui_content.AbstractContentPanel;
import grade_ui_content.ScorePanel;
import grade_ui_content.ScoreStdPanel;
import grade_ui_content.SearchPanel;
import grade_ui_list.AbstractCustomTablePanel;
import grade_ui_list.SearchTablePanel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;

public class SearchManagerUI extends AbstractManagerUI<StudentDto> implements ActionListener {
	private StudentService service;
	private ScoreStdPanel pItem;
	private ScorePanel scorePanel;
	private ScoreManagerUI frame;
	private SearchPanel searchPanel;

	public int kor;
	private boolean check;

	public SearchManagerUI() {
		btnAdd.setText("검색");

		stdListByBanItem.setText(AbstractManagerUI.VIEW_MENU);

		updateItem.setVisible(false);
		deleteItem.setVisible(false);
		
	}

	@Override
	protected void setService() {
		service = new StudentService();
	}

	@Override
	protected void tableLoadData() {
		((SearchTablePanel) pList).setService(service);
		
		pList.loadData();
		
	}

	@Override
	protected AbstractContentPanel<StudentDto> createContentPanel() {
		searchPanel = new SearchPanel();
		searchPanel.setService(service);
		return searchPanel;
	}

	@Override
	protected AbstractCustomTablePanel<StudentDto> createTablePanel() {
		return new SearchTablePanel();
	}

	@Override
	protected void actionPerformedMenuGubun() {
		StudentDto std = pList.getItem();
//		System.out.println(std);
		frame = new ScoreManagerUI();
		frame.setStdNo(std);
		frame.setVisible(true);
		frame.extracted();

	}

	@Override
	protected void actionPerformedMenuUpdate() {

	}

	@Override
	protected void actionPerformedMenuDelete() {

	}

	@Override
	protected void actionPerformedBtnUpdate(ActionEvent e) {
		StudentDto search = searchPanel.getItem();
		pList.initList2(search);
		JFreeChart chart = getChart();
		ChartPanel subjChart = new ChartPanel(chart);
		if(check==true) {
			((SearchTablePanel) pList).pSubjChart.removeAll();
		System.out.println("지움");
		}
		((SearchTablePanel) pList).pSubjChart.add(subjChart);
		check = true;
		subjChart.setSize(800, 400);
		subjChart.setVisible(check);
		
	}

	@Override
	protected void actionPerformedBtnAdd(ActionEvent e) {

	}

	@Override
	protected void actionPerformedBtnClear(ActionEvent e) {
		pContent.clearTf();

		tableLoadData();

	}

	public JFreeChart getChart() {
		// 데이터 생성
//		tfKor.setText(String.valueOf(koravg));
//		tfEng.setText(String.valueOf(engavg));
//		tfMath.setText(String.valueOf(mathavg));
//		tfSoc.setText(String.valueOf(socavg));
//		tfSic.setText(String.valueOf(sciavg));
		DefaultCategoryDataset dataset1 = new DefaultCategoryDataset(); // bar chart 1
		// 데이터 입력 ( 값, 범례, 카테고리 )
		// 그래프 1
		int kor=((SearchTablePanel) pList).getKoravg();
		int eng=((SearchTablePanel) pList).getEngavg();
		int math=((SearchTablePanel) pList).getMathavg();
		int soc=((SearchTablePanel) pList).getSocavg();
		int sci=((SearchTablePanel) pList).getSciavg();

		dataset1.addValue(kor, "S1", "국어");
		
		dataset1.addValue(eng, "S1", "영어");

		dataset1.addValue(math, "S1", "수학");

		dataset1.addValue(soc, "S1", "사회");

		dataset1.addValue(sci, "S1", "과학");

		// 그래프 2


		// 렌더링 생성 및 세팅

		// 렌더링 생성

		final BarRenderer renderer = new BarRenderer();


		// 공통 옵션 정의

		final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();

		final ItemLabelPosition p_center = new ItemLabelPosition(

				ItemLabelAnchor.CENTER, TextAnchor.CENTER

		);

		final ItemLabelPosition p_below = new ItemLabelPosition(

				ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT

		);

		Font f = new Font("Gulim", Font.BOLD, 14);

		Font axisF = new Font("Gulim", Font.PLAIN, 14);

		// 렌더링 세팅
		renderer.setBaseItemLabelFont(f);

		// 그래프 1

		renderer.setBaseItemLabelGenerator(generator);

		renderer.setBaseItemLabelsVisible(true);

		renderer.setBasePositiveItemLabelPosition(p_center);


//        renderer.setGradientPaintTransformer(new StandardGradientPaintTransformer(

//                GradientPaintTransformType.VERTICAL));

//        renderer.setSeriesPaint(0, new GradientPaint(1.0f, 1.0f, Color.white, 0.0f, 0.0f, Color.blue));

		renderer.setSeriesPaint(0, new Color(0, 162, 255));

		// 그래프 2


		// 그래프 3


		// plot 생성

		final CategoryPlot plot = new CategoryPlot();

		// plot 에 데이터 적재

		plot.setDataset(dataset1);

		plot.setRenderer(renderer);

		// plot 기본 설정

		plot.setOrientation(PlotOrientation.VERTICAL); // 그래프 표시 방향

		plot.setRangeGridlinesVisible(true); // X축 가이드 라인 표시여부

		plot.setDomainGridlinesVisible(true); // Y축 가이드 라인 표시여부

		// 렌더링 순서 정의 : dataset 등록 순서대로 렌더링 ( 즉, 먼저 등록한게 아래로 깔림 )

		plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

		// X축 세팅

		plot.setDomainAxis(new CategoryAxis()); // X축 종류 설정

		plot.getDomainAxis().setTickLabelFont(axisF); // X축 눈금라벨 폰트 조정

		plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD); // 카테고리 라벨 위치 조정

		// Y축 세팅

		plot.setRangeAxis(new NumberAxis()); // Y축 종류 설정

		plot.getRangeAxis().setTickLabelFont(axisF); // Y축 눈금라벨 폰트 조정

		// 세팅된 plot을 바탕으로 chart 생성

		final JFreeChart chart = new JFreeChart(plot);

//        chart.setTitle("Overlaid Bar Chart"); // 차트 타이틀

//        TextTitle copyright = new TextTitle("JFreeChart WaferMapPlot", new Font("SansSerif", Font.PLAIN, 9));

//        copyright.setHorizontalAlignment(HorizontalAlignment.RIGHT);

//        chart.addSubtitle(copyright);  // 차트 서브 타이틀

		return chart;

	}

}
