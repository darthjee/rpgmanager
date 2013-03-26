/**
 * @file Crypto.java
 * @brief contem a classe @link formularios::Crypto Cryrypto @endlink
 */

package manager;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @brief classe abstrata com metodos de criptografia
 */
public abstract class Crypto {

  /**
   * @brief algoritmo para extrair o hash MD5Sum de uma String
   * O algoritimo abaixo foi extraido do site
   * http://www.programei.org/index.php/05/02/2010/md5-em-java/
   * @param senha : a string a ser trabalhada
   * @return uma String com o MD5
   */
  public static String md5(String senha){
    String sen = "";
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    BigInteger hash = new BigInteger(1, md.digest(senha.getBytes()));
    sen = hash.toString(16);
    return sen;
  }
}
