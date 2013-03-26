package manager

/**
 *
 * @author darthjee
 */
enum TipoPessoa {
  U("Usuario"),S("Sistema"),D("Dummy")

  final String value

  TipoPessoa(String value) { this.value = value }

  String toString() { value }
  String getKey() { name() }
}


