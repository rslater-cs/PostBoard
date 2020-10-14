<?php
    class Database{
        private $ENDPOINT = "test-db.cqmmammonmll.us-east-2.rds.amazonaws.com";
        private $DB_NAME = "testdb";
        private $USER = "SLTR";
        private $PASSWORD = "masterpass";
        
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