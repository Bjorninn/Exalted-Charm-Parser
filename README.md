# Exalted Charm Parser - v0.7
This little java program parses the Charm data from the Exalted 3e Core Rulebook into machine friendly json format.
This project does not contain any charm data, users need to [buy the book](http://www.drivethrurpg.com/product/162759/Exalted-3rd-Edition) from a vendor like DriveThruRPG and copy the text from
a legally obtained pdf.

## How to prepare the data
This version works for the pdf that was uploaded by [Onyx Path Publishing](http://www.drivethrurpg.com/browse/pub/4261/Onyx-Path-Publishing)
to [DriveThruRPG](http://www.drivethrurpg.com/product/162759/Exalted-3rd-Edition) on the 20th of April 2016. I used Adobe Acrobat Reader DC, using other pdf readers might change the data somewhat so keep that in mind.

1. Create a folder named data in the root of the project.  
2. Copy everything from the headline **Archery** on page 255 to the last character on page 423 and paste that into a file called **charms.txt** and
place it into the data folder.  
3. Copy everything from the headline **Snake Style** on page 427 to the last line of the last charm before the **Sorcery** headline on page 464 into a file called ma_charms.txt and place it into the data folder.
4. Copy everything from the headline **Terrestrial Circle Spells** on page 471 to the last line of the last spell before the **Sorcerous Workings** headline on page 483 into a file called spells.txt and place it into the data folder.

Now when you run the program it should output two files **charms_json.json** and **ma_charms_json** into the data folder.

## Todos
1. Add the ability to process the **Miracles of the Solar Exalted**.  
2. Add page number and source book to charms and spells.  
3. Generate trees from charms.  
4. Process *cost* string (so probably a list of type and value).
5. Add javadoc
6. Manually check json for errors.

## JSON Schema
*Ability Charms*
```
{
  "type": "object",
  "properties": {
    "charms": {
      "type": "array",
      "items": {
        "type": "object",
        "properties": {
          "ability": {
            "type": "string"
          },
          "name": {
            "type": "string"
          },
          "cost": {
            "type": "string"
          },
          "min_ability": {
            "type": "object",
            "properties": {
              "name": {
                "type": "string"
              },
              "value": {
                "type": "integer"
              }
            },
            "required": [
              "name",
              "value"
            ]
          },
          "min_essence": {
            "type": "object",
            "properties": {
              "name": {
                "type": "string"
              },
              "value": {
                "type": "integer"
              }
            },
            "required": [
              "name",
              "value"
            ]
          },
          "type": {
            "type": "string"
          },
          "duration": {
            "type": "string"
          },
          "keywords": {
            "type": "array",
            "items": {
              "type": "string"
            }
          },
          "prerequisite_charms": {
            "type": "array",
            "items": {
              "type": "string"
            }
          },
          "description": {
            "type": "string"
          }
        },
        "required": [
          "ability",
          "name",
          "cost",
          "min_ability",
          "min_essence",
          "type",
          "duration",
          "keywords",
          "prerequisite_charms",
          "description"
        ]
      }
    }
  },
  "required": [
    "charms"
  ]
}
```
*Martial Arts Charms*
```
{
  "type": "object",
  "properties": {
    "charms": {
      "type": "array",
      "items": {
        "type": "object",
        "properties": {
          "martial_art_style": {
            "type": "string"
          },
          "mastery": {
            "type": "string"
          },
          "terrestrial": {
            "type": "string"
          },
          "special_activation_rules": {
            "type": "string"
          },
          "ability": {
            "type": "string"
          },
          "name": {
            "type": "string"
          },
          "cost": {
            "type": "string"
          },
          "min_ability": {
            "type": "object",
            "properties": {
              "name": {
                "type": "string"
              },
              "value": {
                "type": "integer"
              }
            },
            "required": [
              "name",
              "value"
            ]
          },
          "min_essence": {
            "type": "object",
            "properties": {
              "name": {
                "type": "string"
              },
              "value": {
                "type": "integer"
              }
            },
            "required": [
              "name",
              "value"
            ]
          },
          "type": {
            "type": "string"
          },
          "duration": {
            "type": "string"
          },
          "keywords": {
            "type": "array",
            "items": {
              "type": "string"
            }
          },
          "prerequisite_charms": {
            "type": "array",
            "items": {
              "type": "string"
            }
          },
          "description": {
            "type": "string"
          }
        },
        "required": [
          "martial_art_style",
          "mastery",
          "terrestrial",
          "special_activation_rules",
          "ability",
          "name",
          "cost",
          "min_ability",
          "min_essence",
          "type",
          "duration",
          "keywords",
          "prerequisite_charms",
          "description"
        ]
      }
    }
  },
  "required": [
    "charms"
  ]
}
```
*Spells*
```
{
  "$schema": "http://json-schema.org/draft-04/schema#",
  "type": "object",
  "properties": {
    "spells": {
      "type": "array",
      "items": {
        "type": "object",
        "properties": {
          "name": {
            "type": "string"
          },
          "circle": {
            "type": "string"
          },
          "cost": {
            "type": "string"
          },
          "duration": {
            "type": "string"
          },
          "keywords": {
            "type": "array",
            "items": {
              "type": "string"
            }
          },
          "description": {
            "type": "string"
          },
          "distortion": {
            "type": "string"
          }
        },
        "required": [
          "name",
          "circle",
          "cost",
          "duration",
          "keywords",
          "description",
          "distortion"
        ]
      }
    }
  },
  "required": [
    "spells"
  ]
}
```
