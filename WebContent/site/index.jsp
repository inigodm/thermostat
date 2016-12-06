<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:wrapper>
<div class="panel-group" ng-app="newApp" ng-controller="newCtrl">
            <div class="panel panel-default">
                <div class="panel-heading col-sm-12">
	                    <div class="col-sm-2"></div>
	                    <div class="col-sm-1">
	                    	<div ng-class="ngcircle"></div>
	                    </div>
                        <div class="col-sm-8">      
                        	Thermostate <span  class="lcd-text"><c:out value="{%roomTemp%}"/></span>ºC
                        </div>
                        <div class="col-sm-2"></div>
	            </div>
                <div class="panel-body">
                    <div class="col-sm-12" style="line-height: 10em;">
                        <div class="col-sm-2">
                        </div>
                        <div class="col-sm-2 boton big linea boton-left" ng-click="add(1)"><span>+</span></div>
                        <div id="temp" class="col-sm-4 lcd-text-lg big linea">
                            <span id="valor1" class="digito">{%valor.substring(0,1)%}</span>
                            <span id="valor2" class="digito">{%valor.substring(1)%}</span>
                        </div>
                        <div class="col-sm-2 boton big linea boton-rigth"  ng-click="add(-1)"><span>-</span></div>
                        <div class="col-sm-2">
                        	<span>{%state%}</span>
                        </div>
                    </div>
                </div>
            </div>
            <script>
              var MAX_COLOR = 255; 
              var MIN_COLOR = 50;
              var FIXED_COLOR = 255;
              var initColor = [MIN_COLOR,MIN_COLOR,MAX_COLOR];
              var tempmin = 10;
              var tempmax = 30;
              var flag=false;
              var gap = (MAX_COLOR - MIN_COLOR) / (tempmax - tempmin);
              var mod = angular.module('newApp', []).config(function($interpolateProvider) {
      		    $interpolateProvider.startSymbol('{%');
      		    $interpolateProvider.endSymbol('%}');
              });
              mod.controller("newCtrl",['$scope', '$timeout',  '$http', '$interval', function($scope, $timeout, $http, $interval){
                $scope.valor = "" + tempmin;
                $scope.val = tempmin;
                $scope.ponerTemperaturaMarcador = function(){
                    $scope.valor = paddy($scope.val, 2);
                    $scope.color=calcColor($scope.val);
                    $('#temp').css('background-color', $scope.color);
                };
                $scope.getTemps = check;
                function check(){
                	$scope.add(0);
                }
                $scope.add = function(value){
                	$http.post("/Thermostat/site/thermostat/changer",
                			JSON.stringify({"method":"changetemp", "data": value})
                	).success($scope.manage_thermostatInfo);
                };
                $scope.manage_thermostatInfo = function(resp){
                	$scope.val = resp.desiredTemp;
                	$scope.roomTemp = resp.roomTemp;
                	var isOn = resp.isOn;
                	if (isOn || isOn=="true"){
                		$scope.ngcircle="circle-red";
                	}else{
                		$scope.ngcircle="circle-green";
                	}
                    $scope.ponerTemperaturaMarcador();
                }
                //TODO: Esto no lo hace bien, probar en el interval
                $scope.add(0);
                $interval(function(){$scope.getTemps();}, 10000);
                //TODO: en la raspberry solo
                //$interval(function(){$scope.getRoomTemp();}, 1000);
              }]
            );
              
            
            function paddy(n, p, c) {
                    var pad_char = typeof c !== 'undefined' ? c : '0';
                    var pad = new Array(1 + p).join(pad_char);
                    return (pad + n).slice(-pad.length);
                }
                
            function calcColor(valor){
                if (valor > tempmax){
                    return arrayToColor([MAX_COLOR, FIXED_COLOR, MIN_COLOR]);
                }
                if (valor > tempmin){
                    var toadd = Math.floor(((valor - tempmin) * gap));
                    return arrayToColor([(MIN_COLOR + toadd) , FIXED_COLOR, (MAX_COLOR - toadd) ]);
                }
                return arrayToColor([MIN_COLOR, FIXED_COLOR, MAX_COLOR]);
            }
            
            function arrayToColor(arr){
                return 'rgb('+arr[0]+','+arr[1]+','+arr[2]+')'
            }
            </script>
         </div>
</t:wrapper>