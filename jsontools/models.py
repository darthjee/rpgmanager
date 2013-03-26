from django.db import models
from django.db.models.fields.files import ImageFieldFile
import types
import re

# Create your models here.

class jsontools():
    '''Tool to parse objects to jsons'''
    def __init__(self,json):
        self.json = jsontools.parseToDictionary(json)
    def jsonfy(self):
        return jsontools.jsonfyThis(self.json)
    
    @classmethod
    def parseToDictionary(cla,object):
        def parseArray(array):
            return [ cla.parseToDictionary(value) for value in array ]
        
        def parseObject(object):
            def isProp(object,prop):
                if re.match("^_", prop):
                    return False
                try:
                    attr = getattr(object, prop)
                except AttributeError:
                    return False
                if callable(attr):
                    return False
                return True
            return {prop:getattr(object, prop) for prop in dir(object) if isProp(object,prop)}
        
        def parseJsonfy(object):
            return object.__jsonfy__()
        
        def isJsonfy(object):
            try:
                f = getattr(object, '__jsonfy__')
                return callable(f)
            except AttributeError:
                return False
        
        if isJsonfy(object):
            return parseJsonfy(object)
        elif isinstance(object, list):
            return parseArray(object)
        elif isinstance(object, dict):
            return object
        elif object == None or type(object) == types.BooleanType or isinstance(object, (int,long,float)):
            return object
        return parseObject(object)
    
    @classmethod
    def jsonfyThis(cla,obj):
        '''Jsonfy and Dictionary or an Array'''
        def parseObject(value):
            if not isinstance(value,(int,long,float,complex,unicode,str,bool,None.__class__)):
                value = jsontools(value).jsonfy()
            elif type(value) == types.BooleanType:
                value = repr(value).lower()
            elif isinstance(value,(long)):
                value = re.sub('L$','',repr(value))
            elif value == None:
                value = 'null'
            elif isinstance(value, unicode):
                value=re.sub("'$",'"',re.sub("^u'",'"',repr(value)))
            elif type(value) != types.StringType:
                value = repr(value)
            else:
                value = '"%s"' % value
            return "%s" % value
        def isArray(obj):
            return isinstance(obj, list)
        def isDict(obj):
            return isinstance(obj, dict)
        
        if isDict(obj):
            return "{%s}" % ",".join(['"%s":%s' % (key,parseObject(value)) for (key,value) in obj.items() if value != None])
        elif isArray(obj):
            return "[%s]" % ",".join([parseObject(value) for value in obj])
        return jsontools(obj).jsonfy()
    
    
def f(self):
    return {'url':self.name}
ImageFieldFile.__jsonfy__ = f
del f