DROP TABLE IF EXISTS `banking_db`.`customer`;
CREATE TABLE `banking_db`.`customer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`));

DROP TABLE IF EXISTS `banking_db`.`account`;
CREATE TABLE `banking_db`.`account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `balance` DECIMAL(10, 2) NOT NULL,
  `type` SMALLINT(2) NOT NULL,
  `customer_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_customer_id_idx` (`customer_id` ASC),
  CONSTRAINT `FK_customer_id`
    FOREIGN KEY (`customer_id`)
    REFERENCES `banking_db`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

DROP TABLE IF EXISTS `banking_db`.`transaction`;
CREATE TABLE `banking_db`.`transaction` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sum` DECIMAL(10,2) NOT NULL,
  `id_account` INT NOT NULL,
  `date` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `id_account` (`id_account` ASC),
  CONSTRAINT `id_account`
    FOREIGN KEY (`id_account`)
    REFERENCES `banking_db`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

