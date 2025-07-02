@football
Feature: Football player request
  
  Scenario: Football player request
	When I send POST request to /api/v1/request/football/player with request body from football/player/incoming/request.json
	Then 2s Football players defined in football/player/outgoing/request.json are requested