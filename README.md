CODING CHALLENGE DESCRIPTION

Implement a system to record and calculate royalty payments owed to Rights Owners based on viewing activity of customers. Royalties will be calculated at the Rights Owner level, 
so each episode belonging to a specific Rights Owner will be worth the same amount. The system must meet the provided REST API specification and accept/return JSON.

• Customer viewings will be tracked by a POST to the VIEWING endpoint. 
The system is not concerned about whether a given customer ID is valid, it assumes the consuming system is sending a valid customer ID. The episode ID must be valid and exist in the system for the viewing to be tracked.

• Royalties owed will be listed by a GET to the PAYMENTS endpoint. 
This will return a list of the royalty payments owed to the studios in GBP£.

• Royalties owed to a specific Rights Owner will be returned by a GET to the PAYMENTS/{Rights Owner GUID} endpoint. 
This will return a single object representing the royalty payment owed to the studio in GBP£.

• The Reset endpoint will reset the internal state of the system (setting viewing counters back to 0).

--- API SPEC --
POST /royaltymanager/reset

No req body

No validation

Response

HTTP 202 with empty body

----

POST /royaltymanager/viewing

Req Body

{ 
  "episode": "GUID",
  "customer": "GUID"
}
  
Validation
  
  episode GUID mandatory and must exist in system
  customer GUID mandatory, no further validation
  
Response

HTTP 202 with empty body

----

GET /royaltymanager/payments

Response

HTTP 200

Response Body

{ [
	{
	  "rightsownerId": "GUID",
	  "rightsowner": "Name"
	  "royalty:" Value in GBP£ e.g 15000.23,
	  "viewings": number of viewings
	},
	...
] }

----

GET /royaltymanager/payments/{Rights Owner GUID}

Response

HTTP 200

Response Body

	{
	  "rightsowner: "Name",
	  "royalty": Value in £ e.g 15000.23,
	  "viewings": number of viewings
	}
	
No GUID validation, return 404 on GUID not found.