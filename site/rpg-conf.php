<?php

defined('_JEXEC') or die('Acesso Restrito');
?>

<?php
global $BDconf;
global $RPGDB;
global $JUser;
global $PGconf;
global $DEBUG;
global $RPGconf;

$DEBUG = true;
$RPFconf['pass'] = '';
$RPGconf['user'] = '';
$RPGconf['host'] = '';
$RPGconf['banco'] = '';

$RPGconf['comdirstr'] = "components/com_rpgmanager";

$diraux = $_SERVER['PHP_SELF'];
ereg("\/.*\/",$diraux,$diraux);
$RPGconf['comdir'] = $diraux[0].$RPGconf['comdirstr'];

$RPGconf['jdir'] = '/var/www/joomla';
$RPGconf['basedir'] = $RPGconf['jdir'].'/'.$RPGconf['comdirstr'];
$RPGconf['tpdir'] = $RPGconf['basedir'].'/templates';

$RPGconf['pgparam'] = "?";
foreach ($_GET as $key => $arg)
{
  if ($key != 'ajax')
    $RPGconf['pgparaux'][] ="$key=$arg";
}
$RPGconf['pgparam'].= implode($RPGconf['pgparaux'],'&');

$RPGconf['page'] = "index.php{$RPGconf['pgparam']}";
$RPGconf['apage'] = "index2.php{$RPGconf['pgparam']}";

$BDconf = $RPGconf;
$PGconf = $RPGconf;

$RPGDB = new  BDPGClass();
$JUser = JFactory::getUser();

$rpg_modulos = array(
    'editor',
    'dms',
    'player'
    );

$rpg_modulo = $_GET['rpg_modulo'];

$ajaxes = array(
    'subs',
    'login',
    );

$ajax = $_GET['ajax'];
?>
