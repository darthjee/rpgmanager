# Create your views here.
from django.http import HttpResponse, Http404
from models import *
from django.shortcuts import render_to_response
from django.template import RequestContext
from django.core import serializers




def showClasse(request, classe_id, language_id=None):
    try:
        classe = ClasseDed35Trans.getTranslate(classe_id, language_id)
    except Language.DoesNotExist,ClasseDed35Trans.DoesNotExist :
        raise Http404
    
    classe.classe.setDefAvatar()
    context = {
        'classe':classe,
    }
    return render_to_response('ded35/templates/classeView.html', context, context_instance=RequestContext(request))



def jsonClasse(request, classe_id):
    try:
        classe = ClasseDed35.objects.get(id=classe_id)
    except ClasseDed35.DoesNotExist:
        raise Http404
    json = serializers.serialize("json", [classe])
    return HttpResponse(json)
