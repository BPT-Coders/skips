<?php

require_once 'connect.php';
require_once 'LogHTML.php';
$connect = new connect();

//Выбираем временной интервал	
$today = date("Y-m-d");
$fromDay = strtotime('-7 days', strtotime($today));
$fromDay = date('Y-m-j', $fromDay);
$log = new LogHTML($fromDay, $today);

//загружаем список студентов
$i = 0;
$connect->qr("select * from `students`;");
while($data = mysql_fetch_array($connect->qr_res)){
	$students[$i][0] = $data['id'];
	$students[$i][1] = $data['lastName'];
	$students[$i][2] = $data['parentPhone'];
	$i++;
	}

//Перебираем каждого студента и считаем часы
foreach ($students as $student){
	$log->addStudent($student[1]);
	$connect->qr("select date, hours from `skips` where idStudent = '" . $student[0] . "' and date between '" . $fromDay . "' AND  '" . $today . "';");
	$hours = 0;
	while($data = mysql_fetch_array($connect->qr_res)){
		$hours = $hours + $data['hours'];
		$log->addHours($data['date'], $data['hours']);
	}
	if ($hours > 0) {
		$text = "Ваш+ребёнок+пропустил+за+неделю+" . $hours . "+час.";
		$parentPhone = $student["2"];
		//$body=file_get_contents("http://sms.ru/sms/send?api_id=24f8dacb-859a-8e04-3515-f1d7bd7c3ca8&to=7" . $parentPhone . "&text=".$text);
		}
}

$text = "Родители+оповещены";
//$body=file_get_contents("http://sms.ru/sms/send?api_id=24f8dacb-859a-8e04-3515-f1d7bd7c3ca8&to=79616307282&text=".$text);
?>