# Games-App

## Table of contents
* [General info](#general-info)
* [Functionality](#functionality)
* [Technologies](#technologies)
* [Setup](#setup)
* [License](#license)

## General info
Game browsing app that allows users to explore and select games by genre, built using modern Android technologies including Jetpack Compose.

## Functionality
- Display all games in home screen by filter selection by genre, plus details screen for each game to see more details.

## Technologies

#### Languages:
- Kotlin 

#### User interface structure:
- Jetpack Compose

#### Architecture patterns:
- MVI, I choose mvi for better sperations of concern. each layer can handle it's purpose efficiency. model is data layer which contains business logic, view is ui layer and it's responsability for render ui only and the last is intent. intent which means user action on the screen.

#### Libraries:
- Retrofit
- Constraint Layout
- Coil
- Hilt
- gson
- moshi
- Hilt Navigation         

## Setup

- To run this project, install it by download or clone.
- Then regiser an account on this website https://rawg.io/apidocs to acquire a new api key.
- Enter this api key in Constants object under API_KEY field [path: data/local/Constants]


#### System requirements
- Android Studio Panda 2 | 2025.3.2
- Minimum sdk v24
- Target sdk v36
- Compile sdk v36

## License

```html
MIT Licence 

Copyright (c) 2026 Ahmed Zaki

Permission is hereby granted, free of charge, to any person obtaining a copy of this software
and associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, 
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial 
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
AND NONINFRINGEMENT.IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```
