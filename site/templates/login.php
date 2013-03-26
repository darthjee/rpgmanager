<?php

// no direct access
defined('_JEXEC') or die('Acesso Restrito');
?>

<?php
global $JUser
?>

<div id='sublogin'>
  <div id='login'>
    Se voce esta registrado, fa&ccedil;a o seu login
    <form id='flogin'>
      <table>
        <tr>
          <td>
            Nick:
          </td><td>
            <input type='text' name="login" value='<?php echo $JUser->name?>' />
          </td>
        </tr>
        <tr>
          <td>
            PassWord:
          </td><td>
            <input type='password' name="pass" value="" />
          </td>
        </tr>
      </table>
      <input type="button" value="Login" />
    </form>
    <a onclick="javascript:get_subs_form('#sublogin');" href="#">Registre-se</a>
  </div>
</div>
