<%@tag description="User Page template" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${sessionScope.user != null}">
<div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav navbar-left">
	      	<li>
	          <a href="/Thermostat/site/thermostat/schedules">Schedules</a>
	        </li>
	        <li>
	          <a href="/Thermostat/site/stats">Stats</a>
	        </li>
          </ul>
           <ul class="nav navbar-nav navbar-right">
            <!--li class="dropdown">
	          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Help <span class="caret"></span></a>
	          <ul class="dropdown-menu">
	                <li><a href="#contact">Contact</a></li>
	                <li><a href="#about">About</a></li>
	          </ul>
	        </li-->
	        <li><a href="/Thermostat/logout">Log out</a></li>
          </ul>
        </div>
</c:if>