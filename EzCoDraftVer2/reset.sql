/****** Script for SelectTopNRows command from SSMS  ******/
USE [EzCoVer2]

DELETE FROM [dbo].Ingredient
DELETE FROM [dbo].IngredientMenu
DELETE FROM [dbo].InstructionMenu
DELETE FROM [dbo].Recipe

DBCC CHECKIDENT ('[IngredientMenu]', RESEED, 0);
DBCC CHECKIDENT ('[InstructionMenu]', RESEED, 0);

SELECT * FROM Recipe
SELECT * FROM Ingredient
SELECT * FROM IngredientMenu
SELECT * FROM InstructionMenu