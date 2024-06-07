select * from guestbook;
select * from guestbook_log;
delete from guestbook_log;

-- guestbook_log
-- 게시판에 글이 들어간 다음인지 상관없음(why? transaction의 대상이므로), tx대상의 dml1 / dml2 순서는 상관없이 두 query가 모두 성공해야함
-- insert 후 첫  번째 쿼리는 update 
update guestbook_log set count = count + 1 where date = current_date();
-- count가 0이면 insert, 1 개 이상에선 update쿼리만 탐. 
insert into guestbook_log values(current_date(), 1);

 -- select date(reg_date) from guestbook where no = 30; 30번 글을 지우겠다 가정하면, 현재 datetime type -> date 타입으로 캐스팅 해줘야함. 
 -- 이를 update에서 한 번에, delete 할 땐 no를 통해
update guestbook_log set count = count - 1 where date = (select date(reg_date) from guestbook where no = 30);

