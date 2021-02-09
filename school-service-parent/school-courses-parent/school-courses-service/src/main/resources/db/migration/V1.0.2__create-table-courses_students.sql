CREATE TABLE IF NOT EXISTS courses_students (
	course_id varchar(50) NOT NULL,
	student_id varchar(50) NULL,
	CONSTRAINT courses_students_course_id__student_id_u UNIQUE(course_id, student_id),
	CONSTRAINT courses_students_course_id_fk FOREIGN KEY (course_id) REFERENCES courses(id)
);
