git clone https://github.com/in-jex/docker-compose-context.git
cd ./docker-compose-context
pip3 install -r requirements.txt
python3 setup.py install
echo "alias dcc=docker-compose-context" >> ~/.zshrc
echo "Congrats, your dcc(docker-compose-context) is registered and ready to be used"
echo "Start by opening a folder with your docker-compose.yaml and call:"
echo "====="
echo "dcc register ./docker-compose.yaml service_name"
echo "====="
echo "You can later start/stop it from any terminal window in any folder"
echo "====="
echo "dcc up service_name"
echo "dcc down service_name"
echo "====="
echo "For more commands use $(dcc) command"
