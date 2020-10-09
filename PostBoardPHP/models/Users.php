<?php
 class Users{
     private $conn;

     public function __construct($db){
         $this->conn = $db;
     }

     public function log_in($username, $hash){
        $query = 'SELECT pk FROM users 
         WHERE username = "' . $username . '" AND password = "' . $hash . '";';

        $result = mysqli_query($this->conn, $query);

        if(mysqli_num_rows($result) == 0){
            return -1;
        }

        return $result['pk'];
     }

     public function register($username, $hash){
         $query = 'INSERT INTO users (username, password) 
         VALUES ("' . $username . '", "' . $hash . '");';

         try{
            mysqli_query($this->conn, $query);
         }catch(Exception $e){
             echo "Error: " . $e->getMessage();
             return -1;
         }

         return $this->log_in($username, $password);
     }
 }