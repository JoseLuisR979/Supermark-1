-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema supermark
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema supermark
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `supermark` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `supermark` ;

-- -----------------------------------------------------
-- Table `supermark`.`categoriaproductos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `supermark`.`categoriaproductos` (
  `idCategoria` INT UNSIGNED NOT NULL,
  `Almacen` VARCHAR(45) NULL DEFAULT NULL,
  `Bebidas` VARCHAR(45) NULL DEFAULT NULL,
  `Lacteos` VARCHAR(45) NULL DEFAULT NULL,
  `Panaderia` VARCHAR(45) NULL DEFAULT NULL,
  `CuidadoPersonal` VARCHAR(45) NULL DEFAULT NULL,
  `Mascotas` VARCHAR(45) NULL DEFAULT NULL,
  `Limpieza` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idCategoria`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `supermark`.`detalleventas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `supermark`.`detalleventas` (
  `idDetalleVentas` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `CantidadProducto` INT UNSIGNED NULL DEFAULT NULL,
  `PrecioUnitario` DECIMAL(8,2) UNSIGNED NULL DEFAULT NULL,
  `DetalleProducto` VARCHAR(45) NULL DEFAULT NULL,
  `ventas_idventas` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idDetalleVentas`, `ventas_idventas`),
  INDEX `fk_DetalleVentas_ventas1_idx` (`ventas_idventas` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `supermark`.`persona`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `supermark`.`persona` (
  `idPersona` INT NOT NULL AUTO_INCREMENT,
  `Documento` INT UNSIGNED NOT NULL,
  `TipoDocumento` VARCHAR(4) NULL DEFAULT NULL,
  `Nombre` VARCHAR(50) NULL DEFAULT NULL,
  `Apellido` VARCHAR(50) NULL DEFAULT NULL,
  `Direccion` VARCHAR(100) NULL DEFAULT NULL,
  `Telefono` INT NULL DEFAULT NULL,
  `Categoria` VARCHAR(8) NULL DEFAULT NULL,
  `Pass` VARCHAR(8) NULL DEFAULT NULL,
  PRIMARY KEY (`idPersona`, `Documento`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `supermark`.`productos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `supermark`.`productos` (
  `idProductos` INT UNSIGNED NOT NULL,
  `Nombre` VARCHAR(50) NULL DEFAULT NULL,
  `Descripcion` VARCHAR(50) NULL DEFAULT NULL,
  `Cantidad` INT UNSIGNED NULL DEFAULT NULL,
  `PrecioUnitario` DECIMAL(8,2) UNSIGNED NULL DEFAULT NULL,
  `detalleventas_idDetalleVentas` INT UNSIGNED NOT NULL,
  `detalleventas_ventas_idventas` INT UNSIGNED NOT NULL,
  `categoriaproductos_idCategoria` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idProductos`, `detalleventas_idDetalleVentas`, `detalleventas_ventas_idventas`, `categoriaproductos_idCategoria`),
  INDEX `fk_productos_detalleventas1_idx` (`detalleventas_idDetalleVentas` ASC, `detalleventas_ventas_idventas` ASC) VISIBLE,
  INDEX `fk_productos_categoriaproductos_idx` (`categoriaproductos_idCategoria` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `supermark`.`ventas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `supermark`.`ventas` (
  `idventas` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `MontoDeCompra` DECIMAL(8,2) UNSIGNED NULL DEFAULT NULL,
  `Recibo` INT UNSIGNED NULL DEFAULT NULL,
  `Observaciones` VARCHAR(100) NULL DEFAULT NULL,
  `Fecha` DATETIME NULL DEFAULT NULL,
  `persona_idPersona` INT NOT NULL,
  `persona_Documento` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idventas`, `persona_idPersona`, `persona_Documento`),
  INDEX `fk_ventas_persona1_idx` (`persona_idPersona` ASC, `persona_Documento` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
