-- sequence

drop sequence user_seq;
create sequence user_seq start with 1 increment by 1 maxvalue 9999999999;

drop sequence guestbook_seq;
create sequence guestbook_seq start with 1 increment by 1 maxvalue 9999999999;

drop sequence board_seq;
create sequence board_seq start with 1 increment by 1 maxvalue 9999999999;