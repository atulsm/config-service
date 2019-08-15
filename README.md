# ConfigService

Scope
---

1. Create a config service which is a simple key value store of buckets

Usage
---

1. Lookup a config => GET /bucket/esconfig 
2. Create a new config => POST /bucket/esconfig -d '{ip:192.1.0.0}'
3. Delete a config => DELETE /bucket/esconfig

How to start the ConfigService application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/configservice-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8088/healthcheck`

TODO
---

1. Create a simple UI to manage the config
