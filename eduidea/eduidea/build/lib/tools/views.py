from django.http import HttpResponse
import qrcode
from django.utils.six import BytesIO
# Create your views here.

def qrcodeView(request,data):
    data = request.scheme+'://'+request.get_host()+data
    print(request.get_host())
    img = qrcode.make(data)
    buf = BytesIO()
    img.save(buf)
    img_stream = buf.getvalue() #Return bytes containing the entire contents of the buffer.
    response = HttpResponse(img_stream,content_type='image/png')
    return response

