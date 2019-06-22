IF EXISTS 
   (SELECT name FROM master.dbo.sysdatabases 
    WHERE name = 'EzCoVer1')
BEGIN
    DROP DATABASE EzCoVer1
	CREATE DATABASE [EzCoVer1]
END

use [EzCoVer1]
go

create table [Ingredient](
	[ID] int not null PRIMARY KEY IDENTITY,
	[OldID] nchar(500), 
	[Name] nvarchar(800),
	[Price] int,
	[Link] nchar(1000),
	[Image] nchar(100),
	[Unit] int
)

create table [Recipe](
	[ID] int not null PRIMARY KEY IDENTITY,
	[Title] nvarchar(1000),
	[Description] nvarchar(2000),
	[Ration] int,
	[PrepareTime] int,
	[CookingTime] int,
	[Instruction] nvarchar(4000)
)

create table [IngredientMenu](
	[ID] int not null PRIMARY KEY IDENTITY,
	[RecipeID] int FOREIGN KEY REFERENCES [Recipe](ID),
	[IngredientID] int FOREIGN KEY REFERENCES [Ingredient](ID),
	[Quantitive] int
)