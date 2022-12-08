<?php
    include "config.php";
    $query = "SELECT * FROM `sanpham`";
    $data = mysqli_query($conn, $query);
    $result = array();
    while ($row = mysqli_fetch_array($data)) {
        $result[] = ($row);
    }

    if(!empty($result))
    {
        $array = [
            'success' => true,
            'message' => "successfully",
            'result' => $result
        ];
    }
    else{
        $array = [
            'success' => false,
            'message' => "failed",
            'result' => $result
        ];
    }

    print_r(json_encode($array));
?>