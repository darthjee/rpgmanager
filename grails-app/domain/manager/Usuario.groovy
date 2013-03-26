package manager


class Usuario {
  Integer id
  String login
  String senha
  String novaSenha
  String email
  Boolean ativo
  TipoPessoa tipo


  static mapping = {
    table 'usuario'
    version false
    id column:'id_user'
    login column:'login',index:'login_index',unique:true,sqlType:"varchar(100)"
    senha column:'senha',sqlType:"char(32)"
    email column:'email',index:'email_index',unique:true
    ativo column:'ativo',index:'ativo_index',unique:false
    tipo column:'tipo',index:'tipo_index',unique:false
  }

  static transients = [ "novaSenha" ]

  static constraints = {
    login(blank:false,unique:true,nulable:false,maxsize:99)
    senha(blank:false,unique:false,nulable:true,password:true)
    novaSenha(blank:true,nulable:true,password:true)
    email(blank:false,unique:true,nulable:true,password:false,email:true)
    ativo(blank:false,nulable:false)
    tipo(blank:false,nulable:false)
  }

  String toString(){ login }
  String getNovaSenha(){ novaSenha }
  void setNovaSenha(String value){
    String aux = Crypto.md5(value);
    if (!(value == null || value.trim().equals("")))
      novaSenha = aux;
  }
  String getSenha(){
    if (!(novaSenha == null || novaSenha.trim().equals("")))
      return novaSenha;
    else
      return senha
  }
  void setSenha(value){
    if (senha == null || senha.trim().equals(""))
      senha = value;
    else
      setNovaSenha(value)
  }
  Boolean getAtivo(){ if (ativo == null) return false; else ativo }
}
