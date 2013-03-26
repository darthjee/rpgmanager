<?php

defined('_JEXEC') or die('Acesso Restrito');
?>

<?php
require_once('inc/bd_pg_class.php');
require_once("inc/gamer_class.php");
require_once('rpg-conf.php');

if(!$ajax)
{
?>
  <head>
  <style type="text/css">
    <?php include('templates/rpg.css'); ?>
  </style>
  <script src="scripts/jquery-1.2.6.js" type="text/javascript"></script>
  <script language="javascript" type="text/javascript">
  <!--
    <?php include($PGconf['basedir'].'/scripts/rpg.js') ?>
  -->
  </script>
  </head>
<?php
}

?><div id='rpgmanager'><div id='rpgmanagercont'><?php

/* quando o jogador eh registrado, podemos comecar a abrir as
   funcionalidades para ele */
if (Gamer::is_regis() || $ajax=='subs') /* checagem de cadastro */
{
  if (in_array($ajax,$ajaxes))
  {
    include("ajaxes/$ajax.php");
  }
  /* se o modulo for um modulo de verdade, este sera exibido */
  else if (in_array($rpg_modulo, $rpg_modulos))
  {
    include("modulos/$rpg_modulo.php");
  }
}
/* se o jogador ainda nao se registrou, deve faze-lo agora */
else
  include("templates/login.php");

?></div></div>
