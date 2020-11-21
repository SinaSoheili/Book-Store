<?php

    //insert_user.php ? name = & email =  & phone= [& family_name = & avatar = ]

    require_once('DB_Info.php');

    if(! (isset($_GET['name']) && isset($_GET['email']) && isset($_GET['phone'])))
    {
        echo "request isn't valid";
        exit();
    }

    $name  = $_GET['name'] ;
    $email = $_GET['email'] ;
    $phone = $_GET['phone'] ;

    if(isset($_GET['family_name']))
    {
        $family_name = $_GET['family_name'];
    }

    if(isset($_GET['avatar']))
    {
        $avatar = $_GET['avatar'];
    }

    $database = new mysqli(HOST , USER_NAME , PASSWORD , DB_NAME);
    $database->set_charset("utf8");
    if($database->connect_error === true)
    {
        echo "can not connect to databse";
        exit();
    }
    if(isset($family_nam) && isset($avatar))
    {
        $cmd = "INSERT INTO ".TBL_USER." 
                    (`NAME` , `FAMILY_NAME` ,  `EMAIL` , `PHONE` , `AVATAR`) 
                    VALUES 
                    (\"".$name."\",\"".$family_name."\",\"".$email."\",\"".$phone."\",\"".$avatar."\")";
    }
    else
    {
        $cmd = "INSERT INTO ".TBL_USER." 
                    (`NAME` ,  `EMAIL` , `PHONE`) 
                    VALUES 
                    (\"".$name."\",\"".$email."\",\"".$phone."\")";
    }


    $query_result = $database->query($cmd);
    if($query_result === true) // mean insert is success and retrun user
    {
        $cmd = "SELECT *
                    FROM `user`
                    WHERE EMAIL = \"".$email."\" AND PHONE = \"".$phone."\"";
        $result = $database->query($cmd);
        $s_result = $result->fetch_assoc();
        print_r(json_encode($s_result));
    }
    else // mean insert is failed and retrun null
    {
        print_r(json_encode(null));
    }

    $database->close();
?>