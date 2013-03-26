package personagem.ded35

import personagem.*

class Ficha {
  Long id
  Personagem personagem
  Raca raca
  TipoPersonagem tipo

  static mapping = {
    table 'ded35_ficha'
    version false
    id column:'id_ficha'
    personagem column:'id_personagem'
    raca column:'id_raca'
    tipo column:'tipo'
  }

  static constraints = {
    personagem(unique:false,nulable:false,blank:false)
    raca(unique:false,nulable:false,blank:false)
    tipo(unique:false,nulable:false,blank:false)
  }
}
