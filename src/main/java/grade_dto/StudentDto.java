package grade_dto;

import java.util.List;

import grade_dao.SubjectDao;

public class StudentDto {

	private int stdNo;
	private String stdName;
	private BanDto ban;
	private List<ScoreDto> jumsu;

	private String order;
	private int limit;
	private double avg;

	
	public StudentDto(int stdNo) {
		this.stdNo = stdNo;
	}

	public StudentDto(String stdName) {
		this.stdName = stdName;
	}

	public StudentDto(BanDto ban) {
		this.ban = ban;
	}

	public StudentDto(BanDto ban, String order, int limit) {
		this.ban = ban;
		this.order = order;
		this.limit = limit;
	}

	public StudentDto(int stdNo, String stdName, BanDto ban) {
		this.stdNo = stdNo;
		this.stdName = stdName;
		this.ban = ban;
	}

	public StudentDto(int stdNo, List<ScoreDto> jumsu) {
		this.stdNo = stdNo;
		this.jumsu = jumsu;
	}

	public StudentDto(int stdNo, String stdName, BanDto ban, List<ScoreDto> jumsu, double avg) {
		this.stdNo = stdNo;
		this.stdName = stdName;
		this.ban = ban;
		this.jumsu = jumsu;
		this.avg = avg;
	}



	public StudentDto(String order, int limit) {
		this.order = order;
		this.limit = limit;
	}



	public StudentDto() {
		// TODO Auto-generated constructor stub
	}

	public int getStdNo() {
		return stdNo;
	}

	public void setStdNo(int stdNo) {
		this.stdNo = stdNo;
	}

	public String getStdName() {
		return stdName;
	}

	public void setStdName(String stdName) {
		this.stdName = stdName;
	}

	public BanDto getBan() {
		return ban;
	}

	public void setBan(BanDto ban) {
		this.ban = ban;
	}

	public List<ScoreDto> getJumsu() {
		return jumsu;
	}

	public void setJumsu(List<ScoreDto> jumsu) {
		this.jumsu = jumsu;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	@Override
	public String toString() {
		return String.format("StudentDto [stdNo=%s, stdName=%s, ban=%s, jumsu=%s, order=%s, limit=%s, avg=%s]", stdNo,
				stdName, ban, jumsu, order, limit, avg);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + stdNo;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentDto other = (StudentDto) obj;
		if (stdNo != other.stdNo)
			return false;
		return true;
	}

	


}
