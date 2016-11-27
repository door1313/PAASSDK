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

            
        </script>
    </head>
    <body>

        <div id="accordion">
            <h3>Get All Instances Result</h3>
            <div>
                <p><td>${requestScope.AllInstanceResults}</td></p>
            </div>


            <h3>Get All Managed Server Results</h3>
            <div>
                <p><td>${requestScope.AllManagedServerResults}</td></p>
            </div>



        </div>
    </div>


</body>
</html>