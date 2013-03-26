from django.db import models



class Classe(models.Model):
    types = (('B','Basic'),('P','Prestige'),('M','Monster'),)
    
    bab = models.SmallIntegerField()
    will = models.SmallIntegerField()
    fort = models.SmallIntegerField()
    refl = models.SmallIntegerField()
    dice = models.SmallIntegerField()
    skill = models.SmallIntegerField()
    type = models.CharField(max_length=1, choices=types, default='B', null=True,blank=True)
    
    class Meta:
        abstract = True



class TipoTalento(models.Model):    
    class Meta:
        abstract = True
    
    

class Talento(models.Model):
    tipo = TipoTalento
    class Meta:
        abstract = True



class Feature(models.Model):
    power = models.CharField(max_length=32, blank=True)
    column = models.BooleanField()
    class Meta:
        abstract = True



class FeatureClasse(models.Model):
    feature = Feature
    classe = Classe
    powerArgs = models.CharField(max_length=32, blank=True)
    lvl = models.SmallIntegerField()
    repeat = models.SmallIntegerField()
    class Meta:
        abstract = True