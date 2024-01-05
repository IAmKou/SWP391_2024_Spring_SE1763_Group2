drop database if exists house_finder;
create database house_finder;
use house_finder;
CREATE TABLE Role(
	id INT not null auto_increment,
    primary key(id),
	role_name VARCHAR(50)
);
Create table USER(
userID int not null auto_increment, 
primary key (userID),
fullName varchar(250),
userName varchar(250),
passWord varchar(250),
roleID int,
location varchar(250),
phone varchar(11),
email varchar(250),
foreign key (roleID) references Role(id) 
);
CREATE TABLE TypeOfHouse (
    TypeOfHouseID INT PRIMARY KEY,
    Description VARCHAR(255)
);
CREATE TABLE House (
    HouseID INT not null auto_increment,
    primary key (HouseID),
    Location VARCHAR(255),
    TypeOfHouseID INT,
    Description VARCHAR(255),
    HouseOwnerID INT,
    PricePerUnit DECIMAL(10, 2),
    Picture VARCHAR(255),
    Available BOOLEAN,
    FOREIGN KEY (TypeOfHouseID) REFERENCES TypeOfHouse(TypeOfHouseID),
    FOREIGN KEY (HouseOwnerID) REFERENCES USER(userID)
);
CREATE TABLE SellPost (
    postID INT not null auto_increment,
    PRIMARY KEY (postID),
    houseID INT,
    userID INT,
    Time DATETIME,
    TypeOfHouseID INT,
    FOREIGN KEY (houseID) REFERENCES House(HouseID),
    FOREIGN KEY (userID) REFERENCES USER(userID),
    FOREIGN KEY (TypeOfHouseID) REFERENCES TypeOfHouse(TypeOfHouseID)
);
CREATE TABLE RequestSellPost (
    rsID INT not null ,
    primary key (postID),
    postID int,
    foreign key(postID) references SellPost(postID)
);
CREATE TABLE OderBooking (
    userID INT,
    houseID INT,
    Time DATETIME,
    FOREIGN KEY (userID) REFERENCES USER(userID),
    FOREIGN KEY (houseID) REFERENCES House(HouseID)
);
CREATE TABLE RentingHouse (
    HouseID INT,
    rhID INT not null auto_increment,
    PRIMARY KEY(rhID),
    renterID INT,
    timeFrom DATETIME,
    timeTo DATETIME,
    fee DECIMAL(10, 2),
    priceTotal DECIMAL(10, 2),
    FOREIGN KEY (HouseID) REFERENCES House(HouseID),
    FOREIGN KEY (renterID) REFERENCES USER(userID)
);
CREATE TABLE Record (
    renterID INT,
    rhID INT,
    Time DATETIME,
    FOREIGN KEY (renterID) REFERENCES USER(userID),
    FOREIGN KEY (rhID) REFERENCES RentingHouse(rhID)
);
CREATE TABLE Feedback (
    feedBackID int not null auto_increment,
    primary key (feedBackID),
    userID INT,
    rhID INT,
    grade INT,
    FeedBack VARCHAR(255),
    Time DATETIME,
    FOREIGN KEY (userID) REFERENCES USER(userID),
    FOREIGN KEY (rhID) REFERENCES RentingHouse(rhID)
);

INSERT INTO role (`id`, `role_name`) VALUES ('1', 'user');
INSERT INTO role (`id`, `role_name`) VALUES ('2', 'admin');




