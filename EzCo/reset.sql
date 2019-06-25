/****** Script for SelectTopNRows command from SSMS  ******/
USE [EzCoVer1]

DELETE FROM [dbo].Ingredient
DELETE FROM [dbo].IngredientMenu
DELETE FROM [dbo].InstructionMenu
DELETE FROM [dbo].Recipe

DBCC CHECKIDENT ('[IngredientMenu]', RESEED, 0);
DBCC CHECKIDENT ('[InstructionMenu]', RESEED, 0);

SELECT * FROM [EzCoVer1].[dbo].Recipe
SELECT * FROM [EzCoVer1].[dbo].Ingredient
SELECT * FROM [EzCoVer1].[dbo].IngredientMenu
SELECT * FROM [EzCoVer1].[dbo].InstructionMenu