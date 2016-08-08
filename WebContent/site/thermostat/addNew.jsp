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
						      <tr onclick="alert('de')">
						        <td>John</td>
						        <td>Doe</td>
						        <td>john@example.com</td>
						        <td>
						         	<span class="glyphicon glyphicon-pencil"></span>
									<span class="glyphicon glyphicon-trash"></span>
								</td>
						      </tr>
						      <tr>
						        <td>Mary</td>
						        <td>Moe</td>
						        <td>mary@example.com</td>
						        <td>
						         	<span class="glyphicon glyphicon-pencil"></span>
									<span class="glyphicon glyphicon-trash"></span>
								</td>
						      </tr>
						      <tr>
						        <td>July</td>
						        <td>Dooley</td>
						        <td>july@example.com</td>
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
            </div>
            <script src="../js/angular.min.js"></script>
            <script>
            </script>
         </div>
</t:wrapper>