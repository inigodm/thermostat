<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:wrapper>
<link href="/Thermostat/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="/Thermostat/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<div class="panel-group" ng-app="newApp" ng-controller="newCtrl">
            <div class="panel panel-default">
                <div class="panel-heading">Thermostate: stats</div>
                <div class="panel-body">
       				<div id="chart_div" style="width: 100%; height: 500px;"></div>
                </div>
                <div class="col-sm-2 boton big linea boton-left" ng-click="find()"><span class="btn-mas-menos">+</span></div>
           </div>
        <script type="text/javascript" src="/Thermostat/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="/Thermostat/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
		<script type="text/javascript" src="/Thermostat/js/locales/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
            <script>
            var mod = angular.module('newApp', []).config(function($interpolateProvider) {
      		    $interpolateProvider.startSymbol('{%');
      		    $interpolateProvider.endSymbol('%}');
              });
            mod.controller("newCtrl",['$scope', '$timeout',  '$http', '$interval', function($scope, $timeout, $http, $interval){
                $scope.find = function(value){
            	$http.get("/Thermostat/site/stats/get?fromDate=2016-12-26&toDate=2016-12-26&fromHour=00:00&toHour=00:00")
            	.success($scope.doReturnOk);
            	};
            	$scope.doReturnOk = function(resp){
                	alert(resp);
                }
            }]);
            google.charts.load('current', {'packages':['corechart']});
            google.charts.setOnLoadCallback(drawChart);

            function drawChart() {
              var data = google.visualization.arrayToDataTable([
                ['Year', 'Sales', 'Expenses', 'Expenses2'],
                [new Date(2014, 10, 15, 7, 20, 45),  15,      23, 22],
                [new Date(2014, 10, 15, 7, 21, 45),  20,      23, 0],
                [new Date(2014, 10, 15, 7, 22, 45),  18,       12, 1],
                [new Date(2014, 10, 15, 7, 23, 45),  12,      2, 1]
              ]);

              var options = {
                title: 'Company Performance',
                hAxis: {title: 'Year',  titleTextStyle: {color: '#333'}},
                vAxis: {minValue: 0}
              };

              var chart = new google.visualization.AreaChart(document.getElementById('chart_div'));
              chart.draw(data, options);
            }
            </script>
       </div>    
</t:wrapper>