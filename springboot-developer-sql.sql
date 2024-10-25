use springboot_db;

create table book (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    writer VARCHAR(100),
    title varchar(150),
    content varchar(550),
    category enum("NOTICE", "FREE", "QNA", "EVENT")
);

select * from book;

-- ###################################
use springboot_db;

create table if not exists post (
	id bigint auto_increment primary key,
    title varchar(255) not null,
    content varchar(255) not null,
    author varchar(255) not null
);

create table if not exists comment (
	id bigint auto_increment primary key,
    post_id bigint,
    content varchar(255) not null,
    commenter varchar(255) not null,
    foreign key (post_id) references post(id) on delete cascade
);

-- ###################################
use springboot_db;

create table if not exists users (
	id bigint primary key auto_increment,
    email varchar(255) unique not null,
    password varchar(255) not null,
    created_at datetime not null,
    updated_at datetime
);

-- ###################################
use springboot_db;

create table if not exists Menu (
	id BIGINT AUTO_INCREMENT PRIMARY KEY, -- 메뉴 고유값
    user_email VARCHAR(255) NOT NULL, -- 메뉴 등록 사용자 UserEmail
    name VARCHAR(255) NOT NULL, -- 메뉴명
    description TEXT NOT NULL, -- 메뉴 설명
    price INT NOT NULL CHECK (price >= 0), -- 메뉴 가격 (0 이상으로 설정)
    is_available BOOLEAN NOT NULL DEFAULT TRUE, -- 메뉴 이용가능 여부 (기본값 true - 가능)
    category VARCHAR(100) NOT NULL, -- 메뉴 카테고리
    size VARCHAR(50) DEFAULT 'Regular', -- 메뉴 사이즈 (기본값 'Regular' - 보통)
    CONSTRAINT chk_category CHECK (category IN ('Food', 'Drink', 'Dessert'))
    -- 카테고리는 'Food', 'Drink', 'Dessert'로 제한
);

select * from Menu;

drop table Menu;