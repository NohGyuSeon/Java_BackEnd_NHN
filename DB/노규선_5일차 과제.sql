USE DatamotionMovieDatabase;

# 1. DBManager 라는 이름을 가지는 사용자를 작성하세요.
CREATE USER DBManager IDENTIFIED BY '12341';

# 2. User 라는 이름을 가지는 사용자를 작성하세요.
CREATE USER User IDENTIFIED BY '12342';

# 3. DBManger 사용자는 DatamotionMovieDatabase의 모든 개체에 모든 권한을 가지되, 다른 데이터베이스에 대한 권한은 가지지 않아야 합니다.
GRANT ALL PRIVILEGES ON DatamotionMovieDatabase.* TO 'DBManager'@'%';

# 4. User 사용자는 가지는 데이터베이스의 모든 개체에 대한 읽기 권한과, PersonTrivia 테이블과 MovieTrivia 테이블에는 읽기 및 쓰기 권한을 가집니다.
GRANT SELECT ON DatamotionMovieDatabase.* TO 'User'@'%';
GRANT SELECT, INSERT, UPDATE ON DatamotionMovieDatabase.PersonTrivia TO 'User'@'%';
GRANT SELECT, INSERT, UPDATE ON DatamotionMovieDatabase.MovieTrivia TO 'User'@'%';

# 5. '영화'에 출연한 '배우'와 관련된 뷰를 작성하세요.
CREATE VIEW movie_cast AS
SELECT Title, KoreanTitle, RoleName
FROM movie as m
INNER JOIN appear a ON m.MovieID = a.MovieID
INNER JOIN role r ON a.RoleID = r.RoleID
WHERE r.RoleKorName = '배우';

# 6. '영화'를 감독한 '감독'과 관련된 뷰를 작성하세요.
CREATE VIEW movie_director AS
SELECT COUNT(*) AS '감독 수', KoreanTitle
FROM movie as m
INNER JOIN appear a ON m.MovieID = a.MovieID
INNER JOIN role r ON a.RoleID = r.RoleID
WHERE r.RoleKorName = '감독';

# 7. '아카데미 시상 결과'과 관련된 뷰를 작성하세요.
CREATE VIEW award_winner AS
SELECT m.Title, p.KoreanName
FROM movie as m
INNER JOIN appear a ON m.MovieID = a.MovieID
INNER JOIN person p ON a.PersonID = p.PersonID
INNER JOIN awardinvolve ai ON a.AppearID = ai.AppearID
INNER JOIN winning w ON ai.WinningID = w.WinningID
INNER JOIN awardyear ay ON ai.AwardYearID = ay.AwardYearID
INNER JOIN award aw ON ay.AwardID = aw.AwardID
WHERE w.WinningID = 2;

# 8. '영화 장르별 제작비와 흥행 수익'에 관련된 뷰를 작성하세요.
CREATE VIEW budget_margin_withGenre AS
SELECT DISTINCT AVG(m.Budget) AS '제작비', AVG(m.BoxOfficeWWGross) AS '흥행 수익', g.GenreKorName
FROM movie as m
INNER JOIN moviegenre mg ON m.MovieID = mg.MovieID
INNER JOIN genre g ON mg.GenreID = g.GenreID
GROUP BY g.GenreKorName;

# 9. 영화 '매트릭스' 의 감독이 몇명인지 출력하세요
SELECT COUNT(*) AS '감독 수', KoreanTitle
FROM movie as m
INNER JOIN appear a ON m.MovieID = a.MovieID
INNER JOIN role r ON a.RoleID = r.RoleID
WHERE r.RoleKorName = '감독' AND m.KoreanTitle = '매트릭스';

# 10. 상영시간이 100분 이상인 영화 중 레오나르도 디카프리오가 출연한 영화를 출력하세요.
SELECT DISTINCT m.Title
FROM movie as m
INNER JOIN appear a ON m.MovieID = a.MovieID
INNER JOIN person p ON a.PersonID = p.PersonID
WHERE m.RunningTime >= 100 AND p.KoreanName = '레오나르도 디카프리오';

# 11. '알란 실버스트리'가 음악을 작곡한 영화와 '애런 소킨'이 각본을 쓴 영화를 출력하세요. (애런 소킨 데이터를 찾아보세요)
SELECT DISTINCT Title, KoreanTitle, RoleName
FROM movie as m
INNER JOIN moviegenre mg ON m.MovieID = mg.MovieID
INNER JOIN genre g ON mg.GenreID = g.GenreID
INNER JOIN appear a ON m.MovieID = a.MovieID
INNER JOIN person p ON a.PersonID = p.PersonID
INNER JOIN role r ON a.RoleID = r.RoleID
WHERE (p.KoreanName = '알란 실버스트리' AND r.RoleName = 'Composer')
   OR (r.RoleKorName = '각본가' AND p.KoreanName = '애런 소킨');

# 12. '쿠엔틴 타란티노'가 감독한 영화에 출연한 배우들이 출연한 영화의 수익율을 배우별로 출력하세요.
SELECT p.KoreanName AS '배우', AVG(m.BoxOfficeWWGross - m.Budget) / AVG(m.BoxOfficeWWGross) * 100 AS margin
FROM movie AS m
INNER JOIN appear AS a ON m.MovieID = a.MovieID
INNER JOIN role AS r ON a.RoleID = r.RoleID
INNER JOIN person AS p ON a.PersonID = p.PersonID
WHERE r.RoleKorName = '배우' AND m.MovieID IN (
    SELECT m.MovieID
    FROM movie AS m
    INNER JOIN appear AS a ON m.MovieID = a.MovieID
    INNER JOIN role AS r ON a.RoleID = r.RoleID
    INNER JOIN person AS p ON a.PersonID = p.PersonID
    INNER JOIN role AS r2 ON a.RoleID = r.RoleID
    WHERE r2.RoleKorName = '감독' AND p.KoreanName = '쿠엔틴 타란티노'
)
GROUP BY p.KoreanName;

# 13. 위 문제들 중, 생성한 뷰에서 질의가 어려운 쿼리에 대한 뷰를 작성하세요.
CREATE VIEW director_count_matrix AS
SELECT COUNT(*) AS '감독 수', KoreanTitle
FROM movie as m
INNER JOIN appear a ON m.MovieID = a.MovieID
INNER JOIN role r ON a.RoleID = r.RoleID
WHERE r.RoleKorName = '감독' AND m.KoreanTitle = '매트릭스';

CREATE VIEW overRunningTime_100minutes_byLeonardo AS
SELECT DISTINCT m.Title
FROM movie as m
INNER JOIN appear a ON m.MovieID = a.MovieID
INNER JOIN person p ON a.PersonID = p.PersonID
WHERE m.RunningTime >= 100 AND p.KoreanName = '레오나르도 디카프리오';

CREATE VIEW compose_byAlanSilvestri AS
SELECT DISTINCT Title, KoreanTitle, RoleName
FROM movie as m
INNER JOIN moviegenre mg ON m.MovieID = mg.MovieID
INNER JOIN genre g ON mg.GenreID = g.GenreID
INNER JOIN appear a ON m.MovieID = a.MovieID
INNER JOIN person p ON a.PersonID = p.PersonID
INNER JOIN role r ON a.RoleID = r.RoleID
WHERE (p.KoreanName = '알란 실버스트리' AND r.RoleName = 'Composer')
   OR (r.RoleKorName = '각본가' AND p.KoreanName = '애런 소킨');

CREATE VIEW direct_byQuentinTarantino AS
SELECT p.KoreanName AS '배우', AVG(m.BoxOfficeWWGross - m.Budget) / AVG(m.BoxOfficeWWGross) * 100 AS margin
FROM movie AS m
INNER JOIN appear AS a ON m.MovieID = a.MovieID
INNER JOIN role AS r ON a.RoleID = r.RoleID
INNER JOIN person AS p ON a.PersonID = p.PersonID
WHERE r.RoleKorName = '배우' AND m.MovieID IN (
    SELECT m.MovieID
    FROM movie AS m
    INNER JOIN appear AS a ON m.MovieID = a.MovieID
    INNER JOIN role AS r ON a.RoleID = r.RoleID
    INNER JOIN person AS p ON a.PersonID = p.PersonID
    INNER JOIN role AS r2 ON a.RoleID = r.RoleID
    WHERE r2.RoleKorName = '감독' AND p.KoreanName = '쿠엔틴 타란티노'
)
GROUP BY p.KoreanName;

# 14. 새로 생성한 뷰를 사용해서 쿼리를 다시 작성하세요.
SELECT * FROM director_count_matrix;
SELECT * FROM overRunningTime_100minutes_byLeonardo;
SELECT * FROM compose_byAlanSilvestri;
SELECT * FROM direct_byQuentinTarantino;

# 14. 사용자 테이블을 작성하세요. 사용자 테이블은 사용자의 ID, 이름, 패스워드, 전자메일 주소를 필드로 가집니다.
CREATE TABLE user (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  password VARCHAR(50) NOT NULL,
  email VARCHAR(100) NOT NULL
);

# 15. 사용자가 MovieTrivia 테이블과 PersonTrivia 테이블에 투플을 삽입할 수 있고, 각 투플에 삽입한 사용자를 식별할 수 있는 정보가 포함되어야 할 때, 두 테이블의 릴레이션 스키마를 수정하세요.
ALTER TABLE MovieTrivia ADD COLUMN id INT;
ALTER TABLE PersonTrivia ADD COLUMN id INT;

# 16. 수정된 테이블 두 테이블(MovieTrivia, PersonTrivia)과 사용자 테이블 사이의 참조 무결성을 위한 제약조건을 설정하세요.
ALTER TABLE MovieTrivia
ADD CONSTRAINT FK_MovieTrivia_UserID
FOREIGN KEY (id) REFERENCES user(id) ON DELETE CASCADE;

ALTER TABLE PersonTrivia
ADD CONSTRAINT FK_PersonTrivia_UserID
FOREIGN KEY (id) REFERENCES user(id) ON DELETE CASCADE;

# 17. User 사용자가 두 테이블(MovieTrivia, PersonTrivia)에 데이터를 삽입할 수 있도록 권한을 설정하세요.
GRANT INSERT ON MovieTrivia To User;
GRANT INSERT ON PersonTrivia To User;

# 18. MovieTrivia 테이블에 데이터를 삽입하는 저장 프로시저를 작성하세요.
DELIMITER //
CREATE PROCEDURE insert_movie_trivia (
    IN trivia_id INT,
    IN movie_id INT,
    IN user_id INT
)
BEGIN
    INSERT INTO MovieTrivia (TriviaID, MovieID, id)
    VALUES (trivia_id, movie_id, user_id);
END //
DELIMITER ;

# 19. PersonTrivia 테이블에 데이터를 삽입하는 저장 프로시저를 작성하세요.
DELIMITER //
CREATE PROCEDURE insert_person_trivia (
    IN trivia_id INT,
    IN person_id INT,
    IN user_id INT
)
BEGIN
    INSERT INTO PersonTrivia (TriviaID, PersonID, id)
    VALUES (trivia_id, person_id, user_id);
END //
DELIMITER ;

# 20. 주어진 감독이 감독한 영화를 모두 출력하는 저장 프로시저를 작성하세요.
DELIMITER //
CREATE PROCEDURE getMoviesByDirector(IN director_name VARCHAR(50))
BEGIN
    -- 해당 감독의 ID 조회
    SELECT p.PersonID INTO @director_id
    FROM movie AS m
    INNER JOIN appear a ON m.MovieID = a.MovieID
    INNER JOIN role r ON a.RoleID = r.RoleID
    INNER JOIN person p ON a.PersonID = p.PersonID
    WHERE p.KoreanName = director_name AND r.RoleKorName = '감독'
    LIMIT 1;

    -- 해당 감독이 감독한 영화 정보 가져오기
    SELECT m.Title, p.KoreanName
    FROM movie AS m
    INNER JOIN appear a ON m.MovieID = a.MovieID
    INNER JOIN role r ON a.RoleID = r.RoleID
    INNER JOIN person p ON a.PersonID = p.PersonID
    WHERE p.PersonID = @director_id;
END //
DELIMITER ;

CALL getMoviesByDirector('스티븐 스필버그');

# 21. 주어진 영화에 출연한 배우를 모두 출력하는 저장 프로시저를 작성하세요.
DELIMITER //
CREATE PROCEDURE getActorsByMovie(IN movie_name VARCHAR(50))
BEGIN
    -- 해당 영화의 ID 조회
    SELECT m.MovieID INTO @movie_id
    FROM movie AS m
    WHERE m.KoreanTitle = movie_name;

    -- 해당 영화에 출연한 배우 정보 가져오기
    SELECT m.Title, p.KoreanName, r.RoleKorName
    FROM movie AS m
    INNER JOIN appear a ON m.MovieID = a.MovieID
    INNER JOIN role r ON a.RoleID = r.RoleID
    INNER JOIN person p ON a.PersonID = p.PersonID
    WHERE m.MovieID = @movie_id AND r.RoleKorName = '배우';
END //
DELIMITER ;

CALL getActorsByMovie('유주얼 서스펙트');

# 22. 주어진 영화에 참여한, 감독, 각본, 배우를 제외한 모든 사람을 출력하는 저장 프로시저를 작성하세요.
DELIMITER //
CREATE PROCEDURE getExtrasByMovie(IN movie_name VARCHAR(50))
BEGIN
    -- 해당 영화의 ID 조회
    SELECT m.MovieID INTO @movie_id
    FROM movie AS m
    WHERE m.KoreanTitle = movie_name
    LIMIT 1;

    -- 해당 영화에 참여한, 감독, 각본, 배우를 제외한 모든 사람 정보 가져오기
    SELECT m.Title, p.KoreanName, r.RoleKorName
    FROM movie AS m
    INNER JOIN appear a ON m.MovieID = a.MovieID
    INNER JOIN role r ON a.RoleID = r.RoleID
    INNER JOIN person p ON a.PersonID = p.PersonID
    WHERE m.MovieID = @movie_id
      AND r.RoleKorName != '감독'
      AND r.RoleKorName != '각본가'
      AND r.RoleKorName != '배우';
END //
DELIMITER ;

CALL getExtrasByMovie('타이타닉');

# 23. 주어진 사람이 '참여'한 영화를 출력하는 저장 프로시저를 작성하세요.
DELIMITER //
CREATE PROCEDURE getMoviesByPerson(IN person_name VARCHAR(50))
BEGIN
    -- 해당 사람의 ID 조회
    SELECT p.PersonID INTO @person_id
    FROM movie AS m
    INNER JOIN appear a ON m.MovieID = a.MovieID
    INNER JOIN role r ON a.RoleID = r.RoleID
    INNER JOIN person p ON a.PersonID = p.PersonID
    WHERE p.KoreanName = person_name
    Limit 1;

    -- 해당 사람이 '참여'한 영화 정보 가져오기
    SELECT m.Title, p.KoreanName, r.RoleKorName
    FROM movie AS m
    INNER JOIN appear a ON m.MovieID = a.MovieID
    INNER JOIN role r ON a.RoleID = r.RoleID
    INNER JOIN person p ON a.PersonID = p.PersonID
    WHERE p.PersonID = @person_id;
END //
DELIMITER ;

CALL getMoviesByPerson('히스 레저');

# 24. 주어진 장르에 대한 제작비, 전체 수익과 수익율을 출력하는 저장 프로시저를 작성하세요.
DELIMITER //
CREATE PROCEDURE getCostByGenre(IN genre_name VARCHAR(30))
BEGIN
    -- 해당 장르의 ID 조회
    SELECT g.GenreID INTO @genre_id
    FROM movie AS m
    INNER JOIN moviegenre mg ON m.MovieID = mg.MovieID
    INNER JOIN genre g ON mg.GenreID = g.GenreID
    WHERE g.GenreKorName = genre_name
    LIMIT 1;

    -- 해당 장르에 대한 제작비, 전체 수익과 수익률 정보 가져오기
    SELECT g.GenreKorName AS '장르명', AVG(m.budget) AS '제작비', AVG(m.BoxOfficeWWGross) AS '전체 수익', AVG(m.BoxOfficeWWGross - m.Budget) / AVG(m.BoxOfficeWWGross) * 100 AS '수익률'
    FROM movie AS m
    INNER JOIN moviegenre mg ON m.MovieID = mg.MovieID
    INNER JOIN genre g ON mg.GenreID = g.GenreID
    WHERE g.GenreID = @genre_id
    GROUP BY g.GenreKorName;
END //
DELIMITER ;

CALL getCostByGenre('판타지');

# <수업하지 않은 내용>
# 25. 저장 프로시저의 파라미터는 출력/입력/입출력 형태의 파라미터를 가질 수 있습니다. 주어진 영화(MovieID)로 출연/참여자 정보를 제외한 모든 정보를 출력 파라미터로 가지고, 실행 결과가 출력 파라미터에 기록되도록 하는 저장 프로시저를 작성하세요.
DELIMITER //
CREATE PROCEDURE getMovieInfo(
    IN movie_id INT,
    OUT title VARCHAR(50),
    OUT korean_title VARCHAR(250),
    OUT plot VARCHAR(4000),
    OUT release_year SMALLINT,
    OUT running_time SMALLINT,
    OUT grade_id SMALLINT,
    OUT gradeInKorea_id SMALLINT,
    OUT poster VARCHAR(200),
    OUT release_dateInKorea DATE,
    OUT boxOfficeWW_gross BIGINT,
    OUT boxOfficeUS_gross BIGINT,
    OUT budget BIGINT,
    OUT original_author VARCHAR(200),
    OUT original_source VARCHAR(400)
)
BEGIN
    -- 영화 정보 조회
    SELECT Title, KoreanTitle, Plot, ReleaseYear, RunningTime, GradeID, GradeInKoreaID, Poster, ReleaseDateInKorea, BoxOfficeWWGross, BoxOfficeUSGross, Budget, OriginalAuthor, OriginalSource
    INTO title, korean_title, plot, release_year, running_time, grade_id, gradeInKorea_id, poster, release_dateInKorea, boxOfficeWW_gross, boxOfficeUS_gross, budget, original_author, original_source
    FROM movie
    WHERE MovieID = movie_id;

    -- 출연/참여자 정보 제외한 모든 정보 출력
    SELECT title, korean_title, plot, release_year, running_time, grade_id, gradeInKorea_id, poster, release_dateInKorea, boxOfficeWW_gross, boxOfficeUS_gross, budget, original_author, original_source;
END //
DELIMITER ;

CALL getMovieInfo(3550, @title, @korean_title, @plot, @release_year, @running_time, @grade_id,
                  @gradeInKorea_id, @poster, @release_dateInKorea, @boxOfficeWW_gross,
                  @boxOfficeUS_gross, @budget, @original_author, @original_source);
