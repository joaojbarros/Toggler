

# Toggler ![alt text](https://api.travis-ci.org/joaojbarros/Toggler.svg?branch=master)
The Toggler Application provide apis for manager toggles for features from services/applications.
The clients sends only yours identifiers and versions, then they can view all features and the hierarchy of toggles for that feature.

The Toggler Applicaton provide on the GET api the reduce concept with two query strings, that are:
```html
-fields
```
Represents the fields that you not want on response body.
Ex: 
```shell
http://toggler.joaojbarros.com/toggler/services?-fields=serviceFeatures.serviceTogglesDefinitions,serviceFeatures.featureName
```
This call return all fields from model entity without serviceFeatures.serviceTogglesDefinitions and serviceFeatures.featureName
```html
fields
```
Represents the fields that you want on response body
Ex.: 
```shell
http://toggler.joaojbarros.com/toggler/services?fields=serviceFeatures.featureName,serviceId
```
This call return only the serviceId and featureName from the entity serviceFeatures
## Package
Use: mvn package
## Running
### Running as a Packaged Application
```shell
$ java -jar target/toggler-0.0.1-SNAPSHOT.jar
```

It is also possible to run a packaged application with remote debugging support enabled. Doing so lets you attach a debugger to your packaged application, as shown in the following example:

```shell
$ java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n \
       -jar target/toggler-0.0.1-SNAPSHOT.jar
```
### Using the Maven Plugin
```shell
$ mvn spring-boot:run
```
## Swagger
http://toggler.joaojbarros.com/swagger-ui.html
![alt text](https://raw.githubusercontent.com/joaojbarros/images-readme/master/swagger.png)

## Watcher
![alt text](https://raw.githubusercontent.com/joaojbarros/images-readme/master/view.png)

## CI - Travis
https://travis-ci.org/joaojbarros/Toggler

## Implemented
### Authentication/Authorization
### SQL Database for user and roles
### NoSQL for toggles and services definitions
### Payload reduce on call
### Http headers with cache controll
### Hypermedia - Hateos
### Swagger definitions
### Automation/Unit/Integration Testing


## Not implemented - need improve
### Easy way to change the hostname on Hateos Link

