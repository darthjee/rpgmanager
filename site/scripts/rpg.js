<?php

// no direct access
defined('_JEXEC') or die('Acesso Restrito');

?>

var apage='<?php echo $RPGconf['apage']; ?>';

function get_subs_form(id)
{
  var login;

  login = document.forms.flogin.login.value;

  $(id).load(apage+'&ajax=subs div#subs', {'login':login});
}

function get_login_form(id)
{
  var login;

  login = document.forms.fsubs.login.value;

  $(id).load(apage+'&ajax=login div#login', {'login':login});
}

function subscribe(cid, fid)
{
  form = document.getElementById(fid);
  post = ({
      'login':form.login.value,
      'nome':form.nome.value,
      'email':form.email.value,
      'password':hex_md5(form.pass.value)
      });
  $(cid).load(apage+'&ajax=subs '+cid+'cont', post);
}
