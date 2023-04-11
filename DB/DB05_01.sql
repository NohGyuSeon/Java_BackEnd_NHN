show databases;

use module06;

show tables;

CREATE VIEW ReserveInfo
AS
	SELECT p.PassengerNo, p.Grade, p.Age, r.ReservedDate, f.FlightNO
	FROM Passenger AS p INNER JOIN Reservation AS r ON p.PassengerNo = r.PassengerNO
		INNER JOIN Flight AS f ON f.flightNo = r.flightNo;
        
show tables;

SELECT *
FROM ReserveInfo;

DELIMITER $$
CREATE PROCEDURE GetReserveInfoForPassenger2
(
	passenger int
)
BEGIN
	SELECT p.PassengerNo, p.Grade, p.Age, r.ReservedDate, f.FlightNO
	FROM Passenger AS p 
    INNER JOIN Reservation AS r ON p.PassengerNo = r.PassengerNO
	INNER JOIN Flight AS f ON f.flightNo = r.flightNo
	WHERE p.PassengerNo = passenger;
END $$
DELIMITER ;

CALL GetReserveInfoForPassenger2(1);

  