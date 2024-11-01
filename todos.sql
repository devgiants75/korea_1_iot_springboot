use springboot_db;

create table if not exists todos (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    task VARCHAR(255) NOT NULL,
    category VARCHAR(50) NOT NULL CHECK (category IN ('WORK', 'PERSONAL', 'STUDY', 'SHOPPING', 'OTHER')),
    description TEXT,
    status BOOLEAN DEFAULT FALSE
);