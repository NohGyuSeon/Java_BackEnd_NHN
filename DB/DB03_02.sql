show databases;

CREATE DATABASE Module06;

show databases;

SELECT database();

use Module06;

SELECT database();

CREATE TABLE Passenger (
    PassengerNo int, 
	PassengerName NVARCHAR(10) NOT NULL,
    GRADE int CHECK(GRADE >= 1 AND GRADE <= 10) DEFAULT 1,
    AGE int NULL,
	CONSTRAINT PRIMARY KEY (PassengerNo)
);

DESC Passenger;

INSERT INTO Passenger 
VALUES(1, '홍길동', 7, 44);

INSERT INTO Passenger (PassengerNo, PassengerName, Age) 
VALUES (2, '이순신', 44);

SELECT * FROM Passenger;

INSERT INTO Passenger (PassengerNo, Grade, Age) VALUES (3, 7, 40); -- 도메인 무결성 위반

INSERT INTO Passenger (PassengerNo, PassengerName, Grade) VALUES(3, '안중근', 7);

SELECT * FROM Passenger;

INSERT INTO Passenger
VALUES (4, '김영랑', 9, 54);

SELECT * FROM Passenger;

INSERT INTO Passenger VALUES (4, '김소월',9, 45); -- 개체 무결성 위반

INSERT INTO Passenger 
VALUES (5, '김소월', 9, 45); 

INSERT INTO Passenger 
VALUES (6, '윤동주', 10, 26);

INSERT INTO Passenger 
VALUES (7, '천상병', 9, 55);

SELECT * FROM Passenger;

SELECT database();

show tables;

CREATE TABLE AirCraft (
    Aircraftno int,
    KindofAircraft varchar(20),
    Airline varchar(10)
);

show tables;

desc Aircraft;

ALTER TABLE Aircraft 
ADD CONSTRAINT pk_Aircraft PRIMARY KEY(Aircraft);

desc Aircraft;

ALTER TABLE Aircraft ADD CONSTRAINT pk_Aircraft PRIMARY KEY(AircraftNo);

ALTER TABLE Aircraft 
MODIFY COLUMN KindOfAircraft varchar(20) NOT NULL;

desc Aircraft;

INSERT INTO Aircraft
VALUES (101, 'Boeing 747', '대한항공');

INSERT INTO Aircraft
VALUES (102, 'Boeing 727', '대한항공');

INSERT INTO Aircraft
VALUES (103, 'Airbus A380', '아시아나항공');

INSERT INTO Aircraft
VALUES (104, 'Airbus A300', '대한항공');

INSERT INTO Aircraft
VALUES (105, 'Boeing 747-800', '제주항공');

SELECT * FROM Aircraft;

SELECT database();

ALTER TABLE Aircraft CHANGE Aircraftno AircraftNo int;

CREATE TABLE Flight (
    FlightNo int,
    AircraftNo int, 
    Deparetures nvarchar(10) NOT NULL,
	Arrival nvarchar(10) NOT NULL, 
    Price int DEFAULT 0,
    FlightDate datetime NOT NULL, 
    CONSTRAINT pk_Flight PRIMARY KEY(FlightNo),
    CONSTRAINT fk_Flight_Aircraft FOREIGN KEY(AircraftNo) REFERENCES Aircraft(AircraftNo)
    );
    
show tables;
    
desc Flight;

INSERT INTO Flight
VALUES (1, 101, '인천', '샌프란시스코', 1230000, '2022-10-23 10:20');

SELECT * FROM Flight;
    
INSERT INTO Flight
VALUES (2, 106, '샌프란시스코', '인천', 1230000, '2022-10-23 10:20'); -- 참조 무결성 위반

INSERT INTO Flight
VALUES (2, 101, '샌프란시스코', '인천', 1320000, '2022-10-26 13:00');

INSERT INTO Flight
VALUES (3, 105, '김포', '제주', 720000, '2022-11-23 09:00');

INSERT INTO Flight
VALUES (4, 105, '김포', '김해', 680000, '2022-11-12 17:30');

INSERT INTO Flight
VALUES (5, 103, '인천', '프랑크푸르트', 1480000, '2022-12-01 18:00');

INSERT INTO Flight
VALUES (6, 103, '프랑크푸르트', '인천', 1560000, '2022-12-10 10:00');

INSERT INTO Flight
VALUES (7, 104, '김해', '김포', 70000, '2022-11-13 11:00');

INSERT INTO Flight
VALUES (8, 101, '인천', '샌프란시스코', 1230000, '2022-11-15 10:00');

SELECT * FROM Flight;

SELECT database();

CREATE TABLE Reservation (
    PassengerNo int,
    FlightNo int,
    ReservedDate date NOT NULL
    );
    
show tables;

ALTER TABLE Reservation ADD CONSTRAINT pk_Reservation PRIMARY KEY (PassengerNo, FlightNo);

DESC Reservation;

ALTER TABLE Reservation ADD CONSTRAINT fk_reservation_passenger FOREIGN KEY(PassengerNo) REFERENCES Passenger(PassengerNo);

ALTER TABLE Reservation ADD CONSTRAINT fk_reservation_flight FOREIGN KEY(FlightNo) REFERENCES Flight(FlightNo);

SELECT CONSTRAINT_NAME, CONSTRAINT_TYPE, ENFORCED FROM
information_schema.table_constraints WHERE table_name = 'Reservation';

INSERT INTO Reservation VALUES (1, 4, '2022-10-22');

INSERT INTO Reservation VALUES (3, 1, '2022-10-11');

INSERT INTO Reservation VALUES (4, 7, '2022-10-11');

INSERT INTO Reservation VALUES (6, 7, '2022-10-21');

INSERT INTO Reservation VALUES (2, 1, '2022-10-11');

INSERT INTO Reservation VALUES (2, 2, '2022-10-11');

INSERT INTO Reservation VALUES (7, 3, '2022-09-11');

INSERT INTO Reservation VALUES (1, 3, '2022-11-09');

SELECT * FROM Reservation;

USE Module06;

show tables;

SELECT KindOfAircraft, Airline, FlightDate
FROM Aircraft AS a LEFT OUTER JOIN Flight AS f On a.AircraftNo = f.aircraftNo;

-- 대한항공을 예약한 모든 승객의 이름과 나이를 구하라
SELECT p.PassengerNo, p.PassengerName
FROM Passenger 
	AS p Inner JOIN Reservation AS r ON p.PassengerNo = r.PassengerNo
		Inner JOIN Flight AS f ON f.FlightNo = r.FlightNo
		Inner JOIN Aircraft AS a ON a.AircraftNo = f.AircraftNo
WHERE a.Airline = '대한항공';

SELECT PassengerName, Age
FROM Aircraft A, Reservation r, Passenger p, Flight F
WHERE 
	A.AircraftNo = F.AircraftNo AND
	F.FlightNo = R.FlightNo AND
    P.PassengerNo = R.PassengerNo AND
    A.Airline = '대한항공';

-- 나이가 40을 넘는 모든 승객의 정보를 구하라
SELECT *
FROM Passenger
WHERE age > 40;

EXPLAIN
SELECT * FROM Passenger WHERE Grade > 5
UNION
SELECT * FROM Passenger WHERE Age > 40;

EXPLAIN
SELECT * FROM Passenger
WHERE
	Grade > 5
	OR
    Age > 40;

-- 번호가 101인 비행기의 운항 일자를 구하라
SELECT FlightDate
FROM Aircraft AS A INNER JOIN Flight AS F ON A.AircraftNo = F.AircraftNo
WHERE A.AircraftNo = 101;
    
-- 인천에서 출발하는 비행기와 제주에 도착하는 비행기의 비행기 번호와 항공사를 구하라
SELECT DISTINCT tempFlight.FlightNo, A.AircraftNo
FROM (SELECT * FROM Flight WHERE Deparetures = '인천' UNION SELECT * FROM Flight WHERE Arrival = '제주') AS tempFlight
INNER JOIN Aircraft AS A ON tempFlight.AircraftNo = A.AircraftNo;

-- 대한항공으로 운행되는 항공기의 정보를 구하라

-- JOIN 연산
SELECT FlightNo, Deparetures, Arrival, Price
FROM Flight AS f INNER JOIN Aircraft AS a ON f.AircraftNo = a.AircraftNo
WHERE a.Airline = '대한항공';

SELECT FlightNo, Deparetures, Arrival, Price
FROM Flight AS f INNER JOIN Aircraft AS a On f.AircraftNo = a.AircraftNo
WHERE a.Airline = '대한항공';

SELECT FlightNo, Deparetures, Arrival, Price
FROM Flight As f INNER JOIN Aircraft AS a ON f.AircraftNo = a.AircraftNo
WHERE a.Airline = '대한항공';

-- 서브 쿼리 (이중 루프 수행 전략)
SELECT FlightNo, Deparetures, Arrival, Price
FROM Flight
WHERE AircraftNo IN (
	SELECT AircraftNo
    FROM Aircraft
    WHERE Airline = '대한항공'
);

-- 인천에서 출발하는 비행기의 종류를 구하라 
SELECT KindOfAircraft
FROM Aircraft
WHERE AircraftNo IN (
	SELECT AircraftNo
    FROM Flight
    WHERE Deparetures = '인천'
);

-- 샌프란시스코에 도착하는 비행기를 예약한 승객의 이름을 구하라
SELECT PassengerName
FROM Passenger
WHERE PassengerNo IN (
	SELECT PassengerNo
    FROM Reservation
    WHERE FlightNo IN (
		SELECT FlightNo
        FROM Flight
        WHERE Arrival = '샌프란시스코'
	)
);

-- 인천에서 출발하고 샌프란시스코에 도착하는 항공기의 종류를 구하시오

-- JOIN 연산
SELECT DISTINCT KindOfAircraft
FROM Aircraft AS a INNER JOIN Flight AS f ON a.AircraftNo = f.AircraftNo
WHERE Deparetures = '인천' AND Arrival = '샌프란시스코';

-- 인라인 뷰 서브 쿼리
SELECT DISTINCT KindOfAircraft
FROM Aircraft AS a,
	(SELECT * FROM Flight WHERE Deparetures = '인천' AND Arrival = '샌프란시스코') AS F
WHERE
	A.AircraftNo = F.AircraftNo;

-- 항공 편명의 출발지와 도착지, 운영 항공사를 구하시오

-- JOIN 연산
SELECT FlightNo, Deparetures, Arrival, Airline
FROM Flight as f INNER JOIN Aircraft AS a ON f.AircraftNo = a.AircraftNo;

-- 스칼라 서브 쿼리
SELECT FlightNo, Deparetures, Arrival, (SELECT Airline FROM Aircraft AS a WHERE a.AircraftNo = f.AircraftNo) AS Airline
FROM Flight AS f;


-- 같은 등급의 평균 나이보다 많은 나이의 승객의 이름과 나이를 구하시오



-- 각 등급별로 가장 적은 나이를 구하라
SELECT MIN(Age)
FROM Passenger
GROUP BY Grade;

--
SELECT PassengerName
FROM Passenger
ORDER BY Age DESC 
LIMIT 1;

--
SELECT PassengerName 
FROM Passenger
WHERE Age = (SELECT MAX(Age) FROM Passenger);

--
SELECT COUNT(Grade)
FROM Passenger
GROUP BY grade;

--
SELECT Grade, COUNT(Grade)
FROM Passenger
GROUP BY grade;

SELECT Grade, Min(Age)
FROM Passenger























    
    
