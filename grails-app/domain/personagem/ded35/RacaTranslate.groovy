/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package personagem.ded35

/**
 *
 * @author fvfavini
 */
class RacaTranslate extends Translate {
  Raca raca
  TipoTranslate tipo=TipoTranslate.R

   static belongsTo = [raca:Raca]

  static mapping = {
    discriminator value:'R'
  }

  static transients = [ "raca" ]

  Raca getRaca()
  {
    if (raca == null)
      raca = Raca.get(idObj)
    raca
  }

  void setRaca(Raca value){
    raca = value
    idObj = raca.id
  }

  static RacaTranslate getTrans(Integer idObjRel)
  {
    Translate.getTrans(idObjRel,TipoTranslate.R)
  }
}

