# Group3JavaEE


Create a database in PostgreSQL called group3db. 

Create a connection pool by putting in the following commands in the command window (Don't close it even after writing the commands):
>asadmin

>create-jdbc-connection-pool --datasourceclassname org.postgresql.ds.PGSimpleDataSource --restype javax.sql.XADataSource --property portNumber=5432:password=postgres:user=postgres:serverName=localhost:databaseName=group3db jpa_group3db_postgresql_pool

Go to localhost:4848 > Look at the Common Tasks box > Resources > JDBC > JDBC Connection Pools > click jpa_person_postgresql_pool > press the ping button and pray it will work.

Go back to the command window and put in:
>create-jdbc-resource --connectionpoolid jpa_group3db_postgresql_pool jdbc/group3db

If all of those above steps were completed then they don't need to be done again.

Example data for the database will be generated when the application runs.

All of the user examples have the password "12345" (please don't do this in real life)

To login as a student: is@mail.com

To login as a teacher: aa@mail.com

To login as an admin: jk@mail.com