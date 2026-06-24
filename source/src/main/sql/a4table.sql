create table employees (
id int PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(20) NOT NULL,
age int NOT NULL,
gender Int NOT NULL,
phone VARCHAR(20) NOT NULL,
address VARCHAR(100) NOT NULL,
admin VARCHAR(1),
login_id VARCHAR(20) NOT NULL,
password VARCHAR(20) NOT NULL
);

create table cows (
number Int PRIMARY KEY AUTO_INCREMENT,				
id VARCHAR(10) NOT NULL,				
name VARCHAR(20) NOT NULL,				
gender Int NOT NULL,				
birth_day DATE,				
status Int DEFAULT '0',				
photo VARCHAR(50),				
updatedate DATE,				
cause VARCHAR(20),				
regist_day DATE 
);	

create table cows_daily (						
day DATE NOT NULL,			
number Int,			
temperature DECIMAL(4,1) NOT NULL,			
appetite Int NOT NULL,			
drinking Int NOT NULL,			
manure Int NOT NULL,			
health Int NOT NULL
);		
create table cows_monthly (								
number Int,				
weight DECIMAL(5,1) NOT NULL,				
milkquality Int NOT NULL,				
bacterial_count VARCHAR(20) NOT NULL,				
milk_fat_content DECIMAL(4,2) NOT NULL,				
somatic_cell_count Int NOT NULL,				
day DATE 
);				
create table shift (				
shift_id Int PRIMARY KEY AUTO_INCREMENT,				
id Int,				
intime Int(1) NOT NULL,				
day Date NOT NULL				
);	
create table weather (			
day DATE PRIMARY KEY,			
weather Int,			
high_temperature DECIMAL(3,1),			
low_temperature DECIMAL(3,1),			
humidity INT,			
precipitation DECIMAL(4,1),			
windpower DECIMAL(4,1)		
);		
create table feeds (				
feed_id Int PRIMARY KEY AUTO_INCREMENT,				
date Date,				
increase_amount Int,				
decrease_amount Int
);				);				
create table all_money_daily (					
	money_id Int PRIMARY KEY AUTO_INCREMENT,					
	income Int NOT NULL,					
	expense Int NOT NULL,					
	date Date					
	);	



    INSERT INTO employees
(name, age, gender, phone, address,admin , login_id, password)
VALUES
('山田太郎', 30, 1, '090-1111-2222', '東京都新宿区1-2-3', '1', 'taro', 'pass123'),
('佐藤花子', 28, 2, '080-3333-4444', '千葉県千葉市4-5-6', '0', 'hanako', 'hanako01'),
('鈴木一郎', 45, 1, '070-5555-6666', '神奈川県横浜市7-8-9', '0', 'ichiro', 'suzuki45'),
('田中美咲', 32, 2, '090-7777-8888', '埼玉県さいたま市10-11-12', '0', 'misaki', 'tanaka32'),
('高橋健', 38, 1, '080-9999-0000', '千葉県千葉市13-14-15', '1', 'ken', 'takahashi');


INSERT INTO cows (id, name, gender, birth_day, status, photo, updatedate, cause, regist_day)
VALUES
('C001', 'Sakura', 1, '2020-03-15', 0, 'sakura.jpg', '2024-01-10', NULL, '2020-03-20'),
('C002', 'Kuro', 0, '2019-11-02', 2, 'kuro.png', '2024-02-05', 'Moved', '2019-11-10'),
('C003', 'Momo', 1, '2021-07-21', 0, NULL, '2024-03-01', NULL, '2021-07-25'),
('C004', 'Shiro', 0, '2018-05-30', 1, 'shiro.jpg', '2023-12-18', 'Death', '2018-06-05'),
('C005', 'Hana', 1, '2022-09-12', 0, 'hana.png', '2024-01-28', NULL, '2022-09-15');

UPDATE cows SET id = '0000000001' WHERE id = 'C001';
UPDATE cows SET id = '0000000002' WHERE id = 'C002';
UPDATE cows SET id = '0000000003' WHERE id = 'C003';
UPDATE cows SET id = '0000000004' WHERE id = 'C004';
UPDATE cows SET id = '0000000005' WHERE id = 'C005';

INSERT INTO cows_daily (day, number, temperature, appetite, drinking, manure, health) VALUES
('2024-04-01', 1, 38.6, 1, 1, 1, 1),
('2024-04-01', 2, 38.9, 2, 2, 2, 2),
('2024-04-01', 3, 38.4, 1, 1, 1, 1),
('2024-04-01', 4, 39.1, 3, 3, 3, 3),
('2024-04-01', 5, 38.7, 1, 2, 1, 1),
('2024-04-02', 1, 38.5, 1, 1, 1, 1),
('2024-04-02', 2, 39.0, 2, 2, 2, 2),
('2024-04-02', 3, 38.3, 1, 1, 1, 1),
('2024-04-02', 4, 39.2, 3, 3, 3, 3),
('2024-04-02', 5, 38.6, 1, 2, 1, 1),
('2024-04-03', 1, 38.7, 1, 1, 1, 1),
('2024-04-03', 2, 38.8, 2, 2, 2, 2),
('2024-04-03', 3, 38.5, 1, 1, 1, 1),
('2024-04-03', 4, 39.3, 3, 3, 3, 3),
('2024-04-03', 5, 38.9, 1, 2, 1, 1);

INSERT INTO cows_monthly (number, weight, milkquality, bacterial_count, milk_fat_content, somatic_cell_count, day) VALUES
(1, 518.2, 1, 17000, 4.20, 110000, '2024-01-31'),
(3, 478.0, 1, 16000, 4.40, 90000,  '2024-01-31'),
(5, 448.7, 1, 19000, 4.55, 105000, '2024-01-31'),
(1, 519.8, 1, 18000, 4.15, 115000, '2024-02-29'),
(3, 479.5, 1, 17000, 4.38, 95000,  '2024-02-29'),
(5, 450.2, 1, 20000, 4.48, 112000, '2024-02-29'),
(1, 520.5, 1, 18000, 4.10, 120000, '2024-03-31'),
(3, 480.7, 1, 16000, 4.35, 95000,  '2024-03-31'),
(5, 450.3, 1, 20000, 4.50, 110000, '2024-03-31'),
(1, 522.0, 1, 17500, 4.18, 118000, '2024-04-30'),
(3, 482.1, 1, 16500, 4.42, 93000,  '2024-04-30'),
(5, 451.0, 1, 19500, 4.52, 108000, '2024-04-30');

INSERT INTO shift (id, intime, day) VALUES
(1, 4, '2024-06-01'),  
(2, 0, '2024-06-01'),  
(3, 1, '2024-06-01'),  
(4, 3, '2024-06-01'),  
(5, 4, '2024-06-01'),  
(1, 0, '2024-06-02'),  
(2, 1, '2024-06-02'),  
(3, 4, '2024-06-02'),  
(4, 3, '2024-06-02'),  
(5, 0, '2024-06-02'),  
(1, 1, '2024-06-03'),  
(2, 4, '2024-06-03'), 
(3, 3, '2024-06-03'),  
(4, 0, '2024-06-03'),  
(5, 4, '2024-06-03');  
			
INSERT INTO weather (day, weather, high_temperature, low_temperature, humidity, precipitation, windpower) VALUES
('2024-04-01', 1, 18.5, 10.2, 55, 0.0, 2.5),   
('2024-04-02', 3, 20.1, 12.3, 60, 0.0, 3.0),   
('2024-04-03', 51, 16.8, 9.5, 72, 12.5, 4.1),  
('2024-04-04', 0, 19.4, 11.0, 58, 0.0, 2.0),   
('2024-04-05', 2, 22.0, 13.8, 48, 0.0, 1.8),   
('2024-04-06', 45, 17.6, 10.1, 85, 0.0, 1.2),  
('2024-04-07', 82, 15.9, 8.7, 90, 18.3, 5.0),  
('2024-04-08', 1, 21.2, 12.0, 52, 0.0, 2.3),   
('2024-04-09', 3, 23.5, 14.4, 65, 0.0, 1.5),   
('2024-04-10', 95, 18.0, 11.2, 70, 6.7, 6.0);  

INSERT INTO feeds (date, increase_amount, decrease_amount) VALUES
('2024-03-01', 5000, 0),
('2024-03-02', 0, 1200),
('2024-03-03', 3000, 500),
('2024-03-04', 0, 2000),
('2024-03-05', 8000, 0),
('2024-03-06', 1500, 300),
('2024-03-07', 0, 1000),
('2024-03-08', 4000, 0),
('2024-03-09', 2500, 700),
('2024-03-10', 0, 1800);
