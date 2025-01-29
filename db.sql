insert into `country` (id,name,countryCode,phoneCode,active)
    values (1,'United Arab Emirates','UAE','00971',1);

insert into `country` (id,name,countryCode,phoneCode,active)
values (2,'Pakistan','PK','0092',1);

insert into `company` (id,name,sample,countryId,active)
values (1,'Sample',true,1,1);

insert into `role` (id,name,code,companyId,groupTask,taskAssignable,active)
values (1,'Product Manager','PM',1,1,1,1);
insert into `role` (id,name,code,companyId,groupTask,taskAssignable,active)
values (2,'Product Owner','PO',1,1,1,1);

insert into `companyweekend` (id,day,companyId,active)
values (1,'SATURDAY',1,1);

insert into `companyweekend` (id,day,companyId,active)
values (2,'SUNDAY',1,1);

update companyweekend_seq set next_val=(select max(id)+1 from companyweekend);

update country_seq set next_val=(select max(id)+1 from country);

update company_seq set next_val=(select max(id)+1 from company);
update role_seq set next_val=(select max(id)+1 from role);
