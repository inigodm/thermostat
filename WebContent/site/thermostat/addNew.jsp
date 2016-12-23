<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:wrapper>
<link href="/Thermostat/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="/Thermostat/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<div class="panel-group" ng-app="newApp" ng-controller="newCtrl">
            <div class="panel panel-default">
                <div class="panel-heading">Thermostate: add new Schedule</div>
                <div class="panel-body">
                    <form class="form-inline" role="form">
                    	<div class="form-group-line">
                    		<div class="col-sm-6">
		                        <label class="control-label col-sm-5" for="horainicio">Init date</label>
		                        <div class="input-group col-sm-6">
			                       <div class="input-append date form_date" id="horainicio" data-date="" data-date-format="dd-mm-yyyy">
								        <input class="span2" size="16" type="text" value="">
								        <span class="add-on"><i class="icon-remove"></i></span>
								        <span class="add-on"><i class="icon-th"></i></span>
								    </div>  
	                        </div>
	                        </div>
	                        <div class="col-sm-6">
	                        	 <label class="control-label col-sm-5" for="horafin">End date</label>
		                         <div class="input-group col-sm-6">
				                    <div class="input-append date form_date" id="horafin" data-date="" data-date-format="dd-mm-yyyy">
								        <input class="span2" size="16" type="text" value="">
								        <span class="add-on"><i class="icon-remove"></i></span>
								        <span class="add-on"><i class="icon-th"></i></span>
								    </div>  
		                         </div>
	                        </div>
                        </div>
                        <div class="form-group-line">
                    		<div class="col-sm-6">
		                        <label class="control-label col-sm-5" for="fechainicio">Init time</label>
		                        <div class="input-group col-sm-6">
			                        <div id="fechainicio" class="controls input-append date form_time" data-date="" data-date-format="hh:ii" data-link-field="dtp_input3" data-link-format="hh:ii">
								        <input class="span2" size="16" type="text" value="" readonly>
								        <span class="add-on"><i class="icon-remove"></i></span>
								        <span class="add-on"><i class="icon-th"></i></span>
								    </div>     
								    <input type="hidden" id="dtp_input3" value="" />       
	                        </div>
	                        </div>
	                        <div class="col-sm-6">
	                        	 <label class="control-label col-sm-5" for="fechafin">End time</label>
		                         <div class="input-group col-sm-6">
				                    <div id="fechafin" class="controls input-append date form_time" data-date="" data-date-format="hh:ii" data-link-field="dtp_input4" data-link-format="hh:ii">
								        <input class="span2" size="16" type="text" value="" readonly>
								        <span class="add-on"><i class="icon-remove"></i></span>
								        <span class="add-on"><i class="icon-th"></i></span>
								    </div>  
		                         </div>
		                         <input type="hidden" id="dtp_input4" value="" />   
	                        </div>
                        </div>
                        <div class="form-group-line">
                    		<div class="col-sm-6">
		                        <label class="control-label col-sm-5" for="mintemp">Min Temp</label>
		                        <div class="input-group col-sm-6">
			                        <input id="mintemp" type="number" value="15" class="form-control input-small"/>
	                        </div>
	                        </div>
	                        <div class="col-sm-6">
		                        <label class="control-label col-sm-5" for="active">Active</label>
		                        <input id="active" type="checkbox" value="true" class="form-control input-small"/>
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
						        <th>Date range</th>
						        <th>Time range</th>
						        <th>Temperature</th>
						      </tr>
						    </thead>
						    <tbody>
						      <tr ng-repeat="x in schedules">
						        <td>{%x.startDate%} - {%x.endDate%}</td>
						        <td>{%x.startTime%} - {%x.stopTime%}</td>
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
        <script src="/Thermostat/js/jquery-2.2.0.min.js"></script>
		<script type="text/javascript" src="/Thermostat/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="/Thermostat/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
		<script type="text/javascript" src="/Thermostat/js/locales/bootstrap-datetimepicker.fr.js" charset="UTF-8"></script>
            <script>
            $('.form_time').datetimepicker({
            weekStart: 1,
            todayBtn:  1,
    		autoclose: 1,
    		todayHighlight: 1,
    		startView: 1,
    		minView: 0,
    		maxView: 1,
    		forceParse: 0
       		 });
            //$('.form_date').datetimepicker({format: 'yyyy-mm-dd hh:ii'});
            $('.form_date').datetimepicker({
            weekStart: 1,
            todayBtn:  1,
    		autoclose: 1,
    		todayHighlight: 1,
    		startView: 2,
    		minView: 2,
    		forceParse: 0
        	});
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