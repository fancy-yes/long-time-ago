from django.contrib.auth.models import User
import mistune

from django.db import models

# Create your models here.

class Category(models.Model):
    STATUS_NORMAL = 1
    STATUS_DELETE = 0
    STATUS_ITEMS = ((STATUS_NORMAL,'正常'),
                    (STATUS_DELETE,'删除'),)

    name = models.CharField(max_length=50, verbose_name="名称")
    status = models.PositiveIntegerField(default=STATUS_NORMAL,
                                         choices=STATUS_ITEMS,
                                         verbose_name="状态")
    is_nav = models.BooleanField(default=False,
                                 verbose_name="是否为导航")
    owner = models.ForeignKey(User, verbose_name="作者")
    created_time = models.DateTimeField(auto_now_add=True, verbose_name="创建时间")
    

    class Meta:
        verbose_name = verbose_name_plural = "部门"

    def __str__(self):
        return self.name

    @classmethod
    def get_navs(cls):
        categories = cls.objects.filter(status=cls.STATUS_NORMAL)
        nav_categories = []
        normal_categories = []
        for cate in categories:
            if cate.is_nav:
                nav_categories.append(cate)
            else:
                normal_categories.append(cate)
        return {
            'navs': nav_categories,
            'categories': normal_categories,
        }


class Tag(models.Model):
    STATUS_NORMAL = 1
    STATUS_DELETE = 0
    STATUS_ITEMS = ((STATUS_NORMAL, '正常'),
                    (STATUS_DELETE, '删除'),)

    name = models.CharField(max_length=50, verbose_name="名称")
    status = models.PositiveIntegerField(default=STATUS_NORMAL,
                                         choices=STATUS_ITEMS,
                                         verbose_name="状态")
    owner = models.ForeignKey(User, verbose_name="作者")
    created_time = models.DateTimeField(auto_now_add=True, verbose_name="创建时间")

    class Meta:
        verbose_name = verbose_name_plural = "标签"

    def __str__(self):
        return self.name


class Post(models.Model):
    STATUS_NORMAL = 1
    STATUS_DELETE = 0
    STATUS_DRAFT = 2
    STATUS_ITEMS = ((STATUS_NORMAL, '正常'),
                    (STATUS_DELETE, '删除'),
                    (STATUS_DRAFT, '草稿'),)
    is_md = models.BooleanField(default=False, verbose_name="markdown格式")
    title = models.CharField(max_length=255, verbose_name="标题")
    desc = models.CharField(max_length=1024, blank=True, verbose_name="摘要")
    # 表单中为content，在保存时(重写save方法)转化为content_html字段（markdown格式）
    # 这里保留content的原因，是因为html格式的存储不便于下次修改。
    content = models.TextField(verbose_name="正文")
    content_html = models.TextField(verbose_name="正文html",blank=True,editable=False)
    status = models.PositiveIntegerField(default=STATUS_NORMAL,
                                         choices=STATUS_ITEMS,
                                         verbose_name="状态")
    category = models.ForeignKey(Category,verbose_name="部门")
    tag = models.ManyToManyField(Tag, verbose_name="标签")
    owner = models.ForeignKey(User, verbose_name="作者")
    created_time = models.DateTimeField(auto_now_add=True, verbose_name="创建时间")
    is_top = models.BooleanField(default=False,verbose_name="置顶")
	

    class Meta:
        verbose_name = verbose_name_plural = "文章"
        ordering = ['-id']

    @classmethod  # 用于置顶优先、时间倒叙的全部文章
    def get_by_all_posts(cls):
        queryset = cls.objects.filter(status = cls.STATUS_NORMAL).order_by('-is_top','-created_time')
        return queryset

    def __str__(self):
        return self.title


    @staticmethod
    def get_by_tag(tag_id):
        try:
            tag = Tag.objects.get(id=tag_id)
        except Tag.DoesNotExist:
            tag = None
            post_list =[]
        else:
            post_list = tag.post_set.filter(status = Post.STATUS_NORMAL)\
                .select_related('owner','category')

        return post_list, tag

    @staticmethod
    def get_by_category(category_id):
        try:
            category = Category.objects.get(id=category_id)
        except Category.DoesNotExist:
            category = None
            post_list = []
        else:
            post_list = category.post_set.filter(status=Post.STATUS_NORMAL)\
                .select_related('owner', 'category')

        return post_list, category


    @classmethod  # 最新文章
    def latest_posts(cls,num=5):
        queryset = cls.objects.filter(status = cls.STATUS_NORMAL).only('title','id').order_by('-pv')[:num]
        return queryset


    # 给Post模型增加两个字段pv、uv，用于统计每篇文章的访问量;同时绑定一个方法便于调用
    pv = models.PositiveIntegerField(default=1)
    uv = models.PositiveIntegerField(default=1)
    @classmethod
    def hot_posts(cls,num=5):
        return cls.objects.filter(status = cls.STATUS_NORMAL).only('title','id').order_by('-pv')[:num]

    # 重写save方法,转为正文格式为markdown或者富文本（都是Html代码）
    def save(self, *args,**kwargs):
        if self.is_md:
            self.content_html = mistune.markdown(self.content)
        else:
            self.content_html = self.content
        super().save(*args,**kwargs)


