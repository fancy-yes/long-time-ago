"""eduidea URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/1.11/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  url(r'^$', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  url(r'^$', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.conf.urls import url, include
    2. Add a URL to urlpatterns:  url(r'^blog/', include('blog.urls'))
"""
import xadmin
from django.conf.urls import url,include
from django.conf import settings
from django.conf.urls.static import static
from django.contrib import admin
from blog.rss import LatestPostFeed
from .autocomplete import CategoryAutocomplete,TagAutocomplete

from blog.views import (IndexView,CategoryView,
                        TagView,PostDetailView,
                        SearchView,AuthorView)
from config.views import LinkListView
from comment.views import CommentView
from tools.views import qrcodeView
from .custom_site import custom_site

'''添加name，在其他地方使用reverse函数，url反向解析，即解码思想'''
urlpatterns = [
    
    url(r'^qrcode(.+)$', qrcodeView, name='qrcode'),
    url(r'^category-autocomplete/$', CategoryAutocomplete.as_view(), name='category-autocomplete'),
    url(r'^tag-autocomplete/$', TagAutocomplete.as_view(), name='tag-autocomplete'),
    url(r'^ckeditor/', include('ckeditor_uploader.urls')),
    url(r'rss|feed/',LatestPostFeed(),name='rss'),
    url(r'^comment/$',CommentView.as_view(),name='comment'),
    url(r'^links/$', LinkListView.as_view(),name='links'),
    url(r'^author/(?P<owner_id>\d+)/$', AuthorView.as_view(),name='author'),
    url(r'^search/$', SearchView.as_view(),name = 'search'),
    url(r'^$', IndexView.as_view(), name='index'),
    url(r'^category/(?P<category_id>\d+)/$', CategoryView.as_view(),name='category-list'),
    url(r'^tag/(?P<tag_id>\d+)/$', TagView.as_view(),name='tag-list'),
    url(r'^post/(?P<post_id>\d+).html$', PostDetailView.as_view(),name='post-detail'),
    # url(r'^super_admin/', admin.site.urls,name='super-admin'),
    # url(r'^admin/', custom_site.urls,name='admin'),
    url(r'^admin/', xadmin.site.urls,name='xadmin'),
] + static(settings.MEDIA_URL, document_root=settings.MEDIA_ROOT)





"""这里是 函数视图  已废弃


from django.conf.urls import url
from django.contrib import admin

from eduidea.custom_site import custom_site
from eduidea.super_site import super_site
from blog.views import post_list,post_detail
from config.views import links


'''添加名字，其他地方使用reverse函数，url反向解析，即解耦码思想'''
urlpatterns = [
    url(r'^$', post_list, name='index'),
    url(r'^category/(?P<category_id>\d+)/$', post_list,name='category-list'),
    url(r'^tag/(?P<tag_id>\d+)/$', post_list,name='tag-list'),
    url(r'^post/(?P<post_id>\d+).html$', post_detail,name='post-detail'),
    url(r'^links/$',links,name='links'),
    #url(r'^super_admin/', super_site.urls,name='super-admin'),
    url(r'^super_admin/', admin.site.urls,name='super-admin'),
    url(r'^admin/', custom_site.urls,name='admin'),
]

"""
