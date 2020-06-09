# kumbaya-hhs

This is a software project created for [Kumbaya Inc](https://kumbaya.co/) during the 2020 Summer European Project Semester at The Hague University of Applied Sciences taking place from February to July 2020. It is compatible with the IoT device created by the same students as a second part of the solution for the company. The purpose of this software is to combine practical and educational platform for farmers in Malawi, Africa.
>
- Practical: Defining the type of soil the farmer has, providing prices at which the farmer can sell their crop
- Educational: Videos explaining how to use the IoT device, how to use fertilizers and a list of fertilizers for crops suitable for Malawi 

## Table Of Contents 
- [Overview](#overview)
- [Features](#features)
- [Tests](#tests)
- [Future Development](#development)
- [Contributing](#contributing)
- [Authors](#authors)

## Overview
Basic information about the project:
>
- Written in Java and [JavaFX](https://openjfx.io/)
- Created using [Maven](https://maven.apache.org/)
- Created using Layered Architecture Pattern with following layers: Data, Logic and Presentation
- Current Version (As of 12/06/2020) is not final

## Features
Main features of the project include:
>
- [Defining the soil](#soil)
- [Displaying educational videos](#videos)
- [Checking prices of the crops](#prices)

## Soil
The IoT device made for the purpose of this project is responsible for measuring the state of the following soil parameters: pH, N (Nitrogen), P (Phosphorus), K (Potassium). The soil data for particular user is sent to the cloud database (Azure CosmosDB). 
> 
The software retrieves the latest uploaded data for the particular user using the following query:
>

```SQL
SELECT TOP 1 * FROM c
WHERE c.userid = userid
AND c.measureDate <= GetCurrentDateTime() 
ORDER BY c.measureDate DESC

```
Where userid is a parameter provided in the function

```java
public SoilMeasurement readSoilMeasurement(String userid)
```
The measurement is retrieved with the use of FeedResponse from [CosmosDB documentation](https://docs.microsoft.com/en-us/azure/cosmos-db/sql-api-sdk-java)

```java
Iterator<FeedResponse<CosmosItemProperties>> responseIterator = container.queryItems(query, queryOptions);
        responseIterator.forEachRemaining( response -> {
response.results().forEach( item -> {
                SoilMeasurement sm = new SoilMeasurement(item.getString("id"), item.getString("userid"), item.getString("measureDate"), item.getDouble("nParam"), item.getDouble("pParam"), item.getDouble("kParam"), item.getDouble("pHParam"));
                measurements.add(sm);
            });
        });
```


## Tests
The project uses [JUnit](https://junit.org/junit5/) for unit tests.
> 
The most crucial methods for the software in Data and Logic module are tested in the test directory.

## Development
Future development includes plan to:
>
- Implement log-in or user specific system instead of hardcoded user
- Implement Asynchronous communication instead of Synchronous for faster performance
- Implement ListView of fertilizers
- Implement the system of crop size = crop price


## Contributing
This project is not open for contribution.

## Authors
[Ariana Lamar](https://github.com/alam97)
