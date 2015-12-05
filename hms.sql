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

create table attendance(
	roll_no varchar(20) references student,
	att_pres int,
	att_total int,
	updated date
);


insert into attendance values('12044', 0, 0);

drop table attendance;

Select att_present('12021', '05/12/2015');
Select att_absent('12021',  '04/12/2015');

select * from attendance;

Select att_pres from attendance a where a.roll_no = '12021'

drop function att_absent(wid varchar(15), sid varchar(15));

create or replace function att_present(roll varchar(15), d date) RETURNS text as
$$
	DECLARE
		res text;
		pres int := (Select att_pres from attendance a where a.roll_no = roll);
		total int := (Select att_total from attendance a where a.roll_no = roll);
	BEGIN
		/*pres := (Select att_pres from attendance a where a.roll_no = roll);
		total := (Select att_total from attendance a where a.roll_no = roll);*/
		update attendance set att_pres=pres+1, att_total=total+1, updated = d where roll_no = roll;
		res = 'Attendance updated successfully';
		return res;
	END;
$$ Language plpgsql

/* Function to insert Absent Student */
create or replace function att_absent(roll varchar(15), t date) RETURNS text as
$$
	DECLARE
		res text;
		pres int := (Select att_pres from attendance a where a.roll_no = roll);
		total int := (Select att_total from attendance a where a.roll_no = roll);
	BEGIN
		/*pres := (Select att_pres from attendance a where a.roll_no = roll);
		total := (Select att_total from attendance a where a.roll_no = roll);*/
		update attendance set att_pres=pres, att_total=total+1, updated = t where roll_no = roll;
		res = 'Attendance updated successfully';
		return res;
	END;
$$ Language plpgsql
