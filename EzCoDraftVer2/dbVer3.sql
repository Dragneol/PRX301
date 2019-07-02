IF EXISTS 
   (SELECT name FROM master.dbo.sysdatabases 
    WHERE name = 'EzCoVer3')
BEGIN
    DROP DATABASE EzCoVer3
END

CREATE DATABASE [EzCoVer3]
go
use [EzCoVer3]
go

create table [Ingredient](
	[ID] nchar(10) not null PRIMARY KEY, 
	[Name] nvarchar(800),
	[Price] int,
	[Link] nchar(1000),
	[Image] nchar(1000),
	[Unit] nchar(10),
	[Quantitive] nchar (10)
)
go

create table [Recipe](
	[ID] int not null PRIMARY KEY,
	[Title] nvarchar(1000),
    [Link] nvarchar(1000),
    [Image] nvarchar(1000),
	[Description] nvarchar(2000),
	[Ration] int,
	[PrepareTime] int,
	[CookingTime] int,
)
go

create table [IngredientMenu](
	[ID] int not null PRIMARY KEY IDENTITY,
	[RecipeID] int FOREIGN KEY REFERENCES [Recipe](ID),
	[Name] nvarchar(1000),
	[Unit] nvarchar(50),
	[Quantitive] nchar(10)
)
go

create table [InstructionMenu](
        [ID] int not null PRIMARY KEY IDENTITY,
        [RecipeID] int FOREIGN KEY REFERENCES [Recipe](ID),
        [NumStep] int,
        [Detail] nvarchar(1000),
)
go