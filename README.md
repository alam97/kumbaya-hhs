# kumbaya-hhs

This is a software project created for [Kumbaya Inc](https://kumbaya.co/) during the 2020 Summer European Project Semester at [The Hague University of Applied Sciences](https://www.thehagueuniversity.com/) taking place from February to July 2020. It is compatible with the IoT device created by the same students as a second part of the solution for the company. The purpose of this software is to combine practical and educational platform for farmers in Malawi, Africa.
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
- [Database communication and functions](#database)
- [Defining the soil](#algorithm)
- [Displaying educational videos](#videos)
- [Checking prices of the crops](#prices)

## Database
This project is compatible with [Microsoft Azure CosmosDB](https://docs.microsoft.com/en-us/azure/cosmos-db/sql-api-sdk-java) based on SQL. The models of entities (containers) are defined in the ```datamodel``` package of the Data layer. The same layer contains the ```DataProvider``` class that is reponsible for:

- Establishing and closing the connection to the database
- Retrieving the data
- Updating the item in the database

The methods and data types used in this project are meant for Synchronous communication.
> CosmosSyncClient, CosmosSyncDatabase, CosmosSyncContainer etc.

### Establishing and closing the connection
The Data layer contains a resouce bundle ```databasekeys``` with endpoint and URI keys for the database, as well its name and the names of the containers. 

Inside the ```DataProvider``` class, the bundle is defined:
```java
    private ResourceBundle bundle = ResourceBundle.getBundle("databasekeys");
```

The methods ```connectToDB``` and ```close``` are designed to establish and close the connection respectively.
The connection is established by creating a ```CosmosSyncClient``` instance and using the ```getDatabase``` method from [CosmosDB documentation](https://docs.microsoft.com/en-us/azure/cosmos-db/sql-api-sdk-java):

```java
client = new CosmosClientBuilder()
                .endpoint(EndpointUri)
                .key(AuthKey)
                .connectionPolicy(defaultPolicy)
                .consistencyLevel(ConsistencyLevel.EVENTUAL)
                .buildSyncClient();
                
database = client.getDatabase(databaseName);
```
The connection is closed using the ```close``` method for the ```CosmosSyncClient```.

### Retrieving the data
Data retrieving happens inside the ```DataProvider``` class. Each retrieving (read) method uses FeedResponse from [CosmosDB documentation](https://docs.microsoft.com/en-us/azure/cosmos-db/sql-api-sdk-java).

> Example of Soil Measurement retrieving:
```java
Iterator<FeedResponse<CosmosItemProperties>> responseIterator = container.queryItems(query, queryOptions);
        responseIterator.forEachRemaining( response -> {
response.results().forEach( item -> {
                SoilMeasurement sm = new SoilMeasurement(item.getString("id"), item.getString("userid"), item.getString("measureDate"), item.getDouble("nParam"), item.getDouble("pParam"), item.getDouble("kParam"), item.getDouble("pHParam"));
                measurements.add(sm);
            });
        });
```

Where ```query``` is a String of a SQL query 
> Example query of the latest Soil Measurement retrieving:

```SQL
SELECT TOP 1 * FROM c
WHERE c.userid = userid
AND c.measureDate <= GetCurrentDateTime() 
ORDER BY c.measureDate DESC
```
Where ```userid``` is the parameter of the user's id provided to the method

```java
public SoilMeasurement readSoilMeasurement(String userid)
```
### Updating the item in the database
CosmosDB does not support ```UPDATE``` SQL statement, it only supports ```SELECT```, therefore items must be upserted/inserted. In the software ```updateSoilType``` method is used to update the item in the database after having the Soil Measurement go through the soil defining algorithm. It is done by using the ```upsertItem``` method for ```CosmosSyncContainer```.

```java
public void updateSoilType(SoilMeasurement soilMeasurement) throws Exception {
        queryOptions.populateQueryMetrics(true);
        CosmosSyncContainer container = database.getContainer(bundle.getString("soilmeasurement"));
        container.upsertItem(soilMeasurement);
    }
```

## Algorithm
The IoT device made for the purpose of this project is responsible for measuring the state of the following soil parameters: pH, N (Nitrogen), P (Phosphorus), K (Potassium). The soil data for particular user is sent to the cloud database (Azure CosmosDB). The software retrieves the data, passes it through a soil defining algorithm and updates the database with the type of the soil the user has.

The latest soil measurement as well as min and max ranges are stored in the database and retrieved using the ```DataProvider``` methods

It is possible to define the type of the soil (low, medium or high fertility) based on the ranges of the pH and NPK parameters. The research done by the Kumbaya HHS team prove that pH is the parameter that holds greater importance when deciding on the soil type. Since N, P, K are measured within the same unit, it is reasonable to calculate the harmonic mean of that subset. Then, to ultimately decide the type of soil, a weighted mean of the subsets should be used, giving the prevalent weight to the pH. Calculating minimum and maximum possible values for every soil type, there are left 3 sets of ranges the result of the device’s measurement can be classified into, naming soil Low, Medium or High fertility type.

Logic layer class ```SoilDefiner``` is responsible for defining the soil based on the parameters from the Soil Measurement. The method ```defineSoil``` implements the algorithm that uses weighted and harmonic means. 

```java
 double harmonic_mean = 3 / (1 / soilMeasurement.getkParam() + 1 / soilMeasurement.getnParam() + 1 / soilMeasurement.getpParam());
        double weighted_mean = (pH_weight * soilMeasurement.getpHParam() + npk_weight * harmonic_mean) / (pH_weight + npk_weight);
```
The possible solutions are put into a ```List``` and filtered if there exists more than one

```java
     if (possibleSolutions.size() == 1) {
            soilMeasurement.setSoiltype(possibleSolutions.get(0).getSoiltype());
        } else if (possibleSolutions.size() > 1) {
            for (Range range : possibleSolutions) {
                if (range.getMin() == weighted_mean) {
                    soilMeasurement.setSoiltype(range.getSoiltype());
                }
            }

        }
        else throw new Exception();
```
Then the update method is called

```java
        dataLogicProvider.updateSoilType(soilMeasurement);
```
Because the communication with the database requires time, in order not to confuse the potential user about the screen not changing into the result of their soil analysis, a ```MeasurementPreloader``` class was implemented in the Presentation layer using ```threads``` and methods ```run``` to retrieve the data and ```Platform.runLater``` to switch the screen to an appropriate result image.

## Videos
Educational videos were implemented using ```MediaView``` and ```WebView``` types from the [JavaFX documentation](https://docs.oracle.com/javase/8/javafx/api/toc.htm). The mp4 file of the animation about how to use the sensor of the IoT device prepared by the Kumbaya HHS team is included in the ```resources/videos```, therefore ```MediaView``` was implemented. 

```java
    @FXML
    private MediaView mv;
    private MediaPlayer mp;
    private Media me;
    
    @FXML
    private void initialize() {
        me = new Media(this.getClass().getResource("videos/HowToUseSensor.mp4").toString());
        mp = new MediaPlayer(me);
        mv.setMediaPlayer(mp);
        mp.setAutoPlay(true);
        }
```
The source of educational videos about fertilizers was YouTube, therefore ```WebView``` was implemented. The embed links to each YouTube video is stored in the resource bundle ```cropvideos``` 

```java
private ResourceBundle bundle = ResourceBundle.getBundle("org/kumbaya/hhs/videos/cropvideos");
```
and chosen appropriately depending on which crop the user chooses, the key is the argument of the constructor of ```FertilizerVideoController```.

```java
 FertilizerVideoController(String key) {
        this.key = bundle.getString(key);
        this.goBackScene = key;
    }
```
The initialize() method puts appropriate video into the ```webView``` after the ```FXMLLoader``` is set as root.
```java
 @FXML
    private void initialize() {

        webview.getEngine().loadContent(key);
        webview.setContextMenuEnabled(true);
       ...
    }
```

## Prices
As of 12/06/2020, the Prices feature is not implemented. The database contains dummy data of prices for each crop and the ```readPrice``` method is implemented in Data layer's ```DataProvider```.

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
