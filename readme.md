# docker-compose-context

A simple tool to never forget what docker-compose did you ran

## [MACOS INTEL] One-line zsh installation
```shell
 zsh <(curl -s https://raw.githubusercontent.com/in-jex/docker-compose-context/master/install.sh)
```

## Install locally
You need to have GraalVM and Maven installed
```shell
mvn install -Pnative
```
## How to use
```shell
dcc register service_name ./docker-compose.yaml

# start all for service_name
dcc up service_name
# start only db for service_name
dcc up service_name -s db
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
