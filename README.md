# MDP JSON Parser

This library provides a Java implementation for parsing JSON messages used in the MDP (Multi-Disciplinary Project) communication protocol.

## Installation

### Maven

To use this library in your Maven project, add the following dependency to your `pom.xml`:

```xml
<dependency>
    <groupId>com.github.zeon256</groupId>
    <artifactId>mdp-json-parser</artifactId>
    <version>1.2.0</version>
</dependency>
```

### Gradle

For Android projects using Gradle, add the following to your build.gradle file:

```gradle
repositories {
    maven {
        url "https://maven.pkg.github.com/zeon256/mdp-json-parser"
        credentials {
            username = "YOUR_GITHUB_USERNAME"
            password = "YOUR_GITHUB_PERSONAL_ACCESS_TOKEN" // make sure that this token only has read package access
        }
    }
}

dependencies {
    implementation 'com.github.zeon256:mdp-json-parser:1.2.0'
}
```

## Usage
1. Set up Gson with custom type adapters:

```java
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.github.zeon256.mdp.*;

Gson gson = new GsonBuilder()
    .registerTypeAdapter(Mdp.class, new MdpDeserializer())
    .registerTypeAdapter(InfoValue.class, new InfoValueDeserializer())
    .registerTypeAdapter(ErrorValue.class, new ErrorValueDeserializer())
    .registerTypeAdapter(StatusValue.class, new StatusValueDeserializer())
    .registerTypeAdapter(ParameterizedControlValue.class, new ParameterizedControlValue.Serializer())
    .create();
```

2. Parse JSON message into Mdp object:

```java
String json = "{\"cat\":\"info\",\"value\":\"Robot is ready!\"}";
Mdp<?> message = gson.fromJson(json, Mdp.class);

System.out.println(message.getCat()); // Outputs: INFO
System.out.println(message.getValue()); // Outputs: Robot is ready!
```

3. Create and serialize Mdp object into JSON message:

```java
LocationValue locationValue = new LocationValue(1, 2, 3);
Mdp<LocationValue> message = new Mdp<>(Mdp.Cat.LOCATION, locationValue);

String json = gson.toJson(message);
System.out.println(json);
// Outputs: {"cat":"location","value":{"x":1,"y":2,"d":3}}
```

4. Handle different message types:

```java
public void handleMessage(Mdp<?> message) {
    switch (message.getCat()) {
        case INFO:
            System.out.println("Info: " + message.getValue());
            break;
        case ERROR:
            System.out.println("Error: " + message.getValue());
            break;
        case LOCATION:
            LocationValue loc = (LocationValue) message.getValue();
            System.out.printf("Location: (%d, %d, %d)%n", loc.getX(), loc.getY(), loc.getD());
            break;
        case STATUS:
            System.out.println("Status: " + message.getValue());
            break;
        case OBSTACLES:
            ObstaclesValue obstacles = (ObstaclesValue) message.getValue();
            System.out.println("Obstacles: " + obstacles.getObstacles());
            break;
        case IMAGE_REC:
            ImageRecValue imageRec = (ImageRecValue) message.getValue();
            System.out.printf("Image Recognition: ID %s, Obstacle %s%n",
                    imageRec.getImageId(), imageRec.getObstacleId());
            break;
        case CONTROL:
            System.out.println("Control: " + message.getValue());
            break;
    }
}
```

## Custom Type Adapters

This library includes custom type adapters for proper serialization and deserialization of MDP messages:

- `MdpDeserializer`: Handles deserialization of the main Mdp class.
- `InfoValueDeserializer`: Deserializes InfoValue enums.
- `ErrorValueDeserializer`: Deserializes ErrorValue enums.
- `StatusValueDeserializer`: Deserializes StatusValue enums.
- `ParameterizedControlValue.Serializer`: Handles both serialization and deserialization of ParameterizedControlValue objects.

Make sure to register these type adapters with your Gson instance as shown in the usage example.


