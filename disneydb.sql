drop table passenger cascade CONSTRAINTS;
drop table cabin cascade CONSTRAINTS;
drop table passengerdining cascade CONSTRAINTS;
drop table manager cascade CONSTRAINTS;
drop table crew cascade CONSTRAINTS;
drop table managecrew cascade CONSTRAINTS;
drop table cleaningschedule cascade CONSTRAINTS;
drop table entertainmentschedule cascade CONSTRAINTS;
drop table entertainmentschedulecontent cascade CONSTRAINTS;
drop table entertainment cascade CONSTRAINTS;
drop table tour cascade CONSTRAINTS;
drop table stop cascade CONSTRAINTS;
drop table show cascade CONSTRAINTS;
drop table fitnesscentre cascade CONSTRAINTS;
drop table restaurant cascade CONSTRAINTS;
drop table dining cascade CONSTRAINTS;
drop table menu cascade CONSTRAINTS;
drop table diningmenu cascade CONSTRAINTS;
drop table schedule cascade CONSTRAINTS;
drop table schedulecontent cascade CONSTRAINTS;


create table entertainment
	(eid char(8) not null,
	edate date null,
	en_stime interval day(0) to second null,
	en_etime interval day(0) to second null,
	eloc varchar(30) not null,
	ename varchar(30) not null,
	primary key (eid));

grant select on entertainment to public;

create table cabin
	(cid char(3) not null,
	cabin_type varchar(20) null,
	cabin_loc varchar(10) not null,
	capacity int not null,
	csid char(8) null,
	primary key (cid));

grant select on cabin to public;

create table manager
	(man_id char(6) not null,
	password char(6) not null,
	mname varchar(20) not null,
	department varchar(60) not null,
	cid char(3) not null,
	primary key (man_id),
	foreign key (cid) references cabin);

grant select on manager to public;

create table cleaningschedule
	(csid char(8) not null,
	cs_stime interval day(0) to second not null,
	cs_etime interval day(0) to second not null,
	man_id char(6) not null,
	primary key (csid),
	foreign key (man_id) references manager);

grant select on cleaningschedule to public;

create table entertainmentschedule 
	(esid char(8) not null,
	man_id char(5) not null,
	primary key (esid));

grant select on entertainmentschedule to public;

create table entertainmentschedulecontent 
	(esid char(8) not null,
	eid char(8) not null,
	es_stime interval day(0) to second not null,
	es_etime interval day(0) to second not null,
	primary key (esid, eid),
	foreign key (esid) references entertainmentschedule,
	foreign key (eid) references entertainment);

grant select on entertainmentschedulecontent to public;

create table crew 
	(crew_id char(5) not null,
	password char(6) not null,
	cname varchar(20) not null,
	department varchar(60) not null,
	cid char(3) not null,
	csid char(8) null,
	esid char(8) null,
	primary key (crew_id));

grant select on crew to public;

create table managecrew
	(man_id char(6) not null,
	crew_id char(5) not null,
	primary key (man_id, crew_id),
	foreign key (man_id) references manager,
	foreign key (crew_id) references crew);

grant select on managecrew to public;

create table passenger
	(pid char(5) not null,
	pname varchar(20) not null,
	age smallint not null,
	password varchar(6) not null,
	cid char(3) not null,
	primary key (pid),
	foreign key (cid) references cabin,
	constraint valid_age 
		check (age >= 0 and age <= 100));

grant select on passenger to public;

create table schedule
	(sid char(8) not null,
	pid char(5) not null,
	primary key (sid),
	foreign key (pid) references passenger ON DELETE CASCADE);

grant select on schedule to public;

create table schedulecontent
	(sid char(8) not null,
	eid char(8) not null,
	sstime date not null,
	setime date not null,
	primary key (sid, eid),
	foreign key (sid) references schedule,
	foreign key (eid) references entertainment);

grant select on schedulecontent to public;

create table stop 
	(sname varchar(25) not null,
	primary key (sname));

grant select on stop to public; 

create table tour
	(eid char(8) not null,
	sname varchar(25) not null,
	primary key (eid),
	foreign key (eid) references entertainment,
	foreign key (sname) references stop ON DELETE CASCADE);

grant select on tour to public;

create table show 
	(eid char(8) not null,
	primary key (eid),
	foreign key (eid) references entertainment);

grant select on show to public;

create table fitnesscentre
	(eid char(8) not null,
	type char(10) not null,
	primary key (eid),
	foreign key (eid) references entertainment);

grant select on fitnesscentre to public;

create table restaurant 
	(eid char(8) not null,
	type char(20) null,
	seats int not null,
	primary key (eid),
	foreign key (eid) references entertainment);

grant select on restaurant to public;

create table dining
	(diningid char(8) not null,
	availability char check (availability in (0,1)),
	dining_stime date not null,
	dining_etime date not null,
	eid char(8) not null,
	primary key (diningid),
	foreign key (eid) references restaurant ON DELETE CASCADE);

grant select on dining to public;

create table menu
	(menu_id char(2) not null,
	menu_type char(10) not null,
	menu_name char(40) not null,
	primary key (menu_id));

grant select on menu to public;

create table diningmenu
	(diningid char(8) not null,
	menu_id char(2) not null,
	primary key (diningid, menu_id),
	foreign key (diningid) references dining ON DELETE CASCADE,
	foreign key (menu_id) references menu);

grant select on diningmenu to public;

create table passengerdining
	(pid char(5) not null,
	diningid char(8) not null,
	primary key (pid, diningid),
	foreign key (pid) references passenger,
	foreign key (diningid) references dining);

grant select on passengerdining to public;





insert into entertainment
	values('100-7067', null, interval '0 08:00:00' day(0) to second, interval '0 21:00:00' day(0) to second, 'Lower Deck', 'Disney Store');
insert into entertainment
	values('150-6666', null, interval '0 10:00:00' day(0) to second, interval '0 22:30:00' day(0) to second, 'Main Rotational Dining Hall', 'Royal Court');
insert into entertainment
	values('133-3260', null, null, null, 'Main Rotational Dining Hall', 'Kisha Poppo');
insert into entertainment
	values('986-3333', '2017-06-15', interval '0 13:30:00' day(0) to second, interval '0 15:00:00' day(0) to second, 'Gate 4', 'Beach Tour');
insert into entertainment
	values('300-7777', '2017-06-10', interval '0 19:00:00' day(0) to second, interval '0 20:00:00' day(0) to second, 'Main Deck', 'Beauty and The Beast');
insert into entertainment
	values('500-6541', null, interval '0 09:00:00' day(0) to second, interval '0 00:30:00' day(0) to second, 'Main Deck', 'Big Waves');
insert into entertainment
	values('500-7850', null, interval '0 07:30:00' day(0) to second, interval '0 11:00:00' day(0) to second, 'Upper Deck', 'Total Eclipse');
insert into entertainment
	values('300-0613', '2017-06-13', interval '0 19:30:00' day(0) to second, interval '0 20:00:00' day(0) to second, 'Main Deck', 'The Golden Mickeys');
insert into entertainment
	values('300-0614', '2017-06-14', interval '0 19:00:00' day(0) to second, interval '0 20:00:00' day(0) to second, 'Main Deck', 'An Unforgettable Journey');

insert into cabin
	values('003', 'crew', 'Deck 0', 4, null);
insert into cabin
	values('010', 'crew', 'Deck 0', 6, null);
insert into cabin
	values('301', 'oceanview', 'Deck 3', 2, '710-4555');
insert into cabin
	values('244', 'concierge', 'Deck 2', 4, '710-3466');
insert into cabin
	values('007', 'crew', 'Deck 0', 6, null);

insert into manager
	values('50000', 'g0b0b0', 'Carly', 'show', '003');
insert into manager
	values('30000', 'gokee8', 'Samir', 'cabin', '010');
insert into manager
	values('40000', 'njekrn', 'Yiyun', 'food', '003');
insert into manager
  values('60000', 'hjdwjk', 'James', 'shop', '010');
insert into manager
  values('70000', 'boohoo', 'Boo', 'gym', '010');
insert into manager
  values('80000', '35g644', 'Cleo', 'pool', '003');

insert into cleaningschedule
	values('710-4555', interval '0 10:25:00' day(0) to second, interval '0 15:30:00' day(0) to second, '30000');
insert into cleaningschedule
	values('710-3466', interval '0 10:00:00' day(0) to second, interval '0 14:30:00' day(0) to second, '30000');

insert into entertainmentschedule
	values('230-8888', '40000');
insert into entertainmentschedule
	values('230-5555', '40000');
insert into entertainmentschedule
	values('310-2333', '50000');
insert into entertainmentschedule
	values('720-6854', '70000');
insert into entertainmentschedule
	values('710-2311', '70000');
insert into entertainmentschedule
	values('310-2300', '50000');

insert into entertainmentschedulecontent
	values('230-8888', '150-6666', interval '0 08:00:00' day(0) to second, interval '0 11:30:00' day(0) to second);
insert into entertainmentschedulecontent
	values('230-8888', '133-3260', interval '0 14:00:00' day(0) to second, interval '0 21:00:00' day(0) to second);
insert into entertainmentschedulecontent
	values('230-5555', '150-6666', interval '0 09:45:00' day(0) to second, interval '0 11:00:00' day(0) to second);
insert into entertainmentschedulecontent
	values('230-5555', '133-3260', interval '0 18:00:00' day(0) to second, interval '0 20:00:00' day(0) to second);
insert into entertainmentschedulecontent
	values('310-2333', '300-7777', interval '0 18:00:00' day(0) to second, interval '0 20:00:00' day(0) to second);
insert into entertainmentschedulecontent
	values('720-6854', '500-7850', interval '0 10:30:00' day(0) to second, interval '0 16:30:00' day(0) to second);
insert into entertainmentschedulecontent
	values('710-2311', '500-6541', interval '0 17:00:00' day(0) to second, interval '0 00:40:00' day(0) to second);
insert into entertainmentschedulecontent
	values('310-2300', '300-0614', interval '0 18:30:00' day(0) to second, interval '0 20:00:00' day(0) to second);
insert into entertainmentschedulecontent
	values('310-2333', '300-0614', interval '0 18:30:00' day(0) to second, interval '0 20:00:00' day(0) to second);

insert into crew
	values('40069', 'palace', 'Tiana', 'food', '007', null, '230-8888');
insert into crew
	values('31105', '3g89he', 'Abhi', 'cabin', '010', '710-3466', null);
insert into crew 
	values('42396', 'dhuike', 'Simon', 'food', '010', null, '230-5555');
insert into crew
	values('32877', 'cincin', 'Cinderella', 'cabin', '007', '710-4555', null);
insert into crew
	values('50065', 'oceano', 'Ariel', 'show', '007', null, '310-2333');
insert into crew
  values('50166', 'woooof', 'Goofy', 'show', '007', null, '310-2300');
insert into crew
  values('60088', 'ppappa', 'Snow', 'show', '003', null, null);
insert into crew
  values('70143', '563hue', 'Pete', 'gym', '010', null, '720-6854');
insert into crew
  values('32567', 'chichi', 'Chip', 'cabin', '007', null, null);
insert into crew
  values('81123', '94h670', 'Nemo', 'pool', '007', null, '710-2311');

insert into managecrew
	values('50000', '50065');
insert into managecrew
	values('40000', '40069');
insert into managecrew
	values('40000', '42396');
insert into managecrew
	values('30000', '31105');
insert into managecrew
	values('30000', '32877');
insert into managecrew
  values('50000', '50166');
insert into managecrew
  values('60000', '60088');
insert into managecrew
  values('70000', '70143');
insert into managecrew
  values('30000', '32567');
insert into managecrew
  values('80000', '81123');

insert into passenger
	values('ua301', 'Micky', 19, 'notche', '301');
insert into passenger
	values('ub301','Minnie', 18, 'wonidd', '301');
insert into passenger
	values('ma244', 'Tiger Lily', 30, 'indell', '244');
insert into passenger
	values('mb244','Tinker Bell', 45, 'tinket', '244');
insert into passenger
	values('mc244', 'Peter', 20, 'frvyng', '244');

insert into schedule
	values('301-1001', 'ua301');
insert into schedule
	values('244-3001', 'mc244');
insert into schedule
	values('244-1002', 'ma244');
insert into schedule
	values('301-2030', 'ub301');

insert into schedulecontent
	values('301-1001', '500-6541', to_date('2017-06-12 13:00:00', 'YYYY-MM-DD HH24:MI:SS'), to_date('2017-06-12 15:00:00', 'YYYY-MM-DD HH24:MI:SS'));
insert into schedulecontent
	values('244-3001', '986-3333', to_date('2017-06-15 13:30:00', 'YYYY-MM-DD HH24:MI:SS'), to_date('2017-06-15 15:00:00', 'YYYY-MM-DD HH24:MI:SS'));
insert into schedulecontent
	values('244-1002', '300-7777', to_date('2017-06-10 19:00:00', 'YYYY-MM-DD HH24:MI:SS'), to_date('2017-06-10 20:00:00', 'YYYY-MM-DD HH24:MI:SS'));
insert into schedulecontent
	values('244-1002', '500-7850', to_date('2017-06-11 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), to_date('2017-06-11 11:00;00', 'YYYY-MM-DD HH24:MI:SS'));
insert into schedulecontent
	values('301-1001', '300-0613', to_date('2017-06-13 19:30:00', 'YYYY-MM-DD HH24:MI:SS'), to_date('2017-06-13 20:00:00', 'YYYY-MM-DD HH24:MI:SS'));
insert into schedulecontent
	values('301-2030', '300-0613', to_date('2017-06-13 19:30:00', 'YYYY-MM-DD HH24:MI:SS'), to_date('2017-06-13 20:00:00', 'YYYY-MM-DD HH24:MI:SS'));

insert into stop
	values('San Juan, Puerto Rico');
insert into stop
	values('Nassau, Bahamas');

insert into tour
	values('986-3333', 'San Juan, Puerto Rico');

insert into show
	values('300-7777');
insert into show
	values('300-0613');
insert into show
	values('300-0614');

insert into fitnesscentre
	values('500-6541', 'pool');
insert into fitnesscentre
	values('500-7850', 'gym');

insert into restaurant
	values('150-6666', 'French', 180);
insert into restaurant
	values('133-3260', 'Japanese/Buffet', 200);

insert into dining
	values('150-0001', 1, to_date('2017-06-05 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), to_date('2017-06-05 13:30:00', 'YYYY-MM-DD HH24:MI:SS'), '150-6666');
insert into dining
	values('150-0104', 1, to_date('2017-06-10 16:00:00', 'YYYY-MM-DD HH24:MI:SS'), to_date('2017-06-10 17:30:00', 'YYYY-MM-DD HH24:MI:SS'), '150-6666');
insert into dining
	values('133-0156', 1, to_date('2017-06-15 17:30:00', 'YYYY-MM-DD HH24:MI:SS'), to_date('2017-06-15 18:30:00', 'YYYY-MM-DD HH24:MI:SS'), '133-3260');
insert into dining
  values('131-0034', 1, to_date('2017-06-11 16:00:00', 'YYYY-MM-DD HH24:MI:SS'), to_date('2017-06-11 17:30:00', 'YYYY-MM-DD HH24:MI:SS'), '133-3260');
insert into dining
  values('150-0125', 1, to_date('2017-06-07 12:00:00', 'YYYY-MM-DD HH24:MI:SS'), to_date('2017-06-07 13:00:00', 'YYYY-MM-DD HH24:MI:SS'), '150-6666');

insert into menu
	values('b0', 'brunch', 'Vegetarian Brunch');
insert into menu
	values('b1', 'brunch', 'Sunshine Brunch');
insert into menu
	values('d0', 'dinner', 'Sushi Delight');
insert into menu
	values('d1', 'dinner', 'Disney Wonder');

insert into diningmenu
	values('150-0001', 'b0');
insert into diningmenu
	values('150-0001', 'b1');
insert into diningmenu
	values('150-0104', 'd1');
insert into diningmenu
	values('133-0156', 'd0');

insert into passengerdining
	values('ub301', '133-0156');
insert into passengerdining
	values('ma244', '150-0104');
insert into passengerdining
	values('mc244', '150-0104');
insert into passengerdining
  values('ua301', '131-0034');
insert into passengerdining
  values('mb244', '150-0125');

alter table cabin
  add foreign key (csid) references cleaningschedule (csid);


/*
create trigger CrewWorkScheduleNotNull before insert on crew
		for each row begin 
  	    if (new.csid is null and new.esid is null) then
		 	signal SQLSTATE '45000'
		 	set message_text = '\'csid\' and \'esid\' cannot both be null'; 
		 endif;
	end//
*/



