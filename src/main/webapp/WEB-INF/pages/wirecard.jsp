<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Wireframe IFrame</title>
    <script type="text/javascript" src="${storage.javascriptUrl}"></script>
</head>
<body>
    <div id="creditcardDataIframe"></div>
    <button id="next" onclick="next()">Next</button>

    <script type="text/javascript">
        var wd = new WirecardCEE_DataStorage();
        wd.buildIframeCreditCard('creditcardDataIframe', '100%', '250px');
        function next() {
            wd.storeCreditCardInformation(null, function(wirecard) {
                var errors = wirecard.getErrors();
                if(errors) {
                    for(var error in errors) {
                        console.log(error);
                    }
                } else {
                    <%= %>


                    var response = wirecard.response;
                    console.log(response);
                    var xhr = new XMLHttpRequest();
                    xhr.open('POST', '/pay', true);
                    xhr.send(JSON.stringify(response));
                }
            });
        }
    </script>
</body>
</html>