package personagem

import manager.*

class Personagem {
  Long id
  String nome
  String role
  String raca
  String desc
  Usuario owner

  static mapping = {
    table 'personagem'
    version false
    id column:'id_personagem'
    nome column:'nome'
    role column:'role'
    raca column:'raca'
    desc column:'descricao'
    owner column:'id_owner'
  }

  static constraints = {
    nome(maxsize:100,blank:false,unique:false,nulable:false)
    role(maxsize:100,blank:true,unique:false,nulable:true)
    raca(maxsize:100,blank:true,unique:false,nulable:true)
    desc(unique:false,nulable:true)
    owner(blank:true,unique:false,nulable:false)
  }

  String toString(){nome}
}
