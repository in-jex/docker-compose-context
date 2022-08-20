unameOut="$(uname -s)"
case "${unameOut}" in
    Linux*)     release=docker-compose-context-linux;;
    Darwin*)    release=docker-compose-context-macos;;
    *)          release=docker-compose-context-linux
esac
wget -O /usr/local/bin/dcc https://github.com/in-jex/docker-compose-context/releases/download/0.1.0/${release}
chmod a+x /usr/local/bin/dcc
echo "\033[0;36m"
echo  ".__          __    __        __"
echo  "|__|  ____   \ \   \ \      |__|  ____  ___  ___"
echo  "|  | /    \   \ \   \ \     |  |_/ __ \ \  \/  /"
echo  "|  ||   |  \  / /   / /     |  |\  ___/  >    <"
echo  "|__||___|  / /_/   /_/  /\__|  | \___  >/__/\_ \\"
echo  "         \/             \______|     \/       \/"
echo "\033[0;32m"
echo "Congrats, your dcc(docker-compose-context) is installed"
echo "Start by opening a folder with your docker-compose.yaml and call:"
echo "\033[1;36m"
echo "====="
echo "dcc register service_name ./docker-compose.yaml"
echo "====="
echo "\033[0;32m"
echo "You can later start/stop it from any terminal window in any folder"
echo "\033[1;36m"
echo "====="
echo "dcc up service_name"
echo "dcc down service_name"
echo "====="
echo "\033[0;32m"
echo "For more commands use \033[1;33m'dcc'\033[0;32m command"
echo "\033[0m"
