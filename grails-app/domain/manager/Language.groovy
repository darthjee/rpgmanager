package manager

class Language {
  String id


  static transients = [ "code" ]

  static mapping = {
    table 'languages'
    version false
    id column:'lang', generator:'assigned'
  }

  String getCode(){ id }
  void setCode(String value){ id = value }


  static constraints = {
  }

  String toString(){ id }

  boolean equals(Language obj) { id.equals(obj.id) }
}
