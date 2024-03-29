# docker-compose-context
[![CI Linux](https://github.com/in-jex/docker-compose-context/actions/workflows/main-linux.yml/badge.svg?branch=master)](https://github.com/in-jex/docker-compose-context/actions/workflows/main-linux.yml) [![CI MacOS](https://github.com/in-jex/docker-compose-context/actions/workflows/main-macos.yml/badge.svg)](https://github.com/in-jex/docker-compose-context/actions/workflows/main-macos.yml)

Tired of having thousands of docker containers running on startup, just because you forgot to do `docker-compose down`? Controll them with `docker-compose-context`(`dcc`)! The tool allows you to register, control(up, down, restart), and monitor(todo) your `docker-compose` files from any terminal on your computer

## How to use
```shell
dcc register service_name ./docker-compose.yaml

# start all for service_name
dcc up service_name
# start only db for service_name
dcc up service_name -s db
# or
dcc up service_name:db
# show status of services running on service_name docker-compose
dcc ps service_name
# stop all for service_name
dcc down service_name
# or do both
dcc restart service_name
# de-register, remove from a list
dcc deregister service_name
# list all registered services
dcc list
# list available composer services to run(if you want to target e.g database)
dcc svc list service_name
```


## One-line installation
On MacOS:
```shell
curl -s https://raw.githubusercontent.com/in-jex/docker-compose-context/master/install.sh | sh
```
On Linux(just add sudo to sh command):
```shell
curl -s https://raw.githubusercontent.com/in-jex/docker-compose-context/master/install.sh | sudo sh
```

## Build locally
You need to have GraalVM and Maven installed
```shell
mvn package -Dnative
```

For linux, you can try to run the following without GraalVM
```
mvn package -Dnative -Dquarkus.native.container-build=true
```
