<?php

    //search_book.php ?  search_type = [id | name | autor_name | group_name] & [id | name | autor_name | group_name] =

    require_once ('DB_Info.php');

    if(! isset($_GET["search_type"]))
    {
        echo "request isn't valid";
        exit();
    }

    $search_type = $_GET["search_type"]; // search type can be : id , name , autor_name , group_name
    switch($search_type)
    {
        case "id":

            if(! isset($_GET["id"]))
            {
                echo "request isn't valid";
                exit();
            }
            $cmd = "SELECT * FROM ".TBL_BOOK." WHERE ID = ".$_GET["id"];

            break;

        case "name":

            if(! isset($_GET["name"]))
            {
                echo "request isn't valid";
                exit();
            }
            $cmd = "SELECT * FROM ".TBL_BOOK." WHERE NAME = \"".$_GET["name"]."\"";

            break;

        case "autor_name":

            if(! isset($_GET["autor_name"]))
            {
                echo "request isn't valid";
                exit();
            }
            $cmd = "SELECT * FROM ".TBL_BOOK." WHERE AUTOR_NAME = \"".$_GET["autor_name"]."\"";

            break;

        case "group_name":

            if(! isset($_GET["group_name"]))
            {
                echo "request isn't valid";
                exit();
            }
            $cmd = "SELECT * FROM ".TBL_BOOK." WHERE GROUP_NAME = \"".$_GET["group_name"]."\"";

            break;

        default :
            echo "request isn't valid";
            exit();
    }

    $db_book = new mysqli(HOST , USER_NAME , PASSWORD , DB_NAME);
    if($db_book->connect_error === true)
    {
        echo "can not connect to databse";
        exit();
    }

    $query_result = $db_book->query($cmd);
    $s_result = $query_result->fetch_all(MYSQLI_ASSOC);
    print_r(json_encode($s_result));
    $db_book->close();
?>