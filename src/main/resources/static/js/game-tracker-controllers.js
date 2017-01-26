angular.module('GameTrackerApp', [])
   .controller('GameTrackerController', function($scope, $http) {

        $scope.testUpload = function() {
            console.log("testUpload()");
            var f = document.getElementById('imageFile').files[0];
            console.log("got a handle to the file object from the HTML: " + f);
            var r = new FileReader();
            r.onloadend = function(e) {
                console.log("File is done loading ...");
                var data = e.target.result;
                console.log(data);
                console.log("Ready to send data via http request");


                $http.post('/test-upload.json', data, {
                    headers: {'Content-Type': undefined },
                    transformRequest: angular.identity
                })
                .success(function() {
                    console.log("it worked! ")
                })
                .error(function() {
                    console.log("it didn't work");
                });

                    //send your binary data via $http or $resource or do anything else with it
            }
            console.log("About to read file as binary string");
            r.readAsBinaryString(f);
            console.log("Done reading file as binary");
        }

        console.log("Initializing SampleController");
    });