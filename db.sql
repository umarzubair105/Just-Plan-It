insert into `country` (id, name, countryCode, phoneCode, active)
values (1, 'United Arab Emirates', 'UAE', '00971', 1);

insert into `country` (id, name, countryCode, phoneCode, active)
values (2, 'Pakistan', 'PK', '0092', 1);

insert into `company` (id, name, code, sample, countryId, active)
values (1, 'Product based IT Services', '981101', true, 1, 1);

insert into `role` (id, name, code, companyId, groupTask, taskAssignable, active)
values (1, 'Product Manager', 'PM', 1, 1, 1, 1);
insert into `role` (id, name, code, companyId, groupTask, taskAssignable, active)
values (2, 'Product Owner', 'PO', 1, 1, 1, 1);
insert into `role` (id, name, code, companyId, groupTask, taskAssignable, active)
values (3, 'Business Analyst', 'BA', 1, 1, 1, 1);
insert into `role` (id, name, code, companyId, groupTask, taskAssignable, active)
values (4, 'Solution Architect', 'SA', 1, 1, 1, 1);
insert into `role` (id, name, code, companyId, groupTask, taskAssignable, active)
values (5, 'Backend Developer', 'BSE', 1, 1, 1, 1);
insert into `role` (id, name, code, companyId, groupTask, taskAssignable, active)
values (6, 'UI Developer', 'UID', 1, 1, 1, 1);
insert into `role` (id, name, code, companyId, groupTask, taskAssignable, active)
values (7, 'QA Engineer', 'QAE', 1, 1, 1, 1);
insert into `role` (id, name, code, companyId, groupTask, taskAssignable, active)
values (8, 'Administrator', 'ADMIN', 1, 1, 1, 1);
insert into `role` (id, name, code, companyId, groupTask, taskAssignable, active)
values (9, 'DBA', 'DBA', 1, 1, 1, 1);
insert into `role` (id, name, code, companyId, groupTask, taskAssignable, active)
values (10, 'HR', 'HR', 1, 1, 1, 1);
insert into `role` (id, name, code, companyId, groupTask, taskAssignable, active)
values (11, 'Fullstack Developer', 'FSE', 1, 1, 1, 1);
insert into `role` (id, name, code, companyId, groupTask, taskAssignable, active)
values (12, 'Scrum Master', 'SM', 1, 1, 1, 1);

insert into `companyweekend` (id, day, companyId, active)
values (1, 'SATURDAY', 1, 1);

insert into `companyweekend` (id, day, companyId, active)
values (2, 'SUNDAY', 1, 1);

insert into `companyweekend` (id, day, companyId, active)
values (2, 'SUNDAY', 1, 1);

insert into CompanyWorkingHour (id, minutes, description, scope, companyId, eventDate, dayOfWeek, startDate, endDate,
                                recurring, active)
values (1, 480, 'Daily working minutes', 'DEFAULT', 1, null, null, null, null, 1, 1);

insert into CompanyWorkingHour (id, minutes, description, scope, companyId, eventDate, dayOfWeek, startDate, endDate,
                                recurring, active)
values (2, 420, 'Friday working minutes', 'WEEK_DAY', 1, null, 'FRIDAY', null, null, 1, 1);


insert into CompanyWorkingHour (id, minutes, description, scope, companyId, eventDate, dayOfWeek, startDate, endDate,
                                recurring, active)
values (5, 480, 'Daily working minutes', 'DEFAULT', 951, null, null, null, null, 1, 1);

insert into CompanyWorkingHour (id, minutes, description, scope, companyId, eventDate, dayOfWeek, startDate, endDate,
                                recurring, active)
values (4, 420, 'Friday working minutes', 'WEEK_DAY', 951, null, 'FRIDAY', null, null, 1, 1);

update `companycalendar_seq`
set next_val=10;

update companyweekend_seq
set next_val=1000;

update country_seq
set next_val=1000;

update company_seq
set next_val=1000;
update role_seq
set next_val=1000;
