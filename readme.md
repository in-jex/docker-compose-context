# docker-compose-context

A simple tool to never forget what docker-compose did you ran

## One-line zsh installation
```shell
 bash <(curl -s https://raw.githubusercontent.com/in-jex/docker-compose-context/master/install.sh)
```

## Install locally
```shell
pip3 install -r requirements.txt
python3 setup.py install
```

```shell
echo "alias dcc=docker-compose-context" >> ~/.zshrc

dcc register service_name ./docker-compose.yaml

# start all for service_name
dcc up service_name
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
# start only db for service_name
dcc up service_name -s db
```
