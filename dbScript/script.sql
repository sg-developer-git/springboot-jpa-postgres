create schema postgres_poc;

create sequence if NOT EXISTS postgres_poc.postgres_poc_student_id
start 10
increment 1;						

