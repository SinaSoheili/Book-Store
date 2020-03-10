<?php
    //is_user_valid.php ? phone= & email=
    require_once('DB_Info.php');

    if(! (isset($_GET["email"]) && isset($_GET["phone"])))
    {
        echo "request isn't valid";
        return;
    }

    $email = $_GET["email"];
    $phone = $_GET["phone"];

    $db = new mysqli(HOST , USER_NAME , PASSWORD , DB_NAME);
    if($db->connect_error === true)
    {
        echo "can't connect to database";
        return;
    }
    $cmd = "SELECT * FROM `".TBL_USER."` WHERE PHONE = \"".$phone."\" AND EMAIL = \"".$email."\"";
    $result = $db->query($cmd);
    $s_result = $result->fetch_assoc();
    if(isset($s_result))
    {
        echo "true";
    }
    else
    {
        echo "false";
    }
    $db->close();
?>