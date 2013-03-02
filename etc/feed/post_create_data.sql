--Locations
update location
set app_current = true, deleted = 0
where code = 'CR';

update location
set deleted = 1
where code != 'CR'
and parent is null;

commit;