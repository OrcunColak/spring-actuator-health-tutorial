# Read me

Only one health indicator down makes the whole application's health status down

# Actuator Port

We can change the actuator port

```
management.server.port=8081
```

Access the actuator with  
http://localhost:8081/actuator/health/

The rest controllers still run on port 8080