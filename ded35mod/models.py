from django.db import models
from ded35.core.models import Classe,TipoTalento,Talento,Feature,FeatureClasse
from languages.models import Language,Translation,Translated
##########  Translates ########


class ClasseDed35ModTrans(models.Model, Translation):
    object = models.ForeignKey('ClasseDed35Mod', related_name='+')
    language = models.ForeignKey(Language, related_name='+')
    nome = models.CharField(max_length=100)
    abreviatura = models.CharField(max_length=4)
    
    def __unicode__(self):
        return self.nome
    class Meta:
        abstract = False
    
    
    

class TipoTalentoDed35ModTrans(models.Model, Translation):
    object = models.ForeignKey('TipoTalentoDed35Mod', related_name='+')
    language = models.ForeignKey(Language, related_name='+')
    nome = models.CharField(max_length=100)
    
    def __unicode__(self):
        return self.nome
    class Meta:
        abstract = False
    


class TalentoDed35ModTrans(models.Model, Translation):
    object = models.ForeignKey('TalentoDed35Mod', related_name='+')
    language = models.ForeignKey(Language, related_name='+')
    nome = models.CharField(max_length=100)
    desc = models.CharField(max_length=256)
    
    def __unicode__(self):
        return self.nome
    class Meta:
        abstract = False
    


class FeatureDed35ModTrans(models.Model, Translation):
    object = models.ForeignKey('FeatureDed35Mod', related_name='+')
    language = models.ForeignKey(Language, related_name='+')
    nome = models.CharField(max_length=100)
    desc = models.CharField(max_length=256)
    
    def __unicode__(self):
        return self.nome

    

# Classes

class ClasseDed35Mod(Classe, Translated):
    avatar = models.ImageField(upload_to='./imgs/ded35/defines',blank=True)
    picture = models.ImageField(upload_to='./imgs/ded35/defines',blank=True)
    translation = ClasseDed35ModTrans
    displayAvatar = None
    
    class Meta:
        abstract = False
    
    def setDefAvatar(self):
        if (self.avatar.name == ''):
            self.displayAvatar = 'imgs/ded35/defines/hood.png'
        else:
            self.displayAvatar = self.avatar.name



class TipoTalentoDed35Mod(TipoTalento, Translated):
    translation = TipoTalentoDed35ModTrans
    class Meta:
        abstract = False
    


class TalentoDed35Mod(Talento, Translated):
    tipo = models.ForeignKey(TipoTalentoDed35Mod,related_name='+')
    translation = TalentoDed35ModTrans
    class Meta:
        abstract = False



class FeatureDed35Mod(Feature,Translated):
    translation=FeatureDed35ModTrans
    class Meta:
        abstract = False



class FeatureClasseDed35Mod(FeatureClasse):
    feature = models.ForeignKey(FeatureDed35Mod,related_name='+')
    classe = models.ForeignKey(ClasseDed35Mod, related_name='features')
    def __unicode__(self):
        return "%s (%s%s)" % (self.feature['nome'],self.classe['abreviatura'],self.lvl)