first login into 'php my admin' and create a new database 'DB_Book_Store'.

create 'User' table :
            "CREATE TABLE `DB_Book_Store`.`User`
                        ( `ID` INT NOT NULL AUTO_INCREMENT ,
                          `NAME` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL ,
                          `FAMILY_NAME` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,
                          `EMAIL` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL ,
                          `PHONE` VARCHAR(11) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL ,
                          `AVATAR` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,
                          PRIMARY  KEY (`ID`), UNIQUE `unique user email` (`EMAIL`),
                          UNIQUE `unique user phone` (`PHONE`)
                        )
                        ENGINE = InnoDB CHARSET=utf8 COLLATE utf8_unicode_ci;"


create 'Discount' table :
              "CREATE TABLE `db_book_store`.`discount`
                          ( `DISCOUNT_ID` VARCHAR(10) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL ,
                            `PERSENT` INT(2) NOT NULL ,
                            PRIMARY KEY (`DISCOUNT_ID`)
                          )
                          ENGINE = InnoDB CHARSET=utf8 COLLATE utf8_unicode_ci;"


creat 'Book' table :
              "CREATE TABLE `db_book_store`.`book`
              ( `ID` INT NOT NULL AUTO_INCREMENT ,
                `NAME` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL ,
                `AUTOR_NAME` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL ,
                `PRINT_YEAR` INT(4) NOT NULL ,
                `PUBLISHER` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,
                `SUMMERY` VARCHAR(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,
                `SHABAK` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL ,
                `PRICE` FLOAT(4) NOT NULL ,
                `TRANSLATOR` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,
                `GROUP_NAME` VARCHAR(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,
                `PAGE_COUNT` INT(6) NOT NULL ,
                `FRONT_PIC` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,
                `BACK_PIC` VARCHAR(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,
                `AVAILABLE_COUNT` INT(10) NOT NULL ,
                `DISCOUNT_CODE` VARCHAR(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL ,
                PRIMARY KEY (`ID`),
                UNIQUE `unique_book_name_and_autor_name` (`NAME`, `AUTOR_NAME`),
                UNIQUE (`SHABAK`), UNIQUE (`FRONT_PIC`),
                UNIQUE (`BACK_PIC`)
              )
              ENGINE = InnoDB CHARSET=utf8 COLLATE utf8_unicode_ci;"

and add foreign key 'DISCOUNT_CODE' from discount table.


create 'Comment' table :
              "CREATE TABLE `db_book_store`.`comment`
               ( `ID` INT NOT NULL AUTO_INCREMENT ,
                 `CONTENT` VARCHAR(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL ,
                 `DATE` DATE NOT NULL ,
                 `USER_ID` INT NOT NULL ,
                 `BOOK_ID` INT NOT NULL ,
                 PRIMARY KEY (`ID`),
                 UNIQUE (`USER_ID`),
                 UNIQUE (`BOOK_ID`)
                )
                ENGINE = InnoDB CHARSET=utf8 COLLATE utf8_unicode_ci;"

and added foreign key 'USER_ID' and 'BOOK_ID' from 'user' and 'book'  table. // user id and book id must be index and not be unique

create 'User Buy' table :
                "CREATE TABLE `db_book_store`.`user_buy`
                ( `ID` INT NOT NULL AUTO_INCREMENT ,
                  `USER_ID` INT NOT NULL ,
                  `BOOK_ID` INT NOT NULL ,
                  `DATE` DATE NOT NULL ,
                  `COUNT` INT NOT NULL DEFAULT '1' ,
                  PRIMARY KEY (`ID`)
                )
                ENGINE = InnoDB CHARSET=utf8 COLLATE utf8_unicode_ci;"

and add foreign key 'USER_ID' , 'BOOK_ID' from 'User' , 'Book' table.

create 'like_book' table :
                  "CREATE TABLE `db_book_store`.`Like_book`
                  ( `USER_ID` INT NOT NULL ,
                    `BOOK_ID` INT NOT NULL ,
                    UNIQUE `unique with book id together` (`USER_ID`, `BOOK_ID`)
                  )
                  ENGINE = InnoDB CHARSET=utf8 COLLATE utf8_unicode_ci;"

and add foreign key 'USER_ID' , 'BOOK_ID' from 'user' , 'book' table.
