package manager

class PreferedLanguage {
  Long id
  Usuario usuario
  Language idioma
  Short orderInd

  static mapping = {
    table 'prefered_lang'
    version false
    sort 'orderInd'
    id column:'id'
    usuario column:'id_usuario'
    idioma column:'lang'
    orderInd column:'orderInd'
  }

  static constraints = {
    usuario(unique:'idioma',nulable:false,blank:false)
    idioma(nulable:false,blank:false)
    orderInd(nulable:true,blank:true)
  }

  static List<Language> getPrefereLangListForUser(Integer id){
    getPrefereLangListForUser(Usuario.read(id))
  }
  static List<Language> getPrefereLangListForUser(Usuario user){
    def list = PreferedLanguage.findAllByUsuario(user)
    def finalList = []
    for (PreferedLanguage p:list)
      finalList.add(p.idioma)
    list = PreferedLanguage.findAllByUsuario(Usuario.findByLogin("modelo"))
    for (PreferedLanguage p:list)
      if (!finalList.contains(p.idioma))
        finalList.add(p.idioma)
    finalList
  }
}
