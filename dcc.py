import os
import re
import subprocess
from os.path import expanduser

import click
import yaml


def run(command):
    os.system(command)


def get_properties(file):
    with open(file, 'r') as property_file:
        return yaml.safe_load(property_file)


def get_path(name):
    home = expanduser("~")
    with open(f"{home}/.docker-compose-context", 'r') as property_file:
        lines = property_file.readlines()
        selected = [compose for compose in lines if name == compose.split('=')[0]]
    path = selected[0].strip('\n').split('=')[1]
    return path.strip('\n')


@click.group()
def cli():
    """Small CLI to manage your docker-compose nightmare"""
    pass


@click.command()
@click.argument('name')
@click.argument('composefile')
def register(name, composefile):
    """Add compose file to registry"""
    home = expanduser("~")
    abspath = os.path.abspath(composefile)
    with open(f"{home}/.docker-compose-context", 'a') as property_file:
        property_file.write(f"{name}={abspath}\n")


@click.command()
@click.argument('name')
def deregister(name):
    """Remove compose file from registry"""
    home = expanduser("~")
    with open(f"{home}/.docker-compose-context", 'r') as property_file:
        lines = property_file.readlines()
        selected = [compose for compose in lines if name == compose.split('=')[0]]
    print(selected)
    if len(selected) <= 0:
        click.echo('Nothing to de-register')
        return
    else:
        click.echo(f'Deregistering {selected[0]}...')

    with open(f"{home}/.docker-compose-context", 'w') as property_file:
        for line in lines:
            if line != selected[0]:
                property_file.write(line)


@click.command()
@click.argument('name')
@click.option('-s', '--service')
def up(name, service):
    """Start registered docker-compose"""
    do_up(name, service)


def do_up(name, service):
    filepath = get_path(name)
    dirpath = filepath[:(len(filepath) - len(os.path.basename(filepath)))]
    os.chdir(dirpath)
    run(f'docker-compose -f {filepath} up -d {service if service is not None else ""}')


@click.command()
@click.argument('name')
def down(name):
    """Stop registered docker-compose"""
    do_down(name)


def do_down(name):
    filepath = get_path(name)
    dirpath = filepath[:(len(filepath) - len(os.path.basename(filepath)))]
    os.chdir(dirpath)
    run(f'docker-compose -f {filepath} down')


@click.command()
@click.argument('name')
def restart(name):
    """Restart registered docker-compose"""
    do_down(name)
    do_up(name, None)


@click.command
@click.argument('name')
def get(name):
    """Get path to docker-compose via name"""
    click.echo(get_path(name))


@click.command
def list():
    """List all registered docker-compose files"""
    home = expanduser("~")
    with open(f"{home}/.docker-compose-context", 'r') as property_file:
        lines = property_file.readlines()
        click.echo('Contexts:')
        for line in lines:
            split = line.strip('\n').split('=')
            click.echo(f'\t{split[0]}:\t{split[1]}')


@click.group()
def svc():
    pass


@click.command('list')
@click.argument('name')
def svc_list(name):
    filepath = get_path(name)
    dirpath = filepath[:(len(filepath) - len(os.path.basename(filepath)))]
    services_ = get_properties(filepath)['services']
    service_names = [str(svc) for svc in services_]
    output = str(subprocess.check_output(['docker-compose', '-f', filepath, 'ps'])).split('\\n')
    active = []
    for svc in output[1:]:
        active_svc_name = re.split(r'\s+', svc)[0]
        active_svc_original_name = [service for service in service_names if service in active_svc_name]
        if len(active_svc_original_name) > 0:
            active.append(active_svc_original_name[0])

    click.echo(f'\033[1;32m{name}\033[0m services:')
    for service in services_:
        status = "\033[1;32m" if str(service) in active else "\033[1;31m"
        click.echo(f'\t{status}{service}\033[0m')


cli.add_command(register)
cli.add_command(deregister)
cli.add_command(up)
cli.add_command(down)
cli.add_command(restart)
cli.add_command(get)
cli.add_command(list)
cli.add_command(svc)
svc.add_command(svc_list)
