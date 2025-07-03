@football
Feature: Football requests
  
  Scenario: Football coach request
	When I send POST request to /api/v1/request/football/coach with request body from football/coach/incoming/request.json
	Then 2s Football coaches defined in football/coach/outgoing/request.json are requested
  
  Scenario: Football player request
	When I send POST request to /api/v1/request/football/player with request body from football/player/incoming/request.json
	Then 2s Football players defined in football/player/outgoing/request.json are requested
  
  Scenario: Football team request
	When I send POST request to /api/v1/request/football/team with request body from football/team/incoming/request.json
	Then 2s Football teams defined in football/team/outgoing/request.json are requested