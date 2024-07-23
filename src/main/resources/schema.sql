-- テーブルの作成
CREATE TABLE IF NOT EXISTS account (
	user_id			VARCHAR(255) NOT NULL,
	user_name		VARCHAR(255) ,
	password		VARCHAR(255) NOT NULL,
	height			FLOAT  	 ,
	weight			FLOAT  	 ,
	role_name		VARCHAR(255) ,
	gender			VARCHAR(5)	 ,
	PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS user_health_data (
    id BIGSERIAL PRIMARY KEY,
    user_id VARCHAR(255) NOT NULL,
    weight FLOAT NOT NULL,
    height FLOAT NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(5) NOT NULL,
    bmi FLOAT NOT NULL,
    body_fat_percentage FLOAT NOT NULL,
    entry_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES account(user_id)
);
