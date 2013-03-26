<?php

/**
 * @file gamer_class.php
 * @brief Controla o cadastro de gamers
 */

// no direct access
defined('_JEXEC') or die('Acesso Restrito');
?>

<?php

/**
 * @brief Classe de controle dos usuarios 
 */
class Gamer
{
  var $id_gamer;
  var $nick;
  var $passwd;
  var $valido;
  var $id_joomla;

  /**
   * @brief busca se um usario é ou nao cadastrado
   */
  function is_regis($id_joomla=null)
  {
    global $JUser;
    global $RPGDB;
    global $DEBUG;

    if (!$id_joomla)
      $id_joomla = $JUser->id;

    $query="select * from 'public'.'gamers' where id_joomla=$id_joomla limit 1";
    $query = 'SELECT "id_gamer" FROM "public"."Gamers"';
    $bool = $RPGDB->check_query($query, $DEBUG);

    return $bool;
  }
}
?>
