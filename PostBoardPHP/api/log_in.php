<?php
header('Access-Control-Allow-Origin: *');
header('Content-Type: applicaution/json');
header('Access-Control-Allow-Methods: POST');
header('Access-Control-Allow-Headers: Access-Control-Allow-Headers, Content-Type,Access-Control_Allow-Methods, Authorization, X-Requested-With');

include_once __DIR__.'\..\config\Database.php';
include_once __DIR__.'\..\models\Users.php';

$database = new Database();
$db = $database->connect();

$users = new Users($db);

$data = json_decode(file_get_contents("php://input"));

$userKey = $users->log_in($data->username, $data->hash);

if($userKey == -1){
    echo json_encode(array('message' => 'log in failed', 'key' => $userKey));
}else{
    echo json_encode(array('message' => 'log in succeeded', 'key' => $userKey));
}

