select * from login;

truncate table login;

create table login (
    username varchar(20),
    password varchar(20)
}

insert into login values('admin', 'admin');

create table student (
	roll_no  varchar(20),
	fname varchar(20),
	lname varchar(20),
	gaurdian varchar(20),
	address varchar(140),
	hostel varchar(20),
	room varchar(20),
	email varchar(30),
	contact varchar(20),
	remarks varchar(160),
	primary key (roll_no) 
);


select * from student;

insert into student values ('asdf', '', '', '', '', '', '', '', '', '');

drop table student cascade;
