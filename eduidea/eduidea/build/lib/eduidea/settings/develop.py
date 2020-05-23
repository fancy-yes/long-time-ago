from .base import *
import pymysql

# SECURITY WARNING: don't run with debug turned on in production!
# 开发环境中开启debug，线上环境应该关闭
DEBUG = True

ALLOWED_HOSTS = ['*'] # 任何人都可以访问
# Database
# https://docs.djangoproject.com/en/1.11/ref/settings/#databases

DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.sqlite3',
        'NAME': os.path.join(BASE_DIR, 'db.sqlite3'),
    }
}
'''
DATABASES = {
    'default': {
        'ENGINE': 'django.db.backends.mysql',
        'NAME': 'eduidea',
        'USER':'root',
        'PASSWORD':'123456',
        'HOST':'localhost',
        'PORT':3306,
    }
}


'''



