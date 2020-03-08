<?php

    //user_like_book.php ? type=[delete_like , insert_like , check_like] & user_id =  & book_id =

    require_once ('DB_Info.php');

    if(! (isset($_GET["book_id"]) && isset($_GET["user_id"]) && isset($_GET["type"])))
    {
        echo "request is not valid";
        exit();
    }

    $type    = $_GET["type"];
    $book_id = (int)$_GET["book_id"];
    $user_id = (int)$_GET["user_id"];

    if($type === "insert_like")
    {
        $cmd = "INSERT INTO `".TBL_LIKE_BOOK."` (BOOK_ID , USER_ID) VALUES (".$book_id.",".$user_id.")";
    }
    else if($type === "delete_like")
    {
        $cmd = "DELETE FROM `".TBL_LIKE_BOOK."` WHERE BOOK_ID = ".$book_id." AND USER_ID = ".$user_id;
    }
    else if($type === "check_like")
    {
        $cmd = "SELECT * FROM `".TBL_LIKE_BOOK."`
                WHERE BOOK_ID = ".$book_id." AND USER_ID = ".$user_id;
    }

    $db = new mysqli(HOST , USER_NAME , PASSWORD , DB_NAME);
    $db->set_charset("utf8");
    if($db->connect_error === true)
    {
        echo "can't connect to database";
        exit();
    }

    $result = $db->query($cmd);

    if($type === "check_like")
    {
        $s_result = $result->fetch_assoc();
        if(isset($s_result))
        {
            echo "true";
        }
        else
        {
            echo "false";
        }
    }
    else
    {
        if($result === true)
        {
            echo "true";
        }
        else if($result === false)
        {
            echo "false";
        }
    }
    $db->close();
?>