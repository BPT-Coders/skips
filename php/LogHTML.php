<?php
class LogHTML{
	public $file;
	public function __construct($fDate, $date){
		$this->file = fopen('logs/'.$fDate.'-'.$date.'.html', "w");
		$text = '<!DOCTYPE><html><body><h1>Пропуски с '.$fDate.' по '.$date.'</h1><br>';
		fwrite($this->file, $text);
	}

	public function addStudent($text){
		$text = '<h3>'.$text.'</h3><br>';
		fwrite($this->file, $text);
	}
	
	public function addHours($date, $hours){
		$text = $date.' - '.$hours.'<br>';
		fwrite($this->file, $text);
	}
	
	public function __destruct() {
		$text = '</body></html>';
		fwrite($this->file, $text);
		fclose($this->file);
	}

}
?>