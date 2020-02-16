Make sure maven installed when running app from IDE / building war file to deploy in tomcat app server

Application technology stacks:
- Maven as project manager
- Spring Framework
- Postgresql
- Hibernate
- Lombok

All dependency will import and manage by maven

Applicaton properties file:
- datasource.properties
	- for datasource and hibernate properties value
- app.properties
	- for record per page pagination property value

Postgresql table structure:
Postgresql database name : gln
Postgresql gln database schema : gln
- table.sql
	Table name : User
	- id
	- email
	- first_name
	- last_name
	- avatar
	- job
	- dt_create
	- dt_update
	
- sequence.sql
	- sequence for auto increment id