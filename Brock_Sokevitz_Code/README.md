Brock Sokevitz's Code
JDBCBank project 0 - use of jdbc and the DAO design pattern in order to create, retreive, update, and delete data.

ERS project 1 - expand on project 0 by using apache tomcat to host a webapp. I personally used the front controller
design pattern, i think it'd be better if i split my services up more that would make the code for the individual
services less robust and easier to read. It'd also help separate the concerns. The services contact the the dao
and then the dao talks with the database just like in project 0. Also made use of javascript to perform ajax, fetch,
and dynamic page loading. I invalidated the session, but forgot to make superuser reimbursement tables require the
session information to load. Images didn't work for the presentation, but that was a simple change of the javascript that
i broke while trying to get logout to work. I fixed it after the presentation. The program also sends emails to people.
I have my test email's username and password hardcoded into the project, and those should be added to a properties file.
This is just the primary things I can think of to fix. For future reference for myself, I also think that i should handle 
linking the images differently.