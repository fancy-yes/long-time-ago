import os.path
import sys
NOW_DIR = os.path.dirname(os.path.abspath(__file__)) # 当前目录的绝对地址
sys.path.insert(0,os.path.dirname(NOW_DIR)) # 将上层目录加入sys.path

from django.db import models

from blog.models import  Post



# Create your models here.
# Create your models here.

class Comment(models.Model):
    STATUS_NORMAL = 1
    STATUS_DELETE = 0
    STATUS_ITEMS = ((STATUS_NORMAL,'正常'),
                    (STATUS_DELETE,'删除'),)
    target = models.CharField(max_length= 150, verbose_name="评论目标")
    content = models.CharField(max_length=2000, verbose_name="内容")
    nickname = models.CharField(max_length=50, verbose_name="昵称")
    # 网站和邮箱可选字段
    website = models.URLField(verbose_name="网站",blank=True)
    email = models.EmailField(verbose_name="邮箱",blank=True)
    status = models.PositiveIntegerField(default=STATUS_NORMAL,
                                         choices=STATUS_ITEMS,
                                         verbose_name="状态")
    created_time = models.DateTimeField(auto_now_add=True, verbose_name="创建时间")

    class Meta:
        verbose_name = verbose_name_plural = "评论"

    def __str__(self):
        return self.nickname


    @classmethod
    def get_by_target(cls, target):
        return cls.objects.filter(target=target, status = cls.STATUS_NORMAL)

    @classmethod
    def get_by_lasted_comment(cls,num=5):
        return cls.objects.filter(status = cls.STATUS_NORMAL)[: num]


