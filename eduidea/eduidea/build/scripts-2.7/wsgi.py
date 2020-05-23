"""
WSGI config for eduidea project.

It exposes the WSGI callable as a module-level variable named ``application``.

For more information on this file, see
https://docs.djangoproject.com/en/1.11/howto/deployment/wsgi/
"""

import os

from django.core.wsgi import get_wsgi_application

	# 拆分settings之后的修改 2行
profile = os.environ.get('EDUIDEA_PROFILE','develop')
os.environ.setdefault("DJANGO_SETTINGS_MODULE", "eduidea.settings.%s" % profile)

application = get_wsgi_application()
