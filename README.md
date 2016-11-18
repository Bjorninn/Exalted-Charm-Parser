# Exalted Charm Parser - v0.8
This little java program parses the Charm data from the Exalted 3e Core Rulebook into machine friendly json format.
This project does not contain any charm data, users need to [buy the book](http://www.drivethrurpg.com/product/162759/Exalted-3rd-Edition) from a vendor like DriveThruRPG and copy the text from a legally obtained pdf.

## How to prepare the data
This version works for the Core Rulebook pdf that was uploaded by [Onyx Path Publishing](http://www.drivethrurpg.com/browse/pub/4261/Onyx-Path-Publishing)
to [DriveThruRPG](http://www.drivethrurpg.com/product/162759/Exalted-3rd-Edition) on the 20th of April 2016. The [Miracles of the Solar Exalted](http://www.drivethrurpg.com/product/184596/Miracles-of-the-Solar-Exalted) version is the one uploaded on the 29th of July, 2016. I used Adobe Acrobat Reader DC, using other pdf readers might change the data somewhat so keep that in mind.

1. Create a folder named data in the root of the project.  
2. Copy everything from the headline **Archery** on page 255 to the last character on page 423 and paste that into a file called **charms.txt** and
place it into the data folder.  
3. Copy everything from the headline **Snake Style** on page 427 to the last line of the last charm before the **Sorcery** headline on page 464 into a file called ma_charms.txt and place it into the data folder.
4. Copy everything from the headline **Terrestrial Circle Spells** on page 471 to the last line of the last spell before the **Sorcerous Workings** headline on page 483 into a file called spells.txt and place it into the data folder.
5. If you own Miracles of the Solar Exalted you can copy everything from the headline **Archery** on page 5 to the last line on page 38 into a file called charms_motse.txt and place it into the data folder.

Now when you run the program it should output zero to four files: **charms_json.json**, **ma_charms_json.json**, **spells_json.json** and/or **charms_motse_json.json** into the data folder.

## Todos
- Generate trees from charms.  
- Process *cost* string (so probably a list of type and value).
- Add javadoc
- Manually check json for errors.

## JSON Schema
*Ability Charms*
```

```
*Martial Arts Charms*
```

```
*Spells*
```

```
