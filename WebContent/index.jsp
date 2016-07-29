<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:wrapper>
<div class="panel-group" ng-app="newApp" ng-controller="newCtrl">
            <div class="panel panel-default">
                <div class="panel-heading">Thermostate <span  class="lcd-text"><c:out value="${cpuTemp}"/></span>ºC</div>
                <div class="panel-body">
                    <div class="col-sm-12" style="line-height: 10em;">
                        <div class="col-sm-2"></div>
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
                    <div class="col-sm-12">
                        <div class="col-sm-2"></div>
                        <div class="col-sm-8">
                        <span>Current CPU Temperature: <span  class="lcd-text"><c:out value="${cpuTemp}"/></span>ºC</span>
                        </div>
                        <div class="col-sm-2"></div>
                    </div>
                </div>
            </div>
            <script src="/js/angular.min.js"></script>
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
              mod.controller("newCtrl",['$scope', '$timeout', '$interval', function($scope, $timeout, $interval){
                $scope.valor = "" + tempmin;
                $scope.currentCPUTemp="";
                $scope.val = tempmin;
                $scope.state = 'Off';
                $scope.add = function(newValue){
                    //if ($scope.val + newValue > tempmax && $scope.val + newValue < tempmin) return;
                    //$scope.val = $scope.val + newValue;
                    $scope.changeTemp(newValue);
                    //$scope.ponerTemperaturaMarcador();
                };
                $scope.ponerTemperaturaMarcador = function(){
                    $scope.valor = paddy($scope.val, 2);
                    $scope.color=calcColor($scope.val);
                    $('#temp').css('background-color', $scope.color);
                };
                // test sensor
                $scope.getCPUTemp = function(){
                    Dajaxice.thermostat.getValue($scope.callback_getCPUTemp);
                };
                $scope.getRoomTemp = function(){
                    Dajaxice.thermostat.getRoomThemperature($scope.callback_getRoomTemp);
                };
                $scope.changeTemp = function(toAdd){
                	Dajaxice.thermostat.changeTemp($scope.callback_changeTemp, {'value':toAdd});
                };
                $scope.callback_changeTemp = function(data){
                    $scope.val = data.desiredTemp;
                    $scope.state = data.state;
                    $scope.ponerTemperaturaMarcador();
                };
                $scope.callback_getCPUTemp = function(data){
                	$scope.currentCPUTemp = data.temp;
                	$scope.val = data.desiredTemp;
                    $scope.state = data.state;
                    $scope.ponerTemperaturaMarcador();
                };
                $scope.callback_getRoomTemp = function(data){
                    $scope.currentRoomTemp = data;
                };
                //TODO: Esto no lo hace bien, probar en el interval
                $scope.add(0);
                $interval(function(){$scope.getCPUTemp();}, 10000);
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