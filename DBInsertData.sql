Insert INTO Users VALUES('IN5', 'Instructor5', 'N');
Insert INTO Users VALUES('AD2', 'Admin2', 'Y');

Insert INTO Stream VALUES ('FSD123', 'Java Full Stack Developer');
Insert INTO Stream VALUES ('DB343', 'Database');

Insert INTO Category VALUES ('FND', 'Foundation');

Insert INTO Class VALUES ('JV123', 'FSD123', 'IN5', TO_DATE('06/06/1994', 'mm/dd/yyyy'), TO_DATE('07/06/1994', 'mm/dd/yyyy'));

Insert INTO Employees VALUES('IM505', 'IceMan', 'IM505@syn.com', 'JV123');

Insert INTO Modules VALUES('10', 'JavaMod1', 'FND', 'FSD123');
Insert INTO Modules VALUES('5', 'OracleMod', 'FND', 'DB343');

Insert INTO Courses VALUES('OR1.3', 'Oracle1.3', '5');

Insert INTO Employees_Take_Modules VALUES('10', 'IM505', 83.00);

commit;