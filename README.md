# QUOTES

To run this application MySql database should be installed.

Modify properties file by adding local user and psw to be connected with database.

update path for log4j (referring to local dir)

App will start on port 8080.

Use link below to open swagger documentation: http://localhost:8080/swagger-ui/index.html

ps: Mainly I have relied on into building apis, and so I haven't put test (mockito).

External service to retrieve list of quotes is triggered on start of project as a @postconstruct, values are inserted on table. 
