# Hoover Spring Boot App

## Description

Spring boot api receiving instructions to hoover a room. 
The input instructions as well as the results are saved to a embedded h2 database.

## Running the App

### Using Docker



## Usage

### To send instructions:

#### Request:
```
curl [...]
```
#### Response:
```
{
  "coords" : [1, 3],
  "patches" : 1
}
```

## Database

The `h2` database contains two tables, `input` and `result`,

![input table](input_table.png?raw=true "input Table")
![result table](result_table.png?raw=true "result Table")

The two tables can be joined via a foreign key (input_id) in the result table:
```sql
SELECT input.id as run_id, input.start_position, result.end_position, input.directions, input.patches_in_room, input.room_size, result.cleaned_patches
FROM RESULT
join input on input.id = result.input_id 
```

![joined table](joined_table.png?raw=true "joined Table")

## Tests



## FAQ

## Ideas for improvement

### Code
- use interceptor to write automatically to the db on every request
  - this would add entries even though the request never entered the hooverService.start() method
- write errors to the database
- refactor the converters and remove code duplication
- add swagger doc

### Scalability
- create cloud instance for the database (e.g. AWS RDS)
- run this API in kubernetes (e.g. on AWS EKS). Scaling horizontally, accessing same RDS (which also can be scaled)

# Original task description starting now

Yoti SDK Back-end test
======================

## Introduction
You will write a service that navigates a imaginary robotic hoover (much like a Roomba) through an equally imaginary room based on:

- room dimensions as X and Y coordinates, identifying the top right corner of the room rectangle. This room is divided up in a grid based on these dimensions; a room that has dimensions X: 5 and Y: 5 has 5 columns and 5 rows, so 25 possible hoover positions. The bottom left corner is the point of origin for our coordinate system, so as the room contains all coordinates its bottom left corner is defined by X: 0 and Y: 0.
- locations of patches of dirt, also defined by X and Y coordinates identifying the bottom left corner of those grid positions.
- an initial hoover position (X and Y coordinates like patches of dirt)
- driving instructions (as cardinal directions where e.g. N and E mean "go north" and "go east" respectively)

The room will be rectangular, has no obstacles (except the room walls), no doors and all locations in the room will be clean (hoovering has no effect) except for the locations of the patches of dirt presented in the program input.

Placing the hoover on a patch of dirt ("hoovering") removes the patch of dirt so that patch is then clean for the remainder of the program run. The hoover is always on - there is no need to enable it.

Driving into a wall has no effect (the robot skids in place).

## Goal
The goal of the service is to take the room dimensions, the locations of the dirt patches, the hoover location and the driving instructions as input and to then output the following:

- The final hoover position (X, Y)
- The number of patches of dirt the robot cleaned up

The service must persist every input and output to a database.

## Input
Program input will be received in a json payload with the format described here.

Example:

```javascript
{
  "roomSize" : [5, 5],
  "coords" : [1, 2],
  "patches" : [
    [1, 0],
    [2, 2],
    [2, 3]
  ],
  "instructions" : "NNESEESWNWW"
}
```

## Output
Service output should be returned as a json payload.

Example (matching the input above):

```javascript
{
  "coords" : [1, 3],
  "patches" : 1
}
```

Where `coords` are the final coordinates of the hoover and patches is the number of cleaned patches.

## Deliverable
The service:

- is a web service
- must run on Mac OS X or Linux (x86-64)
- must be written in any of the languages that we support with our SDKs (Java, C#, Python, Ruby, PHP, Node, Go)
- can make use of any existing open source libraries that don't directly address the problem statement (use your best judgement).

Send us:

- The full source code, including any code written which is not part of the normal program run (scripts, tests)
- Clear instructions on how to obtain and run the program
- Please provide any deliverables and instructions using a public Github (or similar) Repository as several people will need to inspect the solution

## Evaluation
The point of the exercise is for us to see some of the code you wrote (and should be proud of).

We will especially consider:

- Code organisation
- Quality
- Readability
- Actually solving the problem

This test is based on the following gist https://gist.github.com/alirussell/9a519e07128b7eafcb50


