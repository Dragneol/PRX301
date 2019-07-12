IF EXISTS 
   (SELECT name FROM master.dbo.sysdatabases 
    WHERE name = 'EzCo')
BEGIN
    DROP DATABASE EzCo
END

CREATE DATABASE [EzCo]
go
use [EzCo]
go

create table [RecipeCategory](
	[ID] int not null PRIMARY KEY,
	[Name] nvarchar(1000),
	[Link] nchar(2000)
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

create table [CateRep](
        [ID] int NOT NULL PRIMARY KEY IDENTITY,
	[RecipeID] int FOREIGN KEY REFERENCES [Recipe](ID),
	[CateID] int FOREIGN KEY REFERENCES [RecipeCategory](ID)
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
        [Detail] nvarchar(2000),
)
go

create table [Ingredient](
	[ID] nchar(10) not null PRIMARY KEY, 
	[Name] nvarchar(800),
	[Price] int,
	[Link] nchar(1000),
	[Image] nchar(1000),
	[Description] nvarchar(2000)
)
go