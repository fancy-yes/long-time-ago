from django.contrib.admin import AdminSite

'''
这个需求： 用户模块的管理和部门分类等数据的管理分开
还需要修改@admin.register(Post)为@admin.register(Post，site=custom_site)
以及其他调整
'''
class Customsite(AdminSite):
    site_header = 'Eduidea教务网络社区 管理后台'
    site_title = 'Eduidea教务网络社区 管理后台'
    index_title = '首页'


custom_site = Customsite(name = 'cus_admin')
