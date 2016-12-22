<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:wrapper>
<div class="panel-group" ng-app="newApp" ng-controller="newCtrl">
            <div class="panel panel-default">
                <div class="panel-heading">Thermostate: add new Schedule</div>
                <div class="panel-body">
                    <form class="form-inline" role="form">
                    	<div class="form-group-line">
                    		<div class="col-sm-6">
		                        <label class="control-label col-sm-5" for="horainicio">Init time</label>
		                        <div class="input-group col-sm-6">
			                        <input id="horainicio" type="text" class="form-control input-small"/>
			                        <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
	                        </div>
	                        </div>
	                        <div class="col-sm-6">
	                        	 <label class="control-label col-sm-5" for="horafin">End time</label>
		                         <div class="input-group col-sm-6">
				                    <input id="horafin" type="text" class="form-control input-small"/>
		                        	<div class="input-group-addon">
		                        		<span class="glyphicon glyphicon-time"></span>
		                        	</div>
		                         </div>
	                        </div>
                        </div>
                        <div class="form-group-line">
                    		<div class="col-sm-6">
		                        <label class="control-label col-sm-5" for="horainicio">Init date</label>
		                        <div class="input-group col-sm-6">
			                        <input id="fechainicio" type="text" class="form-control input-small"/>
			                        <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
	                        </div>
	                        </div>
	                        <div class="col-sm-6">
	                        	 <label class="control-label col-sm-5" for="horafin">End date</label>
		                         <div class="input-group col-sm-6">
				                    <input id="fechafin" type="text" class="form-control input-small"/>
		                        	<div class="input-group-addon">
		                        		<span class="glyphicon glyphicon-time"></span>
		                        	</div>
		                         </div>
	                        </div>
                        </div>
                        <div class="form-group-line">
                    		<div class="col-sm-6">
		                        <label class="control-label col-sm-5" for="horainicio">Min Temp</label>
		                        <div class="input-group col-sm-6">
			                        <input id="fechainicio" type="text" class="form-control input-small"/>
			                        <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
	                        </div>
	                        </div>
	                        <div class="col-sm-6">
	                        	 <label class="control-label col-sm-5" for="horafin">Max Temp</label>
		                         <div class="input-group col-sm-6">
				                    <input id="fechafin" type="text" class="form-control input-small"/>
		                        	<div class="input-group-addon">
		                        		<span class="glyphicon glyphicon-time"></span>
		                        	</div>
		                         </div>
	                        </div>
                        </div>
                        <div class="form-group-inline">
						    <div class="col-sm-offset-2 col-sm-10">
						      <button type="submit" class="btn btn-default">Submit</button>
						    </div>
						  </div>
                    </form>
                    <div class="col-sm-12">
                        <table class="table table-striped">
						    <thead>
						      <tr>
						        <th>Time range</th>
						        <th>Date range</th>
						        <th>Temp range</th>
						      </tr>
						    </thead>
						    <tbody>
						      <tr ng-repeat="x in schedules">
						        <td>{%x.startTime%} - {%x.stopTime%}</td>
						        <td>{%x.startDate%} - {%x.endDate%}</td>
						        <td>{%x.minTemp%}</td>
						        <td>
						         	<span class="glyphicon glyphicon-pencil"></span>
									<span class="glyphicon glyphicon-trash"></span>
								</td>
						      </tr>
						    </tbody>
						  </table>
                    </div>
                </div>
           </div>
            <script>
            var mod = angular.module('newApp', []).config(function($interpolateProvider) {
      		    $interpolateProvider.startSymbol('{%');
      		    $interpolateProvider.endSymbol('%}');
            });
                 
            mod.controller("newCtrl",['$scope','$http', function($scope, $http){
            	$scope.add = function(value){
                	$http.post("/Thermostat/site/thermostat/scheduleManager",
                			JSON.stringify({"method":"changetemp", "data": value})
                	).success($scope.schedules = []);
            	};
            	$scope.findSchedules = function(value){
                	$http.post("/Thermostat/site/thermostat/scheduleManager",
                			JSON.stringify({"method":"changetemp", "data": {method:"getAll"}})
                	).success($scope.schedules = []);
            	};
            }]);
            </script>
       </div>    
</t:wrapper>