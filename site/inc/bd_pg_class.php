<?php
/**
 * @file bd_pg_class.php
 * Classe de banco de dados @ref BDPGClass
 */

// no direct access
defined('_JEXEC') or die('Acesso Restrito');
?>

<?php
/**
 * @brief Classe Banco de Dados do tipo PostGres
 *
 * Toda a comunicação com o banco de dados passa
 * atraves deste objeto.
 * A configuracao deste objeto exige que ele seja
 * instanciado
 */
class BDPGClass
{
  /** @brief senha do banco de dados */
  var $Pass;
  /** @brief usuario do banco de dados */
  var $User;
  /** @brief servidor do banco de dados */
  var $Host;
  /** @brief nome do banco do banco de dados */
  var $Banco;

  /** @brief ID do link com o banco */
  var $LinkID = 0;
  /** @brief ID da ultima Query */
  var $QueryID = 0;

  /** @brief codigo de erro */
  var $Errno = 0;
  /** @brief string de identificacao do erro */
  var $Error = "";

  /** @brief Array contendo os resultados tabelados */
  var $Results = array();
  /** @brief numero de linhas dos resultados */
  var $Rows = 0;
  /** @brief Quantas linhas formam afetadas no ultimo update/insert */
  var $Affected = 0;
  /** @brief ID retornado por uma operacao de insercao */
  var $NewID = 0;


  /**
   * @brief conecta ao banco de dados
   */
  function connect() 
  {
    $this->Error = "";
    $this->Errno = 0;
    $this->Rows = 0;
    $this->Affected = 0;
    $this->NewID = 0;
    $this->QueryID = 0;
    $this->Results = array();

    if (0 == $this->LinkID || pg_connection_status($this->LinkID)) {
      $host = $this->Host;
      $user = $this->User;
      $pass = $this->Pass;
      $banco = $this->Banco;

      $this->LinkID = pg_connect("host=$host user=$user password=$pass dbname=$banco");
      if (!$this->LinkID) {
	$this->halt("Link-ID == false, falha de conexão");
      }
    } 
  }

  /**
   * @brief para o sistema em caso de erro
   */
  function halt($msg)
  {
    die("$msg<br>Sistem indisponivel, tente mais tade<br>Sessao encerrada.<br>");
  }

  /**
   * @brief faz uma busca no banco retornando
   * o id da busca
   * @see BDClass::update
   * @see BDClass::insert
   * @see BDClass::delete
   */
  function query($query_str, $debug=null)
  {
    $this->connect();

    $this->QueryID = pg_query($this->LinkID, $query_str);
    $this->Rows = pg_num_rows($this->QueryID);
    $this->Error = pg_result_error($this->QueryID);
    $this->Errno = pg_result_status($this->QueryID);

    if ($debug)
    {
      ?><div class="debug"><?php
        echo "$query_str<br>";
        echo "Error:{$this->Error}<br>";
        echo "Errno:{$this->Errno}<br>";
      ?></div><?php
    }

    if ($this->Error)
    {
      pg_errormessage($this->LinkID);
      echo "<br>";
      $this->halt($this->Error);
    }

    return $this->QueryID;
  }


  /**
   * @brief Faz uma busca retornando o numero de rows
   * encontrados;
   */
  function check_query($querystr, $debug=null)
  {
    $this->query($querystr, $debug);
    return $this->Rows;
  }


  /**
   * @brief Fecha a conexao com o banco
   */
  function close()
  {
    if ($this->LinkID)
      pg_close($this->LinkID);
    $this->LinkID = 0;
  }

  /**
   * @brief executa uma busca e retorna o resultado
   * em uma tabela
   */
  function query_to_table($query_str, $debug=null)
  {
    $this->query($query_str, $debug);

    while($row = pg_fetch_array($this->QueryID))
      $this->Results = array_merge($this->Results,array($row));

    return $this->Results;
  }

  /**
   * @brief executa uma busca e retorna o resultado
   * em um array
   */
  function query_to_row($query_str, $debug=null)
  {
    $this->query($query_str, $debug);
    $this->Results[0] = pg_fetch_array($QueryID, 0, PGSQL_ASSOC);

    return $this->Results[0];
  }

  /**
   * @brief executa uma busca e retorna o resultado
   * em um valor
   */
  function query_to_val($query_str, $debug=null)
  {
    $Row = $this->query_to_row($query_str, $debug);

    if ($Row)
      $val = array_shift($Row);
    else
      $val = null;
    return $val;
  }

  /**
   * @brief faz uma operacao de update atualizando
   * as propriedades certas do Objeto
   * @param $update : string SQL de update
   * @see BDClass::query
   * @see BDClass::insert
   * @see BDClass::delete
   */
  function update($update)
  {
    $this->connect();

    $this->QueryID = pg_query($update);
    $this->Error = pg_result_error($this->QueryID);
    $this->Errno = pg_result_status($this->QueryID);
    $this->Affected = pg_affected_rows($this->LinkID);

    return $this->Affected;
  }

  /**
   * @brief insere uma nova linha na tabela
   * atualizando as propriedades certas do
   * Objeto;
   * @param $insert : string SQL de insert
   * @see BDClass::query
   * @see BDClass::update
   * @see BDClass::delete
   */
  function insert($insert)
  {
    $this->connect();

    $this->QueryID = pg_query($insert);
    $this->Error = pg_result_error($this->QueryID);
    $this->Errno = pg_result_status($this->QueryID);
    $this->Affected = pg_affected_rows($this->LinkID);
    $this->NewID = pg_last_oid($this->LinkID);

    return $this->NewID;
  }

  /**
   * @brief fazz uma operacao de delete sql
   * atualizando as proprieades certas do banco
   * @param $insert : string SQL de insert
   * @see BDClass::query
   * @see BDClass::update
   * @see BDClass::insert
   */
  function delete($delete)
  {
    $this->connect();

    $this->QueryID = pg_query($delete);
    $this->Error = pg_result_error($this->QueryID);
    $this->Errno = pg_result_status($this->QueryID);
    $this->Affected = pg_affected_rows($this->LinkID);
    $this->NewID = pg_last_oid($this->QueryID);

    return $this->Affected;
  }

  /**
   * @brief ajusta os parametros iniciais do banco
   * @see BDClass
   */
  function set_param_banco($pass=null, $user=null, $host=null, $banco=null)
  {
    global $BDconf;

    if ($pass)
      $this->Pass=$pass;
    else if (!$this->Pass)
      $this->Pass=$BDconf['pass'];

    if ($user)
      $this->User=$user;
    else if (!$this->User)
      $this->User=$BDconf['user'];

    if ($host)
      $this->Host=$host;
    else if (!$this->Host)
      $this->Host=$BDconf['host'];

    if ($banc)
      $this->Banco=$banco;
    else if (!$this->Banco)
      $this->Banco=$BDconf['banco'];
  }

  /**
   * @brief copia os parametros de um banco para outro
   * @see BDClass
   */
  function cp_param_banco($BancoOBJ)
  {
    $this->Pass=$BancoOBJ->Pass;
    $this->User=$BancoOBJ->User;
    $this->Host=$BancoOBj->Host;
    $this->Banco=$BancoOBJ->Banco;
  }

  /**
   * @brief inicializa uma instancia de @ref BDClass
   */
  function BDPGClass($pass=null, $user=null, $host=null, $banco=null)
  {
    $this->set_param_banco($pass, $user, $host, $banco);
  }

}

?>
