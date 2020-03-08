<?php
    //insert_book_comment.php ? content=  & date= & user_id=  &book_id= 
    require_once('DB_Info.php');

    if(! (isset($_GET["content"]) && isset($_GET["date"]) && isset($_GET["user_id"]) && isset($_GET["book_id"])))
    {
        echo 'request is not valid';
        exit();
    }

    $content = $_GET["content"];
    $date    = $_GET["date"];
    $user_id = $_GET["user_id"];
    $book_id = $_GET["book_id"];

    $db = new mysqli(HOST , USER_NAME , PASSWORD , DB_NAME);
    $db->set_charset("utf8");
    if($db->connect_error === true)
    {
        echo "can't connect to server";
        exit();
    }
    $cmd = "INSERT INTO `".TBL_COMMENT."` 
                    (CONTENT , DATE , USER_ID , BOOK_ID)
                     VALUES 
                    (\"".$content."\",\"".$date."\",".$user_id.",".$book_id.")";
    $result = $db->query($cmd);
    if($result === true)
    {
        echo "true";
    }
    else
    {
        echo "false";
    }
?>