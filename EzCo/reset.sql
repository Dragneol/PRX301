/****** Script for SelectTopNRows command from SSMS  ******/
USE [EzCo]
GO

DELETE FROM [dbo].[Ingredient]
GO

DBCC CHECKIDENT ('[Ingredient]', RESEED, 0);
GO

SELECT TOP (1000) [ID]
      ,[OldID]
      ,[Name]
      ,[Price]
      ,[Link]
      ,[Image]
      ,[Unit]
  FROM [EzCo].[dbo].[Ingredient]