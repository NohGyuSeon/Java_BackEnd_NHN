show databases;

CREATE DATABASE Module0301;

show databases;

SELECT database();

use Module0301;

SELECT database();

-- 학생
CREATE TABLE Student (
	ID int, 
	StudentName NVARCHAR(10) NOT NULL,
	Major NVARCHAR(10) NOT NULL,
    AGE int NOT NULL,
	Grade int, 
	CONSTRAINT PRIMARY KEY (ID)
);

DESC Student;
SELECT * FROM Student;



 -- 교수
CREATE TABLE Professor (
    ProfessorID int, 
	ProfessorName NVARCHAR(20) NOT NULL, 
	DepartmentID int NOT NULL
);

DESC Professor;
SELECT * FROM Professor;

ALTER TABLE Professor ADD CONSTRAINT PRIMARY KEY (ProfessorID);



-- 과목
CREATE TABLE Subject (
	SubjectName NVARCHAR(20),
	ClassTime DATETIME, 
    ClassRoom NVARCHAR(10),
    ProfessorID int NOT NULL,
	CONSTRAINT PRIMARY KEY (SubjectName),
	CONSTRAINT FOREIGN KEY(ProfessorID) REFERENCES Professor(ProfessorID)
);

DESC Subject;
SELECT * FROM Subject;



-- 수강
CREATE TABLE Course (
    ID int, 
	SubjectName NVARCHAR(20),
	CONSTRAINT FOREIGN KEY(ID) REFERENCES Student(ID),
    CONSTRAINT FOREIGN KEY(SubjectName) REFERENCES Subject(SubjectName)
);

DESC COURSE;
SELECT * FROM COURSE;

show tables;

ALTER TABLE Student MODIFY Major NVARCHAR(20) NOT NULL;

INSERT INTO Student (ID, StudentName, Major, AGE, Grade) VALUES
(1, '김철수', '컴퓨터공학', 20, 3),
(2, '박영희', '경영학', 22, 4),
(3, '이승우', '영어교육학', 21, 3),
(4, '홍길동', '전자공학', 23, 4),
(5, '최영민', '국어교육학', 20, 3);

INSERT INTO Professor (ProfessorID, ProfessorName, DepartmentID) VALUES
(101, '김교수', 1),
(102, '이교수', 2),
(103, '박교수', 3);

INSERT INTO Subject (SubjectName, ClassTime, ClassRoom, ProfessorID) VALUES
('컴퓨터구조', '2023-04-10 09:00:00', '2호관 302호', 101),
('소프트웨어공학', '2023-04-11 13:00:00', '1호관 101호', 102),
('영어회화', '2023-04-12 11:00:00', '4호관 202호', 103),
('데이터베이스', '2023-04-13 10:00:00', '3호관 201호', 101),
('국어문학', '2023-04-14 14:00:00', '5호관 301호', 103);

INSERT INTO Course (ID, SubjectName) VALUES
(1, '컴퓨터구조'),
(3, '영어회화'),
(5, '국어문학');

INSERT INTO Student (ID, StudentName, Major, AGE, Grade) VALUES
(6, '김영희', '사회학', 21, 2),
(7, '박진수', '물리학', 23, 4),
(8, '이준호', '정치외교학', 22, 3),
(9, '홍길순', '영문학', 20, 2),
(10, '최인천', '국제통상학', 23, 4);

INSERT INTO Professor (ProfessorID, ProfessorName, DepartmentID) VALUES
(104, '김교수', 1),
(105, '이교수', 2),
(106, '박교수', 3),
(107, '장교수', 1),
(108, '임교수', 2);

INSERT INTO Subject (SubjectName, ClassTime, ClassRoom, ProfessorID) VALUES
('물리학개론', '2023-04-15 15:00:00', '3호관 301호', 104),
('정치경제학', '2023-04-16 10:00:00', '2호관 201호', 105);

INSERT INTO Course (ID, SubjectName) VALUES
(1, '컴퓨터구조'),
(3, '영어회화'),
(5, '국어문학'),
(1, '컴퓨터구조'),
(3, '영어회화'),
(5, '국어문학');

INSERT INTO Professor (ProfessorID, ProfessorName, DepartmentID) VALUES
(109, '이순신', 1);

INSERT INTO Subject (SubjectName, ClassTime, ClassRoom, ProfessorID) VALUES
('역사', '2023-04-15 15:00:00', '3호관 301호', 109);

INSERT INTO Student (ID, StudentName, Major, AGE, Grade) VALUES
(11, '김영선', '사회학', 22, 3),
(12, '박진선', '물리학', 24, 3);

INSERT INTO Course (ID, SubjectName) VALUES
(11, '역사'),
(12, '역사');

-- A
SELECT s.StudentName
FROM Student s
INNER JOIN Course c ON s.ID = c.ID
INNER JOIN Subject sj ON c.SubjectName = sj.SubjectName AND sj.ProfessorID = (SELECT ProfessorID FROM Professor WHERE ProfessorName = '이순신')
WHERE s.Grade = 3;

-- B
SELECT MAX(s.AGE)
FROM Student s
INNER JOIN Course c ON s.ID = c.ID
INNER JOIN Subject sj ON c.SubjectName = '역사' OR sj.ProfessorID = (SELECT ProfessorID FROM Professor WHERE ProfessorName = '이순신')
WHERE s.Grade = 3;

-- C
SELECT s.SubjectName
FROM Subject s  
INNER JOIN Course c ON s.SubjectName = c.SubjectName
WHERE s.ClassRoom = '5호관 301호' OR s.SubjectName = c.SubjectName >= 5;

-- D
SELECT s.StudentName
FROM Subject s  
INNER JOIN Course c ON s.SubjectName = c.SubjectName
INNER JOIN Subject sj ON c.SubjectName = sj.SubjectName AND sj.ProfessorID = (SELECT ProfessorID FROM Professor WHERE ProfessorName = '이순신')
WHERE s.Grade = 3;




-- E
SELECT s.SubjectName
FROM Subject s  
INNER JOIN Course c ON s.SubjectName = c.SubjectName
WHERE s.ClassRoom = '5호관 301호' OR s.SubjectName = c.SubjectName >= 5;



use DatamotionMovieDatabase;

show tables;

select *
from movie;












