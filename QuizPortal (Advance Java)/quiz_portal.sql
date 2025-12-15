CREATE DATABASE quiz_portal;
USE quiz_portal;


select * from users;
select * from admin_users;
-- Admin table
CREATE TABLE admin_users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100)
);

INSERT INTO admin_users (name,email,password)
VALUES ('John Deo','admin@quiz.com','admin123');

-- Normal users
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Questions master
CREATE TABLE questions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    question_text VARCHAR(500),
    option1 VARCHAR(200),
    option2 VARCHAR(200),
    option3 VARCHAR(200),
    option4 VARCHAR(200),
    correct_option CHAR(1),
    category VARCHAR(50)
);

-- Quiz master
CREATE TABLE quizzes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200),
    category VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

select * from quizzes;

-- mapping quiz ↔ questions (many-to-many)
CREATE TABLE quiz_questions (
    quiz_id INT,
    question_id INT,
    PRIMARY KEY (quiz_id, question_id),
    FOREIGN KEY (quiz_id) REFERENCES quizzes(id) ON DELETE CASCADE,
    FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE CASCADE
);

-- quiz attempt / score
CREATE TABLE results (
    id INT AUTO_INCREMENT PRIMARY KEY,
    quiz_id INT,
    user_id INT,
    score INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (quiz_id) REFERENCES quizzes(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);
