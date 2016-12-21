insert into Profile(profileName) values('admin');
update Profile set profileName = 'Administrator' where profileId=1;
select * from Profile;

insert into Person(personName, personConnexionName, personPassword, personProfile) values('Administrator', 'admin', 'admin', 1);
select * from Person;