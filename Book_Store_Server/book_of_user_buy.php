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
    $db_user_buy->set_charset("utf8");
    if($db_user_buy->connect_error === true)
    {
        echo "can not connect to databse";
        exit();
    }

    $cmd = "SELECT book.AUTOR_NAME     , book.AVAILABLE_COUNT , book.BACK_PIC   , 
                   book.DISCOUNT_CODE  , book.FRONT_PIC       , book.GROUP_NAME , 
                   book.ID             , book.NAME            , book.PAGE_COUNT , 
                   book.PRICE          , book.PRINT_YEAR      , book.PUBLISHER  , 
                   book.SHABAK         , book.SUMMERY         , book.TRANSLATOR 
            FROM book
            INNER JOIN user_buy 
            ON user_buy.BOOK_ID = book.ID
            WHERE user_buy.USER_ID = ".$user_id;
    $query_result = $db_user_buy->query($cmd);
    $s_result = $query_result->fetch_all(MYSQLI_ASSOC);
    print_r(json_encode($s_result));
    $db_user_buy->close();

?>