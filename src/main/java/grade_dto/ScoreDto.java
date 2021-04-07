package grade_dto;

import java.util.List;

public class ScoreDto {
	
	private SubjectDto subject;
	private int jumsu;
	
	public ScoreDto() {
	}
	
	public ScoreDto(int jumsu) {
		this.jumsu = jumsu;
	}

	public ScoreDto(SubjectDto subject, int jumsu) {
		this.subject = subject;
		this.jumsu = jumsu;
	}

	public SubjectDto getSubject() {
		return subject;
	}

	public void setSubject(SubjectDto subject) {
		this.subject = subject;
	}

	public int getJumsu() {
		return jumsu;
	}

	public void setJumsu(int jumsu) {
		this.jumsu = jumsu;
	}

	@Override
	public String toString() {
		return String.format("ScoreDto [subject=%s, jumsu=%s]", subject, jumsu);
	}
}
