## Micronaut 4.4.2 Documentation
- [User Guide](https://docs.micronaut.io/4.4.2/guide/index.html)
- [API Reference](https://docs.micronaut.io/4.4.2/api/index.html)
- [Configuration Reference](https://docs.micronaut.io/4.4.2/guide/configurationreference.html)
- [Micronaut Guides](https://guides.micronaut.io/index.html)

---

- [Shadow Gradle Plugin](https://plugins.gradle.org/plugin/com.github.johnrengelman.shadow)
- [Micronaut Gradle Plugin documentation](https://micronaut-projects.github.io/micronaut-gradle-plugin/latest/)
- [GraalVM Gradle Plugin documentation](https://graalvm.github.io/native-build-tools/latest/gradle-plugin.html)

## Feature serialization-jackson documentation
- [Micronaut Serialization Jackson Core documentation](https://micronaut-projects.github.io/micronaut-serialization/latest/guide/)

## Feature micronaut-aot documentation
- [Micronaut AOT documentation](https://micronaut-projects.github.io/micronaut-aot/latest/guide/)

# Running app
```bash
./gradlew run
```

# Building app
```bash
./gradlew clean shadowJar
```

# Building docker image
```bash
docker build . -t micronaut-hello-world:v0.0.1 --no-cache --progress=plain
```

# Running docker image
```bash
# Create a container with the given image
# `-d` - Run in detached mode (in the background)
# If :v0.0.1 is not provided, docker will default to "latest"
docker run -d -p 8080:8080 micronaut-hello-world:v0.0.1
```

# Moving from development to production
```bash
# Exporting docker image
docker save micronaut-hello-world:v0.0.1 > micronaut-hello-world.tar

# Loading from saved image
docker load < micronaut-hello-world.tar
```

# Example
### GET rquest
```bash
# Get multiple element
http://localhost:8080/fruit/api/all

# Get single element
http://localhost:8080/fruit/api/{id}

# Example GET request
curl http://localhost:8080/fruit/api/all
```

### POST
```bash
http://localhost:8080/fruit/api/newFruit

# Body
{
  "name": "pear",
  "price": 4.4
}
```

### PUT
```bash
http://localhost:8080/fruit/api/4

# Body
{
  "name": "mango",
  "price": 5.5
}
```

### DELETE
```bash
http://localhost:8080/fruit/api/4
```

# Common issue
## gradlew: not found
- Ensure gradlew is saved with `LF` line sequence instead of `CRLF`