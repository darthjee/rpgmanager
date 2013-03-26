<?php

// no direct access
defined('_JEXEC') or die('Acesso Restrito');
?>

<?php
global $JUser
?>
<?php print_r($_POST); ?>
<div id='sublogin'>
  <div id='subs'>
    Registre-se abaixo
    <form id='fsubs'>
      <table>
        <tr>
          <td>
            Nick:
          </td><td>
            <input type='text' name="login" value='<?php echo $JUser->username?>' />
          </td>
        </tr>
        <tr>
          <td>
            Nome:
          </td><td>
            <input type='text' name="nome" value='<?php echo $JUser->name?>' />
          </td>
        </tr>
        <tr>
          <td>
            Nick:
          </td><td>
            <input type='text' name="email" value='<?php echo $JUser->email?>' />
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
      <input type="button" value="Registar" onclick="javascript:subscribe('#rpgmanager','fsubs')"/>
    </form>
    <a onclick="javascript:get_login_form('#sublogin');" href="#">Fa&ccedil;a o login</a>
  </div>
</div>
