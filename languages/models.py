from django.db import models
import types

# Create your models here.

class Language(models.Model):
    key = models.CharField(max_length=6, primary_key=True)
    
    def __unicode__(self):
        return self.key

    @staticmethod
    def getDefLangList():
        return Language.objects.all()
    

class Translation():
    id = 0
    ''' Classe de traducao de objeto '''
    object = None
    language = models.ForeignKey(Language)
    
    class Meta:
        unique_together = (("object", "language"),)
    
    @classmethod
    def getTranslate(cls, object_id, language_id=None):
        ''' retorna uma traducao de um objeto de id object_id '''
        object = None
        if language_id == None:
            languages = Language.getDefLangList()
        else:
            try:
                languages = [Language.objects.get(key=language_id)]
            except Language.DoesNotExist:
                raise Language.DoesNotExist
        for language in languages:
            try:
                object = cls.objects.get(object=object_id, language=language)
            except cls.DoesNotExist:
                continue
            break
        if object == None:
            raise cls.DoesNotExist
        return object



class Translated():
    ''' classe de objeto base a ser traduzido '''
    id=0
    translation = None
    
    def getTranslation(self, language_id=None):
        ''' retorna o objeto de traducao do objeto '''
        if (callable(self.translation)):
            t = self.translation.getTranslate(self.id, language_id);
            if (t != None):
                self.translation = t
                return t
            else:
                raise self.translation.DoesNotExist
        else:
            return self.translation
    
    def __unicode__(self):
        '''representacao de texto utilizando a traducao'''
        try:
            translation = self.getTranslation()
            return translation.__unicode__()
        except Exception:
            return "%d" % self.id
    
    def __getitem__(self, key):
        ''' retorna um atributo do objeto ou da traducao (fallback) '''
        try:
            value = getattr(self, key)
        except AttributeError:
            value = getattr(self.getTranslation(), key)
        return value