package personagem.ded35

import manager.PreferedLanguage
import manager.Language

class Translate {
  Integer id
  Integer idObj
  Language lang
  String nome
  String desc
  TipoTranslate tipo

  static mapping = {
    table 'ded35_translates'
    version false
    id column:'id_translate'
    idObj column:'id_obj'
    lang column:'lang'
    nome column:'nome'
    desc column:'descricao'
    tipo column:'tipo'
    discriminator formula:"case when tipo = 'R' then 'R' when tipo = 'C' then 'C' end", type:"string"
  }

  static constraints = {
    idObj(unique:["lang","tipo"],blank:false,nulable:false)
    lang(unique:false,blank:false,nulable:false,maxsize:6)
    nome(unique:false,blank:false,nulable:false,maxsize:100)
    desc(unique:false,nulable:true)
  }



  static Translate getTrans(Integer idObjRel, TipoTranslate tipoTrans){
    def trans
    def langs = PreferedLanguage.getPrefereLangListForUser(1)
    for (def l:langs)
    {
      if (trans == null)
        trans = Translate.find('from Translate t where t.lang.id=:lang AND t.idObj =:idObjRel AND t.tipo=:tipo',[lang:l.id,idObjRel:idObjRel,tipo:tipoTrans])
      else
        break;
    }
    if (trans == null)
    {
      trans = Translate.find('from Translate t where t.idObj =:idObjRel AND t.tipo=:tipo',[idObjRel:idObjRel,tipo:tipoTrans])
    }
    trans
  }
}
