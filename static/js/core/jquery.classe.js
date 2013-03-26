(function($){
	/**
	 * @brief buscaos dados sobre uma classe e completa a tabela da classe
	 */
	function fillClasse(json){
		var $this = this;
		
		if (typeof json == "number")
		{
			var id = json;
			$.ajax(fillClasse.baseUrl.replace(/\{id\}/,id), {
				dataType:'json',
				success:function(json){
					$this.fillClasse(json);
				}
			});
		}
		else
		{
			$this.parseClasse(json);
		}
	};
	/* URL de busca de dados (podendo ser modificada) */
	fillClasse.baseUrl = 'http://localhost:8000/ded35/classe/json/{id}/';
	$.fn.fillClasse = fillClasse;
	
	/**
	 * @brief funcao a ser chamada ja com o json carregado parseando o json gerando a tabela
	 */
	$.fn.parseClasse = function(json){
		var $table = this.find('.level-adv tbody');
		
		new fillTable($table, json);
	};
	
	/**
	 * @brief objeto de controle de preenchimento da tabela com os dados
	 */
	function fillTable(table, json, lvlIni, lvlFim){
		this.lvlIni = lvlIni ? lvlIni : 1;
		this.lvlFim = lvlFim ? lvlFim : 21;
		this.json = json;
		this.table = table;
		
		this.fill();
	}
	
	fillTable.prototype.fill = function(){
		var $table = this.table;
		for (var i = this.lvlIni; i < this.lvlFim; i++)
		{
			$table.append(this.createRow(i));
		}
	};
	
	/**
	 * @brief metodo de criacao de uma linha da tabela
	 */
	fillTable.prototype.createRow = function(lvl)
	{
		var $tr = $('<tr><th>'+lvl+'</th></tr>');
		var cols = this.json.powers ? this.json.powers : [];
		cols.unshift('bab',{key:'save', val:'will'},{key:'save', val:'fort'},{key:'save', val:'refl'});
		
		for (var i = 0; i < cols.length; i++)
		{
			var key = typeof cols[i] == "string" ? cols[i] : cols[i].key;
			var $td = this.createCol[key].call(this, lvl, cols[i]);
			$tr.append($td);
		}
		
		return $tr;
	};
	
	/**
	 * @brief pacote de funcoes para a criacao de colunas da tabela
	 */
	createCol = {}
	/**
	 * @brief criacao da coluna de BAB
	 */
	createCol.bab = function(lvl)
	{
		var $td = $('<td></td>');
		$td.text(Math.floor((this.json.bab+2)*lvl/4));
		return $td;
	};
	/**
	 * @brief funcao que calcula os saves
	 */
	createCol.save = function(lvl, type)
	{
		type = type.val;
		var $td = $('<td></td>');
		$td.text(Math.floor(this.json[type]*2 + (this.json[type]+2)*lvl/6));
		return $td;
	};
	fillTable.prototype.createCol = createCol;
	$.fn.parseClasse.fillTable = fillTable;
})(jQuery)
