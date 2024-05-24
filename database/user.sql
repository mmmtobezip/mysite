desc user;

-- join
-- insert into user values(name, email, password, gender, join_date);
-- join_date를 Date 데이터로 지정해서 now()를 하면 시간까지 나오므로 에러 발생 ->  current_date() 사용
insert into user values(null, '관리자', 'admin@mysite.com', password('1234'), 'female', current_date());

-- test
select * from user;

select * from guestbook;