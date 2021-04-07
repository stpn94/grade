package grade_dto;

public class SubjectDto {

	private int subjNo;
	private String subjName;

	public SubjectDto() {
	}

	public SubjectDto(String subjName) {
		this.subjName = subjName;
	}

	public SubjectDto(int subjNo) {
		this.subjNo = subjNo;
	}

	public SubjectDto(int subjNo, String subjName) {
		this.subjNo = subjNo;
		this.subjName = subjName;
	}

	public int getSubjNo() {
		return subjNo;
	}

	public void setSubjNo(int subjNo) {
		this.subjNo = subjNo;
	}

	public String getSubjName() {
		return subjName;
	}

	public void setSubjName(String subjName) {
		this.subjName = subjName;
	}

	@Override
	public String toString() {
		return String.format("%s(%d)", subjName, subjNo);
	}
}
