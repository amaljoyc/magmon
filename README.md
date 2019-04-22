# magmon

To run the app

```
./bootstrap.sh
```

Logging is done with console as well as under file `magmon.log`

#### Reporting API

```
http://localhost:8080/report/{rangeInMins}
```

Sample Request

```
http://localhost:8080/report/3
```

Sample Response

```
{"totalFailureCount":5,"failureRate":"13.888889%"}
```

#### Monitoring Frequency
configured with application property

```
monitor.frequency.inSeconds=5
```

#### Tech Stack

- Java 8
- Spring Boot 2
- H2 In-Memory Database
