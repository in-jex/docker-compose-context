from setuptools import setup

setup(
    name='docker-compose-context',
    version='0.1',
    py_modules=['dcc'],
    install_requires=[
        'Click',
        'click_aliases',
        'pyyaml'
    ],
    entry_points='''
        [console_scripts]
        docker-compose-context=dcc:cli
    ''',
)
