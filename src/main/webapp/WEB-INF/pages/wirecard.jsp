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
<button id="storage" onclick="readStorage()">Read Storage</button>

<script type="text/javascript">
    var wd = new WirecardCEE_DataStorage();
    wd.buildIframeCreditCard('creditcardDataIframe', '100%', '250px');

    function next() {
        wd.storeCreditCardInformation(null, function (wirecard) {
            var errors = wirecard.getErrors();
            if (errors) {
                for (var error in errors) {
                    console.log(error);
                }
            } else {
                var response = wirecard.response;
                var xhr = new XMLHttpRequest();
                xhr.onreadystatechange = function () {
                    var DONE = this.DONE || 4;
                    if (this.readyState === DONE && this.status === 200) {
                        var data = JSON.parse(this.response);
                        window.location = data.redirectUrl;
                    }
                };
                xhr.open('POST', '/pay', true);
                xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
                xhr.send(JSON.stringify(response));
            }
        });
    }

    function readStorage() {
        wd.storeCreditCardInformation(null, function (wirecard) {
            var errors = wirecard.getErrors();
            if (errors) {
                for (var error in errors) {
                    console.log(error);
                }
            } else {
                var response = wirecard.response;

                var xhr = new XMLHttpRequest();
                xhr.onreadystatechange = function () {
                    var DONE = this.DONE || 4;
                    if (this.readyState === DONE && this.status === 200) {
                        var data = JSON.parse(this.response);
                        console.log(data);
                    }
                };
                xhr.open('POST', '/storage', true);
                xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
                xhr.send(JSON.stringify(response))
            }
        });
    }
</script>
</body>
</html>