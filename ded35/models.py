from django.db import models

from core.models import Classe,TipoTalento,Talento
from languages.models import Language, Translation

##########  Classes Ded35 ########


class ClasseDed35(Classe):
    avatar = models.ImageField(upload_to='./imgs/ded35/defines',blank=True)
    picture = models.ImageField(upload_to='./imgs/ded35/defines',blank=True)
    displayAvatar = None
    
    def __unicode__(self):
        try:
            classe = ClasseDed35Trans.getTranslate(self.id)
            return classe.nome
        except Exception:
            return "%d" % self.id
    
    def setDefAvatar(self):
        if (self.avatar.name == ''):
            self.displayAvatar = 'imgs/ded35/defines/hood.png'
        else:
            self.displayAvatar = self.avatar.name



class TipoTalentoDed35(TipoTalento):
    def __unicode__(self):
        return self.getNome()
    def getNome(self):
        try:
            tipo = TipoTalentoDed35Trans.getTranslate(self.id)
            return tipo.nome
        except Exception:
            return "%d" % self.id
    


class TalentoDed35(Talento):
    tipo = models.ForeignKey(TipoTalentoDed35)
    def __unicode__(self):
        return self.getNome()
    def getNome(self):
        try:
            talento = TalentoDed35Trans.getTranslate(self.id)
            return talento.nome
        except Exception:
            return "%d" % self.id




##########  Translates ########


class ClasseDed35Trans(models.Model, Translation):
    classe = models.ForeignKey(ClasseDed35)
    language = models.ForeignKey(Language)
    nome = models.CharField(max_length=100)
    abreviatura = models.CharField(max_length=4)
    class Meta:
        unique_together = (("classe", "language"),)
    
    def __unicode__(self):
        return self.nome



class TipoTalentoDed35Trans(models.Model, Translation):
    tipo = models.ForeignKey(TipoTalentoDed35)
    language = models.ForeignKey(Language)
    nome = models.CharField(max_length=100)
    class Meta:
        unique_together = (("tipo", "language"),)
    
    def __unicode__(self):
        return self.nome
    


class TalentoDed35Trans(models.Model, Translation):
    talento = models.ForeignKey(TalentoDed35)
    language = models.ForeignKey(Language)
    nome = models.CharField(max_length=100)
    desc = models.CharField(max_length=256)
    class Meta:
        unique_together = (("talento", "language"),)
    
    def __unicode__(self):
        return self.nome