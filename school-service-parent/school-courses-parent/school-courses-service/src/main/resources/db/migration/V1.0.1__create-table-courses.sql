CREATE TABLE IF NOT EXISTS courses (
    id varchar(50) NOT NULL, 
    name varchar(25) NOT NULL,
    create_at timestamp, 
    CONSTRAINT courses_id_pk primary key (id),
	CONSTRAINT courses_name_u UNIQUE(name)
);