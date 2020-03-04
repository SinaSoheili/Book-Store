<?php

    //get_book_comment.php ? book_id =

    require_once ('DB_Info.php');

    if(! isset($_GET["book_id"]))
    {
        echo "request isn't valid";
        exit();
    }

    $book_id = (int)$_GET["book_id"];

    $db_comment = new mysqli(HOST , USER_NAME , PASSWORD , DB_NAME);
    if($db_comment->connect_error === true)
    {
        echo "can not connect to databse";
        exit();
    }

    $cmd = "SELECT * FROM ".TBL_COMMENT." WHERE BOOK_ID = ".$book_id;
    $query_result = $db_comment->query($cmd);
    $s_result = $query_result->fetch_all(MYSQLI_ASSOC);
    print_r(json_encode($s_result));
    $db_comment->close();

?>