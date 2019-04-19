DROP TABLE IF EXISTS `banking`.`customer`;
CREATE TABLE `banking`.`customer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `telephone` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));

DROP TABLE IF EXISTS `banking`.`account`;
CREATE TABLE `banking`.`account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `balance` DECIMAL(10, 2) NOT NULL,
  `type` SMALLINT(2) NOT NULL,
  `customer_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_customer_id_idx` (`customer_id` ASC),
  CONSTRAINT `FK_customer_id`
    FOREIGN KEY (`customer_id`)
    REFERENCES `banking`.`customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

DROP TABLE IF EXISTS `banking`.`transaction`;
CREATE TABLE `banking`.`transaction` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sum` DECIMAL(10,2) NOT NULL,
  `from_account_id` INT NOT NULL,
  `to_account_id` INT NOT NULL,
  `date` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_from_account_id_idx` (`from_account_id` ASC),
  INDEX `FK_to_account_id_idx` (`to_account_id` ASC),
  CONSTRAINT `FK_from_account_id`
    FOREIGN KEY (`from_account_id`)
    REFERENCES `banking`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_to_account_id`
    FOREIGN KEY (`to_account_id`)
    REFERENCES `banking`.`account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

