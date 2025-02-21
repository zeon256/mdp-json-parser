# MDP JSON Parser

This library provides a Java implementation for parsing JSON messages used in the MDP (Multi-Disciplinary Project) communication protocol.

## Installation

### Maven

To use this library in your Maven project, add the following dependency to your `pom.xml`:

```xml
<dependency>
  <groupId>com.github.zeon256</groupId>
  <artifactId>mdp-json-parser</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>
```

### Gradle

For Android projects using Gradle, add the following to your build.gradle file:

```gradle
repositories {
    maven {
        url "https://maven.pkg.github.com/zeon256/mdp-json-parser"
    }
}

dependencies {
    implementation 'com.github.zeon256:mdp-json-parser:1.0-SNAPSHOT'
}
```

## Usage

1. Parse JSON message into Mdp object:

```java
import com.google.gson.Gson;
import com.github.zeon256.mdp.Mdp;

Gson gson = new Gson();
String json = "{\"cat\":\"info\",\"value\":{\"infoValue\":\"Robot is ready!\"}}";
Mdp message = gson.fromJson(json, Mdp.class);

System.out.println(message.getCat()); // Outputs: INFO
System.out.println(message.getValue().getInfoValue()); // Outputs: Robot is ready!
```

2. Create and serialize Mdp object into JSON message:

```java
import com.google.gson.Gson;
import com.github.zeon256.mdp.Mdp;
import com.github.zeon256.mdp.Value;
import com.github.zeon256.mdp.LocationValue;

Gson gson = new Gson();
Mdp message = new Mdp();
message.setCat(Mdp.Cat.LOCATION);

Value value = new Value();
LocationValue locationValue = new LocationValue(1, 2, 3);
value.setLocationValue(locationValue);
message.setValue(value);

String json = gson.toJson(message);
System.out.println(json);
// Outputs: {"cat":"location","value":{"locationValue":{"x":1,"y":2,"d":3}}}
```

3. Handle different message types:

```java
import com.github.zeon256.mdp.Mdp;
import com.github.zeon256.mdp.Value;

public void handleMessage(Mdp message) {
    switch (message.getCat()) {
        case INFO:
            System.out.println("Info: " + message.getValue().getInfoValue());
            break;
        case ERROR:
            System.out.println("Error: " + message.getValue().getErrorValue());
            break;
        case LOCATION:
            LocationValue loc = message.getValue().getLocationValue();
            System.out.printf("Location: (%d, %d, %d)%n", loc.getX(), loc.getY(), loc.getD());
            break;
        // Handle other message types...
    }
}

```