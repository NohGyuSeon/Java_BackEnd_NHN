CREATE TABLE Pilot1 (
PilotNo INTEGER,
PilotName NVARCHAR(12),
Grade INTEGER, 
Age INTEGER
);

DESC Pilot1;
SELECT * FROM Pilot1;

CREATE TABLE AirCraft (
AirCrafttNo INTEGER,
AirCraftName NVARCHAR(20),
AirCraftModel NVARCHAR(12)
);

DESC AirCraft ;
SELECT * FROM AirCraft;

CREATE TABLE Flight (
FlightNo INTEGER,
FlightNumber INTEGER,
FlightDate Date
);

DESC Flight;
SELECT * FROM Flight;




