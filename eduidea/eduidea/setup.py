# coding:utf-8
from setuptools import setup, find_packages


setup(
    name='eduidea',
    version='0.1',
    description='CMS base on Django',
    author='xujia',
    author_email='xujia028@qq.com',
    url='https://xujia028.cn',
    license='MIT',
    packages=find_packages('eduidea'),
    package_dir={'': 'eduidea'},
    # package_data={'': [    # 打包数据文件，方法一
        # 'themes/*/*/*/*',  # 需要按目录层级匹配
    # ]},
    include_package_data=True,  # 方法二 配合 MANIFEST.in文件
    install_requires=[
        'backports.csv==1.0.7',
        'colorama==0.4.1',
        'defusedxml==0.6.0',
        'diff-match-patch==20181111',
        'Django==1.11.28',
        'django-autocomplete-light==3.2.10',
        'django-ckeditor==5.4.0',
        'django-crispy-forms==1.7.2',
        'django-formtools==2.1',
        'django-import-export==1.2.0',
        'django-js-asset==1.2.2',
        'django-reversion==3.0.3',
        'et-xmlfile==1.0.1',
        'future==0.17.1',
        'httplib2==0.9.2',
        'image==1.5.27',
        'jdcal==1.4.1',
        'mistune==0.8.4',
        'odfpy==1.4.0',
        'openpyxl==2.6.2',
        'Pillow==5.1.0',
        'pytz==2019.1',
        'PyYAML==5.1',
        'qrcode==6.1',
        'six==1.12.0',
        'tablib==0.13.0',
        'xadmin==0.6.1',
        'xlrd==1.2.0',
        'xlwt==1.3.0',

],
    scripts=[
        'eduidea/manage.py',
        'eduidea/eduidea/wsgi.py',
    ],
    entry_points={
        'console_scripts': [
            'eduidea_manage = manage:main',
        ]
    },
    classifiers=[  # Optional
        # How mature is this project? Common values are
        #   3 - Alpha
        #   4 - Beta
        #   5 - Production/Stable
        'Development Status :: 3 - Alpha',

        # Indicate who your project is intended for
        'Intended Audience :: Developers',
        'Topic :: Blog :: Django CMS',

        # Pick your license as you wish
        'License :: OSI Approved :: MIT License',

        # Specify the Python versions you support here. In particular, ensure
        # that you indicate whether you support Python 2, Python 3 or both.
        'Programming Language :: Python :: 3.6',
    ],

)
