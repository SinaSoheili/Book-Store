<?php

    //book_list.php ? type = [top_discount | newest | favoriot | top_sell]

    require_once('DB_Info.php');

    if(! isset($_GET["type"]))
    {
        echo "request isn't valid";
        exit();
    }

    $type = $_GET["type"]; //type can be [top_discount | newest | favoriot | top_sell]
    switch($type)
    {
        case "top_discount":
            $cmd = "SELECT book.ID , book.NAME , book.PRICE , book.FRONT_PIC , book.DISCOUNT_CODE 
                        FROM ".TBL_BOOK." 
                        INNER JOIN `discount` ON book.DISCOUNT_CODE = discount.DISCOUNT_ID
                        ORDER BY discount.PERSENT DESC
                        LIMIT 20";
            break;

        case "newest":
            $cmd = "SELECT book.ID , book.NAME , book.PRICE , book.FRONT_PIC , book.DISCOUNT_CODE
                        FROM ".TBL_BOOK." 
                        ORDER BY ID DESC LIMIT 20";
            break;

        case "favoriot":
            $cmd = "SELECT book.ID , book.NAME , book.PRICE , book.FRONT_PIC , book.DISCOUNT_CODE
                        FROM ".TBL_BOOK."
                        ORDER BY LIKE_COUNT DESC
                        LIMIT 20";
            break;

        case "top_sell":
            $cmd = "SELECT book.ID , book.NAME , book.PRICE , book.FRONT_PIC , book.DISCOUNT_CODE
                        FROM ".TBL_BOOK." 
                        INNER JOIN `user_buy` ON book.ID = user_buy.BOOK_ID
                        ORDER BY user_buy.COUNT DESC
                        LIMIT 20";
            break;

        default :
            echo "request isn't valid";
            exit();
    }

    $database = new mysqli(HOST , USER_NAME , PASSWORD , DB_NAME);
    if($database->connect_error === true)
    {
        echo "can not connect to databse";
        exit();
    }

    $query_result = $database->query($cmd);
    $s_result = $query_result->fetch_all(MYSQLI_ASSOC);
    print_r(json_encode($s_result));
    $database->close();
?>