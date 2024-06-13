desc user;

-- join
-- insert into user values(name, email, password, gender, join_date);
-- join_date를 Date 데이터로 지정해서 now()를 하면 시간까지 나오므로 에러 발생 ->  current_date() 사용
insert into user values(null, '관리자', 'admin@mysite.com', password('1234'), 'female', current_date(), 'ADMIN');
update user set role='ADMIN' where no = 1;

-- test
select * from user;
select * from guestbook;

-- user login(in mysite2/UserDao)
select no, name from user where email = 'robin@gmail.com' and password=password('robin');

-- user update
update user set name="로로", gender='male' where no = 4;

-- role 추가
alter table user add column role enum('ADMIN', 'USER') not null default 'USER';
-- 새로 생성할 경우 
insert into user values(null, '관리자', 'admin@mysite.com', password('1234'), 'female', current_date(), 'ADMIN');
-- 권한만 업데이트할 경우 
update user set role='ADMIN' where no = 1;

update user set role='ADMIN' where no = 14;