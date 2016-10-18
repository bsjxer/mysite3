-- users

-- insert
insert into users values( user_seq.nextval, '배승진', 'bsjxer@naver.com', '1234', 'male');

-- delete
delete from users;

commit;

-- select ( login )
select no, name from users where email='bsjxer@naver.com' and password='1234';
