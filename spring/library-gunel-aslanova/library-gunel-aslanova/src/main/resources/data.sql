insert into authority_list
( authority,librarian) values
('ROLE_ADD_BOOK',1),
('ROLE_DELETE_BOOK',1),
('ROLE_GET_BOOK',1),
('ROLE_UPDATE_BOOK',1) ;

insert into users
(username,password,enabled,user_id,user_type) values
('u1','{noop}1',1,1,'librarian'),
('u2','{noop}1',1,2,'librarian'),
('u3','{noop}1',1,3,'librarian');

insert into authorities(username,authority) 
select 'u1',authority from authority_list where librarian=1;

insert into authorities(username,authority) 
select 'u2',authority from authority_list where librarian=1;


insert into librarians
(name,surname,phone,birthday) values
('gunel','aslanova','12345','2012-09-07');






