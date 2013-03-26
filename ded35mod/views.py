# Create your views here.
from django.http import HttpResponse, Http404
from models import *
from django.shortcuts import render_to_response
from django.template import RequestContext
from django.core import serializers
from jsontools.models import jsontools


def listClasses(request):
    basicas = ClasseDed35Mod.objects.filter(type='B')
    prestigio = ClasseDed35Mod.objects.filter(type='P')
    monstro = ClasseDed35Mod.objects.filter(type='M')
    
    context = {
       'classes':{
            'basicas':basicas,
            'prestigio':prestigio,
            'monstro':monstro,
        }
    }
    return render_to_response('ded35mod/templates/classeList.html', context, context_instance=RequestContext(request))
    


def showClasse(request, classe_id, language_id=None):
    try:
        classe = ClasseDed35ModTrans.getTranslate(classe_id, language_id)
    except Language.DoesNotExist, ClasseDed35ModTrans.DoesNotExist :
        raise Http404
    
    classe.object.setDefAvatar()
    context = {
        'classe':classe,
    }
    return render_to_response('ded35mod/templates/classeView.html', context, context_instance=RequestContext(request))



def jsonClasse(request, classe_id, lvlStart=1, lvlEnd=20):
    try:
        classe = ClasseDed35Mod.objects.get(id=classe_id)
    except ClasseDed35Mod.DoesNotExist:
        raise Http404
    json = jsontools(classe)
    json.json["features"] = classe.features.all()
    return HttpResponse(json.jsonfy())
