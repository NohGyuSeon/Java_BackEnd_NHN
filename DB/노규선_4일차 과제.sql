USE DatamotionMovieDatabase;

# 1. 영화 '퍼스트 맨'의 제작 연도, 영문 제목, 러닝 타임, 플롯을 출력하세요.
SELECT ReleaseYear, Title, RunningTime, Plot
FROM movie m
WHERE KoreanTitle = '퍼스트 맨';

# 2. 2003년에 개봉한 영화의 한글 제목과 영문 제목을 출력하세요
SELECT KoreanTitle, Title
FROM movie
WHERE ReleaseYear = '2003';

# 3. 영화 '글래디에이터'의 작곡가를 고르세요
SELECT Title, KoreanTitle, RoleName
FROM movie as m
INNER JOIN appear a ON m.MovieID = a.MovieID
INNER JOIN role r ON a.RoleID = r.RoleID
WHERE r.RoleName = 'Composer' AND m.KoreanTitle = '글래디에이터';

# 4. 영화 '매트릭스' 의 감독이 몇명인지 출력하세요
SELECT COUNT(*) AS '감독 수', KoreanTitle
FROM movie as m
INNER JOIN appear a ON m.MovieID = a.MovieID
INNER JOIN role r ON a.RoleID = r.RoleID
WHERE r.RoleKorName = '감독' AND m.KoreanTitle = '매트릭스';

# 5. 감독이 2명 이상인 영화를 출력하세요
SELECT m.MovieID, COUNT(m.MovieID) AS cnt, concat(m.Title) AS title
FROM movie as m
INNER JOIN appear a ON m.MovieID = a.MovieID
INNER JOIN role r ON a.RoleID = r.RoleID
WHERE r.RoleKorName = '감독'
GROUP BY m.MovieID
HAVING cnt >= 2;

# 6. '한스 짐머'가 참여한 영화 중 아카데미를 수상한 영화를 출력하세요
SELECT m.Title
FROM movie as m
INNER JOIN appear a ON m.MovieID = a.MovieID
INNER JOIN person p ON a.PersonID = p.PersonID
INNER JOIN awardinvolve ai ON a.AppearID = ai.AppearID
INNER JOIN winning w ON ai.WinningID = w.WinningID
INNER JOIN awardyear ay ON ai.AwardYearID = ay.AwardYearID
INNER JOIN award aw ON ay.AwardID = aw.AwardID
WHERE p.KoreanName = '한스 짐머' AND w.WinningID = 2;

# 7. 감독이 '제임스 카메론'이고 '아놀드 슈워제네거'가 출연한 영화를 출력하세요
SELECT DISTINCT m.Title
FROM movie as m
INNER JOIN appear a ON m.MovieID = a.MovieID
INNER JOIN person p ON a.PersonID = p.PersonID
INNER JOIN role r ON a.RoleID = r.RoleID
WHERE r.RoleKorName = '배우' AND p.KoreanName = '아놀드 슈워제네거' AND
      m.MovieID IN (SELECT DISTINCT m.MovieID
            FROM movie as m
            INNER JOIN appear a ON m.MovieID = a.MovieID
            INNER JOIN person p ON a.PersonID = p.PersonID
            WHERE p.KoreanName = '제임스 카메론');

# 8. 상영시간이 100분 이상인 영화 중 레오나르도 디카프리오가 출연한 영화를 고르시오
SELECT DISTINCT m.Title
FROM movie as m
INNER JOIN appear a ON m.MovieID = a.MovieID
INNER JOIN person p ON a.PersonID = p.PersonID
WHERE m.RunningTime >= 100 AND p.KoreanName = '레오나르도 디카프리오';오

# 9. 청소년 관람불가 등급의 영화 중 가장 많은 수익을 얻은 영화를 고르시오
SELECT DISTINCT m.Title, MAX(m.BoxOfficeWWGross) as b
FROM movie as m
INNER JOIN gradeinkorea as g ON m.GradeInKoreaID = g.GradeInKoreaID
WHERE g.GradeInKoreaName = '청소년 관람불가'
GROUP BY m.MovieID
ORDER BY b DESC
LIMIT 1;

# 10. 1999년 이전에 제작된 영화의 수익 평균을 고르시오
SELECT DISTINCT AVG(m.BoxOfficeWWGross) AS '수익 평균'
FROM movie AS m
WHERE m.ReleaseYear <= 1999;

# 11. 가장 많은 제작비가 투입된 영화를 감독한 사람은 누구입니까?
SELECT p.KoreanName, r.RoleKorName, m.Budget
FROM movie as m
INNER JOIN appear a ON m.MovieID = a.MovieID
INNER JOIN person p ON a.PersonID = p.PersonID
INNER JOIN role r ON a.RoleID = r.RoleID
WHERE r.RoleKorName = '감독' AND
      m.Budget = (SELECT MAX(Budget)
                  FROM movie);

# 12. 제작한 영화의 제작비 총합이 가장 높은 감독은 누구입니까?
SELECT p.KoreanName, r.RoleKorName, m.Budget
FROM movie as m
INNER JOIN appear a ON m.MovieID = a.MovieID
INNER JOIN person p ON a.PersonID = p.PersonID
INNER JOIN role r ON a.RoleID = r.RoleID
WHERE r.RoleKorName = '감독' AND
      m.Budget = (SELECT MAX(Budget)
                  FROM movie);

SELECT p.KoreanName, r.RoleKorName, IFNULL(m.Budget, 0) AS Budget
FROM movie AS m
         INNER JOIN appear AS a ON m.MovieID = a.MovieID
         INNER JOIN person AS p ON a.PersonID = p.PersonID
         INNER JOIN role AS r ON a.RoleID = r.RoleID
WHERE r.RoleKorName = '감독'
  AND m.Budget = (SELECT MAX(IFNULL(Budget, 0)) FROM movie);

# 13. 출연한 영화의 모든 수익을 합하여, 총 수입이 가장 많은 배우를 출력하세요.
SELECT DISTINCT SUM(m.BoxOfficeWWGross) AS gross, p.KoreanName
FROM movie as m
INNER JOIN appear a ON m.MovieID = a.MovieID
INNER JOIN person p ON a.PersonID = p.PersonID
INNER JOIN role r ON a.RoleID = r.RoleID
WHERE r.RoleKorName = '배우'
GROUP BY p.PersonID
ORDER BY gross DESC;

# 14. 제작비가 가장 적게 투입된 영화의 수익을 고르세요. (제작비가 0인 영화는 제외합니다)
SELECT p.KoreanName, r.RoleKorName, m.Budget
FROM movie as m
INNER JOIN appear a ON m.MovieID = a.MovieID
INNER JOIN person p ON a.PersonID = p.PersonID
INNER JOIN role r ON a.RoleID = r.RoleID
WHERE r.RoleKorName = '감독' AND
      m.Budget = (SELECT Budget AS b
                  FROM movie
                  WHERE Budget > 0
                  ORDER BY b ASC
                  LIMIT 1);

# 15. 제작비가 5000만 달러 이하인 영화의 미국내 평균 수익을 고르세요
SELECT AVG(m.BoxOfficeWWGross) AS '평균 수익'
FROM movie as m
WHERE m.Budget <= 5000;

# 16. 액션 장르 영화의 평균 수익을 집계하세요.
SELECT AVG(m.BoxOfficeWWGross) AS '액션 평균 수익'
FROM movie as m
INNER JOIN moviegenre mg ON m.MovieID = mg.MovieID
INNER JOIN genre g ON mg.GenreID = g.GenreID
WHERE g.GenreKorName = '액션';

# 17. 드라마, 전쟁 장르의 영화를 고르세요.
SELECT *
FROM movie m
INNER JOIN moviegenre mg ON m.MovieID = mg.MovieID
INNER JOIN genre g ON mg.GenreID = g.GenreID
WHERE g.GenreKorName = '드라마' OR '전쟁';

# 18. 톰 행크스가 출연한 영화 중 상영 시간이 가장 긴 영화의 제목, 한글제목, 개봉연도를 출력하세요.
SELECT m.Title, m.KoreanTitle, m.ReleaseYear
FROM movie m
INNER JOIN appear a ON m.MovieID = a.MovieID
INNER JOIN person p ON a.PersonID = p.PersonID
INNER JOIN role r ON a.RoleID = r.RoleID
WHERE r.RoleKorName = '배우'
  AND p.KoreanName = '톰 행크스'
ORDER BY m.RunningTime DESC
LIMIT 1;

# 19. 아카데미 남우주연상을 가장 많이 수상한 배우를 고르시오
SELECT p.KoreanName, COUNT(p.KoreanName) AS cnt
FROM movie as m
INNER JOIN appear a ON m.MovieID = a.MovieID
INNER JOIN person p ON a.PersonID = p.PersonID
INNER JOIN awardinvolve ai ON a.AppearID = ai.AppearID
INNER JOIN winning w ON ai.WinningID = w.WinningID
INNER JOIN sector s ON ai.SectorID = s.SectorID
INNER JOIN awardyear ay ON ai.AwardYearID = ay.AwardYearID
INNER JOIN award aw ON ay.AwardID = aw.AwardID
INNER JOIN role r ON a.RoleID = r.RoleID
WHERE r.RoleKorName = '배우' AND s.SectorKorName = '남우주연상' AND w.WinningID = 2
GROUP BY p.KoreanName
ORDER BY cnt DESC
LIMIT 1;

# 20. 아카데미상을 가장 많이 수상한 영화인을 고르시오 ('수상자 없음'이 이름인 영화인은 제외합니다)
SELECT p.KoreanName, COUNT(p.KoreanName) AS cnt
FROM movie as m
INNER JOIN appear a ON m.MovieID = a.MovieID
INNER JOIN person p ON a.PersonID = p.PersonID
INNER JOIN awardinvolve ai ON a.AppearID = ai.AppearID
INNER JOIN winning w ON ai.WinningID = w.WinningID
INNER JOIN sector s ON ai.SectorID = s.SectorID
INNER JOIN awardyear ay ON ai.AwardYearID = ay.AwardYearID
INNER JOIN award aw ON ay.AwardID = aw.AwardID
INNER JOIN role r ON a.RoleID = r.RoleID
WHERE p.KoreanName != '수상자 없음' AND r.RoleKorName = '배우' AND w.WinOrNot = 'Winner'
GROUP BY p.KoreanName
ORDER BY cnt DESC
LIMIT 1;

# 21. 아카데미 남우주연상을 2번 이상 수상한 배우를 고르시오
SELECT CONCAT(p.KoreanName), COUNT(m.MovieID) AS cnt
    FROM movie as m
    INNER JOIN appear a ON m.MovieID = a.MovieID
    INNER JOIN person p ON a.PersonID = p.PersonID
    INNER JOIN awardinvolve ai ON a.AppearID = ai.AppearID
    INNER JOIN winning w ON ai.WinningID = w.WinningID
    INNER JOIN sector s ON ai.SectorID = s.SectorID
    INNER JOIN role r ON a.RoleID = r.RoleID
    WHERE r.RoleKorName = '배우' AND s.SectorKorName = '남우주연상' AND w.WinOrNot = 'Winner'
    GROUP BY p.PersonID
    HAVING cnt >= 2;

# 23. 아카데미상을 가장 많이 수상한 사람을 고르세요.
SELECT p.KoreanName, COUNT(p.KoreanName) AS cnt
FROM movie as m
INNER JOIN appear a ON m.MovieID = a.MovieID
INNER JOIN person p ON a.PersonID = p.PersonID
INNER JOIN awardinvolve ai ON a.AppearID = ai.AppearID
INNER JOIN winning w ON ai.WinningID = w.WinningID
INNER JOIN sector s ON ai.SectorID = s.SectorID
INNER JOIN awardyear ay ON ai.AwardYearID = ay.AwardYearID
INNER JOIN award aw ON ay.AwardID = aw.AwardID
INNER JOIN role r ON a.RoleID = r.RoleID
WHERE p.KoreanName != '수상자 없음' AND w.WinOrNot = 'Winner'
GROUP BY p.KoreanName
ORDER BY cnt DESC
LIMIT 1;

# 24. 아카데미상에 가장 많이 노미네이트 된 영화를 고르세요.
SELECT m.Title, COUNT(m.MovieID) AS cnt
FROM movie as m
INNER JOIN appear a ON m.MovieID = a.MovieID
INNER JOIN awardinvolve ai ON a.AppearID = ai.AppearID
INNER JOIN winning w ON ai.WinningID = w.WinningID
INNER JOIN awardyear ay ON ai.AwardYearID = ay.AwardYearID
INNER JOIN award aw ON ay.AwardID = aw.AwardID
WHERE w.WinOrNot = 'Nominated'
GROUP BY m.MovieID
ORDER BY cnt DESC
LIMIT 1;

# 25. 가장 많은 영화에 출연한 여배우를 고르세요.
SELECT p.KoreanName, COUNT(p.PersonID) AS cnt
FROM movie as m
INNER JOIN appear a ON m.MovieID = a.MovieID
INNER JOIN person p ON a.PersonID = p.PersonID
INNER JOIN role r ON a.RoleID = r.RoleID
WHERE r.RoleName = 'Actress'
GROUP BY p.PersonID
ORDER BY cnt DESC
LIMIT 1;

# 26. 수익이 가장 높은 영화 TOP 10을 출력하세요.
SELECT m.Title
FROM movie AS m
ORDER BY m.BoxOfficeWWGross DESC
LIMIT 10;

# 27. 수익이 10억불 이상인 영화중 제작비가 1억불 이하인 영화를 고르시오.
SELECT m.Title, m.BoxOfficeWWGross, m.Budget
FROM movie AS m
WHERE m.BoxOfficeWWGross >= 1000000000 AND m.Budget <= 100000000;

# 28. 전쟁 영화를 가장 많이 감독한 사람을 고르세요.
SELECT COUNT(m.MovieID) AS cnt, CONCAT(p.KoreanName)
FROM movie m
INNER JOIN appear a ON m.MovieID = a.MovieID
INNER JOIN role r ON a.RoleID = r.RoleID
INNER JOIN person p ON a.PersonID = p.PersonID
INNER JOIN moviegenre mg ON m.MovieID = mg.MovieID
INNER JOIN genre g ON mg.GenreID = g.GenreID
WHERE g.GenreKorName = '전쟁' AND r.RoleKorName = '감독'
GROUP BY p.PersonID
ORDER BY cnt DESC
LIMIT 1;

# 29. 드라마에 가장 많이 출연한 사람을 고르세요.
SELECT DISTINCT p.KoreanName, COUNT(p.PersonID) AS cnt
FROM movie m
INNER JOIN appear a ON m.MovieID = a.MovieID
INNER JOIN role r ON a.RoleID = r.RoleID
INNER JOIN person p ON a.PersonID = p.PersonID
INNER JOIN moviegenre mg ON m.MovieID = mg.MovieID
INNER JOIN genre g ON mg.GenreID = g.GenreID
WHERE g.GenreKorName = '드라마' AND p.KoreanName != '수상자 없음'
GROUP BY p.PersonID
ORDER BY cnt DESC
LIMIT 1;

# 30. 드라마 장르에 출연했지만 호러 영화에 한번도 출연하지 않은 사람을 고르세요.
SELECT DISTINCT p.KoreanName
FROM movie m
INNER JOIN appear a ON m.MovieID = a.MovieID
INNER JOIN role r ON a.RoleID = r.RoleID
INNER JOIN person p ON a.PersonID = p.PersonID
INNER JOIN moviegenre mg ON m.MovieID = mg.MovieID
INNER JOIN genre g ON mg.GenreID = g.GenreID
WHERE g.GenreKorName = '드라마' AND r.RoleKorName = '배우'
AND p.PersonID NOT IN (
    SELECT DISTINCT p.PersonID
    FROM movie m
    INNER JOIN appear a ON m.MovieID = a.MovieID
    INNER JOIN role r ON a.RoleID = r.RoleID
    INNER JOIN person p ON a.PersonID = p.PersonID
    INNER JOIN moviegenre mg ON m.MovieID = mg.MovieID
    INNER JOIN genre g ON mg.GenreID = g.GenreID
    WHERE g.GenreKorName = '공포' AND r.RoleKorName = '배우'
);

# 31. 아카데미 영화제가 가장 많이 열린 장소는 어디인가요?
SELECT DISTINCT Location, COUNT(AwardYearID) AS cnt
FROM awardyear
GROUP BY Location
ORDER BY cnt DESC;

# 33. 첫 번째 아카데미 영화제가 열린지 올해 기준으로 몇년이 지났나요?
SELECT YEAR(CURDATE()) - YEAR(ay.Date) AS years_passed
FROM movie as m
INNER JOIN appear a ON m.MovieID = a.MovieID
INNER JOIN awardinvolve ai ON a.AppearID = ai.AppearID
INNER JOIN awardyear ay ON ai.AwardYearID = ay.AwardYearID
ORDER BY ay.Date
LIMIT 1;

# 34. 아카데미에 가장 많이 노미네이트된 사람은 누구인가요?
SELECT DISTINCT p.PersonID, p.KoreanName, COUNT(p.PersonID) AS cnt
FROM movie as m
INNER JOIN appear a ON m.MovieID = a.MovieID
INNER JOIN person p ON a.PersonID = p.PersonID
INNER JOIN awardinvolve ai ON a.AppearID = ai.AppearID
INNER JOIN winning w ON ai.WinningID = w.WinningID
INNER JOIN awardyear ay ON ai.AwardYearID = ay.AwardYearID
INNER JOIN award aw ON ay.AwardID = aw.AwardID
WHERE w.WinOrNot = 'Nominated' AND p.KoreanName != '수상자 없음'
GROUP BY p.PersonID
ORDER BY cnt DESC
LIMIT 1;

# 35. 1999년에서 2009년 사이에 남우 주연상을 수상한 배우를 모두 고르세요.
SELECT DISTINCT p.KoreanName, YEAR(ay.Date)
FROM movie AS m
         INNER JOIN appear a ON m.MovieID = a.MovieID
         INNER JOIN person p ON a.PersonID = p.PersonID
         INNER JOIN awardinvolve ai ON a.AppearID = ai.AppearID
         INNER JOIN awardyear ay ON ai.AwardYearID = ay.AwardYearID
         INNER JOIN winning w ON ai.WinningID = w.WinningID
         INNER JOIN sector s ON ai.SectorID = s.SectorID
         INNER JOIN role r ON a.RoleID = r.RoleID
WHERE r.RoleKorName = '배우'
  AND s.SectorKorName = '남우주연상'
  AND w.WinOrNot = 'Winner'
  AND YEAR(ay.Date) BETWEEN 1999 AND 2009;

# 36. 아카데미를 한번 수상한 후, 가장 짧은 기간에 한번 더 수상한 사람을 고르세요.  @@@
SELECT p.KoreanName, MIN(DATEDIFF(ay.Date, ay2.Date)) as days_between
FROM movie AS m
INNER JOIN appear a ON m.MovieID = a.MovieID
INNER JOIN person p ON a.PersonID = p.PersonID
INNER JOIN role r ON a.RoleID = r.RoleID
INNER JOIN awardinvolve ai ON a.AppearID = ai.AppearID
INNER JOIN awardyear ay ON ai.AwardYearID = ay.AwardYearID
INNER JOIN winning w ON ai.WinningID = w.WinningID
INNER JOIN awardinvolve ai2 ON a.AppearID = ai2.AppearID
INNER JOIN awardyear ay2 ON ai2.AwardYearID = ay2.AwardYearID
INNER JOIN winning w2 ON ai2.WinningID = w2.WinningID
WHERE r.RoleKorName = '배우'
  AND w.WinOrNot = 'Winner'
  AND w2.WinOrNot = 'Winner'
  AND ay2.Date < ay.Date
GROUP BY p.KoreanName
HAVING COUNT(DISTINCT CASE WHEN w2.WinningID = w.WinningID AND ay2.Date < ay.Date THEN ay2.Date END) >= 1
ORDER BY days_between ASC
LIMIT 1;

# 37. '제임스 카메론'의 영화에 출연한 모든 배우를 고르세요
SELECT DISTINCT p.KoreanName, r.RoleKorName
FROM movie AS m
         INNER JOIN appear a ON m.MovieID = a.MovieID
         INNER JOIN person p ON a.PersonID = p.PersonID
         INNER JOIN role r ON a.RoleID = r.RoleID
WHERE r.RoleKorName = '배우'
  AND m.MovieID IN (SELECT DISTINCT m.MovieID
                    FROM movie AS m
                             INNER JOIN appear a ON m.MovieID = a.MovieID
                             INNER JOIN person p ON a.PersonID = p.PersonID
                    WHERE p.KoreanName = '제임스 카메론');

SELECT p.KoreanName, r.RoleKorName
FROM movie AS m
         INNER JOIN appear a ON m.MovieID = a.MovieID
         INNER JOIN person p ON a.PersonID = p.PersonID
         INNER JOIN role r ON a.RoleID = r.RoleID
         INNER JOIN (
             SELECT DISTINCT m.MovieID
             FROM movie AS m
                      INNER JOIN appear a ON m.MovieID = a.MovieID
                      INNER JOIN person p ON a.PersonID = p.PersonID
             WHERE p.KoreanName = '제임스 카메론'
         ) AS sub ON m.MovieID = sub.MovieID
WHERE r.RoleKorName = '배우';

# 38. '월드 디즈니'가 수상한 아카데미상의 종류를 고르세요
SELECT s.SectorKorName, p.KoreanName
    FROM movie as m
    INNER JOIN appear a ON m.MovieID = a.MovieID
    INNER JOIN person p ON a.PersonID = p.PersonID
    INNER JOIN awardinvolve ai ON a.AppearID = ai.AppearID
    INNER JOIN winning w ON ai.WinningID = w.WinningID
    INNER JOIN sector s ON ai.SectorID = s.SectorID
    WHERE p.KoreanName = '월트 디즈니' AND w.WinOrNot = 'Winner';

# 39. 장르별 영화의 제작비 평균을 구하세요.
SELECT DISTINCT AVG(m.BoxOfficeWWGross) AS '평균 수익', g.GenreKorName
FROM movie as m
INNER JOIN moviegenre mg ON m.MovieID = mg.MovieID
INNER JOIN genre g ON mg.GenreID = g.GenreID
GROUP BY g.GenreKorName;

# 40. 장르별 영화의 제작비 대비 수익률을 구하세요.
SELECT g.GenreKorName, AVG(m.BoxOfficeWWGross - m.Budget) / AVG(m.BoxOfficeWWGross) * 100 AS margin
FROM movie AS m
INNER JOIN moviegenre AS mg ON m.MovieID = mg.MovieID
INNER JOIN genre AS g ON mg.GenreID = g.GenreID
GROUP BY g.GenreKorName;
