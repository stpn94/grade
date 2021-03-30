insert into ban
values
(1,'A01'),
(2,'A02');



insert into subject 
values
(4,'국어'),
(5,'영어'),
(6,'수학'),
(7,'사회'),
(8,'과학');

insert into student
values
(2001,'박재선',1),
(2002,'한동성',1),
(2003,'정정일',1),
(2004,'정명훈',1),
(2005,'임정만',1),
(2006,'임성준',1),
(2007,'윤석수',1),
(2008,'이준민',1),
(2009,'이강길',1),
(2010,'박철호',1),
(2011,'여재일',1),
(2012,'박상엽',1),
(2013,'간효상',1),
(2014,'현동훈',1),
(2015,'진인우',1),
(2016,'박운승',2),
(2017,'김윤재',2),
(2018,'황보동명',2),
(2019,'사대호',2),
(2020,'박동수',2),
(2021,'안추환',2),
(2022,'정문식',2),
(2023,'윤대건',2),
(2024,'양우준',2),
(2025,'서동윤',2),
(2026,'송부길',2),
(2027,'박영우',2),
(2028,'신우석',2),
(2029,'이보민',2),
(2030,'조정우',2);


insert into score
values
(2001,4,72 ),
(2002,4,83 ),
(2003,4,88 ),
(2004,4,90 ),
(2005,4,78 ),
(2006,4,77 ),
(2007,4,71 ),
(2008,4,95 ),
(2009,4,97 ),
(2010,4,95 ),
(2011,4,96 ),
(2012,4,89 ),
(2013,4,73 ),
(2014,4,87 ),
(2015,4,94 ),
(2016,4,84 ),
(2017,4,77 ),
(2018,4,81 ),
(2019,4,71 ),
(2020,4,86 ),
(2021,4,98 ),
(2022,4,92 ),
(2023,4,87 ),
(2024,4,95 ),
(2025,4,95 ),
(2026,4,77 ),
(2027,4,84 ),
(2028,4,70 ),
(2029,4,100),
(2030,4,74 );


insert into score
values
(2001,5,73),
(2002,5,72),
(2003,5,72),
(2004,5,70),
(2005,5,95),
(2006,5,95),
(2007,5,72),
(2008,5,94),
(2009,5,78),
(2010,5,78),
(2011,5,72),
(2012,5,93),
(2013,5,82),
(2014,5,90),
(2015,5,75),
(2016,5,97),
(2017,5,83),
(2018,5,90),
(2019,5,83),
(2020,5,98),
(2021,5,97),
(2022,5,80),
(2023,5,77),
(2024,5,84),
(2025,5,96),
(2026,5,80),
(2027,5,74),
(2028,5,78),
(2029,5,76),
(2030,5,78);

insert into score
values
(2001,6,90 ),
(2002,6,83 ),
(2003,6,82 ),
(2004,6,82 ),
(2005,6,79 ),
(2006,6,87 ),
(2007,6,92 ),
(2008,6,93 ),
(2009,6,78 ),
(2010,6,80 ),
(2011,6,75 ),
(2012,6,100),
(2013,6,95 ),
(2014,6,92 ),
(2015,6,76 ),
(2016,6,88 ),
(2017,6,70 ),
(2018,6,74 ),
(2019,6,79 ),
(2020,6,92 ),
(2021,6,93 ),
(2022,6,75 ),
(2023,6,85 ),
(2024,6,73 ),
(2025,6,98 ),
(2026,6,78 ),
(2027,6,95 ),
(2028,6,77 ),
(2029,6,96 ),
(2030,6,93 );

insert into score
values
(2001,7,72 ),
(2002,7,90 ),
(2003,7,88 ),
(2004,7,93 ),
(2005,7,79 ),
(2006,7,81 ),
(2007,7,91 ),
(2008,7,88 ),
(2009,7,90 ),
(2010,7,92 ),
(2011,7,81 ),
(2012,7,88 ),
(2013,7,76 ),
(2014,7,96 ),
(2015,7,97 ),
(2016,7,87 ),
(2017,7,98 ),
(2018,7,73 ),
(2019,7,99 ),
(2020,7,81 ),
(2021,7,90 ),
(2022,7,81 ),
(2023,7,78 ),
(2024,7,76 ),
(2025,7,100),
(2026,7,99 ),
(2027,7,76 ),
(2028,7,97 ),
(2029,7,75 ),
(2030,7,83 );

insert into score
values
(2001,8,84 ),
(2002,8,91 ),
(2003,8,99 ),
(2004,8,98 ),
(2005,8,97 ),
(2006,8,85 ),
(2007,8,96 ),
(2008,8,100),
(2009,8,73 ),
(2010,8,72 ),
(2011,8,81 ),
(2012,8,83 ),
(2013,8,90 ),
(2014,8,73 ),
(2015,8,75 ),
(2016,8,85 ),
(2017,8,88 ),
(2018,8,73 ),
(2019,8,83 ),
(2020,8,73 ),
(2021,8,71 ),
(2022,8,100),
(2023,8,78 ),
(2024,8,84 ),
(2025,8,77 ),
(2026,8,82 ),
(2027,8,96 ),
(2028,8,71 ),
(2029,8,87 ),
(2030,8,73 );

delete 
from std_detail
where stdNo = 2001;

insert into std_detail
values
(2001,null ,1,19940817),
(2002,null ,1,19940425),
(2003,null ,1,19940525),
(2004,null ,1,19940421),
(2005,null ,1,19940422),
(2006,null ,1,19940325),
(2007,null ,1,19940125),
(2008,null ,1,19940225),
(2009,null ,1,19940325),
(2010,null ,1,19940525),
(2011,null ,1,19940625),
(2012,null ,1,19940725),
(2013,null ,1,19940825),
(2014,null ,1,19940925),
(2015,null ,1,19941225),
(2016,null ,1,19941025),
(2017,null ,1,19941125),
(2018,null ,1,19940221),
(2019,null ,1,19940422),
(2020,null ,2,19940423),
(2021,null ,2,19940405),
(2022,null ,2,19940401),
(2023,null ,2,19940408),
(2024,null ,2,19940406),
(2025,null ,2,19940408),
(2026,null ,2,19940409),
(2027,null ,2,19940422),
(2028,null ,2,19940411),
(2029,null ,2,19940424),
(2030,null ,2,19940603);