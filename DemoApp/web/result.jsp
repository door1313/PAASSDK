<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Demo Results</title>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <link rel="stylesheet" href="/resources/demos/style.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script>
            $(function () {
                $("#accordion").accordion({
                    heightStyle: "content"
                });
            });

            $(function () {
                $("#dialog").dialog({
                    autoOpen: false,
                    width:500, 
                    show: {
                        effect: "blind",
                        duration: 1000
                    },
                    hide: {
                        effect: "explode",
                        duration: 1000
                    }
                });

                $("#opener").on("click", function () {
                    $("#dialog").dialog("open");
                });
            });

            $(function () {
                $("#dialog1").dialog({
                    autoOpen: false,
                    height:300, 
                    width:1600, 
                    show: {
                        effect: "blind",
                        duration: 1000
                    },
                    hide: {
                        effect: "explode",
                        duration: 1000
                    }
                });

                $("#opener1").on("click", function () {
                    $("#dialog1").dialog("open");
                });
            });
            
             $(function () {
                $("#dialog2").dialog({
                    autoOpen: false,
                    width:500, 
                    show: {
                        effect: "blind",
                        duration: 1000
                    },
                    hide: {
                        effect: "explode",
                        duration: 1000
                    }
                });

                $("#opener2").on("click", function () {
                    $("#dialog2").dialog("open");
                });
            });
            
             $(function () {
                $("#dialog3").dialog({
                    autoOpen: false,
                    width:1600, 
                    show: {
                        effect: "blind",
                        duration: 1000
                    },
                    hide: {
                        effect: "explode",
                        duration: 1000
                    }
                });

                $("#opener3").on("click", function () {
                    $("#dialog3").dialog("open");
                });
            });
        </script>
    </head>
    <body>

        <div id="accordion">
            <h3>Get All Instances Result</h3>
            <div>



                <div id="dialog" title="Get All Instances Result Status Code ">
                    <p><td>${requestScope.InstanceStatus}</td></p>
                </div>

                <button id="opener">Status</button>
                <br>
                <br>
                <div id="dialog1" title="Get All Instances Result Content ">
                    <p><td>${requestScope.InstanceContent}</td></p>
                </div>

                <button id="opener1">Content</button>
            </div>


            <h3>Get All Managed Server Results</h3>
            <div>
                <div id="dialog2" title="Get All Managed Server Result Status Code ">
                    <p><td>${requestScope.ManagedServerStatus}</td></p>
                </div>

                <button id="opener2">Status</button>
                
                <br>
                <br>
                <div id="dialog3" title="Get All Managed Server Result Content ">
                    <p><td>${requestScope.ManagedServerContent}</td></p>
                </div>

                <button id="opener3">Content</button>
            </div>



        </div>
    </div>


</body>
</html>