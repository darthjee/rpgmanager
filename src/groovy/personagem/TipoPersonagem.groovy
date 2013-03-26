package personagem
/**
 *
 * @author fvfavini
 */
enum TipoPersonagem {
  P("PC"),N("NPC"),M("Monster")

  final String value

  TipoPersonagem(String value) { this.value = value }

  String toString() { value }
  String getKey() { name() }
}

