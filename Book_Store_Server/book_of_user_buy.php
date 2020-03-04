<?php

    //book_of_user_buy.php ? user_id =

    require_once('DB_Info.php');

    if(! isset($_GET["user_id"]))
    {
        echo "request isn't valid";
        exit();
    }

    $user_id = (int)$_GET["user_id"];
    $db_user_buy = new mysqli(HOST , USER_NAME , PASSWORD , DB_NAME);
    if($db_user_buy->connect_error === true)
    {
        echo "can not connect to databse";
        exit();
    }

    $cmd = "SELECT * FROM ".TBL_USER_BUY." WHERE USER_ID = ".$user_id;
    $query_result = $db_user_buy->query($cmd);
    $s_result = $query_result->fetch_all(MYSQLI_ASSOC);
    print_r(json_encode($s_result));
    $db_user_buy->close();

?>