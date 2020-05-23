from django.db.models import Q, F
from django.shortcuts import render
from django.views.generic import ListView,DetailView
from django.shortcuts import get_object_or_404
from datetime import date
from django.core.cache import cache

from .models import Post,Tag,Category
from config.models import SideBar
# Create your views here.


"""为类视图增加处理通用数据的类( 侧栏、导航)"""
class CommonViewMixin:
    def get_context_data(self,**kwargs):
        context = super().get_context_data(**kwargs)
        context.update({
            'sidebars':SideBar.get_all(),
        })
        context.update( Category.get_navs())
        return context

"""类视图实现主页,继承了CommonViewMixin类和listView类。
    首页视图查询了数据库中的全部文章，但是注意：querset是懒加载。
"""
class IndexView(CommonViewMixin,ListView):
    queryset = Post.get_by_all_posts() # 这里是有置顶优先的
    paginate_by = 5 # 分页
    context_object_name = 'post_list'
    template_name = 'blog/list.html'


class CategoryView(IndexView):
    def get_context_data(self,**kwargs):
        context = super().get_context_data(**kwargs)
        category_id = self.kwargs.get('category_id')
        category = get_object_or_404(Category, pk=category_id)
        context.update({
            'category':category,
        })
        return context

    def get_queryset(self):
        """重写queryset,根据部门分类过滤"""
        queryset = super().get_queryset()
        category_id = self.kwargs.get('category_id')
        return queryset.filter(category_id=category_id)


class TagView(IndexView):
    def get_context_data(self,**kwargs):
        context = super().get_context_data(**kwargs)
        tag_id= self.kwargs.get('tag_id')
        tag = get_object_or_404(Tag,pk=tag_id)
        context.update({
            'tag': tag,
        })
        return context

    def get_queryset(self):
        """重写queryset，根据标签分类"""
        queryset = super().get_queryset()
        tag_id = self.kwargs.get('tag_id')
        return queryset.filter(tag__id=tag_id)

# 虽然有评论模块,但是已抽象化为评论模块，所以没有在此重写评论相关的get_context_data
class PostDetailView(CommonViewMixin,DetailView):
    queryset = Post.get_by_all_posts()
    template_name = 'blog/detail.html'
    context_object_name = 'post'
    pk_url_kwarg = 'post_id'

    '''以下处理 访问统计'''
    def get(self, request, *args, **kwargs):
        response = super().get(request, *args, **kwargs)
        self.handle_visited()
        return response

    def handle_visited(self):
        increase_pv = False
        increase_uv = False
        uid = self.request.uid
        pv_key = 'pv:%s:%s' % (uid, self.request.path)
        if not cache.get(pv_key):
            increase_pv = True
            cache.set(pv_key, 1, 1*60)  # 1分钟有效

        uv_key = 'uv:%s:%s:%s' % (uid, str(date.today()), self.request.path)
        if not cache.get(uv_key):
            increase_uv = True
            cache.set(uv_key, 1, 24*60*60)  # 24小时有效

        if increase_pv and increase_uv:
            Post.objects.filter(pk=self.object.id).update(pv=F('pv') + 1, uv=F('uv') + 1)
        elif increase_pv:
            Post.objects.filter(pk=self.object.id).update(pv=F('pv') + 1)
        elif increase_uv:
            Post.objects.filter(pk=self.object.id).update(uv=F('uv') + 1)


class SearchView(IndexView):
    def get_context_data(self):
        context = super().get_context_data()
        context.update({
            'keyword': self.request.GET.get('keyword','')
        })
        return context

    def get_queryset(self):
        queryset = super().get_queryset()
        keyword = self.request.GET.get('keyword')
        keyword = keyword.strip()
        if not keyword:
            return queryset
        return queryset.filter(Q(title__icontains=keyword) | Q(desc__icontains = keyword))


class AuthorView(IndexView):
    def get_queryset(self):
        queryset = super().get_queryset()
        author_id = self.kwargs.get('owner_id')
        return queryset.filter(owner__id = author_id)



'''测试代码  url到view的数据映射 
from django.http import HttpResponse

def post_list(request, category_id = None, tag_id = None):
    content = 'post_list category_id={category_id}, tag_id={tag_id}'.format(
        category_id = category_id,
        tag_id = tag_id,
    )

    return HttpResponse(content)

def post_detail(request, post_id):
    return HttpResponse('detail')
'''

""" 这里是函数视图，已经废弃

def post_list(request, category_id = None, tag_id = None):
    tag = None
    category = None

    if tag_id:
        post_list, tag = Post.get_by_tag(tag_id)
    elif category_id:
        post_list, category = Post.get_by_category(category_id)
    else:
        post_list = Post.latest_posts()

    context = {
        'category': category,
        'tag':tag,
        'post_list':post_list,
        'sidebars':SideBar.get_all(),
    }
    context.update(Category.get_navs())
    return render(request, 'blog/list.html', context = context)

def post_detail(request, post_id):
    try:
        post = Post.objects.get(id = post_id)
    except Post.DoesNotExist:
        post = None

    context = {
        'post':post,
        'sidebars': SideBar.get_all(),
    }
    context.update(Category.get_navs())
    return render(request, 'blog/detail.html', context=context)

"""

