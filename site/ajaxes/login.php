<?php

// no direct access
defined('_JEXEC') or die('Acesso Restrito');
?>

<?php
$login = $_POST['login'];
$pass = $_POST['pass'];

include($PGconf['tpdir'].'/login.php');
?>
