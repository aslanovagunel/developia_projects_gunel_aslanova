insert into users
(username,password,enabled) values
('u1','{noop}1',1),
('u2','{noop}1',1);

insert into authority_list
( authority,user_num) values
('ROLE_ADD_EXPENSE_CATEGORY',1),
('ROLE_GET_EXPENSE_CATEGORY',1),
('ROLE_UPDATE_EXPENSE_CATEGORY',1),
('ROLE_DELETE_EXPENSE_CATEGORY',1),
('ROLE_ADD_INCOME_CATEGORY',1),
('ROLE_GET_INCOME_CATEGORY',1),
('ROLE_UPDATE_INCOME_CATEGORY',1),
('ROLE_DELETE_INCOME_CATEGORY',1),
('ROLE_ADD_INCOME',1),
('ROLE_GET_INCOME',1),
('ROLE_UPDATE_INCOME',1),
('ROLE_DELETE_INCOME',1),
('ROLE_GET_CATEGORY_DATE_RANGE',1),
('ROLE_GET_DATE_RANGE',1),
('ROLE_ADD_EXPENSE',1),
('ROLE_GET_EXPENSE',1),
('ROLE_UPDATE_EXPENSE',1),
('ROLE_DELETE_EXPENSE',1),
('ROLE_GET_DATE',1),
('ROLE_GET_CURRENT_BALANCE',1),
('ROLE_GET_CURRENT_BALANCE_AND_DATE',1),
('ROLE_POST_EXPENSE_PLAN',1),
('ROLE_GET_CHECK_EXPENSE_PLAN',1);


insert into authorities(username,authority) 
select 'u1',authority from authority_list where user_num=1;

insert into authorities(username,authority) 
select 'u2',authority from authority_list where user_num=1;

insert into incomes
(user_id,created_date,inc_category_id,amount,description) values
(1,'2024-12-12',1,'200','miqdar budur'),
(1,'2023-12-12',2,'100','miqdar budur'),
(1,'2022-12-12',3,'200','miqdar budur'),
(1,'2021-12-12',4,'100','miqdar budur');

insert into expenses
(user_id,created_date,exp_category_id,amount,description) values
(1,'2024-12-12',1,'10','miqdar budur'),
(1,'2023-12-12',2,'100','miqdar budur'),
(1,'2022-12-12',3,'300','miqdar budur'),
(1,'2021-12-12',4,'100','miqdar budur');

insert into expense_categories
(user_id,created_date,name) values
(1,'2024-12-12','qida'),
(2,'2023-12-12','neqliyyat'),
(1,'2022-12-12','eylence'),
(1,'2021-12-12','gezmek');

insert into income_categories
(user_id,created_date,name) values
(1,'2024-12-12','qida'),
(2,'2023-12-12','neqliyyat'),
(1,'2022-12-12','eylence'),
(1,'2021-12-12','gezmek');

insert into expense_plan
(total_amount,start_date,end_date) values
('100','2025-06-01','2025-06-10'),
('100','2025-06-02','2025-06-11'),
('100','2025-06-03','2025-06-12'),
('100','2025-06-04','2025-06-13');
