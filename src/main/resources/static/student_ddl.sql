-- Create Database
CREATE DATABASE IF NOT EXISTS student_management;

USE student_management;

-- Create table
CREATE TABLE IF NOT EXISTS school
(
    school_id INT NOT NULL AUTO_INCREMENT,
    name      VARCHAR(250),
    code      VARCHAR(50),
    email     VARCHAR(50),
    phone     VARCHAR(50),
    address   VARCHAR(250),
    website   VARCHAR(50),
    PRIMARY KEY (school_id)
);


CREATE TABLE IF NOT EXISTS department
(
    department_id INT NOT NULL AUTO_INCREMENT,
    name          VARCHAR(250),
    code          VARCHAR(50),
    email         VARCHAR(50),
    phone         VARCHAR(50),
    address       VARCHAR(250),
    website       VARCHAR(50),
    PRIMARY KEY (department_id)
    -- FOREIGN KEY ()
);



CREATE TABLE IF NOT EXISTS majoring
(
    majoring_id INT NOT NULL AUTO_INCREMENT,
    name        VARCHAR(250),
    code        VARCHAR(50),
    email       VARCHAR(50),
    phone       VARCHAR(50),
    address     VARCHAR(250),
    PRIMARY KEY (majoring_id)
);

CREATE TABLE IF NOT EXISTS course
(
    course_id  INT NOT NULL AUTO_INCREMENT,
    start_date DATE,
    end_date   DATE,
    name       VARCHAR(50),
    PRIMARY KEY (course_id)

);

CREATE TABLE IF NOT EXISTS class
(
    class_id    INT NOT NULL AUTO_INCREMENT,
    name        VARCHAR(250),
    code        VARCHAR(50),
    number      INT,
    limit_class INT,
    type        INT,
    status      int,
    description VARCHAR(250),
    start_date  DATE,
    end_date    DATE,
    address     VARCHAR(250),
    PRIMARY KEY (class_id)

);

CREATE TABLE IF NOT EXISTS teacher
(
    teacher_id INT NOT NULL AUTO_INCREMENT,
    name       VARCHAR(50),
    code       VARCHAR(50),
    birthday   DATE,
    email      VARCHAR(250),
    phone      VARCHAR(50),
    sex        INT,
    address    VARCHAR(250),
    status     INT,
    PRIMARY KEY (teacher_id)

);

CREATE TABLE IF NOT EXISTS class_teacher
(
    clas_teacher_id INT NOT NULL AUTO_INCREMENT,
    class_id        INT,
    teacher_id      INT,
    PRIMARY KEY (clas_teacher_id)
);

CREATE TABLE IF NOT EXISTS student
(
    student_id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(50),
    last_name  VARCHAR(50),
    code       VARCHAR(50),
    sex        INT,
    birthday   DATE,
    email      VARCHAR(50),
    phone      VARCHAR(50),
    address    VARCHAR(50),
    status     INT,
    PRIMARY KEY (student_id)
);

CREATE TABLE IF NOT EXISTS class_student
(
    class_student_id INT NOT NULL AUTO_INCREMENT,
    class_id         int,
    student_id       int,
    status           int,
    fee              float,
    PRIMARY KEY (class_student_id)
);

CREATE TABLE IF NOT EXISTS result
(
    result_id         INT NOT NULL AUTO_INCREMENT,
    first_year_score  float,
    second_year_score float,
    third_year_score  float,
    fourth_year_score float,
    fifth_year_score  float,
    rank_result       int,
    gap               float,
    PRIMARY KEY (result_id)

);

CREATE TABLE IF NOT EXISTS subject
(
    subject_id     INT NOT NULL AUTO_INCREMENT,
    name           VARCHAR(250),
    code           VARCHAR(50),
    number_credits int,
    type           INT,
    description    VARCHAR(255),
    PRIMARY KEY (subject_id)
);

CREATE TABLE IF NOT EXISTS lesson
(
    lesson_id INT NOT NULL AUTO_INCREMENT,
    lesson    int,
    PRIMARY KEY (lesson_id)
);

CREATE TABLE IF NOT EXISTS subject_lesson
(
    subject_lesson_id INT NOT NULL AUTO_INCREMENT,
    lesson_id         INT,
    subject_id        INT,
    PRIMARY KEY (subject_lesson_id)
);


CREATE TABLE IF NOT EXISTS semester
(
    semester_id   INT NOT NULL AUTO_INCREMENT,
    name          VARCHAR(50),
    main          INT,
    extra_evening INT,
    extra_summer  INT,
    status        INT,
    PRIMARY KEY (semester_id)

);

CREATE TABLE IF NOT EXISTS subject_semester
(
    subject_semester_id INT NOT NULL AUTO_INCREMENT,
    semester_id         INT,
    subject_id          INT,
    PRIMARY KEY (subject_semester_id)
);

CREATE TABLE IF NOT EXISTS class_subject
(
    class_subject_id INT NOT NULL AUTO_INCREMENT,
    class_id         INT,
    subject_id       INT,
    day              INT,
    lesson           int,
    PRIMARY KEY (class_subject_id)
);

CREATE TABLE IF NOT EXISTS school_year
(
    year_id    INT NOT NULL AUTO_INCREMENT,
    name       VARCHAR(50),
    start_date DATE,
    end_date   DATE,
    PRIMARY KEY (year_id)
);

CREATE TABLE IF NOT EXISTS class_semester
(
    class_semester_id INT NOT NULL AUTO_INCREMENT,
    class_id          INT,
    semester_id       INT,
    PRIMARY KEY (class_semester_id)
);


CREATE TABLE IF NOT EXISTS class_course
(
    class_course_id INT NOT NULL AUTO_INCREMENT,
    class_id        INT,
    course_id       INT,
    PRIMARY KEY (class_course_id)
);

CREATE TABLE IF NOT EXISTS schedule_exam
(
    schedule_exam_id INT NOT NULL AUTO_INCREMENT,
    class_id         INT,
    subject_id       INT,
    day              DATE,
    lesson           INT,
    address          VARCHAR(50),
    PRIMARY KEY (schedule_exam_id)
);


CREATE TABLE IF NOT EXISTS mark
(
    mark_id    INT NOT NULL AUTO_INCREMENT,
    class_id   INT,
    student_id INT,
    subject_id INT,
    type       INT,
    mark       FLOAT,
    date       DATE,
    PRIMARY KEY (mark_id)
);

CREATE TABLE IF NOT EXISTS account
(
    id        FLOAT NOT NULL AUTO_INCREMENT,
    user_name VARCHAR(250),
    password  VARCHAR(250),
    status    INT,
    email VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS roles
(
        role_id   INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(250),
    PRIMARY KEY (role_id)
);

CREATE TABLE IF NOT EXISTS account_roles
(
    id        FLOAT NOT NULL AUTO_INCREMENT,
    account_id INT,
    roles_id INT,
    PRIMARY KEY (id)
);


-- Create constraint (Primary key & Foreign key)
-- ALTER TABLE Persons
-- ADD CONSTRAINT PK_Person PRIMARY KEY (ID,LastName);


-- Insert data
INSERT INTO school (name, code, email, phone, address, website)
VALUES ('transport and communicate', 'utc', 'utc.@gmail.com', '0240650123', 'CauGiay', 'utc.com');



