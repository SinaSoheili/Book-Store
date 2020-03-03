<?php
    //user_get_specifications.php ? user_id =
    require_once('DB_Info.php');

    if(! isset($_GET["user_id"]))
    {
        echo "request isn't valid";
        exit();
    }

    $user_id = (int)$_GET["user_id"];
    $db_user = new mysqli(HOST , USER_NAME , PASSWORD , DB_NAME);
    if($db_user->connect_error === true)
    {
        echo "can not connect to databse";
        exit();
    }

    $cmd = "SELECT * FROM ".TBL_USER." WHERE ID = ".$user_id;
    $query_result = $db_user->query($cmd);
    $s_result = $query_result->fetch_assoc();
    print_r(json_encode($s_result));

?>