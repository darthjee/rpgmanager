package personagem.ded35

import org.springframework.context.i18n.LocaleContextHolder  as LCH

class Raca {
  Integer id
  String nome
  RacaTranslate trans



  static mapping = {
    table 'ded35_raca'
    version false
    id column:'id_raca'
  }
  static constraints = {
  }
  static transients = [ "nome","trans" ]

  String toString(){if (getNome() != null) nome; else id}

  String getNome(){
    if (nome == null)
    {
      nome = getTrans()?.nome;
      if (trans == null) nome=id
    }
    nome
  }

  RacaTranslate getTrans(){
    if (trans == null)
      trans = RacaTranslate.getTrans(this.id)
    trans
  }
}
