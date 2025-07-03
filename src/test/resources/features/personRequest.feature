@person
Feature: Person request
  
  Scenario: Person request
	When I send POST request to /api/v1/request/person with request body from person/incoming/request.json
	Then 2s Person defined in person/outgoing/request.json is requested