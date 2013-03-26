/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package personagem.ded35

/**
 *
 * @author darthjee
 */
enum TipoTranslate {
  R("Raca"),C("Classe")

  final String value

  TipoTranslate(String value) { this.value = value }

  String toString() { value }
  String getKey() { name() }
}

