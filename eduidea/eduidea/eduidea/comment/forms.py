from django import forms

import mistune # 第三方Markdown库
from ckeditor_uploader.widgets import CKEditorUploadingWidget


from .models import Comment

'''这里继承Django的ModelForm，随后在view中操作，最后在模板中被渲染为html的form'''
class CommentForm(forms.ModelForm):
    nickname = forms.CharField(
        label='昵称',
        max_length=20,
        widget=forms.widgets.Input(
            attrs={'class': 'form-control', 'style': "width: 60%;"}
        )
    )
    email = forms.CharField(
        required= False, # 这里是可选的，评论model里保持一直。
        label='Email(可选)',
        max_length=50,
        widget=forms.widgets.EmailInput(
            attrs={'class': 'form-control', 'style': "width: 60%;"}
        )
    )
    website = forms.CharField(
        required= False,
        label='个人链接(可选 默认为当前链接)',
        max_length=100,
        widget=forms.widgets.URLInput(
            attrs={'class': 'form-control', 'style': "width: 60%;"}
        )
    )

    # ck编辑器
    content = forms.CharField(widget=CKEditorUploadingWidget(),
                              label='正文',
                              required=True,
                              )


    ''' 自带编辑器
    content = forms.CharField(
        label="内容",
        max_length=500,
        widget=forms.widgets.Textarea(
            attrs={'rows': 6, 'cols': 60, 'class': 'form-control'}
        )
    )
    '''
    """这个是表单验证的。
    1：限制输入评论的长度
    2：在返回给数据库前，将数据格式转换为markdown类型(废弃)
    """
    def clean_content(self):
        content = self.cleaned_data.get('content')
        if len(content) < 4:
            raise forms.ValidationError('内容长度太短！！')
        # 这个开关是 Markdown 的支持
        # content = mistune.markdown(content)
        return content
    class Meta:
        model = Comment
        fields = ['nickname', 'email', 'website','content']
