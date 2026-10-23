### Run

```
docker compose -f common.yml -f zookeeper.ym up
```

New terminal
```
echo ruok | nc localhost 2181
```

```
docker compose -f common.yml -f kafka_cluster.yml up
```

INIT topic
```
docker compose -f common.yml -f int_kafka.yml up
```


#### Go to localhost:9000 to check 