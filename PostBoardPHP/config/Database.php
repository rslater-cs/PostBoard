<?php
    class Database{
        private $ENDPOINT = "";
        private $DB_NAME = "";
        private $USER = "";
        private $PASSWORD = "";
        
        private $conn;

        public function connect(){
            $this->conn = null;

            try{
                $this->conn = mysqli_connect($this->ENDPOINT, $this->USER, 
                                            $this->PASSWORD, $this->DB_NAME);
            }catch(Exception $e){
                echo 'Connection Error: ' . $e->getMessage();
            }

            return $this->conn;
        }
    }
?>