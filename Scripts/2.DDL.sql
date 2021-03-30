-- 내 스키마
DROP SCHEMA IF EXISTS grade;

-- 내 스키마
CREATE SCHEMA grade;

-- 학생
CREATE TABLE grade.student (
	stdNo   INT         NOT NULL COMMENT '학생번호', -- 학생번호
	stdName VARCHAR(50) NULL     COMMENT '학생명', -- 학생명
	ban     INT         NULL     COMMENT '반' -- 반
)
COMMENT '학생';

-- 학생
ALTER TABLE grade.student
	ADD CONSTRAINT PK_student -- 학생 기본키
		PRIMARY KEY (
			stdNo -- 학생번호
		);

-- 학생정보
CREATE TABLE grade.std_detail (
	stdNo    INT      NOT NULL COMMENT '학생번호', -- 학생번호
	pic      LONGBLOB NULL     COMMENT '증명사진', -- 증명사진
	gender   TINYINT  NULL     COMMENT '성별', -- 성별
	birthday DATE     NULL     COMMENT '생년월일' -- 생년월일
)
COMMENT '학생정보';

-- 학생정보
ALTER TABLE grade.std_detail
	ADD CONSTRAINT PK_std_detail -- 학생정보 기본키
		PRIMARY KEY (
			stdNo -- 학생번호
		);

-- 과목
CREATE TABLE grade.subject (
	subjNo   INT         NOT NULL COMMENT '과목번호', -- 과목번호
	subjName VARCHAR(20) NULL     COMMENT '과목명' -- 과목명
)
COMMENT '과목';

-- 과목
ALTER TABLE grade.subject
	ADD CONSTRAINT PK_subject -- 과목 기본키
		PRIMARY KEY (
			subjNo -- 과목번호
		);

-- 반
CREATE TABLE grade.ban (
	banNo   INT         NOT NULL COMMENT '반넘버', -- 반넘버
	banCode VARCHAR(10) NULL     COMMENT '반코드' -- 반코드
)
COMMENT '반';

-- 반
ALTER TABLE grade.ban
	ADD CONSTRAINT PK_ban -- 반 기본키
		PRIMARY KEY (
			banNo -- 반넘버
		);

-- 점수
CREATE TABLE grade.Score (
	stdNo   INT NOT NULL COMMENT '학생번호', -- 학생번호
	subject INT NOT NULL COMMENT '과목', -- 과목
	jumsu   INT NULL     COMMENT '점수' -- 점수
)
COMMENT '점수';

-- 점수
ALTER TABLE grade.Score
	ADD CONSTRAINT PK_Score -- 점수 기본키
		PRIMARY KEY (
			stdNo,   -- 학생번호
			subject  -- 과목
		);

-- 학생
ALTER TABLE grade.student
	ADD CONSTRAINT FK_ban_TO_student -- 반 -> 학생
		FOREIGN KEY (
			ban -- 반
		)
		REFERENCES grade.ban ( -- 반
			banNo -- 반넘버
		)
		ON DELETE CASCADE
		ON UPDATE CASCADE;

-- 학생정보
ALTER TABLE grade.std_detail
	ADD CONSTRAINT FK_student_TO_std_detail -- 학생 -> 학생정보
		FOREIGN KEY (
			stdNo -- 학생번호
		)
		REFERENCES grade.student ( -- 학생
			stdNo -- 학생번호
		);
		

-- 점수
ALTER TABLE grade.Score
	ADD CONSTRAINT FK_student_TO_Score -- 학생 -> 점수
		FOREIGN KEY (
			stdNo -- 학생번호
		)
		REFERENCES grade.student ( -- 학생
			stdNo -- 학생번호
		);

-- 점수
ALTER TABLE grade.Score
	ADD CONSTRAINT FK_subject_TO_Score -- 과목 -> 점수
		FOREIGN KEY (
			subject -- 과목
		)
		REFERENCES grade.subject ( -- 과목
			subjNo -- 과목번호
		);
		
	