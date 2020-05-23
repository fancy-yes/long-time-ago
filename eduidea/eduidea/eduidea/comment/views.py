from django.shortcuts import redirect
from django.views.generic import TemplateView, View

from .forms import CommentForm


class CommentView(TemplateView):
    http_method_names = ['post']
    template_name = 'comment/result.html'  # 直接给出页面模板

    def post(self, request, *args, **kwargs):
        comment_form = CommentForm(request.POST)
        target = request.POST.get('target')
        '''评论合法则，予以保存到commit模型
        (注意：target字段不是在form表单中，
        所以需要两次保存分为两步，如下：)'''
        if comment_form.is_valid():
            instance = comment_form.save(commit=False)
            # 如果不填写个人链接，则默认为当前网页
            if not instance.website:
                instance.website = target
            instance.target = target
            instance.save()
            succeed = True
            return redirect(target)
        else:
            succeed = False

        '''如果评论不合法，这里需要给浏览器反馈一些信息。方法开头template_name已指明目标url'''
        context = {
            'succeed': succeed,
            'form': comment_form,
            'target': target,
        }
        return self.render_to_response(context)

