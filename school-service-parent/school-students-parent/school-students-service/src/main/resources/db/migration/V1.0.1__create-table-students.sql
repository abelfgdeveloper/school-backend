CREATE TABLE IF NOT EXISTS students (
    id varchar(50) NOT NULL, 
    first_name varchar(25) NOT NULL,
    last_name varchar(25) NOT NULL,
    email varchar(50) NOT NULL,
    create_at timestamp, 
    CONSTRAINT students_id_pk primary key (id),
	CONSTRAINT students_email_u UNIQUE(email)
);