Insert INTO Users VALUES('IN5', 'Instructor5', 'N');
Insert INTO Users VALUES('AD2', 'Admin2', 'Y');

Insert INTO Stream VALUES ('FSD123');
Insert INTO Stream VALUES ('DB343');

Insert INTO Class VALUES ('JV123', 'FSD123', 'IN5');

Insert INTO Employees VALUES('IM505', 'IceMan', 'IM505@syn.com', 'JV123');

Insert INTO Type VALUES('Java', 'FSD123');
Insert INTO Type VALUES('DB', 'DB343');

Insert INTO Modules VALUES('10', 'JavaMod1', 'Java');
Insert INTO Modules VALUES('5', 'OracleMod', 'DB');

Insert INTO Courses VALUES('OR1.3', 'Oracle1.3', '5');

Insert INTO Employees_Take_Modules VALUES('10', 'IM505', );

Insert INTO Class_Has_Modules VALUES('JV123', '10');